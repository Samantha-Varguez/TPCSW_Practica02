//statements

package org.uv.tpcsw.practica02;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
   private static ConexionDB cx = null; 
   private Connection con = null; 
   
   public static ConexionDB getInstance(){
       if(cx==null){
           cx=new ConexionDB();
       }
       return cx;
   }
   
    private ConexionDB(){
        try {
               String url="jdbc:postgresql://localhost:5432/ejemplo";
               con=DriverManager.getConnection(url, "postgres", "postgres");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet select (String sql){
        Statement stm=null;
       try {
           stm =  con.createStatement();
             ResultSet reg=stm.executeQuery(sql);
        return reg;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      return null;
       }
    }
    
    
    // tiene patron singleton y se vuelve en controlador de transacciones 
    public boolean execute(TransactionDB transaction) {
        return transaction.execute(con);
    }
    
    public boolean execute(String sql){
        // se inicializa fuera del try
        Statement stm=null;
        try {
            stm = con.createStatement();
            return stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        
        return false;
    } finally{
    if (stm != null) {
        try{
            stm.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    }
    
      public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
