package group43.entities;

import java.io.Serializable;
import javax.persistence.*;

import group43.utils.Roles;

import java.util.List;

/**
 * The persistent class for the usertable database table.
 * 
 */
@Entity
@Table(name = "user", schema = "db_project_db2")
@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2")

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;

	private String username;

	private String password;

	private String email;
	
	private Roles role;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "iduser")
	private List<Answer> answers;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "idcreator")
	private List<Questionnaire> questionnaires;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "iduser")
	private List<QuestionnaireInteraction> interactions;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "iduser")
	private List<Review> reviews;
	
	

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = Roles.getRoleByString(role);
		if(this.role == Roles.NONE) {
			System.err.println(this.iduser + " user:: can't understand role");
		}
	}

	

}