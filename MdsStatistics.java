/* Copyright 2015 Wouter Spekkink
Authors : Wouter Spekkink <wouterspekkink@gmail.com>
Website : http://www.wouterspekkink.org
DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
Copyright 2015 Wouter Spekkink. All rights reserved.
The contents of this file are subject to the terms of either the GNU
General Public License Version 3 only ("GPL") or the Common
Development and Distribution License("CDDL") (collectively, the
"License"). You may not use this file except in compliance with the
License. You can obtain a copy of the License at
http://gephi.org/about/legal/license-notice/
or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
specific language governing permissions and limitations under the
License. When distributing the software, include this License Header
Notice in each file and include the License files at
/cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
License Header, with the fields enclosed by brackets [] replaced by
your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"
If you wish your version of this file to be governed by only the CDDL
or only the GPL Version 3, indicate your decision by adding
"[Contributor] elects to include this software in this distribution
under the [CDDL or GPL Version 3] license." If you do not indicate a
single choice of license, a recipient has the option to distribute
your version of this file under either the CDDL, the GPL Version 3 or
to extend the choice of license to its licensees as provided above.
However, if you add GPL Version 3 code and therefore, elected the GPL
Version 3 license, then the option applies only if the new code is
made subject to such option by the copyright holder.
Contributor(s): Wouter Spekkink

The plugin makes use of the MDSJ library, which is available under the Creative Commons License "by-nc-sa" 3.0.
Link to license: http://creativecommons.org/licenses/by-nc-sa/3.0/
Ref: "Algorithmics Group. MDSJ: Java Library for Multidimensional Scaling (Version 0.2). 
Available at http://www.inf.uni-konstanz.de/algo/software/mdsj/. University of Konstanz, 2009."

*/
package org.wouterspekkink.mdsstatistics;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.data.attributes.api.AttributeOrigin;
import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.data.attributes.api.AttributeType;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;
import org.gephi.statistics.spi.Statistics;
import mdsj.MDSJ;
import mdsj.StressMinimization;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.EdgeIterable;
import org.gephi.graph.api.GraphController;
import org.openide.util.Lookup;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;


/**
 * The plugin makes use of the MDSJ library, which is available under the Creative Commons License "by-nc-sa" 3.0.
 * Link to license: http://creativecommons.org/licenses/by-nc-sa/3.0/
 * Ref: "Algorithmics Group. MDSJ: Java Library for Multidimensional Scaling (Version 0.2). 
 * Available at http://www.inf.uni-konstanz.de/algo/software/mdsj/. University of Konstanz, 2009."
 *
 * For the calculation of shortest paths the plugin uses the algorithm originally used by Gephi as a step in
 * the calculation of centrality metrics.
 * 
 * @author wouter
 */


public class MdsStatistics implements Statistics, LongTask {
    
    /* Currently I am assuming that we're working with only two dimensions
    Later I might want to add an iterator to determine the number of
    dimensions to be used. */
    
    public static final String DIM_1 = "Dimension_1";
    public static final String DIM_2 = "Dimension_2";
    public static final String WEIGHT = "Weight";
    
    private double stress;
    
    private int N;
    
    //Doesn't do anything now.
    private boolean isDirected;

    private boolean useWeights =  false;
    
    //Distance matrix for path distances
    double [][] pathDistances;
    
    //Weight matrix for MDS calculations; check whether it works as expected.
    double [][] weightMatrix; 
    
    //When using weights for distances
    double [][] weightDist;
    
    private ProgressTicket progress;
    private boolean isCanceled;

    
    public MdsStatistics() {
        GraphController graphController = Lookup.getDefault().lookup(GraphController.class);
        if (graphController != null && graphController.getModel()!= null) {
            isDirected = graphController.getModel().isDirected();
        }
    }

    @Override
    public void execute(GraphModel graphModel, AttributeModel attributeModel) {
        Graph graph = null;
        isDirected = graphModel.isDirected();
        if (isDirected) {
            graph = graphModel.getDirectedGraphVisible();
        } else {
            graph = graphModel.getUndirectedGraphVisible();
        }
        execute(graph, attributeModel);
    }
  
    public void execute(Graph hgraph, AttributeModel attributeModel) {
        isCanceled = false;
        //Look if the result column already exist and create it if needed
        AttributeTable nodeTable = attributeModel.getNodeTable();
        AttributeTable edgeTable = attributeModel.getEdgeTable();
        AttributeColumn col1 = nodeTable.getColumn(DIM_1);
        AttributeColumn col2 = nodeTable.getColumn(DIM_2);
        AttributeColumn weight = edgeTable.getColumn(WEIGHT);
        if (col1 == null && col2 == null) {
            col1 = nodeTable.addColumn(DIM_1, "Dimension_1", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, 0.0);
            col2 = nodeTable.addColumn(DIM_2, "Dimension_2", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, 0.0);
        }

        //Lock to graph. This is important to have consistent results if another
        //process is currently modifying it.
        hgraph.readLock();
        
        N = hgraph.getNodeCount();
        HashMap<Node, Integer> indicies = createIndiciesMap(hgraph);
                
        pathDistances = new double[N][N];
        weightMatrix = new double[N][N];
        // Initialize the path distances
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pathDistances[i][j] = 0;
            }
        }
            
        //Check whether the length-method works like it should.
        pathDistances = calculateDistanceMetrics(hgraph, indicies, isDirected);
            
        //Need something here to calculate coordinates
        weightMatrix = StressMinimization.weightMatrix(pathDistances, -2);
        double output [][] = MDSJ.stressMinimization(pathDistances, weightMatrix);
        stress = StressMinimization.normalizedStress(pathDistances, weightMatrix, output);
          
        //Iterate on all nodes and store coordinates
        for (Node n : hgraph.getNodes()) {
            int n_index = indicies.get(n);
            n.getAttributes().setValue(col1.getIndex(), output[0][n_index]);
            n.getAttributes().setValue(col2.getIndex(), output[1][n_index]);
        }
        hgraph.readUnlock();
    }
    // Let's see how I can use the stuff below
    //public Map<String, double[]> calculateDistanceMetrics(Graph hgraph, HashMap<Node, Integer> indicies, boolean directed, boolean normalized) {
    // Does not work yet.
    public double[][] calculateDistanceMetrics(Graph hgraph, HashMap<Node, Integer> indicies, boolean directed) {
        int n = hgraph.getNodeCount();
        
        double [][] distances = new double[n][n];
        
        Progress.start(progress, hgraph.getNodeCount());
        int count = 0;
        
        for (Node s : hgraph.getNodes()) {
            Stack<Node> S = new Stack<Node>();

            LinkedList<Node>[] P = new LinkedList[n];
            double[] d = new double[n];
            
            int s_index = indicies.get(s);
            
            setInitParametersForNode(s, P, d, s_index, n);

            LinkedList<Node> Q = new LinkedList<Node>();
            Q.addLast(s);
            while (!Q.isEmpty()) {
                Node v = Q.removeFirst();
                S.push(v);
                int v_index = indicies.get(v);

                EdgeIterable edgeIter = getEdgeIter(hgraph, v, directed);

                for (Edge edge : edgeIter) {
                    Node reachable = hgraph.getOpposite(v, edge);
                    double currentWeight = edge.getWeight();
                    int r_index = indicies.get(reachable);
                    if (d[r_index] < 0) {
                        Q.addLast(reachable);
                        if (useWeights) {
                            d[r_index] = d[v_index] + (1 / currentWeight);
                        } else {
                            d[r_index] = d[v_index] + 1;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {              
                if (d[i] > 0) {
                    distances[s_index][i] = d[i];
                }
            }
            count++;
            if(isCanceled) {
                hgraph.readUnlockAll();
                return distances;
            }
            Progress.progress(progress, count);
        }
        return distances;
    }
    private void setInitParametersForNode(Node s, LinkedList<Node>[] P, double[] d, int index, int n) {           
            for (int j = 0; j < n; j++) {
                P[j] = new LinkedList<Node>();
                d[j] = -1;
            }
            d[index] = 0;
    }
    
    private EdgeIterable getEdgeIter(Graph hgraph, Node v, boolean directed) {
            EdgeIterable edgeIter = null;
            if (directed) {
                edgeIter = ((DirectedGraph) hgraph).getEdges(v);
            } else {
                edgeIter = hgraph.getEdges(v);
            }
            return edgeIter;
    }
    
    public  HashMap<Node, Integer> createIndiciesMap(Graph hgraph) {
       HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
        int index = 0;
        for (Node s : hgraph.getNodes()) {
            indicies.put(s, index);
            index++;
        } 
        return indicies;
    }
     
    public void setDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }
    
    public void setWeights(boolean useWeights) {
        this.useWeights = useWeights;
    }
    
    public boolean isWeights() {
        return useWeights;
    }
    
        @Override
    public String getReport() {
        //This is the HTML report shown when execution ends. 
        //One could add a distribution histogram for instance
        String report = "<HTML> <BODY> <h1>Stress value</h1> "
                + "<hr>"
                + "<br> Stress of this MDS configuration: " + stress + "<br />"
                + "<br> <br />"
                + "</BODY></HTML>";
        return report;
    }
    
    @Override
    public boolean cancel() {
        this.isCanceled = true;
        return true;
    }
    
    @Override
    public void setProgressTicket(ProgressTicket progressTicket) {
    this.progress  = progressTicket;
    }
}
