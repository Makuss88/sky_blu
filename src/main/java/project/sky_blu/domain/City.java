package project.sky_blu.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class City {

    @Id
    @GeneratedValue
    private Long idCity;

    @Column(nullable = false)
    private String flyName;

    @Column(nullable = false)
    private String officialName;

    public City() {
    }

    public City(String flyName, String officialName) {
        this.flyName = flyName;
        this.officialName = officialName;
    }
}
