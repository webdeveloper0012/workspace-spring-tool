
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buxheti")
public class Buxheti 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private BigInteger id;
	
	@Column(name = "numri")
	private String numri;

	public Buxheti() {
		super();
	}

	public Buxheti(BigInteger id, String numri) {
		super();
		this.id = id;
		this.numri = numri;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNumri() {
		return numri;
	}

	public void setNumri(String numri) {
		this.numri = numri;
	}

	@Override
	public String toString() {
		return "Buxheti [id=" + id + ", numri=" + numri + "]";
	}
	
}
