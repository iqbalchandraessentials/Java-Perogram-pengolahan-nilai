
package mainkoneksi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class koneksi2 {
    public Connection koneksi2;
    private Object DriveManager;
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil koneksi");
        } catch (ClassNotFoundException ex){
            System.out.println("eror koneksi bos : " +ex);
        }
        String url ="jdbc:mysql://localhost:3306/smk-im";
        try {
            koneksi2 = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil Koneksi Database");
        } catch (SQLException ex){
            System.out.println("Gagal koneksi Database bos : "+ex);
        }
        return koneksi2;
    }
}