
package org.uv.tpcsw.practica02;
// org.uv.tpcsw.practica01.dao

import java.sql.Connection;

// modelo abstracto que a diferencia de una interfaz trae elementos concretos pero tiene constructor
public abstract class TransactionDB<T> {
    protected T pojo;
    protected TransactionDB(T pojo) {
        this.pojo=pojo;
    }
    public abstract boolean execute(Connection con);
    
    
}
