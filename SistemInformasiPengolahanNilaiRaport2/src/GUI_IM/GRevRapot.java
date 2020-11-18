
package GUI_IM;
import FormReport.GRRaport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import mainkoneksi.koneksi2;

/**
 *
 * @author fidia
 */
public class GRevRapot extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();

    public GRevRapot() {
        initComponents();
//        Proses();
    }
    
    public void Simpan(){
        if (txidSis.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Maaf data kosong");
        } else  {
        Statement st;
        ResultSet rsnmr;
        String sql = "UPDATE tb_rapot SET id_siswa=?, nm_siswa=?, kompetensi=?, "
                + "kelas=?, semester=?, ta=?, pai=?, pkn=?, bind=?, penjas=?, "
                + "bing=?, mtk=?, ipa=?, ips=?, kkpi=?, kwu=?, fisika=?, kimia=?, "
                + "biologi=?, pteori=?, ppraktik=?, pprakerin=?, bsu=?, plh=?, "
                + "pspj=?, pramuka=?, osis=?, pmr=?, kerajinan=?, kerapian=?, "
                + "kelakuan=?, sakit=?, izin=?, alpha=?, rata=?, ket=?, "
                + "catatan=? WHERE id_rapot='"+txid.getText()+"'";
            try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txidSis.getText());
            stat.setString(2,txNm.getText());
            stat.setString(3,txKomp.getText());
            stat.setString(4,txKel.getText());
            stat.setString(5,txSm.getText());
            stat.setString(6,txTa.getText());
            stat.setString(7,txpai.getText());
            stat.setString(8,txpkn.getText());
            stat.setString(9,txbind.getText());
            stat.setString(10,txpenjas.getText());
            stat.setString(11,txbing.getText());
            stat.setString(12,txmtk.getText());
            stat.setString(13,txipa.getText());
            stat.setString(14,txips.getText());
            stat.setString(15,txkkpi.getText());
            stat.setString(16,txkwu.getText());
            stat.setString(17,txfsk.getText());
            stat.setString(18,txkma.getText());
            stat.setString(19,txbio.getText());
            stat.setString(20,txpteori.getText());
            stat.setString(21,txppraktik.getText());
            stat.setString(22,txpprakerin.getText());
            stat.setString(23,txbsu.getText());
            stat.setString(24,txplh.getText());
            stat.setString(25,txpspj.getText());
            stat.setString(26,txpramuka.getText());
            stat.setString(27,txosis.getText());
            stat.setString(28,txpmr.getText());
            stat.setString(29,txkerajinan.getText());
            stat.setString(30,txkerapian.getText());
            stat.setString(31,txkelakuan.getText());
            stat.setString(32,txsakit.getText());
            stat.setString(33,txizin.getText());
            stat.setString(34,txalpha.getText());
            stat.setString(35,txrata.getText());
            stat.setString(36,txket.getText());
            stat.setString(37,txcat.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            GRRaport gr = new GRRaport();
            gr.setVisible(true);
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
            System.err.println(e);
        }
        }
    }
    
    public void Proses(){
        int tot = Integer.parseInt(txpai.getText())+Integer.parseInt(txpkn.getText())/2;
        System.out.println(tot);
    }
    
    public void Update(){
        Statement st;
        ResultSet rsnmr;
        
        String sql, nmsiswa, nsmt, komp, kls, npramuka, nosis, npmr, nkerajinan, 
                nkerapian, nkelakuan,nsakit, nizin, nalpha, nket, ncatatan ;
        
        int npai, npkn, nbind, npenjas, nbing, nmtk, nipa, nips, nkkpi, nkwu, 
                nfsk, nkma, nbio, npteori, nppraktik, npprakerin, nbsu, nplh, npspj;
        
        double nrata;
        
        //nama siswa
        nmsiswa = null;
        try {
            ResultSet rsnm;
            st=conn.createStatement();
            rsnm=st.executeQuery("SELECT * FROM tb_siswa WHERE id_siswa='"+txidSis.getText()+"'");
            while(rsnm.next()){
                nmsiswa = rsnm.getString("nm_siswa");
                txNm.setText(nmsiswa);
            }
            rsnm.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kompetensi
        komp = null;
        try {
            ResultSet rskomp;
            st=conn.createStatement();
            rskomp=st.executeQuery("SELECT tb_kompetensi.kompetensi FROM "
                    + "tb_kompetensi, tb_status_siswa WHERE tb_kompetensi.id_kompetensi = "
                    + "tb_status_siswa.id_kompetensi AND "
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"'");
            while(rskomp.next()){
                komp = rskomp.getString("kompetensi");
                txKomp.setText(komp);
            }
            rskomp.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //kelas
        kls = null;
        try {
            ResultSet rskls;
            st=conn.createStatement();
            rskls=st.executeQuery("SELECT tb_kelas.kelas FROM "
                    + "tb_kelas, tb_status_siswa WHERE tb_kelas.id_kelas = "
                    + "tb_status_siswa.id_kelas AND "
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"'");
            while(rskls.next()){
                kls = rskls.getString("kelas");
                txKel.setText(kls);
            }
            rskls.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //semester
        nsmt = null;
        try {
            ResultSet rssmt;
            st=conn.createStatement();
            rssmt=st.executeQuery("SELECT tb_semester.semester FROM "
                    + "tb_semester, tb_status_siswa WHERE tb_semester.id_semester = "
                    + "tb_status_siswa.id_semester AND "
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"'");
            while(rssmt.next()){
                nsmt = rssmt.getString("semester");
                txSm.setText(nsmt);
            }
            rssmt.close();
        } catch (Exception e) {
            System.err.println(e);
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspai.next()){
                uh = rspai.getInt("nilai_harian");
                ut = rspai.getInt("nilai_uts");
                ua = rspai.getInt("nilai_uas");
            }
            rspai.close();
            npai = (uh*20/100)+(ut*30/100)+(ua*50/100);
            txpai.setText(Integer.toString(npai));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspkn.next()){
                uhpkn = rspkn.getInt("nilai_harian");
                utpkn = rspkn.getInt("nilai_uts");
                uapkn = rspkn.getInt("nilai_uas");
            }
            rspkn.close();
            npkn = (uhpkn*20/100)+(utpkn*30/100)+(uapkn*50/100);
            txpkn.setText(Integer.toString(npkn));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsbind.next()){
                uhbind = rsbind.getInt("nilai_harian");
                utbind = rsbind.getInt("nilai_uts");
                uabind = rsbind.getInt("nilai_uas");
            }
            rsbind.close();
            nbind = (uhbind*20/100)+(utbind*30/100)+(uabind*50/100);
            txbind.setText(Integer.toString(nbind));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsp.next()){
                uhp = rsp.getInt("nilai_harian");
                utp = rsp.getInt("nilai_uts");
                uap = rsp.getInt("nilai_uas");
            }
            rsp.close();
            npenjas = (uhp*20/100)+(utp*30/100)+(uap*50/100);
            txpenjas.setText(Integer.toString(npenjas));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsbing.next()){
                uhbing = rsbing.getInt("nilai_harian");
                utbing = rsbing.getInt("nilai_uts");
                uabing = rsbing.getInt("nilai_uas");
            }
            rsbing.close();
            nbing = (uhbing*20/100)+(utbing*30/100)+(uabing*50/100);
            txbing.setText(Integer.toString(nbing));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsmtk.next()){
                uhmtk = rsmtk.getInt("nilai_harian");
                utmtk = rsmtk.getInt("nilai_uts");
                uamtk = rsmtk.getInt("nilai_uas");
            }
            rsmtk.close();
            nmtk = (uhmtk*20/100)+(utmtk*30/100)+(uamtk*50/100);
            txmtk.setText(Integer.toString(nmtk));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsipa.next()){
                uhipa = rsipa.getInt("nilai_harian");
                utipa = rsipa.getInt("nilai_uts");
                uaipa = rsipa.getInt("nilai_uas");
            }
            rsipa.close();
            nipa = (uhipa*20/100)+(utipa*30/100)+(uaipa*50/100);
            txipa.setText(Integer.toString(nipa));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsips.next()){
                uhips = rsips.getInt("nilai_harian");
                utips = rsips.getInt("nilai_uts");
                uaips = rsips.getInt("nilai_uas");
            }
            rsips.close();
            nips = (uhips*20/100)+(utips*30/100)+(uaips*50/100);
            txips.setText(Integer.toString(nips));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rskkpi.next()){
                uhkkpi = rskkpi.getInt("nilai_harian");
                utkkpi = rskkpi.getInt("nilai_uts");
                uakkpi = rskkpi.getInt("nilai_uas");
            }
            rskkpi.close();
            nkkpi = (uhkkpi*20/100)+(utkkpi*30/100)+(uakkpi*50/100);
            txkkpi.setText(Integer.toString(nkkpi));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rskwu.next()){
                uhkwu = rskwu.getInt("nilai_harian");
                utkwu = rskwu.getInt("nilai_uts");
                uakwu = rskwu.getInt("nilai_uas");
            }
            rskwu.close();
            nkwu = (uhkwu*20/100)+(utkwu*30/100)+(uakwu*50/100);
            txkwu.setText(Integer.toString(nkwu));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsfsk.next()){
                nhfsk = rsfsk.getInt("nilai_harian");
                utfsk = rsfsk.getInt("nilai_uts");
                uafsk = rsfsk.getInt("nilai_uas");
            }
            rsfsk.close();
            nfsk = (nhfsk*20/100)+(utfsk*30/100)+(uafsk*50/100);
            txfsk.setText(Integer.toString(nfsk));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rskma.next()){
                nhkma = rskma.getInt("nilai_harian");
                utkma = rskma.getInt("nilai_uts");
                uakma = rskma.getInt("nilai_uas");
            }
            rskma.close();
            nkma = (nhkma*20/100)+(utkma*30/100)+(uakma*50/100);
            txkma.setText(Integer.toString(nkma));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsbio.next()){
                nhbio = rsbio.getInt("nilai_harian");
                utbio = rsbio.getInt("nilai_uts");
                uabio = rsbio.getInt("nilai_uas");
            }
            rsbio.close();
            nbio = (nhbio*20/100)+(utbio*30/100)+(uabio*50/100);
            txbio.setText(Integer.toString(nbio));
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspt.next()){
                npteori = rspt.getInt("teori");
            }
            rspt.close();
            txpteori.setText(Integer.toString(npteori));
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //Produktif pratik
        nppraktik = 0;
        try {
            ResultSet rspp;
            st=conn.createStatement();
            rspp=st.executeQuery("SELECT tb_produktif.praktik FROM "
                    + "tb_produktif, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_produktif.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspp.next()){
                nppraktik = rspp.getInt("praktik");
            }
            rspp.close();
            txppraktik.setText(Integer.toString(nppraktik));
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsppr.next()){
                npprakerin = rsppr.getInt("pkl");
            }
            rsppr.close();
            txpprakerin.setText(Integer.toString(npprakerin));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsbsu.next()){
                nhbsu = rsbsu.getInt("nilai_harian");
                utbsu = rsbsu.getInt("nilai_uts");
                uabsu = rsbsu.getInt("nilai_uas");
            }
            rsbsu.close();
            nbsu = (nhbsu*20/100)+(utbsu*30/100)+(uabsu*50/100);
            txbsu.setText(Integer.toString(nbsu));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsplh.next()){
                nhplh = rsplh.getInt("nilai_harian");
                utplh = rsplh.getInt("nilai_uts");
                uaplh = rsplh.getInt("nilai_uas");
            }
            rsplh.close();
            nplh = (nhplh*20/100)+(utplh*30/100)+(uaplh*50/100);
            txplh.setText(Integer.toString(nplh));
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
                    + "tb_nilai_mapel.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspspj.next()){
                nhpspj = rspspj.getInt("nilai_harian");
                utpspj = rspspj.getInt("nilai_uts");
                uapspj = rspspj.getInt("nilai_uas");
            }
            rspspj.close();
            npspj = (nhplh*20/100)+(utplh*30/100)+(uaplh*50/100);
            txpspj.setText(Integer.toString(npspj));
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspra.next()){
                npramuka = rspra.getString("pramuka");
            }
            rspra.close();
            txpramuka.setText(npramuka);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsos.next()){
                nosis = rsos.getString("osis");
            }
            rsos.close();
            txosis.setText(nosis);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rspm.next()){
                npmr = rspm.getString("pmr");
            }
            rspm.close();
            txpmr.setText(npmr);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rskr.next()){
                nkerajinan = rskr.getString("kerajinan");
            }
            rskr.close();
            txkerajinan.setText(nkerajinan);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rske.next()){
                nkerapian = rske.getString("kerapian");
            }
            rske.close();
            txkerapian.setText(nkerapian);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rskel.next()){
                nkelakuan = rskel.getString("kelakuan");
            }
            rskel.close();
            txkelakuan.setText(nkelakuan);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rssakit.next()){
                nsakit = rssakit.getString("sakit");
            }
            rssakit.close();
            txsakit.setText(nsakit);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsizin.next()){
                nizin = rsizin.getString("izin");
            }
            rsizin.close();
            txizin.setText(nizin);
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
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rsalpha.next()){
                nalpha = rsalpha.getString("alpha");
            }
            rsalpha.close();
            txalpha.setText(nalpha);
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
        txrata.setText(Double.toString(nrata));
        
        //keterangan 
        if ((int)nrata >= 60) {
            nket = "LULUS";
        } else {
            nket = "TIDAK LULUS";
        }
        txket.setText(nket);
        
        //catatan
        ncatatan = null;
        try {
            ResultSet rskel;
            st=conn.createStatement();
            rskel=st.executeQuery("SELECT tb_kepribadian.catatan FROM "
                    + "tb_kepribadian, tb_status_siswa, tb_tahun_ajaran WHERE "
                    + "tb_tahun_ajaran.id_ta = tb_status_siswa.id_ta AND "
                    + "tb_kepribadian.id_status = tb_status_siswa.id_status AND "
                    + "tb_status_siswa.id_siswa='"+txidSis.getText()+"' AND "
                    + "tb_tahun_ajaran.ta = '"+txTa.getText()+"'");
            while(rskel.next()){
                ncatatan = rskel.getString("catatan");
            }
            rskel.close();
            txcat.setText(ncatatan);
        } catch (Exception e) {
            System.err.println(e);
        }
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        GsExit = new javax.swing.JButton();
        txNm = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txid = new javax.swing.JLabel();
        txidSis = new javax.swing.JLabel();
        txSm = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txKomp = new javax.swing.JLabel();
        txKel = new javax.swing.JLabel();
        txTa = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txpai = new javax.swing.JTextField();
        txpkn = new javax.swing.JTextField();
        txbind = new javax.swing.JTextField();
        txpenjas = new javax.swing.JTextField();
        txbing = new javax.swing.JTextField();
        txmtk = new javax.swing.JTextField();
        txipa = new javax.swing.JTextField();
        txips = new javax.swing.JTextField();
        txplh = new javax.swing.JTextField();
        txbio = new javax.swing.JTextField();
        txpspj = new javax.swing.JTextField();
        txbsu = new javax.swing.JTextField();
        txkma = new javax.swing.JTextField();
        txfsk = new javax.swing.JTextField();
        txkwu = new javax.swing.JTextField();
        txkkpi = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txpteori = new javax.swing.JTextField();
        txppraktik = new javax.swing.JTextField();
        txpprakerin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txpramuka = new javax.swing.JTextField();
        txosis = new javax.swing.JTextField();
        txpmr = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txkerapian = new javax.swing.JTextField();
        txkelakuan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txkerajinan = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txsakit = new javax.swing.JTextField();
        txizin = new javax.swing.JTextField();
        txalpha = new javax.swing.JTextField();
        txrata = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txket = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txcat = new javax.swing.JTextArea();
        btSimpan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Revisi Data Rapot");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

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
                .addGap(0, 727, Short.MAX_VALUE)
                .addComponent(GsExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(GsExit)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, -1));

        txNm.setText("-");
        jPanel1.add(txNm, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 100, -1));

        jLabel7.setText("id Rapot");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel8.setText("id Siswa");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel9.setText("Nama");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        txid.setText("-");
        jPanel1.add(txid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 100, -1));

        txidSis.setText("-");
        jPanel1.add(txidSis, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 100, -1));

        txSm.setText("-");
        jPanel1.add(txSm, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 100, -1));

        jLabel13.setText("Kompetensi");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        jLabel14.setText("Kelas");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jLabel15.setText("Semester");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        txKomp.setText("-");
        jPanel1.add(txKomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 170, -1));

        txKel.setText("-");
        jPanel1.add(txKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 100, -1));

        txTa.setText("-");
        jPanel1.add(txTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 90, -1));

        jLabel19.setText("Tahun Ajaran");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nilai mapel"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txpai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 44, -1));
        jPanel3.add(txpkn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 44, -1));
        jPanel3.add(txbind, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 44, -1));

        txpenjas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpenjasActionPerformed(evt);
            }
        });
        jPanel3.add(txpenjas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 44, -1));
        jPanel3.add(txbing, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 44, -1));
        jPanel3.add(txmtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 44, -1));
        jPanel3.add(txipa, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 44, -1));

        txips.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txipsActionPerformed(evt);
            }
        });
        jPanel3.add(txips, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 44, -1));
        jPanel3.add(txplh, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 44, -1));
        jPanel3.add(txbio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 44, -1));

        txpspj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpspjActionPerformed(evt);
            }
        });
        jPanel3.add(txpspj, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 44, -1));
        jPanel3.add(txbsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 44, -1));

        txkma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkmaActionPerformed(evt);
            }
        });
        jPanel3.add(txkma, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 44, -1));
        jPanel3.add(txfsk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 44, -1));
        jPanel3.add(txkwu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 44, -1));
        jPanel3.add(txkkpi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 44, -1));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("IPS");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 80, -1));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("PAI");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, -1));

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("PKN");
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 80, -1));

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("B. Indonesia");
        jPanel3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 80, -1));

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Penjas");
        jPanel3.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 80, -1));

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("B. Inggris");
        jPanel3.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 80, -1));

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Matematika");
        jPanel3.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 80, -1));

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("IPA");
        jPanel3.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 80, -1));

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("PSPJ");
        jPanel3.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 80, -1));

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("KKPI");
        jPanel3.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, -1));

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("KWU");
        jPanel3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 80, -1));

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Fisika");
        jPanel3.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 80, -1));

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Kimia");
        jPanel3.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 80, -1));

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Biologi");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 80, -1));

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("B. Sunda");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 80, -1));

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("PLH");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 80, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 730, 130));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nilai produktif"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(txpteori, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 44, -1));
        jPanel4.add(txppraktik, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 44, -1));
        jPanel4.add(txpprakerin, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 44, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Prakerin");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 60, -1));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Teori");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 60, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Praktik");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 60, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 730, 60));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(txpramuka, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 12, 88, -1));
        jPanel5.add(txosis, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 38, 88, -1));
        jPanel5.add(txpmr, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 64, 88, -1));

        jLabel3.setText("Pramuka");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 15, -1, -1));

        jLabel4.setText("Osis");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 41, -1, -1));

        jLabel5.setText("PMR");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 67, -1, -1));
        jPanel5.add(txkerapian, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 38, 88, -1));
        jPanel5.add(txkelakuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 64, 88, -1));

        jLabel6.setText("Kerajianan");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 15, -1, -1));

        jLabel32.setText("Kerapian");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 41, -1, -1));

        jLabel33.setText("Kelakuan");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 67, -1, -1));
        jPanel5.add(txkerajinan, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 12, 88, -1));

        jLabel34.setText("Sakit");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 15, -1, -1));

        jLabel35.setText("Izin");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 41, -1, -1));

        jLabel36.setText("Alpha");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 67, -1, -1));
        jPanel5.add(txsakit, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 12, 44, -1));
        jPanel5.add(txizin, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 38, 44, -1));
        jPanel5.add(txalpha, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 64, 44, -1));

        txrata.setEditable(false);
        jPanel5.add(txrata, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 12, 44, -1));

        jLabel37.setText("Rata-Rata");
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 15, -1, -1));

        jLabel38.setText("Keterangan");
        jPanel5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 41, -1, -1));

        txket.setEditable(false);
        jPanel5.add(txket, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 38, 91, -1));

        jLabel39.setText("Catatan");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        txcat.setColumns(1);
        txcat.setLineWrap(true);
        txcat.setRows(5);
        txcat.setTabSize(2);
        jScrollPane1.setViewportView(txcat);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 290, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 730, 160));

        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, -1, -1));

        jButton1.setText("Perbarui");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GsExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GsExitActionPerformed
        // Keluar
        dispose();
    }//GEN-LAST:event_GsExitActionPerformed
    
    int x, y;
    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // Mouse drag
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // window
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void txkmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkmaActionPerformed

    private void txpspjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpspjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpspjActionPerformed

    private void txipsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txipsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txipsActionPerformed

    private void txpenjasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpenjasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpenjasActionPerformed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // simpan
        Simpan();
    }//GEN-LAST:event_btSimpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GRevRapot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GRevRapot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GRevRapot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GRevRapot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GRevRapot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GsExit;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel txKel;
    public javax.swing.JLabel txKomp;
    public javax.swing.JLabel txNm;
    public javax.swing.JLabel txSm;
    public javax.swing.JLabel txTa;
    public javax.swing.JTextField txalpha;
    public javax.swing.JTextField txbind;
    public javax.swing.JTextField txbing;
    public javax.swing.JTextField txbio;
    public javax.swing.JTextField txbsu;
    public javax.swing.JTextArea txcat;
    public javax.swing.JTextField txfsk;
    public javax.swing.JLabel txid;
    public javax.swing.JLabel txidSis;
    public javax.swing.JTextField txipa;
    public javax.swing.JTextField txips;
    public javax.swing.JTextField txizin;
    public javax.swing.JTextField txkelakuan;
    public javax.swing.JTextField txkerajinan;
    public javax.swing.JTextField txkerapian;
    public javax.swing.JTextField txket;
    public javax.swing.JTextField txkkpi;
    public javax.swing.JTextField txkma;
    public javax.swing.JTextField txkwu;
    public javax.swing.JTextField txmtk;
    public javax.swing.JTextField txosis;
    public javax.swing.JTextField txpai;
    public javax.swing.JTextField txpenjas;
    public javax.swing.JTextField txpkn;
    public javax.swing.JTextField txplh;
    public javax.swing.JTextField txpmr;
    public javax.swing.JTextField txpprakerin;
    public javax.swing.JTextField txppraktik;
    public javax.swing.JTextField txpramuka;
    public javax.swing.JTextField txpspj;
    public javax.swing.JTextField txpteori;
    public javax.swing.JTextField txrata;
    public javax.swing.JTextField txsakit;
    // End of variables declaration//GEN-END:variables
}
