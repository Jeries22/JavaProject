package application;

import java.io.Serializable;

import javafx.scene.image.Image;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String pName;
	private String Ptype;
	private Byte[] image;
	
	public Product(String id, String pName, String Ptype)
	{
		this.id = id;
		this.pName = pName;
		this.Ptype = Ptype;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		System.out.println("ID set to "+id);
	}
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
		System.out.println("pName set to "+pName);
	}
	
	public String getPtype() {
		return Ptype;
	}
	public void setPtype(String Ptype) {
		this.Ptype = Ptype;
		System.out.println("Ptype set to "+Ptype);
	}
	

	public String toString(){
		return String.format("\nID: %s\t %s\t %s\n",id,pName,Ptype);
	}
}
