package project.sky_blu;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/")
    public String index(){
        return "/index";
    }
}
