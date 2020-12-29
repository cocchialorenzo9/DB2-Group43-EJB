package group43.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import group43.entities.Product;
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
	
	public List<Product> findProductsByCreatorId(int idcreator){
		Query findAllInteractions = em.createQuery(
				"SELECT p "
				+ "FROM Product p JOIN p.questionnaire q "
				+ "WHERE p.questionnaire.user.iduser = :iduser");
		findAllInteractions.setParameter("iduser", idcreator);
		
		List<Product> allProducts = null;
		try {
			allProducts = ListCaster.castList(Product.class, findAllInteractions.getResultList());
		} catch (ClassCastException e) {
			System.out.println("Problems in casting the product list");
			// BadCastProductsException
		}
		
		
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
