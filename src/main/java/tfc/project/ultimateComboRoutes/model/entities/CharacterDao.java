package tfc.project.ultimateComboRoutes.model.entities;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CharacterDao extends PagingAndSortingRepository<Character, Long> {

	ArrayList<Character> findAllBy();

	@Query("SELECT c from Character c WHERE LOWER(c.name) LIKE %?1%")
	ArrayList<Character> findByName(String characterName);

	boolean existsByName(String characterName);
}
