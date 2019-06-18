
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Show extends javax.swing.JFrame {

    /**
     * Creates new form Show
     */
    public Show() {
        initComponents();
    }
    
    public Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/sale_db","root","lkm042000");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ShowSpecserv.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Sale> getSaleList() {
        ArrayList<Sale> saleList = new ArrayList<>();
        Connection con = getConnection();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String addDate;
        addDate = dateFormat.format(new java.sql.Date(datec.getDate().getTime()));
        String query;
        if("Other".equals((String)combo_Ser.getSelectedItem())){
             query = "SELECT * FROM sales where add_date = " + "'" + addDate + "'" + "and server = " + "'" + othert.getText() + "'"+ ";" ;
    }
        else{
            query =  "SELECT * FROM sales where add_date = " + "'" + addDate + "'" + "and server = " + "'" + (String) combo_Ser.getSelectedItem() + "'"+ ";" ;
        }
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Sale sale;

            while (rs.next()) {
                sale = new Sale(rs.getInt("id"), Float.parseFloat(rs.getString("amount")), rs.getString("server"), rs.getString("add_date"));
                saleList.add(sale);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saleList;

    }

    //      2 - Populate The JTable
    public void Show_Sales_In_JTable() {
        ArrayList<Sale> list = getSaleList();
        DefaultTableModel model = (DefaultTableModel) JTable_Sales.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getDate();
            row[2] = list.get(i).getServer();
            row[3] = list.get(i).getAmount();

            model.addRow(row);
        }

    }

    public ArrayList<Payment> getPaymentList() {
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = getConnection();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String addDate;
        addDate = dateFormat.format(new java.sql.Date(datec.getDate().getTime()));
        String query ;
        if("Other".equals((String)combo_Ser.getSelectedItem())){
             query = "SELECT * FROM payments where add_date = " + "'" + addDate + "'" + "and server = " + "'" + othert.getText() + "'"+ ";" ;
    }
        else{
            query =  "SELECT * FROM payments where add_date = " + "'" + addDate + "'" + "and server = " + "'" + (String) combo_Ser.getSelectedItem() + "'"+ ";" ;
        }
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Payment payment;

            while (rs.next()) {
                payment = new Payment(rs.getInt("id"), Float.parseFloat(rs.getString("amount")), rs.getString("server"), rs.getString("add_date"));
                paymentList.add(payment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paymentList;

    }

    //      2 - Populate The JTable
    public void Show_Payments_In_JTable() {
        ArrayList<Payment> list = getPaymentList();
        DefaultTableModel model = (DefaultTableModel) JTable_Payment.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getDate();
            row[2] = list.get(i).getServer();
            row[3] = list.get(i).getAmount();

            model.addRow(row);
        }

    }
    
    public void showTotalSales() {
        float tsale = 0;
        
        Connection con = getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select sum(amount) from sales where add_date = ?" + "and server = ?; ");
            ps.setDate(1, new java.sql.Date(datec.getDate().getTime()));
             if("Other".equals((String)combo_Ser.getSelectedItem())){
              ps.setString(2, othert.getText());
            }
        else{
             ps.setString(2, (String) combo_Ser.getSelectedItem());      
            }
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tsale = (float) rs.getLong(1);
        }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(Todays_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        salet.setText(String.valueOf(tsale));
        
    }
    
    public void showTotalPayments() {
        
        float tpayment = 0;
        Connection con = getConnection();
        
        try {
            
        
        PreparedStatement ps1 = con.prepareStatement("select sum(amount) from payments where add_date = ?" + "and server = ?;" );
        ps1.setDate(1, new java.sql.Date(datec.getDate().getTime()));
         if("Other".equals((String)combo_Ser.getSelectedItem())){
              ps1.setString(2, othert.getText());
            }
        else{
             ps1.setString(2, (String) combo_Ser.getSelectedItem());      
            }
        
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()) {
            tpayment = (float) rs1.getLong(1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(Todays_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       
        paymentt.setText(String.valueOf(tpayment));
       
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
        BackRButton = new javax.swing.JButton();
        combo_Ser = new javax.swing.JComboBox<>();
        label_Amt2 = new javax.swing.JLabel();
        othert = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        salet = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable_Payment = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        paymentt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Sales = new javax.swing.JTable();
        datec = new com.toedter.calendar.JDateChooser();
        show = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(1024, 768));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        BackRButton.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        BackRButton.setText("Back");
        BackRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackRButtonActionPerformed(evt);
            }
        });
        BackRButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BackRButtonKeyPressed(evt);
            }
        });

        combo_Ser.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combo_Ser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Pay1", "TTC", "TM", "Tata Sky 98", "Vodafone", "Idea", "Airtel", "Jio", "DishTV", "Videocon", "Airtel Money", "Airtel DTH", "Spice", "Tata Sky 99", "Other" }));
        combo_Ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_SerActionPerformed(evt);
            }
        });
        combo_Ser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combo_SerKeyPressed(evt);
            }
        });

        label_Amt2.setBackground(new java.awt.Color(255, 255, 0));
        label_Amt2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label_Amt2.setText("Other  :");

        othert.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        othert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                othertKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Total :");

        salet.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        JTable_Payment.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        JTable_Payment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Add Date", "Server", "Amount"
            }
        ));
        JTable_Payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_PaymentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable_Payment);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setText("Total :");

        paymentt.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        jLabel3.setText("SALE");

        jLabel4.setText("PAYMENT");

        JTable_Sales.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        JTable_Sales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Add Date", "Server", "Amount"
            }
        ));
        JTable_Sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_SalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Sales);

        datec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datecKeyPressed(evt);
            }
        });

        show.setText("Show");
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });
        show.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                showKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salet, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 69, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(paymentt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(98, 98, 98))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackRButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_Amt2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(othert, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(combo_Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(datec, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(show)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(293, 293, 293))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(combo_Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(othert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Amt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datec, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BackRButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(show)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(salet, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(paymentt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackRButtonActionPerformed
        this.setVisible(false);
        new Ser().setVisible(true);
    }//GEN-LAST:event_BackRButtonActionPerformed

    private void BackRButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BackRButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
            new Ser().setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            combo_Ser.requestFocus();

            combo_Ser.setPopupVisible(true);

        }
    }//GEN-LAST:event_BackRButtonKeyPressed

    private void combo_SerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_SerActionPerformed
        if ("Other".equals((String) combo_Ser.getSelectedItem())) {
            othert.setVisible(true);
        } else {
            othert.setVisible(false);
            datec.requestFocus();
        }
    }//GEN-LAST:event_combo_SerActionPerformed

    private void combo_SerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_SerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("Other".equals((String) combo_Ser.getSelectedItem())) {
                othert.requestFocus();
            }
            else{
                datec.requestFocus();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
            new Ser().setVisible(true);
        }
    }//GEN-LAST:event_combo_SerKeyPressed

    private void othertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_othertKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datec.requestFocus();
            
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
            new Ser().setVisible(true);
        }
    }//GEN-LAST:event_othertKeyPressed

    private void JTable_PaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_PaymentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JTable_PaymentMouseClicked

    private void JTable_SalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_SalesMouseClicked

    }//GEN-LAST:event_JTable_SalesMouseClicked

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed

    }//GEN-LAST:event_jPanel2KeyPressed

    private void datecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            show.requestFocus();
            
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
            new Ser().setVisible(true);
        }
    }//GEN-LAST:event_datecKeyPressed

    private void showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showActionPerformed
        showTotalSales();
        showTotalPayments();
        Show_Sales_In_JTable();
        Show_Payments_In_JTable();
    }//GEN-LAST:event_showActionPerformed

    private void showKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_showKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         showTotalSales();
        showTotalPayments();
        Show_Sales_In_JTable();
        Show_Payments_In_JTable();   
            
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
            new Ser().setVisible(true);
        }
    }//GEN-LAST:event_showKeyPressed

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
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Show().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackRButton;
    private javax.swing.JTable JTable_Payment;
    private javax.swing.JTable JTable_Sales;
    private javax.swing.JComboBox<String> combo_Ser;
    private com.toedter.calendar.JDateChooser datec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_Amt2;
    private javax.swing.JTextField othert;
    private javax.swing.JLabel paymentt;
    private javax.swing.JLabel salet;
    private javax.swing.JButton show;
    // End of variables declaration//GEN-END:variables
}
