/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidore;

import com.redes.app.service.ServidorService;
import javax.swing.JOptionPane;

/**
 *
 * @author JMMCC
 */
public class ExecutorServer extends Thread {
    public boolean tcp;
    @Override
    public void run() {                      
        new ServidorService(5555);                  
    }
    public ExecutorServer(boolean t){
        tcp=t;
    }
    
}
