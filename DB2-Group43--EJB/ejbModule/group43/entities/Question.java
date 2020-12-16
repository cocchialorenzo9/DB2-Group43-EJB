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
	
	
   
}
