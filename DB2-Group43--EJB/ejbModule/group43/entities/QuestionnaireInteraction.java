package group43.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: QuestionnaireInteraction
 *
 */
@Entity
@Table(name="questionnaire_interaction", schema = "db_project_db2")
@NamedQuery(name = "QuestionnaireInteraction.findLastInteraction", query = "SELECT i FROM QuestionnaireInteraction i WHERE i.logtimestamp = (SELECT MAX(i2.logtimestamp) FROM QuestionnaireInteraction i2 WHERE i2.user.iduser = :userId AND i2.questionnaire.idquestionnaire = :questionnaireId)")
@NamedQuery(name = "QuestionnaireInteraction.findInteractionsOfTheDay", query = "SELECT i FROM QuestionnaireInteraction i WHERE CAST(i.logtimestamp AS date) = CURRENT_DATE AND i.completed = 1" )
public class QuestionnaireInteraction implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquestionnaire_interaction;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idquestionnaire")
	private Questionnaire questionnaire;
	
	private Timestamp logtimestamp;
	
	private boolean completed;
	
	
	private int score;
	
	private int age; 
	
	private String expertise_level;
	
	private String sex;

	public QuestionnaireInteraction() {
		super();
	}

	public int getIdquestionnaire_interaction() {
		return idquestionnaire_interaction;
	}

	public void setIdquestionnaire_interaction(int idquestionnaire_interaction) {
		this.idquestionnaire_interaction = idquestionnaire_interaction;
	}


	public Timestamp getLogtimestamp() {
		return logtimestamp;
	}

	public void setLogtimestamp(Timestamp logtimestamp) {
		this.logtimestamp = logtimestamp;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public QuestionnaireInteraction(User user, Questionnaire questionnaire, boolean completed, Timestamp date) {
		this.user = user;
		this.questionnaire = questionnaire;
		this.completed = completed;
		this.logtimestamp = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getExpertise_level() {
		return expertise_level;
	}

	public void setExpertise_level(String expertise_level) {
		this.expertise_level = expertise_level;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public User getUser() {
		return user;
	}

}
