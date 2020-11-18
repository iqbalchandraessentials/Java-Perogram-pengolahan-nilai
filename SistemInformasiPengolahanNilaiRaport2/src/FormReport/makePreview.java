/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormReport;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
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
public class makePreview {
    public void makePreview (String vName){
        try {
            String KopLaporan = getClass().getResource("/img/kop smk im_1.jpg").toString();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new koneksi2().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            File report_file = new File (namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false );
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
