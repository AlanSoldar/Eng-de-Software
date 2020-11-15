package com.example.demo.services;

import com.example.demo.entities.Chat;
import com.example.demo.entities.ChatId;
import com.example.demo.entities.ChatConteudo;
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

    public Chat findChatByUsersIds(Long usuario_1_id, Long usuario_2_id) {
        return chatRepository.findById_Usuario1IdAndId_Usuario2Id(usuario_1_id, usuario_2_id)
                .orElseThrow(() -> httpResponseService.notFound("chat not found"));
    }

    public List<ChatConteudo> listAllContent() {
        List<ChatConteudo> conteudoList = new ArrayList<>();
        chatConteudoRepository.findAll().forEach(conteudoList::add);
        return conteudoList;
    }

    public List<ChatConteudo> findAllChatContentByUsersIds(Long usuario_1_id, Long usuario_2_id) {
        return chatConteudoRepository.findByUsuario1IdAndUsuario2Id(usuario_1_id, usuario_2_id);
    }


}
