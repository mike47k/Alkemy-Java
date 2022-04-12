package disney.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import disney.model.PeliculaSerie;
import disney.model.Personaje;
import disney.service.IPeliculaSerieService;
import disney.service.IPersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

	@Autowired
	private IPersonajeService personajeService;
	
	@Autowired
	private IPeliculaSerieService peliculaSerieService;
	
	
	@PostMapping
	public Personaje guardarPersonaje(@RequestBody Personaje personaje) {
		List<PeliculaSerie> peliculas = new ArrayList<>();
		if (personaje.getPeliculas() != null) {
			personaje.getPeliculas().forEach((p)->{
				peliculas.add(peliculaSerieService.obtenerPeliculaSeriesPorId(p.getId()).get());
			});
			personaje.setPeliculas(peliculas);
		}
		return personajeService.guardarPersonaje(personaje);
	}
	
	@GetMapping("/{id}")
	public Personaje mostrarPersonajePorId(@PathVariable(value= "id") Integer id) {
		return personajeService.obtenerPersonajePorId(id).get();
	}
	
	@GetMapping()
	public List<Personaje> busquedaPersonaje(@RequestParam(required = false) String name,
											@RequestParam(required = false) Integer age,
											@RequestParam(required = false) Double weight,
											@RequestParam(required = false) Integer movies){
		if (name == null && age == null && weight == null && movies == null) {
			return personajeService.obtenerPersonajes();
		} else {
			if (movies == null) {
				return personajeService.busquedaPersonaje(name, age, weight);
			} else {
				return personajeService.busquedaPersonajeConPelicula(name, age, weight, movies);
			}
			

		}
		
		
	}
	
	@DeleteMapping("/{id}")
	public void borrarPersonaje(@PathVariable(value= "id") Integer id) {
		personajeService.eliminarPersonaje(id);
	}
	
}
