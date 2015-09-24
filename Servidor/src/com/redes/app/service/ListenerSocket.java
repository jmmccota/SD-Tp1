/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.app.service;

import com.redes.app.bean.ChatMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JMMCC
 */
 public class ListenerSocket implements Runnable {

        private ObjectOutputStream output;
        private ObjectInputStream input;
        private ServidorService ss;
        public ListenerSocket(Socket socket, ServidorService ss) {
            this.ss = ss;
            try {
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            ChatMessage message = null;
            try {
                while ((message = (ChatMessage) input.readObject()) != null) {
                    ChatMessage.Action action = message.getAction();
                    if (action.equals(ChatMessage.Action.CONNECT)) {
                        boolean isConnect = ss.connect(message, output);
                        if (isConnect) {
                            ss.getMapOnlines().put(message.getName(), output);
                            ss.sendOnlines();
                        }
                    } else if (action.equals(ChatMessage.Action.DISCONNECT)) {
                        ss.disconnect(message, output);
                        ss.sendOnlines();
                        return;
                    } else if (action.equals(ChatMessage.Action.SEND_ONE)) {
                        ss.sendOne(message);
                    } else if (action.equals(ChatMessage.Action.SEND_ALL)) {
                        ss.sendAll(message);
                    } else if (action.equals(ChatMessage.Action.USERS_ONLINE)) {
                        //sendOnlines(); //so para um botão refresh
                    }
                }
            } catch (SocketException ex) {
                ss.disconnect(message, output);
                ss.sendOnlines();
              //  Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch ( IOException ex){
                ss.disconnect(message, output);
                ss.sendOnlines();
              //  Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);    
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }