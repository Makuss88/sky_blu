package project.sky_blu.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Getter
@Setter
@Service
public class FlyInformation {


    private String from;
    private String to;
    private String dateStart;

    public FlyInformation() {
    }

    public FlyInformation(String from, String to, String dateStart) {
        this.from = from;
        this.to = to;
        this.dateStart = dateStart;
    }
}
