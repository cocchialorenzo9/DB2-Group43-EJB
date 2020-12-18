package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.Question;


@Stateless
public class QuestionService {

	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;

	public QuestionService() {
		super();
	}
	
	public List<Question> findQuestionsByQuestionnaireId(int questionnaireId){
		
		List<Question> questions = null;
		try {
			questions = em.createNamedQuery("Question.findQuestionsByQuestionnaireId", Question.class).setParameter("questId", questionnaireId).getResultList();

		} catch (PersistenceException e) {
			//throw new ProjectException("Cannot load projects");
			System.out.println("persistence problem");
		}
		return questions;
	}
	
	public Question findQuestionByQuestionnaireAndNumber(int questionnaireId, int numQuestion) {
		Question questions = null;
		try {
			questions = em.createNamedQuery("Question.findQuestionsByQuestionnaireIdAndNumber", Question.class).setParameter("questId", questionnaireId).setParameter("numQuest", numQuestion).getSingleResult();

		} catch (PersistenceException e) {
			//throw new ProjectException("Cannot load projects");
			System.out.println("persistence problem");
		}
		return questions;
	}
	
}
