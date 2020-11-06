package com.example.demo.services;

import com.example.demo.data_transfer_objects.LojaDTO;
import com.example.demo.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LojaService extends BaseService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<Produto> listProdutos(Pageable page) {
        return produtoService.findAllProdutos(page);

    }

    public void venderProdutoParaUsuario(LojaDTO lojaDTO) {
        this.validateLojaDTO(lojaDTO);
        Produto produto = produtoService.findProdutoById(lojaDTO.getProdutoId());
        usuarioService.removeSaldo(lojaDTO.getUsuarioId(), produto.getPreco());
        usuarioService.adicionaProdutoNaBibliotecaDoUsuario(lojaDTO.getUsuarioId(), lojaDTO.getProdutoId());

    }

    public void validateLojaDTO(LojaDTO lojaDTO) {
        if (Objects.isNull(lojaDTO.getProdutoId()) || Objects.isNull(lojaDTO.getUsuarioId())) {
            httpResponseService.badRequest("usuarioId e produtoId nao podem ser nulos");
        }
    }

}
