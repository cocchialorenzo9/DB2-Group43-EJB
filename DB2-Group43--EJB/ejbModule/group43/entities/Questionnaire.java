package group43.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Questionnaire
 *
 */
@Entity
@Table(name="questionnaire", schema = "db_project_db2")
public class Questionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquestionnaire;
	
	private Date date;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcreator")
	private User user;
	
	// managing persist manually for the bug
	@OneToOne(fetch = FetchType.EAGER, 
			cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idproduct")
	private Product product;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private List<QuestionnaireInteraction> interactions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private List<Question> questions;

	public Questionnaire() {
		super();
	}
	
	public Questionnaire(Date date, User user, Product product) {
		this.date = date;
		this.user = user;
		this.product = product;
	}

	public int getIdquestionnaire() {
		return idquestionnaire;
	}

	public void setIdquestionnaire(int idquestionnaire) {
		this.idquestionnaire = idquestionnaire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Product getProduct() {
		return product;
	}
   
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<QuestionnaireInteraction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<QuestionnaireInteraction> interactions) {
		this.interactions = interactions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
