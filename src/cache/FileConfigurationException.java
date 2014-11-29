
package cache;

import org.apache.jcs.access.exception.CacheException;

public class FileConfigurationException extends CacheException {

    public FileConfigurationException() {
        super("Archivo de configuracion mal configurado");
    }
    
}
