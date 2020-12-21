package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.QuestionnaireInteraction;
import group43.exceptions.QuestionnaireInteractionException;
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
			qIList = em.createNamedQuery("QuestionnaireInteraction.findInteractionsOfTheDay", QuestionnaireInteraction.class).getResultList();
		} catch (PersistenceException e) {
			throw new QuestionnaireInteractionException ("Could not find questionnaire interaction");
		}
		return qIList;
	}
}
