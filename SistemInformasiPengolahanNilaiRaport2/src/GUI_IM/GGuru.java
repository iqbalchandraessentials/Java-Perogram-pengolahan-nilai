
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
public class GGuru extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;
    String tanggal1;
    

    public GGuru() {
        initComponents();
        dataTable();
        AutoNomor();
        txNmGur.requestFocus();
        SaveAktif();
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_guru order by id_guru desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String idg = rs.getString("id_guru").substring(1);
                 String AN = "" + (Integer.parseInt(idg)+1);
                 String Nol = "";
                 
                if (AN.length()==2) {
                     Nol ="00";
                 }if (AN.length()==3) {
                     Nol ="0";
                 } if (AN.length()==4) {
                     Nol="";
                 }
                 txnip.setText("5"+Nol+AN);
                 
             } else {
                 txnip.setText("51001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    protected void dataTable(){
        Object [] baris = {"id_guru","nama","kelahiran","tanggal","jenkel","agama","alamat","telp","pendidikan","perguruan","jabatan"};
        tabmode = new DefaultTableModel(null, baris);
        tabelGuru.setModel(tabmode);
        String sql="select*from tb_guru";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String gid = hasil.getString("id_guru");
            String gnama = hasil.getString("nm_guru");
            String gtl = hasil.getString("tmp_lahir");
            String gta = hasil.getString("tgl_lahir");
            String gjk = hasil.getString("jenkel");
            String gagm = hasil.getString("agama");
            String galm = hasil.getString("alamat");
            String gno = hasil.getString("no_telp");
            String pnd = hasil.getString("pend_terakhir");
            String pgr = hasil.getString("perguruan");
            String jbt = hasil.getString("jabatan");
            String[]data = {gid,gnama,gtl,gta,gjk,gagm,galm,gno,pnd,pgr,jbt};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tabelGuru.getSelectedRow();
        txnip.setText(tabmode.getValueAt(bar, 0).toString());
        txNmGur.setText(tabmode.getValueAt(bar, 1).toString());
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
            radioGurP.setSelected(true);
        } else {
            radioGurL.setSelected(true);
        };
        if ("islam".equals(tabmode.getValueAt(bar, 5).toString())){
            cbGurAgama.setSelectedIndex(0);
        } if ("kristen".equals(tabmode.getValueAt(bar, 5).toString())){
            cbGurAgama.setSelectedIndex(1);
        } if ("hindu".equals(tabmode.getValueAt(bar, 5).toString())){
            cbGurAgama.setSelectedIndex(2);
        }if ("budha".equals(tabmode.getValueAt(bar, 5).toString())){
            cbGurAgama.setSelectedIndex(3);
        }if ("katolik".equals(tabmode.getValueAt(bar, 5).toString())){
            cbGurAgama.setSelectedIndex(4);
        };
        txAlamatGur.setText(tabmode.getValueAt(bar, 6).toString());
        txGurTlp.setText(tabmode.getValueAt(bar, 7).toString());
        txPenTer.setText(tabmode.getValueAt(bar, 8).toString());
        txPer.setText(tabmode.getValueAt(bar, 9).toString());
        txJbt.setText(tabmode.getValueAt(bar, 10).toString());
    }
    
    public void Kosong(){
        AutoNomor();
        txNmGur.setText("");
        txTmpLahir.setText("");
        txTglLahir.setDate(null);
        GrupJenkel.clearSelection();
        cbGurAgama.setSelectedIndex(0);
        txAlamatGur.setText("");
        txGurTlp.setText("");
        txPenTer.setText("");
        txPer.setText("");
        txJbt.setText("");
        txNmGur.requestFocus();
    }
    
    public void Simpan(){
        String sql = "insert into tb_guru values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            if (txnip.getText().equals("")||txNmGur.getText().equals("")||
                    txTmpLahir.getText().equals("")||txTglLahir.getDate()==null||
                    GrupJenkel.isSelected(null)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!"); 
                txNmGur.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            SimpleDateFormat Format=new SimpleDateFormat("dd MMMM yyyy");
            tanggal1 = Format.format(txTglLahir.getDate());
            stat.setString(1,txnip.getText());
            stat.setString(2,txNmGur.getText());
            stat.setString(3,txTmpLahir.getText());
            stat.setString(4,tanggal1);
            
            String G = "" ;
            if (radioGurL.isSelected())
                G = "laki-laki";
            else if (radioGurP.isSelected())
                    G = "perempuan";
            stat.setString(5,G);
            stat.setString(6,String.valueOf(cbGurAgama.getSelectedItem()));
            stat.setString(7,txAlamatGur.getText());
            stat.setString(8,txGurTlp.getText());
            stat.setString(9,txPenTer.getText());
            stat.setString(10,txPer.getText());
            stat.setString(11,txJbt.getText());
            
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
            String sql ="update tb_guru set nm_guru=?, tmp_lahir=?, tgl_lahir=?, "
                    + "jenkel=?, agama=?, alamat=?, no_telp=?, pend_terakhir=?, "
                    + "perguruan=?, jabatan=? where id_guru='"+txnip.getText()+"'";
            if (txnip.getText().equals("")||txNmGur.getText().equals("")||
                    txTmpLahir.getText().equals("")||txTglLahir.getDate()==null||
                    GrupJenkel.isSelected(null)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txNmGur.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            SimpleDateFormat Format=new SimpleDateFormat("dd MMMM yyyy");
            tanggal1 = Format.format(txTglLahir.getDate());
            stat.setString(1,txNmGur.getText());
            stat.setString(2,txTmpLahir.getText());
            stat.setString(3,tanggal1);
            
            String G = "" ;
            if (radioGurL.isSelected())
                G = "laki-laki";
            else if (radioGurP.isSelected())
                    G = "perempuan";
            stat.setString(4,G);
            stat.setString(5,String.valueOf(cbGurAgama.getSelectedItem()));
            stat.setString(6,txAlamatGur.getText());
            stat.setString(7,txGurTlp.getText());
            stat.setString(8,txPenTer.getText());
            stat.setString(9,txPer.getText());
            stat.setString(10,txJbt.getText());
            
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
        if (txnip.getText().equals("")||txNmGur.getText().equals("")||
                txTmpLahir.getText().equals("")||
                txTglLahir.getDate()==null||GrupJenkel.isSelected(null)) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txNmGur.requestFocus();
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin "
                    + "ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_guru where id_guru='"+txnip.getText()+"'";
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
        int row=tabelGuru.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "tb_guru where nm_guru like '%"+tfCariGur.getText()+"%' or "
                    + "id_guru like '%"+tfCariGur.getText()+"%' or "
                    + "jabatan like '%"+tfCariGur.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6),
                    rs.getString(7),rs.getString(8),rs.getString(9),
                    rs.getString(10),rs.getString(11)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GGuru.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }
    
    public void SaveAktif() {
        btHapusGur.setEnabled(false);
        btUbahGur.setEnabled(false);
        btSisSimpanGur.setEnabled(true);
    }
    
    public void SaveUnAktif(){
        btHapusGur.setEnabled(true);
        btUbahGur.setEnabled(true);
        btSisSimpanGur.setEnabled(false);
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
        tabelGuru = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txnip = new javax.swing.JTextField();
        txNmGur = new javax.swing.JTextField();
        txTmpLahir = new javax.swing.JTextField();
        txAlamatGur = new javax.swing.JTextField();
        txGurTlp = new javax.swing.JTextField();
        txPenTer = new javax.swing.JTextField();
        txPer = new javax.swing.JTextField();
        txJbt = new javax.swing.JTextField();
        btBatalGur = new javax.swing.JButton();
        btSisSimpanGur = new javax.swing.JButton();
        btUbahGur = new javax.swing.JButton();
        btHapusGur = new javax.swing.JButton();
        radioGurP = new javax.swing.JRadioButton();
        radioGurL = new javax.swing.JRadioButton();
        cbGurAgama = new javax.swing.JComboBox();
        txTglLahir = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        tfCariGur = new javax.swing.JTextField();
        btCariGur = new javax.swing.JButton();

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
        jLabel1.setText("OLAH DATA GURU");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        tabelGuru.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelGuruMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelGuru);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 510, 420));

        jLabel2.setText("Tanggal Lahir");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 90, -1));

        jLabel3.setText("id guru");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 100, -1));

        jLabel4.setText("Jenis Kelamin");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 100, -1));

        jLabel5.setText("Tempat Lahir");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 100, -1));

        jLabel7.setText("Nama");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 100, -1));

        jLabel8.setText("Agama");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 100, -1));

        jLabel9.setText("Alamat");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 100, -1));

        jLabel10.setText("No. Telp");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 100, -1));

        jLabel11.setText("Perguruan");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 100, -1));

        jLabel12.setText("Pen. Terakhir");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 100, -1));

        jLabel13.setText("Jabatan");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 100, -1));
        jPanel1.add(txnip, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 250, -1));
        jPanel1.add(txNmGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 250, -1));
        jPanel1.add(txTmpLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 250, -1));
        jPanel1.add(txAlamatGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 250, -1));
        jPanel1.add(txGurTlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 250, -1));
        jPanel1.add(txPenTer, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 250, -1));
        jPanel1.add(txPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 250, -1));
        jPanel1.add(txJbt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 250, -1));

        btBatalGur.setText("Batal");
        btBatalGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalGurActionPerformed(evt);
            }
        });
        jPanel1.add(btBatalGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 70, -1));

        btSisSimpanGur.setText("Simpan");
        btSisSimpanGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSisSimpanGurActionPerformed(evt);
            }
        });
        jPanel1.add(btSisSimpanGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 430, -1, -1));

        btUbahGur.setText("Ubah");
        btUbahGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahGurActionPerformed(evt);
            }
        });
        jPanel1.add(btUbahGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 430, 70, -1));

        btHapusGur.setText("Hapus");
        btHapusGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusGurActionPerformed(evt);
            }
        });
        jPanel1.add(btHapusGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, 70, -1));

        radioGurP.setBackground(new java.awt.Color(204, 204, 255));
        GrupJenkel.add(radioGurP);
        radioGurP.setText("Perempuan");
        jPanel1.add(radioGurP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 210, -1, 20));

        radioGurL.setBackground(new java.awt.Color(204, 204, 255));
        GrupJenkel.add(radioGurL);
        radioGurL.setText("Laki-Laki");
        jPanel1.add(radioGurL, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, 20));

        cbGurAgama.setBackground(new java.awt.Color(204, 204, 255));
        cbGurAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Hindu", "Budha", "Katolik" }));
        jPanel1.add(cbGurAgama, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 110, 20));

        txTglLahir.setDateFormatString("dd MMMM yyyy");
        jPanel1.add(txTglLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 160, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 380, 290));

        tfCariGur.setBackground(new java.awt.Color(204, 204, 255));
        tfCariGur.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfCariGur.setToolTipText("cari data");
        tfCariGur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        tfCariGur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfCariGurKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariGurKeyReleased(evt);
            }
        });
        jPanel1.add(tfCariGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 180, 20));

        btCariGur.setText("cari");
        btCariGur.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btCariGur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariGurActionPerformed(evt);
            }
        });
        jPanel1.add(btCariGur, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 50, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        setSize(new java.awt.Dimension(950, 605));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GsExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GsExitActionPerformed
        // Keluar
        dispose();
    }//GEN-LAST:event_GsExitActionPerformed

    private void btBatalGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalGurActionPerformed
        // Batal
        Kosong();
        SaveAktif();
    }//GEN-LAST:event_btBatalGurActionPerformed

    private void btSisSimpanGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSisSimpanGurActionPerformed
        // Simpan
        Simpan();
        SaveAktif();
    }//GEN-LAST:event_btSisSimpanGurActionPerformed

    private void btUbahGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahGurActionPerformed
        // Ubah
        Ubah();
        SaveAktif();
        
    }//GEN-LAST:event_btUbahGurActionPerformed

    private void btHapusGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusGurActionPerformed
        // Hapus
        Hapus();
        SaveAktif();
    }//GEN-LAST:event_btHapusGurActionPerformed

    private void tabelGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelGuruMouseClicked
        // Tabel Klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tabelGuruMouseClicked

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

    private void btCariGurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariGurActionPerformed
        // cari
        Cari();
    }//GEN-LAST:event_btCariGurActionPerformed

    private void tfCariGurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariGurKeyReleased
        // cari releas
//        Cari();
    }//GEN-LAST:event_tfCariGurKeyReleased

    private void tfCariGurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariGurKeyPressed
        // cari enter
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
            Cari();
        }
    }//GEN-LAST:event_tfCariGurKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GGuru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupJenkel;
    private javax.swing.JButton GsExit;
    private javax.swing.JButton btBatalGur;
    private javax.swing.JButton btCariGur;
    private javax.swing.JButton btHapusGur;
    private javax.swing.JButton btSisSimpanGur;
    private javax.swing.JButton btUbahGur;
    private javax.swing.JComboBox cbGurAgama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioGurL;
    private javax.swing.JRadioButton radioGurP;
    private javax.swing.JTable tabelGuru;
    private javax.swing.JTextField tfCariGur;
    private javax.swing.JTextField txAlamatGur;
    private javax.swing.JTextField txGurTlp;
    private javax.swing.JTextField txJbt;
    private javax.swing.JTextField txNmGur;
    private javax.swing.JTextField txPenTer;
    private javax.swing.JTextField txPer;
    private com.toedter.calendar.JDateChooser txTglLahir;
    private javax.swing.JTextField txTmpLahir;
    private javax.swing.JTextField txnip;
    // End of variables declaration//GEN-END:variables
}
