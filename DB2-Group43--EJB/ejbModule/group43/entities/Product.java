package group43.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name="product", schema = "db_project_db2")

public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idproduct;
	
	private String name;
	
	private String image;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "idproduct")
	private List<Review> reviews;

	public Product() {
		super();
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
   
	
}
