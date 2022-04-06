package disney.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String imagen;
	
	@JoinTable(
	        name = "genero_pelicula",
	        joinColumns = @JoinColumn(name = "FK_GENERO"),
	        inverseJoinColumns = @JoinColumn(name="FK_PELICULA")
	    )
	@ManyToMany(cascade = CascadeType.ALL)
	private List<PeliculaSerie> peliculasG;

}
