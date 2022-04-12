package disney.service;

import java.util.List;
import java.util.Optional;

import disney.model.Personaje;

public interface IPersonajeService {
	
	public Personaje guardarPersonaje(Personaje personaje);
	public void eliminarPersonaje(Integer id);
	public List<Personaje> obtenerPersonajes();
	public Optional<Personaje> obtenerPersonajePorId(Integer id);
	public List<Personaje> busquedaPersonajeConPelicula(String nombre,Integer edad,Double peso,Integer idMovie);
	public List<Personaje> busquedaPersonaje(String nombre,Integer edad,Double peso);
	

}
