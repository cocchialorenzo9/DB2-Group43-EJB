package group43.services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import group43.exceptions.CredentialsException;

import javax.persistence.NonUniqueResultException;
import group43.entities.User;

import java.util.List;

@Stateless
public class UserService {
	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;

	public UserService() {
	}

	public User checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (uList.isEmpty())
			return null;
		else if (uList.size() == 1)
			return uList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");

	}
	
	public boolean usernameExists (String username) throws CredentialsException  {
		
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.findUserByUsername", User.class).setParameter("usrn", username).getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify username");
		}
		 if(uList.isEmpty() == false) {
			 return true;
		 }else {
			 return false;
		 }
	
		
	}
	
	public boolean emailExists (String email) throws CredentialsException  {
		
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.findUserByEmail", User.class).setParameter("mail", email).getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify username");
		}
		 if(uList.isEmpty() == false) {
			 return true;
		 }else {
			 return false;
		 }
	
		
	}
	
	
	public void  createUser (String username, String email, String password ) {
		
		User user = new User(username, password, email);
		// for debugging: let's check if mission is managed
		System.out.println("Method createUser");
		System.out.println("Is user object managed?  " + em.contains(user));

		em.persist(user); // makes also mission object managed via cascading
		
	}
	/*
	public void updateProfile(User u) throws UpdateProfileException {
		try {
			em.merge(u);
		} catch (PersistenceException e) {
			throw new UpdateProfileException("Could not change profile");
		}
	}
	*/
}
