package group43.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import group43.entities.*;
import group43.utils.ListCaster;


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
		
		Answer answer = new Answer(user, question, text);
		
		//update both side of the relationship
		question.getAnswers().add(answer);
		user.getAnswers().add(answer);
		
		em.persist(answer);
		
		System.out.println("Method insertAnswer");
		System.out.println("Is answer object managed?  " + em.contains(answer));
	}
	
	public List<Answer> findAnswersByQuestionnaireId(int idquestionnaire){
		Query findAllAnswers = em.createQuery(
				"SELECT a "
				+ "FROM Answer a "
				+ "WHERE a.question.questionnaire.idquestionnaire = :idquestionnaire");
		findAllAnswers.setParameter("idquestionnaire", idquestionnaire);
		
		List<Answer> answerList = null;
		try {
			answerList = ListCaster.castList(Answer.class, findAllAnswers.getResultList());
		} catch (ClassCastException e) {
			System.out.println("Problems in casting questionnaire answers");
			// BadCastAnsersException
		}
		
		return answerList;
	}
}
