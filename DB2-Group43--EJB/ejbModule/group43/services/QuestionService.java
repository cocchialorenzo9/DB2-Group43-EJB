package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.Question;
import group43.entities.Questionnaire;
import group43.exceptions.InvalidQuestionnaireException;
import group43.exceptions.QuestionsException;


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
	
	public Question findQuestionByQuestionnaireAndNumber(int questionnaireId, int numQuestion) throws QuestionsException {
		Question questions = null;
		try {
			questions = em.createNamedQuery("Question.findQuestionsByQuestionnaireIdAndNumber", Question.class).setParameter("questId", questionnaireId).setParameter("numQuest", numQuestion).getSingleResult();

		} catch (PersistenceException e) {
			throw new QuestionsException("Cannot load questions");
		}
		return questions;
	}
	
	public void newQuestions(Questionnaire questionnaire, List<String> questions) {		
		for(int i = 0; i < questions.size(); i++) {
			String text = questions.get(i);
			
			Question newQuestion = new Question(text, questionnaire, i);
			questionnaire.addQuestion(newQuestion);
			
			em.persist(newQuestion);
		}
	}
	
}
