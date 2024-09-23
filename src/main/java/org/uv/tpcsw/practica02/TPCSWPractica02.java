

package org.uv.tpcsw.practica02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samantha
 */
public class TPCSWPractica02 {

    public static void main(String[] args) {
        
    
        @Override
        public boolean save(Empleado pojo){
          ConexionDB con = (ConexionDB) ConexionDB.getInstance();
        TransactionDB<Empleado> transactionDB = new TransactionDB<Empleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                String sql="insert into empleado (clave, nombre, direccion,telefono) values"+"(?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, pojo.getClave());
                pst.setString(2, pojo.getDireccion());
                pst.setString(3, pojo.getTelefono());
                pst.setString(4, pojo.getNombre());
                try {
                    return pst.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }
        };
        return con.execute(transactionDB);
} 
}
