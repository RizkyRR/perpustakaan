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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author RIZKY RAHMADIANTO
 */
public class FrmPeminjaman extends javax.swing.JFrame {

    /**
     * Creates new form FrmPeminjaman
     */
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public FrmPeminjaman(){
        initComponents();
        this.setLocationRelativeTo(null);
        //https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
        //https://www.mkyong.com/java/java-how-to-add-days-to-current-date/
        Date today = Calendar.getInstance().getTime();
        //Date soon  = Calendar.getInstance().getTime();
        
        jDateChooser_TglPinjam.setDate(today);
        //jDateChooser_TglKembali.setDate(soon);
        Date tglKembali = jDateChooser_TglPinjam.getDate();
//        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cr = Calendar.getInstance();
//        cr.setTime(date_format.parse(tglKembali));
//        cr.add(Calendar.DATE, 3);
//        tglKembali = date_format.format(cr.getTime());
        Calendar cl = Calendar.getInstance();
        cl.setTime(tglKembali);
        cl.add(Calendar.DATE, 7);
        Date a = cl.getTime();
        jDateChooser_TglKembali.setDate(a);
        
        namapeminjam();
        namabuku();
        reset();
        save();
    }
    
    private void namapeminjam(){
        String kodePeminjam = jTextField_KodePeminjam.getText();
        //String namaPinjam = jTextField_NamaPeminjam.getText();
        
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            
            String sql = "SELECT * FROM peminjam WHERE peminjam_kode LIKE '%"+kodePeminjam +"%'";
            
            st = con.createStatement();
            //ps.setString(1, kodeBuku);
            
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                jTextField_NamaPeminjam.setText(rs.getString("peminjam_nama"));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    private void namabuku(){
        String kodeBuku   = jTextField_KodeBuku.getText();
        
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            
            String sql = "SELECT * FROM buku WHERE kode_buku LIKE '%"+kodeBuku+"%'";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {                
                jTextField_JudulBuku.setText(rs.getString("buku_nama"));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    private void reset(){
        jTextField_KodePeminjam.setText("");
        jTextField_NamaPeminjam.setText("");
        jTextField_KodeBuku.setText("");
        jTextField_JudulBuku.setText("");
        jTextArea_Tambahan.setText("");
    }
    
    private void save(){
        String inisial = "DP";
        String kodePinjam = jTextField_KodePinjam.getText();
        String kodePeminjam = jTextField_KodePeminjam.getText();
        String namaPinjam = jTextField_NamaPeminjam.getText();
        String kodeBuku   = jTextField_KodeBuku.getText();
        String judulBuku  = jTextField_JudulBuku.getText();
        String tambahan   = jTextArea_Tambahan.getText();
        
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String select = "SELECT MAX(right(peminjaman_id, 3)) FROM peminjaman";
            st  = con.createStatement();
            rs  = st.executeQuery(select);
            
            while (rs.next()) {                
                if (rs.first() == false) {
                    jTextField_KodePinjam.setText(inisial + "-" + "001");
                } else {
                    rs.last();
                    
                    int tambah      = rs.getInt(1)+1;
                    String angka    = String.valueOf(tambah);
                    int pjgAngka    = angka.length();
                    
                    for (int i = 0; i < 3 - pjgAngka; i++) {
                        angka = "0" + angka;
                    }
                    jTextField_KodePinjam.setText(inisial + "-" + angka);
                }
            }
            
            String sql = "INSERT INTO `peminjaman`(`peminjaman_kode`, `peminjam_kode`, `buku_kode`, `tgl_pinjam`, `tgl_kembali`, `tambahan`) VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, kodePinjam);
            ps.setString(2, kodePeminjam);
            ps.setString(3, kodeBuku);
            ps.setString(4, ((JTextField)jDateChooser_TglPinjam.getDateEditor().getUiComponent()).getText());
            ps.setString(5, ((JTextField)jDateChooser_TglKembali.getDateEditor().getUiComponent()).getText());
            ps.setString(6, tambahan);
            
            //validate
            if (kodePeminjam.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode peminjam tidak boleh kosong !");
            } else if (namaPinjam.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Nama peminjam tidak boleh kosong !");
            } else if (kodeBuku.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode buku tidak boleh kosong !");
            } else if (judulBuku.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Judul buku tidak boleh kosong !");
            } else {
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Data peminjaman berhasil disimpan !", "Success", JOptionPane.INFORMATION_MESSAGE);
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

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_KodePinjam = new javax.swing.JTextField();
        jTextField_KodePeminjam = new javax.swing.JTextField();
        jTextField_NamaPeminjam = new javax.swing.JTextField();
        jTextField_KodeBuku = new javax.swing.JTextField();
        jTextField_JudulBuku = new javax.swing.JTextField();
        jDateChooser_TglPinjam = new com.toedter.calendar.JDateChooser();
        jDateChooser_TglKembali = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Tambahan = new javax.swing.JTextArea();
        jButton_Reset = new javax.swing.JButton();
        jButton_Cancel = new javax.swing.JButton();
        jButton_Save = new javax.swing.JButton();

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Peminjaman");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 84, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PEMINJAMAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Peminjaman");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kode Peminjam");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama Peminjam");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kode Buku");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Judul Buku");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal Pinjam");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tanggal Kembali");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tambahan");

        jTextField_KodePinjam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_KodePinjam.setEnabled(false);

        jTextField_KodePeminjam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_KodePeminjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_KodePeminjamKeyReleased(evt);
            }
        });

        jTextField_NamaPeminjam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_NamaPeminjam.setEnabled(false);

        jTextField_KodeBuku.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_KodeBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_KodeBukuKeyReleased(evt);
            }
        });

        jTextField_JudulBuku.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_JudulBuku.setEnabled(false);

        jDateChooser_TglPinjam.setDateFormatString("yyyy-MM-dd");
        jDateChooser_TglPinjam.setEnabled(false);
        jDateChooser_TglPinjam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jDateChooser_TglKembali.setDateFormatString("yyyy-MM-dd");
        jDateChooser_TglKembali.setEnabled(false);
        jDateChooser_TglKembali.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

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
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(23, 23, 23)
                                .addComponent(jTextField_KodePeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_KodePinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_KodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_NamaPeminjam)
                                .addComponent(jDateChooser_TglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_JudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jDateChooser_TglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_KodePinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_KodePeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_NamaPeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_KodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_JudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDateChooser_TglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jDateChooser_TglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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
                .addGap(6, 6, 6)
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

    private void jTextField_KodePeminjamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_KodePeminjamKeyReleased
        namapeminjam();
    }//GEN-LAST:event_jTextField_KodePeminjamKeyReleased

    private void jTextField_KodeBukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_KodeBukuKeyReleased
        namabuku();
    }//GEN-LAST:event_jTextField_KodeBukuKeyReleased

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
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancel;
    private javax.swing.JButton jButton_Reset;
    private javax.swing.JButton jButton_Save;
    private com.toedter.calendar.JDateChooser jDateChooser_TglKembali;
    private com.toedter.calendar.JDateChooser jDateChooser_TglPinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextArea jTextArea_Tambahan;
    private javax.swing.JTextField jTextField_JudulBuku;
    private javax.swing.JTextField jTextField_KodeBuku;
    private javax.swing.JTextField jTextField_KodePeminjam;
    private javax.swing.JTextField jTextField_KodePinjam;
    private javax.swing.JTextField jTextField_NamaPeminjam;
    // End of variables declaration//GEN-END:variables
}
