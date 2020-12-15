package group43.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the question database table.
 * 
 */
@Embeddable
public class QuestionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int idquestionnaire;

	@Column(unique=true, nullable=false)
	private int numberquestion;

	public QuestionPK() {
	}
	public int getIdquestionnaire() {
		return this.idquestionnaire;
	}
	public void setIdquestionnaire(int idquestionnaire) {
		this.idquestionnaire = idquestionnaire;
	}
	public int getNumberquestion() {
		return this.numberquestion;
	}
	public void setNumberquestion(int numberquestion) {
		this.numberquestion = numberquestion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QuestionPK)) {
			return false;
		}
		QuestionPK castOther = (QuestionPK)other;
		return 
			(this.idquestionnaire == castOther.idquestionnaire)
			&& (this.numberquestion == castOther.numberquestion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idquestionnaire;
		hash = hash * prime + this.numberquestion;
		
		return hash;
	}
}