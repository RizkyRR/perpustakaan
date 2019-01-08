/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author RIZKY RAHMADIANTO
 */
public class Koneksi {
    private Connection koneksi;
    
    public Connection getConnection(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, cnfe);
        }
        
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/perpusdb","root","");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se);
        }
        return koneksi;
    }
}
