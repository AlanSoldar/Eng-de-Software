package com.example.demo.services;

import com.example.demo.data_transfer_objects.ChatDTO;
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
    private BibliotecaService bibliotecaService;
    @Autowired
    private ChatService chatService;

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
     * Retorna um interesse especifico entre dois usuarios
     *
     * @param donoId
     * @param interessadoId
     * @return
     */
    public Interesse findInteresseByDonoIdAndInteressadoid(Long donoId, Long interessadoId) {
        Interesse interesse = interesseRepository.findByIdDonoIdAndIdInteressadoId(donoId, interessadoId).orElseThrow(() -> httpResponseService.notFound("interesse not found"));
        System.out.println("retornando interesse do usuario com id = " + donoId.toString());

        return interesse;
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
     * Retorna verdadeiro se o interesse pode ser criado e processado
     * Retorna falso caso contrario
     *
     * @param interesse tipo Interesse
     * @return boolean
     */
    public boolean interesseValido(Interesse interesse) {
        return bibliotecaService.usuarioTemJogo(interesse.getId().getInteressadoId(), interesse.getId().getProdutoId())
                && !bibliotecaService.usuarioTemJogo(interesse.getId().getDonoId(),interesse.getId().getProdutoId());

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
                        chatService.publicarChat(
                                ChatDTO.builder()
                                        .usuario1Id(interesseId.getDonoId())
                                        .usuario2Id(interesseId.getInteressadoId())
                                        .resolvidoFlag(false)
                                        .build());
//
//                        processarMatch(
//                                interesseId.getDonoId(),
//                                interesseId.getInteressadoId(),
//                                interesseId.getProdutoId(),
//                                interesseMatch.getId().getProdutoId()
//                        );
                });
    }

    public void deleteInteresse(Long usuario1Id, Long usuario2Id){
        interesseRepository.delete(interesseRepository.findByIdDonoIdAndIdInteressadoId(usuario1Id,usuario2Id).orElse(Interesse.builder().build()));
        interesseRepository.delete(interesseRepository.findByIdDonoIdAndIdInteressadoId(usuario2Id,usuario1Id).orElse(Interesse.builder().build()));
    }
}
