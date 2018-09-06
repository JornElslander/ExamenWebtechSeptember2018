package edu.ap.spring.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("session")
public class JokeController {
   
   public JokeController() {
   }
       
   @RequestMapping("/joke")
   public String joke() {
	   return "joke";
   }
		   
   @RequestMapping("/joke_post")
   public String joke_post(@RequestParam("voornaam") String voornaam,
                           @RequestParam("naam") String naam,
                           Model model) {
       model.addAttribute("voornaam", voornaam);
       model.addAttribute("naam", naam);
       return "joke_post";
   }
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }
}
