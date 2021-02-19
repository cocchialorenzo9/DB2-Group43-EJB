package group43.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name="product", schema = "db_project_db2")
@NamedQuery(name = "Product.findAll", query = 
		"SELECT p "
		+ "FROM Product p "
		+ "ORDER BY p.questionnaire.date ASC")
@NamedQuery(name="Product.findByAdminId", query="SELECT p "
		+ "FROM Product p JOIN p.questionnaire q "
		+ "WHERE p.questionnaire.user.iduser = :iduser "
		+ "ORDER BY p.questionnaire.date ASC")
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idproduct;
	
	private String name;
	
	private String image;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<Review> reviews = new ArrayList<>();
	
	// managing persist manually
	@OneToOne(mappedBy = "product",
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	private Questionnaire questionnaire;

	public Product() {
		super();
	}
	
	public Product(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
   
	
}
