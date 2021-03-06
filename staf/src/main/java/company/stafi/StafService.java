

import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author enea.nako
 *
 * Kjo klase ka te gjitha metodat qe nevojiten per te marre te dhenat nga databaza duke thirrur query-te.
 */
@Service
public class StafService 
{
	@Autowired
	private StafRepository stafRepository;
	
	@Autowired
	private FaturaRepository faturaRepository;
	
	@Autowired
	private BuxhetiRepository buxhetiRepository;
	
	/**
	 * @param option, vlera qe vjen nga drop down
	 * @param value, vlera qe vjen nga input text
	 * @return array list me te dhena
	 */
	public ArrayList<Staf> findStafByAnything(String option, String value) 
	{
		ArrayList<Staf> person = new ArrayList<Staf>();
		
		// mbushet lista me te dhena ne baze te zgjedhjes se drop down
		switch (option) 
		{
		case "emriMbiemri": 
			String[] emriMbiemri = value.split(" ");
			person = stafRepository.findByEmriAndMbiemri(emriMbiemri[0], emriMbiemri[1]);
			break;
			
		case "emri": 
			person = stafRepository.findByEmri(value);
			break;
			
		case "mbiemri":
			person = stafRepository.findByMbiemri(value);
			break;
			
		case "qyteti":
			person = stafRepository.findByQyteti(value);
			break;
			
		case "godina":
			person = stafRepository.findByGodina(value);
			break;
			
		case "zyra":
			person = stafRepository.findByZyra(value);
			break;
			
		case "departamenti":
			person = stafRepository.findByDepartamenti(value);
			break;
			
		case "drejtoria":
			person = stafRepository.findByDrejtoria(value);
			break;
			
		case "dega":
			person = stafRepository.findByDega(value);
			break;
			
		case "pozicioni":
			person = stafRepository.findByPozicioni(value);
			break;
			
		case "nrZyre":
			person = stafRepository.findByNrZyre(value);
			break;
			
		case "nr6Shifra":
			person = stafRepository.findByNr6Shifra(value);
			break;
			
		case "nr41":
			person = stafRepository.findByNr41sh(value);
			break;
			
		case "nrPersonal":
			person = stafRepository.findByNrPersonal(value);
			break;
			
		case "voip":
			person = stafRepository.findByVoip(value);
			break;
		}
		
        return person;
    }
	
	/**
	 * @param s, entitet tipi Staf
	 * regjistron te dhenat e personit
	 */
	public void regjistroStaf(Staf s) 
	{
		stafRepository.save(s);
	}
	
	/**
	 * @param id, vlera qe ka ne database
	 * @return gjen personin me id perkatese
	 */
	public ArrayList<Staf> findByID(BigInteger id) 
	{
		ArrayList<Staf> staf = stafRepository.findById(id);
		return staf;
	}
	
	/**
	 * @param id, vlera qe ka ne database
	 * fshin personin
	 */
	public void delete(BigInteger id) 
	{
		ArrayList<Staf> staf = stafRepository.findById(id);
		stafRepository.delete(staf.get(0));
	}
	
	/**
	 * @param f, entitet tipi Fatura
	 * regjistron te dhenat e faturave
	 */
	public void regjistroFatura(Fatura f) 
	{
		faturaRepository.save(f);
	}
	
	/**
	 * @param muaji
	 * @param viti
	 * @param drejtoria
	 * @return, array list me fatura
	 */
	public ArrayList<Object[]> getFaturat(int muaji, int viti, String drejtoria) 
	{
		ArrayList<Object[]> faturat;
		
		// nqs zgjidhet buxheti, do merren vetem numrat qe jane specifik, pra nga tabela Buxheti, jo te varur nga drejtoria
		if(drejtoria.equals("Buxheti"))
		{
			faturat = buxhetiRepository.findBuxhetiByMuajiViti(muaji, viti);
		}
		else // nqs zgjidhet nje drejtori, do merren numrat e asaj drejtorie, pa perfshire numrat qe jane te tabela Buxheti
		{
			faturat = faturaRepository.findFaturaByMuajiVitiDrejtoria(muaji, viti, drejtoria);
		}

		return faturat;
	}
}