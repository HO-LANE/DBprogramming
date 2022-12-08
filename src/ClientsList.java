import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

class ClientsList
{
    private ArrayList<BufferedWriter> cliList;

    public ClientsList(){
        cliList = new ArrayList<BufferedWriter>();
    }

    public synchronized void addClient(BufferedWriter bw) {
        cliList.add(bw);
    }

    public synchronized void removeClient(BufferedWriter bw) {
        cliList.remove(bw);
    }

    public synchronized void sendDataToAllClients(BufferedWriter fromBw, String msg){
        try{
            for(BufferedWriter bw : cliList){
                if(bw != fromBw){
                    bw.write(msg + "\r\n");
                    bw.flush();
                }
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }
}