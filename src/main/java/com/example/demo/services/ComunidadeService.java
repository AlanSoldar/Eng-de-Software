package com.example.demo.services;

import com.example.demo.data_transfer_objects.ComunidadeDTO;
import com.example.demo.data_transfer_objects.InteresseDTO;
import com.example.demo.entities.Comunidade;
import com.example.demo.entities.ComunidadeId;
import com.example.demo.entities.Interesse;
import com.example.demo.repositories.ComunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ComunidadeService extends BaseService {

    @Autowired
    private ComunidadeRepository comunidadeRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Comunidade> listProdutos() {
        List<Comunidade> comunidadeList = new ArrayList<>();
        comunidadeRepository.findAll().forEach(comunidadeList::add);
        return comunidadeList;

    }

    public void publicarProdutoNaComunidade(ComunidadeDTO comunidadeDTO) {
        validateComunidadeDTO(comunidadeDTO);
        if (!usuarioService.findBibliotecaByProdutoId(comunidadeDTO.getProdutoId()).isEmpty()) {
            comunidadeRepository.save(Comunidade
                    .builder()
                    .id(ComunidadeId
                            .builder()
                            .usuarioId(comunidadeDTO.getUsuarioId())
                            .produtoId(comunidadeDTO.getProdutoId())
                            .build())
                    .valor(comunidadeDTO.getValor())
                    .build());
            System.out.println("produto publicado na loja da comunidade");
        } else {
            httpResponseService.badRequest("usuario nao possui este produto em sua biblioteca");
        }
    }

    public void removerProdutoDaComunidade(ComunidadeDTO comunidadeDTO) {
        validateComunidadeDTO(comunidadeDTO);
        comunidadeRepository.delete(comunidadeRepository.findById_UsuarioIdAndId_ProdutoId(comunidadeDTO.getUsuarioId(), comunidadeDTO.getProdutoId())
                .orElseThrow(() -> httpResponseService.notFound("produto nao encontrado na comunidade")));
            System.out.println("produto removido da loja da comunidade");
    }

    public void validateComunidadeDTO(ComunidadeDTO comunidadeDTO) {
        if (Objects.isNull(comunidadeDTO.getProdutoId()) || Objects.isNull(comunidadeDTO.getUsuarioId())) {
            httpResponseService.badRequest("usuarioId e produtoId nao podem ser nulos");
        }
    }
}
