package disney.service;

import java.util.List;
import java.util.Optional;

import disney.model.Genero;

public interface IGeneroService {
	
	public Genero guardarGenero(Genero genero);
	public void eliminarGenero(Integer id);
	public List<Genero> obtenerGeneros();
	public Optional<Genero> obtenerGeneroPorId(Integer id);

}
