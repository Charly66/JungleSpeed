/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeed.service;

import java.util.List;
import junglespeed.entity.Partie;
import junglespeed.enumeration.TypePartie;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author admin
 */
public interface PartieService extends CrudRepository<Partie, Long>{
    
    public List<Partie> findByTypePartie(TypePartie typePartie);
    public Partie findByJoueursId(long id);
    
}
