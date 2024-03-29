
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csa
 */
public class server extends javax.swing.JFrame {

    /**
     * Creates new form server
     */
     public server() {
        initComponents();
        Show_Server_In_JTable();
        showTotal();
        
    }
     
public Connection getConnection() {
    
     
        try {
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sale_db","root","lkm042000");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}

public boolean checkInputs(){
    if (txt2_Amt.getText() == null ){
        return false;
    }
    else{
        try{
            Float.parseFloat(txt2_Amt.getText());
            return true;
        }catch(NumberFormatException ex)
        {
            return false;
        }
    }
}

 public ArrayList<Serverc> getServerList()
    {
            ArrayList<Serverc> serverList  = new ArrayList<>();
            Connection con = getConnection();
            String query = "SELECT * FROM server";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Serverc server;
            
            while(rs.next())
            {
                server = new Serverc(rs.getInt("id"),Float.parseFloat(rs.getString("amount")),rs.getString("server"));
                serverList.add(server);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return serverList; 
                
    }
    
    
    //      2 - Populate The JTable
    
    public void Show_Server_In_JTable()
    {
        ArrayList<Serverc> list = getServerList();
        DefaultTableModel model = (DefaultTableModel)JTable_Server.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getServer();
            row[2] = list.get(i).getAmount();
            
            model.addRow(row);
        }
    
    }
    
    // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt2_id.setText(Integer.toString(getServerList().get(index).getId()));
            txt2_Amt.setText(Float.toString(getServerList().get(index).getAmount()));
    }
    
    public void showTotal() {
        float tserver = 0;
       
        Connection con = getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select sum(amount) from server;");
            
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tserver = (float) rs.getLong(1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(Todays_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        serv.setText(String.valueOf(tserver));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        BackPButton = new javax.swing.JButton();
        txt2_id = new javax.swing.JTextField();
        label1_Amt = new javax.swing.JLabel();
        txt2_Amt = new javax.swing.JTextField();
        label1_Amt1 = new javax.swing.JLabel();
        update2 = new javax.swing.JButton();
        serv = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable_Server = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        BackPButton.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        BackPButton.setText("Back");
        BackPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPButtonActionPerformed(evt);
            }
        });
        BackPButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BackPButtonKeyPressed(evt);
            }
        });

        txt2_id.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txt2_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2_idKeyPressed(evt);
            }
        });

        label1_Amt.setBackground(new java.awt.Color(255, 255, 0));
        label1_Amt.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label1_Amt.setText("ID :");

        txt2_Amt.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txt2_Amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2_AmtKeyPressed(evt);
            }
        });

        label1_Amt1.setBackground(new java.awt.Color(255, 255, 0));
        label1_Amt1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label1_Amt1.setText("AMOUNT : ");

        update2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        update2.setText("UPDATE");
        update2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update2ActionPerformed(evt);
            }
        });
        update2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                update2KeyPressed(evt);
            }
        });

        serv.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setText("TOTAL  :");

        JTable_Server.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Server", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTable_Server.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ServerMouseClicked(evt);
            }
        });
        JTable_Server.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTable_ServerKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTable_ServerKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(JTable_Server);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(109, Short.MAX_VALUE)
                                .addComponent(label1_Amt)
                                .addGap(70, 70, 70)
                                .addComponent(txt2_id, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(BackPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(label1_Amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(txt2_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(141, 141, 141)
                                        .addComponent(update2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(serv, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(BackPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(label1_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1_Amt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt2_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(88, 88, 88))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt2_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(update2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPButtonActionPerformed
        this.setVisible(false);
        new MainMenu().setVisible(true);
    }//GEN-LAST:event_BackPButtonActionPerformed

    private void update2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update2ActionPerformed
        if(checkInputs() && txt2_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            try {
                UpdateQuery = "UPDATE server SET amount = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt2_Amt.getText());

                ps.setInt(2, Integer.parseInt(txt2_id.getText()));

                ps.executeUpdate();
                Show_Server_In_JTable();
                

            } catch (SQLException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
        showTotal();
         txt2_id.setText("");
        txt2_Amt.setText("");
        
    }//GEN-LAST:event_update2ActionPerformed

    private void BackPButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BackPButtonKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
        else if(evt.getKeyCode() == KeyEvent.VK_ENTER  ){
             JTable_Server.setRowSelectionInterval(0,0);
            int index = JTable_Server.getSelectedRow();
        ShowItem(index);
        JTable_Server.requestFocus();
        }
        
    }//GEN-LAST:event_BackPButtonKeyPressed

    private void txt2_AmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2_AmtKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
        else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              if(checkInputs() && txt2_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            try {
                UpdateQuery = "UPDATE server SET amount = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt2_Amt.getText());

                ps.setInt(2, Integer.parseInt(txt2_id.getText()));

                ps.executeUpdate();
                Show_Server_In_JTable();
               
            } catch (SQLException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
        showTotal();
        txt2_id.setText("");
        txt2_Amt.setText("");
        BackPButton.requestFocus();
        }
    }//GEN-LAST:event_txt2_AmtKeyPressed

    private void update2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update2KeyPressed
          if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
          else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              if(checkInputs() && txt2_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            try {
                UpdateQuery = "UPDATE server SET amount = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt2_Amt.getText());

                ps.setInt(2, Integer.parseInt(txt2_id.getText()));

                ps.executeUpdate();
                Show_Server_In_JTable();
                

            } catch (SQLException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
        showTotal();
        txt2_id.setText("");
        txt2_Amt.setText("");
        }
    }//GEN-LAST:event_update2KeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
          else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              if(checkInputs() && txt2_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            try {
                UpdateQuery = "UPDATE server SET amount = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt2_Amt.getText());

                ps.setInt(2, Integer.parseInt(txt2_id.getText()));

                ps.executeUpdate();
                Show_Server_In_JTable();
                

            } catch (SQLException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
        showTotal();
        txt2_id.setText("");
        txt2_Amt.setText("");
        }
    }//GEN-LAST:event_jPanel2KeyPressed

    private void txt2_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2_idKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
          else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              if(checkInputs() && txt2_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            try {
                UpdateQuery = "UPDATE server SET amount = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt2_Amt.getText());

                ps.setInt(2, Integer.parseInt(txt2_id.getText()));

                ps.executeUpdate();
                Show_Server_In_JTable();
                JOptionPane.showMessageDialog(null, "Server Updated");

            } catch (SQLException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
        showTotal();
        txt2_id.setText("");
        txt2_Amt.setText("");
        }
    }//GEN-LAST:event_txt2_idKeyPressed

    private void JTable_ServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ServerMouseClicked
         int index = JTable_Server.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ServerMouseClicked

    private void JTable_ServerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTable_ServerKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.setVisible(false);
            new MainMenu().setVisible(true);
        }

        else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txt2_Amt.requestFocus();
        }

    }//GEN-LAST:event_JTable_ServerKeyPressed

    private void JTable_ServerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTable_ServerKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_UP  ){

            int index = JTable_Server.getSelectedRow();
            ShowItem(index);
        }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN ){

            int index = JTable_Server.getSelectedRow();
            ShowItem(index);
        }

    }//GEN-LAST:event_JTable_ServerKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackPButton;
    private javax.swing.JTable JTable_Server;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label1_Amt;
    private javax.swing.JLabel label1_Amt1;
    private javax.swing.JLabel serv;
    private javax.swing.JTextField txt2_Amt;
    private javax.swing.JTextField txt2_id;
    private javax.swing.JButton update2;
    // End of variables declaration//GEN-END:variables
}
