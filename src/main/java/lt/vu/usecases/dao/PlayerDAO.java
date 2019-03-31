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
}
