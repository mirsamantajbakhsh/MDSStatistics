This plugin performs a Multidimensional Scaling Analysis, using the Stress Minimization algorithm included in the MDSJ library. The MDSJ library was created by Algorithmics Group of the University of Konstanz (see reference below). The MDSJ library was made available under the Creative Commons License "by-nc-sa" 3.0. The license is available at http://creativecommons.org/licenses/by-nc-sa/3.0/.

The author of the plugin for Gephi was not involved in making the MDSJ library.

The plugin uses path distances between nodes as input for the Multidimensional Scaling analysis, and it assigns geometric coordinates to the nodes in such a way that nodes with shorter path distances are close together and nodes with longer path distances are far apart. The plugin also reports a stress value that indicates the fit of the configuration. The lower the stress, the better the configuration represents that actual path distances between the nodes.

Optionally, edge weights can be taken into account. Edges with higher weights are then interpreted as being shorter than edges with lower weights. This will cause nodes with higher edge weights to generally be closer together in the resulting configuration.

The direction of edges/paths is currently ignored.

The plugin produces coordinates on two dimensions and assigns these to the nodes in the nodes list (see the data laboratory). The coordinates can then be used by the MDS_Layout plugin (needs to be downloaded separately).

Reference:
Algorithmics Group. MDSJ: Java Library for Multidimensional Scaling (Version 0.2). Available at http://www.inf.uni-konstanz.de/algo/software/mdsj/. University of Konstanz, 2009.
