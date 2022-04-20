package disney.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disney.model.Genero;
import disney.model.PeliculaSerie;
import disney.service.IGeneroService;
import disney.service.IPeliculaSerieService;

@RestController
@RequestMapping("/gender")
public class GeneroController {
	
	@Autowired
	private IGeneroService generoService;
	
	@Autowired
	private IPeliculaSerieService peliculaSerieService;
	
	@GetMapping
	public List<Genero> mostrarGeneros() {
		return generoService.obtenerGeneros();
	}

	@PostMapping
	public Genero guardarGenero(@Valid @RequestBody Genero genero) {
		List<PeliculaSerie> peliculas = new ArrayList<>();
		if (genero.getPeliculasG() != null) {
			genero.getPeliculasG().forEach((p)->{
				peliculas.add(peliculaSerieService.obtenerPeliculaSeriesPorId(p.getId()).get());
			});
			genero.setPeliculasG(peliculas);
		}
		return generoService.guardarGenero(genero);
	}
	
	@GetMapping("/{id}")
	public Genero obtenerGeneroPorId(@PathVariable(value= "id")Integer id) {
		return generoService.obtenerGeneroPorId(id).get();
		
	}
	@DeleteMapping("/{id}")
	public void borrarGenero(@PathVariable(value= "id")Integer id) {
		generoService.eliminarGenero(id);
	}
	
}
