package lt.vu.usecases.dao;

import lt.vu.entities.Player;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TeamDAO {

    @Inject
    private EntityManager entityManager;

    public List<Team> getAllTeams(){
        return entityManager.createNamedQuery("Team.findAll", Team.class).getResultList();
    }

    public Team getTeamById(Integer id){
        return entityManager.find(Team.class, id);
    }

    public void createTeam(Team team){
        entityManager.persist(team);
    }

    public void updateTeam(Team team){
        entityManager.merge(team);
    }

}
