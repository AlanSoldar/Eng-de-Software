package com.example.demo.controllers;

import com.example.demo.entities.Chat;
import com.example.demo.entities.ChatConteudo;
import com.example.demo.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
public class ChatController extends BaseController {

    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/chat")
    public ResponseEntity getAllChats() {
        try {
            return ResponseEntity.ok().body(chatService.listChats());
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }
    }

    @GetMapping(value = "/chat/{id}")
    public ResponseEntity getUserIdChats(@PathVariable("id") Long id) {
        List<Chat> chats;
        try {
            chats = chatService.findChatsByUserId(id);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(chats);

    }

    @GetMapping(value = "/chat/user1/{id1}/user2/{id2}")
    public ResponseEntity getUsersIdsChat(@PathVariable("id1") Long user_1_id, @PathVariable("id2") Long user_2_id) {
        Chat chat;
        System.out.println("cheguei");
        try {
            chat = chatService.findChatByUsersIds(user_1_id,user_2_id);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(chat);

    }

    @GetMapping(value = "/chat/conteudo")
    public ResponseEntity getAllChatsContent() {
        try {
            return ResponseEntity.ok().body(chatService.listAllContent());
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }
    }

    @GetMapping(value = "/chat/conteudo/user1/{id1}/user2/{id2}")
    public ResponseEntity getUsersIdsChatContent(@PathVariable("id1") Long user_1_id, @PathVariable("id2") Long user_2_id) {
        List<ChatConteudo> conteudo;
        try {
            conteudo = chatService.findAllChatContentByUsersIds(user_1_id,user_2_id);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().body(conteudo);

    }

}



