package project.sky_blu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.sky_blu.domain.City;
import project.sky_blu.persistance.CityRepository;
import project.sky_blu.service.FlyInformation;
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
        model.addAttribute("flyInformation", new FlyInformation());
        model.addAttribute("airport", airport);
        return "/index";

    }

    @PostMapping(value = "/search")
    public String search(@ModelAttribute("flyInformation") FlyInformation flyInformation, Model model) {

        String from = flyInformation.getFrom();
        String to = flyInformation.getTo();
        String dateStart = flyInformation.getDateStart();

        String ryanairURL = ryanairService.parseURL(from, to, dateStart);

        ryanairService.getPriceAndData(ryanairURL);


        model.addAttribute("price", ryanairService.getPrices());
        model.addAttribute("date", ryanairService.getDates());

        return "/search";
    }

}
