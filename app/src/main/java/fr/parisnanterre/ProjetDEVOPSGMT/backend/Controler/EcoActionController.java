package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.EcoAction;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.UserRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.EcoActionService;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eco-actions")
public class EcoActionController {

    private final EcoActionService ecoActionService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public EcoActionController(EcoActionService ecoActionService, UserService userService, UserRepository userRepository) {
        this.ecoActionService = ecoActionService;
        this.userService = userService;
        this.userRepository= userRepository;
    }

    @PostMapping
    public ResponseEntity<EcoAction> addEcoAction(@RequestBody EcoAction ecoAction) {
       
        Long userId = ecoAction.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Associe l'utilisateur récupéré à l'action
        ecoAction.setUser(user);

        EcoAction savedAction = ecoActionService.saveAction(ecoAction);

        return ResponseEntity.ok(savedAction);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EcoAction>> getEcoActionsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        List<EcoAction> actions = ecoActionService.getActionsByUser(user);
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/user/{userId}/total-co2")
    public ResponseEntity<Double> getTotalCO2Saved(@PathVariable Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        double totalCO2 = ecoActionService.getTotalCO2Saved(user);
        return ResponseEntity.ok(totalCO2);
    }
}
