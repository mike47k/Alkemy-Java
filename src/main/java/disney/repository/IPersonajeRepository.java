package disney.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import disney.model.Personaje;


public interface IPersonajeRepository extends CrudRepository<Personaje, Integer> {
	
	List<Personaje> findByNombreOrEdadOrPesoOrPeliculasId(String nombre,Integer edad,Double peso, Integer idMovie);
	List<Personaje> findByNombreOrEdadOrPeso(String nombre,Integer edad,Double peso );

}