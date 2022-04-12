package disney.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import disney.model.Genero;
import disney.repository.IGeneroRepository;
import disney.service.IGeneroService;

@Service
public class GeneroServiceImp implements IGeneroService {
	
	@Autowired
	private IGeneroRepository generoRepository;

	@Override
	public Genero guardarGenero(Genero genero) {
		// TODO Auto-generated method stub
		return generoRepository.save(genero);
	}

	@Override
	public void eliminarGenero(Integer id) {
		// TODO Auto-generated method stub
		generoRepository.deleteById(id);
	}

	@Override
	public List<Genero> obtenerGeneros() {
		// TODO Auto-generated method stub
		return (List<Genero>) generoRepository.findAll();
	}

	@Override
	public Optional<Genero> obtenerGeneroPorId(Integer id) {
		// TODO Auto-generated method stub
		return generoRepository.findById(id);
	}

}
