package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.entities.OffensiveWord;
import group43.exceptions.OffensiveWordException;


@Stateless
public class OffensiveWordService {
	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;
	
	public List<OffensiveWord> findAllOffensiveWords() throws OffensiveWordException {
		List<OffensiveWord> words = null;
		try {
			words = em.createNamedQuery("OffensiveWord.findAll", OffensiveWord.class).getResultList();

		} catch (PersistenceException e) {
			throw new OffensiveWordException("Cannot load offensive words");

		}
		return words;
	}
}

