package application;
// This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import common.ChatIF;
import client.ChatClient;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
//comment
public class ClientConsole implements ChatIF {
	// Class variables *************************************************

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;
	public static ArrayList<Product> Products = new ArrayList<Product>();

	JOptionPane frame;
	// Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	static ChatClient client;
	
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 */

	public ClientConsole(String host, int port) {
		try {
			client = new ChatClient(host, port, this);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(frame, "Server is not connected");
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	public ClientConsole() {

	}

	// Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it sends
	 * it to the client's message handler.
	 */
	public void accept() {
		client.handleMessageFromClientUI("connect");

	}
	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message
	 *            The string to be displayed.
	 */

	public void display(Object message) {

		if (message instanceof String) {
			if (message.equals("kick")) {
				JOptionPane.showMessageDialog(frame, "You Were Kicked From Server !");
				ClientConsole.client.quit();
				System.exit(0);
			}
		}
		

		else
			setProductsArrayList(message);

	}

	@SuppressWarnings("unchecked")
	public void setProductsArrayList(Object msg) {
		ClientConsole.Products = (ArrayList<Product>) msg;
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args[0]
	 *            The host to connect to is 10.0.0.8
	 */
	public void startconnection() {
		String host = "";
		 try
		    {
		      host = ClientGUI.ip;
		    }
		    catch(Exception e)
		    {
		      host = "localhost";
		    }
		ClientConsole chat = new ClientConsole(host, DEFAULT_PORT);
		chat.accept(); // Wait for console data
	}

}
// End of ConsoleChat class
