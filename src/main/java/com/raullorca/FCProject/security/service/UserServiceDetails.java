package com.raullorca.FCProject.security.service;

import com.raullorca.FCProject.entity.AppUser;
import com.raullorca.FCProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceDetails implements UserDetailsService {
    @Autowired
    UserRepository UserRepository;

    /**
     * Obtención de los datos para el Login y creación del objeto authenticate con el email
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            //Como tenemos que logearnos con el email, buscamos el usuario utilizando este para despues poder autenticar
            //usando los datos del usuario registrado en BBDD
            AppUser user = UserRepository.findByEmail(email);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),new ArrayList<>());
        } catch (UsernameNotFoundException e){
            return (UserDetails) new UsernameNotFoundException("Email no encontrado: " + email);
        }
    }
}
