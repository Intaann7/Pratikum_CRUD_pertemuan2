/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class MahasiswaManager {
    Connection conn = null;
    Statement st = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_si_43";
    
    public MahasiswaManager() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,"root","");
               st = conn.createStatement();
        } catch (Exception e){
            e.printStackTrace(); 
        }
    }
    public List getMahasiswa() {
        ResultSet rs = null;
        List Mahasiswa = new ArrayList();
            try {
                rs = st.executeQuery("Select*From tabel_mahasiswa");
                while (rs.next()) {
                    Mahasiswa m = new Mahasiswa();
                    m.setnobp (rs.getString("No_BP"));
                    m.setnama(rs.getString("Nama"));
                    m.settempatlahir(rs.getString("Tempat_Lahir"));
                    m.settgllahir(rs.getString("Tgl_Lahir"));
                    m.setalamat(rs.getString("Alamat"));
                    m.setphone(rs.getString("Phone"));
                    m.setasalsekolah(rs.getString("Asal_Sekolah"));                 
                }              
           } catch (Exception e) {
               e.printStackTrace();
           }
            return Mahasiswa;
        }
    public int Insert(Mahasiswa m) {
        int result = 0;
        try {
            result = st.executeUpdate("insert into tabel_mahasiswa value ('"+m.getnobp() +"','"+m.getnama() +"','"+m.gettempatlahir() +"','"+m.gettgllahir() +"','"+m.getalamat()+"','"+m.getphone()+"','"+m.getasalsekolah()+"')");
            
        }catch (Exception e) {
            e.printStackTrace();
            
        }
        return result;
    }
    public int Delete(Mahasiswa m){
        int result = 0;
        try {
            result = st.executeUpdate("delete tabel_mahasiswa where nobp='"+m.getnobp()+"'");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int Update(Mahasiswa m) {
        int result = 0;
        try {
            result = st.executeUpdate("update tabel_mahasiswa setnobp='"+ m.getnobp() +"',nama='"+ m.getnama() +"',tempatlahir='"+ m.gettempatlahir()+"',tgllahir='"+ m.gettgllahir()+"',alamat='"+ m.getalamat()+"',phone='"+ m.getphone()+"',asalsekolah='"+ m.getasalsekolah()+"' where nobp='"+ m.getnobp()+"'");
            
        } catch (Exception e) {
            
        }
        return result;
    }
    public void closeConnection() {
        try {
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
        
    
    
}
