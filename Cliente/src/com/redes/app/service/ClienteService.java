/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.app.service;

import com.redes.app.bean.ChatMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JM
 * Classe que controla o cliente
 */
public class ClienteService {
    private Socket socket;
    private ObjectOutputStream output;
    
    // metodo que é invocado para efetuar a conexão do cliente a um servidor padrão
    public Socket connect(){
        try {
            this.socket = new Socket("localhost",5555);
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socket;
    }
    // metodo que é invocado para efetuar a conexão do cliente, que recebe como parametro o endereço do servidor e a porta
    public Socket connect(String server, int port) throws IOException{
        try {
            System.out.println(server+":"+port);
            this.socket = new Socket(server, port);
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw ex;
            //Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socket;
    }
    
    // função que recebe a mensagem que será enviada, essa mensagem pode conter ou não um destinatario
    public void send(ChatMessage message){
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
