package facades;

import dtos.AnimalDTOs.AnimalDTO;
import entities.Animal;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;

class AnimalFacadeTest {
    private static EntityManagerFactory emf;
    private static AnimalFacade facade;
    private static Animal a1, a2;

    public AnimalFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AnimalFacade.getAnimalFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        a1 = new Animal("crab", 2);
        a2 = new Animal("shark", 11);


        try {
            em.getTransaction().begin();
            em.createNamedQuery("Animal.deleteAllRows").executeUpdate();
            em.persist(a1);
            em.persist(a2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    @Test
    public void createAnimalTest() throws Exception {
        AnimalDTO a3DTO = facade.create(new AnimalDTO("maddiker", 1));

        long expected = 3;
        long actual = facade.getCount();

        assertEquals(expected, actual);

        AnimalDTO a1DTO = new AnimalDTO(a1);
        AnimalDTO a2DTO = new AnimalDTO(a2);


        assertThat(facade.getAllAnimal().getAnimals(), containsInAnyOrder(a1DTO, a2DTO, a3DTO));

    }


    @Test
    public void getAllAnimalTest() throws Exception {
        int expected = 2;
        int actual = facade.getAllAnimal().getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void editAnimalTest() throws Exception {
        a1.setRace("elephant");
        a1.setAge(33);

        AnimalDTO editedAnimal = new AnimalDTO(a1);
        facade.editAnimal(editedAnimal);

        assertEquals(facade.getAnimalById(a1.getId()).getRace(), editedAnimal.getRace());
        assertEquals(facade.getAnimalById(a1.getId()).getAge(), editedAnimal.getAge());

    }

    @Test
    public void deleteAnimal() throws Exception {
        facade.deleteAnimal(a2.getId());

        assertEquals(1,facade.getAllAnimal().getSize());

        AnimalDTO a2DTO = new AnimalDTO(a2);
        assertThat(facade.getAllAnimal().getAnimals(), not(hasItem(a2DTO)));

    }

}