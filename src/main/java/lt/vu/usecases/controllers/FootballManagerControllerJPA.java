package lt.vu.usecases.controllers;

import lombok.Getter;
import lt.vu.entities.League;
import lt.vu.entities.Player;
import lt.vu.entities.Team;
import lt.vu.usecases.dao.LeagueDAO;
import lt.vu.usecases.dao.PlayerDAO;
import lt.vu.usecases.dao.TeamDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class FootballManagerControllerJPA {

    @Getter
    private Team team = new Team();

    @Getter
    private Player player = new Player();

    @Getter
    private League league = new League();

    @Getter
    private List<Team> teams;

    @PostConstruct
    public void init(){
        loadAllTeams();
    }

    @Inject
    private TeamDAO teamDAO;

    @Inject
    private PlayerDAO playerDAO;

    @Inject
    private LeagueDAO leagueDAO;

    @Transactional
    public void signNewPlayer(){
        teamDAO.getTeamById(team.getId());
        player.setTeam(team);
        playerDAO.create(player);
    }
//komentaras
    @Transactional
    public void CreateNewTeam(){
        leagueDAO.getLeagueById(league.getId());
        List<League> leagueList = new ArrayList<>();
        leagueList.add(league);
        team.setLeagues(leagueList);
        teamDAO.createTeam(team);
    }

    private void loadAllTeams()
    {
        this.teams = teamDAO.getAllTeams();
    }
}
