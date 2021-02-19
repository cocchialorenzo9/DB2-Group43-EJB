package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.Question;
import group43.entities.Questionnaire;
import group43.exceptions.InvalidQuestionnaireException;


@Stateless
public class QuestionService {

	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;

	public QuestionService() {
		super();
	}
	
public List<Question> findQuestionsByQuestionnaireId(int questionnaireId) throws InvalidQuestionnaireException{
		
		List<Question> questions = null;
		try {
			questions = em.createNamedQuery("Question.findQuestionsByQuestionnaireId", Question.class).setParameter("questId", questionnaireId).getResultList();

		} catch (PersistenceException e) {
			throw new InvalidQuestionnaireException("Cannot load questions for the current questionnaire");
		}
		return questions;
	}
	
	public void newQuestions(int idquestionnaire, List<String> questions) {
		Questionnaire questionnaire = em.find(Questionnaire.class, idquestionnaire);
		
		for(int i = 0; i < questions.size(); i++) {
			String text = questions.get(i);
			
			Question newQuestion = new Question(text, questionnaire);
			questionnaire.addQuestion(newQuestion);
			
			em.persist(newQuestion);
		}
	}
	
}
