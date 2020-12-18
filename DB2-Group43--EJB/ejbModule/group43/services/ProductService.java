package group43.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import group43.entities.Product;

@Stateless
public class ProductService {

	@PersistenceContext(unitName = "DB2-Group43-EJB")
	private EntityManager em;

	public ProductService() {
		super();
	}
	
	public Product findProductById(int productId) {
		Product product = em.find(Product.class, productId);
		return product;
	}
	
}
