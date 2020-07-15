
import java.math.BigInteger;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fatura")
public class Fatura 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private BigInteger id;
	
	@Column(name = "muaji")
	private int muaji;
	
	@Column(name = "viti")
	private int viti;
	
	@Column(name = "serial_fatura")
	private String serialFatura;
	
	@Column(name = "numri")
	private String numri;
	
	@Column(name = "vlera")
	private double vlera;

	public Fatura() {
		super();
	}

	public Fatura(BigInteger id, int muaji, int viti, String serialFatura, String numri, double vlera) {
		super();
		this.id = id;
		this.muaji = muaji;
		this.viti = viti;
		this.serialFatura = serialFatura;
		this.numri = numri;
		this.vlera = vlera;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public int getMuaji() {
		return muaji;
	}

	public void setMuaji(int muaji) {
		this.muaji = muaji;
	}

	public int getViti() {
		return viti;
	}

	public void setViti(int viti) {
		this.viti = viti;
	}

	public String getSerialFatura() {
		return serialFatura;
	}

	public void setSerialFatura(String serialFatura) {
		this.serialFatura = serialFatura;
	}

	public String getNumri() {
		return numri;
	}

	public void setNumri(String numri) {
		this.numri = numri;
	}

	public double getVlera() {
		return vlera;
	}

	public void setVlera(double vlera) {
		this.vlera = vlera;
	}
	
	public static Comparator<Object[]> getFaturaComparator() {
		return faturaComparator;
	}

	public static void setFaturaComparator(Comparator<Object[]> faturaComparator) {
		Fatura.faturaComparator = faturaComparator;
	}

	// metode per renditjen
	public static Comparator<Object[]> faturaComparator = new Comparator<Object[]>() 
	{
		@Override
		public int compare(Object[] o1, Object[] o2) 
		{
			// renditen sipas emrit te punonjesit, object[0] eshte emri i punonjesit i marre nga query.
			String stafName1 = ((String) o1[0]).toUpperCase();
			String stafName2 = ((String) o2[0]).toUpperCase();

			//ascending order
			return stafName1.compareTo(stafName2);

			//descending order
			//return stafName2.compareTo(stafName1);
		}
	};
	
	@Override
	public String toString() {
		return "Fatura [id=" + id + ", muaji=" + muaji + ", viti=" + viti + ", serialFatura=" + serialFatura
				+ ", numri=" + numri + ", vlera=" + vlera + "]";
	}
	
}
