/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPII_ders1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author BurakGulsen
 */
public class DosyaOlrkEklemeSayfasi extends javax.swing.JFrame {

    /**
     * Creates new form DosyaOlrkEklemeSayfasi
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BPII_ders1PU");

    public DosyaOlrkEklemeSayfasi() {
        initComponents();
        comboTur.addItem(".txt");
        comboTur.addItem(".obj");
        comboTur.addItem(".jpg");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     public boolean personelKontrol() {
        boolean varMı = false;
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        if (personeller.size() != 0) {
            varMı = true;
        }
        return varMı;
    }
    public boolean kontrol() {
        boolean varMi = false;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from Personel_1 p");
        List<Personel_1> personeller = q.getResultList();
        for (Personel_1 p : personeller) {
            if (p.getPersonelId().equals(Integer.parseInt(txtID.getText()))) {
                varMi = true;
            }

        }
        return varMi;
    }

    public void eklePersonelObj() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
        q.setParameter("personelId", Integer.parseInt(txtID.getText()));
        List<Personel_1> personeller = q.getResultList();

        if (dosyaKontrolObj() == false) {

            for (Personel_1 p : personeller) {
                try {
                    // TODO add your handling code here:
                    Path dosya = Paths.get("c:\\Personeller", p.getAdi() + " " + p.getSoyadi() + comboTur.getSelectedItem().toString());
                    Files.createFile(dosya);
                    JOptionPane.showMessageDialog(null, "Başarıyla Eklendi!");

                } catch (IOException ex) {
                    Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Zaten böyle bir dosya var!", "UYARI!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eklePersonelJpg() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
        q.setParameter("personelId", Integer.parseInt(txtID.getText()));
        List<Personel_1> personeller = q.getResultList();

        if (dosyaKontrolJpg() == false) {

            for (Personel_1 p : personeller) {
                try {
                    // TODO add your handling code here:
                    Path dosya = Paths.get("c:\\Personeller", p.getAdi() + " " + p.getSoyadi() + comboTur.getSelectedItem().toString());
                    Files.createFile(dosya);
                    JOptionPane.showMessageDialog(null, "Başarıyla Eklendi!");

                } catch (IOException ex) {
                    Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Zaten böyle bir dosya var!", "UYARI!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eklePersonelTxt() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
        q.setParameter("personelId", Integer.parseInt(txtID.getText()));
        List<Personel_1> personeller = q.getResultList();

        if (dosyaKontrolTxt() == false) {

            for (Personel_1 p : personeller) {
                try {
                    // TODO add your handling code here:
                    Path dosya = Paths.get("c:\\Personeller", p.getAdi() + " " + p.getSoyadi() + comboTur.getSelectedItem().toString());
                    Files.createFile(dosya);
                    JOptionPane.showMessageDialog(null, "Başarıyla Eklendi!");

                } catch (IOException ex) {
                    Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Zaten böyle bir dosya var!", "UYARI!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean dosyaKontrolTxt() {
        boolean varMi = false;
        try {

            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
            q.setParameter("personelId", Integer.parseInt(txtID.getText()));
            List<Personel_1> personeller = q.getResultList();
            Path p = Paths.get("c:\\Personeller");
            DirectoryStream<Path> adresler = Files.newDirectoryStream(p, "*.{txt}");
            for (Path path : adresler) {
                for (int i = 0; i < personeller.size(); i++) {
                    if (path.getFileName().toString().equals(personeller.get(i).getAdi() + " " + personeller.get(i).getSoyadi() + ".txt")) {
                        varMi = true;
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varMi;
    }

    public boolean dosyaKontrolJpg() {
        boolean varMi = false;
        try {

            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
            q.setParameter("personelId", Integer.parseInt(txtID.getText()));
            List<Personel_1> personeller = q.getResultList();
            Path p = Paths.get("c:\\Personeller");
            DirectoryStream<Path> adresler = Files.newDirectoryStream(p, "*.{jpg}");
            for (Path path : adresler) {
                for (int i = 0; i < personeller.size(); i++) {
                    if (path.getFileName().toString().equals(personeller.get(i).getAdi() + " " + personeller.get(i).getSoyadi() + ".jpg")) {
                        varMi = true;
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varMi;
    }

    public boolean dosyaKontrolObj() {
        boolean varMi = false;
        try {

            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT p FROM Personel_1 p WHERE p.personelId = :personelId");
            q.setParameter("personelId", Integer.parseInt(txtID.getText()));
            List<Personel_1> personeller = q.getResultList();
            Path p = Paths.get("c:\\Personeller");
            DirectoryStream<Path> adresler = Files.newDirectoryStream(p, "*.{obj}");
            for (Path path : adresler) {
                for (int i = 0; i < personeller.size(); i++) {
                    if (path.getFileName().toString().equals(personeller.get(i).getAdi() + " " + personeller.get(i).getSoyadi() + ".obj")) {
                        varMi = true;
                    }
                    System.out.println(path.getFileName().toString());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varMi;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboTur = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("TÜM PERSONELLERİ EKLE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("     LÜTFEN DOSYA TÜRÜNÜ SEÇTİKTEN SONRA EKLEMEK ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("      İSTEDİĞİNİZ PERSONELİN SİCİL NO'SUNU YAZIN ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        comboTur.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboTur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTurActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("EKLE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("GERİ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(comboTur, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboTur, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(txtID))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(personelKontrol()==true){
            if (kontrol() == true) {
                if (comboTur.getSelectedItem().toString().equals(".txt")) {
                    eklePersonelTxt();
                } else if (comboTur.getSelectedItem().toString().equals(".obj")) {
                    eklePersonelObj();
                } else if (comboTur.getSelectedItem().toString().equals(".jpg")) {
                    eklePersonelJpg();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lütfen geçerli sicil numarası girin!", "UYARI!", JOptionPane.ERROR_MESSAGE);
            }
            }else{
                 JOptionPane.showMessageDialog(null, "Şirkette Personel Bulunmamaktadır!", "UYARI!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "HATALI YAZIM!", "UYARI!", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboTurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTurActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EklemeİslemleriMenu dosya = new EklemeİslemleriMenu();
        dosya.setVisible(true);
        dosya.setLocationRelativeTo(null);
        dosya.pack();
        dosya.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DosyaOlrkEklemeSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DosyaOlrkEklemeSayfasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboTur;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
