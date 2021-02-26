package com.curso.expert.rest.service;

import com.curso.expert.domain.entity.Usuario;
import com.curso.expert.domain.repositorio.UsuarioRepository;
import com.curso.expert.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository  usuarioRepository;

    @Transactional
    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar (Usuario usuario){
        UserDetails user =loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("Usuario não encontrado na base de dados"));

       String [] roles = usuario.isAdmin() ?
               new String[]{"ADMIN","USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }


}
