package com.example.demo.services;

import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.BibliotecaId;
import com.example.demo.entities.Interesse;
import com.example.demo.entities.InteresseId;
import com.example.demo.repositories.BibliotecaRepository;
import com.example.demo.repositories.InteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InteresseService extends BaseService {
    @Autowired
    private InteresseRepository interesseRepository;
    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    public Page<Interesse> listInteresses(Pageable page) {
        return interesseRepository.findAll(page);
    }

    /**
     * Retorna uma lista de interessados no usuario
     *
     * @param donoId
     * @return List<Interesse>
     */
    public List<Interesse> findInteresseByDonoId(Long donoId) {
        List<Interesse> interesses = interesseRepository.findByIdDonoId(donoId).orElseThrow(() -> httpResponseService.notFound("interesse not found"));
        System.out.println("retornando interesses do usuario com id = " + donoId.toString());

        return interesses;
    }

    /**
     * Retorna uma lista de interesses do usuario
     *
     * @param interessadoId
     * @return List<Interesse>
     */
    public List<Interesse> findInteresseByInteressadoId(Long interessadoId) {
        List<Interesse> interesses = interesseRepository.findByIdInteressadoId(interessadoId).orElseThrow(() -> httpResponseService.notFound("interesse not found"));
        System.out.println("retornando interessados no usuario com id = " + interessadoId.toString());

        return interesses;
    }

    /**
     * Checa se um usuario tem determinado jogo em sua biblioteca
     *
     * @param usuarioId
     * @param produtoId
     * @return boolean
     */
    private boolean usuarioTemJogo(Long usuarioId, Long produtoId){
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
     * Retorna verdadeiro se o interesse pode ser criado e processado
     * Retorna falso caso contrario
     *
     * @param interesse tipo Interesse
     * @return boolean
     */
    private boolean interesseValido(Interesse interesse) {
        return usuarioTemJogo(interesse.getId().getInteressadoId(), interesse.getId().getProdutoId())
                && !usuarioTemJogo(interesse.getId().getDonoId(),interesse.getId().getProdutoId());

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
                            if(interesseValido(interesse)) {
                                interesseRepository.save(interesse);
                                System.out.println("interesse salvo");
                                checarMatch(interesse.getId());
                            }
                            else{
                                throw httpResponseService.badRequest("O interessado ja tem o jogo ou o dono nao tem o jogo");
                            }
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
            httpResponseService.badRequest("Erro no processamento do match");
            System.out.println("Erro no processamento do match");
        }
    }
}
