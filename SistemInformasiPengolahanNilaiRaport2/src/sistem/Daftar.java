
package sistem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import mainkoneksi.koneksi2;

/**
 *
 * @author fidia
 */
public class Daftar extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();

    public Daftar() {
        initComponents();
        AutoNomor();
        isiComboId();
    } 
    
    void Klik(){
        String sql = "insert into tb_pengguna values (?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txid.getText());
            stat.setString(2,txidGur.getText());
            stat.setString(3,tduNama1.getText());
            stat.setString(4,tduUser.getText());
            stat.setString(5,tduPass.getText());
            stat.setString(6,(String)cbuLev.getSelectedItem());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil mendaftar");
            Kosong();
            Login mm = new Login();
            this.dispose();
            mm.setVisible(true);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mendaftar "+e);
        }
    } 
    
    private void Kosong(){
        txid.setText("");
        txidGur.setText("");
        tduNama1.setText("");
        tduUser.setText("");
        cbuLev.setSelectedIndex(0);
        tduPass.setText("");
        tduPass2.setText("");
        tduToken.setText("");
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_pengguna order by id_pengguna desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String idg = rs.getString("id_pengguna");
                 String AN = "" + (Integer.parseInt(idg)+1);
                 txid.setText(AN);
             } else {
                 txid.setText("1");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
    }
    
     public void isiComboId(){
        
        try {
            Statement st;
             ResultSet rs;
             String sql;
             sql ="SELECT * FROM tb_guru";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                cbidGur.addItem(rs.getString("id_guru"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tduUser = new javax.swing.JTextField();
        txid = new javax.swing.JTextField();
        tduPass = new javax.swing.JPasswordField();
        tduPass2 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        cbuLev = new javax.swing.JComboBox();
        tduToken = new javax.swing.JPasswordField();
        brDaftar = new javax.swing.JButton();
        btDafBat = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tduNama1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txidGur = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbidGur = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("id");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel3.setText("Nama User");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel4.setText("Password");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel5.setText("Ketik Ulang Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel6.setText("Token Registrasi");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));
        jPanel1.add(tduUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 229, -1));

        txid.setEditable(false);
        jPanel1.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 120, -1));
        jPanel1.add(tduPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 229, -1));
        jPanel1.add(tduPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 229, -1));

        jLabel7.setText("Level");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        cbuLev.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Guru", "Wali", "Kepsek" }));
        jPanel1.add(cbuLev, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 118, -1));
        jPanel1.add(tduToken, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 229, -1));

        brDaftar.setText("Daftar");
        brDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brDaftarActionPerformed(evt);
            }
        });
        jPanel1.add(brDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, -1));

        btDafBat.setText("Batal");
        btDafBat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDafBatActionPerformed(evt);
            }
        });
        jPanel1.add(btDafBat, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, -1, -1));

        jLabel8.setText("Nama Lengkap");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));
        jPanel1.add(tduNama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 229, -1));

        jLabel9.setText("id guru");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        txidGur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txidGurMouseExited(evt);
            }
        });
        txidGur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txidGurKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txidGurKeyTyped(evt);
            }
        });
        jPanel1.add(txidGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 120, -1));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Daftar Pengguna");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 442, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 90));

        cbidGur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih id -" }));
        cbidGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidGurActionPerformed(evt);
            }
        });
        jPanel1.add(cbidGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btDafBatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDafBatActionPerformed
        // batal
        Login mm=new Login();
        this.dispose();
        mm.setVisible(true);
    }//GEN-LAST:event_btDafBatActionPerformed

    private void brDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brDaftarActionPerformed
        // Daftar
        String pw = tduPass.getText();
        String pw2 = tduPass2.getText();
        String tk = String.valueOf(tduToken.getPassword());
        String token = "1234";
        
        if (token.equals(tk)) {
            if (pw.equals(pw2)) {
                Klik();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf Password yang anda masukkan "
                        + "tidak sama, silahkan coba kembali");
                tduPass.setText("");
                tduPass2.setText("");
                tduPass.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Token yang anda masukkan tidak valid, "
                    + "silahkan periksa kembali");
            tduToken.requestFocus();
        }
    }//GEN-LAST:event_brDaftarActionPerformed

    private void cbidGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidGurActionPerformed
        // cb klik
        try {
            Statement st;
            ResultSet rs;
            String sql ="SELECT * FROM tb_guru where id_guru='"
                    +String.valueOf(cbidGur.getSelectedItem())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txidGur.setText(rs.getString("id_guru"));
                tduNama1.setText(rs.getString("nm_guru"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cbidGurActionPerformed

    private void txidGurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txidGurMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txidGurMouseExited

    private void txidGurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txidGurKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txidGurKeyTyped

    private void txidGurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txidGurKeyReleased
        // TODO add your handling code here:
         try {
            Statement st;
            ResultSet rs;
            String sql ="SELECT * FROM tb_guru where id_guru='"+txidGur.getText()+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            if (txidGur.getText().equals("")) {
                tduNama1.setText("");
            } else  {
                while(rs.next()){
                tduNama1.setText(rs.getString("nm_guru"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_txidGurKeyReleased

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
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Daftar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brDaftar;
    private javax.swing.JButton btDafBat;
    private javax.swing.JComboBox cbidGur;
    private javax.swing.JComboBox cbuLev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tduNama1;
    private javax.swing.JPasswordField tduPass;
    private javax.swing.JPasswordField tduPass2;
    private javax.swing.JPasswordField tduToken;
    private javax.swing.JTextField tduUser;
    private javax.swing.JTextField txid;
    private javax.swing.JTextField txidGur;
    // End of variables declaration//GEN-END:variables
}
