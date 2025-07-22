package com.interview.pedidos.service;

import com.interview.pedidos.entities.Usuario;
import com.interview.pedidos.repository.UsuarioRepository;
import com.interview.pedidos.dto.AuthDTO;
import com.interview.pedidos.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
	@Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario authenticate(AuthDTO authDTO) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(authDTO.getEmail());
        if (userOpt.isEmpty())
            return null;
        Usuario usuario = userOpt.get();
        if (passwordEncoder.matches(authDTO.getPassword(), usuario.getSenha()))
            return usuario;
        return null;
    }

    public Usuario register(AuthDTO authDTO) {
        String encodedPassword = passwordEncoder.encode(authDTO.getPassword());
        Usuario newUser = new Usuario();
        newUser.setEmail(authDTO.getEmail());
        newUser.setSenha(encodedPassword);
        newUser.setNome(authDTO.getName());
        newUser.setRole(Role.USER);
        return usuarioRepository.save(newUser);
    }

	public Usuario registerAdmin(AuthDTO authDTO) {
		 String encodedPassword = passwordEncoder.encode(authDTO.getPassword());
	        Usuario newUser = new Usuario();
	        newUser.setEmail(authDTO.getEmail());
	        newUser.setSenha(encodedPassword);
	        newUser.setNome(authDTO.getName());
	        newUser.setRole(Role.ADMIN);
	        return usuarioRepository.save(newUser);
	}
	
} 