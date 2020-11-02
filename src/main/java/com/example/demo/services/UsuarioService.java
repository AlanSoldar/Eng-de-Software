package com.example.demo.services;

import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BibliotecaService bibliotecaService;

    @Autowired
    private ProdutoService produtoService;

    public Usuario findUsuarioById(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("usuario not found"));
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

    public Usuario autenticaUsuario(String usuario, String password) {

        Optional<Usuario> usuarioAutenticado = usuarioRepository.findByUsuarioAndPassword(usuario, password);
        if (usuarioAutenticado.isPresent()) {
            System.out.println("usuario autenticado");
            return usuarioAutenticado.get();
        } else {
            System.out.println("usuario e senha informados não são validos");
            throw new NotFoundException("usuario e senha informados não são validos");
        }
    }

    public List<Produto> findBibliotecaByUsuarioId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaService.findBibliotecasByUsuarioId(id);
        List<Produto> produtos = new ArrayList<>();

        bibliotecas.forEach(biblioteca -> produtos.add(produtoService.findProdutoById(biblioteca.getId().getProdutoId())));

        return produtos;

    }

}
