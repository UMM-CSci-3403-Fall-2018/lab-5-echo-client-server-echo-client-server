package echoserver;
import java.io.*;
import java.net.*;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException{
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // connect to server
            Socket socket = new Socket(server, portNumber);

            // Buffered Reader to read from standard input
            InputStream stdin = System.in;

            // outputStream to send stuff to the server
            OutputStream output = socket.getOutputStream();

            // Get the input stream so we can read from that socket
            InputStream serverInput = socket.getInputStream();

            byte[] userInput = new byte[1];
            byte[] echo = new byte[1];

            // if no more bytes were read, break out of the loop
            while(stdin.read(userInput) != -1) {
                output.write(userInput[0]);
                serverInput.read(echo);
                System.out.write(echo[0]);
            }
            // sometimes you just gotta flush
            System.out.flush();

            output.close();
            stdin.close();
            serverInput.close();
            socket.close();

        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}
