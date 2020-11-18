
package GUI_IM;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.koneksi2;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fidia
 */
public class GSiswa extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;
    String tanggal1;
    

    public GSiswa() {
        initComponents();
        dataTable();
        SaveAktif();
    }
    
    public void SaveAktif() {
        btSisHapus.setEnabled(false);
        btSisUbah.setEnabled(false);
        btSisSimpan.setEnabled(true);
        AutoNomor();
        txNmSis.requestFocus();
    }
    
    public void SaveUnAktif(){
        btSisHapus.setEnabled(true);
        btSisUbah.setEnabled(true);
        btSisSimpan.setEnabled(false);
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_siswa order by id_siswa desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String ids = rs.getString("id_siswa").substring(1);
                 String AN = "" + (Integer.parseInt(ids)+1);
                 String Nol = "";
                 
                 if (AN.length()==1) {
                     Nol ="000";
                 } if (AN.length()==2) {
                     Nol ="00";
                 }if (AN.length()==3) {
                     Nol ="0";
                 } if (AN.length()==4) {
                     Nol="";
                 }
                 txnis.setText("2"+Nol+AN);
                 
             } else {
                 txnis.setText("210001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
    
    protected void dataTable(){
        Object [] baris = {"id siswa","nama","kelahiran","tanggal","jenkel","agama","alamat","telp","ayah","ibu","pkj ayah","pkj ibu","saudara","wali","asal"};
        tabmode = new DefaultTableModel(null, baris);
        tabelSiswa.setModel(tabmode);
        String sql="select*from tb_siswa";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String sid = hasil.getString("id_siswa");
            String snama = hasil.getString("nm_siswa");
            String stl = hasil.getString("tmp_lahir");
            String sta = hasil.getString("tgl_lahir");
            String sjk = hasil.getString("jenkel");
            String sagm = hasil.getString("agama");
            String salm = hasil.getString("alamat");
            String sno = hasil.getString("no_tlp");
            String sayh = hasil.getString("nm_ayah");
            String sibu = hasil.getString("nm_ibu");
            String spa = hasil.getString("pkj_ayah");
            String sib = hasil.getString("pkj_ibu");
            String sjs = hasil.getString("jml_saudara");
            String swali = hasil.getString("nm_wali");
            String sasal = hasil.getString("asal_sekolah");
            String[]data = {sid,snama,stl,sta,sjk,sagm,salm,sno,sayh,sibu,spa,sib,sjs,swali,sasal};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tabelSiswa.getSelectedRow();
        txnis.setText(tabmode.getValueAt(bar, 0).toString());
        txNmSis.setText(tabmode.getValueAt(bar, 1).toString());
        txTmpLahir.setText(tabmode.getValueAt(bar, 2).toString());
        String date = String.valueOf(tabmode.getValueAt(bar, 3).toString());
        Date tanggal = null;
        try {
        tanggal = new SimpleDateFormat("dd MMMM yyyy").parse(date);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        txTglLahir.setDate(tanggal);
        if ("perempuan".equals(tabmode.getValueAt(bar, 4).toString())){
            radioSisP.setSelected(true);
        } else {
            radioSisL.setSelected(true);
        };
        if ("islam".equals(tabmode.getValueAt(bar, 5).toString())){
            cbSisAgama.setSelectedIndex(0);
        } if ("kristen".equals(tabmode.getValueAt(bar, 5).toString())){
            cbSisAgama.setSelectedIndex(1);
        } if ("hindu".equals(tabmode.getValueAt(bar, 5).toString())){
            cbSisAgama.setSelectedIndex(2);
        }if ("budha".equals(tabmode.getValueAt(bar, 5).toString())){
            cbSisAgama.setSelectedIndex(3);
        }if ("katolik".equals(tabmode.getValueAt(bar, 5).toString())){
            cbSisAgama.setSelectedIndex(4);
        };
        txAlamat.setText(tabmode.getValueAt(bar, 6).toString());
        txSisTlp.setText(tabmode.getValueAt(bar, 7).toString());
        txSisAyah.setText(tabmode.getValueAt(bar, 8).toString());
        txSisIbu.setText(tabmode.getValueAt(bar, 9).toString());
        txSisPAyah.setText(tabmode.getValueAt(bar, 10).toString());
        txPIbu.setText(tabmode.getValueAt(bar, 11).toString());
        txSisSaudara.setText(tabmode.getValueAt(bar, 12).toString());
        txSisWali.setText(tabmode.getValueAt(bar, 13).toString());
        txSisAsal.setText(tabmode.getValueAt(bar, 14).toString());
    }
    
    public void Kosong(){
        txNmSis.setText("");
        txTmpLahir.setText("");
        txTglLahir.setDate(null);
        GrupJenkel.clearSelection();
        cbSisAgama.setSelectedIndex(0);
        txAlamat.setText("");
        txSisTlp.setText("");
        txSisAyah.setText("");
        txSisIbu.setText("");
        txSisPAyah.setText("");
        txPIbu.setText("");
        txSisSaudara.setText("");
        txSisWali.setText("");
        txSisAsal.setText("");
    }
    
    public void Simpan(){
        String sql = "insert into tb_siswa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            if (txnis.getText().equals("")||txNmSis.getText().equals("")||
                    txTmpLahir.getText().equals("")||txTglLahir.getDate()==null||GrupJenkel.isSelected(null)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                AutoNomor();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            SimpleDateFormat Format=new SimpleDateFormat("dd MMMM yyyy");
            tanggal1 = Format.format(txTglLahir.getDate());
            stat.setString(1,txnis.getText());
            stat.setString(2,txNmSis.getText());
            stat.setString(3,txTmpLahir.getText());
            stat.setString(4,tanggal1);
            String G = "" ;
            if (radioSisL.isSelected())
                G = "laki-laki";
            else if (radioSisP.isSelected())
                    G = "perempuan";
            stat.setString(5,G);
            stat.setString(6,String.valueOf(cbSisAgama.getSelectedItem()));
            stat.setString(7,txAlamat.getText());
            stat.setString(8,txSisTlp.getText());
            stat.setString(9,txSisAyah.getText());
            stat.setString(10,txSisIbu.getText());
            stat.setString(11,txSisPAyah.getText());
            stat.setString(12,txPIbu.getText());
            stat.setString(13,txSisSaudara.getText());
            stat.setString(14,txSisWali.getText());
            stat.setString(15,txSisAsal.getText());
            
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
        String sql ="update tb_siswa set nm_siswa=?, tmp_lahir=?, tgl_lahir=?, jenkel=?, agama=?, alamat=?, no_tlp=?, nm_ayah=?, nm_ibu=?, pkj_ayah=?, pkj_ibu=?, jml_saudara=?, nm_wali=?, asal_sekolah=? where id_siswa='"+txnis.getText()+"'";
        try{
            if (txnis.getText().trim().equals("")||txNmSis.getText().trim().equals("")||
                    txTmpLahir.getText().trim().equals("")||txTglLahir.getDate()==null||GrupJenkel.isSelected(null)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                AutoNomor();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            SimpleDateFormat Format=new SimpleDateFormat("dd MMMM yyyy");
            tanggal1 = Format.format(txTglLahir.getDate());
            stat.setString(1,txNmSis.getText());
            stat.setString(2,txTmpLahir.getText());
            stat.setString(3,tanggal1);
            
            String G = "" ;
            if (radioSisL.isSelected())
                G = "laki-laki";
            else if (radioSisP.isSelected())
                    G = "perempuan";
            stat.setString(4,G);
            stat.setString(5,String.valueOf(cbSisAgama.getSelectedItem()));
            stat.setString(6,txAlamat.getText());
            stat.setString(7,txSisTlp.getText());
            stat.setString(8,txSisAyah.getText());
            stat.setString(9,txSisIbu.getText());
            stat.setString(10,txSisPAyah.getText());
            stat.setString(11,txPIbu.getText());
            stat.setString(12,txSisSaudara.getText());
            stat.setString(13,txSisWali.getText());
            stat.setString(14,txSisAsal.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            dataTable();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }
    
    public void Hapus(){
        if (txnis.getText().equals("")||txNmSis.getText().equals("")||
                    txTmpLahir.getText().equals("")||txTglLahir.getDate()==null||GrupJenkel.isSelected(null)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                AutoNomor();
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_siswa where id_siswa='"+txnis.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Data berhasil di hapus");
                Kosong();
                dataTable();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(rootPane, "Data gagal di hapus"+e);
            }
        } else {
            Kosong();
        }
        }
    } 
    
    public void Cari(){
        int row=tabelSiswa.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from tb_siswa where nm_siswa like '%"+txCari.getText()+"%' or id_siswa like '%"+txCari.getText()+"%' or jenkel like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GSiswa.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupJenkel = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        GsExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSiswa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txSisAsal = new javax.swing.JTextField();
        txnis = new javax.swing.JTextField();
        txNmSis = new javax.swing.JTextField();
        txTmpLahir = new javax.swing.JTextField();
        txAlamat = new javax.swing.JTextField();
        txSisTlp = new javax.swing.JTextField();
        txSisAyah = new javax.swing.JTextField();
        txSisIbu = new javax.swing.JTextField();
        txSisPAyah = new javax.swing.JTextField();
        txPIbu = new javax.swing.JTextField();
        txSisSaudara = new javax.swing.JTextField();
        txSisWali = new javax.swing.JTextField();
        btSisBatal = new javax.swing.JButton();
        btSisSimpan = new javax.swing.JButton();
        btSisUbah = new javax.swing.JButton();
        btSisHapus = new javax.swing.JButton();
        radioSisP = new javax.swing.JRadioButton();
        radioSisL = new javax.swing.JRadioButton();
        cbSisAgama = new javax.swing.JComboBox();
        txTglLahir = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        txCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
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

        GsExit.setBackground(new java.awt.Color(255, 0, 0));
        GsExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GsExit.setText("X");
        GsExit.setToolTipText("keluar");
        GsExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GsExit.setIconTextGap(1);
        GsExit.setMargin(new java.awt.Insets(2, 2, 2, 2));
        GsExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GsExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 907, Short.MAX_VALUE)
                .addComponent(GsExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(GsExit)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, -1));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("OLAH DATA SISWA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        tabelSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSiswa);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 510, 420));

        jLabel2.setText("Tanggal Lahir");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 90, -1));

        jLabel3.setText("id siswa");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 100, -1));

        jLabel4.setText("Jenis Kelamin");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 100, -1));

        jLabel5.setText("Tempat Lahir");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 100, -1));

        jLabel6.setText("Asal Sekolah");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, 100, -1));

        jLabel7.setText("Nama");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 100, -1));

        jLabel8.setText("Agama");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 100, -1));

        jLabel9.setText("Alamat");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 100, -1));

        jLabel10.setText("No. Telp");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 100, -1));

        jLabel11.setText("Nama Ibu");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 100, -1));

        jLabel12.setText("Nama Ayah");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 100, -1));

        jLabel13.setText("Pekerjaan Ayah");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 100, -1));

        jLabel14.setText("Pekerjaan Ibu");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 100, -1));

        jLabel15.setText("Jumlah Saudara");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 100, -1));

        jLabel16.setText("Nama Wali");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 100, -1));
        jPanel1.add(txSisAsal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 250, -1));
        jPanel1.add(txnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 250, -1));
        jPanel1.add(txNmSis, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 250, -1));
        jPanel1.add(txTmpLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 250, -1));
        jPanel1.add(txAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 250, -1));
        jPanel1.add(txSisTlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 250, -1));
        jPanel1.add(txSisAyah, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 250, -1));
        jPanel1.add(txSisIbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 250, -1));
        jPanel1.add(txSisPAyah, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 250, -1));
        jPanel1.add(txPIbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 250, -1));
        jPanel1.add(txSisSaudara, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 250, -1));
        jPanel1.add(txSisWali, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 390, 250, -1));

        btSisBatal.setText("Batal");
        btSisBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSisBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btSisBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, 70, -1));

        btSisSimpan.setText("Simpan");
        btSisSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSisSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSisSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 490, -1, -1));

        btSisUbah.setText("Ubah");
        btSisUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSisUbahActionPerformed(evt);
            }
        });
        jPanel1.add(btSisUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 70, -1));

        btSisHapus.setText("Hapus");
        btSisHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSisHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btSisHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 490, 70, -1));

        radioSisP.setBackground(new java.awt.Color(204, 204, 255));
        GrupJenkel.add(radioSisP);
        radioSisP.setText("Perempuan");
        jPanel1.add(radioSisP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 210, -1, 20));

        radioSisL.setBackground(new java.awt.Color(204, 204, 255));
        GrupJenkel.add(radioSisL);
        radioSisL.setText("Laki-Laki");
        jPanel1.add(radioSisL, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, 20));

        cbSisAgama.setBackground(new java.awt.Color(204, 204, 255));
        cbSisAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Hindu", "Budha", "Katolik" }));
        jPanel1.add(cbSisAgama, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 110, 20));

        txTglLahir.setDateFormatString("dd MMMM yyyy");
        jPanel1.add(txTglLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 160, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 380, 350));

        txCari.setBackground(new java.awt.Color(204, 204, 255));
        txCari.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txCari.setToolTipText("cari data");
        txCari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 180, 20));

        btCari.setText("cari");
        btCari.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 50, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        setSize(new java.awt.Dimension(950, 605));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GsExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GsExitActionPerformed
        // Keluar
        dispose();
    }//GEN-LAST:event_GsExitActionPerformed

    private void btSisBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSisBatalActionPerformed
        // Batal
        Kosong();
        SaveAktif();
    }//GEN-LAST:event_btSisBatalActionPerformed

    private void btSisSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSisSimpanActionPerformed
        // Simpan
        Simpan();
        SaveAktif();
    }//GEN-LAST:event_btSisSimpanActionPerformed

    private void btSisUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSisUbahActionPerformed
        // Ubah
        Ubah();
        SaveAktif();
    }//GEN-LAST:event_btSisUbahActionPerformed

    private void btSisHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSisHapusActionPerformed
        // Hapus
        Hapus();
        SaveAktif();
    }//GEN-LAST:event_btSisHapusActionPerformed

    private void tabelSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSiswaMouseClicked
        // Tabel Klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tabelSiswaMouseClicked

    int x, y;
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // window
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // Mouse drag
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        // bt cari
        Cari();
    }//GEN-LAST:event_btCariActionPerformed

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
        // cari enter
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
            Cari();
        }
    }//GEN-LAST:event_txCariKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupJenkel;
    private javax.swing.JButton GsExit;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btSisBatal;
    private javax.swing.JButton btSisHapus;
    private javax.swing.JButton btSisSimpan;
    private javax.swing.JButton btSisUbah;
    private javax.swing.JComboBox cbSisAgama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioSisL;
    private javax.swing.JRadioButton radioSisP;
    private javax.swing.JTable tabelSiswa;
    private javax.swing.JTextField txAlamat;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txNmSis;
    private javax.swing.JTextField txPIbu;
    private javax.swing.JTextField txSisAsal;
    private javax.swing.JTextField txSisAyah;
    private javax.swing.JTextField txSisIbu;
    private javax.swing.JTextField txSisPAyah;
    private javax.swing.JTextField txSisSaudara;
    private javax.swing.JTextField txSisTlp;
    private javax.swing.JTextField txSisWali;
    private com.toedter.calendar.JDateChooser txTglLahir;
    private javax.swing.JTextField txTmpLahir;
    private javax.swing.JTextField txnis;
    // End of variables declaration//GEN-END:variables
}
