
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuxhetiRepository extends CrudRepository<Buxheti, String>
{
	// Merren vetem numrat qe jane specifik, pra nga tabela Buxheti, jo te varur nga drejtoria
	@Query("SELECT s.emri, s.mbiemri, s.pozicioni, f.numri, f.vlera "
			+ " FROM Staf s "
			+ " JOIN Fatura f "
			+ " ON s.nr41sh1 = f.numri "
			+ " OR s.nr41sh2 = f.numri "
			+ " WHERE f.muaji = ?1 "
			+ " AND f.viti = ?2 "
			+ " AND f.numri IN "
			+ " (SELECT b.numri FROM Buxheti b) ")
	ArrayList<Object[]> findBuxhetiByMuajiViti(int muaji, int viti);
}
