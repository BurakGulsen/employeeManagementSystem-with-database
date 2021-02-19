/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPII_ders1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BurakGulsen
 */
public class PersonelEklemeSayfası extends javax.swing.JFrame {

    Personel personel;
    DefaultTableModel dtm = new DefaultTableModel();
    int Unvan = 4;
    String[] a = {"Erkek", "Kadın"};
    String ID = "1010101";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BPII_ders1PU");
    EntityManager em = emf.createEntityManager();

    public PersonelEklemeSayfası() {
        initComponents();

        tablo.setModel(dtm);
        dtm.setColumnIdentifiers(new String[]{"Sicil No", "Adı", "Soyadı", "Yaş", "Cinsiyet", "Ünvan"});
        combo.addItem("Erkek");
        combo.addItem("Kadın");

    }
    Query q1 = em.createQuery("SELECT p FROM PersonelGrsCks p");
    List<PersonelGrsCks> personeller = q1.getResultList();
    
 
    public int cikanEnBuyukID() {
        Query q1 = em.createQuery("SELECT p FROM PersonelGrsCks p");
        List<PersonelGrsCks> personeller = q1.getResultList();
        int prsID = 0;
        for (PersonelGrsCks prs : personeller) {
             if(prs.getPersonelId()>prsID){
                prsID=prs.getPersonelId();
            }

        }
   return prsID; }
    public int enBuyukID(){
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        int prsID=0;
        for (Personel_1 p: personeller) {
            if(p.getPersonelId()>prsID){
                prsID=p.getPersonelId();
            }
            
        }
    return prsID;}
 
    public void sonKontrol() {
        Query q1 = em.createQuery("SELECT p FROM PersonelGrsCks p");
        List<PersonelGrsCks> personeller = q1.getResultList();
        int persID = 0;
        if (personeller.size() == 0) {
            sicilNumKontrol();
        } else {
            if(cikanEnBuyukID()> enBuyukID()){
                ID= String.valueOf(cikanEnBuyukID()+10);
            }else if(cikanEnBuyukID()< enBuyukID()){
                sicilNumKontrol();
            }
            

        }
    }

    public String sicilNumKontrol() {
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        int prsID = 0;
        for (Personel_1 prs : personeller) {
            if (prs.getPersonelId() > prsID) {
                prsID = prs.getPersonelId();
                ID = String.valueOf(prs.getPersonelId() + 10);

            }

        }
       return ID; }

    public void yasKontrol() {
        int sayi = Integer.valueOf(txtYas.getText());
        if (sayi > 64 || sayi < 18) {
            JOptionPane.showMessageDialog(null, "Lütfen geçerli bir yaş girin", "UYARI!", JOptionPane.ERROR_MESSAGE);
            txtYas.setText("");
        } else {
            unvanEkleme();
        }

    }

    public void UnvanSecimKntrl() {
        if (radioIsci.isSelected() == false && radioMuh.isSelected() == false && radioTeknik.isSelected() == false
                && radioYntc.isSelected() == false && txtUnvn.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Lütfen bir unvan seçiniz veya giriniz.", "UYARI", JOptionPane.ERROR_MESSAGE);
        }
    }
    boolean varMı = false;

 
    public boolean Unvankntrl() {
          boolean VarMı = false;
        Query q1 = em.createQuery("SELECT u FROM Unvan u");
        List<Unvan> unvanlar = q1.getResultList();
        for (Unvan u : unvanlar) {
            if (u.getUnvani().equals(txtUnvn.getText())) {
                VarMı = true;
            }
        }

        return VarMı;
    }

    public void unvanEkleme() {
        SimpleDateFormat bicim3 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        GregorianCalendar gcalender = new GregorianCalendar();
        String tarih = bicim3.format(gcalender.getTime());

        String unvan = " ";
        String maas = "";

        if (txtUnvn.getText().isEmpty() == true) {
            if (radioIsci.isSelected()) {
                unvan = radioIsci.getText();
                Unvan = 1;
                em.getTransaction().begin();
                sonKontrol();

                Personel_1 personel = new Personel_1();

                personel.setPersonelId(Integer.parseInt(ID));
                personel.setAdi(txtAdi.getText());
                personel.setSoyadi(txtSoyadi.getText());
                personel.setYas(Integer.parseInt(txtYas.getText()));
                personel.setCinsiyet(combo.getSelectedItem().toString());
                personel.setUnvanId(Unvan);
                personel.setMaas(Integer.parseInt(txtMaas.getText()));
                personel.setGiris(tarih);
                personel.setCikis("-");
                dtm.addRow(new String[]{ID, txtAdi.getText(), txtSoyadi.getText(), txtYas.getText(), combo.getSelectedItem().toString(), unvan});
                JOptionPane.showMessageDialog(null, "Başarıyla Eklendi");
                em.persist(personel);

                em.getTransaction().commit();

            } else if (radioMuh.isSelected()) {
                unvan = radioMuh.getText();
                Unvan = 2;

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                sonKontrol();
                Personel_1 personel = new Personel_1();
                personel.setPersonelId(Integer.parseInt(ID));
                personel.setAdi(txtAdi.getText());
                personel.setSoyadi(txtSoyadi.getText());
                personel.setYas(Integer.parseInt(txtYas.getText()));
                personel.setCinsiyet(combo.getSelectedItem().toString());
                personel.setUnvanId(Unvan);
                personel.setMaas(Integer.parseInt(txtMaas.getText()));
                personel.setGiris(tarih);
                personel.setCikis("-");
                dtm.addRow(new String[]{ID, txtAdi.getText(), txtSoyadi.getText(), txtYas.getText(), combo.getSelectedItem().toString(), unvan});
                JOptionPane.showMessageDialog(null, "Başarıyla Eklendi");
                em.persist(personel);

                em.getTransaction().commit();

            } else if (radioTeknik.isSelected()) {
                unvan = radioTeknik.getText();
                Unvan = 3;

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                  sonKontrol();
                Personel_1 personel = new Personel_1();
                personel.setPersonelId(Integer.parseInt(ID));
                personel.setAdi(txtAdi.getText());
                personel.setSoyadi(txtSoyadi.getText());
                personel.setYas(Integer.parseInt(txtYas.getText()));
                personel.setCinsiyet(combo.getSelectedItem().toString());
                personel.setUnvanId(Unvan);
                personel.setMaas(Integer.parseInt(txtMaas.getText()));
                personel.setGiris(tarih);
                personel.setCikis("-");
                dtm.addRow(new String[]{ID, txtAdi.getText(), txtSoyadi.getText(), txtYas.getText(), combo.getSelectedItem().toString(), unvan});
                JOptionPane.showMessageDialog(null, "Başarıyla Eklendi");
                em.persist(personel);
                em.getTransaction().commit();

            } else if (radioYntc.isSelected()) {
                unvan = radioYntc.getText();
                Unvan = 4;

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
               sonKontrol();
                Personel_1 personel = new Personel_1();
                personel.setPersonelId(Integer.parseInt(ID));
                personel.setAdi(txtAdi.getText());
                personel.setSoyadi(txtSoyadi.getText());
                personel.setYas(Integer.parseInt(txtYas.getText()));
                personel.setCinsiyet(combo.getSelectedItem().toString());
                personel.setUnvanId(Unvan);
                personel.setMaas(Integer.parseInt(txtMaas.getText()));
                personel.setGiris(tarih);
                personel.setCikis("-");
                dtm.addRow(new String[]{ID, txtAdi.getText(), txtSoyadi.getText(), txtYas.getText(), combo.getSelectedItem().toString(), unvan});
                JOptionPane.showMessageDialog(null, "Başarıyla Eklendi");
                em.persist(personel);
                em.getTransaction().commit();

            }
        } else {
            radioIsci.setEnabled(false);
            radioMuh.setEnabled(false);
            radioTeknik.setEnabled(false);
            radioYntc.setEnabled(false);
            Query q1 = em.createQuery("SELECT u FROM Unvan u");
            List<Unvan> unvanlar = q1.getResultList();

            if (Unvankntrl() == true) {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                  sonKontrol();
                Personel_1 personel = new Personel_1();
                personel.setPersonelId(Integer.parseInt(ID));
                personel.setAdi(txtAdi.getText());
                personel.setSoyadi(txtSoyadi.getText());
                personel.setYas(Integer.parseInt(txtYas.getText()));
                personel.setCinsiyet(combo.getSelectedItem().toString());
                int Unvan = 0;
                for (Unvan u : unvanlar) {
                    if (txtUnvn.getText().equals(u.getUnvani())) {
                        Unvan = u.getUnvanId();
                    }
                }

                personel.setUnvanId(Unvan);
                personel.setMaas(Integer.parseInt(txtMaas.getText()));
                personel.setGiris(tarih);
                personel.setCikis("-");
                dtm.addRow(new String[]{ID, txtAdi.getText(), txtSoyadi.getText(), txtYas.getText(), combo.getSelectedItem().toString(), txtUnvn.getText()});
                JOptionPane.showMessageDialog(null, "Başarıyla Eklendi");
                em.persist(personel);
                em.getTransaction().commit();
            } else {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                 sonKontrol();
                Unvan u = new Unvan();
                int Unvan = 0;
                int temp = 1;
                for (int i = 0; i < unvanlar.size(); i++) {
                    if (unvanlar.get(i).getUnvanId() == temp) {
                        temp++;
                        Unvan = temp;

                    }

                }
                u.setUnvanId(Unvan);
                u.setUnvani(txtUnvn.getText());
                Personel_1 personel = new Personel_1();
                personel.setPersonelId(Integer.parseInt(ID));
                personel.setAdi(txtAdi.getText());
                personel.setSoyadi(txtSoyadi.getText());
                personel.setYas(Integer.parseInt(txtYas.getText()));
                personel.setCinsiyet(combo.getSelectedItem().toString());
                personel.setUnvanId(Unvan);
                personel.setMaas(Integer.parseInt(txtMaas.getText()));
                personel.setGiris(tarih);
                personel.setCikis("-");
                dtm.addRow(new String[]{ID, txtAdi.getText(), txtSoyadi.getText(), txtYas.getText(), combo.getSelectedItem().toString(), txtUnvn.getText()});
                JOptionPane.showMessageDialog(null, "Başarıyla Eklendi");
                em.persist(personel);
                em.persist(u);
                em.getTransaction().commit();

            }
        }
    }

    public void boslukKontrol() {
        if (txtAdi.getText().equals("") || txtSoyadi.getText().equals("") || txtYas.getText().equals("") || txtMaas.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Lütfen boş kalan kısımları doldurun", "UYARI!", JOptionPane.ERROR_MESSAGE);;
        } else {
            yasKontrol();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        label1 = new java.awt.Label();
        txtUnvan = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        UnvanGrup = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablo = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAdi = new javax.swing.JTextField();
        txtSoyadi = new javax.swing.JTextField();
        txtYas = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ekleBtn2 = new javax.swing.JButton();
        geriBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        radioIsci = new javax.swing.JRadioButton();
        radioMuh = new javax.swing.JRadioButton();
        radioTeknik = new javax.swing.JRadioButton();
        lblYasUyarı = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        radioYntc = new javax.swing.JRadioButton();
        txtUnvn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaas = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Adı   :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Yaş       :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Soyadı :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cinsiyet  :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Çalıştığı Departman :");

        jTable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Kontrol Et");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label1.setText("label1");
        label1.getAccessibleContext().setAccessibleName("Ünvan : ");
        label1.getAccessibleContext().setAccessibleParent(label1);

        txtUnvan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        tablo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane3.setViewportView(tablo);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Adı                 :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Soyadı           :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Yaş                :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cinsiyet        :");

        txtAdi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtAdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdiActionPerformed(evt);
            }
        });

        txtSoyadi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtYas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtYas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtYasKeyPressed(evt);
            }
        });

        combo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("  LÜTFEN AŞAĞIDAKİ BOŞLUKLARI DOLDURUN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        ekleBtn2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ekleBtn2.setText("EKLE");
        ekleBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ekleBtn2ActionPerformed(evt);
            }
        });

        geriBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        geriBtn.setText("GERİ");
        geriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriBtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Ünvan          :");

        radioIsci.setBackground(new java.awt.Color(153, 153, 153));
        UnvanGrup.add(radioIsci);
        radioIsci.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioIsci.setText("İşçi");
        radioIsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIsciActionPerformed(evt);
            }
        });

        radioMuh.setBackground(new java.awt.Color(153, 153, 153));
        UnvanGrup.add(radioMuh);
        radioMuh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioMuh.setText("Mühendis");

        radioTeknik.setBackground(new java.awt.Color(153, 153, 153));
        UnvanGrup.add(radioTeknik);
        radioTeknik.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioTeknik.setText("Teknisyen");

        lblYasUyarı.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblYasUyarı.setForeground(new java.awt.Color(204, 0, 0));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("TEMİZLE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        radioYntc.setBackground(new java.awt.Color(153, 153, 153));
        UnvanGrup.add(radioYntc);
        radioYntc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioYntc.setText("Yönetici");
        radioYntc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioYntcActionPerformed(evt);
            }
        });

        txtUnvn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Ekstra Ünvan Eklemek için bu alanı kullanınız.");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Maaş            :");

        txtMaas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoyadi, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(radioIsci, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioMuh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioTeknik, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioYntc, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUnvn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtMaas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtYas, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblYasUyarı, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 52, Short.MAX_VALUE))
                            .addComponent(ekleBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoyadi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtYas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblYasUyarı, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioIsci)
                    .addComponent(radioMuh)
                    .addComponent(radioTeknik)
                    .addComponent(radioYntc)
                    .addComponent(txtUnvn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ekleBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ekleBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleBtn2ActionPerformed
        try {
            boslukKontrol();
            UnvanSecimKntrl();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lütfen geçerli bir maaş girin", "UYARI!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ekleBtn2ActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed

    }//GEN-LAST:event_comboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    private void geriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriBtnActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.pack();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();


    }//GEN-LAST:event_geriBtnActionPerformed

    private void radioIsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIsciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioIsciActionPerformed

    private void txtYasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYasKeyPressed
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtYas.setEditable(false);
            lblYasUyarı.setText("Lütfen sadece sayı giriniz");
        } else {
            txtYas.setEditable(true);
        }


    }//GEN-LAST:event_txtYasKeyPressed

    private void txtAdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdiActionPerformed

    }//GEN-LAST:event_txtAdiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int satir = dtm.getRowCount();
        for (int i = satir - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void radioYntcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioYntcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioYntcActionPerformed

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
            java.util.logging.Logger.getLogger(PersonelEklemeSayfası.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonelEklemeSayfası.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonelEklemeSayfası.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonelEklemeSayfası.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonelEklemeSayfası().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup UnvanGrup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton ekleBtn2;
    private javax.swing.JButton geriBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private java.awt.Label label1;
    private javax.swing.JLabel lblYasUyarı;
    private javax.swing.JRadioButton radioIsci;
    private javax.swing.JRadioButton radioMuh;
    private javax.swing.JRadioButton radioTeknik;
    private javax.swing.JRadioButton radioYntc;
    private javax.swing.JTable tablo;
    private javax.swing.JTextField txtAdi;
    private javax.swing.JTextField txtMaas;
    private javax.swing.JTextField txtSoyadi;
    private javax.swing.JTextField txtUnvan;
    private javax.swing.JTextField txtUnvn;
    private javax.swing.JTextField txtYas;
    // End of variables declaration//GEN-END:variables
}
