package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AnimalDTOs.AnimalDTO;
import dtos.ZooDTOs.ZooDTO;
import facades.ZooFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/zoo")
public class ZooResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World! ZOO API'S HERE";
    }

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ZooFacade FACADE = ZooFacade.getZooFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String showMessage() {
        return "{\"msg\":\"This is the menu API section\"}";
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllZoos() {
        return GSON.toJson(FACADE.getAllZoo());
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public String getZooById(@PathParam("id") int id) {
        return GSON.toJson((FACADE.getZooById(id)));
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addZoo(String zoo) {
        ZooDTO zooDTO = GSON.fromJson(zoo, ZooDTO.class);
        ZooDTO zNew = FACADE.createZoo(zooDTO);

        return GSON.toJson((zNew));
    }

    @Path("/add/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addAnimalToZoo(@PathParam("id") int id, String animal) {
        AnimalDTO animalDTO = GSON.fromJson(animal, AnimalDTO.class);
        ZooDTO zooDtoEdited = FACADE.addAnimalToZoo(id, animalDTO);
        return GSON.toJson((zooDtoEdited));
    }

    @Path("edit/{id}")
    @PUT
    public String editZoo(@PathParam("id")int id, String zoo){
        ZooDTO zooDTO = GSON.fromJson(zoo, ZooDTO.class );
        zooDTO.setId(id);
        ZooDTO zooEdited = FACADE.editZoo(zooDTO);
        return GSON.toJson(zooEdited);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteZoo(@PathParam("id") int id) {
        ZooDTO zooDeleted = FACADE.deleteZoo(id);
        return GSON.toJson(zooDeleted);
    }
} 


