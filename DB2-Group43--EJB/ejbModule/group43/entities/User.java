package group43.entities;

import java.io.Serializable;
import javax.persistence.*;

import group43.utils.Roles;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the usertable database table.
 * 
 */
@Entity
@Table(name = "user", schema = "db_project_db2")
@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2")
@NamedQuery(name = "User.findUserByUsername", query = "SELECT r FROM User r  WHERE r.username = :usrn")
@NamedQuery(name = "User.findUserByEmail", query = "SELECT r FROM User r  WHERE r.email = :mail")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;

	private String username;

	private String password;

	private String email;
	
	private boolean blocked;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@OneToMany(mappedBy = "user")
	private List<Answer> answers = new ArrayList<>();
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "user")
	private List<Questionnaire> questionnaires = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<QuestionnaireInteraction> interactions = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Review> reviews = new ArrayList<>();
	
	

	public User() {
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = Roles.USER;
		this.blocked = false;
	}

	public int getIduser() {
		return iduser;
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

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	public List<QuestionnaireInteraction> getInteractions() {
		return interactions;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	
}