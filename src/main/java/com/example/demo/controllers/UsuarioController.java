package com.example.demo.controllers;

import com.example.demo.data_transfer_objects.InteresseDTO;
import com.example.demo.data_transfer_objects.PagamentoDTO;
import com.example.demo.entities.Interesse;
import com.example.demo.entities.InteresseId;
import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.services.InteresseService;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class UsuarioController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InteresseService interesseService;

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

    @PostMapping(value = "/usuario/novo")
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

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/usuario/autenticar", params = {"usuario", "password"})
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

    @DeleteMapping(value = "/usuario/{usuarioId}/produto/{produtoId}")
    public ResponseEntity deleteBibliotecaDoUsuario(@PathVariable("usuarioId") Long usuarioId, @PathVariable("produtoId") Long produtoId) {

        try {
            usuarioService.removeProdutoNaBibliotecaDoUsuario(usuarioId, produtoId);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();

    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/usuario/{usuarioId}/saldo")
    public ResponseEntity postBibliotecaDoUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody PagamentoDTO pagamentoDTO) {

        try {
            return ResponseEntity.ok().body(usuarioService.processTransacao(usuarioId, pagamentoDTO));
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

    }

    /**
     * Retorna um JSON com os interesses do usuario
     *
     * @param usuarioId
     * @return
     */
    @GetMapping(value = "usuario/{usuarioId}/interesses")
    public ResponseEntity getInteresses (@PathVariable("usuarioId") Long usuarioId){
        try{
            List<Interesse> interesses = interesseService.findInteresseByInteressadoId(usuarioId);
            return ResponseEntity.ok().body(interesses);
        } catch (HttpClientErrorException exception){
            return createResponseEntity(exception);
        }
    }

    /**
     * Retorna um JSON com os interesses de outros usuarios em seus produtos postados na comunidade
     *
     * @param usuarioId
     * @return List<InteresseId>
     */
    @GetMapping(value = "usuario/{usuarioId}/interessados")
    public ResponseEntity getInteressados (@PathVariable("usuarioId") Long usuarioId){
        try{
            List<Interesse> interesses = interesseService.findInteresseByDonoId(usuarioId);
            return ResponseEntity.ok().body(interesses);
        } catch (HttpClientErrorException exception){
            return createResponseEntity(exception);
        }

    }
}
