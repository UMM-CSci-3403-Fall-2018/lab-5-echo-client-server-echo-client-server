package echoserver;
import java.io.*;
import java.net.*;
import java.util.Scanner;

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

      // scanner to read from standard input
      Scanner input = new Scanner(System.in);

      // printwriter to send stuff to the server
      PrintWriter output = new PrintWriter(socket.getOutputStream());

      // scanner to read what the server sends back
      Scanner serverInput = new Scanner(socket.getInputStream());

      while(input.hasNextByte()) {
        output.print(input.nextByte());
        System.out.print(serverInput.nextByte());
      }

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
