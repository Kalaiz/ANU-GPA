import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.*;



public class Server {
    public static void main(String[] args) throws Exception{
        System.out.println("Server has initiated");
        ServerSocket serverSocket = new ServerSocket(5005);
        System.out.println("Server is waiting for client request");
        Socket listeningSocket = serverSocket.accept();// Accepting data from client
        System.out.println("Server has connected with a client");
        InputStreamReader streamReader =new InputStreamReader(listeningSocket.getInputStream());
        BufferedReader br=new BufferedReader(streamReader);
        String time = LocalTime.now().toString().substring(0,5);
        String listeningSocketData= "Feedback received on " + LocalDate.now()+ " at " + time +"\n";
        String temp="";
        try{
            while(true){
                temp=br.readLine();
                if(temp!=null){
                    listeningSocketData+=temp+ " ";}
                else{
                    break;
                }
            }
        }
        catch (IOException i){

        }


        System.out.println("Server has received : " + listeningSocketData);

    }
}
