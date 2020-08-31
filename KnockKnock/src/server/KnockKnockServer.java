package server;

import java.net.*;
import java.io.*;
import server.KnockKnockProtocol;

public class KnockKnockServer {

	public static void main(String[] arg) throws InterruptedException {
		if (arg.length != 1)
			throw new IllegalArgumentException();
		int port = Integer.parseInt(arg[0]);
		try (
			// user chooses unused port to run server on
			ServerSocket serverSocket = new ServerSocket(port);
			//will wait for request on server socket at port
			//when successfully connected, accept() returns new Socket object bound to that port
			//server can communicate with client over this socket
			Socket clientSocket = serverSocket.accept();
			// PrintWriter(OutputStream, bool:autoFlush)
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {
			String inputLine, outputLine;

			// Start convo with client
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			output.println(outputLine);

			while ((inputLine = input.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				output.println(outputLine);
				if (outputLine.equals("Ok, well, bye."))
					break;
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
}
