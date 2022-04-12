package disney.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import disney.model.PeliculaSerie;
import disney.repository.IPeliculaSerieRepository;
import disney.service.IPeliculaSerieService;
@Service
public class PeliculaSerieServiceImp implements IPeliculaSerieService{

	@Autowired
	private IPeliculaSerieRepository peliculaSerieRepository;
	
	@Override
	public PeliculaSerie guardarPeliculaSerie(PeliculaSerie peliculaSerie) {
		// TODO Auto-generated method stub
		return peliculaSerieRepository.save(peliculaSerie);
	}

	@Override
	public void eliminarPeliculaSerie(Integer id) {
		// TODO Auto-generated method stub
		peliculaSerieRepository.deleteById(id);
	}

	@Override
	public List<PeliculaSerie> obtenerPeliculaSeries() {
		// TODO Auto-generated method stub
		return (List<PeliculaSerie>) peliculaSerieRepository.findAll();
	}

	@Override
	public Optional<PeliculaSerie> obtenerPeliculaSeriesPorId(Integer id) {
		// TODO Auto-generated method stub
		return peliculaSerieRepository.findById(id);
	}

}
