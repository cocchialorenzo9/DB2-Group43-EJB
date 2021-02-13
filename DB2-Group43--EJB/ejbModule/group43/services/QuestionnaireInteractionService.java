package group43.services;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import group43.entities.Questionnaire;
import group43.entities.QuestionnaireInteraction;
import group43.entities.User;
import group43.utils.ListCaster;
import group43.exceptions.LastInteractionException;
import group43.exceptions.QuestionnaireInteractionException;
import group43.exceptions.UpdateInteractionException;
@Stateless

public class QuestionnaireInteractionService {
	

	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;
	
	public QuestionnaireInteractionService() {
		super();
	}
	
	public List<QuestionnaireInteraction> findInteractionOfTheDay () throws QuestionnaireInteractionException{
		List<QuestionnaireInteraction> qIList = null;
		try {
			qIList = em.createNamedQuery("QuestionnaireInteraction.findInteractionsOfTheDay", QuestionnaireInteraction.class)
					.setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
		} catch (PersistenceException e) {
			throw new QuestionnaireInteractionException ("Could not find questionnaire interaction");
		}
		return qIList;
	}

	
	public List<QuestionnaireInteraction> findInteractionsByQuestionnaireId(int idquestionnaire){
		Query findAllInteractions = em.createQuery(
				"SELECT q "
				+ "FROM QuestionnaireInteraction q "
				+ "WHERE q.questionnaire.idquestionnaire = :idquestionnaire");
		findAllInteractions.setParameter("idquestionnaire", idquestionnaire);
		
		List<QuestionnaireInteraction> qIList = null;
		try {
			qIList = ListCaster.castList(QuestionnaireInteraction.class, findAllInteractions.getResultList());
		} catch (ClassCastException e) {
			System.out.println("Problems in casring questionnaire interactions");
			// BadCastInteractionsException
		}
		
		return qIList;
	}
	
	public void insertInteraction(int userId, int questionnaireId) {
		User user = em.find(User.class, userId);	
		Questionnaire questionnaire = em.find(Questionnaire.class, questionnaireId);
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		//create interaction
		QuestionnaireInteraction interaction = new QuestionnaireInteraction(user, questionnaire, false, date);
		
		//update both size of the relationships
		user.getInteractions().add(interaction);
		questionnaire.getInteractions().add(interaction);
		
		//persist the new entity
		em.persist(interaction);

	}
	
	public void UpdateInteraction(int interactionId) throws LastInteractionException {
		QuestionnaireInteraction interaction = em.find(QuestionnaireInteraction.class, interactionId);
		interaction.setLogtimestamp(new Timestamp(System.currentTimeMillis()));
		
		try {
			em.flush(); // ensures status updated in the database as soon as possible
		} catch (PersistenceException e) {
			throw new LastInteractionException("Cannot update the last interaction");
		}
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
	
	
	public void updateStatisticalSection(int userId, int questionnaireId, int age, String sex, String explevel) throws UpdateInteractionException, LastInteractionException {
		
		QuestionnaireInteraction interaction = null;
		try {
			interaction = em.createNamedQuery("QuestionnaireInteraction.findLastInteraction", QuestionnaireInteraction.class).setParameter("userId", userId).setParameter("questionnaireId", questionnaireId).getSingleResult();
		} catch (PersistenceException e) {
			throw new LastInteractionException("Cannot load the last interaction");
		}
		
		if(age != 0)
			interaction.setAge(age);
		if(explevel != null)
			interaction.setExpertise_level(explevel);
		if(sex != null)
			interaction.setSex(sex);
		
		interaction.setCompleted(true);
		try {
			em.flush(); // ensures status updated in the database as soon as possible
		} catch (PersistenceException e) {
			throw new LastInteractionException("Cannot update the last interaction");
		}
	}
	
	
}
