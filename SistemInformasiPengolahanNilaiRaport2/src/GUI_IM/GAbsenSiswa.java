
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
public class GAbsenSiswa extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;


    public GAbsenSiswa() {
        initComponents();
        dataTable();
        isiComboNIS();
        isiComboKelas();
        AutoNomor();
        AllUnAktif();
    }
    
    protected void dataTable(){
        Object [] baris = {"id","id status","id_siswa","nama","sakit","izin","alpha"};
        tabmode = new DefaultTableModel(null, baris);
        tbAbsen.setModel(tabmode);
        String sql="select*from tb_absen";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_absen");
            String ids = hasil.getString("id_status");
            String nis = hasil.getString("id_siswa");
            String nm = hasil.getString("nm_siswa");
            String ts = hasil.getString("sakit");
            String ti = hasil.getString("izin");
            String tal = hasil.getString("alpha");
           
            String[]data = {id,ids,nis,nm,ts,ti,tal};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tbAbsen.getSelectedRow();
        txid.setText(tabmode.getValueAt(bar, 0).toString());
        txidSts.setText(tabmode.getValueAt(bar, 1).toString());
        String vnis = String.valueOf(tabmode.getValueAt(bar, 2).toString());
        cbNIS.setSelectedItem(vnis);
        txNmSis1.setText(tabmode.getValueAt(bar, 3).toString());
        txSkt.setText(tabmode.getValueAt(bar, 4).toString());
        txIzn.setText(tabmode.getValueAt(bar, 5).toString());
        txAlp.setText(tabmode.getValueAt(bar, 6).toString());
        
//        String vnis = String.valueOf(cbNIS.getSelectedItem());
        String vnm,vkls,vkomp,vsms,vta,ids ;
        try {
            Statement st;
             ResultSet rs;
             String sql;
             sql ="SELECT * FROM tb_status_siswa WHERE id_siswa='"+vnis+"'";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                ids = rs.getString("id_status");
                vnis = rs.getString("id_siswa");
                vnm = rs.getString("nm_siswa");
                vkls = rs.getString("id_kelas");
                vkomp = rs.getString("id_kompetensi");
                vsms = rs.getString("id_semester");
                vta = rs.getString("id_ta");
                txidSts.setText(ids);
                txNmSis1.setText(vnm);
                txKelas.setText(vkls);
                txKomp.setText(vkomp);
                txSemester.setText(vsms);
                txTA.setText(vta);
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void Kosong(){
        txid.setText("");
        txidSts.setText("");
        cbNIS.setSelectedIndex(0);
        txNmSis1.setText("");
        txKelas.setText("");
        txKomp.setText("");
        txSemester.setText("");
        txTA.setText("");
        txSkt.setText("");
        txIzn.setText("");
        txAlp.setText("");
        AutoNomor();
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_absen order by id_absen desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String nis = rs.getString("id_absen").substring(3);
                 String AN = "" + (Integer.parseInt(nis)+1);
                 String Nol = "";
                 
                if (AN.length()==2) {
                     Nol ="00";
                 }if (AN.length()==3) {
                     Nol ="0";
                 } if (AN.length()==4) {
                     Nol="";
                 }
                 txid.setText("ABS"+Nol+AN);
                 
             } else {
                 txid.setText("ABS1001");
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
             sql ="SELECT * FROM tb_status_siswa where id_kelas='"+String.valueOf(cbKelas.getSelectedItem())+"'";
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
                 cbKelas.addItem(rs.getString("id_kelas"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void Simpan(){
        String sql = "insert into tb_absen values (?,?,?,?,?,?,?)";
        try {
            if (txidSts.getText().equals("")||txSkt.getText().equals("")||txIzn.getText().equals("")||txAlp.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txid.getText());
            stat.setString(2,txidSts.getText());
            stat.setString(3,String.valueOf(cbNIS.getSelectedItem()));
            stat.setString(4,txNmSis1.getText());
            stat.setString(5,txSkt.getText());
            stat.setString(6,txIzn.getText());
            stat.setString(7,txAlp.getText());
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
            String sql ="update tb_absen set id_status=?, id_siswa=?, nm_siswa=?, sakit=?, izin=?, alpha=? where id_absen='"+txid.getText()+"'";
            if (txidSts.getText().equals("")||txSkt.getText().equals("")||txIzn.getText().equals("")||txAlp.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txidSts.getText());
            stat.setString(2,String.valueOf(cbNIS.getSelectedItem()));
            stat.setString(3,txNmSis1.getText());
            stat.setString(4,txSkt.getText());
            stat.setString(5,txIzn.getText());
            stat.setString(6,txAlp.getText());
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
        if (txAlp.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_absen where id_absen='"+txid.getText()+"'";
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
        int row=tbAbsen.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "tb_absen where nm_siswa like '%"+txCari.getText()+ 
                    "%' or id_siswa like '%"+txCari.getText()+"%' "
                    + "or id_status like '%"+txCari.getText()+"%' "
                    + "or id_absen like '%"+txCari.getText()+"%' ");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3)
                        ,rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GAbsenSiswa.class.getName()).log(Level.SEVERE, null, ex);  
        }
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
    
    public void AllUnAktif(){
        cbNIS.setEnabled(false);
        btBatal.setEnabled(false);
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(false);
        tbAbsen.setVisible(false);
        cbKelas.requestFocus();
    }
    
    public void AllAktif(){
        cbNIS.setEnabled(true);
        btBatal.setEnabled(true);
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(true);
        tbAbsen.setVisible(true);
        cbNIS.requestFocus();
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
        txAlp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbNIS = new javax.swing.JComboBox();
        txNmSis1 = new javax.swing.JTextField();
        txKelas = new javax.swing.JTextField();
        txKomp = new javax.swing.JTextField();
        txSemester = new javax.swing.JTextField();
        txTA = new javax.swing.JTextField();
        txIzn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txSkt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txidSts = new javax.swing.JTextField();
        txid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbKelas = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAbsen = new javax.swing.JTable();
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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 40));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Kelola Absen Siswa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("id siswa");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel3.setText("Nama");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel4.setText("Kelas");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel5.setText("Alpha");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));
        jPanel2.add(txAlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 50, -1));

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
        jPanel2.add(cbNIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 100, -1));

        txNmSis1.setEditable(false);
        txNmSis1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txNmSis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 180, -1));

        txKelas.setEditable(false);
        txKelas.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 180, -1));

        txKomp.setEditable(false);
        txKomp.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txKomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 180, -1));

        txSemester.setEditable(false);
        txSemester.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 180, -1));

        txTA.setEditable(false);
        txTA.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 180, -1));
        jPanel2.add(txIzn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 50, -1));

        jLabel8.setText("Tahun Ajaran");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel9.setText("Sakit");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel10.setText("Izin");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));
        jPanel2.add(txSkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 50, -1));

        jLabel12.setText("id status");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txidSts.setEditable(false);
        txidSts.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txidSts, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 80, -1));

        txid.setEditable(false);
        txid.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 80, -1));

        jLabel13.setText("id");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel14.setText("Kelas");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        cbKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih kelas -" }));
        cbKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKelasActionPerformed(evt);
            }
        });
        jPanel2.add(cbKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 80, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 320, 360));

        tbAbsen.setModel(new javax.swing.table.DefaultTableModel(
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
        tbAbsen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAbsenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAbsen);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 480, 330));

        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 190, -1));

        btCari.setText("cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 60, -1));

        btBatal.setText("Batal");
        btBatal.setToolTipText("Batal");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 470, -1, -1));

        btSimpan.setText("Simpan");
        btSimpan.setToolTipText("Simpan");
        btSimpan.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 470, 70, -1));

        btHapus.setText("Hapus");
        btHapus.setToolTipText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, -1));

        btUbah1.setText("Ubah");
        btUbah1.setToolTipText("Ubah");
        btUbah1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btUbah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 510));

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

    private void tbAbsenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAbsenMouseClicked
        // tabel klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tbAbsenMouseClicked

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
        // cb NIS
        String vnis = String.valueOf(cbNIS.getSelectedItem());
        String vnm,vkls,vkomp,vsms,vta,ids ;
        try {
            Statement st;
             ResultSet rs;
             String sql;
             sql ="SELECT * FROM tb_status_siswa WHERE id_siswa='"+vnis+"'";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                ids = rs.getString("id_status");
                vnis = rs.getString("id_siswa");
                vnm = rs.getString("nm_siswa");
                vkls = rs.getString("id_kelas");
                vkomp = rs.getString("id_kompetensi");
                vsms = rs.getString("id_semester");
                vta = rs.getString("id_ta");
                txidSts.setText(ids);
                txNmSis1.setText(vnm);
                txKelas.setText(vkls);
                txKomp.setText(vkomp);
                txSemester.setText(vsms);
                txTA.setText(vta);
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cbNISActionPerformed

    private void cbKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKelasActionPerformed
        // cb kelas
        try {
            for(int i=cbNIS.getItemCount()-1;i>0;i++){
            cbNIS.removeItemAt(i);
        }
        } catch (Exception e) {
        }
        isiComboNIS();
        AllAktif();
        Kosong();
    }//GEN-LAST:event_cbKelasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GAbsenSiswa().setVisible(true);
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
    private javax.swing.JComboBox cbKelas;
    private javax.swing.JComboBox cbNIS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbAbsen;
    private javax.swing.JTextField txAlp;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txIzn;
    private javax.swing.JTextField txKelas;
    private javax.swing.JTextField txKomp;
    private javax.swing.JTextField txNmSis1;
    private javax.swing.JTextField txSemester;
    private javax.swing.JTextField txSkt;
    private javax.swing.JTextField txTA;
    private javax.swing.JTextField txid;
    private javax.swing.JTextField txidSts;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
