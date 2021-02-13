package group43.services;


import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import group43.entities.Product;
import group43.entities.Questionnaire;
import group43.entities.User;
@Stateless
public class QuestionnaireService {

	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;
	
	public QuestionnaireService() {
		super();
	}
	
	public Questionnaire findQuestionnaireById(int questionnaireId) {
		Questionnaire questionnaire = em.find(Questionnaire.class, questionnaireId);
		return questionnaire;
	}
	
	public Questionnaire findQuestionnaireByProductid(int idproduct) {
		Product product = em.find(Product.class, idproduct);
		
		return product.getQuestionnaire();
	}
	
	public Questionnaire findQuestionnaireOfTheDay() {
		List<Questionnaire> questionnaires = em.createQuery("Select q from Questionnaire q where CAST(q.date AS date) = CURRENT_DATE", Questionnaire.class).getResultList();
				
		if( !questionnaires.isEmpty()) {
			Questionnaire questionnaireOfTheDay = questionnaires.get(0);
			return questionnaireOfTheDay;
		}
		else 
			return null;
	}
	
	public boolean isAlreadyDayOfAnotherQuestionnaire(Date date) {
		System.out.println(date);
		Query findQuestionnaire = em.createQuery(
				"SELECT q "
				+ "FROM Questionnaire q "
				+ "WHERE q.date = :date");
		// dates have to be the same because hours, minutes, and seconds are set to 0 everytime
		findQuestionnaire.setParameter("date", date);
		List<Questionnaire> questionnaires = findQuestionnaire.getResultList();
		
		for(int i=0; i< questionnaires.size(); i++)
			System.out.println(" finded__ " + i + " : " + questionnaires.get(i).getDate());
		
		return !questionnaires.isEmpty();
	}
	
	public Questionnaire newQuestionnaire(Date date, int iduser, int idproduct) {
		// get the admin class
		User admin = em.find(User.class, iduser);
		Product product = em.find(Product.class, idproduct);
		
		Questionnaire questionnaire = new Questionnaire(date, admin, product);
		product.setQuestionnaire(questionnaire);
		
		em.persist(questionnaire);
		
		return questionnaire;
	}
	
	public int deleteQuestionnaire(int idquestionnaire) {
		Questionnaire questionnaire = em.find(Questionnaire.class, idquestionnaire);
		
		// for cascading setting, it removes all questionnaire interactions, product, questions, answers
		// related to the questionnaire
		em.remove(questionnaire);
		
		if(em.contains(questionnaire)) {
			return -1;
		} else {
			return 1;
		}
	}
}
