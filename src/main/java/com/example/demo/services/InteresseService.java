package com.example.demo.services;

import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.BibliotecaId;
import com.example.demo.entities.Interesse;
import com.example.demo.entities.InteresseId;
import com.example.demo.repositories.BibliotecaRepository;
import com.example.demo.repositories.InteresseRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InteresseService extends BaseService {
    @Autowired
    private InteresseRepository interesseRepository;
    private UsuarioRepository usuarioRepository;
    private BibliotecaRepository bibliotecaRepository;

    public Page<Interesse> listInteresses(Pageable page) {
        return interesseRepository.findAll(page);

    }

    /**
     * Cria um interesse em determinado produto.
     * Se há um produto, chama uma funcao que checa se houve match
     *
     * @param interesse
     */
    public void processarInteresse(Interesse interesse) {
        InteresseId interesseId = interesse.getId();
        interesseRepository.findByIdDonoIdAndIdInteressadoId(interesseId.getDonoId(), interesseId.getInteressadoId())
                .ifPresentOrElse(
                        interesse2 -> {
                            throw httpResponseService.badRequest("An interesse exists with the same donoId and interessadoId");
                        },
                        () -> {
                            interesseRepository.save(interesse);
                            System.out.println("interesse salvo");
                            checarMatch(interesse.getId());
                        });

    }

    /**
     * Checa se houve match no interesse
     *
     * @param interesseId
     */
    public void checarMatch(InteresseId interesseId) {
        interesseRepository.findByIdDonoIdAndIdInteressadoIdAndIdProdutoId(interesseId.getInteressadoId(), interesseId.getDonoId(), interesseId.getProdutoId())
                .ifPresent(interesseMatch -> {
                    if (interesseId.getDonoId().equals(interesseMatch.getId().getInteressadoId()) && interesseId.getInteressadoId().equals(interesseMatch.getId().getDonoId()))
                        processarMatch(
                                interesseId.getDonoId(),
                                interesseId.getInteressadoId(),
                                interesseId.getProdutoId(),
                                interesseMatch.getId().getProdutoId()
                        );
                });
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
            System.out.println("Erro no processamento do match");
        }
    }
}
