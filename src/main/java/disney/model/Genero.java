package disney.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String imagen;
	@JsonIgnoreProperties("generos")
	@JoinTable(
	        name = "genero_pelicula",
	        joinColumns = @JoinColumn(name = "FK_GENERO"),
	        inverseJoinColumns = @JoinColumn(name="FK_PELICULA")
	    )
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<PeliculaSerie> peliculasG;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<PeliculaSerie> getPeliculasG() {
		return peliculasG;
	}

	public void setPeliculasG(List<PeliculaSerie> peliculasG) {
		this.peliculasG = peliculasG;
	}
	
	

}
