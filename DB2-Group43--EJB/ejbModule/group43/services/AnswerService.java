package group43.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.*;
import group43.exceptions.AnswersException;


@Stateless
public class AnswerService {

	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;
	@EJB(name = "group43.services/QuestionService")
	private QuestionService qService;
	
	public AnswerService() {
		super();
	}
	
	public void insertAnswer(int userId, String text, int questionId) {
		User user = em.find(User.class, userId);
		
		// checking the correctness of the insertion process
		if(user.isBlocked())
				return;
		
		Question question = em.find(Question.class, questionId);
		
		Answer answer = new Answer(user, question, text);
		
		//update both side of the relationship
		question.getAnswers().add(answer);
		user.getAnswers().add(answer);
		
		em.persist(answer);
		
		System.out.println("Method insertAnswer");
		System.out.println("Is answer object managed?  " + em.contains(answer));
	}
	
	public List<Answer> findAnswersByQuestionnaireId(int idquestionnaire) throws AnswersException{
		List<Answer> answerList = null;
		
		try {
			answerList = em.createNamedQuery("Answer.findAnswersByQuestionnaireId", Answer.class)
					.setParameter("idquest", idquestionnaire)
					.getResultList();
		} catch (PersistenceException e) {
			throw new AnswersException("Can't retrieve the answers of this questionnaire");
		}
		
		return answerList;
	}
}
