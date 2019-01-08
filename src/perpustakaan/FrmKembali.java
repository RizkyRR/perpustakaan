/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author RIZKY RAHMADIANTO
 */
public class FrmKembali extends javax.swing.JFrame {

    /**
     * Creates new form FrmKembali
     */
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    public FrmKembali() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        Date today = Calendar.getInstance().getTime();
        jDateChooser_TglKembali.setDate(today);
        
        TampilDetail();
        DeletePeminjaman();
        reset();
        save();
    }
    
    private void TampilDetail(){
        String cari = jTextField_KodePeminjaman.getText();
        
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String select = "SELECT buku.buku_nama, peminjam.peminjam_nama, peminjaman.peminjaman_kode, peminjaman.tambahan FROM peminjaman INNER JOIN buku ON buku.kode_buku = peminjaman.buku_kode INNER JOIN peminjam ON peminjam.peminjam_kode = peminjaman.peminjam_kode LIKE '%"+cari+"%'";
            st  = con.createStatement();
            rs  = st.executeQuery(select);
                        
            //https://stackoverflow.com/questions/16013364/inner-join-with-3-tables-in-mysql
            while (rs.next()) {                
                jTextField_NamaPeminjaman.setText(rs.getString("peminjam_nama"));
                jTextField_JudulBuku.setText(rs.getString("buku_nama"));
                jTextArea_Tambahan.setText(rs.getString("tambahan"));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    private void DeletePeminjaman(){
        String kode = jTextField_KodePeminjaman.getText();
        
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "DELETE FROM `peminjaman` WHERE `peminjaman_kode` = ?";
            ps  = con.prepareStatement(sql);
            ps.setString(1, kode);
            
            ps.executeUpdate();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    private void reset(){
        jTextField_KodePeminjaman.setText("");
        jTextField_NamaPeminjaman.setText("");
        jTextField_JudulBuku.setText("");
        jTextArea_Tambahan.setText("");
    }
    
    private void save(){
        String inisial          = "DK";
        String kodeKembali      = jTextField_KodePengembalian.getText();
        String kodePeminjaman   = jTextField_KodePeminjaman.getText();
        String namaPeminjam     = jTextField_NamaPeminjaman.getText();
        String judulBuku        = jTextField_JudulBuku.getText();
        String tambahan         = jTextArea_Tambahan.getText();
        
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String select = "SELECT MAX(right(kembali_id, 3)) FROM pengembalian";
            st  = con.createStatement();
            rs  = st.executeQuery(select);
            
            while (rs.next()) {                
                if (rs.first() == false) {
                    jTextField_KodePengembalian.setText(inisial + "-" + "001");
                } else {
                    rs.last();
                    
                    int tambah   = rs.getInt(1)+1;
                    String angka = String.valueOf(tambah);
                    int pjgAngka = angka.length();
                    
                    for (int i = 0; i < 3 - pjgAngka; i++) {
                        angka = "0" + angka;
                    }
                    jTextField_KodePengembalian.setText(inisial + "-" + angka);
                }
            }
            
            String sql = "INSERT INTO `pengembalian`(`kembali_kode`, `kembali_nama`, `judul_buku`, `tgl_kembali`, `tambahan`) VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, kodeKembali);
            ps.setString(2, namaPeminjam);
            ps.setString(3, judulBuku);
            ps.setString(4, ((JTextField)jDateChooser_TglKembali.getDateEditor().getUiComponent()).getText());
            ps.setString(5, tambahan);
            
            if (kodePeminjaman.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode peminjam tidak boleh kosong !");
            } else {
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Buku sudah dikembalikan !", "Success", JOptionPane.INFORMATION_MESSAGE);
                DeletePeminjaman();
                this.dispose();
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_KodePengembalian = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_KodePeminjaman = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_NamaPeminjaman = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_JudulBuku = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser_TglKembali = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Tambahan = new javax.swing.JTextArea();
        jButton_Reset = new javax.swing.JButton();
        jButton_Cancel = new javax.swing.JButton();
        jButton_Save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 84, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PENGEMBALIAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Pengembalian");

        jTextField_KodePengembalian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_KodePengembalian.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kode Peminjaman");

        jTextField_KodePeminjaman.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_KodePeminjaman.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_KodePeminjamanKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Peminjaman");

        jTextField_NamaPeminjaman.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_NamaPeminjaman.setEnabled(false);
        jTextField_NamaPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NamaPeminjamanActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Judul Buku");

        jTextField_JudulBuku.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_JudulBuku.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggal Kembali");

        jDateChooser_TglKembali.setDateFormatString("yyyy-MM-dd");
        jDateChooser_TglKembali.setEnabled(false);
        jDateChooser_TglKembali.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tambahan");

        jTextArea_Tambahan.setColumns(20);
        jTextArea_Tambahan.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Tambahan);

        jButton_Reset.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Reset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Reset.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Reset.setText("Reset");
        jButton_Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ResetActionPerformed(evt);
            }
        });

        jButton_Cancel.setBackground(new java.awt.Color(255, 0, 0));
        jButton_Cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Cancel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cancel.setText("Cancel");
        jButton_Cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelActionPerformed(evt);
            }
        });

        jButton_Save.setBackground(new java.awt.Color(0, 255, 0));
        jButton_Save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Save.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Save.setText("Save");
        jButton_Save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_JudulBuku, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser_TglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(28, 28, 28)
                                        .addComponent(jTextField_KodePeminjaman))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_KodePengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jTextField_NamaPeminjaman)))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jButton_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_KodePengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_KodePeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_NamaPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_JudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser_TglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_CancelActionPerformed

    private void jButton_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ResetActionPerformed
        reset();
    }//GEN-LAST:event_jButton_ResetActionPerformed

    private void jButton_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveActionPerformed
        save();
    }//GEN-LAST:event_jButton_SaveActionPerformed

    private void jTextField_NamaPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NamaPeminjamanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NamaPeminjamanActionPerformed

    private void jTextField_KodePeminjamanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_KodePeminjamanKeyReleased
        TampilDetail();
    }//GEN-LAST:event_jTextField_KodePeminjamanKeyReleased

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
            java.util.logging.Logger.getLogger(FrmKembali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmKembali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmKembali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmKembali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmKembali().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancel;
    private javax.swing.JButton jButton_Reset;
    private javax.swing.JButton jButton_Save;
    private com.toedter.calendar.JDateChooser jDateChooser_TglKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Tambahan;
    private javax.swing.JTextField jTextField_JudulBuku;
    private javax.swing.JTextField jTextField_KodePeminjaman;
    private javax.swing.JTextField jTextField_KodePengembalian;
    private javax.swing.JTextField jTextField_NamaPeminjaman;
    // End of variables declaration//GEN-END:variables
}
