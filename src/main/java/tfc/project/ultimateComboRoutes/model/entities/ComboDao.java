package tfc.project.ultimateComboRoutes.model.entities;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComboDao extends PagingAndSortingRepository<Combo, Long> {

	@Query("SELECT c FROM Combo c WHERE c.smashCharacter = characterId")
	ArrayList<Combo> findByCharacterId(Long characterId);

	@Query("SELECT c FROM Combo c WHERE (c.smashCharacter = characterId) AND (LOWER(c.difficulty) LIKE %?2%")
	ArrayList<Combo> findByCharaterIdAndDifficulty(Long characterId, String difficulty);
}
