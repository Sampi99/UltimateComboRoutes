package tfc.project.ultimateComboRoutes.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdministratorDao extends PagingAndSortingRepository<Administrator, Long> {

	boolean existsByUsername(String username);

	Optional<Administrator> findByUsername(String username);

}
