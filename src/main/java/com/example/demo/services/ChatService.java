package com.example.demo.services;

import com.example.demo.entities.Chat;
import com.example.demo.entities.ChatId;
import com.example.demo.repositories.ChatRepository;
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

    public List<Chat> listChats() {
        List<Chat> chatList = new ArrayList<>();
        chatRepository.findAll().forEach(chatList::add);
        return chatList;
    }
}
