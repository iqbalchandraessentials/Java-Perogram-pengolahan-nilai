
package GUI_IM;

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
public class GKelas extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;


    public GKelas() {
        initComponents();
        dataTable();
        isiCombo();
        SaveAktif();
        txKdKelas.requestFocus();
    }
    
    protected void dataTable(){
        Object [] baris = {"id kelas","kelas","id guru","guru"};
        tabmode = new DefaultTableModel(null, baris);
        tbKelas.setModel(tabmode);
        String sql="select*from tb_kelas";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String kd = hasil.getString("id_kelas");
            String mp = hasil.getString("kelas");
            String idp = hasil.getString("id_guru");
            String np = hasil.getString("nm_guru");
           
            String[]data = {kd,mp,idp,np};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tbKelas.getSelectedRow();
        txKdKelas.setText(tabmode.getValueAt(bar, 0).toString());
        txNmKelas1.setText(tabmode.getValueAt(bar, 1).toString());
        String vnama = String.valueOf(tabmode.getValueAt(bar, 2).toString());
        cbNmGur.setSelectedItem(vnama);
        txNmGur.setText(tabmode.getValueAt(bar, 3).toString());

    }
    
    public void Kosong(){
        txKdKelas.setText("");
        txNmKelas1.setText("");
        cbNmGur.setSelectedIndex(0);
        txNmGur.setText("");
        txKdKelas.requestFocus();
    }
    
    public void SaveAktif() {
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(true);
    }
    
    public void SaveUnAktif(){
        btHapus.setEnabled(true);
        btUbah1.setEnabled(true);
        btSimpan.setEnabled(false);
    }

    
    public void isiCombo(){
        try {
            Statement st;
            ResultSet rs;
            String sql ="SELECT * FROM tb_guru";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbNmGur.addItem(rs.getString("id_guru"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void Simpan(){
        String sql = "insert into tb_kelas values (?,?,?,?)";
        try {
            if (txKdKelas.getText().equals("")||txNmKelas1.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!"); 
                txKdKelas.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txKdKelas.getText());
            stat.setString(2,txNmKelas1.getText());
            stat.setString(3,String.valueOf(cbNmGur.getSelectedItem()));
            stat.setString(4,txNmGur.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            Kosong();
            dataTable();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
        }
    }
    
    public void Ubah(){
        try{
            String sql ="update tb_kelas set kelas=?, id_guru=?, nm_guru=? where id_kelas='"+txKdKelas.getText()+"'";
            if (txKdKelas.getText().equals("")||txNmKelas1.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txKdKelas.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txNmKelas1.getText());
            stat.setString(2,String.valueOf(cbNmGur.getSelectedItem()));
            stat.setString(3,txNmGur.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            dataTable();
            stat.close();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }
    
    public void Hapus(){
        if (txKdKelas.getText().equals("")||txNmKelas1.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txKdKelas.requestFocus();
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin "
                    + "menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_kelas where id_kelas='"+txKdKelas.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Data berhasil di hapus");
                Kosong();
                dataTable();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(rootPane, "Data gagal di hapus "+e);
            }
        } else {
            Kosong();
        }
        }
    } 
    
    public void Cari(){
        int row=tbKelas.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from tb_kelas where kelas like '%"+txCari.getText()+"%' or id_kelas like '%"+txCari.getText()+"%' or nm_guru like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GKelas.class.getName()).log(Level.SEVERE, null, ex);  
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
        jLabel4 = new javax.swing.JLabel();
        txKdKelas = new javax.swing.JTextField();
        txNmGur = new javax.swing.JTextField();
        cbNmGur = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txNmKelas1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKelas = new javax.swing.JTable();
        txCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();
        btUbah1 = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();

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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 40));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Kelola Data Kelas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Kode Kelas");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Nama Kelas");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setText("Nama Guru");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));
        jPanel2.add(txKdKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 27, 167, -1));

        txNmGur.setEditable(false);
        jPanel2.add(txNmGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 166, -1));

        cbNmGur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-pilih guru-" }));
        cbNmGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNmGurActionPerformed(evt);
            }
        });
        jPanel2.add(cbNmGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 166, -1));

        jLabel5.setText("id Guru");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        jPanel2.add(txNmKelas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 58, 166, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 280, 170));

        tbKelas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKelas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 320, 240));

        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 120, 170, -1));

        btCari.setText("cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 60, -1));

        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, -1, 30));

        btUbah1.setText("Ubah");
        btUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btUbah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 70, 30));

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 70, 30));

        btBatal.setText("Batal");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 70, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 410));

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

    private void cbNmGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNmGurActionPerformed
        //cb id gur
        try {
            Statement st;
            ResultSet rs;
            String sql ="SELECT * FROM tb_guru WHERE id_guru='"+String.valueOf(cbNmGur.getSelectedItem())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txNmGur.setText(rs.getString("nm_guru"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cbNmGurActionPerformed

    private void tbKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKelasMouseClicked
        // tabel klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tbKelasMouseClicked

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

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // simpan
        Simpan();
        SaveAktif();
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btUbah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbah1ActionPerformed
        // ubah
        Ubah();
        SaveAktif();
    }//GEN-LAST:event_btUbah1ActionPerformed

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
            java.util.logging.Logger.getLogger(GKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GKelas().setVisible(true);
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
    private javax.swing.JComboBox cbNmGur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbKelas;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txKdKelas;
    private javax.swing.JTextField txNmGur;
    private javax.swing.JTextField txNmKelas1;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
