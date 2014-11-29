package cache;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

public class Cache {

    private JCS jcsCache;
    private static Cache instancia;

    /**
     * Singleton
     *
     * @return instancia de Cache
     * @throws FileConfigurationException
     */
    public static Cache getInstancia() throws FileConfigurationException {
        if (instancia == null) {
            return new Cache();
        } else {
            return instancia;
        }
    }

    /**
     * Constructor privado de Cache
     *
     * @throws FileConfigurationException
     */
    private Cache() throws FileConfigurationException {
        try {
            // Se carga el cache usando el archivo de configuracion
            jcsCache = JCS.getInstance("Cache");
        } catch (CacheException ex) {
            throw new FileConfigurationException();
        }
    }

    /**
     * Agrega el objeto especificado a la memoria caché
     *
     * @param objeto el objeto a almacenar
     * @throws cache.ObjetoDuplicadoException
     */
    public void agregarObjeto(Cacheable objeto) throws ObjetoDuplicadoException {
        try {
            jcsCache.put(objeto.getId(), objeto);
        } catch (CacheException ex) {
            throw new ObjetoDuplicadoException();
        }

    }

    /**
     * Obtiene de la caché un objeto tipo Cacheable a partir del id especificado
     *
     * @param id el identificador del objeto
     * @return el objeto que se obtuvo del caché
     * @throws cache.ObjetoDesconocidoException
     */
    public Cacheable obtenerObjeto(int id) throws ObjetoDesconocidoException {
        if (existenciaDeObjeto(id)) {
            return (Cacheable) jcsCache.get(id);
        } else {
            throw new ObjetoDesconocidoException();
        }
    }

    /**
     * Método que verifica si un objeto se encuentra en la caché
     *
     * @param id el identificador asociado al objeto que se desee encontrar
     * @return true si el objeto existe, false si no existe
     */
    public boolean existenciaDeObjeto(int id) {
        Cacheable object;
        object = (Cacheable) jcsCache.get(id);
        return object != null;
    }

    /**
     * Método que elimina un objeto de la memoria caché
     *
     * @param objeto el objeto que se desea eliminar de la caché
     * @throws ObjetoDesconocidoException
     */
    public void eliminarObjeto(Cacheable objeto) throws ObjetoDesconocidoException {
        try {
            jcsCache.remove(objeto.getId());
        } catch (CacheException ex) {
            throw new ObjetoDesconocidoException();
        }
    }
    
    /**
     * Método que elimina un objeto de la memoria caché
     *
     * @param id el identificador del objeto que se desea eliminar de la caché
     * @throws ObjetoDesconocidoException
     */
    public void eliminarObjeto(int id) throws ObjetoDesconocidoException {
        try {
            jcsCache.remove(id);
        } catch (CacheException ex) {
            throw new ObjetoDesconocidoException();
        }
    }
    
    public ArrayList toArray(int inicio, int fin) {
        ArrayList<Cacheable> arreglo = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            try {
                arreglo.add(obtenerObjeto(i));
            } catch (ObjetoDesconocidoException ex) {
                Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arreglo;
        
    }

}
