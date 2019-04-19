import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        System.out.println("Server has initiated");
        ServerSocket serverSocket = new ServerSocket(5005);
        System.out.println("Server is waiting for client request");
        Socket listeningSocket = serverSocket.accept();// Accepting data from client
        System.out.println("Server has connected with a client");
        InputStreamReader streamReader =new InputStreamReader(listeningSocket.getInputStream());
        BufferedReader br=new BufferedReader(streamReader);
        String listeningSocketData=br.readLine();
        System.out.println("Server has received : " + listeningSocketData);

    }
}