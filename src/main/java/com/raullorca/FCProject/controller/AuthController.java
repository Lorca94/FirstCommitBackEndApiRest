package com.raullorca.FCProject.controller;

import com.raullorca.FCProject.entity.AppUser;
import com.raullorca.FCProject.repository.UserRepository;
import com.raullorca.FCProject.security.jwt.JwtTokenUtil;
import com.raullorca.FCProject.security.payload.JwtResponse;
import com.raullorca.FCProject.security.payload.LoginRequest;
import com.raullorca.FCProject.security.payload.MessageResponse;
import com.raullorca.FCProject.security.payload.RegisterUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository UserRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authManager,
                          UserRepository UserRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil){
        this.authManager = authManager;
        this.UserRepository = UserRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Login para un usuario
     * @param loginRequest payload de login
     * @return token
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Registra un usuario
     * @param signUpRequest payload para registro
     * @return Response entity indicando si se ha creado o no el usuario
     */
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterUserRequest signUpRequest) {

        //Comprobar username
        if(UserRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username en uso!"));
        }
        //Comprobar email
        if (UserRepository.existsByUsername(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email en uso!"));
        }
        //Se crea el usuario con la request
        AppUser user = new AppUser(signUpRequest.getUsername(),signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        UserRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado con éxito!"));
    }
}
