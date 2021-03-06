/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank.gui;

import java.util.List;
import java.util.Random;
import javax.swing.JComboBox;
import rmbank.Account;
import rmbank.Card;
import rmbank.database.BankDB;

/**
 *
 * @author Rolo
 */
public class CardInfo extends javax.swing.JPanel {

    /**
     * Creates new form CardInfo
     */
    public CardInfo() {
        initComponents();
        pinField.setEchoChar('\u25cf');
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardBox = new javax.swing.JComboBox();
        toggleBlockedButton = new javax.swing.JButton();
        account = new javax.swing.JLabel();
        blocked = new javax.swing.JLabel();
        newCardButton = new javax.swing.JButton();
        pinField = new javax.swing.JPasswordField();
        changePINButton = new javax.swing.JButton();
        showPINButton = new javax.swing.JButton();
        accountBox = new javax.swing.JComboBox();

        cardBox.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cardBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardBoxActionPerformed(evt);
            }
        });

        toggleBlockedButton.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        toggleBlockedButton.setText("Toggle Blocked");
        toggleBlockedButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        toggleBlockedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBlockedButtonActionPerformed(evt);
            }
        });

        account.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        account.setText("Account: -----");

        blocked.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        blocked.setText("Blocked: -----");

        newCardButton.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        newCardButton.setText("New Card");
        newCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCardButtonActionPerformed(evt);
            }
        });

        pinField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pinField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pinField.setMaximumSize(new java.awt.Dimension(6, 35));

        changePINButton.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        changePINButton.setText("Change PIN");
        changePINButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        changePINButton.setMaximumSize(new java.awt.Dimension(87, 35));
        changePINButton.setMinimumSize(new java.awt.Dimension(87, 35));
        changePINButton.setPreferredSize(new java.awt.Dimension(87, 35));
        changePINButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePINButtonActionPerformed(evt);
            }
        });

        showPINButton.setFont(new java.awt.Font("Arial", 0, 5)); // NOI18N
        showPINButton.setText("SHOW");
        showPINButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPINButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showPINButtonMouseReleased(evt);
            }
        });
        showPINButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPINButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardBox, 0, 621, Short.MAX_VALUE)
                    .addComponent(account, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blocked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(pinField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changePINButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showPINButton))
                            .addComponent(newCardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toggleBlockedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(accountBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blocked, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(changePINButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showPINButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pinField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newCardButton)
                    .addComponent(accountBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleBlockedButton)
                .addGap(185, 185, 185))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cardBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardBoxActionPerformed
        try {
            Object o = this.cardBox.getSelectedItem();
            System.out.println("object:"+o.toString());
            if(o==null)System.out.println("preco???????");
            long cc=Long.parseLong(o.toString());
            System.out.println("cc="+cc);
            Card c = new BankDB().getCardByNumber(35435);
            if (c == null) return;
            
            this.account.setText("Account: "+c.getAccnum());
            this.blocked.setText("Blocked: "+(c.isBlocked()?"Yes":"No"));
            
            StringBuffer sb = new StringBuffer(String.valueOf(c.getPin()));
            for (int i = 0; i < 4-sb.capacity(); i++) {
                sb.insert(0, "0");
            }
            this.pinField.setText(sb.toString());
            
        } catch (Exception ex) {ex.printStackTrace();}
    }//GEN-LAST:event_cardBoxActionPerformed

    private void toggleBlockedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleBlockedButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toggleBlockedButtonActionPerformed

    private void newCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCardButtonActionPerformed
        try {
            long number;
            do{
            int n1 = new Random().nextInt()%90000000;
            int n2 = new Random().nextInt()%100000000;
            number = Math.abs(10000000000000000L+((long)n1)*10000000 + (long)n2);
                System.out.println(number);
            }while (new BankDB().doesCardExist(number));

            int accnum = (int)this.accountBox.getSelectedItem();
            new BankDB().createCard(number, accnum, 1+new Random().nextInt(9999));
        } catch (Exception ex) {ex.printStackTrace();}
    }//GEN-LAST:event_newCardButtonActionPerformed

    private void changePINButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePINButtonActionPerformed
        if (pinField.getPassword().length != 4) return;
        try {
            int PIN = Integer.parseInt(String.valueOf(pinField.getPassword()));

        } catch (Exception ex) {ex.printStackTrace();}
    }//GEN-LAST:event_changePINButtonActionPerformed

    private void showPINButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPINButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showPINButtonActionPerformed

    private void showPINButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPINButtonMousePressed
        pinField.setEchoChar((char)0);
    }//GEN-LAST:event_showPINButtonMousePressed

    private void showPINButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPINButtonMouseReleased
        pinField.setEchoChar('\u25cf');
    }//GEN-LAST:event_showPINButtonMouseReleased


    public void load(List<Card> cards) {
        this.cardBox.removeAllItems();
        for (Card c : cards) {
            this.cardBox.addItem(c.getNumber());
        }
        this.accountBox.removeAllItems();
        JComboBox accounts = Manage.getInstance().getAccounts().getAccountBox();
        for (int i = 0; i < accounts.getItemCount(); i++) {
            this.accountBox.addItem(accounts.getItemAt(i));
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account;
    private javax.swing.JComboBox accountBox;
    private javax.swing.JLabel blocked;
    private javax.swing.JComboBox cardBox;
    private javax.swing.JButton changePINButton;
    private javax.swing.JButton newCardButton;
    private javax.swing.JPasswordField pinField;
    private javax.swing.JButton showPINButton;
    private javax.swing.JButton toggleBlockedButton;
    // End of variables declaration//GEN-END:variables
}
