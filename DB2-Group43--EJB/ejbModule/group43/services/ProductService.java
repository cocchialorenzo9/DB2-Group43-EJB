package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import group43.entities.Product;
import group43.exceptions.ProductException;
import group43.utils.ListCaster;

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
	
	public List<Product> findProductsByCreatorId(int idcreator) throws ProductException {		
		List<Product> allProducts = null;
		try {
			allProducts = em.createNamedQuery("Product.findByAdminId", Product.class)
					.setParameter("iduser", idcreator)
					.getResultList();
		} catch (PersistenceException e) {
			throw new ProductException("Can not find all the products by id of the admin");
		}
		
		// force merging
		for(Product p: allProducts) {
			Product fakeP = em.find(Product.class, p.getIdproduct());
			System.out.println("Contains product " + fakeP.getName() + "? " + em.contains(fakeP));
			System.out.println("Contains its questionnaire? " + em.contains(fakeP.getQuestionnaire()));
			System.out.println("Product " + p.getName() + " has questionnaire " + p.getQuestionnaire().getIdquestionnaire());
			
		}
		
		// em.merge(allProducts);
		
		return allProducts;
	}
	
	public Product newProduct(String name, String image) {		
		System.out.println("Adding product " + name);
		
		// create a new product and persist it
		Product product = new Product(name, image);
		
		em.persist(product);
		
		System.out.println("insertedProduct");
		
		return product;
	}
	
}
