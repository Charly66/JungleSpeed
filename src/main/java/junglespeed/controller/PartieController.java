/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeed.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import junglespeed.entity.Carte;
import junglespeed.entity.Joueur;
import junglespeed.entity.Partie;
import junglespeed.enumeration.TypePartie;
import junglespeed.service.ConfigService;
import junglespeed.service.JoueurService;
import junglespeed.service.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tom
 */
@Controller
@RequestMapping(value = "/partie")
public class PartieController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private PartieService partieService;

    @Autowired
    private JoueurService joueurService;

    @RequestMapping(value = "lister", method = RequestMethod.GET)
    public String lister(Model model) {

        List<Partie> liste = new ArrayList<>();
        liste.addAll(partieService.findByTypePartie(TypePartie.EN_ATTENTE));

        model.addAttribute("liste", liste);

        return "lister_parties";
    }

    @RequestMapping(value = "pre_join/{partieId}", method = RequestMethod.GET)
    public String prejoin(@PathVariable(value = "partieId") long partieId, Model model) {
        model.addAttribute("joueur", new Joueur());
        model.addAttribute("partieId", partieId);
        return "join";
    }

    @RequestMapping(value = "join/{partieId}/{nom}", method = RequestMethod.GET)
    public String join(@PathVariable(value = "partieId") long partieId, @PathVariable(value = "nom") String nom, HttpSession session, Model model) {
        Partie partie = partieService.findOne(partieId);

        if (partie.getJoueurs().size() < configService.getNbJoueursMax()) {
            if (joueurService.findByLogin(nom) == null) {
                joueurService.save(new Joueur(nom));
            }

            Joueur joueur = joueurService.findByLogin(nom);
            joueur.setPartie(partie);
            partie.getJoueurs().add(joueur);

            session.setAttribute("idJoueur", joueur.getId());
            joueurService.save(joueur);
            partieService.save(partie);
        }
        if (partie.getJoueurs().size() == configService.getNbJoueursMax()) {
            partie.setTypePartie(TypePartie.EN_COURS);
            partieService.save(partie);
            configService.distributionCartes(partie);
            model.addAttribute("joueur1", partie.getJoueurs().get(0));
            model.addAttribute("joueur2", partie.getJoueurs().get(1));
        }

        return "partie";
    }

    @RequestMapping(value = "refresh/{partieId}", method = RequestMethod.GET)
    public String join(@PathVariable(value = "partieId") long partieId, Model model) {
        Partie partie = partieService.findOne(partieId);

        model.addAttribute("carteJoueur1", partie.getJoueurs().get(0).getCartes().get(0).getCouleur());
        model.addAttribute("carteJoueur2", partie.getJoueurs().get(1).getCartes().get(0).getCouleur());

        for(Joueur joueur : partie.getJoueurs()){
            List liste = joueur.getCartes();
            int size = liste.size();
            System.out.println(size);
            joueur.getCartes().clear();
            joueurService.save(joueur);
            for(int i =1;i<size;i++){
                joueur.getCartes().add((Carte) liste.get(i));
            }
            joueur.getCartes().add((Carte) liste.get(0));
        }
        
        model.addAttribute("partie", partie);
        model.addAttribute("joueur1", partie.getJoueurs().get(0));
        model.addAttribute("joueur2", partie.getJoueurs().get(1));

        return "partie";
    }

}
