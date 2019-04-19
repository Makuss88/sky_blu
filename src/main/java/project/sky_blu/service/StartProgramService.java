package project.sky_blu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sky_blu.domain.City;
import project.sky_blu.persistance.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StartProgramService {

    List<City> airPort = new ArrayList<>();

    @Autowired
    private CityRepository cityRepository;
    public void addCityIntoDB() {

        airPort.add(new City("KRK","Kraków"));
        airPort.add(new City("BRI", "Bari"));
        airPort.add(new City("KAT","Katowice"));
        airPort.add(new City("WAW", "Warszawa"));
        airPort.add(new City("VIE","Vienna"));
        airPort.add(new City("PSA","Piza"));
        airPort.add(new City("DBA", "Dublin"));

        cityRepository.saveAll(airPort);
    }
}
