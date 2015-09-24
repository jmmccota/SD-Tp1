/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.app;

import com.redes.app.service.ServidorService;

/**
 *
 * @author JM
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ServidorService(6666);
        
    }
    
}
