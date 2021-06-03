package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// info
@Controller
@RequestMapping("/ui")
public class WebController {
    @Autowired
    GameServiceImpl service;

    @RequestMapping("/desk")
    String getDesk(Model model){
        model.addAttribute("count", service.countDeck());
    return "clear";
}
    @RequestMapping("/init")
    public String initGame(Model model){
        service.init();
        model.addAttribute("count", service.countDeck());
        return "clear";
    }
    @RequestMapping("/getcard")
    public String getCard(Model model){
        model.addAttribute("card", service.giveCard());
        model.addAttribute("count", service.countDeck());
        return "desk";
    }
    @RequestMapping("/shuffle")
    public String shuffleDeck(Model model){
        service.shuffleDeck();
        model.addAttribute("count", service.countDeck());
        Card card = new Card();
        card.setImg("/img/fulldeck/back.png");
        model.addAttribute("card", card);
        return "desk";
    }
    @RequestMapping("/refill/{amount}")
    public String refill(Model model, @PathVariable("amount") int amount){
        model.addAttribute("list", service.giveMeCards());
        model.addAttribute("count", service.countDeck());
        return "desklist";
    }
    @RequestMapping("/refill")
    public String refill(Model model){
        model.addAttribute("list", service.giveMeCards());
        model.addAttribute("count", service.countDeck());
        return "desklist";
    }

}
