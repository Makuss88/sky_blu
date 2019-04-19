package project.sky_blu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.sky_blu.service.RyanairService;

import javax.annotation.PostConstruct;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private RyanairService ryanairService;



    @PostConstruct
    public void initializer(){



    }


    @GetMapping(value = "/go")
    public String index() {
        return "/index";
    }



    @GetMapping(value = "/")
    public String go(Model model) {

        String[] fly = new String[10];

        fly[0] = ("Kraków");
        fly[1] = ("Bari");
        fly[2] = ("Vienna");
        fly[3] = ("Piza");
        fly[4] = ("Katowice");
        fly[5] = ("Katowice");
        fly[6] = ("Gdańsk");
        fly[7] = ("Łódź");
        fly[8] = ("Bydgoszcz");
        fly[9] = ("Rzeszów");


        model.addAttribute("lotniska", fly);
        return "/index";

    }

    @GetMapping(value = "/search")
    public String search(Model model) {

        String from = "KRK";
        String to = "BRI";
        String dateStart = "2019-04-19";

        String ryanairURL = ryanairService.parseURL(from, to, dateStart);

        String price = ryanairService.getPrice(ryanairURL);

        model.addAttribute("price",price);
        model.addAttribute("date", dateStart);

        return "/search";
    }

}
