
package FormReport;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class GRGuru extends javax.swing.JFrame {
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;
    
    public GRGuru() {
        initComponents();
        dataTable();
    }
    
     protected void dataTable(){
        Object [] baris = {"id Guru","Nama","Jenkel","Alamat","Telp"};
        tabmode = new DefaultTableModel(null, baris);
        tbGuruL.setModel(tabmode);
        String sql="select*from tb_guru";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String sid = hasil.getString("id_guru");
            String snama = hasil.getString("nm_guru");
            String sjk = hasil.getString("jenkel");
            String salm = hasil.getString("alamat");
            String sno = hasil.getString("no_telp");
            String[]data = {sid,snama,sjk,salm,sno};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    public void TabelKlik(){
        int bar = tbGuruL.getSelectedRow();
        txid.setText(tabmode.getValueAt(bar, 0).toString());
    }
     
    private void makePreview (String vName){
        try {
            String KopLaporan = getClass().getResource("/img/kop smk im_1.jpg").toString();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            parameter.put("idguru", String.valueOf(txid.getText()));
            File report_file = new File (namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false );
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e);
        }

}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btCetak = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        window = new javax.swing.JPanel();
        btKel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGuruL = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-20.png"))); // NOI18N
        btCetak.setText("cetak ");
        btCetak.setIconTextGap(8);
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 110, 30));

        jLabel1.setText("cetak berdasarkan id");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txidActionPerformed(evt);
            }
        });
        jPanel1.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 100, -1));

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
        window.add(btKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 45, -1));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 40));

        tbGuruL.setModel(new javax.swing.table.DefaultTableModel(
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
        tbGuruL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGuruLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbGuruL);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 490, 220));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("DATA GURU");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txidActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
        
        if (txid.getText().equals("")) {
            makePreview("RGuru");
        } else {
            makePreview("RBioGuru");
        }
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

    private void tbGuruLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGuruLMouseClicked
        // TODO add your handling code here:
        TabelKlik();
    }//GEN-LAST:event_tbGuruLMouseClicked

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
            java.util.logging.Logger.getLogger(GRGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GRGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GRGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GRGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GRGuru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btKel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbGuruL;
    private javax.swing.JTextField txid;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
}
