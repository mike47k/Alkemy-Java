package disney.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "peliculas")
public class PeliculaSerie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String imagen;
	private String titulo;
	private LocalDate fechaDeCreacion;
	private Integer calificacion;
	
	@ManyToMany(mappedBy = "peliculas")
	private List<Personaje> personajes;
	
	@ManyToMany(mappedBy = "peliculasG")
	private List<Genero> generos;

}
