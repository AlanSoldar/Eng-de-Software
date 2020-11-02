package com.example.demo.controllers;

import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {

        Usuario usuario = usuarioService.findUsuarioById(id);

        return ResponseEntity.ok().body(usuario);

    }

    @GetMapping(value = "/usuario/nome/{nome}")
    public ResponseEntity<List<Usuario>> getUsuario(@PathVariable("nome") String nome) {

        List<Usuario> usuarios = usuarioService.findUsuarioByName(nome);

        return ResponseEntity.ok().body(usuarios);

    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<List<Usuario>> postUsuario(@RequestBody Usuario usuario) {

        usuarioService.saveUsuario(usuario);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping(value = "usuario/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") Long id) {

        usuarioService.deleteUsuarioById(id);

        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/usuario/autenticate", params = {"usuario", "password"})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Usuario> autenticaUsuario(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {

        Usuario usuarioAutenticado = usuarioService.autenticaUsuario(usuario, password);

        return ResponseEntity.ok().body(usuarioAutenticado);

    }

    @GetMapping(value = "/usuario/{id}/biblioteca")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<List<Produto>> getBibliotecaDoUsuario(@PathVariable("id") Long id) {

        List<Produto> biblioteca = usuarioService.findBibliotecaByUsuarioId(id);

        return ResponseEntity.ok().body(biblioteca);

    }

    @PostMapping(value = "/usuario/{usuarioId}/biblioteca/{produtoId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Usuario> postBibliotecaDoUsuario(@PathVariable("usuarioId") Long usuarioId, @PathVariable("produtoId") Long produtoId) {

        //usuarioService.findBibliotecaByUsuarioId(usuarioId, produtoId);

        return ResponseEntity.ok().build();

    }
}
