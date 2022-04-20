package disney.repository;

import org.springframework.data.repository.CrudRepository;

import disney.model.AuthenticationRequest;

public interface IUsuarioRepository extends CrudRepository<AuthenticationRequest, Integer> {
	
	AuthenticationRequest findByUsername(String username);
	boolean existsByUsername(String username);

}