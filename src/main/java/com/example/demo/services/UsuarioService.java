package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuarioById(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new NotFoundException("usuario not found"));
        System.out.println("retornando usuario com id = " + id.toString());

        return usuario;

    }

    public List<Usuario> findUsuarioByName(String nome) {

        List<Usuario> usuarios = usuarioRepository.findByNome(nome);
        System.out.println("retornando lista de usuarios");

        return usuarios;

    }

    public void saveUsuario(Usuario usuario) {

        usuarioRepository.save(usuario);
        System.out.println("usuario salvo");

    }

    public void deleteUsuarioById(Long id) {

        usuarioRepository.deleteById(id);
        System.out.println("deletando com id = " + id.toString());

    }
}
