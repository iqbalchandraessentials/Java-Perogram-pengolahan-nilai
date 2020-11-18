
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
public class GStatus extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;


    public GStatus() {
        initComponents();
        dataTable();
        isiComboNIS();
        isiComboKelas();
        isiComboKomp();
        isiComboSms();
        isiComboTA();
        SaveAktif();
    }
    
    protected void dataTable(){
        Object [] baris = {"id","id siswa","Nama","Kelas","Kompetensi","Semester","Tahun Ajaran"};
        tabmode = new DefaultTableModel(null, baris);
        tbMapel.setModel(tabmode);
        String sql="select*from tb_status_siswa";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_status");
            String ns = hasil.getString("id_siswa");
            String nm = hasil.getString("nm_siswa");
            String kl = hasil.getString("id_kelas");
            String kp = hasil.getString("id_kompetensi");
            String sm = hasil.getString("id_semester");
            String ta = hasil.getString("id_ta");
           
            String[]data = {id,ns,nm,kl,kp,sm,ta};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tbMapel.getSelectedRow();
        txid.setText(tabmode.getValueAt(bar, 0).toString());
        String vnis = String.valueOf(tabmode.getValueAt(bar, 1).toString());
        cbNIS.setSelectedItem(vnis);
        txNmSis.setText(tabmode.getValueAt(bar, 2).toString());
        String vkls = String.valueOf(tabmode.getValueAt(bar, 3).toString());
        cbKelas1.setSelectedItem(vkls);
        String vkomp = String.valueOf(tabmode.getValueAt(bar, 4).toString());
        cbKomp1.setSelectedItem(vkomp);
        String vsms = String.valueOf(tabmode.getValueAt(bar, 5).toString());
        cbSemester.setSelectedItem(vsms);
        String vta = String.valueOf(tabmode.getValueAt(bar, 6).toString());
        cbTa.setSelectedItem(vta);

    }
    
    public void Kosong(){
        cbNIS.setSelectedIndex(0);
        txNmSis.setText("");
        cbKelas1.setSelectedIndex(0);
        cbKomp1.setSelectedIndex(0);
        cbSemester.setSelectedIndex(0);
        cbTa.setSelectedIndex(0);
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_status_siswa order by id_status desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String nis = rs.getString("id_status").substring(2);
                 String AN = "" + (Integer.parseInt(nis)+1);
                 String Nol = "";
                 
                if (AN.length()==2) {
                     Nol ="00";
                 }if (AN.length()==3) {
                     Nol ="0";
                 } if (AN.length()==4) {
                     Nol="";
                 }
                 txid.setText("ST"+Nol+AN);
                 
             } else {
                 txid.setText("ST1001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    public void isiComboNIS(){
        try {
            Statement st;
             ResultSet rs;
             String sql;
             sql ="SELECT * FROM tb_siswa";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                 cbNIS.addItem(rs.getString("id_siswa"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void isiComboKelas(){
        try {
            Statement st;
            ResultSet rs;
            String sql;
            sql ="SELECT * FROM tb_kelas";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbKelas1.addItem(rs.getString("id_kelas"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void isiComboKomp(){
        try {
            Statement st;
             ResultSet rs;
             String sql;
             sql ="SELECT * FROM tb_kompetensi";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                 cbKomp1.addItem(rs.getString("id_kompetensi"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void isiComboSms(){
        try {
            Statement st;
            ResultSet rs;
            String sql;
            sql ="SELECT * FROM tb_semester";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbSemester.addItem(rs.getString("id_semester"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void isiComboTA(){
        try {
            Statement st;
            ResultSet rs;
            String sql;
            sql ="SELECT * FROM tb_tahun_ajaran";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbTa.addItem(rs.getString("id_ta"));
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
        AutoNomor();
    }
    
    public void SaveUnAktif(){
        btHapus.setEnabled(true);
        btUbah1.setEnabled(true);
        btSimpan.setEnabled(false);
    }
    
    public void Simpan(){
        String sql = "insert into tb_status_siswa values (?,?,?,?,?,?,?)";
        try {
            if (txNmSis.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txid.getText());
            stat.setString(2,String.valueOf(cbNIS.getSelectedItem()));
            stat.setString(3,txNmSis.getText());
            stat.setString(4,String.valueOf(cbKelas1.getSelectedItem()));
            stat.setString(5,String.valueOf(cbKomp1.getSelectedItem()));
            stat.setString(6,String.valueOf(cbSemester.getSelectedItem()));
            stat.setString(7,String.valueOf(cbTa.getSelectedItem()));
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
            String sql ="update tb_status_siswa set id_siswa=?, nm_siswa=?, id_kelas=?, id_kompetensi=?, id_semester=?, id_ta=? where id_status='"+txid.getText()+"'";
            if (txNmSis.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,String.valueOf(cbNIS.getSelectedItem()));
            stat.setString(2,txNmSis.getText());
            stat.setString(3,String.valueOf(cbKelas1.getSelectedItem()));
            stat.setString(4,String.valueOf(cbKomp1.getSelectedItem()));
            stat.setString(5,String.valueOf(cbSemester.getSelectedItem()));
            stat.setString(6,String.valueOf(cbTa.getSelectedItem()));
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
        if (txNmSis.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_status_siswa where id_status='"+txid.getText()+"'";
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
        int row=tbMapel.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "tb_status_siswa where nm_siswa like '%"+txCari.getText()+ 
                    "%' or id_siswa like '%"+txCari.getText()+"%' "
                    + "or id_kelas like '%"+txCari.getText()+"%' "
                    + "or id_kompetensi like '%"+txCari.getText()+"%' "
                    + "or id_semester like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3)
                        ,rs.getString(4),rs.getString(5),rs.getString(6)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GStatus.class.getName()).log(Level.SEVERE, null, ex);  
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
        jLabel5 = new javax.swing.JLabel();
        txNmSis = new javax.swing.JTextField();
        cbTa = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbNIS = new javax.swing.JComboBox();
        cbSemester = new javax.swing.JComboBox();
        cbKomp1 = new javax.swing.JComboBox();
        cbKelas1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMapel = new javax.swing.JTable();
        txCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btUbah1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 40));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Kelola Status Siswa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("id");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setText("Nama");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel4.setText("Kelas");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel5.setText("Tahun Ajaran");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));
        jPanel2.add(txNmSis, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 180, -1));

        cbTa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Tahun ajaran-" }));
        cbTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTaActionPerformed(evt);
            }
        });
        jPanel2.add(cbTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 180, -1));

        jLabel6.setText("Kompetensi");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel7.setText("Semester");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        cbNIS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-NIS Siswa-" }));
        cbNIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNISActionPerformed(evt);
            }
        });
        jPanel2.add(cbNIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 180, -1));

        cbSemester.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Semester- " }));
        cbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSemesterActionPerformed(evt);
            }
        });
        jPanel2.add(cbSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 180, -1));

        cbKomp1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Kompetensi-" }));
        cbKomp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKomp1ActionPerformed(evt);
            }
        });
        jPanel2.add(cbKomp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 180, -1));

        cbKelas1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Kelas-" }));
        cbKelas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKelas1ActionPerformed(evt);
            }
        });
        jPanel2.add(cbKelas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 180, -1));

        jLabel8.setText("id siswa");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        txid.setEditable(false);
        jPanel2.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 100, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 300, 280));

        tbMapel.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMapelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMapel);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 410, 290));

        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 170, -1));

        btCari.setText("cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 60, -1));

        btBatal.setText("Batal");
        btBatal.setToolTipText("Batal");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, -1, -1));

        btSimpan.setText("Simpan");
        btSimpan.setToolTipText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(687, 410, -1, -1));

        btHapus.setText("Hapus");
        btHapus.setToolTipText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, -1, -1));

        btUbah1.setText("Ubah");
        btUbah1.setToolTipText("Ubah");
        btUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btUbah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 470));

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

    private void cbTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTaActionPerformed
        // kombo get klik
       
    }//GEN-LAST:event_cbTaActionPerformed

    private void tbMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMapelMouseClicked
        // tabel klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tbMapelMouseClicked

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

    private void cbNISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNISActionPerformed
        // NIS klik
        String ids = String.valueOf(cbNIS.getSelectedItem());
        String vnm = "";
        try {
            Statement st;
             ResultSet rs;
             String sql;
             sql ="SELECT * FROM tb_siswa where id_siswa='"+ids+"'";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                 ids = rs.getString("id_siswa");
                 vnm = rs.getString("nm_siswa");
                 cbNIS.setSelectedItem(ids);
                 txNmSis.setText(vnm);
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cbNISActionPerformed

    private void cbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSemesterActionPerformed

    private void cbKomp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKomp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKomp1ActionPerformed

    private void cbKelas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKelas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKelas1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GStatus().setVisible(true);
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
    private javax.swing.JComboBox cbKelas1;
    private javax.swing.JComboBox cbKomp1;
    private javax.swing.JComboBox cbNIS;
    private javax.swing.JComboBox cbSemester;
    private javax.swing.JComboBox cbTa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMapel;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txNmSis;
    private javax.swing.JTextField txid;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
