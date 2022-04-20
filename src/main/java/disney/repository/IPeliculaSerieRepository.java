package disney.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import disney.model.PeliculaSerie;

public interface IPeliculaSerieRepository extends CrudRepository<PeliculaSerie, Integer>{
	List<PeliculaSerie> findByTituloContainingAndGenerosId(String titulo,Integer id);
	List<PeliculaSerie> findByTituloContaining(String titulo);
	List<PeliculaSerie> findByGenerosId(Integer id);
	

}