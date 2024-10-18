package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    @GetMapping("/Hello")
    public String getHello(){
return"Hello from API";
    }
}
