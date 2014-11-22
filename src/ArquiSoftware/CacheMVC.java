/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquiSoftware;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 *
 * @author BuiRai
 */
public class CacheMVC {
    private JCS jcsCache;
    
    public CacheMVC(){
        try {
            // Se carga el cache usando el archivo de configuracion
            jcsCache = JCS.getInstance("CacheMVC");
        } catch (CacheException ex) {
            Logger.getLogger(CacheMVC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param objeto el objeto a almacenar
     */
    public void agregarObjeto(Cacheable objeto){
        try {
            jcsCache.put(objeto.getId(), objeto);
        } catch (CacheException ex) {
            Logger.getLogger(CacheMVC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param id el identificador del objeto
     * @return 
     */
    public Cacheable obtenerObjeto(int id){
        return (Cacheable)jcsCache.get(id);
    }
    
    /**
     * 
     * @param id el identificador asociado al objeto que se desee encontrar
     * @return true si el objeto existe
     * false si no existe
     */
    public boolean existenciaDeObjeto(int id){
        Cacheable object;
        object = obtenerObjeto(id);
        return object != null;
    }
    
}
