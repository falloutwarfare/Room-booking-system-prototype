package objects;

import java.io.Serializable;

abstract class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	
	public Person() {
		
	}
	
	public Person(String username, String password) {
		
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
