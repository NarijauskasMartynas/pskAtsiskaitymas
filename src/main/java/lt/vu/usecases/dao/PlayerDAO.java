package lt.vu.usecases.dao;

import lt.vu.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PlayerDAO {

    @Inject
    private EntityManager entityManager;

    public void create(Player player) {entityManager.persist(player);}

    public List<Player> getAllPlayers() {
        return entityManager.createNamedQuery("Player.findAll", Player.class).getResultList();
    }

    public void updateAndFlush(Player player) {
        entityManager.merge(player);
        entityManager.flush();
    }

    public Player findById(Integer id){
      return entityManager.find(Player.class, id);
    }
}
