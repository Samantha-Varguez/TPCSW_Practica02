
package org.uv.tpcsw.practica02;
//primer elemento es pojo y segundo elemento es id del pojo 
//baja el acoplamiento
import java.util.List;

public interface IDAOGeneral<T,ID> {
    public boolean save(T pojo);
    public boolean delete(ID id);
    public boolean update(T pojo, ID id);
    public List<T> findAll();
    public T findByID (ID id);
    
}
