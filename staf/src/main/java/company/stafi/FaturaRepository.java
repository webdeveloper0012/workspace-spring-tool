
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturaRepository extends CrudRepository<Fatura, String>
{
	// Merren numrat e asaj drejtorie, pa perfshire numrat qe jane te tabela Buxheti
	@Query("SELECT s.emri, s.mbiemri, s.pozicioni, f.numri, f.vlera "
			+ " FROM Staf s "
			+ " JOIN Fatura f "
			+ " ON s.nr41sh1 = f.numri "
			+ " OR s.nr41sh2 = f.numri "
			+ " OR s.nrPersonal1 = f.numri "
			+ " OR s.nrPersonal2 = f.numri "
			+ " WHERE f.muaji = ?1 "
			+ " AND f.viti = ?2 "
			+ " AND s.drejtoria = ?3 "
			+ " AND f.numri NOT IN "
			+ " (SELECT b.numri FROM Buxheti b) ")
	ArrayList<Object[]> findFaturaByMuajiVitiDrejtoria(int muaji, int viti, String drejtoria);
}
