package edu.ap.spring.controller;

import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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


       try {

           URL url = new URL("http://api.icndb.com/jokes/random?firstName="+ voornaam +"&lastName="+ naam );
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setRequestProperty("Accept", "application/json");

           if (conn.getResponseCode() != 200) {
               throw new RuntimeException("Failed : HTTP error code : "
                       + conn.getResponseCode());
           }

           BufferedReader br = new BufferedReader(new InputStreamReader(
                   (conn.getInputStream())));

           String output;
           String data = "";
           System.out.println("Output from Server .... \n");
           while ((output = br.readLine()) != null) {
               data = output;
           }
           JSONParser parser = new JSONParser();
           JSONObject jsonData = (JSONObject) parser.parse(data);
           JSONObject jsonValue = (JSONObject) parser.parse(jsonData.get("value").toString());
           String joke = jsonValue.get("joke").toString();
           model.addAttribute("joke", joke);


           conn.disconnect();

       } catch (MalformedURLException e) {

           e.printStackTrace();

       } catch (IOException e) {

           e.printStackTrace();

       } catch (ParseException e) {
           e.printStackTrace();
       }
       return "joke_post";

   }
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }
}
