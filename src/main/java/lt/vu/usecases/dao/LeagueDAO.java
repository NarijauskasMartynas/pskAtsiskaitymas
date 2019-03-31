package lt.vu.usecases.dao;

import lt.vu.entities.League;
import lt.vu.entities.Player;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LeagueDAO {

    @Inject
    private EntityManager entityManager;

    public League getLeagueById(Integer id) {
        return entityManager.find(League.class, id);
    }

    public void UpdateLeague(League league){
        entityManager.merge(league);
    }
}


