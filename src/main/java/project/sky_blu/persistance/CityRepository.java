package project.sky_blu.persistance;

import org.springframework.data.repository.CrudRepository;
import project.sky_blu.domain.City;


public interface CityRepository extends CrudRepository<City, Long> {

}
