import java.io.IOException;
import java.net.Socket;

public class ClientMain
{
    // public static final String HOST = "localhost";
    public static final String HOST = "192.168.218.226";

    public static final int PORT = 5100;

    public static void main(String args[]){
        Socket cliSocket = null;
        try{
            cliSocket = new Socket(HOST, PORT);
            System.out.println("Connection successful");

            Protocol protocol = new Protocol(cliSocket);
            protocol.start();
        }catch(IOException e){
            System.err.println(e);
        }
    }
}