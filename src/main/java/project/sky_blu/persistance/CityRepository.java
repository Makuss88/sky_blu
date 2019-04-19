package project.sky_blu.persistance;

import org.springframework.data.repository.CrudRepository;
import project.sky_blu.domain.City;

import java.util.List;


public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findAllByOfficialName(String officialName);
}
