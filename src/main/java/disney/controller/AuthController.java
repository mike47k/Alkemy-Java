package disney.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disney.model.AuthenticationRequest;
import disney.model.AuthenticationResponse;
import disney.repository.IUsuarioRepository;
import disney.security.JWTUtil;
import disney.service.imp.MailService;
import disney.service.imp.UserDetailServiceImp;

@RequestMapping("/auth")
@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImp userDetailServiceImp;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private MailService mailService;
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> crearToken(@RequestBody AuthenticationRequest peticion){
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(peticion.getUsername(), peticion.getPassword()));
			UserDetails userDetails = userDetailServiceImp.loadUserByUsername(peticion.getUsername());
			String jwt = jwtUtil.generarToken(userDetails);
			return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.OK);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registrarUsuario(@Valid @RequestBody AuthenticationRequest usuario,BindingResult resultadoValidacion) throws IOException {
		
		if (resultadoValidacion.hasErrors()) {
			return new ResponseEntity<>("Complete todos los campos",HttpStatus.FORBIDDEN);
		} else {
			if (!usuarioRepository.existsByUsername(usuario.getUsername())) {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				usuarioRepository.save(usuario);
				
				System.out.println(mailService.sendTextEmail(usuario.getEmail(), usuario.getNombre()));
				return new ResponseEntity<>(usuario.getUsername(),HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Usuario existente",HttpStatus.FORBIDDEN);
			}
		}
		
	}

}
