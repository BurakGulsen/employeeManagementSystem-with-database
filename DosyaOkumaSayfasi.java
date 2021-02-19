/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPII_ders1;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author BurakGulsen
 */
public class DosyaOkumaSayfasi extends javax.swing.JFrame {

    /**
     * Creates new form DosyaOkumaSayfasi
     */
    DefaultListModel dlm = new DefaultListModel();

    public DosyaOkumaSayfasi() {
        initComponents();
        listDosya.setModel(dlm);

    }

    public boolean okunabilirMiTxt() {
        boolean okunurMu = false;
        try {

            Path p = Paths.get("c:", "Personeller", txtDosya.getText() + ".txt");
            if (Files.size(p) != 0) {
                okunurMu = true;
            }

        } catch (IOException ex) {
            Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return okunurMu;
    }

    public boolean okunabilirMiJpg() {
        boolean okunurMu = false;
        try {

            Path p = Paths.get("c:", "Personeller", txtDosya.getText() + ".jpg");
            if (Files.size(p) != 0) {
                okunurMu = true;
            }

        } catch (IOException ex) {
            Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return okunurMu;
    }

    public boolean okunabilirMiObj() {
        boolean okunurMu = false;
        try {

            Path p = Paths.get("c:", "Personeller", txtDosya.getText() + ".obj");
            if (Files.size(p) != 0) {
                okunurMu = true;
            }

        } catch (IOException ex) {
            Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return okunurMu;
    }

    public boolean dosyaKontrolJpg() {
        boolean varMi = false;
        try {

            Path adres = Paths.get("c:\\Personeller");
            DirectoryStream<Path> dosyalar = Files.newDirectoryStream(adres);
            for (Path dosya : dosyalar) {
                if (dosya.getFileName().toString().equals(txtDosya.getText() + ".jpg")) {
                    varMi = true;

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varMi;
    }

    public boolean dosyaKontrolTxt() {
        boolean varMi = false;
        try {

            Path adres = Paths.get("c:\\Personeller");
            DirectoryStream<Path> dosyalar = Files.newDirectoryStream(adres);
            for (Path dosya : dosyalar) {
                if (dosya.getFileName().toString().equals(txtDosya.getText() + ".txt")) {
                    varMi = true;

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varMi;
    }

    public boolean dosyaKontrolObj() {
        boolean varMi = false;
        try {

            Path adres = Paths.get("c:\\Personeller");
            DirectoryStream<Path> dosyalar = Files.newDirectoryStream(adres);
            for (Path dosya : dosyalar) {
                if (dosya.getFileName().toString().equals(txtDosya.getText() + ".obj")) {

                    varMi = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varMi;
    }

    public void okuTxt() {
        ObjectInputStream ois = null;
        try {
            String dosya = "c:\\Personeller" + "\\" + txtDosya.getText() + ".txt";;
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dosya)));

            try {
                while (true) {
                    Personel_1 p = (Personel_1) (ois.readObject());
                    dlm.addElement(p.getAdi() + " " + p.getSoyadi());
                }

            } catch (EOFException e) {

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ois.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void okuJpg() {
        ObjectInputStream ois = null;
        try {
            String dosya = "c:\\Personeller" + "\\" + txtDosya.getText() + ".jpg";;
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dosya)));
            try {
                while (true) {
                    Personel_1 p = (Personel_1) (ois.readObject());
                    dlm.addElement(p.getAdi() + " " + p.getSoyadi());
                }
            } catch (EOFException e) {

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ois.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void okuObj() {
        ObjectInputStream ois = null;
        try {
            String dosya = "c:\\Personeller" + "\\" + txtDosya.getText() + ".obj";
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dosya)));
            try {
                while (true) {
                    Personel_1 p = (Personel_1) (ois.readObject());
                    dlm.addElement(p.getAdi() + " " + p.getSoyadi());
                }
            } catch (EOFException e) {

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ois.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(DosyayaEklemeSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDosya = new javax.swing.JList<>();
        txtDosya = new javax.swing.JTextField();
        radioTxt = new javax.swing.JRadioButton();
        radioObj = new javax.swing.JRadioButton();
        radioJpg = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LÜTFEN DOSYA ADINI GİRİP DOSYA TÜRÜNÜ SEÇİN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        listDosya.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        listDosya.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listDosya);

        txtDosya.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDosya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDosyaActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioTxt);
        radioTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        radioTxt.setText(".txt");

        buttonGroup1.add(radioObj);
        radioObj.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        radioObj.setText(".obj");

        buttonGroup1.add(radioJpg);
        radioJpg.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        radioJpg.setText(".jpg");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("OKU");
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Lütfen dosya adını yazarken büyük/küçük ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText(" harf kullanımına dikkat ediniz");

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
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(txtDosya, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(radioTxt)
                        .addGap(49, 49, 49)
                        .addComponent(radioObj)
                        .addGap(42, 42, 42)
                        .addComponent(radioJpg)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDosya, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioTxt)
                    .addComponent(radioObj)
                    .addComponent(radioJpg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
        if (radioJpg.isSelected()) {
            if (dosyaKontrolJpg() == true) {
                if (okunabilirMiJpg() == true) {
                    okuJpg();
                } else {
                    JOptionPane.showMessageDialog(null, "Dosyanın içi boş!", "UYARI!", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Böyle bir dosya bulunmamaktadır!", "UYARI!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (radioObj.isSelected()) {
            if (dosyaKontrolObj() == true) {
                if (okunabilirMiObj() == true) {
                    okuObj();
                } else {
                    JOptionPane.showMessageDialog(null, "Dosyanın içi boş!", "UYARI!", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Böyle bir dosya bulunmamaktadır!", "UYARI!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (radioTxt.isSelected()) {
            if (dosyaKontrolTxt() == true) {
                if (okunabilirMiTxt() == true) {
                    okuTxt();
                } else {
                    JOptionPane.showMessageDialog(null, "Dosyanın içi boş!", "UYARI!", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Böyle bir dosya bulunmamaktadır!", "UYARI!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lütfen bir seçim yapın!", "UYARI!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDosyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDosyaActionPerformed

    }//GEN-LAST:event_txtDosyaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DosyaİslemleriMenu dim = new DosyaİslemleriMenu();
        dim.setVisible(true);
        dim.setLocationRelativeTo(null);
        dim.pack();
        dim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      dlm.removeAllElements();
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
            java.util.logging.Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DosyaOkumaSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DosyaOkumaSayfasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listDosya;
    private javax.swing.JRadioButton radioJpg;
    private javax.swing.JRadioButton radioObj;
    private javax.swing.JRadioButton radioTxt;
    private javax.swing.JTextField txtDosya;
    // End of variables declaration//GEN-END:variables
}
