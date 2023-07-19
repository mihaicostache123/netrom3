package com.example.championship.controller;

import com.example.championship.model.Player;
import com.example.championship.service.PlayerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @GetMapping(value = "/player")
    public String playerPage(){
        return "playerPage";
    }
    @GetMapping(value = "/team")
    public String teamPage(){
        return "teamPage";
    }
    @GetMapping(value = "/game")
    public String gamePage(){
        return "gamePage";
    }

}
