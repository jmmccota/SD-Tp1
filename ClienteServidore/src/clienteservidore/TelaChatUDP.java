package clienteservidore;

import com.redes.app.bean.ChatMessage;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.ListSelectionModel;

public class TelaChatUDP extends javax.swing.JFrame {

    static String ip = "";
    static int porta;
    ArrayList<String> arr;
    //   private BufferedReader br;
    // private InputStreamReader is;

    String nome = "";

    InetAddress ipGrupo;
    MulticastSocket s;
    String msg;
    Thread thread;
    DatagramPacket datagrama;
    byte[] dados = new byte[140];

    InetAddress enderecoMulticast;
    MulticastSocket socket;
    String nomeUsuario;

    public TelaChatUDP(ArrayList<String> arra) {
        initComponents();
        jRadioButtonTCP6.setSelected(false);
        jRadioButtonUDP6.setSelected(true);
        jRadioButtonUDP6.setEnabled(false);
        jRadioButtonTCP6.setEnabled(false);
        arr = arra;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtPorta = new javax.swing.JTextField();
        btConectar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jRadioButtonTCP6 = new javax.swing.JRadioButton();
        jRadioButtonUDP6 = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        btEnviar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaServ = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaUsu = new javax.swing.JTextArea();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txOnlines = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Local do Servidor"));

        jLabel25.setText("IP do Servidor ou Nome");

        txtIp.setText("127.0.0.1");

        jLabel26.setText("Porta");

        txtPorta.setText("5555");

        btConectar.setText("Conectar");
        btConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConectarActionPerformed(evt);
            }
        });

        jLabel27.setText("Nome");

        jRadioButtonTCP6.setText("TCP");
        jRadioButtonTCP6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonTCP6MouseClicked(evt);
            }
        });

        jRadioButtonUDP6.setText("UDP");
        jRadioButtonUDP6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonUDP6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNome))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                            .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(124, 124, 124)
                        .addComponent(jLabel26)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btConectar)
                    .addComponent(jRadioButtonTCP6)
                    .addComponent(jRadioButtonUDP6))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jRadioButtonTCP6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonUDP6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btConectar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jLabel28.setText("Mensagem");

        btEnviar.setText("Envia");
        btEnviar.setEnabled(false);
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        txtAreaServ.setEditable(false);
        txtAreaServ.setColumns(20);
        txtAreaServ.setRows(5);
        jScrollPane4.setViewportView(txtAreaServ);

        txtAreaUsu.setColumns(20);
        txtAreaUsu.setRows(5);
        txtAreaUsu.setEnabled(false);
        jScrollPane5.setViewportView(txtAreaUsu);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addComponent(btEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btEnviar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Onlines"));

        txOnlines.setEditable(false);
        txOnlines.setColumns(20);
        txOnlines.setRows(5);
        jScrollPane7.setViewportView(txOnlines);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConectarActionPerformed
        ThreadUDP();
    }//GEN-LAST:event_btConectarActionPerformed

    private void jRadioButtonTCP6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonTCP6MouseClicked
        // TODO add your handling code here:
        jRadioButtonTCP.setSelected(true);
        jRadioButtonUDP.setSelected(false);
    }//GEN-LAST:event_jRadioButtonTCP6MouseClicked

    private void jRadioButtonUDP6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonUDP6MouseClicked
        // TODO add your handling code here:
        jRadioButtonTCP.setSelected(false);
        jRadioButtonUDP.setSelected(true);
    }//GEN-LAST:event_jRadioButtonUDP6MouseClicked

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        EnviarMensagem();
    }//GEN-LAST:event_btEnviarActionPerformed

    public static void main(String args[]) {
        ArrayList<String> arr=new ArrayList<String>();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new TelaChatUDP(arr).setVisible(false);
            }
        });
    }

    public void ThreadUDP() {
        if (btConectar.getText().equals("Conectar")) {
            thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        porta = Integer.parseInt(txtPorta.getText());
                        enderecoMulticast = InetAddress.getByName("233.99.77.22");
                        socket = new MulticastSocket(porta);
                        socket.joinGroup(enderecoMulticast);

                        nomeUsuario = txtNome.getText();

                        dados = (nomeUsuario + " entrou no chat").getBytes();
                        datagrama = new DatagramPacket(dados, dados.length,
                                enderecoMulticast, porta);

                        socket.send(datagrama);
                        //  System.out.println("Thread foi chamada");
                        for (int i = 0; i < dados.length; i++) {
                            dados[i] = 0;
                        }

                        new GestorReceiveUDP(socket, datagrama, nomeUsuario).start();
                        txtAreaUsu.setEnabled(true);
                        btEnviar.setEnabled(true);
                        btConectar.setText("Desconectar");
                        arr.add(nomeUsuario);
                        refreshOnlines();
                    } catch (IOException e) {
                        showMessageDialog(null, "Erro ao entrar no chat", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            );
            thread.start();
        }else{
            arr.remove(nomeUsuario);
            thread.stop();
        }

    }

    private void refreshOnlines() {
        for (int j = 0; j < arr.size(); j++) {
            txOnlines.setText(arr.get(j) + " \n");
        }
        //System.out.println("lista onlines:" + message.getSetOnlines().toString());
        //Set<String> names = message.getSetOnlines();
        //names.remove(message.getName());
        //String[] array = (String[]) names.toArray(new String[names.size()]);                
    }

    public void EnviarMensagem() {
        String mensagemEnviada = txtAreaUsu.getText();

        dados = criarStringConsole(nomeUsuario, mensagemEnviada);

        datagrama = new DatagramPacket(dados, dados.length,
                enderecoMulticast, porta);
        try {
            socket.send(datagrama);
        } catch (IOException ex) {
            showMessageDialog(null, "Mensagem não enviada", "", JOptionPane.ERROR_MESSAGE);
        }

        for (int i = 0; i < dados.length; i++) {
            dados[i] = 0;
        }
        txtAreaUsu.setText("");

    }

    public static byte[] criarStringConsole(String nomeUsuario, String mensagem) {
        byte[] mensagemConsole = new byte[140];

        StringBuffer stringMensagem = new StringBuffer();        
        stringMensagem.append(nomeUsuario);
        stringMensagem.append(" diz: ");
        stringMensagem.append(mensagem);

        mensagemConsole = stringMensagem.toString().getBytes();

        return mensagemConsole;
    }

    public static String horaAtual() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    class GestorReceiveUDP extends Thread {

        MulticastSocket socket;
        String nomeUsuario;
        byte[] dados = new byte[140];
        DatagramPacket datagrama;

        public GestorReceiveUDP(MulticastSocket skt, DatagramPacket dtg, String nmusr) {
            socket = skt;
            datagrama = dtg;
            nomeUsuario = nmusr;
        }

        public void run() {
            try {
                boolean verdadeiro = true;
                while (verdadeiro) {

                    datagrama.setData(dados);
                    socket.receive(datagrama);
                    dados = datagrama.getData();
                    String mensagem = new String(dados);

                    if ((mensagem.contains("exit"))
                            && (mensagem.contains(nomeUsuario))) {
                        verdadeiro = false;
                    }
                    txtAreaServ.setText(txtAreaServ.getText() + mensagem + "\n");

                    for (int i = 0; i < dados.length; i++) {
                        dados[i] = 0;
                    }
                }

            } catch (Exception exc) {
                System.err.println(exc.toString());
            }
        }
    }

    class GestorReceiveTCP extends Thread {

        Socket socket;
        String nomeUsuario;

        public GestorReceiveTCP(Socket skt, String nmusr) {
            socket = skt;
            nomeUsuario = nmusr;
        }

        public void run() {
            try {
                boolean verdadeiro = true;
                while (verdadeiro) {

                    String mensagem = new String(dados);

                    PrintStream saida = new PrintStream(mensagem);
                    OutputStream out = socket.getOutputStream();
                    DataOutputStream d = new DataOutputStream(out);

                    if ((mensagem.contains("exit"))
                            && (mensagem.contains(nomeUsuario))) {
                        verdadeiro = false;
                    }

                    txtAreaServ.setText(txtAreaServ.getText() + mensagem + "\n");

                }

            } catch (Exception exc) {
                System.err.println(exc.toString());
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConecta;
    private javax.swing.JButton btConecta1;
    private javax.swing.JButton btConecta2;
    private javax.swing.JButton btConecta3;
    private javax.swing.JButton btConecta4;
    private javax.swing.JButton btConecta5;
    private javax.swing.JButton btConectar;
    private javax.swing.JButton btEnviar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonTCP;
    private javax.swing.JRadioButton jRadioButtonTCP1;
    private javax.swing.JRadioButton jRadioButtonTCP2;
    private javax.swing.JRadioButton jRadioButtonTCP3;
    private javax.swing.JRadioButton jRadioButtonTCP4;
    private javax.swing.JRadioButton jRadioButtonTCP5;
    private javax.swing.JRadioButton jRadioButtonTCP6;
    private javax.swing.JRadioButton jRadioButtonUDP;
    private javax.swing.JRadioButton jRadioButtonUDP1;
    private javax.swing.JRadioButton jRadioButtonUDP2;
    private javax.swing.JRadioButton jRadioButtonUDP3;
    private javax.swing.JRadioButton jRadioButtonUDP4;
    private javax.swing.JRadioButton jRadioButtonUDP5;
    private javax.swing.JRadioButton jRadioButtonUDP6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNome1;
    private javax.swing.JTextField tfNome2;
    private javax.swing.JTextField tfNome3;
    private javax.swing.JTextField tfNome4;
    private javax.swing.JTextField tfNome5;
    private javax.swing.JTextField tfPorta;
    private javax.swing.JTextField tfPorta1;
    private javax.swing.JTextField tfPorta2;
    private javax.swing.JTextField tfPorta3;
    private javax.swing.JTextField tfPorta4;
    private javax.swing.JTextField tfPorta5;
    private javax.swing.JTextField tfServer;
    private javax.swing.JTextField tfServer1;
    private javax.swing.JTextField tfServer2;
    private javax.swing.JTextField tfServer3;
    private javax.swing.JTextField tfServer4;
    private javax.swing.JTextField tfServer5;
    private javax.swing.JTextArea txOnlines;
    private javax.swing.JTextArea txtAreaServ;
    private javax.swing.JTextArea txtAreaUsu;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPorta;
    // End of variables declaration//GEN-END:variables

}
