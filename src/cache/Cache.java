/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 *
 * @author BuiRai
 */
public class Cache {

    private JCS jcsCache;
    private final int MAX_ELEMENTS = 1000;

    public Cache() throws FileConfigurationException {
        try {
            // Se carga el cache usando el archivo de configuracion
            jcsCache = JCS.getInstance("Cache");
        } catch (CacheException ex) {
            throw new FileConfigurationException();
        }
    }

    /**
     *
     * @param objeto el objeto a almacenar
     * @throws cache.ObjetoDuplicadoException
     */
    public void agregarObjeto(Cacheable objeto) throws ObjetoDuplicadoException {
        if (!existenciaDeObjeto(objeto.getId())) {
            try {
                jcsCache.put(objeto.getId(), objeto);
            } catch (CacheException ex) {
                Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new ObjetoDuplicadoException();
        }

    }

    /**
     *
     * @param id el identificador del objeto
     * @return
     * @throws cache.ObjetoDesconocidoException
     */
    public Cacheable obtenerObjeto(int id) {//throws ObjetoDesconocidoException {
        if (existenciaDeObjeto(id)) {
            return (Cacheable) jcsCache.get(id);
        } else {
            return null;
//            throw new ObjetoDesconocidoException();
        }
    }

    /**
     *
     * @param id el identificador asociado al objeto que se desee encontrar
     * @return true si el objeto existe false si no existe
     */
    public boolean existenciaDeObjeto(int id) {
        Cacheable object;
        object = (Cacheable) jcsCache.get(id);
        return object != null;
    }

    public void eliminarObjeto(Cacheable objeto) {
        if (existenciaDeObjeto(objeto.getId())) {
            try {
                jcsCache.remove(objeto.getId());
            } catch (CacheException ex) {
                Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No se encontro al objeto");
        }
    }
    
    /**
     * @return el numero maximo de elementos que almacena el cache
     */
    public int getMAX_ELEMENTS() {
        return MAX_ELEMENTS;
    }
    
//    public ArrayList<Cacheable> toArray() {
//        ArrayList<Cacheable> objetos = new ArrayList<>();
//        //Se recorre la cache para agregar los objetos que tenga dentro:
//        for (int i = 1; i < MAX_ELEMENTS; i++) {
//            if (existenciaDeObjeto(i)) {
//                try {
//                    objetos.add((Cacheable) obtenerObjeto(i));
//                } catch (ObjetoDesconocidoException ex) {
//                    Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                break;
//            }
//        }
//        return objetos;
//    }

}
