package project.sky_blu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.sky_blu.domain.City;
import project.sky_blu.persistance.CityRepository;
import project.sky_blu.service.RyanairService;
import project.sky_blu.service.StartProgramService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private RyanairService ryanairService;

    @Autowired
    private StartProgramService startProgramService;



    @Autowired
    private CityRepository cityRepository;


    @PostConstruct
    public void initializer() {

        startProgramService.addCityIntoDB();

    }



    @GetMapping(value = "/go")
    public String index() {
        return "/index";
    }

    @GetMapping(value = "/")
    public String go(Model model) {

        List<City> result = (List<City>) cityRepository.findAll();
        List<String> airport = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            airport.add(result.get(i).getOfficialName());
        }

        model.addAttribute("airport",airport);
        return "/index";

    }

    @GetMapping(value = "/search")
    public String search(Model model) {

        String from = "KRK";
        String to = "BRI";
        String dateStart = "2019-04-20";

        String ryanairURL = ryanairService.parseURL(from, to, dateStart);

        String price = ryanairService.getPrice(ryanairURL);

        model.addAttribute("price", price);
        model.addAttribute("date", dateStart);

        return "/search";
    }

}
