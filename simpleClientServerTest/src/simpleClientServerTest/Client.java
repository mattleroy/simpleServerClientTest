package simpleClientServerTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
	public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 8000;
        Socket socket = new Socket(hostName, portNumber);
        System.out.println("Connected to server on port " + portNumber);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a number: ");
        int number = Integer.parseInt(stdIn.readLine());
        System.out.println(number);
        out.println(number);
        
        boolean isPrime = Boolean.parseBoolean(in.readLine());
        
        if (isPrime) {
            System.out.println(number + " is a prime number");
        } else {
            System.out.println(number + " is not a prime number");
        }
        
        stdIn.close();
        in.close();
        out.close();
        socket.close();
    }
}
