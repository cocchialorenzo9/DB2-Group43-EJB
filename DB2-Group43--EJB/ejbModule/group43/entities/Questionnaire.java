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
@Table(name="questionnaire")
public class Questionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquestionnaire;
	
	private Date date;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcreator")
	private User user;
	
	/**
	 *  product entity is persisted automatically. When Questionnaire is created, a 
	 *  Product instance is already persisted and in the db. 
	 */
	@OneToOne(fetch = FetchType.EAGER, 
			cascade = CascadeType.ALL)
//			cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idproduct")
	private Product product;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private List<QuestionnaireInteraction> interactions;
	
	/**
	 * questions instances are not already present in the persistence context when the questionnaire
	 * is persisted, for this reason PERSIST is not listed in the cascades.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire", 
			cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
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
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
}
