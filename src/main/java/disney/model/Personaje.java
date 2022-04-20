package disney.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "personaje")
public class Personaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String imagen;
	@NotBlank
	private String nombre;
	@NotBlank
	private Integer edad;
	@NotBlank
	private Double peso;
	@NotBlank
	private String historia;
	@JsonIgnoreProperties("personajes")
	 @JoinTable(
		        name = "personaje_pelicula",
		        joinColumns = @JoinColumn(name = "FK_PERSONAJE"),
		        inverseJoinColumns = @JoinColumn(name="FK_PELICULA")
		    )
	 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	 private List<PeliculaSerie> peliculas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<PeliculaSerie> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<PeliculaSerie> peliculas) {
		this.peliculas = peliculas;
	}
	 
	 

}
