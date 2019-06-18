
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
public final class SaleMenu extends javax.swing.JFrame {

    /**
     * Creates new form SaleMenu
     */
    public SaleMenu() {
        initComponents();
        showTotal();
        Show_Sales_In_JTable();
    }
public Connection getConnection(){
    Connection con = null;
     
        try {
        
            con = DriverManager.getConnection("jdbc:mysql://localhost/sale_db","root","lkm042000");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}

public boolean checkInputs(){
    if (txt_Amt.getText() == null || "Select".equals((String)combo_Ser.getSelectedItem( )) || ("Other".equals((String)combo_Ser.getSelectedItem( )) && othert.getText() == null) || ("Select".equals((String)combo_credit.getSelectedItem( )) && "Credit".equals((String)combo_Ser.getSelectedItem())) ){
        
            return false;
        
    }
    else{
        try{
            Float.parseFloat(txt_Amt.getText());
            return true;
        }catch(NumberFormatException ex)
        {
            return false;
        }
    }
       
        
}

 public ArrayList<Sale> getSaleList()
    {
            ArrayList<Sale> saleList  = new ArrayList<>();
            Connection con = getConnection();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String addDate;
            addDate = dateFormat.format(java.sql.Date.valueOf(java.time.LocalDate.now()));
            String query = "SELECT * FROM sales where add_date = " + "'"+addDate+"'";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Sale sale;
            
            while(rs.next())
            {
                sale = new Sale(rs.getInt("id"),Float.parseFloat(rs.getString("amount")),rs.getString("server"),rs.getString("add_date"));
                saleList.add(sale);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saleList; 
                
    }
    
    
    //      2 - Populate The JTable
    
    public void Show_Sales_In_JTable()
    {
        ArrayList<Sale> list = getSaleList();
        DefaultTableModel model = (DefaultTableModel)JTable_Sales.getModel();
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
            String s = (getSaleList().get(index).getServer()).substring(0,(getSaleList().get(index).getServer()).length()-2);
            txt_id.setText(Integer.toString(getSaleList().get(index).getId()));
            if(checkCR(getSaleList().get(index).getServer())){
                combo_Ser.setSelectedItem((Object)"Credit");
                if(checkIteme(s)){
                    combo_credit.setSelectedItem((Object)s);
                }
                else{
                    combo_credit.setSelectedItem((Object)"Other");
                    othert.setText(s);
                }
                
            }else if(checkIteme(getSaleList().get(index).getServer())){
                combo_Ser.setSelectedItem((Object)getSaleList().get(index).getServer());
            }
            else{
                combo_Ser.setSelectedItem((Object)"Other");
                othert.setText(getSaleList().get(index).getServer());
            }
            
            txt_Amt.setText(Float.toString(getSaleList().get(index).getAmount()));
    }
    public Boolean checkIteme (String s){
        Boolean exist = false;
        for(int i = 0 ; i < combo_Ser.getItemCount() && !exist ; i++){
            if(s.equals((String)combo_Ser.getItemAt(i))){
                exist = true;
           }
        }
        return exist;
    } 
    
    public Boolean checkCR(String s){
        Boolean credit = false;
        String lasttwo = null;
        if(s != null && s.length() >= 2){
            lasttwo = s.substring(s.length()-2);
        }
        if("CR".equals(lasttwo)){
            credit = true;
        }
        return credit;
    } 
    
    public void showTotal() {
        float tsale = 0;
        
        Connection con = getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select sum(amount) from sales where add_date = ? and server not like '%CR';");
            ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tsale = (float) rs.getLong(1);
        }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(Todays_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        salet.setText(String.valueOf(tsale));
        
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
        label_Amt = new javax.swing.JLabel();
        txt_Amt = new javax.swing.JTextField();
        combo_Ser = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Sales = new javax.swing.JTable();
        Add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        label_Amt1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        label_Amt2 = new javax.swing.JLabel();
        othert = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        salet = new javax.swing.JLabel();
        combo_credit = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
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

        label_Amt.setBackground(new java.awt.Color(255, 255, 0));
        label_Amt.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label_Amt.setText("ID :");

        txt_Amt.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txt_Amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_AmtKeyPressed(evt);
            }
        });

        combo_Ser.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combo_Ser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Pay1", "TTC", "TM", "Tata Sky 98", "Vodafone", "Idea", "Airtel", "Jio", "DishTV", "Videocon", "Airtel Money", "Airtel DTH", "Spice", "Tata Sky 99", "Credit", "Other" }));
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

        Add.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Add.setText("ADD");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        label_Amt1.setBackground(new java.awt.Color(255, 255, 0));
        label_Amt1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        label_Amt1.setText("AMOUNT TAKEN : ");

        txt_id.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

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

        combo_credit.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combo_credit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Pay1", "TTC", "TM", "Tata Sky 98", "Vodafone", "Idea", "Airtel", "Jio", "DishTV", "Videocon", "Airtel Money", "Airtel DTH", "Spice", "Tata Sky 99", "Other" }));
        combo_credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_creditActionPerformed(evt);
            }
        });
        combo_credit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combo_creditKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("CREDIT :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(322, 322, 322)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salet, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BackRButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_credit, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(label_Amt)
                                                    .addGap(31, 31, 31))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label_Amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label_Amt2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(othert)))
                                        .addComponent(combo_Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salet, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BackRButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(combo_Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_credit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Amt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Amt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(othert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Amt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackRButtonActionPerformed
        this.setVisible(false);
        new MainMenu().setVisible(true);      
    }//GEN-LAST:event_BackRButtonActionPerformed

    private void combo_SerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_SerActionPerformed
       if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
       }
       else if("Credit".equals((String)combo_Ser.getSelectedItem())){
           combo_credit.setVisible(true);
       }
       else{
           combo_credit.setVisible(false);
           othert.setVisible(false);
       }
    }//GEN-LAST:event_combo_SerActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Float i = null;
            Connection con = getConnection();
            try {
                
                PreparedStatement ps1 = con.prepareStatement("select amount from sales where id = ?;");
            ps1.setInt(1, Integer.parseInt(txt_id.getText()) );
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            i = (float) rs.getLong(1);
        }
        
        if(!"Other".equals((String)combo_Ser.getSelectedItem()) || !"Other".equals((String)combo_credit.getSelectedItem())){
            if(i > Float.parseFloat(txt_Amt.getText())){
                PreparedStatement ps2 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps2.setFloat(1, (i-Float.parseFloat(txt_Amt.getText())));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps2.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps2.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps2.executeUpdate();
            }
            else if(i < Float.parseFloat(txt_Amt.getText())){
                PreparedStatement ps2 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps2.setFloat(1, (Float.parseFloat(txt_Amt.getText())-i));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps2.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps2.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps2.executeUpdate();
            }
        }
                UpdateQuery = "UPDATE sales SET amount = ?, server = ?"
                + " WHERE id = ?";
                ps = con.prepareStatement(UpdateQuery);

                ps.setString(1, txt_Amt.getText());
                 if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else if("Credit".equals((String)combo_Ser.getSelectedItem())){
                   if("Other".equals((String)combo_credit.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText() + "CR");
                   }
                   else{
                    combo_credit.setVisible(true);
                    ps.setString(2, (String)combo_credit.getSelectedItem() + "CR");
                   }
                }
                 else{
                ps.setString(2, (String)combo_Ser.getSelectedItem());
                }

                ps.setInt(3, Integer.parseInt(txt_id.getText()));

                ps.executeUpdate();
                Show_Sales_In_JTable();
                JOptionPane.showMessageDialog(null, "Sale Updated");

            } catch (SQLException ex) {
                Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
         txt_id.setText("");
        txt_Amt.setText("");
        othert.setText("");
        combo_Ser.setSelectedItem("Select");showTotal();
        JTable_Sales.scrollRectToVisible(JTable_Sales.getCellRect(JTable_Sales.getRowCount()-1,JTable_Sales.getColumnCount(),true ));
         BackRButton.requestFocus();
         showTotal();
    }//GEN-LAST:event_updateActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        
        if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO sales(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt_Amt.getText());
                if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    if("Other".equals((String)combo_credit.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText() + "CR");
                   }
                   else{
                    combo_credit.setVisible(true);
                    ps.setString(2, (String)combo_credit.getSelectedItem() + "CR");
                   }
                }
                else{
                ps.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Sales_In_JTable();
                
                if(!"Other".equals((String)combo_Ser.getSelectedItem()) || !"Other".equals((String)combo_credit.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt_Amt.getText()));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps1.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps1.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
        txt_id.setText("");
        txt_Amt.setText("");
        othert.setText("");
        combo_Ser.setSelectedItem("Select");showTotal();
        combo_credit.setSelectedItem("Select");
        JTable_Sales.scrollRectToVisible(JTable_Sales.getCellRect(JTable_Sales.getRowCount()-1,JTable_Sales.getColumnCount(),true ));
        BackRButton.requestFocus();
        showTotal();
    }//GEN-LAST:event_AddActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
           if(!txt_id.getText().equals(""))
        {
            try {
                Connection con = getConnection();
                
                if(!"Other".equals((String)combo_Ser.getSelectedItem()) || !"Other".equals((String)combo_credit.getSelectedItem())){
                PreparedStatement ps4 =con.prepareStatement("UPDATE server SET amount = amount + ?" + " WHERE server = ?");
                ps4.setFloat(1, Float.parseFloat(txt_Amt.getText()));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps4.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps4.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps4.executeUpdate();
                }
                
                PreparedStatement ps = con.prepareStatement("DELETE FROM sales WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();

                PreparedStatement ps1 = con.prepareStatement("UPDATE sales SET id = id - 1 WHERE id > ?");
                ps1.setInt(1, id);
                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement("select max(id) from sales;");
                ResultSet rs = ps2.executeQuery();
                int number = 0;
                if (rs.next()) {
                    number = (int) rs.getLong(1);
                }

                PreparedStatement ps3 = con.prepareStatement("ALTER TABLE sales AUTO_INCREMENT = ?");
                ps3.setInt(1, number);
                ps3.executeUpdate();

                Show_Sales_In_JTable();
                JOptionPane.showMessageDialog(null, "Sale Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(SaleMenu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Sale Not Deleted");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Sale Not Deleted : No Id To Delete");
        }
         txt_id.setText("");
        txt_Amt.setText("");
        othert.setText("");
        combo_Ser.setSelectedItem("Select");showTotal();
         combo_credit.setSelectedItem("Select");
        JTable_Sales.scrollRectToVisible(JTable_Sales.getCellRect(JTable_Sales.getRowCount()-1,JTable_Sales.getColumnCount(),true ));
         BackRButton.requestFocus();
    
    }//GEN-LAST:event_deleteActionPerformed

    private void JTable_SalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_SalesMouseClicked
         int index = JTable_Sales.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_SalesMouseClicked

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO sales(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt_Amt.getText());
                if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    if("Other".equals((String)combo_credit.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText() + "CR");
                   }
                   else{
                    combo_credit.setVisible(true);
                    ps.setString(2, (String)combo_credit.getSelectedItem() + "CR");
                   }
                }
                else{
                ps.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Sales_In_JTable();
                
                if(!"Other".equals((String)combo_Ser.getSelectedItem()) || !"Other".equals((String)combo_credit.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt_Amt.getText()));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps1.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps1.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
        txt_id.setText("");
        txt_Amt.setText("");
        othert.setText("");
        combo_Ser.setSelectedItem("Select");showTotal();
        combo_credit.setSelectedItem("Select");
        JTable_Sales.scrollRectToVisible(JTable_Sales.getCellRect(JTable_Sales.getRowCount()-1,JTable_Sales.getColumnCount(),true ));
        BackRButton.requestFocus();
        showTotal();
        }
          else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_jPanel2KeyPressed

    private void combo_SerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_SerKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             if("Select".equals((String)combo_Ser.getSelectedItem( ))){
             
              BackRButton.requestFocus();
             }
             else if("Credit".equals((String)combo_Ser.getSelectedItem( ))){
                  combo_credit.requestFocus(); 
                  combo_credit.setPopupVisible(true);
             }
             else{
                 txt_Amt.requestFocus();
             }
        }
           else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_combo_SerKeyPressed

    private void txt_AmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AmtKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        if("Other".equals((String)combo_Ser.getSelectedItem( ))){
             othert.requestFocus();
             }
             else{
                 if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO sales(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt_Amt.getText());
                if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    if("Other".equals((String)combo_credit.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText() + "CR");
                   }
                   else{
                    combo_credit.setVisible(true);
                    ps.setString(2, (String)combo_credit.getSelectedItem() + "CR");
                   }
                }
                else{
                ps.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Sales_In_JTable();
                
                if(!"Other".equals((String)combo_Ser.getSelectedItem()) || !"Other".equals((String)combo_credit.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt_Amt.getText()));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps1.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps1.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
        txt_id.setText("");
        txt_Amt.setText("");
        othert.setText("");
        combo_Ser.setSelectedItem("Select");showTotal();
        combo_credit.setSelectedItem("Select");
        JTable_Sales.scrollRectToVisible(JTable_Sales.getCellRect(JTable_Sales.getRowCount()-1,JTable_Sales.getColumnCount(),true ));
        BackRButton.requestFocus();
        showTotal();
             }
             
        }
         else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_txt_AmtKeyPressed

    private void othertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_othertKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        if (checkInputs()){
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO sales(amount,server,add_date)" + "values(?,?,?)");
                ps.setString(1, txt_Amt.getText());
                if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText());
                }else if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    if("Other".equals((String)combo_credit.getSelectedItem())){
                    othert.setVisible(true);
                    ps.setString(2, othert.getText() + "CR");
                   }
                   else{
                    combo_credit.setVisible(true);
                    ps.setString(2, (String)combo_credit.getSelectedItem() + "CR");
                   }
                }
                else{
                ps.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.executeUpdate();
                Show_Sales_In_JTable();
                
                if(!"Other".equals((String)combo_Ser.getSelectedItem()) || !"Other".equals((String)combo_credit.getSelectedItem())){
                PreparedStatement ps1 =con.prepareStatement("UPDATE server SET amount = amount - ?" + " WHERE server = ?");
                ps1.setFloat(1, Float.parseFloat(txt_Amt.getText()));
                if("Credit".equals((String)combo_Ser.getSelectedItem())){
                    ps1.setString(2, (String)combo_credit.getSelectedItem());     
                }else{
                    ps1.setString(2, (String)combo_Ser.getSelectedItem());
                }
                ps1.executeUpdate();
                } 
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"One Or More Fields Are Empty");
        }
        txt_id.setText("");
        txt_Amt.setText("");
        othert.setText("");
        combo_Ser.setSelectedItem("Select");showTotal();
        combo_credit.setSelectedItem("Select");
        JTable_Sales.scrollRectToVisible(JTable_Sales.getCellRect(JTable_Sales.getRowCount()-1,JTable_Sales.getColumnCount(),true ));
        BackRButton.requestFocus();
        showTotal();
        }
           else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_othertKeyPressed

    private void BackRButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BackRButtonKeyPressed
           if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
           
           else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
              combo_Ser.requestFocus(); 
             if("Select".equals((String)combo_Ser.getSelectedItem( ))){
              combo_Ser.setPopupVisible(true);
             }
             else{
                 txt_Amt.requestFocus();
             }
        }
    }//GEN-LAST:event_BackRButtonKeyPressed

    private void combo_creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_creditActionPerformed
       if("Other".equals((String)combo_Ser.getSelectedItem())){
                    othert.setVisible(true);
       }
       
       else{
          
           othert.setVisible(false);
       }
    }//GEN-LAST:event_combo_creditActionPerformed

    private void combo_creditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_creditKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             if("Select".equals((String)combo_credit.getSelectedItem( ))){
             
              combo_Ser.requestFocus();
             }
             else{
                 txt_Amt.requestFocus();
             }
        }
           else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
             this.setVisible(false);
        new MainMenu().setVisible(true); 
         }
    }//GEN-LAST:event_combo_creditKeyPressed

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
            java.util.logging.Logger.getLogger(SaleMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton BackRButton;
    private javax.swing.JTable JTable_Sales;
    private javax.swing.JComboBox<String> combo_Ser;
    private javax.swing.JComboBox<String> combo_credit;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Amt;
    private javax.swing.JLabel label_Amt1;
    private javax.swing.JLabel label_Amt2;
    private javax.swing.JTextField othert;
    private javax.swing.JLabel salet;
    private javax.swing.JTextField txt_Amt;
    private javax.swing.JTextField txt_id;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
