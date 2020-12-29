package group43.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Answer
 *
 */
@Entity
@Table(name="answer", schema = "db_project_db2")

public class Answer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idanswer;
	
	@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinColumn(name = "iduser")
	private User user;
		
	@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idquestion")
	private Question question;
	
	private String text;
	

	public int getIdanswer() {
		return idanswer;
	}

	public void setIdanswer(int idanswer) {
		this.idanswer = idanswer;
	}
	

	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	public Answer(User user, Question question, String text) {
		this.user = user;
		this.question = question;
		this.text = text;
	}

	public Answer() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
   
}
