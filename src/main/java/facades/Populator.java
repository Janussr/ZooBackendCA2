/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;

import dtos.AnimalDTOs.AnimalDTO;
import dtos.ZooDTOs.ZooDTO;
import entities.Animal;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        AnimalFacade animalFacade = AnimalFacade.getAnimalFacade(emf);
        ZooFacade zooFacade = ZooFacade.getZooFacade(emf);

        animalFacade.create(new AnimalDTO(new Animal("Lion", 5)));
        animalFacade.create(new AnimalDTO(new Animal("Turtle", 173)));
        animalFacade.create(new AnimalDTO(new Animal("Elephant", 24)));

        zooFacade.createZoo(new ZooDTO("haslev zoo","haslev vej 15"));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
