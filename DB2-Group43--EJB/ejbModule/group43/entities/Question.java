package group43.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * Entity implementation class for Entity: Question
 *
 */
@Entity
@Table(name="question", schema = "db_project_db2")
@NamedQuery(name = "Question.findQuestionsByQuestionnaireId", query = "SELECT q FROM Question q WHERE q.questionnaire.idquestionnaire = :questId")
@NamedQuery(name = "Question.findQuestionsByQuestionnaireIdAndNumber", query = "SELECT q FROM Question q WHERE q.questionnaire.idquestionnaire = :questId AND q.numberquestion = :numQuest")
public class Question implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquestion;
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "idquestionnaire")
	private Questionnaire questionnaire;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "question")
	private List<Answer> answer;
	
	private int numberquestion;

	public Question() {
		super();
	}

	public int getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(int idquestion) {
		this.idquestion = idquestion;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumberquestion() {
		return numberquestion;
	}

	public void setNumberquestion(int numberquestion) {
		this.numberquestion = numberquestion;
	}
	
	
   
}
