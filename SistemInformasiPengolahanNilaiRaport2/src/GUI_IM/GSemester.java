
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
public class GSemester extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;


    public GSemester() {
        initComponents();
        dataTable();
        AutoNomor();
        SaveAktif();
    }
    
    protected void dataTable(){
        Object [] baris = {"id Semester","Semester"};
        tabmode = new DefaultTableModel(null, baris);
        tbSms.setModel(tabmode);
        String sql="select*from tb_semester";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_semester");
            String sm = hasil.getString("semester");
           
            String[]data = {id,sm};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        int bar = tbSms.getSelectedRow();
        txidSms.setText(tabmode.getValueAt(bar, 0).toString());
        txNmSms.setText(tabmode.getValueAt(bar, 1).toString());
    }
    
    public void Kosong(){
        txidSms.setText("");
        txNmSms.setText("");
        AutoNomor();
        txNmSms.requestFocus();
    }
    
    public void AutoNomor(){
         try{
             Statement st;
             ResultSet rs;
             String sql;
             
             sql ="SELECT * FROM tb_semester order by id_semester desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String kd_ta = rs.getString("id_semester").substring(0);
                 String AN = "" + (Integer.parseInt(kd_ta)+1);
                 txidSms.setText(AN);
             } else {
                 txidSms.setText("1");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
    
    public void SaveAktif() {
        btHapus.setEnabled(false);
        btUbah1.setEnabled(false);
        btSimpan.setEnabled(true);
        txNmSms.requestFocus();
    }
    
    public void SaveUnAktif(){
        btHapus.setEnabled(true);
        btUbah1.setEnabled(true);
        btSimpan.setEnabled(false);
    }
    
    public void Simpan(){
        String sql = "insert into tb_semester values (?,?)";
        try {
            if (txidSms.getText().equals("")||txNmSms.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!"); 
                txNmSms.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txidSms.getText());
            stat.setString(2,txNmSms.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            Kosong();
            dataTable();
            AutoNomor();
            } 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
        }
    }
    
    public void Ubah(){
        try{
            String sql ="update tb_semester set semester=? where id_semester='"+txidSms.getText()+"'";
            if (txidSms.getText().equals("")||txNmSms.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txNmSms.requestFocus();
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txNmSms.getText());
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
        if (txidSms.getText().equals("")||txNmSms.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                txNmSms.requestFocus();
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_semester where id_semester='"+txidSms.getText()+"'";
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
        int row=tbSms.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from tb_semester where semester like '%"+txCari.getText()+"%' or id_semester like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GSemester.class.getName()).log(Level.SEVERE, null, ex);  
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
        txidSms = new javax.swing.JTextField();
        txNmSms = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSms = new javax.swing.JTable();
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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 40));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Kelola Data Semester");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "input data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("id semester");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 30, -1, -1));

        jLabel3.setText("Semester");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 61, -1, -1));
        jPanel2.add(txidSms, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 27, 167, -1));
        jPanel2.add(txNmSms, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 58, 166, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 280, 140));

        tbSms.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSmsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSms);

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

        btBatal.setText("Batal");
        btBatal.setToolTipText("Batal");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, -1));

        btSimpan.setText("Simpan");
        btSimpan.setToolTipText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, -1, -1));

        btHapus.setText("Hapus");
        btHapus.setToolTipText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, -1, -1));

        btUbah1.setText("Ubah");
        btUbah1.setToolTipText("Ubah");
        btUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btUbah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 410));

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

    private void tbSmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSmsMouseClicked
        // tabel klik
        TabelKlik();
        SaveUnAktif();
    }//GEN-LAST:event_tbSmsMouseClicked

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GSemester().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSms;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txNmSms;
    private javax.swing.JTextField txidSms;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
