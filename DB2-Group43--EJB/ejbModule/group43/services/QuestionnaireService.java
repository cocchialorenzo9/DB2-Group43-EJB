package group43.services;


import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.Product;
import group43.entities.Questionnaire;
import group43.entities.User;
import group43.exceptions.QuestionnaireException;
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
	
	public Questionnaire findQuestionnaireOfTheDay() throws QuestionnaireException {
		// List<Questionnaire> questionnaires = em.createQuery("Select q from Questionnaire q where CAST(q.date AS date) = CURRENT_DATE", Questionnaire.class).getResultList();
				
		List<Questionnaire> questionnaires = null;
		
		try{
			questionnaires = em.createNamedQuery("Questionnaire.findQuestionnaireOfTheDay", Questionnaire.class)
					.getResultList();
		} catch (PersistenceException e) {
			throw new QuestionnaireException("Can't retrieve today's questionnaire");
		}
				
		
		if(!questionnaires.isEmpty()) {
			Questionnaire questionnaireOfTheDay = questionnaires.get(0);
			return questionnaireOfTheDay;
		}
		else 
			return null;
	}
	
	public boolean isAlreadyDayOfAnotherQuestionnaire(Date date) throws QuestionnaireException {		
		List<Questionnaire> questionnaires = null;
		
		try {
			questionnaires = em.createNamedQuery("Questionnaire.findQuestionnaireByDate", Questionnaire.class)
					.setParameter("date", date)
					.getResultList();
		} catch (PersistenceException e) {
			throw new QuestionnaireException("Can't retrieve the questionnaire for the date " + date.toString());
		}
		
		for(int i=0; i< questionnaires.size(); i++)
			System.out.println(" found__ " + i + " : " + questionnaires.get(i).getDate());
		
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
