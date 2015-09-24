/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidore;

import com.redes.app.service.ServidorService;

/**
 *
 * @author JMMCC
 */
public class ExecutorServer extends Thread {

    @Override
    public void run() {
           new ServidorService(5555);
    }
    
}
