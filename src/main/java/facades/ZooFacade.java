package facades;

import dtos.AnimalDTOs.AnimalDTO;
import dtos.ZooDTOs.ZooDTO;
import dtos.ZooDTOs.ZooDTOs;
import entities.Animal;
import entities.Zoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class ZooFacade {


    private static EntityManagerFactory emf;
    private static ZooFacade instance;

    public static ZooFacade getZooFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ZooFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //add animal to  zoo??? Idk
    public ZooDTO addAnimalToZoo (int zooId, AnimalDTO animalDTO) {
        EntityManager em = emf.createEntityManager();
        Zoo zoo = em.find(Zoo.class, zooId);
        Animal animal = em.find(Animal.class, animalDTO.getId());
        zoo.addAnimalToZoo(animal);
        try {

            em.getTransaction().begin();
            em.merge(zoo);
            em.getTransaction().commit();

            return new ZooDTO(zoo);

        } finally {
            em.close();
        }
    }

    //Create new zoo
    public ZooDTO createZoo (ZooDTO zooDTO) {
        EntityManager em = emf.createEntityManager();
        Zoo zoo = new Zoo(zooDTO.getName(), zooDTO.getAdress());
        try {
            em.getTransaction().begin();
            em.persist(zoo);
            em.getTransaction().commit();
            return new ZooDTO(zoo);
        } finally {
            em.close();
        }
    }


    //Read zoo
    public ZooDTO getZooById(int id) {
        EntityManager em = emf.createEntityManager();
        Zoo zoo = em.find(Zoo.class, id);
        return new ZooDTO(zoo);
    }

    //Read all zoo's
    public ZooDTOs getAllZoo() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Zoo> query = em.createQuery("SELECT a FROM Zoo a", Zoo.class);
            List<Zoo> zooList = query.getResultList();
            return new ZooDTOs(zooList);

        } finally {
            em.close();
        }
    }

    //Update zoo
    public ZooDTO editZoo(ZooDTO zooDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Zoo zoo = em.find(Zoo.class, zooDTO.getId());

            zoo.setName(zooDTO.getName());
            zoo.setAdress(zooDTO.getAdress());

            em.getTransaction().begin();
            em.merge(zoo);
            em.getTransaction().commit();

            return new ZooDTO(zoo);

        } finally {
            em.close();
        }

    }

    //Delete zoo
    public ZooDTO deleteZoo(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Zoo zoo = em.find(Zoo.class, id);
        if (zoo == null) {
            throw new WebApplicationException("Cant find id to match zoo");
        } else {
            try {
                em.getTransaction().begin();
                em.remove(zoo);
                em.getTransaction().commit();

                return new ZooDTO(zoo);
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
