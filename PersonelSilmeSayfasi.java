/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPII_ders1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BurakGulsen
 */
public class PersonelSilmeSayfasi extends javax.swing.JFrame {

    Personel personel;

    /**
     * Creates new form PersonelSilmeSayfasi
     */
    DefaultListModel dlm = new DefaultListModel();
    DefaultTableModel dtm = new DefaultTableModel();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BPII_ders1PU");

    public PersonelSilmeSayfasi() {
        initComponents();
        TabloSil.setModel(dtm);
        dtm.setColumnIdentifiers(new String[]{"Sicil No", "Adı", "Soyadı", "Yaş", "Cinsiyet", "Ünvan"});
        listSil.setModel(dlm);
        personelGoster2();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    public boolean personelSorguKontrl() {
        boolean varMı = false;
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        for (Personel_1 prs : personeller) {
            if (txtSilID.getText().equals(prs.getPersonelId().toString())) {
                varMı = true;
            }

        }
        return varMı;
    }
 

    public void personelCikar() {
        if (personelSorguKontrl() == true) {
            sil();

        } else {
            JOptionPane.showMessageDialog(null, "Lütfen geçerli bir sicil numarası  girin", "UYARI", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean personelKontrol() {
        boolean varMı = true;
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        if (personeller.size() == 0) {
            varMı = false;
        }
        return varMı;
    }
     public void personelGoster2(){
         dtm.setRowCount(0);
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            s = con.createStatement();
            rs = s.executeQuery("select * from Personel");
            while (rs.next()) {
                dtm.addRow(new String[]{rs.getString("personel_id"), rs.getString("adi"), rs.getString("soyadi"), rs.getString("Yas"), rs.getString("Cinsiyet"), rs.getString("UNVAN_ID")});

            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelBilgileriGnclle.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void personelGoster() {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        if (personeller.size() == 0) {
            int satir = dtm.getRowCount();
            for (int i = satir - 1; i >= 0; i--) {
                dtm.removeRow(i);
            }

            JOptionPane.showMessageDialog(null, "Şirkette Personel Bulunmamaktadır", "UYARI", JOptionPane.ERROR_MESSAGE);

        } else {
            dtm.setRowCount(0);

            for (Personel_1 prs : personeller) {
                dtm.addRow(new String[]{prs.getPersonelId().toString(), prs.getAdi(), prs.getSoyadi(), prs.getYas().toString(), prs.getCinsiyet(), prs.getUnvanId().toString()});

            }
        }
    }

    public void sil() {
        SimpleDateFormat bicim3 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        GregorianCalendar gcalender = new GregorianCalendar();
        String tarih = bicim3.format(gcalender.getTime());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PersonelGrsCks personel = new PersonelGrsCks();
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
        q1.setParameter("personelId", Integer.parseInt(txtSilID.getText()));
        List<Personel_1> personeller = q1.getResultList();
        for (Personel_1 prs : personeller) {
            personel.setPersonelGiris(prs.getGiris());
            personel.setAdi(prs.getAdi());
            personel.setSoyadi(prs.getSoyadi());
            personel.setYas(prs.getYas());
            personel.setCinsiyet(prs.getCinsiyet());
            personel.setUnvanId(prs.getUnvanId());
        }
        personel.setPersonelId(Integer.parseInt(txtSilID.getText()));
        personel.setPersonelCikis(tarih);
        em.persist(personel);
        Query q = em.createQuery("delete from Personel_1 p where p.personelId=:pPersonelId");
        q.setParameter("pPersonelId", Integer.parseInt(txtSilID.getText()));
        q.executeUpdate();
        em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "Personel başarıyla çıkarıldı");
        personelGoster2();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabloSil = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSil = new javax.swing.JList<>();
        btnGoster = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSilID = new javax.swing.JTextField();
        btnCikar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabloSil = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        tabloSil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tabloSil.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabloSil);

        listSil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        listSil.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listSil);

        btnGoster.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGoster.setText("ŞU ANKİ PERSONELİ GÖSTER");
        btnGoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGosterActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        txtSilID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btnCikar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCikar.setText("PERSONELİ ÇIKAR");
        btnCikar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCikarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  LÜTFEN BİR SİCİL NUMARASI GİRİNİZ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        TabloSil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TabloSil.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(TabloSil);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("GERİ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("TEMİZLE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSilID, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCikar, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSilID, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCikar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCikarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikarActionPerformed
        if (personelKontrol() == true) {
            personelCikar();
        } else {
            JOptionPane.showMessageDialog(null, "Şirkette Personel Bulunmamaktadır", "UYARI", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnCikarActionPerformed

    private void btnGosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGosterActionPerformed

    }//GEN-LAST:event_btnGosterActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.pack();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int satir = dtm.getRowCount();
        for (int i = satir - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(PersonelSilmeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonelSilmeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonelSilmeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonelSilmeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonelSilmeSayfasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabloSil;
    public static javax.swing.JButton btnCikar;
    private javax.swing.JButton btnGoster;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listSil;
    private javax.swing.JTable tabloSil;
    private javax.swing.JTextField txtSilID;
    // End of variables declaration//GEN-END:variables
}
