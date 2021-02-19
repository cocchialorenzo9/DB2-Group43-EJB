package group43.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Questionnaire
 *
 */
@Entity
@Table(name="questionnaire")
@NamedQuery(name = "Questionnaire.findQuestionnaireOfTheDay", query = 
		"SELECT q "
		+ "FROM Questionnaire q "
		+ "WHERE q.date = CURRENT_DATE")
@NamedQuery(name = "Questionnaire.findQuestionnaireByDate", query = 
		"SELECT q "
		+ "FROM Questionnaire q "
		+ "WHERE q.date = :date")
public class Questionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquestionnaire;
	
	private Date date;
		
	@ManyToOne
	@JoinColumn(name = "idcreator")
	private User user;
	
	/**
	 *  product entity is persisted automatically. When Questionnaire is created, a 
	 *  Product instance is already persisted and in the db. 
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idproduct")
	private Product product;
	
	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private List<QuestionnaireInteraction> interactions = new ArrayList<>();
	
	/**
	 * questions instances are not already present in the persistence context when the questionnaire
	 * is persisted, for this reason PERSIST is not listed in the cascades.
	 */
	@OneToMany(mappedBy = "questionnaire", 
			cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Question> questions = new ArrayList<>();

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
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
}
