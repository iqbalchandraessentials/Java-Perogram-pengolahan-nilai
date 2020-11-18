package sistem;

import DashBoard.DBAdmin;
import DashBoard.DBGuru;
import DashBoard.DBKepsek;
import DashBoard.DBWali;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mainkoneksi.koneksi2;


public class Login extends javax.swing.JFrame {
    private final Connection conn = new koneksi2().connect(); 
    
    public Login() {
        initComponents();
        this.eyeHide.setVisible(false);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        eyeShow = new javax.swing.JLabel();
        eyeHide = new javax.swing.JLabel();
        LogTFUserName = new javax.swing.JTextField();
        LogTFPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LogLupaPass = new javax.swing.JLabel();
        LogBtLogin = new javax.swing.JButton();
        LogBtDaftar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtExit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smk.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 230, 220));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eyeShow.setBackground(new java.awt.Color(255, 255, 255));
        eyeShow.setForeground(new java.awt.Color(255, 255, 255));
        eyeShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eyeShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eye.png"))); // NOI18N
        eyeShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eyeShowMousePressed(evt);
            }
        });
        jPanel3.add(eyeShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 40, 30));

        eyeHide.setBackground(new java.awt.Color(255, 255, 255));
        eyeHide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eyeHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hidden.png"))); // NOI18N
        eyeHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eyeHideMousePressed(evt);
            }
        });
        jPanel3.add(eyeHide, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 40, 30));

        LogTFUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LogTFUserName.setToolTipText("");
        LogTFUserName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        LogTFUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogTFUserNameActionPerformed(evt);
            }
        });
        jPanel3.add(LogTFUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 82, 326, 30));

        LogTFPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LogTFPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogTFPasswordActionPerformed(evt);
            }
        });
        LogTFPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LogTFPasswordKeyPressed(evt);
            }
        });
        jPanel3.add(LogTFPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 136, 326, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 92, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 146, -1, -1));

        LogLupaPass.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        LogLupaPass.setForeground(new java.awt.Color(255, 51, 51));
        LogLupaPass.setText("Lupa Password?");
        LogLupaPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogLupaPassMouseClicked(evt);
            }
        });
        jPanel3.add(LogLupaPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 202, -1, -1));

        LogBtLogin.setText("Login");
        LogBtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogBtLoginActionPerformed(evt);
            }
        });
        jPanel3.add(LogBtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 194, 104, -1));

        LogBtDaftar.setText("Daftar");
        LogBtDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogBtDaftarActionPerformed(evt);
            }
        });
        jPanel3.add(LogBtDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 194, 102, -1));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 540, 240));

        txtExit.setBackground(new java.awt.Color(255, 0, 0));
        txtExit.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        txtExit.setForeground(new java.awt.Color(255, 255, 255));
        txtExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtExit.setText("X");
        txtExit.setOpaque(true);
        txtExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtExitMouseClicked(evt);
            }
        });
        jPanel1.add(txtExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 28, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LogTFUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogTFUserNameActionPerformed
        
    }//GEN-LAST:event_LogTFUserNameActionPerformed

    private void LogBtDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogBtDaftarActionPerformed
       // Daftar
        Daftar mm=new Daftar();
            this.dispose();
            mm.setVisible(true);
    }//GEN-LAST:event_LogBtDaftarActionPerformed

    private void LogLupaPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogLupaPassMouseClicked
       //lupa
        LupaPassword mm=new LupaPassword();
        this.dispose();
        mm.setVisible(true); 
    }//GEN-LAST:event_LogLupaPassMouseClicked

    private void LogBtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogBtLoginActionPerformed
        loginPS();
    }//GEN-LAST:event_LogBtLoginActionPerformed

    private void LogTFPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LogTFPasswordKeyPressed
        // 
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
            loginPS();
        }
    }//GEN-LAST:event_LogTFPasswordKeyPressed

    private void LogTFPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogTFPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LogTFPasswordActionPerformed

    private void eyeShowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eyeShowMousePressed
        // show password
        eyeShow.setVisible(false);
        eyeHide.setVisible(true);
        LogTFPassword.setEchoChar((char)0);
    }//GEN-LAST:event_eyeShowMousePressed

    private void eyeHideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eyeHideMousePressed
        // hide password
        eyeHide.setVisible(false);
        eyeShow.setVisible(true);
        LogTFPassword.setEchoChar('*');
    }//GEN-LAST:event_eyeHideMousePressed

    private void txtExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtExitMouseClicked
        // Exit
        dispose();
    }//GEN-LAST:event_txtExitMouseClicked

    private void loginPS(){
    try {
        if (LogTFUserName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Username Tidak boleh kosong",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            LogTFUserName.requestFocus();
        } else if (LogTFPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Password Tidak boleh kosong",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            LogTFPassword.requestFocus();
        } else {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("SELECT * FROM tb_pengguna  WHERE "
                    + "username='"+LogTFUserName.getText()+"' AND "
                    + "password='"+LogTFPassword.getText()+"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this, "Selamat Datang " +rs.getString("nm_guru")+"!");
                if (rs.getString("level").equals("Admin")) {
                    DBAdmin am = new DBAdmin ();
                    am.getUser().setText(LogTFUserName.getText());
                    this.dispose();
                    am.setVisible(true);
                } else if (rs.getString("level").equals("Guru")){
                    DBGuru gr = new DBGuru ();
                    gr.getUser().setText(LogTFUserName.getText());
                    this.dispose();
                    gr.setVisible(true);
                } else if (rs.getString("level").equals("Wali")){
                    DBWali wl = new DBWali ();
                    wl.getUser().setText(LogTFUserName.getText());
                    this.dispose();
                    wl.setVisible(true);
                } else if(rs.getString("level").equals("Kepsek")) {
                    DBKepsek kp = new DBKepsek();
                    kp.getUser().setText(LogTFUserName.getText());
                    this.dispose();
                    kp.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username dan Password Tidak Sesuai", 
                        "Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogBtDaftar;
    private javax.swing.JButton LogBtLogin;
    private javax.swing.JLabel LogLupaPass;
    private javax.swing.JPasswordField LogTFPassword;
    private javax.swing.JTextField LogTFUserName;
    private javax.swing.JLabel eyeHide;
    private javax.swing.JLabel eyeShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel txtExit;
    // End of variables declaration//GEN-END:variables
}
