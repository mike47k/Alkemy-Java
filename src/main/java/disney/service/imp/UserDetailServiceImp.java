package disney.service.imp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import disney.repository.IUsuarioRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return new User(usuarioRepository.findByUsername(username).getUsername(), usuarioRepository.findByUsername(username).getPassword(), new ArrayList<>());
	}

}
