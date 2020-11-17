package com.example.demo.services;

import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.BibliotecaId;
import com.example.demo.repositories.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BibliotecaService extends BaseService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    public List<Biblioteca> findBibliotecasByUsuarioId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaRepository.findByIdUsuarioId(id);
        System.out.println("retornando todos os bibliotecas do usuario com id = " + id);

        if (bibliotecas.isEmpty()) {
            throw httpResponseService.notFound("No bibliotecas were found");
        }

        return bibliotecas;

    }

    public List<Biblioteca> findBibliotecasByProdutoId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaRepository.findById_ProdutoId(id);
        System.out.println("retornando produto na biblioteca do usuario. produtoId = " + id);

        if (bibliotecas.isEmpty()) {
            throw httpResponseService.notFound("No bibliotecas were found");
        }

        return bibliotecas;

    }

    public List<Biblioteca> findAllBibliotecas() {

        List<Biblioteca> bibliotecaList = new ArrayList<>();
        bibliotecaRepository.findAll().forEach(biblioteca -> bibliotecaList.add(biblioteca));
        System.out.println("retornando todos os bibliotecas");

        if (bibliotecaList.isEmpty()) {
            throw httpResponseService.notFound("No bibliotecas were found");
        }

        return bibliotecaList;

    }

    public Biblioteca findBibliotecaById(BibliotecaId id) {

        Biblioteca biblioteca = bibliotecaRepository.findById(id).orElseThrow(() -> httpResponseService.notFound("biblioteca not found"));
        System.out.println("retornando biblioteca com id = " + id.toString());

        return biblioteca;

    }

    public void saveBiblioteca(Biblioteca biblioteca) {

        bibliotecaRepository.save(biblioteca);
        System.out.println("biblioteca salvo");

    }

    public void deleteBibliotecaById(BibliotecaId id) {

        bibliotecaRepository.deleteById(id);
        System.out.println("deletando biblioteca com id = " + id.toString());

    }

    /**
     * Checa se um usuario tem determinado jogo em sua biblioteca
     *
     * @param usuarioId
     * @param produtoId
     * @return boolean
     */
    public boolean usuarioTemJogo(Long usuarioId, Long produtoId){
        try {
            return bibliotecaRepository.findByIdUsuarioId(usuarioId)
                    .stream()
                    .noneMatch(biblioteca -> Objects.equals(biblioteca.getId().getProdutoId(),produtoId));
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Processa o match com os devidos usuarios, trocando os jogos entre eles
     *
     * @param usuarioA    usuarioA
     * @param usuarioB    usuarioB
     * @param produtoId_A produto do usuarioA
     * @param produtoId_B produto do usuarioB
     */
    public void processarMatch(Long usuarioA, Long usuarioB, Long produtoId_A, Long produtoId_B) {
        Biblioteca bibliotecaA = bibliotecaRepository.findByIdUsuarioIdAndIdProdutoId(usuarioA, produtoId_A).orElseThrow(() -> httpResponseService.notFound("No user was found"));
        Biblioteca bibliotecaB = bibliotecaRepository.findByIdUsuarioIdAndIdProdutoId(usuarioB, produtoId_B).orElseThrow(() -> httpResponseService.notFound("No user was found"));
        try {
            bibliotecaRepository.save(Biblioteca
                    .builder()
                    .id(BibliotecaId
                            .builder()
                            .usuarioId(usuarioA)
                            .produtoId(produtoId_B)
                            .build())
                    .build());
            bibliotecaRepository.save(Biblioteca
                    .builder()
                    .id(BibliotecaId
                            .builder()
                            .usuarioId(usuarioB)
                            .produtoId(produtoId_A)
                            .build())
                    .build());

            bibliotecaRepository.delete(bibliotecaA);
            bibliotecaRepository.delete(bibliotecaB);

        } catch (Exception ex) {
            httpResponseService.badRequest("Erro no processamento do match");
            System.out.println("Erro no processamento do match");
        }
    }
}
