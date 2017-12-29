package application;
// This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * 
	 */

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	static ConnectionToClient saveClient;
	static EchoServer e = new EchoServer(5555);
	public int row;

	public Product newP;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port
	 *            The port number to connect on.
	 */

	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg
	 *            The message received from the client.
	 * @param client
	 *            The connection from which the message originated.
	 * @return
	 * @throws IOException
	 */

	public void handleMessageFromClient(Object msg, ConnectionToClient client)

	{

		if (msg instanceof Integer) {
			handleMessageFromClient((int) msg, client);
		}

		if (msg instanceof Product) {
			handleMessageFromClient((Product) msg, client);
		}

		// string="connect me"
		if (msg instanceof String) {
			if (msg.equals("connect")) {
				EchoServer.saveClient = client;

				try {
					client.sendToClient(Connector.list);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (((String) msg).contains("Check")) {
				String[] str = ((String) msg).split("-");
				try {
					boolean flag;
					flag = Connector.CheckUserExits(str[1], str[2]);
					try {
						if (flag)
							client.sendToClient("true");
						else
							client.sendToClient("False");
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	// get the selected item from combobox (a product)
	public void handleMessageFromClient(int row, ConnectionToClient client) {
		this.row = row;
	}

	// get the product itself
	public void handleMessageFromClient(Product newP, ConnectionToClient client) {
		Connector.SaveChangeInDB(newP, row);
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0]
	 *            The port number to listen on. Defaults to 5555 if no argument is
	 *            entered.
	 */
	public void connectTOServer() {
		Alert alert;
		int port = 0; // Port to listen on

		port = DEFAULT_PORT; // Set port to 5555
		EchoServer sv = new EchoServer(port);
		try {
			System.out.println("Host:" + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sv.listen(); // Start listening for connections
			String msg = "Server listening for connections on port 5555 ---- SQL connection succeeded";
			alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK);
			alert.setHeaderText("Running the server");
			alert.setTitle("Information");
			alert.show();
		} catch (Exception ex) {
			alert = new Alert(AlertType.WARNING, "ERROR - Could not listen for clients!", ButtonType.OK);
			alert.setHeaderText("Server is already running");
			alert.setTitle("Warning");
			alert.show();
		}

	}
}
// End of EchoServer class
