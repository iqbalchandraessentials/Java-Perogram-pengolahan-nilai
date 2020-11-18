
package sistem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mainkoneksi.koneksi2;

/**
 *
 * @author fidia
 */
public class ProfilPengguna extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    String tanggal1;

    public ProfilPengguna() {
        initComponents();
        txPUser.disable();
        txPass.disable();
        txPass2.disable();
        PanelProf.setVisible(false);
        btBatal.setVisible(false);
    }
    
    public JTextField getUser() {
        return txPUser;
    }

    public void setUser(JTextField txPUser) {
        this.txPUser = txPUser;
    }
    
    public void AutoRun(){
        try {
            Statement st;
            ResultSet rs,rs2;
            String sql ="SELECT * FROM tb_pengguna WHERE username='"+txPUser.getText()+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txid.setText(rs.getString("id_pengguna"));
                txLev.setText(rs.getString("level"));
                txPass.setText(rs.getString("password"));
                txPass2.setText(rs.getString("password"));
                txidGur.setText(rs.getString("id_guru"));
            }
            String sql2 ="SELECT * FROM tb_guru WHERE id_guru='"+txidGur.getText()+"'";
            rs2=st.executeQuery(sql2);
            while(rs2.next()){
                txNmGur.setText(rs2.getString("nm_guru"));
                txTemp.setText(rs2.getString("tmp_lahir"));
                String date = rs2.getString("tgl_lahir");
                Date tanggal = null;
                try {
                tanggal = new SimpleDateFormat("dd MMMM yyyy").parse(date);
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                txTgl.setDate(tanggal);
                if (rs2.getString("jenkel").equals("perempuan")){
                    radioGurP.setSelected(true);
                } else {
                    radioGurL.setSelected(true);
                };
                cbGurAgama.addItem(rs2.getString("agama"));
                txAlamat.setText(rs2.getString("alamat"));
                txTlp.setText(rs2.getString("no_telp"));
                txPend.setText(rs2.getString("pend_terakhir"));
                txper.setText(rs2.getString("perguruan"));
                txJabatan.setText(rs2.getString("jabatan"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void Kosong(){
        txid.setText("");
        txLev.setText("");
        txPass.setText("");
        txPass2.setText("");
        txidGur.setText("");
        txNmGur.setText("");
        txTemp.setText("");
        txTgl.setDate(null);
        cbGurAgama.setSelectedIndex(0);
        txAlamat.setText("");
        txTlp.setText("");
        txPend.setText("");
        txper.setText("");
        txJabatan.setText("");
    }
    
    public void Ubah(){
    try{
        if (!txPass.getText().equals(txPass2.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Maaf Password tidak sama!");
                txPass.requestFocus();
            } else {
            String sql2 ="update tb_pengguna set id_guru=?, nm_guru=?, username=?, "
                    + "password=?, level=? where id_pengguna='"+txid.getText()+"'";
            PreparedStatement stat2 = conn.prepareStatement(sql2);
            stat2.setString(1,txidGur.getText());
            stat2.setString(2,txNmGur.getText());
            stat2.setString(3,txPUser.getText());
            stat2.setString(4,txPass.getText());
            stat2.setString(5,txLev.getText());
            stat2.executeUpdate();
            
            String sql ="update tb_guru set nm_guru=?, tmp_lahir=?, tgl_lahir=?, "
                    + "jenkel=?, agama=?, alamat=?, no_telp=?, pend_terakhir=?, "
                    + "perguruan=?, jabatan=? where id_guru='"+txidGur.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            SimpleDateFormat Format=new SimpleDateFormat("dd MMMM yyyy");
            tanggal1 = Format.format(txTgl.getDate());
            stat.setString(1,txNmGur.getText());
            stat.setString(2,txTemp.getText());
            stat.setString(3,tanggal1);
            String G = "" ;
            if (radioGurL.isSelected())
                G = "laki-laki";
            else if (radioGurP.isSelected())
                    G = "perempuan";
            stat.setString(4,G);
            stat.setString(5,String.valueOf(cbGurAgama.getSelectedItem()));
            stat.setString(6,txAlamat.getText());
            stat.setString(7,txTlp.getText());
            stat.setString(8,txPend.getText());
            stat.setString(9,txper.getText());
            stat.setString(10,txJabatan.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            AutoRun();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btkeluar = new javax.swing.JButton();
        btSimp = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txPUser = new javax.swing.JTextField();
        txPass = new javax.swing.JPasswordField();
        clTampil = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txPass2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txLev = new javax.swing.JTextField();
        PanelProf = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txTgl = new com.toedter.calendar.JDateChooser();
        radioGurL = new javax.swing.JRadioButton();
        radioGurP = new javax.swing.JRadioButton();
        txNmGur = new javax.swing.JTextField();
        txTemp = new javax.swing.JTextField();
        txAlamat = new javax.swing.JTextField();
        txTlp = new javax.swing.JTextField();
        txPend = new javax.swing.JTextField();
        txper = new javax.swing.JTextField();
        txJabatan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbGurAgama = new javax.swing.JComboBox();
        txidGur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btKelola = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btkeluar.setBackground(new java.awt.Color(255, 0, 0));
        btkeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btkeluar.setText("X");
        btkeluar.setToolTipText("keluar");
        btkeluar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btkeluarActionPerformed(evt);
            }
        });
        jPanel2.add(btkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 40, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 30));

        btSimp.setText("Simpan");
        btSimp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpActionPerformed(evt);
            }
        });
        jPanel1.add(btSimp, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 680, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rubah Password"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Username");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));
        jPanel3.add(txPUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, -1));
        jPanel3.add(txPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 310, -1));

        clTampil.setBackground(new java.awt.Color(153, 153, 255));
        clTampil.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        clTampil.setText("Tampilkan password");
        clTampil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clTampilActionPerformed(evt);
            }
        });
        jPanel3.add(clTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, 20));

        jLabel4.setText("Konfirmasi");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel3.setText("Password");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));
        jPanel3.add(txPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 310, -1));

        jLabel5.setText("Level");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        txid.setEditable(false);
        txid.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 110, -1));

        jLabel6.setText("id pengguna");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txLev.setEditable(false);
        txLev.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.add(txLev, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 130, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 450, 180));

        PanelProf.setBackground(new java.awt.Color(153, 153, 255));
        PanelProf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Profil"));
        PanelProf.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("jabatan");
        PanelProf.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel8.setText("id guru");
        PanelProf.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel9.setText("Nama");
        PanelProf.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel10.setText("Tempat Lahir");
        PanelProf.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txTgl.setAutoscrolls(true);
        txTgl.setDateFormatString("dd MMMM yyyy");
        PanelProf.add(txTgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 160, 23));

        radioGurL.setBackground(new java.awt.Color(153, 153, 255));
        buttonGroup1.add(radioGurL);
        radioGurL.setText("Laki-Laki");
        PanelProf.add(radioGurL, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, 20));

        radioGurP.setBackground(new java.awt.Color(153, 153, 255));
        buttonGroup1.add(radioGurP);
        radioGurP.setText("Perempuan");
        PanelProf.add(radioGurP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, 20));
        PanelProf.add(txNmGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 270, -1));
        PanelProf.add(txTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 270, -1));
        PanelProf.add(txAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 270, -1));
        PanelProf.add(txTlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 270, -1));
        PanelProf.add(txPend, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 270, -1));
        PanelProf.add(txper, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 270, -1));

        txJabatan.setEditable(false);
        txJabatan.setBackground(new java.awt.Color(204, 204, 204));
        PanelProf.add(txJabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 270, -1));

        jLabel11.setText("Tanggal Lahir");
        PanelProf.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel12.setText("jenis kelamin");
        PanelProf.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel13.setText("agama");
        PanelProf.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel14.setText("alamat");
        PanelProf.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel15.setText("no. telp");
        PanelProf.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel16.setText("pendidikan terakhir");
        PanelProf.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel17.setText("perguruan");
        PanelProf.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        cbGurAgama.setBackground(new java.awt.Color(204, 204, 255));
        cbGurAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Hindu", "Budha", "Katolik" }));
        cbGurAgama.setMinimumSize(new java.awt.Dimension(58, 30));
        cbGurAgama.setName(""); // NOI18N
        PanelProf.add(cbGurAgama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 110, -1));

        txidGur.setEditable(false);
        txidGur.setBackground(new java.awt.Color(204, 204, 204));
        PanelProf.add(txidGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 110, -1));

        jPanel1.add(PanelProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 450, 380));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Profil Pengguna");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 241, -1));

        btKelola.setText("Kelola");
        btKelola.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btKelola.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btKelola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKelolaActionPerformed(evt);
            }
        });
        jPanel1.add(btKelola, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 70, -1));

        btBatal.setText("Batal");
        btBatal.setToolTipText("Batal");
        btBatal.setActionCommand("Batal");
        btBatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btBatal.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btBatal.setMaximumSize(new java.awt.Dimension(35, 21));
        btBatal.setMinimumSize(new java.awt.Dimension(35, 21));
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clTampilActionPerformed
        // tampil password
        if (clTampil.isSelected()) {
            txPass.setEchoChar((char)0);
            txPass2.setEchoChar((char)0);
            txPass.requestFocus();
        } else  {
            txPass.setEchoChar('*');
            txPass2.setEchoChar('*');
        }
    }//GEN-LAST:event_clTampilActionPerformed

    private void btkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btkeluarActionPerformed
        // keluar
        dispose();
    }//GEN-LAST:event_btkeluarActionPerformed
       int x,y;
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed
                                   
    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void btSimpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpActionPerformed
        // simpan
        Ubah();
    }//GEN-LAST:event_btSimpActionPerformed

    private void btKelolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKelolaActionPerformed
        // kelola
        txPUser.enable();
        txPass.enable();
        txPass2.enable();
        PanelProf.setVisible(true);
        AutoRun();
        btKelola.setVisible(false);
        btBatal.setVisible(true);
    }//GEN-LAST:event_btKelolaActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        // Batal
        txPUser.disable();
        txPass.disable();
        txPass2.disable();
        Kosong();
        PanelProf.setVisible(false);
        btBatal.setVisible(false);
        btKelola.setVisible(true);
    }//GEN-LAST:event_btBatalActionPerformed

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
            java.util.logging.Logger.getLogger(ProfilPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfilPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfilPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfilPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfilPengguna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelProf;
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btKelola;
    private javax.swing.JButton btSimp;
    private javax.swing.JButton btkeluar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbGurAgama;
    private javax.swing.JCheckBox clTampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton radioGurL;
    private javax.swing.JRadioButton radioGurP;
    private javax.swing.JTextField txAlamat;
    private javax.swing.JTextField txJabatan;
    private javax.swing.JTextField txLev;
    private javax.swing.JTextField txNmGur;
    public javax.swing.JTextField txPUser;
    private javax.swing.JPasswordField txPass;
    private javax.swing.JPasswordField txPass2;
    private javax.swing.JTextField txPend;
    private javax.swing.JTextField txTemp;
    private com.toedter.calendar.JDateChooser txTgl;
    private javax.swing.JTextField txTlp;
    private javax.swing.JTextField txid;
    private javax.swing.JTextField txidGur;
    private javax.swing.JTextField txper;
    // End of variables declaration//GEN-END:variables
}
