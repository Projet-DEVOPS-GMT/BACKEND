package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.EcoAction;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
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

    @Autowired
    public EcoActionController(EcoActionService ecoActionService, UserService userService) {
        this.ecoActionService = ecoActionService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<EcoAction> addEcoAction(@RequestBody EcoAction ecoAction) {
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
