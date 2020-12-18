package group43.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Questionnaire
 *
 */
@Entity
@Table(name="questionnaire", schema = "db_project_db2")
public class Questionnaire implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquestionnaire;
	
	private Date date;
		
	@ManyToOne
	@JoinColumn(name = "idcreator")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "idproduct")
	private Product product;

	public Questionnaire() {
		super();
	}

	public int getIdquestionnaire() {
		return idquestionnaire;
	}

	public void setIdquestionnaire(int idquestionnaire) {
		this.idquestionnaire = idquestionnaire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Product getProduct() {
		return product;
	}
   
	public void setProduct(Product product) {
		this.product = product;
	}
}
