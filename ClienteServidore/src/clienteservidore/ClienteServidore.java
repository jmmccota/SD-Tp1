package clienteservidore;
public class ClienteServidore {
    public static void main(String[] args) {
        ExecutorCliente ec = new ExecutorCliente();

        ExecutorServer es = new ExecutorServer();
        
        ec.start();
        
        es.start();
        
        if(ec.isInterrupted()){
            es.interrupt();
        }
    }
}