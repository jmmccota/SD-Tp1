package clienteservidore;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteServidore {
    
    public static void main(String[] args) {
        boolean tcp;
        ArrayList<String> arr=new ArrayList<String>();
        
        String opcoes[] = new String[]{"1 - TCP", "2 - UDP"};
        
        int i = Integer.parseInt(((String) JOptionPane.showInputDialog(null, 
            "Selecione o tipo",
            "Tipo",
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0])).split(" ")[0]);
    
        //int i = Integer.parseInt(JOptionPane.showInputDialog("1 - TCP \n 2 -UDP "));
        if (i == 1) {
            tcp = true;
        } else {
            tcp = false;
        }
        if (tcp) {
            ExecutorCliente ec = new ExecutorCliente(tcp);
            ec.start();
            ExecutorServer es = new ExecutorServer(tcp);
            es.start();

            if (ec.isInterrupted()) {
                es.interrupt();
            }
        }else{
            TelaChatUDP t2 = new TelaChatUDP(arr);
            t2.setVisible(true);
        }

    }
}
