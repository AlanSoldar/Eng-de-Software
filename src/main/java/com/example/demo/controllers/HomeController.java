package com.example.demo.controllers;

import com.example.demo.data_transfer_objects.ChatDTO;
import com.example.demo.data_transfer_objects.MatchDTO;
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
public class HomeController extends BaseController {

    @RequestMapping("/")
    public String home() {
        return "hello world";
    }


   }



