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
    private final int MAX_ELEMENTS = 1000;
    
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
     * @throws ArquiSoftware.ObjetoDuplicadoException
     */
    public void agregarObjeto(Cacheable objeto) throws ObjetoDuplicadoException{
        if (!existenciaDeObjeto(objeto.getId())) {
            try {
                jcsCache.put(objeto.getId(), objeto);
            } catch (CacheException ex) {
                Logger.getLogger(CacheMVC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new ObjetoDuplicadoException();
        }
        
    }
    
    /**
     * 
     * @param id el identificador del objeto
     * @return 
     * @throws ArquiSoftware.ObjetoDesconocidoException 
     */
    public Cacheable obtenerObjeto(int id) {//throws ObjetoDesconocidoException{
        if (existenciaDeObjeto(id)) {
            return (Cacheable)jcsCache.get(id);
        }else{
            return null;
            //throw new ObjetoDesconocidoException();
        } 
    }
    
    /**
     * 
     * @param id el identificador asociado al objeto que se desee encontrar
     * @return true si el objeto existe
     * false si no existe
     */
    public boolean existenciaDeObjeto(int id){
        Cacheable object;
        object = (Cacheable)jcsCache.get(id);
        return object != null;
    }

    /**
     * @return el numero maximo de elementos que almacena el cache
     */
    public int getMAX_ELEMENTS() {
        return MAX_ELEMENTS;
    }
    
}
