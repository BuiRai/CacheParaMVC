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
public class CacheCandidatos {
    private JCS jcsCache;
    
    public CacheCandidatos(){
        try {
            // Se carga el cache usando el archivo de configuracion
            jcsCache = JCS.getInstance("CacheMVC");
        } catch (CacheException ex) {
            Logger.getLogger(CacheCandidatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param identifier el identificador del objeto
     * @param objeto el objeto a almacenar
     */
    public void agregarObjeto(long identifier, Cacheable objeto){
        try {
            jcsCache.put(identifier, objeto);
        } catch (CacheException ex) {
            Logger.getLogger(CacheCandidatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param identifier el identificador del objeto
     * @return 
     */
    public Cacheable obtenerObjeto(int identifier){
        return (Cacheable)jcsCache.get(identifier);
    }
    
    /**
     * 
     * @param identifier el identificador asociado al objeto que se desee encontrar
     * @return true si el objeto existe
     * false si no existe
     */
    public boolean existenciaDeObjeto(int identifier){
        Cacheable object;
        object = obtenerObjeto(identifier);
        return object != null;
    }
    
}
