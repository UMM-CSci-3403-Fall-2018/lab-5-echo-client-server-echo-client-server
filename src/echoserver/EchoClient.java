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
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

      // printwriter to send stuff to the server
      PrintWriter output = new PrintWriter(socket.getOutputStream());

      // Get the input stream so we can read from that socket
      InputStream input = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      String userInput;
      while((userInput = stdin.readLine()) != null) {
        output.print(userInput);
        System.out.print(reader.readLine());
      }

      output.close();
      stdin.close();
      input.close();
      reader.close();
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
