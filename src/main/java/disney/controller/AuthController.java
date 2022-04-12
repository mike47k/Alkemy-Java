package disney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disney.model.AuthenticationRequest;
import disney.model.AuthenticationResponse;
import disney.security.JWTUtil;
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
	
	@PostMapping("/authenticate")
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

}
