// package fr.parisnanterre.ProjetDEVOPSGMT.backend.initializer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportServiceImpl;

// @Component
// public class DataInitializer implements CommandLineRunner {

//     @Autowired
//     private TransportServiceImpl transportService;

//     @Override
//     public void run(String... args) throws Exception {
//         String response = transportService.getTransportData("gare1", "gare2");

//         // Sauvegarder les données dans la base
//         transportService.saveTransportData(response);

//         System.out.println("Les données de transport ont été enregistrées.");
//     }
// }
