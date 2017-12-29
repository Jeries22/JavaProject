package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Connector {
	private static Connection con;
	private Statement statement;
	private String query = "SELECT * FROM zrle.products";
	private ResultSet rs;
	public static int flag = 1;
	private static Statement stmt;
	static JOptionPane frame;
	public static ArrayList<Product> list = new ArrayList<Product>();

	public void connectToDB() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
		    this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zrle","root","Braude");
			System.out.println("SQL connection succeed");

		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	

		try {
			statement = con.createStatement();
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (rs.next()) {
			list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3),rs.getBytes(4)));

		}

	}

	public static void SaveChangeInDB(Product p, int row) {
		list.set(row, p);
		String q = "UPDATE zrle.products SET productName = '" + p.getpName() + "' WHERE productID = '" + p.getId()
				+ "';";

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(q);
		

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean CheckUserExits(String userID, String userPass) throws SQLException {
		boolean flag2 = false;
		Statement statment;
		statment = con.createStatement();
		ResultSet rs2 = statment
				.executeQuery("Select * From zrle.users where userID=" + userID + " and userPass=" + userPass + ";");
		if(rs2.next())
			flag2=true;
		return flag2;
	}

}
