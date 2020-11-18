
package FormReport;

import GUI_IM.GRevRapot;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GRRaport extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    private DefaultTableModel tabmode;
    String tanggal1; 
    
    
    public GRRaport() {
        initComponents();
        dataTable2();
        dataTable();
        isicbTahun();
        btCetak.setEnabled(false);
        btProses.setEnabled(false);
        txCari.requestFocus();
        btCari.setEnabled(false);
    }
    
    private void makePreview (String vName){
       
    try {
        String KopLaporan = getClass().getResource("/img/kop smk im_1.jpg").toString();
        Statement st, st2;
        ResultSet rspai,rspkn,rsbind,rspenjas,rsbing, rsmtk, rsipa, rsips, rspspj, 
            rskkpi, rskwu, rsfsk, rskma, rsbio, rsbsun, rsplh, rsta;
        String vpai = null, vpkn = null, vbind = null, vpenjas = null, vpspj=null,
            vbing = null, vmtk = null, vipa = null, vips = null, 
            vkkpi = null, vkwu = null, vfsk = null, vkma = null, 
            vbio = null, vbsun = null, vplh = null, vta =null;
        st=conn.createStatement();
            rsta=st.executeQuery("SELECT ta FROM tb_rapot WHERE "
                    + " ta='"+cbTA.getSelectedItem()+"'");
            while(rsta.next()){
                vta = String.valueOf(rsta.getString("ta"));
            }
            rspai=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='PAI'");
            while(rspai.next()){
                vpai = String.valueOf(rspai.getString("kkm"));
            }
            rspkn=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='PKN'");
            while(rspkn.next()){
                vpkn = String.valueOf(rspkn.getString("kkm"));
            }
            rsbind=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='BIND'");
            while(rsbind.next()){
                vbind = String.valueOf(rsbind.getString("kkm"));
            }
            rspenjas=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='PJS'");
            while(rspenjas.next()){
                vpenjas = String.valueOf(rspenjas.getString("kkm"));
            }
            rsbing=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='BING'");
            while(rsbing.next()){
                vbing = String.valueOf(rsbing.getString("kkm"));
            }
            rsmtk=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='MTK'");
            while(rsmtk.next()){
                vmtk = String.valueOf(rsmtk.getString("kkm"));
            }
            rsipa=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='IPA'");
            while(rsipa.next()){
                vipa = String.valueOf(rsipa.getString("kkm"));
            }
            rsips=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='IPS'");
            while(rsips.next()){
                vips = String.valueOf(rsips.getString("kkm"));
            }
            rskkpi=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='KKPI'");
            while(rskkpi.next()){
                vkkpi = String.valueOf(rskkpi.getString("kkm"));
            }
            rskwu=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='KWH'");
            while(rskwu.next()){
                vkwu = String.valueOf(rskwu.getString("kkm"));
            }
            rsfsk=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='FSK'");
            while(rsfsk.next()){
                vfsk = String.valueOf(rsfsk.getString("kkm"));
            }
            rskma=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='KMA'");
            while(rskma.next()){
                vkma = String.valueOf(rskma.getString("kkm"));
            }
            rsbio=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='BIO'");
            while(rsbio.next()){
                vbio = String.valueOf(rsbio.getString("kkm"));
            }
            rsbsun=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='BSUN'");
            while(rsbsun.next()){
                vbsun = String.valueOf(rsbsun.getString("kkm"));
            }
            rsplh=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='PLH'");
            while(rsplh.next()){
                vplh = String.valueOf(rsplh.getString("kkm"));
            }
            rspspj=st.executeQuery("SELECT kkm FROM tb_mapel where id_mapel='PSJP'");
            while(rspspj.next()){
                vpspj = String.valueOf(rsplh.getString("kkm"));
            }
        st.close();
        String locFile = "src/report/";
        String namaFile = locFile + vName + ".jasper";
        HashMap parameter = new HashMap();
        parameter.put("Logo", KopLaporan);
        parameter.put("idsiswapr", String.valueOf(txCari.getText()));
        parameter.put("pta", vta);
        parameter.put("pai", vpai);
        parameter.put("pkn", vpkn);
        parameter.put("bind", vbind);
        parameter.put("penjas", vpenjas);
        parameter.put("bing", vbing);
        parameter.put("mtk", vmtk);
        parameter.put("ipa", vipa);
        parameter.put("ips", vips);
        parameter.put("kkpi", vkkpi);
        parameter.put("kwu", vkwu);
        parameter.put("fsk", vfsk);
        parameter.put("kma", vkma);
        parameter.put("bio", vbio);
        parameter.put("bsun", vbsun);
        parameter.put("plh", vplh);
        parameter.put("pspj", vpspj);
        File report_file = new File (namaFile);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
        JasperViewer.viewReport(jasperPrint, false );
        JasperViewer.setDefaultLookAndFeelDecorated(true);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    public void dataTable(){
        Object [] baris = {"id","id status","id siswa","nama","id mapel","nilai harian","nilai uts","nilai uas"};
        tabmode = new DefaultTableModel(null, baris);
        tbMapelp.setModel(tabmode);
        String sql="select*from tb_nilai_mapel";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String sin = hasil.getString("id_nilai_mapel");
                String sis = hasil.getString("id_status");
                String sid = hasil.getString("id_siswa");
                String snama = hasil.getString("nm_siswa");
                String idm = hasil.getString("id_mapel");
                String nh = hasil.getString("nilai_harian");
                String nut = hasil.getString("nilai_uts");
                String nua = hasil.getString("nilai_uas");
                String[]data = {sin,sis,sid,snama,idm,nh,nut,nua};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void TabelKlik(){
        try {
        int bar = tbMapelp.getSelectedRow();
        txCari.setText(tabmode.getValueAt(bar, 2).toString());
        } catch(Exception e) {
            System.err.println(e);
        }
        btProses.setEnabled(false);
    }
    
    
    public void dataTable2(){
        Object [] baris = {"id","id siswa","nama","kompetensi","kelas","semester","tahun ajar","rata-rata","keterangan"};
        tabmode = new DefaultTableModel(null, baris);
        tbRaportp.setModel(tabmode);
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
        
    public void TabelKlik2(){
        int bar = tbMapelp.getSelectedRow();
        txCari.setText(tabmode.getValueAt(bar, 1).toString());
    }
    
    public void Cari(){
        ResultSet rs=null, rs2=null;
        String id=null, ta=null ;
        try {
            rs=conn.createStatement().executeQuery("SELECT * FROM tb_rapot WHERE id_siswa='"+txCari.getText()+"' ");
            while(rs.next()){
                id = rs.getString("id_siswa");
            }
            rs2=conn.createStatement().executeQuery("SELECT * FROM tb_rapot WHERE id_siswa='"+txCari.getText()+"' AND ta='"+cbTA.getSelectedItem()+"' ");
            while(rs2.next()){
                ta = rs2.getString("ta");  
            }
        } catch (Exception e) {
        }
       
        if (txCari.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Data masukan tidak boleh kosong");
        }else if (id != null && ta != null) {
            JOptionPane.showMessageDialog(rootPane, "Data siap di cetak");
            btCetak.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Data belum ada, silahkan tekan tombol Proses terlebih dahulu");
            btProses.setEnabled(true);
            btCetak.setEnabled(false);
        }
     
    }
    
    public void isicbTahun(){
        try {
            Statement st;
            ResultSet rs;
            st=conn.createStatement();
            rs=st.executeQuery("SELECT * FROM tb_tahun_ajaran");
            while(rs.next()){
                cbTA.addItem(rs.getString("ta"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void Proses(){
        Statement st;
        ResultSet rsnmr;
        
        String sql, nmr, idrapot, idsiswa, nmsiswa, nsmt, komp, kls, nta, 
                npramuka, nosis, npmr, nkerajinan, nkerapian, nkelakuan, 
                nsakit, nizin, nalpha, nket, ncatatan ;
        
        int npai, npkn, nbind, 
                npenjas, nbing, nmtk, nipa, nips, nkkpi, nkwu, nfsk, nkma, 
                nbio, npteori, nppraktik, npprakerin, nbsu, nplh, npspj;
        
        double nrata;
        
        sql = "insert into tb_rapot values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //id rapot
        idrapot = null;
        try {
        nmr ="SELECT * FROM tb_rapot order by id_rapot desc";
            st=conn.createStatement();
            rsnmr=st.executeQuery(nmr);
            if (rsnmr.next()) {
                String id = rsnmr.getString("id_rapot").substring(2);
                String AN = "" + (Integer.parseInt(id)+1);
                String Nol = "";
                if (AN.length()==2) {Nol ="00";} 
                if (AN.length()==3) {Nol ="0";} 
                if (AN.length()==4) {Nol="";}
                idrapot = "RP"+Nol+AN;
            } else {
                idrapot = "RP10001";
            }
        } catch (Exception e) {
        }
        
        //id siswa
        idsiswa = txCari.getText();
        
        //nama siswa
        nmsiswa = null;
        try {
            ResultSet rsnm;
            st=conn.createStatement();
            rsnm=st.executeQuery("SELECT * FROM tb_siswa WHERE id_siswa='"+txCari.getText()+"'");
            while(rsnm.next()){
                nmsiswa = rsnm.getString("nm_siswa");
            }
            rsnm.close();
        } catch (Exception e) {
            
        }
        
        //kompetensi
        komp = null;
        try {
            ResultSet rskomp;
            st=conn.createStatement();
            rskomp=st.executeQuery("SELECT tb_kompetensi.kompetensi FROM "
                    + "tb_kompetensi, tb_status_siswa WHERE tb_kompetensi.id_kompetensi = "
                    + "tb_status_siswa.id_kompetensi AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"'");
            while(rskomp.next()){
                komp = rskomp.getString("kompetensi");
            }
            rskomp.close();
        } catch (Exception e) {
            
        }
        
        //kelas
        kls = null;
        try {
            ResultSet rskls;
            st=conn.createStatement();
            rskls=st.executeQuery("SELECT tb_kelas.kelas FROM "
                    + "tb_kelas, tb_status_siswa WHERE tb_kelas.id_kelas = "
                    + "tb_status_siswa.id_kelas AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"'");
            while(rskls.next()){
                kls = rskls.getString("kelas");
            }
            rskls.close();
        } catch (Exception e) {
            
        }
        
        //semester
        nsmt = null;
        try {
            ResultSet rssmt;
            st=conn.createStatement();
            rssmt=st.executeQuery("SELECT tb_semester.semester FROM "
                    + "tb_semester, tb_status_siswa WHERE tb_semester.id_semester = "
                    + "tb_status_siswa.id_semester AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"'");
            while(rssmt.next()){
                nsmt = rssmt.getString("semester");
            }
            rssmt.close();
        } catch (Exception e) {
            
        }
        
        //tahun ajaran
        nta = null;
        try {
            ResultSet rsta;
            st=conn.createStatement();
            rsta=st.executeQuery("SELECT tb_tahun_ajaran.ta FROM "
                    + "tb_tahun_ajaran, tb_status_siswa WHERE tb_tahun_ajaran.id_ta = "
                    + "tb_status_siswa.id_ta AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"'");
            while(rsta.next()){
                nta = rsta.getString("ta");
            }
            rsta.close();
        } catch (Exception e) {
            
        }
        
        //pai 
        npai = 0;
        int uh=0, ut=0, ua=0;
        try {
            ResultSet rspai;
            st=conn.createStatement();
            rspai=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'PAI' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspai.next()){
                uh = rspai.getInt("nilai_harian");
                ut = rspai.getInt("nilai_uts");
                ua = rspai.getInt("nilai_uas");
            }
            rspai.close();
            npai = (uh*20/100)+(ut*30/100)+(ua*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //pkn
        npkn = 0;
        int uhpkn=0, utpkn=0, uapkn=0;
        try {
            ResultSet rspkn;
            st=conn.createStatement();
            rspkn=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'PKN' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspkn.next()){
                uhpkn = rspkn.getInt("nilai_harian");
                utpkn = rspkn.getInt("nilai_uts");
                uapkn = rspkn.getInt("nilai_uas");
            }
            rspkn.close();
            npkn = (uhpkn*20/100)+(utpkn*30/100)+(uapkn*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //bahasa indonesia
        nbind = 0;
        int uhbind=0, utbind=0, uabind=0;
        try {
            ResultSet rsbind;
            st=conn.createStatement();
            rsbind=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'BIND' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsbind.next()){
                uhbind = rsbind.getInt("nilai_harian");
                utbind = rsbind.getInt("nilai_uts");
                uabind = rsbind.getInt("nilai_uas");
            }
            rsbind.close();
            nbind = (uhbind*20/100)+(utbind*30/100)+(uabind*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //penjas
        npenjas = 0;
        int uhp=0, utp=0, uap=0;
        try {
            ResultSet rsp;
            st=conn.createStatement();
            rsp=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'PJS' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsp.next()){
                uhp = rsp.getInt("nilai_harian");
                utp = rsp.getInt("nilai_uts");
                uap = rsp.getInt("nilai_uas");
            }
            rsp.close();
            npenjas = (uhp*20/100)+(utp*30/100)+(uap*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //bahasa inggris
        nbing = 0;
        int uhbing=0, utbing=0, uabing=0;
        try {
            ResultSet rsbing;
            st=conn.createStatement();
            rsbing=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'BING' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsbing.next()){
                uhbing = rsbing.getInt("nilai_harian");
                utbing = rsbing.getInt("nilai_uts");
                uabing = rsbing.getInt("nilai_uas");
            }
            rsbing.close();
            nbing = (uhbing*20/100)+(utbing*30/100)+(uabing*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //matematika
        nmtk = 0;
        int uhmtk=0, utmtk=0, uamtk=0;
        try {
            ResultSet rsmtk;
            st=conn.createStatement();
            rsmtk=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'MTK' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsmtk.next()){
                uhmtk = rsmtk.getInt("nilai_harian");
                utmtk = rsmtk.getInt("nilai_uts");
                uamtk = rsmtk.getInt("nilai_uas");
            }
            rsmtk.close();
            nmtk = (uhmtk*20/100)+(utmtk*30/100)+(uamtk*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //ipa
        nipa = 0;
        int uhipa=0, utipa=0, uaipa=0;
        try {
            ResultSet rsipa;
            st=conn.createStatement();
            rsipa=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'IPA' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsipa.next()){
                uhipa = rsipa.getInt("nilai_harian");
                utipa = rsipa.getInt("nilai_uts");
                uaipa = rsipa.getInt("nilai_uas");
            }
            rsipa.close();
            nipa = (uhipa*20/100)+(utipa*30/100)+(uaipa*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //ips
        nips = 0;
        int uhips=0, utips=0, uaips=0;
        try {
            ResultSet rsips;
            st=conn.createStatement();
            rsips=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'IPS' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsips.next()){
                uhips = rsips.getInt("nilai_harian");
                utips = rsips.getInt("nilai_uts");
                uaips = rsips.getInt("nilai_uas");
            }
            rsips.close();
            nips = (uhips*20/100)+(utips*30/100)+(uaips*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kkpi
        nkkpi = 0;
        int uhkkpi=0, utkkpi=0, uakkpi=0;
        try {
            ResultSet rskkpi;
            st=conn.createStatement();
            rskkpi=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'KKPI' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rskkpi.next()){
                uhkkpi = rskkpi.getInt("nilai_harian");
                utkkpi = rskkpi.getInt("nilai_uts");
                uakkpi = rskkpi.getInt("nilai_uas");
            }
            rskkpi.close();
            nkkpi = (uhkkpi*20/100)+(utkkpi*30/100)+(uakkpi*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kwu
        nkwu = 0;
        int uhkwu=0, utkwu=0, uakwu=0;
        try {
            ResultSet rskwu;
            st=conn.createStatement();
            rskwu=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'KWH' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rskwu.next()){
                uhkwu = rskwu.getInt("nilai_harian");
                utkwu = rskwu.getInt("nilai_uts");
                uakwu = rskwu.getInt("nilai_uas");
            }
            rskwu.close();
            nkwu = (uhkwu*20/100)+(utkwu*30/100)+(uakwu*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //fisika
        nfsk = 0;
        int nhfsk=0, utfsk=0, uafsk=0;
        try {
            ResultSet rsfsk;
            st=conn.createStatement();
            rsfsk=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'FSK' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsfsk.next()){
                nhfsk = rsfsk.getInt("nilai_harian");
                utfsk = rsfsk.getInt("nilai_uts");
                uafsk = rsfsk.getInt("nilai_uas");
            }
            rsfsk.close();
            nfsk = (nhfsk*20/100)+(utfsk*30/100)+(uafsk*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kimia
        nkma = 0;
        int nhkma=0, utkma=0, uakma=0;
        try {
            ResultSet rskma;
            st=conn.createStatement();
            rskma=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'KMA' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rskma.next()){
                nhkma = rskma.getInt("nilai_harian");
                utkma = rskma.getInt("nilai_uts");
                uakma = rskma.getInt("nilai_uas");
            }
            rskma.close();
            nkma = (nhkma*20/100)+(utkma*30/100)+(uakma*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //biologi
        nbio = 0;
        int nhbio=0, utbio=0, uabio=0;
        try {
            ResultSet rsbio;
            st=conn.createStatement();
            rsbio=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'BIO' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsbio.next()){
                nhbio = rsbio.getInt("nilai_harian");
                utbio = rsbio.getInt("nilai_uts");
                uabio = rsbio.getInt("nilai_uas");
            }
            rsbio.close();
            nbio = (nhbio*20/100)+(utbio*30/100)+(uabio*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //Produktif teori
        npteori = 0;
        try {
            ResultSet rspt;
            st=conn.createStatement();
            rspt=st.executeQuery("SELECT tb_produktif.teori FROM "
                    + "tb_produktif, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_produktif.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspt.next()){
                npteori = rspt.getInt("teori");
            }
            rspt.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("nilai produktif teori");
        System.out.println(npteori);
        
        //Produktif pratik
        nppraktik = 0;
        try {
            ResultSet rspp;
            st=conn.createStatement();
            rspp=st.executeQuery("SELECT tb_produktif.praktik FROM "
                    + "tb_produktif, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_produktif.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspp.next()){
                nppraktik = rspp.getInt("praktik");
            }
            rspp.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //produktif prakerin
        npprakerin = 0;
        try {
            ResultSet rsppr;
            st=conn.createStatement();
            rsppr=st.executeQuery("SELECT tb_produktif.pkl FROM "
                    + "tb_produktif, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_produktif.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsppr.next()){
                npprakerin = rsppr.getInt("pkl");
            }
            rsppr.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //bahasa sunda
        nbsu = 0;
        int nhbsu=0, utbsu=0, uabsu=0;
        try {
            ResultSet rsbsu;
            st=conn.createStatement();
            rsbsu=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'BSUN' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsbsu.next()){
                nhbsu = rsbsu.getInt("nilai_harian");
                utbsu = rsbsu.getInt("nilai_uts");
                uabsu = rsbsu.getInt("nilai_uas");
            }
            rsbsu.close();
            nbsu = (nhbsu*20/100)+(utbsu*30/100)+(uabsu*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //plh
        nplh = 0;
        int nhplh=0, utplh=0, uaplh=0;
        try {
            ResultSet rsplh;
            st=conn.createStatement();
            rsplh=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'PLH' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsplh.next()){
                nhplh = rsplh.getInt("nilai_harian");
                utplh = rsplh.getInt("nilai_uts");
                uaplh = rsplh.getInt("nilai_uas");
            }
            rsplh.close();
            nplh = (nhplh*20/100)+(utplh*30/100)+(uaplh*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //pspj
        npspj = 0;
        int nhpspj=0, utpspj=0, uapspj=0;
        try {
            ResultSet rspspj;
            st=conn.createStatement();
            rspspj=st.executeQuery("SELECT tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status AND "
                    + "tb_nilai_mapel.id_mapel = 'PLH' AND "
                    + "tb_nilai_mapel.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspspj.next()){
                nhpspj = rspspj.getInt("nilai_harian");
                utpspj = rspspj.getInt("nilai_uts");
                uapspj = rspspj.getInt("nilai_uas");
            }
            rspspj.close();
            npspj = (nhplh*20/100)+(utplh*30/100)+(uaplh*50/100);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //pramuka
        npramuka = null;
        try {
            ResultSet rspra;
            st=conn.createStatement();
            rspra=st.executeQuery("SELECT tb_pengembangan.pramuka FROM "
                    + "tb_pengembangan, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_pengembangan.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspra.next()){
                npramuka = rspra.getString("pramuka");
            }
            rspra.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //osis
        nosis = null;
        try {
            ResultSet rsos;
            st=conn.createStatement();
            rsos=st.executeQuery("SELECT tb_pengembangan.osis FROM "
                    + "tb_pengembangan, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_pengembangan.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsos.next()){
                nosis = rsos.getString("osis");
            }
            rsos.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //pmr
        npmr = null;
        try {
            ResultSet rspm;
            st=conn.createStatement();
            rspm=st.executeQuery("SELECT tb_pengembangan.pmr FROM "
                    + "tb_pengembangan, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_pengembangan.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rspm.next()){
                npmr = rspm.getString("pmr");
            }
            rspm.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kerajinan
        nkerajinan = null;
        try {
            ResultSet rskr;
            st=conn.createStatement();
            rskr=st.executeQuery("SELECT tb_kepribadian.kerajinan FROM "
                    + "tb_kepribadian, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_kepribadian.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rskr.next()){
                nkerajinan = rskr.getString("kerajinan");
            }
            rskr.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kerapian
        nkerapian = null;
        try {
            ResultSet rske;
            st=conn.createStatement();
            rske=st.executeQuery("SELECT tb_kepribadian.kerapian FROM "
                    + "tb_kepribadian, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_kepribadian.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rske.next()){
                nkerapian = rske.getString("kerapian");
            }
            rske.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kelakuan
        nkelakuan = null;
        try {
            ResultSet rskel;
            st=conn.createStatement();
            rskel=st.executeQuery("SELECT tb_kepribadian.kelakuan FROM "
                    + "tb_kepribadian, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_kepribadian.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rskel.next()){
                nkelakuan = rskel.getString("kelakuan");
            }
            rskel.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //sakit
        nsakit = null;
        try {
            ResultSet rssakit;
            st=conn.createStatement();
            rssakit=st.executeQuery("SELECT tb_absen.sakit FROM "
                    + "tb_absen, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_absen.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rssakit.next()){
                nsakit = rssakit.getString("sakit");
            }
            rssakit.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //izin
        nizin = null;
        try {
            ResultSet rsizin;
            st=conn.createStatement();
            rsizin=st.executeQuery("SELECT tb_absen.izin FROM "
                    + "tb_absen, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_absen.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsizin.next()){
                nizin = rsizin.getString("izin");
            }
            rsizin.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //alpha
        nalpha = null;
        try {
            ResultSet rsalpha;
            st=conn.createStatement();
            rsalpha=st.executeQuery("SELECT tb_absen.alpha FROM "
                    + "tb_absen, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_absen.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rsalpha.next()){
                nalpha = rsalpha.getString("alpha");
            }
            rsalpha.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //rata - rata
        int n = 0;
        if (npspj > 0) {
            n = 19;
        } else{
            n=18;
        }
        nrata = 0; 
        nrata = (npai+npkn+nbind+npenjas+nbing+nmtk+nipa+nips+nkkpi+nkwu+nfsk
                +nkma+nbio+npteori+nppraktik+npprakerin+nbsu+nplh+npspj)/n;
        
        //keterangan 
        if ((int)nrata >= 60) {
            nket = "LULUS";
        } else {
            nket = "TIDAK LULUS";
        }
        
        //catatan
        ncatatan = null;
        try {
            ResultSet rskel;
            st=conn.createStatement();
            rskel=st.executeQuery("SELECT tb_kepribadian.catatan FROM "
                    + "tb_kepribadian, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_kepribadian.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txCari.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+cbTA.getSelectedItem()+"'");
            while(rskel.next()){
                ncatatan = rskel.getString("catatan");
            }
            rskel.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,idrapot);
            stat.setString(2,idsiswa);
            stat.setString(3,nmsiswa);
            stat.setString(4,komp);
            stat.setString(5,kls);
            stat.setString(6,nsmt);
            stat.setString(7,nta);
            stat.setString(8,Integer.toString(npai));
            stat.setString(9,Integer.toString(npkn));
            stat.setString(10,Integer.toString(nbind));
            stat.setString(11,Integer.toString(npenjas));
            stat.setString(12,Integer.toString(nbing));
            stat.setString(13,Integer.toString(nmtk));
            stat.setString(14,Integer.toString(nipa));
            stat.setString(15,Integer.toString(nips));
            stat.setString(16,Integer.toString(nkkpi));
            stat.setString(17,Integer.toString(nkwu));
            stat.setString(18,Integer.toString(nfsk));
            stat.setString(19,Integer.toString(nkma));
            stat.setString(20,Integer.toString(nbio));
            stat.setString(21,Integer.toString(npteori));
            stat.setString(22,Integer.toString(nppraktik));
            stat.setString(23,Integer.toString(npprakerin));
            stat.setString(24,Integer.toString(nbsu));
            stat.setString(25,Integer.toString(nplh));
            stat.setString(26,Integer.toString(npspj));
            stat.setString(27,npramuka);
            stat.setString(28,nosis);
            stat.setString(29,npmr);
            stat.setString(30,nkerajinan);
            stat.setString(31,nkerapian);
            stat.setString(32,nkelakuan);
            stat.setString(33,nsakit);
            stat.setString(34,nizin);
            stat.setString(35,nalpha);
            stat.setString(36,Double.toString(nrata));
            stat.setString(37,nket);
            stat.setString(38,ncatatan);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            dataTable2();
            bKelas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
            System.err.println(e);
        }
    } 
    
    public void bKelas(){
        int row=tbMapelp.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("SELECT "
                    + "tb_nilai_mapel.id_nilai_mapel, tb_nilai_mapel.id_status, "
                    + "tb_nilai_mapel.id_siswa, tb_nilai_mapel.nm_siswa, "
                    + "tb_nilai_mapel.id_mapel, tb_nilai_mapel.nilai_harian, "
                    + "tb_nilai_mapel.nilai_uts, tb_nilai_mapel.nilai_uas FROM "
                    + "tb_nilai_mapel, tb_status_siswa WHERE id_kelas = "
                    + "'"+cbKelas.getSelectedItem()+"' AND "
                    + "tb_nilai_mapel.id_status = tb_status_siswa.id_status");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3)
                        ,rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(GRRaport.class.getName()).log(Level.SEVERE, null, ex);  
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
        btCari = new javax.swing.JButton();
        btProses = new javax.swing.JButton();
        btCetak = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbRaportp = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMapelp = new javax.swing.JTable();
        cbKelas = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        cbTA = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txRev = new javax.swing.JLabel();

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
                .addGap(0, 977, Short.MAX_VALUE)
                .addComponent(GsExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(GsExit)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, -1));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Cetak Raport Siswa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        btCari.setText("cari");
        btCari.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 50, -1));

        btProses.setText("Proses");
        btProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesActionPerformed(evt);
            }
        });
        jPanel1.add(btProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 90, 30));

        btCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-20.png"))); // NOI18N
        btCetak.setText("Cetak");
        btCetak.setIconTextGap(8);
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(883, 100, 100, 40));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Nilai Raport"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbRaportp.setModel(new javax.swing.table.DefaultTableModel(
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
        tbRaportp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRaportpMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbRaportp);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 940, 170));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 255));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, -2, 80, 0));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 980, 220));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Nilai Mapel"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbMapelp.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMapelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMapelpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMapelp);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 940, 170));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 980, 220));

        cbKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "11", "12" }));
        cbKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKelasActionPerformed(evt);
            }
        });
        jPanel1.add(cbKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 60, -1));

        jLabel2.setText("Kelas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        txCari.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCari.setMargin(new java.awt.Insets(4, 2, 4, 2));
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 130, -1));

        cbTA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih -" }));
        cbTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTAActionPerformed(evt);
            }
        });
        jPanel1.add(cbTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 90, -1));

        jLabel3.setText("TA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, -1));

        jLabel4.setText("id Siswa");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txRev.setForeground(new java.awt.Color(51, 51, 255));
        txRev.setText("Revisi data rapot");
        txRev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txRevMouseClicked(evt);
            }
        });
        jPanel1.add(txRev, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 404, -1, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 660));

        setSize(new java.awt.Dimension(1021, 660));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GsExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GsExitActionPerformed
        // Keluar
        dispose();
    }//GEN-LAST:event_GsExitActionPerformed

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

    private void tbRaportpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRaportpMouseClicked
        // tb raport klik
        TabelKlik2();
    }//GEN-LAST:event_tbRaportpMouseClicked

    private void cbKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKelasActionPerformed
        // TODO add your handling code here: 
        bKelas();
        txCari.setText("");
        btProses.setEnabled(false);
    }//GEN-LAST:event_cbKelasActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
        makePreview("Rapot");
    }//GEN-LAST:event_btCetakActionPerformed

    private void tbMapelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMapelpMouseClicked
        // TODO add your handling code here:
        TabelKlik();
    }//GEN-LAST:event_tbMapelpMouseClicked

    private void cbTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTAActionPerformed
        // TODO add your handling code here:
        btCari.setEnabled(true);
    }//GEN-LAST:event_cbTAActionPerformed

    private void btProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesActionPerformed
        // proses
        Proses();
    }//GEN-LAST:event_btProsesActionPerformed

    private void txRevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txRevMouseClicked
        // revisi
        if (txCari.getText().equals("") || cbTA.getSelectedItem().equals("- pilih -")) {
            JOptionPane.showMessageDialog(rootPane, "Maaf harap masukkan data id dan tahun ajaran siswa");
        } else  {
        Statement st;
        String xidrp=null, xidsis=null, xnm=null, xkom=null, xkel=null, xsem=null, 
                xta=null, xpai=null, xpkn=null, xbind=null, xpenjas=null, xbing=null,
                xmtk=null, xipa=null, xips=null, xkkpi=null, xkwu=null, xfsk=null, 
                xkma=null, xbio=null, xpteori=null, xppraktik=null, xpprakerin=null, 
                xbsu=null, xplh=null, xpspj=null, xpramuka=null, xosis=null, 
                xpmr=null, xkrj=null, xkrp=null, xklk=null, xs=null, xi=null, xa=null, 
                xrata=null, xket=null, xcat=null;
        try {
            ResultSet rs;
            st=conn.createStatement();
            rs=st.executeQuery("SELECT * FROM tb_rapot WHERE id_siswa='"+txCari.getText()+"' AND "
                    + "ta='"+cbTA.getSelectedItem()+"'");
            while(rs.next()){
                xidrp =  rs.getString("id_rapot");
                xidsis =  rs.getString("id_siswa");
                xnm =  rs.getString("nm_siswa");
                xkom =  rs.getString("kompetensi");
                xkel =  rs.getString("kelas");
                xsem =  rs.getString("semester");
                xta =  rs.getString("ta");
                xpai =  rs.getString("pai");
                xpkn =  rs.getString("pkn");
                xbind =  rs.getString("bind");
                xpenjas =  rs.getString("penjas");
                xbing =  rs.getString("bing");
                xmtk =  rs.getString("mtk");
                xipa =  rs.getString("ipa");
                xips =  rs.getString("ips");
                xkkpi =  rs.getString("kkpi");
                xkwu =  rs.getString("kwu");
                xfsk =  rs.getString("fisika");
                xkma =  rs.getString("kimia");
                xbio =  rs.getString("biologi");
                xpteori =  rs.getString("pteori");
                xppraktik =  rs.getString("ppraktik");
                xpprakerin =  rs.getString("pprakerin");
                xbsu =  rs.getString("bsu");
                xplh =  rs.getString("plh");
                xpspj =  rs.getString("pspj");
                xpramuka =  rs.getString("pramuka");
                xosis =  rs.getString("osis");
                xpmr =  rs.getString("pmr");
                xkrj =  rs.getString("kerajinan");
                xkrp =  rs.getString("kerapian");
                xklk =  rs.getString("kelakuan");
                xs =  rs.getString("sakit");
                xi =  rs.getString("izin");
                xa =  rs.getString("alpha");
                xrata =  rs.getString("rata");
                xket =  rs.getString("ket");
                xcat =  rs.getString("catatan");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        GRevRapot gv = new GRevRapot();
        gv.setVisible(true); 
        gv.txid.setText(xidrp);
        gv.txidSis.setText(xidsis);
        gv.txNm.setText(xnm);
        gv.txKomp.setText(xkom);
        gv.txKel.setText(xkel);
        gv.txSm.setText(xsem);
        gv.txTa.setText(xta);
        gv.txpai.setText(xpai);
        gv.txpkn.setText(xpkn);
        gv.txbind.setText(xbind);
        gv.txpenjas.setText(xpenjas);
        gv.txbing.setText(xbing);
        gv.txmtk.setText(xmtk);
        gv.txipa.setText(xipa);
        gv.txips.setText(xips);
        gv.txkkpi.setText(xkkpi);
        gv.txkwu.setText(xkwu);
        gv.txfsk.setText(xfsk);
        gv.txkma.setText(xkma);
        gv.txbio.setText(xbio);
        gv.txbsu.setText(xbsu);
        gv.txplh.setText(xplh);
        gv.txpspj.setText(xpspj);
        gv.txpteori.setText(xpteori);
        gv.txppraktik.setText(xppraktik);
        gv.txpprakerin.setText(xpprakerin);
        gv.txpramuka.setText(xpramuka);
        gv.txosis.setText(xosis);
        gv.txpmr.setText(xpmr);
        gv.txkerajinan.setText(xkrj);
        gv.txkerapian.setText(xkrp);
        gv.txkelakuan.setText(xklk);
        gv.txsakit.setText(xs);
        gv.txizin.setText(xi);
        gv.txalpha.setText(xa);
        gv.txrata.setText(xrata);
        gv.txket.setText(xket);
        gv.txcat.setText(xcat);
        dispose();
        }
    }//GEN-LAST:event_txRevMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GRRaport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupJenkel;
    private javax.swing.JButton GsExit;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btProses;
    private javax.swing.JComboBox cbKelas;
    private javax.swing.JComboBox cbTA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbMapelp;
    private javax.swing.JTable tbRaportp;
    public javax.swing.JTextField txCari;
    private javax.swing.JLabel txRev;
    // End of variables declaration//GEN-END:variables
}
