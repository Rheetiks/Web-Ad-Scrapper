package com.scrapper.webadscrapper.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scrapper.webadscrapper.Model.Product;
import com.scrapper.webadscrapper.Service.Service;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    @Autowired
    private Service service;
    
    @GetMapping("/test")
    public String test(){
        return "WORKING...";
    }

    @PostMapping("/get/product")
    public Product getProduct(@RequestBody String text) throws InterruptedException{

        return service.getProduct(text);
    }


    // @GetMapping("/try")
    // public String Try(){
    //     String title=null;
    //     try {
    //         Document doc = Jsoup.connect("https://google.com").get();
    //         title = doc.title();
    //         System.out.println("Page Title: " + title);
    //         Elements links = doc.select("a");
    //         for (Element link : links) {
    //             System.out.println("Link: " + link.attr("href"));
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return title;
    // }
}
