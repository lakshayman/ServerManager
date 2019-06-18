
import java.awt.event.KeyEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csa
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
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
        jPanel1 = new javax.swing.JPanel();
        ServerButton = new javax.swing.JButton();
        PaymentButton = new javax.swing.JButton();
        SaleRButton = new javax.swing.JButton();
        TotalButton = new javax.swing.JButton();
        Ser = new javax.swing.JButton();
        crb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Main Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 48), new java.awt.Color(255, 51, 51))); // NOI18N
        jPanel1.setToolTipText("");
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        ServerButton.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        ServerButton.setText("Server Values  (F11)");
        ServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerButtonActionPerformed(evt);
            }
        });
        ServerButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ServerButtonKeyPressed(evt);
            }
        });

        PaymentButton.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        PaymentButton.setText("Payment  (F10)");
        PaymentButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PaymentButtonStateChanged(evt);
            }
        });
        PaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentButtonActionPerformed(evt);
            }
        });
        PaymentButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PaymentButtonKeyPressed(evt);
            }
        });

        SaleRButton.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        SaleRButton.setText("Sale  (F9)");
        SaleRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaleRButtonActionPerformed(evt);
            }
        });
        SaleRButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaleRButtonKeyPressed(evt);
            }
        });

        TotalButton.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        TotalButton.setText("Total and old record  (F12)");
        TotalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalButtonActionPerformed(evt);
            }
        });
        TotalButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalButtonKeyPressed(evt);
            }
        });

        Ser.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Ser.setText("Search Server Record");
        Ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SerActionPerformed(evt);
            }
        });
        Ser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SerKeyPressed(evt);
            }
        });

        crb.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        crb.setText("CREDIT BACK");
        crb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crbActionPerformed(evt);
            }
        });
        crb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                crbKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Ser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(SaleRButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PaymentButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaleRButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Ser, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(crb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
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

    private void PaymentButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PaymentButtonStateChanged

    }//GEN-LAST:event_PaymentButtonStateChanged

    private void SaleRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaleRButtonActionPerformed
        this.setVisible(false);
        new SaleMenu().setVisible(true);
    }//GEN-LAST:event_SaleRButtonActionPerformed

    private void PaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentButtonActionPerformed
        this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
    }//GEN-LAST:event_PaymentButtonActionPerformed

    private void TotalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalButtonActionPerformed

        this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
    }//GEN-LAST:event_TotalButtonActionPerformed

    private void ServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerButtonActionPerformed
        this.setVisible(false);
        server s = new server();
        s.setVisible(true);
    }//GEN-LAST:event_ServerButtonActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
       
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed

    }//GEN-LAST:event_jPanel1KeyPressed

    private void SaleRButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaleRButtonKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_F9){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F10){
            this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F11){
              this.setVisible(false);
        server s = new server();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F12){
            this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
           PaymentButton.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
           Ser.requestFocus();
       }
      
    }//GEN-LAST:event_SaleRButtonKeyPressed

    private void PaymentButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaymentButtonKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_F9){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F10){
            this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F11){
              this.setVisible(false);
        server s = new server();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F12){
            this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
        new PaymentMenu().setVisible(true);
        }
       else if (evt.getKeyCode() == KeyEvent.VK_LEFT){
           SaleRButton.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
           crb.requestFocus();
       }
      
    }//GEN-LAST:event_PaymentButtonKeyPressed

    private void ServerButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ServerButtonKeyPressed
           if(evt.getKeyCode() == KeyEvent.VK_F9){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F10){
            this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F11){
              this.setVisible(false);
        server s = new server();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F12){
            this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
        new server().setVisible(true);
        }
       else if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
           TotalButton.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_UP){
          Ser.requestFocus();
       }
      
    }//GEN-LAST:event_ServerButtonKeyPressed

    private void TotalButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalButtonKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_F9){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F10){
            this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F11){
              this.setVisible(false);
        server s = new server();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F12){
            this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
        new Total().setVisible(true);
        }
       else if (evt.getKeyCode() == KeyEvent.VK_UP){
           crb.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_LEFT){
           ServerButton.requestFocus();
       }
      
    }//GEN-LAST:event_TotalButtonKeyPressed

    private void SerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SerActionPerformed
        this.setVisible(false);
        Ser s = new Ser();
        s.setVisible(true);
    }//GEN-LAST:event_SerActionPerformed

    private void SerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SerKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_F9){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F10){
            this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F11){
              this.setVisible(false);
        server s = new server();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F12){
            this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
        new Ser().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
           crb.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_UP){
           SaleRButton.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
           ServerButton.requestFocus();
       }
    }//GEN-LAST:event_SerKeyPressed

    private void crbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crbActionPerformed
        this.setVisible(false);
        new crb().setVisible(true);
    }//GEN-LAST:event_crbActionPerformed

    private void crbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_crbKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_F9){
            this.setVisible(false);
        new SaleMenu().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F10){
            this.setVisible(false);
        PaymentMenu s = new PaymentMenu();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F11){
              this.setVisible(false);
        server s = new server();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_F12){
            this.setVisible(false);
        Total s = new Total();
        s.setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
        new crb().setVisible(true);
        }
       else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
           Ser.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_UP){
           PaymentButton.requestFocus();
       }
       else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
           TotalButton.requestFocus();
       }
    }//GEN-LAST:event_crbKeyPressed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PaymentButton;
    private javax.swing.JButton SaleRButton;
    private javax.swing.JButton Ser;
    private javax.swing.JButton ServerButton;
    private javax.swing.JButton TotalButton;
    private javax.swing.JButton crb;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}