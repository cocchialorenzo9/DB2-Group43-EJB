package group43.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: QuestionnaireInteraction
 *
 */
@Entity
@Table(name="questionnaire_interaction", schema = "db_project_db2")

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
	
	private Date logtimestamp;
	
	private boolean completed;
	
	private int score;

	public QuestionnaireInteraction() {
		super();
	}

	public int getIdquestionnaire_interaction() {
		return idquestionnaire_interaction;
	}

	public void setIdquestionnaire_interaction(int idquestionnaire_interaction) {
		this.idquestionnaire_interaction = idquestionnaire_interaction;
	}


	public Date getLogtimestamp() {
		return logtimestamp;
	}

	public void setLogtimestamp(Date logtimestamp) {
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
	
	
   
}
