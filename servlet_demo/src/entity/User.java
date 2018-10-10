package entity;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;
   
	private String username;
    
    private String password;
    
    private String phone_no;
    
    public User() {
		super();
	}

	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public User(Integer id, String username, String password,String phone_no) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone_no = phone_no;
    }



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

	
}
