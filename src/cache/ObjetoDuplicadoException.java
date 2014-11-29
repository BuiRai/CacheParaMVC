
package cache;

public class ObjetoDuplicadoException extends Exception{
    
    public ObjetoDuplicadoException(){
        super("El objeto ya existe");
    }
    
}
