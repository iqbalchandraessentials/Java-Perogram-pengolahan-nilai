
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
public class GRekap extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;


    public GRekap() {
        initComponents();
        IsiCbKelas();
        IsiCbTa();
    }
    
    public void IsiCbKelas(){
        try {
            Statement st;
            ResultSet rs;
            String sql;
            sql ="SELECT * FROM tb_kelas";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbkelas.addItem(rs.getString("kelas"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void IsiCbTa(){
        try {
            Statement st;
            ResultSet rs;
            String sql;
            sql ="SELECT * FROM tb_tahun_ajaran";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbta.addItem(rs.getString("ta"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    private void makePreview (String vName){
        String vkls, vtta;
        vkls = (String)cbkelas.getSelectedItem();
        vtta = (String)cbta.getSelectedItem();
    
        try {
            String KopLaporan = getClass().getResource("/img/kop smk im_1.jpg").toString();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new koneksi2().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            parameter.put("pkelas", vkls);
            parameter.put("pta", vtta);
            File report_file = new File (namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false );
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        window = new javax.swing.JPanel();
        btKel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btcetak = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbta = new javax.swing.JComboBox();
        cbkelas = new javax.swing.JComboBox();

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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 40));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        jLabel1.setText("Cetak Rekap Nilai Siswa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        btcetak.setText("Cetak");
        btcetak.setToolTipText("Simpan");
        btcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcetakActionPerformed(evt);
            }
        });
        jPanel1.add(btcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jLabel2.setText("Tahun Ajaran");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel3.setText("Kelas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        cbta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih -" }));
        jPanel1.add(cbta, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 120, -1));

        cbkelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih -" }));
        jPanel1.add(cbkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 220));

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

    private void btcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcetakActionPerformed
        // Cetak
        if (cbkelas.getSelectedItem().equals("- pilih -") || cbta.getSelectedItem().equals("- pilih -")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih kelas dan tahun ajaran terlebih dahulu!");
        } else {
        makePreview("RRekapRapot");
        }
    }//GEN-LAST:event_btcetakActionPerformed

    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GRekap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btKel;
    private javax.swing.JButton btcetak;
    private javax.swing.JComboBox cbkelas;
    private javax.swing.JComboBox cbta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
