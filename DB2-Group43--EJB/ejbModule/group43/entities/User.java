package group43.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int iduser;
    private String username;

    public User() {}
    public User(int id) {
        this.iduser = id;
    }
    
    public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String toString() {
        return "Employee id: " + getIduser() + " name: " + getUsername();
    }
}
