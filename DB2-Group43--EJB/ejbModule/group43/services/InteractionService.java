package group43.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import java.sql.Timestamp;
import java.util.List;

import group43.entities.*;
import group43.exceptions.LastInteractionException;
import group43.exceptions.UpdateInteractionException;

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
	
	public QuestionnaireInteraction findLastInteraction(int userId, int questionnaireId) throws LastInteractionException {
		List<QuestionnaireInteraction> interactions = null;
		try {
			interactions = em.createNamedQuery("QuestionnaireInteraction.findLastInteraction", QuestionnaireInteraction.class).setParameter("userId", userId).setParameter("questionnaireId", questionnaireId).getResultList();

		} catch (PersistenceException e) {
			throw new LastInteractionException("Cannot load the last interaction");
		}
		
		if(!interactions.isEmpty())
			return interactions.get(0);
		else
			return null;
	}
	
	
	// public void updateProfile(User u) throws UpdateProfileException - AGGIUNGERE IL THROW
	public void updateStatisticalSection(QuestionnaireInteraction interaction) throws UpdateInteractionException {
		try {
			em.merge(interaction);
		} catch (PersistenceException e) {
			throw new UpdateInteractionException("Could not change the interaction of the user to the current questionnaire");
		}
	}
}
