package com.example.demo.services;

import com.example.demo.data_transfer_objects.PagamentoDTO;
import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.BibliotecaId;
import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.TransacaoEnum;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService {

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

    public List<Produto> findBibliotecaByProdutoId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaService.findBibliotecasByProdutoId(id);
        List<Produto> produtos = new ArrayList<>();

        bibliotecas.forEach(biblioteca -> produtos.add(produtoService.findProdutoById(biblioteca.getId().getProdutoId())));

        return produtos;

    }

    public void adicionaProdutoNaBibliotecaDoUsuario(Long usuarioId, Long produtoId) {

        Biblioteca biblioteca = Biblioteca.builder().id(BibliotecaId.builder().usuarioId(usuarioId).produtoId(produtoId).build()).build();

        bibliotecaService.saveBiblioteca(biblioteca);
    }

    public Usuario processTransacao(Long usuarioId, PagamentoDTO pagamentoDTO) {
        this.validatePagamentoDTO(pagamentoDTO);
        if (!NUMERO_DE_DIGITOS_DO_CARTAO.equals(pagamentoDTO.getNumeroDoCartao().toString().length())) {
            throw httpResponseService.unauthorized("numero do cartao nao possui 16 digitos");
        }

        if (TransacaoEnum.CREDITO.equals(pagamentoDTO.getTransacao())) {
            return adicionaSaldo(usuarioId, pagamentoDTO.getValor());
        } else if (TransacaoEnum.DEBITO.equals(pagamentoDTO.getTransacao())) {
            return removeSaldo(usuarioId, pagamentoDTO.getValor());
        } else {
            throw httpResponseService.unauthorized("operacao invalida. transacao deve ser DEBITO ou CREDITO");
        }
    }

    public Usuario adicionaSaldo(Long usuarioId, Long valor) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> httpResponseService.notFound("usuario nao encontrado"));
        usuario.setSaldo(usuario.getSaldo() + valor);

        return usuarioRepository.save(usuario);
    }

    public Usuario removeSaldo(Long usuarioId, Long valor) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> httpResponseService.notFound("usuario nao encontrado"));

        Long valorFinal = usuario.getSaldo() - valor;
        if (valorFinal < 0) {
            throw httpResponseService.unauthorized("Valor debitado excede saldo na carteira do usuário");
        }

        usuario.setSaldo(valorFinal);
        return usuarioRepository.save(usuario);
    }

    public void validatePagamentoDTO(PagamentoDTO pagamentoDTO) {
        if (Objects.isNull(pagamentoDTO.getValor()) || Objects.isNull(pagamentoDTO.getNumeroDoCartao()) || Objects.isNull(pagamentoDTO.getTransacao())) {
            httpResponseService.badRequest("usuarioId e produtoId nao podem ser nulos");
        }
    }

}
