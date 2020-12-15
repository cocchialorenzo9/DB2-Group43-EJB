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
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	//TODO: fix multiple foreign key (?)
	
	@ManyToOne
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



	public Answer() {
		super();
	}
   
}
