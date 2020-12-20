package group43.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

import group43.entities.*;

@Stateless
public class InteractionService {
	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;

	public void insertInteraction(int userId, int questionnaireId) {
		User user = em.find(User.class, userId);	
		Questionnaire questionnaire = em.find(Questionnaire.class, questionnaireId);
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		QuestionnaireInteraction interaction = new QuestionnaireInteraction(user, questionnaire, false, date);
		em.persist(interaction);
		
		System.out.println("Method insertInteraction");
		System.out.println("Is answer object managed?  " + em.contains(interaction));
	}
}
