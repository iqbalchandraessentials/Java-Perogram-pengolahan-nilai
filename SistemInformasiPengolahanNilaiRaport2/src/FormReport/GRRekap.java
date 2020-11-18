
package FormReport;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.koneksi2;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fidia
 */
public class GRRekap extends javax.swing.JFrame {
    private Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;
    
    public GRRekap() {
        initComponents();
        dataTable();
        isiComboKelas();
        isiComboTa();
    }
    
    protected void dataTable(){
        Object [] baris = {"id","id siswa","nama","kompetensi","kelas","semester","tahun ajar","rata-rata","keterangan"};
        tabmode = new DefaultTableModel(null, baris);
        tbRekap.setModel(tabmode);
        String sql="select*from tb_rapot";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String sid = hasil.getString("id_rapot");
                String sis = hasil.getString("id_siswa");
                String snama = hasil.getString("nm_siswa");
                String iko = hasil.getString("kompetensi");
                String kls = hasil.getString("kelas");
                String sms = hasil.getString("semester");
                String nta = hasil.getString("ta");
                String rta = hasil.getString("rata");
                String nkt = hasil.getString("ket");
                String[]data = {sid,sis,snama,iko,kls,sms,nta,rta,nkt};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }

     
    private void makePreview (String vName){
    try {
    String locFile = "src/report/";
    String namaFile = locFile + vName + ".jasper";
    Connection conn = new koneksi2().connect();
    HashMap parameter = new HashMap();
    parameter.put("pkelas", String.valueOf(cbKelas.getSelectedItem()));
    parameter.put("pta", String.valueOf(cbTa.getSelectedItem()));
    File report_file = new File (namaFile);
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
    JasperViewer.viewReport(jasperPrint, false );
    JasperViewer.setDefaultLookAndFeelDecorated(true);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
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
    
    public void isiComboTa(){
        try {
            Statement st;
            ResultSet rs;
            String sql;
            sql ="SELECT * FROM tb_tahun_ajaran";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbTa.addItem(rs.getString("ta"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btCetak = new javax.swing.JButton();
        window = new javax.swing.JPanel();
        btKel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRekap = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbKelas = new javax.swing.JComboBox();
        cbTa = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCetak.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-20.png"))); // NOI18N
        btCetak.setText("cetak ");
        btCetak.setIconTextGap(12);
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 130, 40));

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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 40));

        tbRekap.setModel(new javax.swing.table.DefaultTableModel(
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
        tbRekap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRekapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRekap);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 680, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("REKAP NILAI SISWA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        cbKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Kelas -" }));
        jPanel1.add(cbKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 90, -1));

        cbTa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Tahun Ajar -" }));
        jPanel1.add(cbTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 120, -1));

        jLabel1.setText("Tahun Ajaran");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jLabel3.setText("Kelas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
            makePreview("RRekapRapot");
    }//GEN-LAST:event_btCetakActionPerformed

    private void btKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKelActionPerformed
        // keluar
        dispose();
    }//GEN-LAST:event_btKelActionPerformed

    int x, y;
    private void windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMouseDragged
        // mouse drag
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_windowMouseDragged

    private void windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMousePressed
        // window hold
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_windowMousePressed

    private void tbRekapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRekapMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tbRekapMouseClicked

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
            java.util.logging.Logger.getLogger(GRRekap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GRRekap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GRRekap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GRRekap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GRRekap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btKel;
    private javax.swing.JComboBox cbKelas;
    private javax.swing.JComboBox cbTa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbRekap;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
