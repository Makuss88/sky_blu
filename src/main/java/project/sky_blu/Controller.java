package project.sky_blu;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/go")
    public String index(){
        return "/index";
    }

    @GetMapping(value = "/")
    public String go(Model model){

        List<String> fly = new ArrayList<>();

        fly.add("Warszawa Okęcie");
        fly.add("Warszawa Modlin");
        fly.add("Wrocław");
        fly.add("Poznań");
        fly.add("Kraków");
        fly.add("Katowice");
        fly.add("Gdańsk");
        fly.add("Łódź");
        fly.add("Bydgoszcz");
        fly.add("Rzeszów");
        fly.add("Szczecin");
        fly.add("Zielona Góra");


        String [] flyy = new String[fly.size()];
        for (int i = 0; i < fly.size(); i++) {
            flyy[i] = fly.get(i);
        }

        model.addAttribute("lotniska", flyy);
        return "/index";

    }

    @GetMapping(value="/search")
    public String search(){


        return "/search";
    }

}
