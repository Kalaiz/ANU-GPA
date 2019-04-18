import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        System.out.println("Server has initiated");
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Server is waiting for client request");
        Socket socket =serverSocket.accept();// Accepting data from client
        System.out.println("Server has connected with a client");
        //BufferedReader converts a stream of data to Strings
        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String serverOutput=br.readLine();
        System.out.println("Server has received : " + serverOutput);

    }
}
