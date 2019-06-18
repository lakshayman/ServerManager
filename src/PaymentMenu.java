
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
public class PaymentMenu extends javax.swing.JFrame {

    /**
     * Creates new form PaymentMenu
     */
    public PaymentMenu() {
        initComponents();
        showTotal();
        Show_Payments_In_JTable();
    }
public Connection getConnection(){
    Connection con = null;
     
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/sale_db","root","lkm042000");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentMenu.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}

public boolean checkInputs(){
    if (txt1_Amt.getText() == null || "Select".equals((String)combo1_Ser.getSelectedItem( )) || ("Other".equals((String)combo1_Ser.getSelectedItem( )) && othert.getText() == null)){
        return false;
    }
    else{
        try{
            Float.parseFloat(txt1_Amt.getText());
            return true;
        }catch(NumberFormatException ex)
        {
            return false;
        }
    }
}

 public ArrayList<Payment> getPaymentList()
    {
            ArrayList<Payment> paymentList  = new ArrayList<>();
            Connection con = getConnection();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String addDate;
            addDate = dateFormat.format(java.sql.Date.valueOf(java.time.LocalDate.now()));
            String query = "SELECT * FROM payments where add_date = " + "'"+addDate+"'";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Payment payment;
            
            while(rs.next())
            {
                payment = new Payment(rs.getInt("id"),Float.parseFloat(rs.getString("amount")),rs.getString("server"),rs.getString("add_date"));
                paymentList.add(payment);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return paymentList; 
                
    }
    
    
    //      2 - Populate The JTable
    
    public void Show_Payments_In_JTable()
    {
        ArrayList<Payment> list = getPaymentList();
        DefaultTableModel model = (DefaultTableModel)JTable_Payment.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getDate();
            row[2] = list.get(i).getServer();
            row[3] = list.get(i).getAmount();
            
            model.addRow(row);
        }
    
    }
    
    // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt1_id.setText(Integer.toString(getPaymentList().get(index).getId()));
            if(checkIteme(getPaymentList().get(index).getServer())){
                combo1_Ser.setSelectedItem((Object)getPaymentList().get(index).getServer());
            }else{
                combo1_Ser.setSelectedItem((Object)"Other");
                othert.setText(getPaymentList().get(index).getServer());
            }
            txt1_Amt.setText(Float.toString(getPaymentList().get(index).getAmount()));
    }

    public Boolean checkIteme (String s){
        Boolean exist = false;
        for(int i = 0 ; i < combo1_Ser.getItemCount() && !exist ; i++){
            if(s.equals((String)combo1_Ser.getItemAt(i))){
                exist = true;
            }
        }
        return exist;
    } 
    
    public void showTotal() {
        
        float tpayment = 0;
        Connection con = getConnection();
        
        try {
            
        
        PreparedStatement ps1 = con.prepareStatement("select sum(amount) from payments where add_date = ?;");
        ps1.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
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
        BackPButton = new javax.swing.JButton();
        txt1_id = new javax.swing.JTextField();
        label1_Amt = new javax.swing.JLabel();
        txt1_Amt = new javax.swing.JTextField();
        label1_Amt1 = new javax.swing.JLabel();
        combo1_Ser = new javax.swing.JComboBox<>();
        Add1 = new javax.swing.JButton();
        delete1 = new javax.swing.JButton();
        update1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Payment = new javax.swing.JTable();
        label1_Amt2 = new javax.swing.JLabel();
        othert = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        paymentt = new javax.swing.JLabel();

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

        txt1_id.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        label1_Amt.setBackground(new java.awt.Color(255, 255, 0));
        label1_Amt.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label1_Amt.setText("ID :");

        txt1_Amt.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txt1_Amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1_AmtKeyPressed(evt);
            }
        });

        label1_Amt1.setBackground(new java.awt.Color(255, 255, 0));
        label1_Amt1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label1_Amt1.setText("AMOUNT SPENT : ");

        combo1_Ser.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combo1_Ser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Pay1", "TTC", "TM", "Tata Sky 98", "Vodafone", "Idea", "Airtel", "Jio", "DishTV", "Videocon", "Airtel Money", "Airtel DTH", "Spice", "Tata Sky 99", "Other", " " }));
        combo1_Ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo1_SerActionPerformed(evt);
            }
        });
        combo1_Ser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combo1_SerKeyPressed(evt);
            }
        });

        Add1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Add1.setText("ADD");
        Add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add1ActionPerformed(evt);
            }
        });

        delete1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        delete1.setText("DELETE");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });

        update1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        update1.setText("UPDATE");
        update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update1ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(JTable_Payment);

        label1_Amt2.setBackground(new java.awt.Color(255, 255, 0));
        label1_Amt2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label1_Amt2.setText("Other  : ");

        othert.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        othert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                othertKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Total :");

        paymentt.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(paymentt, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BackPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(71, 116, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(label1_Amt)
                                        .addGap(35, 35, 35)
                                        .addComponent(txt1_id, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(combo1_Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(label1_Amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt1_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(label1_Amt2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(othert)))
                                .addGap(36, 36, 36)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(129, 129, 129)
                            .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(649, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(Add1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(update1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(535, 535, 535)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BackPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label1_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt1_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(combo1_Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt1_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1_Amt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(othert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1_Amt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(385, 385, 385))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(465, 465, 465)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Add1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(274, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPButtonActionPerformed
      this.setVisible(false);
        new MainMenu().setVisible(true);
    }//GEN-LAST:event_BackPButtonActionPerformed

    private void combo1_SerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo1_SerActionPerformed
        if("Other".equals((String)combo1_Ser.getSelectedItem())){
                    othert.setVisible(true);
       }
       else{
           othert.setVisible(false);
       }
    }//GEN-LAST:event_combo1_SerActionPerformed

    private void Add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add1ActionPerformed

        if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO payments(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt1_Amt.getText());
                if("Other".equals((String)combo1_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else{
                ps.setString(2, (String)combo1_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Payments_In_JTable();
                
                if(!"Other".equals((String)combo1_Ser.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt1_Amt.getText()));
                ps1.setString(2, (String)combo1_Ser.getSelectedItem());
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
        txt1_id.setText("");
        txt1_Amt.setText("");
        othert.setText("");
        combo1_Ser.setSelectedItem("Select");showTotal();
        JTable_Payment.scrollRectToVisible(JTable_Payment.getCellRect(JTable_Payment.getRowCount()-1,JTable_Payment.getColumnCount(),true ));
        BackPButton.requestFocus();
        showTotal();

    }//GEN-LAST:event_Add1ActionPerformed

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        if(!txt1_id.getText().equals(""))
        {
            try {
                Connection con = getConnection();
                
                if(!"Other".equals((String)combo1_Ser.getSelectedItem())){
                PreparedStatement ps4 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps4.setFloat(1, Float.parseFloat(txt1_Amt.getText()));
                ps4.setString(2, (String)combo1_Ser.getSelectedItem());
                ps4.executeUpdate();
                }
                
                PreparedStatement ps = con.prepareStatement("DELETE FROM payments WHERE id = ?");
                int id = Integer.parseInt(txt1_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();

                PreparedStatement ps1 = con.prepareStatement("UPDATE payments SET id = id - 1 WHERE id > ?");
                ps1.setInt(1, id);
                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement("select max(id) from payments;");
                ResultSet rs = ps2.executeQuery();
                int number = 0;
                if (rs.next()) {
                    number = (int) rs.getLong(1);
                }

                PreparedStatement ps3 = con.prepareStatement("ALTER TABLE payments AUTO_INCREMENT = ?");
                ps3.setInt(1, number);
                ps3.executeUpdate();

                Show_Payments_In_JTable();
                JOptionPane.showMessageDialog(null, "Payment Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Payment Not Deleted");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Payment Not Deleted : No Id To Delete");
        }
         txt1_id.setText("");
        txt1_Amt.setText("");
        othert.setText("");
        combo1_Ser.setSelectedItem("Select");showTotal();
        JTable_Payment.scrollRectToVisible(JTable_Payment.getCellRect(JTable_Payment.getRowCount()-1,JTable_Payment.getColumnCount(),true ));
         BackPButton.requestFocus();
    }//GEN-LAST:event_delete1ActionPerformed

    private void update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update1ActionPerformed
        if(checkInputs() && txt1_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Float i = null;
            Connection con = getConnection();
            try {
                
                PreparedStatement ps1 = con.prepareStatement("select amount from payments where id = ?;");
            ps1.setInt(1, Integer.parseInt(txt1_id.getText()) );
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            i = (float) rs.getLong(1);
        }
        
        if(!"Other".equals((String)combo1_Ser.getSelectedItem())){
            if(i > Float.parseFloat(txt1_Amt.getText())){
                PreparedStatement ps2 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps2.setFloat(1, (i-Float.parseFloat(txt1_Amt.getText())));
                ps2.setString(2, (String)combo1_Ser.getSelectedItem());
                ps2.executeUpdate();
            }
            else if(i < Float.parseFloat(txt1_Amt.getText())){
                PreparedStatement ps2 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps2.setFloat(1, (Float.parseFloat(txt1_Amt.getText())-i));
                ps2.setString(2, (String)combo1_Ser.getSelectedItem());
                ps2.executeUpdate();
            }
        }
                UpdateQuery = "UPDATE payments SET amount = ?, server = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt1_Amt.getText());
                 if("Other".equals((String)combo1_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else{
                ps.setString(2, (String)combo1_Ser.getSelectedItem());
                }

                ps.setInt(3, Integer.parseInt(txt1_id.getText()));

                ps.executeUpdate();
                Show_Payments_In_JTable();
               

            } catch (SQLException ex) {
                Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
         txt1_id.setText("");
        txt1_Amt.setText("");
        othert.setText("");
        combo1_Ser.setSelectedItem("Select");showTotal();
        JTable_Payment.scrollRectToVisible(JTable_Payment.getCellRect(JTable_Payment.getRowCount()-1,JTable_Payment.getColumnCount(),true ));
         BackPButton.requestFocus();
         showTotal();
    }//GEN-LAST:event_update1ActionPerformed

    private void JTable_PaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_PaymentMouseClicked
        int index = JTable_Payment.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_PaymentMouseClicked

    private void combo1_SerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo1_SerKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
          
       if("Select".equals((String)combo1_Ser.getSelectedItem( ))){
              BackPButton.requestFocus();
             }
             else{
                 txt1_Amt.requestFocus();
             }
        }
          else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_combo1_SerKeyPressed

    private void txt1_AmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1_AmtKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           if("Other".equals((String)combo1_Ser.getSelectedItem( ))){
              othert.requestFocus();
             }
             else{
                  if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO payments(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt1_Amt.getText());
                if("Other".equals((String)combo1_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else{
                ps.setString(2, (String)combo1_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Payments_In_JTable();
                
                if(!"Other".equals((String)combo1_Ser.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt1_Amt.getText()));
                ps1.setString(2, (String)combo1_Ser.getSelectedItem());
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
                   txt1_id.setText("");
        txt1_Amt.setText("");
        othert.setText("");
        combo1_Ser.setSelectedItem("Select");showTotal();
        JTable_Payment.scrollRectToVisible(JTable_Payment.getCellRect(JTable_Payment.getRowCount()-1,JTable_Payment.getColumnCount(),true ));
         BackPButton.requestFocus();
             }
           
        }
          else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_txt1_AmtKeyPressed

    private void othertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_othertKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO payments(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt1_Amt.getText());
                if("Other".equals((String)combo1_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else{
                ps.setString(2, (String)combo1_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Payments_In_JTable();
                
                if(!"Other".equals((String)combo1_Ser.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt1_Amt.getText()));
                ps1.setString(2, (String)combo1_Ser.getSelectedItem());
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
             txt1_id.setText("");
        txt1_Amt.setText("");
        othert.setText("");
        combo1_Ser.setSelectedItem("Select");showTotal();
        JTable_Payment.scrollRectToVisible(JTable_Payment.getCellRect(JTable_Payment.getRowCount()-1,JTable_Payment.getColumnCount(),true ));
         BackPButton.requestFocus();
        }
          else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_othertKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO payments(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt1_Amt.getText());
                if("Other".equals((String)combo1_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else{
                ps.setString(2, (String)combo1_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Payments_In_JTable();
                
                if(!"Other".equals((String)combo1_Ser.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt1_Amt.getText()));
                ps1.setString(2, (String)combo1_Ser.getSelectedItem());
                ps1.executeUpdate();
                }  
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
             txt1_id.setText("");
        txt1_Amt.setText("");
        othert.setText("");
        combo1_Ser.setSelectedItem("Select");showTotal();
        JTable_Payment.scrollRectToVisible(JTable_Payment.getCellRect(JTable_Payment.getRowCount()-1,JTable_Payment.getColumnCount(),true ));
         BackPButton.requestFocus();
        }
          else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_jPanel2KeyPressed

    private void BackPButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BackPButtonKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              combo1_Ser.requestFocus(); 
             if("Select".equals((String)combo1_Ser.getSelectedItem( ))){
              combo1_Ser.setPopupVisible(true);
             }
             else{
                 txt1_Amt.requestFocus();
             }
        }
    }//GEN-LAST:event_BackPButtonKeyPressed

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
            java.util.logging.Logger.getLogger(PaymentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add1;
    private javax.swing.JButton BackPButton;
    private javax.swing.JTable JTable_Payment;
    private javax.swing.JComboBox<String> combo1_Ser;
    private javax.swing.JButton delete1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1_Amt;
    private javax.swing.JLabel label1_Amt1;
    private javax.swing.JLabel label1_Amt2;
    private javax.swing.JTextField othert;
    private javax.swing.JLabel paymentt;
    private javax.swing.JTextField txt1_Amt;
    private javax.swing.JTextField txt1_id;
    private javax.swing.JButton update1;
    // End of variables declaration//GEN-END:variables
}
