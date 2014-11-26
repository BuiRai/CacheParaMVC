/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import org.apache.jcs.access.exception.CacheException;

/**
 *
 * @author BuiRai
 */
public class FileConfigurationException extends CacheException {

    public FileConfigurationException() {
        super("Archivo de configuracion mal configurado");
    }
    
}
