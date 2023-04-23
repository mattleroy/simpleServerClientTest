package simpleClientServerTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
	public static void main(String[] args) throws IOException {
        int portNumber = 8000;
        ServerSocket serverSocket = new ServerSocket(portNumber);	// The listener socket for client connections
        System.out.println("Server started on port " + portNumber);
        
        while (true) {
            Socket clientSocket = serverSocket.accept(); // Allow the client connection
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName()); // Tells you which IP has connected
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	// Get input value from client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            int number = Integer.parseInt(in.readLine());	// Read buffered input
            boolean isPrime = checkPrime(number);			// Start checkPrime function on client value
            System.out.println(isPrime);
            
            out.println(isPrime);	// Print
            
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
    
    private static boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}
