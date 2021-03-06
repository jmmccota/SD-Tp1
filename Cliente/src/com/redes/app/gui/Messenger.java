/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.app.gui;

import com.redes.app.bean.ChatMessage;
import com.redes.app.bean.ChatMessage.Action;
import com.redes.app.service.ClienteService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 * tela do bate papo
 * @author JM
 */
public class Messenger extends javax.swing.JFrame {

    private boolean conectado = false;
    private Socket socket;
    private MulticastSocket socketm;
    private DatagramSocket serverdgram;
    private ChatMessage message;
    private ClienteService service;
    public boolean tcp;

    /**
     * Creates new form Messenger
     * inicialização padrão, tcp
     */
    public Messenger() {
        initComponents();
        jRadioButtonTCP.setSelected(true);
        conectado = false;
    }
    // inicialização caso seja tcp(true) udp(false)
    public Messenger(boolean t) {
        initComponents();
        tcp = t;
        if (tcp) {
            jRadioButtonTCP.setSelected(true);
            jRadioButtonUDP.setSelected(false);

        } else {
            jRadioButtonTCP.setSelected(false);
            jRadioButtonUDP.setSelected(true);
        }
        jRadioButtonTCP.setEnabled(false);
        jRadioButtonUDP.setEnabled(false);
    }
    // thread que ficará escutando as respostas do servidor
    private class ListenerSocket implements Runnable {

        private ObjectInputStream input;
        // recebe o socket que ficará escutando
        public ListenerSocket(Socket socket) {
            try {
                input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // para o caso de udp recebe o socket multicast, os datagramas e as strings com as mensagens
        public ListenerSocket(MulticastSocket socketm, DatagramPacket dg, String str) throws IOException {
            byte[] dados = new byte[140];
            MulticastSocket msocket;
            DatagramPacket datagrama = dg;
            msocket = socketm;
            dados = datagrama.getData();
            //datagrama.setData(dados);
            msocket.receive(datagrama);

        }
        // execução para o listener
        @Override
        public void run() {
            ChatMessage message = null;

            try {
                while ((message = (ChatMessage) input.readObject()) != null) {
                    Action action = message.getAction();
                    if (action.equals(Action.CONNECT)) {
                        connected(message);
                    } else if (action.equals(Action.DISCONNECT)) {
                        disconnected();
                        socket.close();
                    } else if (action.equals(Action.SEND_ONE)) {
                        receive(message);
                    } else if (action.equals(Action.USERS_ONLINE)) {
                        refreshOnlines(message);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    // quando conectar manda a mensagem de boas vindas para o servidor ou para os outros usuarios
    private void connected(ChatMessage message) {
        if (message.getText() != null) {
            if (message.getText().equals("NO")) {
                tfNome.setText("");
                JOptionPane.showMessageDialog(this, "Conexão recusada, tente com outro nome", "Erro de Nome", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        this.message = message;
        tfNome.setEnabled(false);
        tfPorta.setEnabled(false);
        jRadioButtonTCP.setEnabled(false);
        jRadioButtonUDP.setEnabled(false);
        tfServer.setEnabled(false);
        btConecta.setText("Desconecta");
        conectado = true;
        txtAreaSend.setEnabled(true);
        btEnvia.setEnabled(true);

        JOptionPane.showMessageDialog(this, "Bem vindo " + tfNome.getText(), "Conectado", JOptionPane.INFORMATION_MESSAGE);
    }
    // efeuta a desconexão
    private void disconnected() {
        //  try {
        //  socket.close();
        tfNome.setEnabled(true);
        tfPorta.setEnabled(true);
        tfServer.setEnabled(true);
        btConecta.setText("Conecta");
        txtAreaSend.setEnabled(false);
        btEnvia.setEnabled(false);
        txtAreaReceive.setText("");
        txtAreaSend.setText("");
        conectado = false;
        JOptionPane.showMessageDialog(this, "Conexão encerrada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(this, "Não desconecta ou já desconectado", "Erro", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    // exibe a mensagem na tela
    private void receive(ChatMessage message) {
        txtAreaReceive.append(message.getName() + " diz: " + message.getText() + "\n");

    }
    // atualiza a lista de clientes online
    private void refreshOnlines(ChatMessage message) {
        System.out.println("lista onlines:" + message.getSetOnlines().toString());
        Set<String> names = message.getSetOnlines();
        names.remove(message.getName());
        String[] array = (String[]) names.toArray(new String[names.size()]);
        listOnlines.setListData(array);
        listOnlines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOnlines.setLayoutOrientation(JList.VERTICAL);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfServer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfPorta = new javax.swing.JTextField();
        btConecta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jRadioButtonTCP = new javax.swing.JRadioButton();
        jRadioButtonUDP = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btEnvia = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaReceive = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaSend = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listOnlines = new javax.swing.JList();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Messenger");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Local do Servidor"));

        jLabel1.setText("IP do Servidor ou Nome");

        tfServer.setText("127.0.0.1");

        jLabel2.setText("Porta");

        tfPorta.setText("5555");

        btConecta.setText("Conectar");
        btConecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConectaActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome");

        jRadioButtonTCP.setText("TCP");
        jRadioButtonTCP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonTCPMouseClicked(evt);
            }
        });

        jRadioButtonUDP.setText("UDP");
        jRadioButtonUDP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonUDPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfNome))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(124, 124, 124)
                                        .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btConecta)
                                .addComponent(jRadioButtonTCP)
                                .addComponent(jRadioButtonUDP))
                        .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jRadioButtonTCP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButtonUDP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btConecta)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jLabel4.setText("Mensagem");

        btEnvia.setText("Envia");
        btEnvia.setEnabled(false);
        btEnvia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviaActionPerformed(evt);
            }
        });

        txtAreaReceive.setEditable(false);
        txtAreaReceive.setColumns(20);
        txtAreaReceive.setRows(5);
        jScrollPane3.setViewportView(txtAreaReceive);

        txtAreaSend.setColumns(20);
        txtAreaSend.setRows(5);
        txtAreaSend.setEnabled(false);
        jScrollPane4.setViewportView(txtAreaSend);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(btEnvia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btEnvia)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Onlines"));

        listOnlines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(listOnlines);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("Messenger");

        pack();
    }// </editor-fold>                        
    // click do botão de conexão
    private void btConectaActionPerformed(java.awt.event.ActionEvent evt) {
        if (conectado == false) {
            String name = tfNome.getText();
            if (!name.isEmpty()) {
                message = new ChatMessage();
                message.setAction(Action.CONNECT);
                message.setName(name);
//                if (socket == null) {
                tcp = jRadioButtonTCP.isSelected();

                service = new ClienteService();
                try {
                    socket = service.connect(tfServer.getText(), Integer.parseInt(tfPorta.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Não Conecta, Servidor inativo ou falha\nVerifique as configurações de Conexão", "Unable to connect", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Thread(new ListenerSocket(socket)).start();
                this.service.send(message);
//                JOptionPane.showMessageDialog(this, "Conectou", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nome não pode ser vazio", "Nome", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            ChatMessage message = new ChatMessage();
            message.setName(this.message.getName());
            message.setAction(Action.DISCONNECT);
            this.service.send(message);
            disconnected();

//                if (conexaoCliente.isClosed()) {
//            JOptionPane.showMessageDialog(this, "Conexão encerrada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//                }
            tfNome.setEnabled(true);
            tfPorta.setEnabled(true);
            tfServer.setEnabled(true);
            btConecta.setText("Conecta");
            conectado = false;

//            JOptionPane.showMessageDialog(this, "Não desconecta ou já desconectado", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    // click do botão de envio de mensagem
    private void btEnviaActionPerformed(java.awt.event.ActionEvent evt) {
        String text = txtAreaSend.getText();
        String name = message.getName();
        message = new ChatMessage(name);
        if (listOnlines.getSelectedIndex() > -1) {
            message.setNameReserved((String) listOnlines.getSelectedValue());
            message.setAction(Action.SEND_ONE);
            listOnlines.clearSelection();
        } else {
            message.setAction(Action.SEND_ALL);
        }
        if (!text.isEmpty()) {
            message.setText(text);

            txtAreaReceive.append("Voce disse: " + text + "\n");
            service.send(message);
        }
        txtAreaSend.setText("");

    }
    // evitar multiplos cliques em radio button
    private void jRadioButtonTCPMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        jRadioButtonTCP.setSelected(true);
        jRadioButtonUDP.setSelected(false);
    }
    // evitar multiplos cliques em radio button
    private void jRadioButtonUDPMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        jRadioButtonTCP.setSelected(false);
        jRadioButtonUDP.setSelected(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Messenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Messenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Messenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Messenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Messenger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btConecta;
    private javax.swing.JButton btEnvia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonTCP;
    private javax.swing.JRadioButton jRadioButtonUDP;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList listOnlines;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPorta;
    private javax.swing.JTextField tfServer;
    private javax.swing.JTextArea txtAreaReceive;
    private javax.swing.JTextArea txtAreaSend;
    // End of variables declaration                   
}
