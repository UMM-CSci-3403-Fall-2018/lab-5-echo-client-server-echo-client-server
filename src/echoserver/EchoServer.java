package echoserver;
import java.io.*;
import java.net.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            // Start listening on the specified port
            ServerSocket sock = new ServerSocket(portNumber);

            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects, thereby requesting a date
                Socket client = sock.accept();
                System.out.println("Got a request!");

                // Construct a writer so we can write to the socket, thereby
                // sending something back to the client.
                OutputStream clientOut = client.getOutputStream();

                // Get input from the client
                InputStream clientIn = client.getInputStream();

                int input;
                while((input = clientIn.read()) != -1) {
                    clientOut.write(input);
                }
                clientOut.flush();

                // Close the client socket since we're done.
                client.close();
            }
            // *Very* minimal error handling.
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}
