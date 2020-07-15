
import java.math.BigInteger;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staf")
public class Staf {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private BigInteger id;
	
	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "add_shtepise")
	private String addShtepise;
	
	@Column(name = "qyteti")
	private String qyteti;
	
	@Column(name = "godina")
	private String godina;
	
	@Column(name = "zyra")
	private String zyra;
	
	@Column(name = "dr_larte")
	private String drLarte;
	
	@Column(name = "departamenti")
	private String departamenti;
	
	@Column(name = "drejtoria")
	private String drejtoria;
	
	@Column(name = "dega")
	private String dega;
	
	@Column(name = "pozicioni")
	private String pozicioni;
	
	@Column(name = "nr_tel_zyre1")
	private String nrZyre1;
	
	@Column(name = "nr_tel_zyre2")
	private String nrZyre2;
	
	@Column(name = "nr_tel_6shifra1")
	private String nr6Shifra1;
	
	@Column(name = "nr_tel_6shifra2")
	private String nr6Shifra2;
	
	@Column(name = "nr_tel_41sh1")
	private String nr41sh1;
	
	@Column(name = "nr_tel_41sh2")
	private String nr41sh2;
	
	@Column(name = "nr_tel_personal1")
	private String nrPersonal1;
	
	@Column(name = "nr_tel_personal2")
	private String nrPersonal2;
	
	@Column(name = "nr_voip1")
	private String nrVoIP1;
	
	@Column(name = "nr_voip2")
	private String nrVoIP2;
	
	public Staf() {
		super();
	}

	public Staf(BigInteger id, String emri, String mbiemri, String username, String addShtepise, String qyteti,
			String godina, String zyra, String drLarte, String departamenti, String drejtoria, String dega,
			String pozicioni, String nrZyre1, String nrZyre2, String nr6Shifra1, String nr6Shifra2, String nr41sh1,
			String nr41sh2, String nrPersonal1, String nrPersonal2, String nrVoIP1, String nrVoIP2) {
		super();
		this.id = id;
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.username = username;
		this.addShtepise = addShtepise;
		this.qyteti = qyteti;
		this.godina = godina;
		this.zyra = zyra;
		this.drLarte = drLarte;
		this.departamenti = departamenti;
		this.drejtoria = drejtoria;
		this.dega = dega;
		this.pozicioni = pozicioni;
		this.nrZyre1 = nrZyre1;
		this.nrZyre2 = nrZyre2;
		this.nr6Shifra1 = nr6Shifra1;
		this.nr6Shifra2 = nr6Shifra2;
		this.nr41sh1 = nr41sh1;
		this.nr41sh2 = nr41sh2;
		this.nrPersonal1 = nrPersonal1;
		this.nrPersonal2 = nrPersonal2;
		this.nrVoIP1 = nrVoIP1;
		this.nrVoIP2 = nrVoIP2;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public String getMbiemri() {
		return mbiemri;
	}

	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddShtepise() {
		return addShtepise;
	}

	public void setAddShtepise(String addShtepise) {
		this.addShtepise = addShtepise;
	}

	public String getQyteti() {
		return qyteti;
	}

	public void setQyteti(String qyteti) {
		this.qyteti = qyteti;
	}

	public String getGodina() {
		return godina;
	}

	public void setGodina(String godina) {
		this.godina = godina;
	}

	public String getZyra() {
		return zyra;
	}

	public void setZyra(String zyra) {
		this.zyra = zyra;
	}

	public String getDrLarte() {
		return drLarte;
	}

	public void setDrLarte(String drLarte) {
		this.drLarte = drLarte;
	}

	public String getDepartamenti() {
		return departamenti;
	}

	public void setDepartamenti(String departamenti) {
		this.departamenti = departamenti;
	}

	public String getDrejtoria() {
		return drejtoria;
	}

	public void setDrejtoria(String drejtoria) {
		this.drejtoria = drejtoria;
	}

	public String getDega() {
		return dega;
	}

	public void setDega(String dega) {
		this.dega = dega;
	}

	public String getPozicioni() {
		return pozicioni;
	}

	public void setPozicioni(String pozicioni) {
		this.pozicioni = pozicioni;
	}

	public String getNrZyre1() {
		return nrZyre1;
	}

	public void setNrZyre1(String nrZyre1) {
		this.nrZyre1 = nrZyre1;
	}

	public String getNrZyre2() {
		return nrZyre2;
	}

	public void setNrZyre2(String nrZyre2) {
		this.nrZyre2 = nrZyre2;
	}

	public String getNr6Shifra1() {
		return nr6Shifra1;
	}

	public void setNr6Shifra1(String nr6Shifra1) {
		this.nr6Shifra1 = nr6Shifra1;
	}

	public String getNr6Shifra2() {
		return nr6Shifra2;
	}

	public void setNr6Shifra2(String nr6Shifra2) {
		this.nr6Shifra2 = nr6Shifra2;
	}

	public String getNr41sh1() {
		return nr41sh1;
	}

	public void setNr41sh1(String nr41sh1) {
		this.nr41sh1 = nr41sh1;
	}

	public String getNr41sh2() {
		return nr41sh2;
	}

	public void setNr41sh2(String nr41sh2) {
		this.nr41sh2 = nr41sh2;
	}

	public String getNrPersonal1() {
		return nrPersonal1;
	}

	public void setNrPersonal1(String nrPersonal1) {
		this.nrPersonal1 = nrPersonal1;
	}

	public String getNrPersonal2() {
		return nrPersonal2;
	}

	public void setNrPersonal2(String nrPersonal2) {
		this.nrPersonal2 = nrPersonal2;
	}

	public String getNrVoIP1() {
		return nrVoIP1;
	}

	public void setNrVoIP1(String nrVoIP1) {
		this.nrVoIP1 = nrVoIP1;
	}

	public String getNrVoIP2() {
		return nrVoIP2;
	}

	public void setNrVoIP2(String nrVoIP2) {
		this.nrVoIP2 = nrVoIP2;
	}

	public static Comparator<Staf> getStafComparator() {
		return stafComparator;
	}

	public static void setStafComparator(Comparator<Staf> stafComparator) {
		Staf.stafComparator = stafComparator;
	}

	// metode per renditjen
	public static Comparator<Staf> stafComparator = new Comparator<Staf>() 
	{
		@Override
		public int compare(Staf s1, Staf s2) 
		{
			// renditen sipas emrit te punonjesit
			String stafName1 = s1.getEmri().toUpperCase();
			String stafName2 = s2.getEmri().toUpperCase();

			//ascending order
			return stafName1.compareTo(stafName2);

			//descending order
			//return stafName2.compareTo(stafName1);
		}
	};

	@Override
	public String toString() {
		return "Staf [id=" + id + ", emri=" + emri + ", mbiemri=" + mbiemri + ", username=" + username
				+ ", addShtepise=" + addShtepise + ", qyteti=" + qyteti + ", godina=" + godina + ", zyra=" + zyra
				+ ", drLarte=" + drLarte + ", departamenti=" + departamenti + ", drejtoria=" + drejtoria + ", dega="
				+ dega + ", pozicioni=" + pozicioni + ", nrZyre1=" + nrZyre1 + ", nrZyre2=" + nrZyre2 + ", nr6Shifra1="
				+ nr6Shifra1 + ", nr6Shifra2=" + nr6Shifra2 + ", nr41sh1=" + nr41sh1 + ", nr41sh2=" + nr41sh2
				+ ", nrPersonal1=" + nrPersonal1 + ", nrPersonal2=" + nrPersonal2 + ", nrVoIP1=" + nrVoIP1
				+ ", nrVoIP2=" + nrVoIP2 + "]";
	}

}