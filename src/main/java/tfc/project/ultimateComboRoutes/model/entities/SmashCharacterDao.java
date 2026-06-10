package tfc.project.ultimateComboRoutes.model.entities;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmashCharacterDao extends PagingAndSortingRepository<SmashCharacter, Long> {

	ArrayList<SmashCharacter> findAllBy();

	@Query("SELECT c from SmashCharacter c WHERE LOWER(c.name) LIKE %?1%")
	ArrayList<SmashCharacter> findByName(String characterName);

	boolean existsByName(String characterName);
}
