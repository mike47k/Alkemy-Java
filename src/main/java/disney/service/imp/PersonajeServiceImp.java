package disney.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import disney.model.Personaje;
import disney.repository.IPersonajeRepository;
import disney.service.IPersonajeService;

@Service
public class PersonajeServiceImp implements IPersonajeService{
	
	@Autowired
	private IPersonajeRepository personajeRepository;

	@Override
	public Personaje guardarPersonaje(Personaje personaje) {
		// TODO Auto-generated method stub
		return personajeRepository.save(personaje);
	}

	@Override
	public void eliminarPersonaje(Integer id) {
		personajeRepository.deleteById(id);
	}

	@Override
	public List<Personaje> obtenerPersonajes() {
		// TODO Auto-generated method stub
		return (List<Personaje>) personajeRepository.findAll();
	}

	@Override
	public Optional<Personaje> obtenerPersonajePorId(Integer id) {
		// TODO Auto-generated method stub
		return personajeRepository.findById(id);
	}

	@Override
	public List<Personaje> busquedaPersonaje(String nombre, Integer edad, Double peso) {
		// TODO Auto-generated method stub
		return personajeRepository.findByNombreOrEdadOrPeso(nombre, edad, peso);
	}

	@Override
	public List<Personaje> busquedaPersonajeConPelicula(String nombre, Integer edad, Double peso, Integer idMovie) {
		// TODO Auto-generated method stub
		return personajeRepository.findByNombreOrEdadOrPesoOrPeliculasId(nombre, edad, peso, idMovie);
	}

}
