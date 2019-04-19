import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.*;


/*Authorship:Kalai (u6555407)*/
public class Server {
    public static void main(String[] args) throws Exception {

        System.out.println("Server has initiated");
        ServerSocket serverSocket = new ServerSocket(5005);
        //making sure server runs until developer stops it.
        while (true) {
            System.out.println("Server is waiting for client request");
            Socket listeningSocket = serverSocket.accept();
            System.out.println("Server has connected with a client");
            InputStreamReader streamReader = new InputStreamReader(listeningSocket.getInputStream());
            BufferedReader br = new BufferedReader(streamReader);
            String time = LocalTime.now().toString().substring(0, 5);
            String listeningSocketData = "Feedback received on " + LocalDate.now() + " at " + time + "\n";
            String temp = "";
            try {
                listeningSocketData += "Ratings is " + br.readLine() + "\n" + "Feedback is " + "\n";
                while (true) {
                    temp = br.readLine();
                    if (temp != null) {
                        listeningSocketData += temp + " ";
                    } else {
                        break;
                    }
                }
            } catch (IOException i) {
                listeningSocketData += "\n";

            }

            System.out.println("Server has received : " + listeningSocketData);
            Writer fileWriter = new FileWriter("Feedbacks.txt", true);
            try {
                fileWriter.write(listeningSocketData);
            } catch (IOException e) {

            }
            fileWriter.close();

        }
    }
}
