package disney.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "personaje")
public class Personaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String imagen;
	private String nombre;
	private Integer edad;
	private Double peso;
	private String historia;
	
	 @JoinTable(
		        name = "personaje_pelicula",
		        joinColumns = @JoinColumn(name = "FK_PERSONAJE"),
		        inverseJoinColumns = @JoinColumn(name="FK_PELICULA")
		    )
	 @ManyToMany(cascade = CascadeType.ALL)
	 private List<PeliculaSerie> peliculas;

}
