import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
    public static final int PORT = 5100;

    public static void main(String args[]){
        ServerSocket listenSocket = null;
        Socket commSocket = null;
        ClientsList cliList = new ClientsList();

        try{
            listenSocket = new ServerSocket(PORT);
            System.out.println("Waiting for connection...");

            while(true){
                commSocket = listenSocket.accept();

                System.out.println("Connection received from " + commSocket.getInetAddress().getHostName() + " : " + commSocket.getPort());

                ClientHandler cliHandler = new ClientHandler(commSocket, cliList);
                cliHandler.start();
            }
        }catch(IOException e){
            System.err.println(e);
        }finally{
            if(listenSocket != null){
                try{
                    listenSocket.close();
                }catch(IOException e){
                    System.out.println(e);
                }
            }
        }
    }
}