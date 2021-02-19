/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPII_ders1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BurakGulsen
 */
public class UnvanİslemleriGuncelleme extends javax.swing.JFrame {

    /**
     * Creates new form UnvanİslemleriGuncelleme
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BPII_ders1PU");
    DefaultTableModel dtm = new DefaultTableModel();

    public UnvanİslemleriGuncelleme() {
        initComponents();
        tabloUnvan.setModel(dtm);
        dtm.setColumnIdentifiers(new String[]{"Unvan Sicil Numarası ", "Unvan"});
        tabloyaYansıt();
    }

    public boolean Unvankntrl() {
        boolean VarMı = false;
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT u FROM Unvan u");
        List<Unvan> unvanlar = q1.getResultList();
        for (Unvan u : unvanlar) {
            if (u.getUnvanId().equals(Integer.parseInt(txtID.getText()))) {
                VarMı = true;
            }
        }

        return VarMı;
    }

    boolean desenKontrol(String aranan, String desen) {
        Pattern p = Pattern.compile(desen);
        Matcher m = p.matcher(aranan);
        return m.find();
    }

    public void tabloyaYansıt() {
        dtm.setRowCount(0);
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            s = con.createStatement();
            rs = s.executeQuery("select * from unvan");
            while (rs.next()) {
                dtm.addRow(new String[]{rs.getString("UNVAN_ID"), rs.getString("UNVANI")});

            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelBilgileriGnclle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void boslukKontrol() {
        if (txtID.getText().equals("") || txtUnvan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lütfen boş kalan kısımları doldurun", "UYARI!", JOptionPane.ERROR_MESSAGE);
        } else {
            guncelle();
        }
    }

    public boolean unvanDefaultKntrl() {
        boolean dogruMu = false;
        /*EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT u FROM Unvan u");
        List<Unvan> unvanlar = q1.getResultList();
        for (int i = 0; i < unvanlar.size(); i++) {
            dogruMu = false;
            if (unvanlar.get(i).getUnvanId().equals(1)) {
                dogruMu = true;

            }

        }*/
        if(Integer.parseInt(txtID.getText())==1){
           dogruMu=true;
       }
        return dogruMu;
    }

    public boolean unvanDefaultKntrl2() {
        boolean dogruMu = false;
        /*EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT u FROM Unvan u");
        List<Unvan> unvanlar = q1.getResultList();
        for (int i = 0; i < unvanlar.size(); i++) {
            dogruMu = false;
            if (unvanlar.get(i).getUnvanId().equals(2)) {
                dogruMu = true;

            }

        }*/
        if(Integer.parseInt(txtID.getText())==2){
           dogruMu=true;
       }
        return dogruMu;
    }

    public boolean unvanDefaultKntrl3() {
        boolean dogruMu = false;
       /* EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT u FROM Unvan u");
        List<Unvan> unvanlar = q1.getResultList();
        for (int i = 0; i < unvanlar.size(); i++) {
            dogruMu = false;
            if (unvanlar.get(i).getUnvanId().equals(3)) {
                dogruMu = true;

            }

        }*/
       if(Integer.parseInt(txtID.getText())==3){
           dogruMu=true;
       }
        return dogruMu;
    }

    public boolean unvanDefaultKntrl4() {
        boolean dogruMu = false;
        /*EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT u FROM Unvan u");
        List<Unvan> unvanlar = q1.getResultList();
        for (int i = 0; i < unvanlar.size(); i++) {
            dogruMu = false;
            if (unvanlar.get(i).getUnvanId().equals(4)) {
                dogruMu = true;

            }

        }*/
      if(Integer.parseInt(txtID.getText())==4){
           dogruMu=true;
       }
        return dogruMu;
    }

    public boolean prsnlUnvanKontrol() {
        boolean varMı = false;
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT p FROM Personel_1 p");
        List<Personel_1> personeller = q1.getResultList();
        for (Personel_1 p : personeller) {
            if (p.getUnvanId().equals(Integer.parseInt(txtID.getText()))) {
                varMı = true;
            }

        }
        return varMı;
    }

    public void guncelle() {
        Connection con = null;
        PreparedStatement sUpdate = null;
        PreparedStatement sInsert = null;
        try {
            if (Unvankntrl() == true) {
                if (prsnlUnvanKontrol() == true || unvanDefaultKntrl() == true || unvanDefaultKntrl2() == true || unvanDefaultKntrl3() == true
                        || unvanDefaultKntrl4() == true) {
                    JOptionPane.showMessageDialog(null, "Bu unvan Güncellenemez!", "UYARI", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (desenKontrol(txtUnvan.getText(), "[a-zA-z]")) {

                        con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
                        con.setAutoCommit(false);
                        String id = txtID.getText();
                        String sqlUpdate = "UPDATE Unvan SET unvani=? WHERE unvan_id=?";
                        sUpdate = con.prepareStatement(sqlUpdate);
                        sUpdate.setString(1, txtUnvan.getText());
                        sUpdate.setInt(2, Integer.parseInt(id));
                        System.out.println(sqlUpdate);
                        sUpdate.executeUpdate();
                        con.commit();
                        tabloyaYansıt();
                        JOptionPane.showMessageDialog(null, "Başarıyla Güncellendi!");

                    } else {
                        JOptionPane.showMessageDialog(null, "HATALI YAZIM!", "UYARI", JOptionPane.ERROR_MESSAGE);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Lütfen geçerli sicil numarası girin!", "UYARI", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UnvanIslemlerEkleme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lütfen geçerli sicil numarası girin!", "UYARI", JOptionPane.ERROR_MESSAGE);
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

        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        silBtn = new javax.swing.JButton();
        eklebtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabloUnvan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtUnvan = new javax.swing.JTextField();
        geriBtn = new javax.swing.JButton();
        guncelleBtn = new javax.swing.JButton();

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText(" BÖLÜMÜNÜN DOLDURULMASI YETERLİDİR");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("UNVAN SİLMEK İÇİN UNVAN ID");

        silBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        silBtn.setText("SİL");
        silBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silBtnActionPerformed(evt);
            }
        });

        eklebtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        eklebtn.setText("EKLE");
        eklebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eklebtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        tabloUnvan.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tabloUnvan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabloUnvan);

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" SİCİL NUMARASINI YAZIP İSTEDİĞİNİZ UNVANIN İSMİNİ YAZIN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("UNVAN ID             :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("UNVAN                  :");

        txtID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtUnvan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        geriBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        geriBtn.setText("GERİ");
        geriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriBtnActionPerformed(evt);
            }
        });

        guncelleBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        guncelleBtn.setText("GÜNCELLE");
        guncelleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guncelleBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUnvan, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtID)))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(guncelleBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(geriBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guncelleBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUnvan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eklebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eklebtnActionPerformed

    }//GEN-LAST:event_eklebtnActionPerformed

    private void geriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriBtnActionPerformed
        UnvanİslemleriMenu unv = new UnvanİslemleriMenu();
        unv.setVisible(true);
        unv.setLocationRelativeTo(null);
        unv.pack();
        unv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_geriBtnActionPerformed

    private void silBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silBtnActionPerformed

    }//GEN-LAST:event_silBtnActionPerformed

    private void guncelleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guncelleBtnActionPerformed
        boslukKontrol();
    }//GEN-LAST:event_guncelleBtnActionPerformed

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
            java.util.logging.Logger.getLogger(UnvanİslemleriGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UnvanİslemleriGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UnvanİslemleriGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UnvanİslemleriGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UnvanİslemleriGuncelleme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eklebtn;
    private javax.swing.JButton geriBtn;
    private javax.swing.JButton guncelleBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton silBtn;
    private javax.swing.JTable tabloUnvan;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtUnvan;
    // End of variables declaration//GEN-END:variables
}
