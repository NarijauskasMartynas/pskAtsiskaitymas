package lt.vu.usecases.rest;

import lt.vu.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Alternative
@ApplicationScoped
@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerRestService {

    @Inject
    private EntityManager em;

    @GET
    @Path("/{playerId}")
    public Player find(@PathParam("playerId") Integer playerId) {
        return em.find(Player.class, playerId);
    }

    @POST
    @Path("/")
    @Transactional
    public Player create(@QueryParam("fullName") String fullName, @QueryParam("teamId") Integer id){
        Player player = new Player();
        player.setFullName(fullName);
        em.persist(player);
        return player;
    }

    @PUT
    @Path("/{playerId}")
    @Transactional
    public Response update(@PathParam("playerId") Integer playerId,
                           @QueryParam("fullName") String fullName){
        Player player = em.find(Player.class, playerId);
        if(player == null){
            throw new IllegalArgumentException("player id " + playerId + " not found");
        }
        player.setFullName(fullName);
        em.merge(player);
        em.flush();
        return Response.ok(player).build();
    }
}
