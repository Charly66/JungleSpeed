/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeed.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junglespeed.entity.Carte;
import junglespeed.entity.Joueur;
import junglespeed.entity.Partie;
import junglespeed.enumeration.TypePartie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ConfigService {

    private int nbJoueursMax = 2;
    private int nbPartiesMax = 4;
    private int nbCartes = 20;
    private int timer = 3;
    private Map<Integer, String> map = new HashMap<>();
    
    @Autowired
    private CarteService carteService;

    @Autowired
    private PartieService partieService;
    
    @Autowired
    private JoueurService joueurService;
    
    public ConfigService() {
    }


    @Scheduled(fixedDelay = 1000)
    public void init() {

        List<Partie> liste = partieService.findByTypePartie(TypePartie.EN_ATTENTE);
        while (liste.size() < nbPartiesMax) {
            Partie partie = new Partie("Partie", TypePartie.EN_ATTENTE);
            partie.setJoueurs(new ArrayList<Joueur>());
            partieService.save(partie);
            liste = partieService.findByTypePartie(TypePartie.EN_ATTENTE);
        }

        for (Partie partie : liste) {
            if (partie.getJoueurs().size() == nbJoueursMax) {
                partie.setTypePartie(TypePartie.EN_COURS);
                partieService.save(partie);
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void timer() {
        while (timer > 0) {
            timer--;
        }
        if (timer == 0) {
            timer = 3;
        }
    }

    public void distributionCartes(Partie partie) {

        map.put(1, "BLUE");
        map.put(2, "RED");
        map.put(3, "YELLOW");
        map.put(4, "GREEN");
        map.put(5, "GREY");
        map.put(6, "WHITE");
        map.put(7, "BLACK");
        map.put(8, "BLUE");

        int lower = 1;
        int higher = 8;
        
        for(Joueur joueur : partie.getJoueurs()){
            for(int i = 0 ; i < nbCartes ; i++){
                int random = (int) (Math.random() * (higher - lower +1)) + lower;
                Carte carte = new Carte(map.get(random), joueur);
                joueur.getCartes().add(carte);
                carteService.save(carte);
                joueurService.save(joueur);
            }
        }
    }

    public int getNbJoueursMax() {
        return nbJoueursMax;
    }

    public void setNbJoueursMax(int nbJoueursMax) {
        this.nbJoueursMax = nbJoueursMax;
    }

    public int getNbPartiesMax() {
        return nbPartiesMax;
    }

    public void setNbPartiesMax(int nbPartiesMax) {
        this.nbPartiesMax = nbPartiesMax;
    }

    public int getNbCartes() {
        return nbCartes;
    }

    public void setNbCartes(int nbCartes) {
        this.nbCartes = nbCartes;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public PartieService getPartieService() {
        return partieService;
    }

    public void setPartieService(PartieService partieService) {
        this.partieService = partieService;
    }

}
