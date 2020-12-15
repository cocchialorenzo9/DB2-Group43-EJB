package group43.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the offensive_word database table.
 * 
 */
@Entity
@Table(name="offensive_word")
@NamedQuery(name="OffensiveWord.findAll", query="SELECT o FROM OffensiveWord o")
public class OffensiveWord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idoffensive_word", unique=true, nullable=false)
	private int idoffensiveWord;

	@Column(nullable=false, length=45)
	private String word;

	public OffensiveWord() {
	}

	public int getIdoffensiveWord() {
		return this.idoffensiveWord;
	}

	public void setIdoffensiveWord(int idoffensiveWord) {
		this.idoffensiveWord = idoffensiveWord;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}