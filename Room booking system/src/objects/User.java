package objects;

import java.io.Serializable;

public class User extends Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public User() {
		
	}
	
	public User(String username, String password) {
		
		super(username, password);
		
		this.username = username;
		this.password = password;
		
	}
	
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
}
