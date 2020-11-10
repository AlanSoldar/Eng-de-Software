package com.example.demo.controllers;

import com.example.demo.data_transfer_objects.PagamentoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
public class UsuarioController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable("id") Long id) {
        Usuario usuario;
        try {
            usuario = usuarioService.findUsuarioById(id);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(usuario);

    }

    @GetMapping(value = "/usuario/nome/{nome}")
    public ResponseEntity getUsuario(@PathVariable("nome") String nome) {
        List usuarios;
        try {
            usuarios = usuarioService.findUsuarioByName(nome);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(usuarios);

    }

    @PostMapping(value = "/usuario")
    public ResponseEntity postUsuario(@RequestBody Usuario usuario) {

        try {
            usuarioService.saveUsuario(usuario);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();

    }

    @DeleteMapping(value = "usuario/{id}")
    public ResponseEntity deleteUsuario(@PathVariable("id") Long id) {

        try {
            usuarioService.deleteUsuarioById(id);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/usuario/autenticar", params = {"usuario", "password"})
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity autenticaUsuario(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
        Usuario usuarioAutenticado;

        try {
            usuarioAutenticado = usuarioService.autenticaUsuario(usuario, password);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(usuarioAutenticado);

    }

    @GetMapping(value = "/usuario/{id}/biblioteca")
    public ResponseEntity getBibliotecaDoUsuario(@PathVariable("id") Long id) {
        List<Produto> biblioteca;

        try {
            biblioteca = usuarioService.findBibliotecaByUsuarioId(id);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(biblioteca);

    }

    @PostMapping(value = "/usuario/{usuarioId}/produto/{produtoId}")
    public ResponseEntity postBibliotecaDoUsuario(@PathVariable("usuarioId") Long usuarioId, @PathVariable("produtoId") Long produtoId) {

        try {
            usuarioService.adicionaProdutoNaBibliotecaDoUsuario(usuarioId, produtoId);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();

    }

    @PostMapping(value = "/usuario/{usuarioId}/saldo")
    public ResponseEntity postBibliotecaDoUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody PagamentoDTO pagamentoDTO) {

        try {
            return ResponseEntity.ok().body(usuarioService.processTransacao(usuarioId, pagamentoDTO));
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

    }
}
