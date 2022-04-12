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
import org.springframework.web.bind.annotation.RestController;

import disney.model.PeliculaSerie;
import disney.model.Personaje;
import disney.service.IGeneroService;
import disney.service.IPeliculaSerieService;
import disney.service.IPersonajeService;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieController {

	@Autowired
	private IPeliculaSerieService peliculaSerieService;
	
	@Autowired 
	private IPersonajeService personajeService;
	@Autowired
	private IGeneroService generoService;
	
	@GetMapping
	public List<PeliculaSerie> busquedaMostrarPeliculaSerie(){
		return peliculaSerieService.obtenerPeliculaSeries();
		
	}
	
	@PostMapping
	public PeliculaSerie guardarPelicuaSerie(@RequestBody PeliculaSerie peliculaSerie) {
		
		
		
		List<Personaje> personajes = peliculaSerie.getPersonajes() ;
		final PeliculaSerie pGuardada = peliculaSerieService.guardarPeliculaSerie(peliculaSerie);
		if (peliculaSerie.getPersonajes() != null) {
		
			personajes.forEach((p)->{
				Personaje auxP = new Personaje();
				List<PeliculaSerie> pelis = new ArrayList<>();
				auxP=personajeService.obtenerPersonajePorId(p.getId()).get();
				pelis = auxP.getPeliculas();
				pelis.add(pGuardada);
				auxP.setPeliculas(pelis);
				personajeService.guardarPersonaje(auxP);
			});
		}
		
		
		
		return peliculaSerieService.obtenerPeliculaSeriesPorId(pGuardada.getId()).get();
	}
	
	@GetMapping("/{id}")
	public PeliculaSerie obtenerPersonajePorId(Integer id) {
		return peliculaSerieService.obtenerPeliculaSeriesPorId(id).get();
	}
	
	@PostMapping("/{idMovies}/characters/{idChar}")
	public PeliculaSerie guardarPeliculasPersonaje(@PathVariable(value= "idMovies") Integer idMovies,@PathVariable(value= "idChar") Integer idChar) {
		Personaje per = personajeService.obtenerPersonajePorId(idChar).get();
		PeliculaSerie peli = peliculaSerieService.obtenerPeliculaSeriesPorId(idMovies).get();
		
		List<PeliculaSerie> listPelis = per.getPeliculas();
		listPelis.add(peli);
		per.setPeliculas(listPelis);
		personajeService.guardarPersonaje(per);
		return peliculaSerieService.obtenerPeliculaSeriesPorId(idMovies).get();
		
		
		
		
	}
	@DeleteMapping("/{idMovies}/characters/{idChar}")
	public PeliculaSerie eliminarPeliculasPersonaje(@PathVariable(value= "idMovies") Integer idMovies,@PathVariable(value= "idChar") Integer idChar) {
		Personaje per = personajeService.obtenerPersonajePorId(idChar).get();
		PeliculaSerie peli = peliculaSerieService.obtenerPeliculaSeriesPorId(idMovies).get();
		
		List<PeliculaSerie> listPelis = per.getPeliculas();
		listPelis.remove(peli);
		per.setPeliculas(listPelis);
		personajeService.guardarPersonaje(per);
		return peliculaSerieService.obtenerPeliculaSeriesPorId(idMovies).get();
	}
	
	@DeleteMapping("/{id}")
	public void borrarPeliculaSerie(@PathVariable(value= "id")Integer id) {
		PeliculaSerie peli = peliculaSerieService.obtenerPeliculaSeriesPorId(id).get();
		if (peli.getPersonajes() != null) {
			peli.getPersonajes().forEach((p)->{
				p.getPeliculas().remove(peli);
				personajeService.guardarPersonaje(p);
			});
		}
		if (peli.getGeneros() != null) {
			peli.getGeneros().forEach((g)->{
				g.getPeliculasG().remove(peli);
				generoService.guardarGenero(g);
			});
		}
		
		peliculaSerieService.eliminarPeliculaSerie(id);
	}
}
