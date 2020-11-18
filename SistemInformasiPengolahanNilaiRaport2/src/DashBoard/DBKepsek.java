
package DashBoard;

import FormReport.GRekap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sistem.About;
import sistem.Hubungi;
import sistem.Login;
import java.util.Date;
import java.text.*;
import mainkoneksi.koneksi2;
import sistem.ProfilPengguna;
import FormReport.makePreview;
/**
 *
 * @author fidia
 */
public class DBKepsek extends javax.swing.JFrame {
    
    private final Connection conn = new koneksi2().connect();
    makePreview mp = new makePreview();
    About ab = new About();
    Hubungi hb = new Hubungi();
    Login log = new Login();
    ProfilPengguna pg = new ProfilPengguna();
    GRekap gr = new GRekap();

    public DBKepsek() {
        initComponents();
        pmnLog.setVisible(false);
        getUser();
        Tanggal();
        JumlahSiswa();
        JumlahGuru();
        JumlahKelas();
    } 
    
    public JLabel getUser() {
        return txmUser2;
    }

    public void setUser(JLabel txmUser2) {
        this.txmUser2 = txmUser2;
    }
    
    public void Tanggal() {
      Date HariSekarang = new Date( );
      SimpleDateFormat fh = new SimpleDateFormat (" EEEE ");
      SimpleDateFormat ft = new SimpleDateFormat (" dd MMMM yyyy ");
      totHari.setText(fh.format(HariSekarang));
      totTanggal.setText(ft.format(HariSekarang));
    }
    
    public void JumlahSiswa(){
        try{
            Statement st;
            ResultSet rs;
            st=conn.createStatement();
            rs=st.executeQuery("select * from tb_siswa");
            while (rs.next()) {
                int b = rs.getRow();
                totSiswa.setText(""+b);
            }
        } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }
    
     public void JumlahGuru(){
        try{
            Statement st;
            ResultSet rs;
            st=conn.createStatement();
            rs=st.executeQuery("select * from tb_guru");
            while (rs.next()) {
                int b = rs.getRow();
                totGuru.setText(""+b);
            }
        } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }
     
    public void JumlahKelas(){
        try{
            Statement st;
            ResultSet rs;
            st=conn.createStatement();
            rs=st.executeQuery("select * from tb_kelas");
            while (rs.next()) {
                int b = rs.getRow();
                totKelas.setText(""+b);
            }
        } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnLog = new javax.swing.JPanel();
        logUser2 = new javax.swing.JLabel();
        txmProfil = new javax.swing.JLabel();
        txmTentang = new javax.swing.JLabel();
        txmKeluar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        totSiswa = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        totGuru = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        totKelas = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        totTanggal = new javax.swing.JLabel();
        totHari = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btmRekap = new rojerusan.RSButtonIconI();
        btmSiswa = new rojerusan.RSButtonIconI();
        btmMapel = new rojerusan.RSButtonIconI();
        btmGuru = new rojerusan.RSButtonIconI();
        jPanel1 = new javax.swing.JPanel();
        txmUser2 = new javax.swing.JLabel();
        logUser3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuKeluar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuProfil = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuBantuan = new javax.swing.JMenuItem();
        jMenuTentang = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pmnLog.setBackground(new java.awt.Color(51, 51, 51));
        pmnLog.setPreferredSize(new java.awt.Dimension(83, 115));

        logUser2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_user_male_circle_32px.png"))); // NOI18N
        logUser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logUser2MouseClicked(evt);
            }
        });

        txmProfil.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txmProfil.setForeground(new java.awt.Color(255, 255, 255));
        txmProfil.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txmProfil.setText("Profil");
        txmProfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txmProfilMouseClicked(evt);
            }
        });

        txmTentang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txmTentang.setForeground(new java.awt.Color(255, 255, 255));
        txmTentang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txmTentang.setText("Tentang");
        txmTentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txmTentangMouseClicked(evt);
            }
        });

        txmKeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txmKeluar.setForeground(new java.awt.Color(255, 255, 255));
        txmKeluar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txmKeluar.setText("Keluar");
        txmKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txmKeluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pmnLogLayout = new javax.swing.GroupLayout(pmnLog);
        pmnLog.setLayout(pmnLogLayout);
        pmnLogLayout.setHorizontalGroup(
            pmnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmnLogLayout.createSequentialGroup()
                .addGroup(pmnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pmnLogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(logUser2))
                    .addComponent(txmProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txmTentang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(txmKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        pmnLogLayout.setVerticalGroup(
            pmnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pmnLogLayout.createSequentialGroup()
                .addComponent(logUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txmProfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txmTentang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txmKeluar)
                .addContainerGap())
        );

        getContentPane().add(pmnLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 0, -1, 110));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel2.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Total Siswa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

        totSiswa.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        totSiswa.setForeground(new java.awt.Color(0, 102, 0));
        totSiswa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totSiswa.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totSiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 160, 120));

        jPanel5.setBackground(new java.awt.Color(102, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Total Guru", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel5.setForeground(new java.awt.Color(102, 102, 102));

        totGuru.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        totGuru.setForeground(new java.awt.Color(0, 102, 0));
        totGuru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totGuru.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totGuru, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totGuru, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 160, 120));

        jPanel6.setBackground(new java.awt.Color(102, 153, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Total Kelas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(102, 102, 102));

        totKelas.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        totKelas.setForeground(new java.awt.Color(0, 102, 0));
        totKelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totKelas.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 160, 120));

        jPanel7.setBackground(new java.awt.Color(102, 153, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Tanggal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel7.setForeground(new java.awt.Color(102, 102, 102));
        jPanel7.setAutoscrolls(true);

        totTanggal.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        totTanggal.setForeground(new java.awt.Color(0, 51, 153));
        totTanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totTanggal.setText("0");

        totHari.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        totHari.setForeground(new java.awt.Color(0, 51, 153));
        totHari.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totHari.setText("Hari");
        totHari.setToolTipText("Tanggal saat ini");
        totHari.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totHari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
            .addComponent(totTanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(totHari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totTanggal)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 160, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smk.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("SMK INSAN MANDIRI SETU BEKASI");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 42, 1130, 640));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-menu-30.png"))); // NOI18N
        jLabel2.setText(" Kepala Sekolah");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, -1));

        btmRekap.setBackground(new java.awt.Color(0, 102, 153));
        btmRekap.setForeground(new java.awt.Color(0, 0, 0));
        btmRekap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report hitam 64_1.png"))); // NOI18N
        btmRekap.setText("REKAP RAPORT");
        btmRekap.setToolTipText("Rekap rapot");
        btmRekap.setActionCommand("Siswa");
        btmRekap.setColorText(new java.awt.Color(0, 0, 0));
        btmRekap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btmRekap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btmRekap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btmRekap.setMargin(new java.awt.Insets(2, 82, 2, 14));
        btmRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmRekapActionPerformed(evt);
            }
        });
        jPanel4.add(btmRekap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 220, 60));

        btmSiswa.setBackground(new java.awt.Color(0, 102, 153));
        btmSiswa.setForeground(new java.awt.Color(0, 0, 0));
        btmSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/studentmeets_4873.png"))); // NOI18N
        btmSiswa.setText("SISWA");
        btmSiswa.setToolTipText("Siswa");
        btmSiswa.setActionCommand("Siswa");
        btmSiswa.setColorText(new java.awt.Color(0, 0, 0));
        btmSiswa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btmSiswa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btmSiswa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btmSiswa.setMargin(new java.awt.Insets(2, 82, 2, 14));
        btmSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSiswaActionPerformed(evt);
            }
        });
        jPanel4.add(btmSiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 220, 60));

        btmMapel.setBackground(new java.awt.Color(0, 102, 153));
        btmMapel.setForeground(new java.awt.Color(0, 0, 0));
        btmMapel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book.png"))); // NOI18N
        btmMapel.setText("MAPEL");
        btmMapel.setToolTipText("Mata Pelajaran");
        btmMapel.setActionCommand("Siswa");
        btmMapel.setColorText(new java.awt.Color(0, 0, 0));
        btmMapel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btmMapel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btmMapel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btmMapel.setMargin(new java.awt.Insets(2, 82, 2, 14));
        btmMapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmMapelActionPerformed(evt);
            }
        });
        jPanel4.add(btmMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 220, 60));

        btmGuru.setBackground(new java.awt.Color(0, 102, 153));
        btmGuru.setForeground(new java.awt.Color(0, 0, 0));
        btmGuru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guru.png"))); // NOI18N
        btmGuru.setText("GURU");
        btmGuru.setToolTipText("Guru");
        btmGuru.setActionCommand("Siswa");
        btmGuru.setColorText(new java.awt.Color(0, 0, 0));
        btmGuru.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btmGuru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btmGuru.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btmGuru.setMargin(new java.awt.Insets(2, 82, 2, 14));
        btmGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmGuruActionPerformed(evt);
            }
        });
        jPanel4.add(btmGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 220, 60));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 220, 640));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txmUser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txmUser2.setForeground(new java.awt.Color(255, 255, 255));
        txmUser2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txmUser2.setText("User");
        jPanel1.add(txmUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 100, -1));

        logUser3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_user_male_circle_32px.png"))); // NOI18N
        logUser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logUser3MouseClicked(evt);
            }
        });
        jPanel1.add(logUser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1365, 40));

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setText("Menu");

        jMenuItem1.setText("Rekap Nilai Rapot");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Lihat Siswa");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Lihat Guru");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Lihat Mapel");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuKeluar.setText("Keluar");
        jMenuKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuKeluarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuKeluar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Kelola");

        jMenuProfil.setText("Profil");
        jMenuProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuProfilActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuProfil);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tentang");

        jMenuBantuan.setText("Bantuan");
        jMenuBantuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBantuanActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuBantuan);

        jMenuTentang.setText("Tentang");
        jMenuTentang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTentangActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuTentang);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1376, 739));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logUser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logUser3MouseClicked
        // User 1 klik
        pmnLog.setVisible(true);
    }//GEN-LAST:event_logUser3MouseClicked

    private void logUser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logUser2MouseClicked
        // User 2 klik
        pmnLog.setVisible(false);
    }//GEN-LAST:event_logUser2MouseClicked

    private void btmSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSiswaActionPerformed
        // siswa
        mp.makePreview("RSiswa");
    }//GEN-LAST:event_btmSiswaActionPerformed

    private void jMenuKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuKeluarActionPerformed
        // Keluar Aplikasi
        int answer = JOptionPane.showConfirmDialog(null, "apakah anda yakin?"," Keluar",JOptionPane.OK_OPTION);
        if (answer==JOptionPane.OK_OPTION) {
           System.exit(0); 
        }
    }//GEN-LAST:event_jMenuKeluarActionPerformed

    private void jMenuTentangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTentangActionPerformed
        // Tentang
        ab.setVisible(true);
    }//GEN-LAST:event_jMenuTentangActionPerformed

    private void jMenuBantuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBantuanActionPerformed
        // Bantuan
        hb.setVisible(true);
    }//GEN-LAST:event_jMenuBantuanActionPerformed

    private void txmKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txmKeluarMouseClicked
        // Log keluar
        this.dispose();
        log.setVisible(true);
    }//GEN-LAST:event_txmKeluarMouseClicked

    private void txmTentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txmTentangMouseClicked
        // Log Tentang
        pmnLog.setVisible(false);
        ab.setVisible(true);
    }//GEN-LAST:event_txmTentangMouseClicked

    private void txmProfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txmProfilMouseClicked
        // Log profil
        pg.getUser().setText(txmUser2.getText());
        pg.setVisible(true);
    }//GEN-LAST:event_txmProfilMouseClicked

    private void btmRekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmRekapActionPerformed
        // Rekap
        gr.setVisible(true);
    }//GEN-LAST:event_btmRekapActionPerformed

    private void btmMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmMapelActionPerformed
        // mapel
        mp.makePreview("RMapel");
    }//GEN-LAST:event_btmMapelActionPerformed

    private void btmGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmGuruActionPerformed
        // guru
        mp.makePreview("RGuru");
    }//GEN-LAST:event_btmGuruActionPerformed

    private void jMenuProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuProfilActionPerformed
        // profil
        pg.getUser().setText(txmUser2.getText());
        pg.setVisible(true);
    }//GEN-LAST:event_jMenuProfilActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        gr.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        mp.makePreview("RSiswa");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        mp.makePreview("RGuru");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        mp.makePreview("RMapel");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DBKepsek().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonIconI btmGuru;
    private rojerusan.RSButtonIconI btmMapel;
    private rojerusan.RSButtonIconI btmRekap;
    private rojerusan.RSButtonIconI btmSiswa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuBantuan;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuKeluar;
    private javax.swing.JMenuItem jMenuProfil;
    private javax.swing.JMenuItem jMenuTentang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel logUser2;
    private javax.swing.JLabel logUser3;
    private javax.swing.JPanel pmnLog;
    private javax.swing.JLabel totGuru;
    private javax.swing.JLabel totHari;
    private javax.swing.JLabel totKelas;
    private javax.swing.JLabel totSiswa;
    private javax.swing.JLabel totTanggal;
    private javax.swing.JLabel txmKeluar;
    private javax.swing.JLabel txmProfil;
    private javax.swing.JLabel txmTentang;
    private javax.swing.JLabel txmUser2;
    // End of variables declaration//GEN-END:variables
}
