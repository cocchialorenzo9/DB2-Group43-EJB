package group43.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import group43.entities.Questionnaire;
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
	
	public Questionnaire findQuestionnaireOfTheDay() {
		List<Questionnaire> questionnaires = em.createQuery("Select q from Questionnaire q where CAST(q.date AS date) = CURRENT_DATE", Questionnaire.class).getResultList();
		Questionnaire questionnaireOfTheDay = questionnaires.get(0);
		return questionnaireOfTheDay;
	}
}
