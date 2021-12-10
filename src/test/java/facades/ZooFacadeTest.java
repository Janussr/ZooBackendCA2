package facades;

import dtos.ZooDTOs.ZooDTO;
import entities.Zoo;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;

import static org.junit.jupiter.api.Assertions.*;

class ZooFacadeTest {
    private static EntityManagerFactory emf;
    private static ZooFacade facade;
    private static Zoo z1, z2;

    public ZooFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ZooFacade.getZooFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        z1 = new Zoo("kbh zoo", "haslev");
        z2 = new Zoo("jysk zoo", "jylland");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Zoo.deleteAllRows").executeUpdate();
            em.persist(z1);
            em.persist(z2);

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
    public void createZooTest() throws Exception {
        ZooDTO z3DTO = facade.createZoo(new ZooDTO("odense", "fynske"));

        long expected = 0;
        long actual = facade.getCount();


        assertEquals(expected, actual);

        ZooDTO z1DTO = new ZooDTO(z1);
        ZooDTO z2DTO = new ZooDTO(z2);

        assertThat(facade.getAllZoo().getZoos(), containsInAnyOrder(z1DTO, z2DTO, z3DTO));

    }


    @Test
    public void getAllZooTest() throws Exception {
        int expected = 2;
        int actual = facade.getAllZoo().getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void editZooTest() throws Exception {
        z1.setName("Edit1Name");
        z1.setAdress("edit1adress");

        ZooDTO editedZoo = new ZooDTO(z1);
        facade.editZoo(editedZoo);

        assertEquals(facade.getZooById(z1.getId()).getAdress(), editedZoo.getAdress());
        assertEquals(facade.getZooById(z1.getId()).getName(), editedZoo.getName());
    }



    @Test
    public void deleteZoo() throws Exception {
        facade.deleteZoo(z2.getId());

        assertEquals(1,facade.getAllZoo().getSize());

        ZooDTO z2DTO = new ZooDTO(z2);
        assertThat(facade.getAllZoo().getZoos(), not(hasItem(z2DTO)));


    }
}