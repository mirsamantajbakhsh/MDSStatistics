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
public class MdsStatisticsPanel extends javax.swing.JPanel {

    /** Creates new form MdsStatisticsPanel */
    public MdsStatisticsPanel() {
        initComponents();
    }

    public void setUseWeights(boolean useWeights) {
        useWeightsButton.setSelected(useWeights);
    }
    
    public boolean isUseWeights() {
        return useWeightsButton.isSelected();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        useWeightsButton = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jTextField1.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jTextField1.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel1.text_1")); // NOI18N

        useWeightsButton.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.useWeightsButton.text")); // NOI18N
        useWeightsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useWeightsButtonActionPerformed(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel6.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel7.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel8.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel9.text")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel10.text")); // NOI18N
        
        jLabel11.setText(org.openide.util.NbBundle.getMessage(MdsStatisticsPanel.class, "MdsStatisticsPanel.jLabel11.text"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(useWeightsButton)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(useWeightsButton)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useWeightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useWeightsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_useWeightsButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox useWeightsButton;
    // End of variables declaration//GEN-END:variables
}
