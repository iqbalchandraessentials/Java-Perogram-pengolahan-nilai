
package sistem;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.koneksi2;

/**
 *
 * @author fidia
 */
public class KelolaPengguna extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;

    public KelolaPengguna() {
        initComponents();
        dataTable();
        AutoNomor();
        isiComboId();
        SaveAktif();
    }
    
    protected void dataTable(){
        Object [] baris = {"id","id guru","nama guru","username","password","level"};
        tabmode = new DefaultTableModel(null, baris);
        tbPengguna.setModel(tabmode);
        String sql="select*from tb_pengguna";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_pengguna");
            String idg = hasil.getString("id_guru");
            String nm = hasil.getString("nm_guru");
            String us = hasil.getString("username");
            String ps = hasil.getString("password");
            String lv = hasil.getString("level");
           
            String[]data = {id,idg,nm,us,ps,lv};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tbPengguna.getSelectedRow();
        txid.setText(tabmode.getValueAt(bar, 0).toString());
        txidGur.setText(tabmode.getValueAt(bar, 1).toString());
        txNm.setText(tabmode.getValueAt(bar, 2).toString());
        txUser.setText(tabmode.getValueAt(bar, 3).toString());
        txPass.setText(tabmode.getValueAt(bar, 4).toString());
        cbuLev.setSelectedItem(tabmode.getValueAt(bar, 5).toString());

    }
    
    public void Kosong(){
        txid.setText("");
        txidGur.setText("");
        txNm.setText("");
        txUser.setText("");
        txPass.setText("");
        cbuLev.setSelectedIndex(0);
        cbidGur.setSelectedIndex(0);
        AutoNomor();
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
     
     public void SaveAktif() {
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(true);
        txidGur.requestFocus();
    }
    
    public void SaveUnAktif(){
        btHapus.setEnabled(true);
        btUbah1.setEnabled(true);
        btSimpan.setEnabled(false);
    }
    
    public void Simpan(){
        String sql = "insert into tb_pengguna values (?,?,?,?,?,?)";
        try {
            if (txidGur.getText().equals("")||txNm.getText().equals("")
                    ||txUser.getText().equals("")||txPass.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!"); 
                txidGur.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txid.getText());
            stat.setString(2,txidGur.getText());
            stat.setString(3,txNm.getText());
            stat.setString(4,txUser.getText());
            stat.setString(5,txPass.getText());
            stat.setString(6,String.valueOf(cbuLev.getSelectedItem()));
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            Kosong();
            dataTable();
            AutoNomor();
            txidGur.requestFocus();
            } 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
        }
    }
    
    public void Ubah(){
        try{
            String sql ="update tb_pengguna set id_guru=?, nm_guru=?, "
                    + "username=?, password=?, level=? where "
                    + "id_pengguna='"+txid.getText()+"'";
            if (txidGur.getText().equals("")||txNm.getText().equals("")
                    ||txUser.getText().equals("")||txPass.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txidGur.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txidGur.getText());
            stat.setString(2,txNm.getText());
            stat.setString(3,txUser.getText());
            stat.setString(4,txPass.getText());
            stat.setString(5,String.valueOf(cbuLev.getSelectedItem()));
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            dataTable();
            AutoNomor();
            stat.close();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }
    
    public void Hapus(){
        if (txidGur.getText().equals("")||txNm.getText().equals("")
                ||txUser.getText().equals("")||txPass.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin "
                    + "ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_pengguna where id_pengguna='"+txid.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Data berhasil di hapus");
                Kosong();
                dataTable();
                AutoNomor();
                stat.close();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(rootPane, "Data gagal di hapus "+e);
            }
        } else {
            Kosong();
        }
        }
    } 
    
    public void Cari(){
        int row=tbPengguna.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "tb_pengguna where nm_guru like '%"+txCari.getText()+"%' "
                    + "or username like '%"+txCari.getText()+"%'"
                    + "or level like '%"+txCari.getText()+"%'"
                    + "or id_guru like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(KelolaPengguna.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        window = new javax.swing.JPanel();
        btKel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        txidGur = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txNm = new javax.swing.JTextField();
        txUser = new javax.swing.JTextField();
        cbidGur = new javax.swing.JComboBox();
        cbuLev = new javax.swing.JComboBox();
        txPass = new javax.swing.JPasswordField();
        cbLihat = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPengguna = new javax.swing.JTable();
        txCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btUbah1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        window.setBackground(new java.awt.Color(0, 102, 153));
        window.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                windowMouseDragged(evt);
            }
        });
        window.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                windowMousePressed(evt);
            }
        });
        window.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btKel.setBackground(new java.awt.Color(255, 0, 0));
        btKel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btKel.setText("X");
        btKel.setIconTextGap(1);
        btKel.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btKel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKelActionPerformed(evt);
            }
        });
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 40));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Kelola Data Pengguna");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("id");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Level");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        txid.setEditable(false);
        jPanel2.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 27, 90, -1));
        jPanel2.add(txidGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 58, 90, -1));

        jLabel4.setText("id guru");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel5.setText("Nama");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel6.setText("Username");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel7.setText("Password");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel2.add(txNm, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 180, -1));
        jPanel2.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 180, -1));

        cbidGur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih id -" }));
        cbidGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidGurActionPerformed(evt);
            }
        });
        jPanel2.add(cbidGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 80, -1));

        cbuLev.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Guru", "Wali", "Kepsek" }));
        jPanel2.add(cbuLev, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 118, -1));
        jPanel2.add(txPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 180, -1));

        cbLihat.setBackground(new java.awt.Color(204, 204, 255));
        cbLihat.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cbLihat.setText("Lihat");
        cbLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLihatActionPerformed(evt);
            }
        });
        jPanel2.add(cbLihat, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 50, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 310, 220));

        tbPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenggunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPengguna);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 360, 240));

        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 170, -1));

        btCari.setText("cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 60, -1));

        btBatal.setText("Batal");
        btBatal.setToolTipText("Batal");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        btSimpan.setText("Simpan");
        btSimpan.setToolTipText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, -1, -1));

        btHapus.setText("Hapus");
        btHapus.setToolTipText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

        btUbah1.setText("Ubah");
        btUbah1.setToolTipText("Ubah");
        btUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btUbah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKelActionPerformed
        // keluar
        dispose();
    }//GEN-LAST:event_btKelActionPerformed

    int x, y;
    private void windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMousePressed
        // window hold
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_windowMousePressed

    private void windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMouseDragged
        // mouse drag
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_windowMouseDragged

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // Simpan
        Simpan();
        SaveAktif();
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // hapus
        Hapus();
        SaveAktif();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        // batal
        Kosong();
        SaveAktif();
    }//GEN-LAST:event_btBatalActionPerformed

    private void btUbah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbah1ActionPerformed
        // Ubah
        Ubah();
        SaveAktif();
    }//GEN-LAST:event_btUbah1ActionPerformed

    private void tbPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenggunaMouseClicked
        // tabel klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tbPenggunaMouseClicked

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        // Cari
        Cari();
    }//GEN-LAST:event_btCariActionPerformed

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
        // cari enter
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
            Cari();
        }
    }//GEN-LAST:event_txCariKeyPressed

    private void cbidGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidGurActionPerformed
        // cb klik
        try {
            Statement st;
            ResultSet rs;
            String sql ="SELECT * FROM tb_guru where id_guru='"+String.valueOf(cbidGur.getSelectedItem())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txidGur.setText(rs.getString("id_guru"));
                txNm.setText(rs.getString("nm_guru"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cbidGurActionPerformed

    private void cbLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLihatActionPerformed
        // tampil password
        if (cbLihat.isSelected()) {
            txPass.setEchoChar((char)0);
            txPass.requestFocus();
        } else  {
            txPass.setEchoChar('*');
        }
    }//GEN-LAST:event_cbLihatActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KelolaPengguna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btKel;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btUbah1;
    private javax.swing.JCheckBox cbLihat;
    private javax.swing.JComboBox cbidGur;
    private javax.swing.JComboBox cbuLev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbPengguna;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txNm;
    private javax.swing.JPasswordField txPass;
    private javax.swing.JTextField txUser;
    private javax.swing.JTextField txid;
    private javax.swing.JTextField txidGur;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
