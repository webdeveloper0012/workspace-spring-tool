
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author enea.nako
 * 
 * Kjo klase ka te gjitha metodat qe nevojiten per te kryer te gjitha veprimet e nevojshme.
 */
@Controller
public class StafController implements ServletContextAware
{
	
	@Autowired
	private StafService stafService;
	
	private static final Logger log = LoggerFactory.getLogger(StafController.class);
	
	private ArrayList<Staf> stafi = new ArrayList<Staf>();
	private ArrayList<Object[]> faturat = new ArrayList<Object[]>();
	private String option, value;
	private XSSFWorkbook workbook;
	
	private ServletContext servletContext;
	
	/**
	 * metoda e pare qe thirret automatikisht
	 * @return faqen login
	 */
	@GetMapping("/")
    public String index() 
	{
		log.info("Method index()");

        return "redirect:/login";
    }

	/**
	 * @param model
	 * @param error, nqs kredencialet nuk jane ne rregull
	 * @return faqen login
	 */
	@GetMapping("/login") 
	public String loginGet(Model model, String error) 
	{
		log.info("Method loginGet()");
		
		if(error != null)
			model.addAttribute("info", "Kujdes te dhenat.");

		return "login";
	}
	
    /**
     * @return faqen homepage, te pare si user
     */
    @GetMapping("/homepageUser")
    public String homepageUserGet() 
    {
    	log.info("Method homepageUserGet()");
    	
        return "homepageUser";
    }
    
    /**
     * @param option, vlera qe vjen nga drop down
     * @param value, vlera qe vjen nga input text
     * @param model
     * @return tabelen me te dhenat e kerkuara, te pare si user
     */
    @PostMapping("/homepageUser")
    public String homepageUserPost(@RequestParam("option") String option, @RequestParam("txt") String value, Model model) 
    {
    	log.info("Method homepageUserPost()");
    	
    	try 
    	{
    		// input text sduhet te jete bosh
    		if(value.equals(""))
    		{
    			model.addAttribute("info", "Ploteso fushen e kerkimit.");
        		return "homepageUser";
    		}
    		else
    		{	
    			// kontrollohet nqs kerkimi eshte ne baze te emrit
    			if(option.equals("emri"))
    			{
    				// vlera ndahet ne disa nen String sipas hapesires
    				String[] emriMbiemri = value.split(" ");
    				// nqs jane 2 qeliza dmth qe personi po kerkon me emer dhe mbiemer
        			if(emriMbiemri.length == 2)
        			{
        				option = "emriMbiemri";
        			}
    			}
    			
    			// mbushet lista me te dhenat
	    		stafi = new ArrayList<>();
	        	stafi = stafService.findStafByAnything(option, value);
	        	// renditja e listes sipas emrave te personave
	        	Collections.sort(stafi, Staf.stafComparator);
	        	
	        	model.addAttribute("stafi", stafi);
	        	
	        	return "numraUser";
    		}
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    		model.addAttribute("info", "Ndodhi nje problem ne gjetjen e personave.");
    		return "homepageUser";
		}
    	
    }
    
    /**
     * @return faqen homepage, te pare si admin
     */
    @GetMapping("/homepageAdmin")
    public String homepageAdminGet() 
    {
    	log.info("Method homepageAdminGet()");
    	
        return "homepageAdmin";
    }
    
    /**
     * @param option, vlera qe vjen nga drop down
     * @param value, vlera qe vjen nga input text
     * @param model
     * @return tabelen me te dhenat e kerkuara, te pare si admin
     */
    @PostMapping("/homepageAdmin")
    public String homepageAdminPost(@RequestParam("option") String option, @RequestParam("txt") String value, Model model) 
    {
    	log.info("Method homepageAdminPost()");
    	
    	try 
    	{
    		// input text sduhet te jete bosh
    		if(value.equals(""))
    		{
    			model.addAttribute("info", "Ploteso fushen e kerkimit.");
        		return "homepageAdmin";
    		}
    		else
    		{
    			// kontrollohet nqs kerkimi eshte ne baze te emrit
    			if(option.equals("emri"))
    			{
    				// vlera ndahet ne disa nen String sipas hapesires
    				String[] emriMbiemri = value.split(" ");
    				// nqs jane 2 qeliza dmth qe personi po kerkon me emer dhe mbiemer
        			if(emriMbiemri.length == 2)
        			{
        				option = "emriMbiemri";
        			}
    			}
    			
    			// var global marrin vleren e var lokal
    			this.option = option;
    			this.value = value;
    			
    			// mbushet lista me te dhenat
    			stafi = new ArrayList<>();
            	stafi = stafService.findStafByAnything(option, value);
            	// renditja e listes sipas emrave te personave
            	Collections.sort(stafi, Staf.stafComparator);
            	
            	model.addAttribute("stafi", stafi);
            	
            	return "numraAdmin";
    		}
    		
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		model.addAttribute("info", "Ndodhi nje problem ne gjetjen e personave.");
    		return "homepageAdmin";
		}
    	
    }
    
    /**
     * @return faqen per shtimin e personi te ri
     */
    @GetMapping("/shtoPerson")
    public String shtoPersonGet() 
    {
    	log.info("Method shtoPersonGet()");
    	
        return "shtoPerson";
    }
    
    /**
     * @param emri
     * @param mbiemri
     * @param addShtepise
     * @param qyteti
     * @param godina
     * @param zyra
     * @param drLarte
     * @param departamenti
     * @param drejtoria
     * @param dega
     * @param pozicioni
     * @param nrZyre1
     * @param nrZyre2
     * @param nr6Shifra1
     * @param nr6Shifra2
     * @param nr41sh1
     * @param nr41sh2
     * @param nrPersonal1
     * @param nrPersonal2
     * @param nrVoIP1
     * @param nrVoIP2
     * @param model
     * @return tabelen me te dhenat e regjistruara, te pare si admin
     */
    @PostMapping("/shtoPerson")
    public String shtoPersonPost(@RequestParam("emri") String emri, @RequestParam("mbiemri") String mbiemri, @RequestParam("addShtepise") String addShtepise, @RequestParam("qyteti") String qyteti, 
    		@RequestParam("godina") String godina, @RequestParam("zyra") String zyra, @RequestParam("drLarte") String drLarte, @RequestParam("departamenti") String departamenti, 
    		@RequestParam("drejtoria") String drejtoria, @RequestParam("dega") String dega, @RequestParam("pozicioni") String pozicioni, @RequestParam("nrZyre1") String nrZyre1, @RequestParam("nrZyre2") String nrZyre2, 
    		@RequestParam("nr6Shifra1") String nr6Shifra1, @RequestParam("nr6Shifra2") String nr6Shifra2, @RequestParam("nr41sh1") String nr41sh1, @RequestParam("nr41sh2") String nr41sh2, 
    		@RequestParam("nrPersonal1") String nrPersonal1, @RequestParam("nrPersonal2") String nrPersonal2, @RequestParam("nrVoIP1") String nrVoIP1, @RequestParam("nrVoIP2") String nrVoIP2, Model model) 
    {
    	log.info("Method shtoPersonPost()");
    	
    	try 
    	{
    		// kontrollohet nqs emri ose mbiemri jane bosh
    		if(emri.equals("") || mbiemri.equals(""))
    		{
    			model.addAttribute("info", "Ju lutem ploteso emrin dhe mbiemrin.");
    			return "shtoPerson";
    		}
    		
    		// krijohet nje entitet dhe vendosen te dhenat e marra nga forma ketij entiteti
        	Staf shtoPerson = new Staf();
        	shtoPerson.setEmri(emri);
        	shtoPerson.setMbiemri(mbiemri);
        	shtoPerson.setUsername(emri.toLowerCase() + "." + mbiemri.toLowerCase());
        	shtoPerson.setAddShtepise(addShtepise);
        	shtoPerson.setQyteti(qyteti);
        	shtoPerson.setGodina(godina);
        	shtoPerson.setZyra(zyra);
        	shtoPerson.setDrLarte(drLarte);
        	shtoPerson.setDepartamenti(departamenti);
        	shtoPerson.setDrejtoria(drejtoria);
        	shtoPerson.setDega(dega);
        	shtoPerson.setPozicioni(pozicioni);
        	shtoPerson.setNrZyre1(nrZyre1);
        	shtoPerson.setNrZyre2(nrZyre2);
        	shtoPerson.setNr6Shifra1(nr6Shifra1);
        	shtoPerson.setNr6Shifra2(nr6Shifra2);
        	shtoPerson.setNr41sh1(nr41sh1);
        	shtoPerson.setNr41sh2(nr41sh2);
        	shtoPerson.setNrPersonal1(nrPersonal1);
        	shtoPerson.setNrPersonal2(nrPersonal2);
        	shtoPerson.setNrVoIP1(nrVoIP1);
        	shtoPerson.setNrVoIP2(nrVoIP2);
        	
        	// regjistrohet ne databaze
    		stafService.regjistroStaf(shtoPerson);
        	
    		// shtohet ne liste per tu shfaqur me pas ne tabele
        	stafi = new ArrayList<>();
        	stafi.add(shtoPerson);
        	
        	model.addAttribute("stafi", stafi);
        	model.addAttribute("info",  emri + " " + mbiemri + " u shtua me sukses!");
        	
        	return "numraAdmin";
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			model.addAttribute("info", "Ndodhi nje problem gjate shtimit. Provoje perseri.");
			return "shtoPerson";
		}
    	
    }
    
    /**
     * @param id,  merr id e personit qe do ndryshohet
     * @param model
     * @return faqen qe ndryshon te dhenat e personit
     */
    @GetMapping("/editPerson/{id}")
    public String editPersonGet(@PathVariable("id") BigInteger id, Model model) 
    {
    	log.info("Method editPersonGet()");
    	
    	try 
    	{
    		// gjendet personi me ane te id
    		Staf editPerson = stafService.findByID(id).get(0);

        	String[] qytete = {""};
        	
        	String[] drLarte = {""};
        	String[] departamenti = {""};
        	String[] drejtoria = {""};
        	String[] dega = {""};
        	
        	// kontrollohet nqs personi NUK eshte pjese e drejtuesve te larte
        	if(editPerson.getDrLarte().equals(""))
        	{
        		// plotesohen drejtuesit e larte per te pasur mundesi zgjedhje
        		drLarte = new String[] {""};
        	}
        	else // nqs personi eshte pjese e drejtuesve te larte
        	{
        		// plotesohen drejtuesit e larte per te pasur mundesi zgjedhje
        		drLarte = new String[] {""};
        	}
        	
        	if(editPerson.getDepartamenti().equals("") && !editPerson.getDrLarte().equals(""))
        	{
        		// kontrollohet nqs departamenti eshte BOSH dhe eshte pjese e drejtuesve te larte, nuk shtohet gje pasi drejtuesit jane shtuar me larte
        	}
        	else if(editPerson.getDepartamenti().equals("")) // kontrollohet nqs departamenti eshte BOSH
    		{
        		// plotesohen departamentet per te pasur mundesi zgjedhje
        		departamenti = new String[] {""};
        		
        		// plotesohen drejtorite e bazes per te pasur mundesi zgjedhje, meqe departamenti eshte bosh
    			drejtoria = new String[] {""};
    			
    			// kontrollohen nqs eshte zgjedhur drejtori ne Baze, dhe shtohen deget per secilen
    			if(editPerson.getDrejtoria().indexOf("Tiranes") != -1)
			    {
			    	dega = new String[] {""};
			    }
    			
    		}
    		
        	
        	if(editPerson.getDrejtoria().equals(""))
    		{
        		// kontrollohet nqs drejtoria eshte BOSH, nuk shtohet gje pasi departamentet jane shtuar me larte
    		}
			
        	model.addAttribute("_person", editPerson);
        	model.addAttribute("qytete", qytete);
        	model.addAttribute("drLarte", drLarte);
        	model.addAttribute("departamenti", departamenti);
        	model.addAttribute("drejtoria", drejtoria);
        	model.addAttribute("dega", dega);
        	
        	return "editPerson";
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			model.addAttribute("info", "Ndodhi nje problem ne gjetjen e personit.");
			return "homepageAdmin";
		}
    	
    }
    
    /**
     * @param id,  merr id e personit qe do ndryshohet
     * @param addShtepise
     * @param qyteti
     * @param godina
     * @param zyra
     * @param drLarte
     * @param departamenti
     * @param drejtoria
     * @param dega
     * @param pozicioni
     * @param nrZyre1
     * @param nrZyre2
     * @param nr6Shifra1
     * @param nr6Shifra2
     * @param nr41sh1
     * @param nr41sh2
     * @param nrPersonal1
     * @param nrPersonal2
     * @param nrVoIP1
     * @param nrVoIP2
     * @param model
     * @return tabelen me te dhenat e kerkuara, te pare si admin
     */
    @PostMapping("/editPerson/{id}")
    public String editPersonPost(@PathVariable("id") BigInteger id, @RequestParam("addShtepise") String addShtepise, @RequestParam("qyteti") String qyteti, @RequestParam("godina") String godina, 
    		@RequestParam("zyra") String zyra, @RequestParam("drLarte") String drLarte, @RequestParam("departamenti") String departamenti, @RequestParam("drejtoria") String drejtoria, 
    		@RequestParam("dega") String dega, @RequestParam("pozicioni") String pozicioni, @RequestParam("nrZyre1") String nrZyre1, @RequestParam("nrZyre2") String nrZyre2, 
    		@RequestParam("nr6Shifra1") String nr6Shifra1, @RequestParam("nr6Shifra2") String nr6Shifra2, @RequestParam("nr41sh1") String nr41sh1, @RequestParam("nr41sh2") String nr41sh2, 
    		@RequestParam("nrPersonal1") String nrPersonal1, @RequestParam("nrPersonal2") String nrPersonal2, @RequestParam("nrVoIP1") String nrVoIP1, @RequestParam("nrVoIP2") String nrVoIP2, Model model) 
    {
    	log.info("Method editPersonPost()");
    	
    	try 
    	{
    		// gjendet personi me ane te id
    		Staf editPerson = stafService.findByID(id).get(0);

    		// vendosen te dhenat e marra nga forma ketij entiteti pas editimit
        	editPerson.setAddShtepise(addShtepise);
        	editPerson.setQyteti(qyteti);
        	editPerson.setGodina(godina);
        	editPerson.setZyra(zyra);
        	editPerson.setDrLarte(drLarte);
        	editPerson.setDepartamenti(departamenti);
        	editPerson.setDrejtoria(drejtoria);
        	editPerson.setDega(dega);
        	editPerson.setPozicioni(pozicioni);
        	editPerson.setNrZyre1(nrZyre1);
        	editPerson.setNrZyre2(nrZyre2);
        	editPerson.setNr6Shifra1(nr6Shifra1);
        	editPerson.setNr6Shifra2(nr6Shifra2);
        	editPerson.setNr41sh1(nr41sh1);
        	editPerson.setNr41sh2(nr41sh2);
        	editPerson.setNrPersonal1(nrPersonal1);
        	editPerson.setNrPersonal2(nrPersonal2);
        	editPerson.setNrVoIP1(nrVoIP1);
        	editPerson.setNrVoIP2(nrVoIP2);
        	
        	// regjistrohet ne databaze
        	stafService.regjistroStaf(editPerson);
        	
        	// mbushet lista me te dhenat bazuar ne kerkimin e fundit
        	stafi = new ArrayList<>();
        	stafi = stafService.findStafByAnything(option, value);
        	
        	model.addAttribute("stafi", stafi);
        	model.addAttribute("info", editPerson.getEmri() + " " + editPerson.getMbiemri() + " u ndryshua me sukses!");
        	
        	return "numraAdmin";
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			model.addAttribute("info", "Ndodhi nje problem gjate ndryshimit. Provoje perseri.");
			return "homepageAdmin";
		}
    	
    }
    
    /**
     * @param id,  merr id e personit qe do fshihet
     * @param model
     * @return tabelen me te dhenat e kerkuara, te pare si admin
     */
    @GetMapping("/deletePerson/{id}")
    public String deletePersonGet(@PathVariable("id") BigInteger id, Model model) 
    {
    	log.info("Method deletePersonGet()");
    	
    	try 
    	{
    		// gjendet personi me ane te id
    		Staf fshiPerson = stafService.findByID(id).get(0);
    		
    		// fshiehet personi
    		stafService.delete(id);

    		// mbushet lista me te dhenat bazuar ne kerkimin e fundit
    		stafi = new ArrayList<>();
        	stafi = stafService.findStafByAnything(option, value);
        	
    		model.addAttribute("stafi", stafi);
    		model.addAttribute("info", fshiPerson.getEmri() + " " + fshiPerson.getMbiemri() + " u fshi me sukses!");
    		
    		return "numraAdmin";
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			model.addAttribute("info", "Ndodhi nje problem gjate fshirjes. Provoje perseri.");
			return "homepageAdmin";
		}
    	
    }
    
    /**
     * @return faqen per kerkimin e faturave
     */
    @GetMapping("/kerkoFatura")
    public String kerkoFaturaGet() 
    {
    	log.info("Method kerkoFaturaGet()");
		
    	return "kerkoFatura";
    }
    
    /**
     * @param muaji
     * @param viti
     * @param drejtoria
     * @param model
     * @return tabelen me te dhenat e kerkuara
     */
    @PostMapping("/kerkoFatura")
    public String kerkoFaturaPost(@RequestParam("muaji") String muaji, @RequestParam("viti") String viti, @RequestParam("drejtoria") String drejtoria, Model model) 
    {
    	log.info("Method kerkoFaturaPost()");
    	
    	try 
    	{
    		// te dhenat e input sduhet te jene bosh
    		if(muaji.equals("") || viti.equals("") || drejtoria.equals(""))
    		{
    			model.addAttribute("info", "Kujdes te dhenat.");
    			return "kerkoFatura";
    		}
    		
    		// mbushet lista me te dhenat
    		faturat = new ArrayList<Object[]>();
    		faturat = stafService.getFaturat(Integer.parseInt(muaji), Integer.parseInt(viti), drejtoria);
    		// renditja e listes sipas emrave te personave
    		Collections.sort(faturat, Fatura.faturaComparator);
    		
    		model.addAttribute("drejtoria", drejtoria);
    		model.addAttribute("faturat", faturat);
    		
    		return "afishoFatura";
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
			model.addAttribute("info", "Kujdes te dhenat.");
			return "kerkoFatura";
		}
    	
    }
    
    /**
     * @return faqen per te upload faturat
     */
    @GetMapping("/uploadFatura")
    public String uploadFaturaGet() 
    {
    	log.info("Method uploadFaturaGet()");
		
    	return "uploadFatura";
    }
    
    /**
     * @param file, nje file i formatit excel
     * @param model
     * @return faqen per kerkimin e faturave
     */
    @PostMapping("/uploadFatura")
    public String uploadFaturaPost(@RequestParam("file") MultipartFile file, Model model) 
    {
    	log.info("Method uploadFaturaPost()");
    	
    	try 
    	{
    		// gjetja e path-it real te file
    		byte[] bytes = file.getBytes();
    		Path path = Paths.get(servletContext.getRealPath("/excels" +  file.getOriginalFilename()));
    		Files.write(path, bytes);
    		String fileName = file.getOriginalFilename()+"";
    		String excelPath = servletContext.getRealPath("/excels" + fileName);
    		
    		// leximi i faqes se excel
    		workbook = new XSSFWorkbook(new FileInputStream(excelPath));
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        Fatura fatura;
	        boolean isFirstRow = true;
	        String[] rresht;
	        // kolonat jane: cust_invoice - month_id - customer_id - cust_name - sub_number - sub_name - sub_service - sub_inv_due_total
	        int maxColumns = 8;
	        int muaji = 0, viti = 0;
	        Cell cell;
	        
	        // kontrollohet cdo rresht i faqes
	        for(Row r : sheet) 
	        {
	        	// nqs eshte rresht i pare nuk lexohet sepse jane header
	        	if(isFirstRow)
	        	{
	        		isFirstRow = false;
	        		continue;	   
	        	}
	        	
	        	rresht = new String[maxColumns];
	        	
	        	// percdo rresht, kontrollohet cdo qelize dhe analizohen te dhenat e saj
                for(int i = 0; i < maxColumns; i++) 
                {
                  cell = r.getCell(i);
	        
                  if(cell == null)
                	  rresht[i] = "";
					else
					// kontrollohet tipi i qelizes
                    switch (cell.getCellType()) 
                    {
                    case Cell.CELL_TYPE_NUMERIC:
                    	// nqs eshte kolona e fundit (vlera e harxhuar), te dhenat merren me 2 shifra pas presjes
                    	if(i == maxColumns - 1)
                    		rresht[i] = String.format("%.2f", cell.getNumericCellValue());
                    	// nqs eshte NUK kolona e fundit, te dhenat merren PA shifra pas presjes
                    	else rresht[i] = String.format("%.0f", cell.getNumericCellValue());
                            break;
                            
                    case Cell.CELL_TYPE_STRING:
                    	rresht[i] = cell.getStringCellValue() + "";
                            break;
                            
                    case Cell.CELL_TYPE_BLANK:
                    	rresht[i] = "";
                            break;
                            
                    default:
                    	rresht[i] = "";
                    }
                }

                // formati i qelizes eshte: vvvvmm, prandaj e ndajme ne dy pjese
                viti = Integer.parseInt(rresht[1].substring(0, 4));
                muaji = Integer.parseInt(rresht[1].substring(4, 6));
                
                // krijohet nje entitet dhe mbushet me te dhena
            	fatura = new Fatura();
            	fatura.setMuaji(muaji);
            	fatura.setViti(viti);
            	fatura.setSerialFatura(rresht[0]);
            	// nr i tel vjen ne formatin 6941x, prandaj i shtojme "0" perpara
            	fatura.setNumri("0" + rresht[4]);
            	fatura.setVlera(Double.parseDouble(rresht[7]));
            	stafService.regjistroFatura(fatura);
        	}
	        
			model.addAttribute("info", "Te dhenat u ngarkuan me sukses!");
			
			return "kerkoFatura";
    	}
    	catch(Exception e) 
    	{
			model.addAttribute("info", "Ndodhi nje problem gjate ngarkimit te te dhenave. Provoje perseri.");
			return "uploadFatura";
		}
    	
    }
    
    /**
     * @param model
     * @return faqen login
     */
    @GetMapping("/logout")
    public String logout(Model model) 
    {
		log.info("Method logout()");
		
		model.addAttribute("info", "Dolet me sukses!");
		
        return "login";
    }

    /**
     * @param model
     * @return tabelen me te dhenat e kerkuara me pare, pasi anullohet pjesa e ndryshimit
     */
    @GetMapping("/back")
    public String back(Model model) 
    {
		log.info("Method back()");
		
		model.addAttribute("stafi", stafi);
    	
    	return "numraAdmin";		
    }
    
	@Override
	public void setServletContext(ServletContext servletContext) 
	{
		this.servletContext = servletContext;
	}
    
}