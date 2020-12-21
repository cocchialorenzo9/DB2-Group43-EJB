package group43.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import group43.entities.*;


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
		Question question = em.find(Question.class, questionId);
		
		System.out.println(question.getNumberquestion());
		System.out.println(text);
		
		Answer answer = new Answer(user, question, text);
		em.persist(answer);
		
		System.out.println("Method insertAnswer");
		System.out.println("Is answer object managed?  " + em.contains(answer));
	}
}
