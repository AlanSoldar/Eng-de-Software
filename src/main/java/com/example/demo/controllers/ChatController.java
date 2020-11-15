package com.example.demo.controllers;

import com.example.demo.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class ChatController extends BaseController {

    @Autowired
    private ChatService comunidadeService;

    @GetMapping(value = "/chat")
    public ResponseEntity getChats() {
        try {
            System.out.println("cheguei");
            return ResponseEntity.ok().body(comunidadeService.listChats());
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }
    }

}



