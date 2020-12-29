package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import group43.entities.QuestionnaireInteraction;
import group43.utils.ListCaster;
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
}
