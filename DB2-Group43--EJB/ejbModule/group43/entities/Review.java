package group43.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Review
 *
 */
@Entity
@Table(name="review", schema = "db_project_db2")

public class Review implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreview;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idproduct")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	private String text;

	public Review() {
		super();
	}

	public int getIdreview() {
		return idreview;
	}

	public void setIdreview(int idreview) {
		this.idreview = idreview;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public User getUser() {
		return user;
	}
	
	
   
}
