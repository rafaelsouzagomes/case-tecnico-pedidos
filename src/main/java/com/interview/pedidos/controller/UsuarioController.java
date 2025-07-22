package com.interview.pedidos.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.pedidos.dto.AuthDTO;
import com.interview.pedidos.entities.Usuario;
import com.interview.pedidos.repository.UsuarioRepository;
import com.interview.pedidos.security.JwtTokenService;
import com.interview.pedidos.service.AuthService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthDTO authDTO) {
        Usuario usuario = authService.authenticate(authDTO);
        if (usuario != null){
            String token = jwtTokenService.generateToken(usuario, usuario.getRole().name());
            return ResponseEntity.ok(Map.of(
                "success", true,
                "token", token,
                "userId", usuario.getId(),
                "email", usuario.getEmail(),
                "name", usuario.getNome()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDTO authDTO) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(authDTO.getEmail());
        if (userOpt.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");

        Usuario newUser = authService.register(authDTO);
        String token = jwtTokenService.generateToken(newUser, "USER");
        return ResponseEntity.ok(Map.of(
            "success", true,
            "userId", newUser.getId(),
            "token", token,
            "email", newUser.getEmail(),
            "name", newUser.getNome()
        ));
    }
    
    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthDTO authDTO) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(authDTO.getEmail());
        if (userOpt.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");

        Usuario newUser = authService.registerAdmin(authDTO);
        String token = jwtTokenService.generateToken(newUser, "USER");
        return ResponseEntity.ok(Map.of(
            "success", true,
            "userId", newUser.getId(),
            "token", token,
            "email", newUser.getEmail(),
            "name", newUser.getNome()
        ));
    }
} 