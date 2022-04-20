package disney.service;

import java.util.List;
import java.util.Optional;

import disney.model.PeliculaSerie;

public interface IPeliculaSerieService {
	
	public PeliculaSerie guardarPeliculaSerie(PeliculaSerie peliculaSerie);
	public void eliminarPeliculaSerie(Integer id);
	public List<PeliculaSerie> obtenerPeliculaSeries();
	public Optional<PeliculaSerie> obtenerPeliculaSeriesPorId(Integer id);
	public List<PeliculaSerie> busquedaPeliculas(String titulo,Integer id);


}