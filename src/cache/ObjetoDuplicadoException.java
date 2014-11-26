/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

/**
 *
 * @author BuiRai
 */
public class ObjetoDuplicadoException extends Exception{
    
    public ObjetoDuplicadoException(){
        super("El objeto ya existe");
    }
    
}
