
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
public class GPengembangan extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;


    public GPengembangan() {
        initComponents();
        dataTable();
        isiComboKelas();
        AutoNomor();
        AllUnAktif();
        
    }
    
    protected void dataTable(){
        Object [] baris = {"id","id status","id siswa","nama siswa","Osis","Pramuka","PMR"};
        tabmode = new DefaultTableModel(null, baris);
        tbPengemb.setModel(tabmode);
        String sql="select*from tb_pengembangan";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String idp = hasil.getString("id_pengembangan");
            String ids = hasil.getString("id_status");
            String idss = hasil.getString("id_siswa");
            String ns = hasil.getString("nm_siswa");
            String tos = hasil.getString("osis");
            String tpra = hasil.getString("pramuka");
            String tpmr = hasil.getString("pmr");
           
            String[]data = {idp,ids,idss,ns,tos,tpra,tpmr};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tbPengemb.getSelectedRow();
        txid.setText(tabmode.getValueAt(bar, 0).toString());
        txidSts.setText(tabmode.getValueAt(bar, 1).toString());
        String vnis = String.valueOf(tabmode.getValueAt(bar, 2).toString());
        cbNIS.setSelectedItem(vnis);
        txNmSis.setText(tabmode.getValueAt(bar, 3).toString());
        String vos = String.valueOf(tabmode.getValueAt(bar, 4).toString());
        cbNOsis.setSelectedItem(vos); 
        String vpra = String.valueOf(tabmode.getValueAt(bar, 5).toString());
        cbNPramuka.setSelectedItem(vpra); 
        String vpmr = String.valueOf(tabmode.getValueAt(bar, 6).toString());
        cbNPMR.setSelectedItem(vpmr);
        btSimpan.setEnabled(false);
    }
    
    public void Kosong(){
        txidSts.setText("");
        cbNIS.setSelectedIndex(0);
        txNmSis.setText("");
        txKelas.setText("");
        txKomp.setText("");
        txSemester.setText("");
        txTA.setText("");
        cbNOsis.setSelectedIndex(0);
        cbNPramuka.setSelectedIndex(0);
        cbNPMR.setSelectedIndex(0);
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_pengembangan order by id_pengembangan desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String nis = rs.getString("id_pengembangan").substring(2);
                 String AN = "" + (Integer.parseInt(nis)+1);
                 String Nol = "";
                 
                if (AN.length()==2) {
                     Nol ="00";
                 }if (AN.length()==3) {
                     Nol ="0";
                 } if (AN.length()==4) {
                     Nol="";
                 }
                 txid.setText("PG"+Nol+AN);
                 
             } else {
                 txid.setText("PG10001");
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
             sql ="SELECT * FROM tb_status_siswa where "
                     + "id_kelas='"+String.valueOf(cbKelas.getSelectedItem())+"'";
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
    
    public void AllUnAktif(){
        cbNIS.setEnabled(false);
        btBatal.setEnabled(false);
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(false);
        tbPengemb.setVisible(false);
        cbKelas.requestFocus();
    }
    
    public void AllAktif(){
        cbNIS.setEnabled(true);
        btBatal.setEnabled(true);
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(true);
        tbPengemb.setVisible(true);
        cbNIS.requestFocus();
    }
    
    public void Simpan(){
        String sql = "insert into tb_pengembangan values (?,?,?,?,?,?,?)";
        try {
            if (txidSts.getText().equals("")||txNmSis.getText().equals("")
                    ||cbNOsis.getInputMap().equals(0)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txid.getText());
            stat.setString(2,txidSts.getText());
            stat.setString(3,String.valueOf(cbNIS.getSelectedItem()));
            stat.setString(4,txNmSis.getText());
            stat.setString(5,String.valueOf(cbNOsis.getSelectedItem()));
            stat.setString(6,String.valueOf(cbNPramuka.getSelectedItem()));
            stat.setString(7,String.valueOf(cbNPMR.getSelectedItem()));
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
            String sql ="update tb_pengembangan set id_status=?, id_siswa=?, "
                    + "nm_siswa=?, osis=?, pramuka=?, pmr=? where "
                    + "id_pengembangan='"+txid.getText()+"'";
            if (txidSts.getText().equals("")||txNmSis.getText().equals("")
                    ||cbNOsis.getInputMap().equals(0)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
             stat.setString(1,txidSts.getText());
            stat.setString(2,String.valueOf(cbNIS.getSelectedItem()));
            stat.setString(3,txNmSis.getText());
            stat.setString(4,String.valueOf(cbNOsis.getSelectedItem()));
            stat.setString(5,String.valueOf(cbNPramuka.getSelectedItem()));
            stat.setString(6,String.valueOf(cbNPMR.getSelectedItem()));
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            btSimpan.setEnabled(true);
            dataTable();
            stat.close();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }
    
    public void Hapus(){
        if (txidSts.getText().equals("")||txNmSis.getText().equals("")
                ||cbNOsis.getInputMap().equals(0)) { 
            JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
            return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin "
                    + "menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_pengembangan where id_pengembangan='"+txid.getText()+"'";
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
        int row=tbPengemb.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "tb_pengembangan where nm_siswa like '%"+txCari.getText()+ 
                    "%' or id_siswa like '%"+txCari.getText()+"%' "
                    + "or id_status like '%"+txCari.getText()+"%' "
                    + "or id_pengembangan like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3)
                        ,rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GPengembangan.class.getName()).log(Level.SEVERE, null, ex);  
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbNIS = new javax.swing.JComboBox();
        txNmSis = new javax.swing.JTextField();
        txKelas = new javax.swing.JTextField();
        txKomp = new javax.swing.JTextField();
        txSemester = new javax.swing.JTextField();
        txTA = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbNPMR = new javax.swing.JComboBox();
        cbNOsis = new javax.swing.JComboBox();
        cbNPramuka = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txidSts = new javax.swing.JTextField();
        txid = new javax.swing.JTextField();
        cbKelas = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPengemb = new javax.swing.JTable();
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
        jLabel1.setText("Kelola Data Pengembangan Siswa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("id");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Nama");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel4.setText("Kelas");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel5.setText("PMR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel6.setText("Kompetensi");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel7.setText("Semester");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        cbNIS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-id siswa-" }));
        cbNIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNISActionPerformed(evt);
            }
        });
        jPanel2.add(cbNIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 100, -1));

        txNmSis.setEditable(false);
        jPanel2.add(txNmSis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 180, -1));

        txKelas.setEditable(false);
        jPanel2.add(txKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 180, -1));

        txKomp.setEditable(false);
        jPanel2.add(txKomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 180, -1));

        txSemester.setEditable(false);
        jPanel2.add(txSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 180, -1));

        txTA.setEditable(false);
        jPanel2.add(txTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 180, -1));

        jLabel8.setText("Tahun Ajaran");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel9.setText("Osis");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel10.setText("Pramuka");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        cbNPMR.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sangat Baik", "Baik", "Cukup", "Kurang" }));
        jPanel2.add(cbNPMR, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 120, -1));

        cbNOsis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sangat Baik", "Baik", "Cukup", "Kurang" }));
        jPanel2.add(cbNOsis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 120, -1));

        cbNPramuka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sangat Baik", "Baik", "Cukup", "Kurang" }));
        jPanel2.add(cbNPramuka, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 120, -1));

        jLabel12.setText("id siswa");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel13.setText("id status");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txidSts.setEditable(false);
        jPanel2.add(txidSts, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 100, -1));

        txid.setEditable(false);
        jPanel2.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 100, -1));

        cbKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih kelas -" }));
        cbKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbKelasMouseClicked(evt);
            }
        });
        cbKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKelasActionPerformed(evt);
            }
        });
        jPanel2.add(cbKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 70, -1));

        jLabel15.setText("Kelas");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 320, 380));

        tbPengemb.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPengemb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengembMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPengemb);

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
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 500, -1, -1));

        btSimpan.setText("Simpan");
        btSimpan.setToolTipText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, -1, -1));

        btHapus.setText("Hapus");
        btHapus.setToolTipText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, -1, -1));

        btUbah1.setText("Ubah");
        btUbah1.setToolTipText("Ubah");
        btUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btUbah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 550));

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

    private void tbPengembMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengembMouseClicked
        // tabel klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tbPengembMouseClicked

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
        String vnis = String.valueOf(cbNIS.getSelectedItem());
        String ids,vnm,vkls,vkomp,vsms,vta ;
        try {
            Statement st;
            ResultSet rs;
            String sql ="SELECT * FROM tb_status_siswa where id_siswa='"+vnis+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                ids = rs.getString("id_status");
                vnm = rs.getString("nm_siswa");
                vkls = rs.getString("id_kelas");
                vkomp = rs.getString("id_kompetensi");
                vsms = rs.getString("id_semester");
                vta = rs.getString("id_ta");
                txidSts.setText(ids);
                txNmSis.setText(vnm);
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

    private void cbKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbKelasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKelasMouseClicked

    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GPengembangan().setVisible(true);
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
    private javax.swing.JComboBox cbNOsis;
    private javax.swing.JComboBox cbNPMR;
    private javax.swing.JComboBox cbNPramuka;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTable tbPengemb;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txKelas;
    private javax.swing.JTextField txKomp;
    private javax.swing.JTextField txNmSis;
    private javax.swing.JTextField txSemester;
    private javax.swing.JTextField txTA;
    private javax.swing.JTextField txid;
    private javax.swing.JTextField txidSts;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
