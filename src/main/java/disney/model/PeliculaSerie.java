package disney.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "peliculas")
public class PeliculaSerie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String imagen;
	@NotBlank
	private String titulo;
	@NotBlank
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaDeCreacion;
	@NotBlank
	@Max(value = 5)
	private Integer calificacion;
	
	@JsonIgnoreProperties("peliculas")
	@ManyToMany(mappedBy = "peliculas",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Personaje> personajes;
	
	@ManyToMany(mappedBy = "peliculasG")
	private List<Genero> generos;

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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	
	
}
