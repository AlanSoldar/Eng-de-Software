package com.example.demo.services;

import com.example.demo.data_transfer_objects.PagamentoDTO;
import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.BibliotecaId;
import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.TransacaoEnum;
import com.example.demo.exceptions.HttpResponseService;
import com.example.demo.repositories.UsuarioRepository;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService{

    private static final Integer NUMERO_DE_DIGITOS_DO_CARTAO = 16;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BibliotecaService bibliotecaService;
    @Autowired
    private ProdutoService produtoService;

    public Usuario findUsuarioById(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> httpResponseService.notFound("usuario not found"));
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
            throw httpResponseService.notFound("usuario e senha informados não são validos");
        }
    }

    public List<Produto> findBibliotecaByUsuarioId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaService.findBibliotecasByUsuarioId(id);
        List<Produto> produtos = new ArrayList<>();

        bibliotecas.forEach(biblioteca -> produtos.add(produtoService.findProdutoById(biblioteca.getId().getProdutoId())));

        return produtos;

    }

    public void postBibliotecaByUsuarioId(Long usuarioId, Long produtoId) {

        Biblioteca biblioteca = Biblioteca.builder().id(BibliotecaId.builder().usuarioId(usuarioId).ProdutoId(produtoId).build()).build();

        bibliotecaService.saveBiblioteca(biblioteca);
    }

    public Usuario processTransacao(Long usuarioId, PagamentoDTO pagamentoDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> httpResponseService.notFound("usuario nao encontrado"));

        if (!NUMERO_DE_DIGITOS_DO_CARTAO.equals(pagamentoDTO.getNumeroDoCartao().toString().length())) {
            throw httpResponseService.unauthorized("numero do cartao nao possui 16 digitos");
        }

        if (TransacaoEnum.CREDITO.equals(pagamentoDTO.getTransacao())) {
                usuario.setSaldo(adicionaSaldo(usuario.getSaldo(), pagamentoDTO.getValor()));
        } else if (TransacaoEnum.DEBITO.equals(pagamentoDTO.getTransacao())) {
                usuario.setSaldo(removeSaldo(usuario.getSaldo(), pagamentoDTO.getValor()));
        }
        return usuarioRepository.save(usuario);
    }

    private Long adicionaSaldo(Long saldo, Long valor) {
        return saldo+valor;
    }

    private Long removeSaldo(Long saldo, Long valor) {
        Long valorFinal = saldo-valor;
        if (valorFinal<0) {
            throw httpResponseService.unauthorized("Valor debitado excede saldo na carteira do usuário");
        }
        return valorFinal;
    }

}
