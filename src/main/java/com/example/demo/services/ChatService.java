package com.example.demo.services;

import com.example.demo.data_transfer_objects.ChatDTO;
import com.example.demo.data_transfer_objects.MatchDTO;
import com.example.demo.entities.*;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.ChatConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ChatService extends BaseService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatConteudoRepository chatConteudoRepository;

    @Autowired
    private BibliotecaService bibliotecaService;

    @Autowired
    private InteresseService interesseService;

    public List<Chat> listChats() {
        List<Chat> chatList = new ArrayList<>();
        chatRepository.findAll().forEach(chatList::add);
        return chatList;
    }

    public List<Chat> findChatsByUserId(Long id) {
        List<Chat> chatList = new ArrayList<>();
        chatList.addAll(chatRepository.findById_Usuario1Id(id));
        chatList.addAll(chatRepository.findById_Usuario2Id(id));
        return chatList;
    }

    public Chat closeChatByUsersId(Long usuario_1_id, Long usuario_2_id) {
        Chat chat = chatRepository.findById_Usuario1IdAndId_Usuario2Id(usuario_1_id, usuario_2_id)
                .orElseThrow(() -> httpResponseService.notFound("chat not found"));
        chat.setResolvidoFlag(true);
        return chatRepository.save(chat);
    }

    public List<ChatConteudo> listAllContent() {
        List<ChatConteudo> conteudoList = new ArrayList<>();
        chatConteudoRepository.findAll().forEach(conteudoList::add);
        return conteudoList;
    }

    public List<ChatConteudo> findAllChatContentByUsersIds(Long usuario_1_id, Long usuario_2_id) {
        return chatConteudoRepository.findByUsuario1IdAndUsuario2Id(usuario_1_id, usuario_2_id);
    }

    public void publicarChat(ChatDTO chatDTO) {
        validateChatDTO(chatDTO);
        chatRepository.save(Chat
                .builder()
                .id(ChatId
                        .builder()
                        .usuario1Id(chatDTO.getUsuario1Id())
                        .usuario2Id(chatDTO.getUsuario2Id())
                        .build())
                .resolvidoFlag(chatDTO.getResolvidoFlag())
                .build());
        System.out.println("chat publicado");
    }

    public void validateChatDTO(ChatDTO chatDTO) {
        if (chatDTO.getUsuario1Id() < chatDTO.getUsuario2Id()) {
            Long id = chatDTO.getUsuario1Id();
            chatDTO.setUsuario1Id(chatDTO.getUsuario2Id());
            chatDTO.setUsuario2Id(id);
        }
        if (Objects.isNull(chatDTO.getUsuario1Id()) || Objects.isNull(chatDTO.getUsuario2Id())) {
            throw httpResponseService.badRequest("usuario1Id e usuario2Id nao podem ser nulos");
        }
    }

    public void publicarChatConteudo(ChatConteudo chatConteudo) {
        System.out.println(chatConteudo);
        validateChatConteudo(chatConteudo);
        try {
        chatConteudoRepository.save(chatConteudo);
        } catch (Exception e) {
            Long id = chatConteudo.getUsuario1Id();
            chatConteudo.setUsuario1Id(chatConteudo.getUsuario2Id());
            chatConteudo.setUsuario2Id(id);
            chatConteudoRepository.save(chatConteudo);
        }
        System.out.println("conteudo publicado");
    }

    public void validateChatConteudo(ChatConteudo chatConteudo) {
        if (Objects.isNull(chatConteudo.getMensagem())) {
            chatConteudo.setMensagem(" ");
        }
        if (Objects.isNull(chatConteudo.getUsuario1Id()) || Objects.isNull(chatConteudo.getUsuario2Id())) {
            throw httpResponseService.badRequest("usuario1Id e usuario2Id nao podem ser nulos");
        }
        else if (chatConteudo.getUsuario1Id() < chatConteudo.getUsuario2Id()) {
            Long id = chatConteudo.getUsuario1Id();
            chatConteudo.setUsuario1Id(chatConteudo.getUsuario2Id());
            chatConteudo.setUsuario2Id(id);
        }
    }

    public void validateMatch (MatchDTO matchDTO){
        if(matchDTO.getMatchAceito()){
            System.out.println("match detectado");
            bibliotecaService.processarMatch(
                    matchDTO.getUsuario1Id(),
                    matchDTO.getUsuario2Id(),
                    interesseService.findInteresseByDonoIdAndInteressadoid(matchDTO.getUsuario1Id(), matchDTO.getUsuario2Id()).getId().getProdutoId(),
                    interesseService.findInteresseByDonoIdAndInteressadoid(matchDTO.getUsuario2Id(), matchDTO.getUsuario1Id()).getId().getProdutoId()
            );
        }

        interesseService.deleteInteresse(matchDTO.getUsuario1Id(), matchDTO.getUsuario2Id());

    }

}
