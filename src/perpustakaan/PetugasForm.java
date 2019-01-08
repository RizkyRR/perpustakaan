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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RIZKY RAHMADIANTO
 */
public class PetugasForm extends javax.swing.JFrame {

    /**
     * Creates new form PetugasForm
     */
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    DefaultTableModel dtm;
    int batas;
    
    public PetugasForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        batas = 20;
        TampilStatus(0);
        
        //Tampilan Halaman
        TampilHalamanBuku();
        TampilHalamanRak();
        TampilHalamanKategori();
        TampilHalamanPeminjam();
        TampilHalamanPeminjaman();
        TampilHalamanPengembalian();
        
        HapusBuku();
        HapusRak();
        HapusKategori();
        HapusPeminjam();
//        HapusPeminjaman();
        HapusPengembalian();
    }
    
    private void TampilStatus(int posisi){
        String value = (String) jComboBox_Status.getSelectedItem();
        if (value == "Data Buku") {
            //Header untuk buku
            String[] HeaderBuku = {"No","Kode Buku","Nama Buku","Tgl Rilis","Serial","Penulis","Lokasi Rak","Kategori"};
            dtm = new DefaultTableModel(HeaderBuku, 0);
            TabelPetugasPerpus.setModel(dtm);
           
            int row = TabelPetugasPerpus.getRowCount();
            for (int i = 0; i < row; i++) {
                dtm.removeRow(0);
            }

            String cari = jTextField_Cari.getText();

            Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "SELECT * FROM buku WHERE kode_buku LIKE '%"+cari+"%' OR buku_nama LIKE '%"+cari+"%' OR tgl_rilis LIKE '%"+cari+"%' OR buku_serial LIKE '%"+cari+"%' OR penulis LIKE '%"+cari+"%' OR kode_rak LIKE '%"+cari+"%' OR kode_kategori LIKE '%"+cari+"%'";
                st  = con.createStatement();
                rs  = st.executeQuery(sql);

                int number = 1 + posisi;

                while (rs.next()) {                
                    String[] baris = {Integer.toString(number),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
                    dtm.addRow(baris);
                    number++;
                }
                TabelPetugasPerpus.setModel(dtm);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }

           
           
        } else if (value == "Kategori Buku") {
            String[] HeaderKategori = {"No","Kode Kategori","Nama Kategori"};
            dtm = new DefaultTableModel(HeaderKategori, 0);
            TabelPetugasPerpus.setModel(dtm);

            int row = TabelPetugasPerpus.getRowCount();
            for (int i = 0; i < row; i++) {
                dtm.removeRow(0);
            }

            String cari = jTextField_Cari.getText();

            Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "SELECT * FROM `kategori` WHERE `kode_kategori` LIKE '%"+cari+"%' OR `nama_kategori` LIKE '%"+cari+"%'";
                st  = con.createStatement();
                rs  = st.executeQuery(sql);

                int number = 1 + posisi;

                while (rs.next()) {                
                    String[] baris = {Integer.toString(number),rs.getString(2),rs.getString(3)};
                    dtm.addRow(baris);
                    number++;
                }
                TabelPetugasPerpus.setModel(dtm);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }

            
        } else if (value == "Rak Buku") {
            String[] HeaderRak = {"No","Kode Rak","Nama Rak","Posisi"};
            dtm = new DefaultTableModel(HeaderRak, 0);
            TabelPetugasPerpus.setModel(dtm);

            int row = TabelPetugasPerpus.getRowCount();
            for (int i = 0; i < row; i++) {
                dtm.removeRow(0);
            }

            String cari = jTextField_Cari.getText();

            Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "SELECT * FROM `rak` WHERE `kode_rak` LIKE '%"+cari+"%' OR `nama_rak` LIKE '%"+cari+"%' OR `posisi` LIKE '"+cari+"'";
                st  = con.createStatement();
                rs  = st.executeQuery(sql);

                int number = 1 + posisi;

                while (rs.next()) {                
                    String[] baris = {Integer.toString(number),rs.getString(2),rs.getString(3),rs.getString(4)};
                    dtm.addRow(baris);
                    number++;
                }
                TabelPetugasPerpus.setModel(dtm);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }

          
        } else if (value == "Data Peminjam") {
            String[] HeaderPeminjam = {"No","Kode Peminjam","Nama Peminjam","KTP","Hp","Alamat"};
            dtm = new DefaultTableModel(HeaderPeminjam, 0);
            TabelPetugasPerpus.setModel(dtm);

            int row = TabelPetugasPerpus.getRowCount();
            for (int i = 0; i < row; i++) {
                dtm.removeRow(0);
            }

            String cari = jTextField_Cari.getText();

            Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "SELECT * FROM `peminjam` WHERE `peminjam_kode` LIKE '%"+cari+"%' OR `peminjam_nama` LIKE '%"+cari+"%' OR `peminjam_ktp` LIKE '"+cari+"' OR `peminjam_hp` LIKE '%"+cari+"%' OR `peminjam_alamat` LIKE '%"+cari+"%'";
                st  = con.createStatement();
                rs  = st.executeQuery(sql);

                int number = 1 + posisi;

                while (rs.next()) {                
                    String[] baris = {Integer.toString(number),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    dtm.addRow(baris);
                    number++;
                }
                TabelPetugasPerpus.setModel(dtm);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }

           
        } else if (value == "Peminjaman") {
            String[] HeaderPeminjaman = {"No","Kode Peminjaman","Kode Peminjam","Kode Buku","Tgl Pinjam","Tgl Kembali","Tambahan"};
            dtm = new DefaultTableModel(HeaderPeminjaman, 0);
            TabelPetugasPerpus.setModel(dtm);

            int row = TabelPetugasPerpus.getRowCount();
            for (int i = 0; i < row; i++) {
                dtm.removeRow(0);
            }

            String cari = jTextField_Cari.getText();

            Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "SELECT * FROM `peminjaman` WHERE `peminjaman_kode` LIKE '%"+cari+"%' OR `peminjam_kode` LIKE '%"+cari+"%' OR `buku_kode` LIKE '"+cari+"' OR `tgl_pinjam` LIKE '%"+cari+"%' OR `tgl_kembali` LIKE '%"+cari+"%' OR `tambahan` LIKE '%"+cari+"%'";
                st  = con.createStatement();
                rs  = st.executeQuery(sql);

                int number = 1 + posisi;

                while (rs.next()) {                
                    String[] baris = {Integer.toString(number),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
                    dtm.addRow(baris);
                    number++;
                }
                TabelPetugasPerpus.setModel(dtm);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }

           
        } else {
            //Header untuk pengembalian
            String[] HeaderPengembalian = {"No","Kode Kembali","Nama Peminjam","Judul Buku","Tgl Kembali","Tambahan"};
            dtm = new DefaultTableModel(HeaderPengembalian, 0);
            TabelPetugasPerpus.setModel(dtm);

            int row = TabelPetugasPerpus.getRowCount();
            for (int i = 0; i < row; i++) {
                dtm.removeRow(0);
            }

            String cari = jTextField_Cari.getText();

            Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "SELECT * FROM `pengembalian` WHERE `kembali_kode` LIKE '%"+cari+"%' OR `kembali_nama` LIKE '%"+cari+"%' OR `judul_buku` LIKE '"+cari+"' OR `tgl_kembali` LIKE '%"+cari+"%' OR `tambahan` LIKE '%"+cari+"%'";
                st  = con.createStatement();
                rs  = st.executeQuery(sql);

                int number = 1 + posisi;

                while (rs.next()) {                
                    String[] baris = {Integer.toString(number),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    dtm.addRow(baris);
                    number++;
                }
                TabelPetugasPerpus.setModel(dtm);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }

           
        }
    }
    
    public void TampilHalamanBuku(){
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "SELECT * FROM `buku`";
            st  = con.createStatement();
            rs  = st.executeQuery(sql);
            
            rs.last();
            
            int jumlah = (int) (Math.ceil(rs.getRow()/batas)+1);
            int number = 1;
            
            while (number <= jumlah) {                
                jComboBox_Halaman.addItem(Integer.toString(number));
                number++;
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void TampilHalamanRak(){
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "SELECT * FROM `rak`";
            st  = con.createStatement();
            rs  = st.executeQuery(sql);
            
            rs.last();
            
            int jumlah = (int) (Math.ceil(rs.getRow()/batas)+1);
            int number = 1;
            
            while (number <= jumlah) {                
                jComboBox_Halaman.addItem(Integer.toString(number));
                number++;
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void TampilHalamanKategori(){
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "SELECT * FROM `kategori`";
            st  = con.createStatement();
            rs  = st.executeQuery(sql);
            
            rs.last();
            
            int jumlah = (int) (Math.ceil(rs.getRow()/batas)+1);
            int number = 1;
            
            while (number <= jumlah) {                
                jComboBox_Halaman.addItem(Integer.toString(number));
                number++;
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void TampilHalamanPeminjam(){
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "SELECT * FROM `peminjam`";
            st  = con.createStatement();
            rs  = st.executeQuery(sql);
            
            rs.last();
            
            int jumlah = (int) (Math.ceil(rs.getRow()/batas)+1);
            int number = 1;
            
            while (number <= jumlah) {                
                jComboBox_Halaman.addItem(Integer.toString(number));
                number++;
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void TampilHalamanPeminjaman(){
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "SELECT * FROM `peminjaman`";
            st  = con.createStatement();
            rs  = st.executeQuery(sql);
            
            rs.last();
            
            int jumlah = (int) (Math.ceil(rs.getRow()/batas)+1);
            int number = 1;
            
            while (number <= jumlah) {                
                jComboBox_Halaman.addItem(Integer.toString(number));
                number++;
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void TampilHalamanPengembalian(){
        Koneksi kon = new Koneksi();
        try {
            con = kon.getConnection();
            String sql = "SELECT * FROM `pengembalian`";
            st  = con.createStatement();
            rs  = st.executeQuery(sql);
            
            rs.last();
            
            int jumlah = (int) (Math.ceil(rs.getRow()/batas)+1);
            int number = 1;
            
            while (number <= jumlah) {                
                jComboBox_Halaman.addItem(Integer.toString(number));
                number++;
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void HapusBuku(){
        String KodeHapus = jTextField_Kode.getText();
        //validate button
        
        Koneksi kon = new Koneksi();
        
            try {
                con = kon.getConnection();
                String sql = "DELETE FROM buku WHERE kode_buku = ?";
                ps  = con.prepareStatement(sql);
                ps.setString(1, KodeHapus);
                
                ps.executeUpdate();
                
                TampilHalamanBuku();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }
            TampilStatus(0);
    }
    
    public void HapusRak(){
        String KodeHapus = jTextField_Kode.getText();
        
        Koneksi kon = new Koneksi();
        
            try {
                con = kon.getConnection();
                String sql = "DELETE FROM rak WHERE kode_rak = ?";
                ps  = con.prepareStatement(sql);
                ps.setString(1, KodeHapus);
                
                ps.executeUpdate();
                
                TampilHalamanRak();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }
            TampilStatus(0);
    }
    
    public void HapusKategori(){
        String KodeHapus = jTextField_Kode.getText();
        
        Koneksi kon = new Koneksi();
        
            try {
                con = kon.getConnection();
                String sql = "DELETE FROM kategori WHERE kode_kategori = ?";
                ps  = con.prepareStatement(sql);
                ps.setString(1, KodeHapus);
                
                ps.executeUpdate();
                
                TampilHalamanKategori();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }
            TampilStatus(0);
    }
    
    public void HapusPeminjam(){
        String KodeHapus = jTextField_Kode.getText();
        
        Koneksi kon = new Koneksi();
        
            try {
                con = kon.getConnection();
                String sql = "DELETE FROM peminjam WHERE peminjam_kode = ?";
                ps  = con.prepareStatement(sql);
                ps.setString(1, KodeHapus);
                
                ps.executeUpdate();
                
                TampilHalamanPeminjam();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }
            TampilStatus(0);
    }
    
//    public void HapusPeminjaman(){
//        int baris = TabelPetugasPerpus.getSelectedRow();
//        dtm       = (DefaultTableModel) TabelPetugasPerpus.getModel();
//
//        String selectRow = dtm.getValueAt(baris, 2).toString();
//        
//        //validate button
//        int confirm = JOptionPane.showConfirmDialog(rootPane, "Apakah Anda yakin untuk menghapus data ini ?","Delete",JOptionPane.YES_NO_OPTION);
//        Koneksi kon = new Koneksi();
//        
//        if (confirm == 0) {
//            try {
//                con = kon.getConnection();
//                String sql = "DELETE FROM peminjaman WHERE kode_buku = ?";
//                ps  = con.prepareStatement(sql);
//                ps.setString(1, selectRow);
//                
//                ps.executeUpdate();
//                
//                JOptionPane.showMessageDialog(rootPane, "Data sudah terhapus !");
//            } catch (SQLException se) {
//                JOptionPane.showMessageDialog(null, se);
//            }
//            TampilStatus(0);
//        }
//    }
    
    public void HapusPengembalian(){
        String KodeHapus = jTextField_Kode.getText();
        
        //validate button
        
        Koneksi kon = new Koneksi();
            try {
                con = kon.getConnection();
                String sql = "DELETE FROM pengembalian WHERE kembali_kode = ?";
                ps  = con.prepareStatement(sql);
                ps.setString(1, KodeHapus);
                
                ps.executeUpdate();
                
                TampilHalamanPengembalian();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }
            TampilStatus(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelPetugasPerpus = new javax.swing.JTable();
        jTextField_Cari = new javax.swing.JTextField();
        jComboBox_Halaman = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton_DataBuku = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton_Close = new javax.swing.JButton();
        jComboBox_Status = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Kode = new javax.swing.JTextField();
        jButton_Delete = new javax.swing.JButton();

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("KTP");

        jButton2.setText("jButton1");

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(22, 160, 133));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PETUGAS - PERPUSTAKAAN ABC");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        TabelPetugasPerpus.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelPetugasPerpus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPetugasPerpusMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelPetugasPerpus);

        jTextField_Cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_CariKeyReleased(evt);
            }
        });

        jComboBox_Halaman.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_Halaman.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_HalamanItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cari");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Halaman");

        jButton1.setBackground(new java.awt.Color(41, 128, 185));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Rak Buku");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton_DataBuku.setBackground(new java.awt.Color(41, 128, 185));
        jButton_DataBuku.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_DataBuku.setForeground(new java.awt.Color(255, 255, 255));
        jButton_DataBuku.setText("Data Buku");
        jButton_DataBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_DataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DataBukuActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(41, 128, 185));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Kategori Buku");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(41, 128, 185));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Data Peminjam");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton6.setBackground(new java.awt.Color(255, 255, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Peminjaman");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Pengembalian");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton_Close.setBackground(new java.awt.Color(255, 0, 0));
        jButton_Close.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Close.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Close.setText("Close");
        jButton_Close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CloseActionPerformed(evt);
            }
        });

        jComboBox_Status.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Buku", "Kategori Buku", "Rak Buku", "Data Peminjam", "Peminjaman", "Pengembalian" }));
        jComboBox_Status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_StatusMouseClicked(evt);
            }
        });
        jComboBox_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_StatusActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Item Kode");

        jTextField_Kode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_Kode.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Kode.setEnabled(false);

        jButton_Delete.setBackground(new java.awt.Color(255, 0, 0));
        jButton_Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Delete.setText("Delete");
        jButton_Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Delete.setEnabled(false);
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_DataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Kode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Delete)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 596, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_Halaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField_Kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Delete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_DataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_Halaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(0, 18, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DataKategori kategori = new DataKategori();
        kategori.setVisible(true);
        kategori.pack();
        kategori.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton_DataBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DataBukuActionPerformed
        DataBuku buku = new DataBuku();
        buku.setVisible(true);
        buku.pack();
        buku.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton_DataBukuActionPerformed

    private void jButton_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CloseActionPerformed
        LoginUser login = new LoginUser();
        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);
        
        this.dispose();
    }//GEN-LAST:event_jButton_CloseActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        FrmPeminjaman peminjaman = new FrmPeminjaman();
        peminjaman.setVisible(true);
        peminjaman.pack();
        peminjaman.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        FrmKembali kembali = new FrmKembali();
        kembali.setVisible(true);
        kembali.pack();
        kembali.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox_StatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_StatusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_StatusMouseClicked

    private void jComboBox_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_StatusActionPerformed
        TampilStatus(0);
    }//GEN-LAST:event_jComboBox_StatusActionPerformed

    private void jTextField_CariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_CariKeyReleased
        TampilStatus(0);
    }//GEN-LAST:event_jTextField_CariKeyReleased

    private void jComboBox_HalamanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_HalamanItemStateChanged
        try {
            int halaman = Integer.parseInt(jComboBox_Halaman.getSelectedItem().toString());
            int posisi  = (halaman - 1) * batas;
            
            
            TampilStatus(posisi);
        } catch (Exception se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }//GEN-LAST:event_jComboBox_HalamanItemStateChanged

    private void TabelPetugasPerpusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPetugasPerpusMouseClicked
        jButton_Delete.setEnabled(true);
        
        int row = TabelPetugasPerpus.getSelectedRow();
        String kode = TabelPetugasPerpus.getValueAt(row, 1).toString();
        
        jTextField_Kode.setText(kode);
    }//GEN-LAST:event_TabelPetugasPerpusMouseClicked

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
            HapusBuku();
            HapusRak();
            HapusKategori();
            HapusPeminjam();
    //        HapusPeminjaman();
            HapusPengembalian();
    }//GEN-LAST:event_jButton_DeleteActionPerformed

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
            java.util.logging.Logger.getLogger(PetugasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PetugasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PetugasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PetugasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PetugasForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelPetugasPerpus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton_Close;
    private javax.swing.JButton jButton_DataBuku;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox_Halaman;
    private javax.swing.JComboBox<String> jComboBox_Status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField_Cari;
    private javax.swing.JTextField jTextField_Kode;
    // End of variables declaration//GEN-END:variables
}
