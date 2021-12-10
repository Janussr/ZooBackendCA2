package facades;

import dtos.AnimalDTOs.AnimalDTO;
import dtos.AnimalDTOs.AnimalsDTO;
import entities.Animal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class AnimalFacade {

    private static EntityManagerFactory emf;
    private static AnimalFacade instance;

    private AnimalFacade() {
    }

    public static AnimalFacade getAnimalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AnimalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    //Create new animal
    public AnimalDTO create(AnimalDTO animalDTO) {
        EntityManager em = emf.createEntityManager();
        Animal animal = new Animal(animalDTO.getRace(), animalDTO.getAge());
        try {
            //add to zoo here
            em.getTransaction().begin();
            em.persist(animal);
            em.getTransaction().commit();
            return new AnimalDTO(animal);
        } finally {
            em.close();
        }
    }

    //Read animal
    public AnimalDTO getAnimalById(int id) {
        EntityManager em = emf.createEntityManager();
        Animal animal = em.find(Animal.class, id);
        return new AnimalDTO(animal);
    }

    //Read all animals
    public AnimalsDTO getAllAnimal() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animalList = query.getResultList();
            return new AnimalsDTO(animalList);
        } finally {
            em.close();
        }
    }

    //:TODO Doesnt work bcus of no DB setup in this class.
    //Update animal
    public AnimalDTO editAnimal(AnimalDTO animalDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Animal animal = em.find(Animal.class, animalDTO.getId());

            animal.setRace(animalDTO.getRace());
            animal.setAge(animalDTO.getAge());

            em.getTransaction().begin();
            em.merge(animal);
            em.getTransaction().commit();

            return new AnimalDTO(animal);

        } finally {
            em.close();
        }

    }

    //delete animal
    public AnimalDTO deleteAnimal(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Animal animal = em.find(Animal.class, id);
        if (animal == null) {
            throw new WebApplicationException("no animal matches the id");
        } else {
            try {
                em.getTransaction().begin();
                em.remove(animal);
                em.getTransaction().commit();

                return new AnimalDTO(animal);
            } finally {
                em.close();
            }
        }
    }


    //Used for test (get number of ppl left in DB after delete test)
    public Long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(a.id) FROM Animal a", Long.class);
            long rows = query.getSingleResult();
            return rows;
        } finally {
            em.close();
        }
    }

}
