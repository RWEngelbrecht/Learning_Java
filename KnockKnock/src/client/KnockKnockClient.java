package client;

import java.net.*;
import java.io.*;

public class KnockKnockClient {
	public static void main(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException();
		String host = args[0];
		int port = Integer.parseInt(args[1]);

		try (
			Socket kkSocket = new Socket(host, port);
			PrintWriter output = new PrintWriter(kkSocket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String frmServer;
			String frmUser;

			while ((frmServer = input.readLine()) != null) {
				System.out.println("Server: " + frmServer);
				if (frmServer.equals("Ok, well, bye."))
					break;

				frmUser = stdIn.readLine();
				if (frmUser != null) {
					System.out.println("Client: "+frmUser);
					output.println(frmUser);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}

	}
}
