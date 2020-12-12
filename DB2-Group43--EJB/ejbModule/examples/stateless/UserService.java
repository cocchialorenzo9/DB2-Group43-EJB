package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.User;

@Stateless
public class UserService {
    @PersistenceContext(unitName="DB2-Group43-EJB")
    protected EntityManager em;

    public User createUser(int id, String username) {
        User emp = new User(id);
        emp.setUsername(username);
        em.persist(emp);
        return emp;
    }

    public User removeUser(int id) {
        User emp = findUser(id);
        if (emp != null) {
            em.remove(emp);
            return emp;
        } else return null;

    }

    public User findUser(int id) {
        return em.find(User.class, id);
    }

    public Collection<User> findAllUsers() {
        Query query = em.createQuery("SELECT e FROM User e");
        return (Collection<User>) query.getResultList();
    }
}
