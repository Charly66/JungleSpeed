/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeed.service;

import junglespeed.entity.Joueur;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author admin
 */
public interface JoueurService extends CrudRepository<Joueur, Long>{
    
    public Joueur findByLogin(String login);
}
