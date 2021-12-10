package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AnimalDTOs.AnimalDTO;
import facades.AnimalFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/animal")
public class AnimalInternalResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AnimalFacade FACADE = AnimalFacade.getAnimalFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World! internal apis HERE ";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String showMessage() {
        return "{\"msg\":\"This is the menu API section\"}";
    }


    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllAnimals() {
        return GSON.toJson(FACADE.getAllAnimal());
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public String getAnimalById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.getAnimalById(id));
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addZoo(String animal) {
        AnimalDTO a = GSON.fromJson(animal, AnimalDTO.class);
        AnimalDTO aNew = FACADE.create(a);

        return GSON.toJson((aNew));
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createAnimal(String animal) {
        AnimalDTO a = GSON.fromJson(animal, AnimalDTO.class);
        AnimalDTO aNew = FACADE.create(a);

        return GSON.toJson((aNew));
    }


    @Path("edit/{id}")
    @PUT
    public String editAnimal(@PathParam("id")int id, String animal ){
        AnimalDTO animalDTO = GSON.fromJson(animal, AnimalDTO.class);
        animalDTO.setId(id);
        AnimalDTO animalEdited = FACADE.editAnimal(animalDTO);
        return GSON.toJson(animalEdited);
    }


    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteAnimal(@PathParam("id")int id){
    AnimalDTO animalDeleted = FACADE.deleteAnimal(id);
    return GSON.toJson(animalDeleted);
    }


}




