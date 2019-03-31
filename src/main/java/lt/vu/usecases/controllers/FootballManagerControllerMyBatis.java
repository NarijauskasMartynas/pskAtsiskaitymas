package lt.vu.usecases.controllers;

import lombok.Getter;
import lt.vu.usecases.mybatis.dao.LeagueMapper;
import lt.vu.usecases.mybatis.dao.PlayerMapper;
import lt.vu.usecases.mybatis.dao.TeamLeagueMapper;
import lt.vu.usecases.mybatis.dao.TeamMapper;
import lt.vu.usecases.mybatis.model.League;
import lt.vu.usecases.mybatis.model.Player;
import lt.vu.usecases.mybatis.model.Team;
import lt.vu.usecases.mybatis.model.TeamLeague;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class FootballManagerControllerMyBatis {

    @Getter
    private Team team = new Team();

    @Getter
    private Player player = new Player();

    @Getter
    private League league = new League();

    @Getter
    private List<Player> players;

    @Getter
    private List<Team> teams;

    @Getter
    private TeamLeague teamLeague = new TeamLeague();

    @PostConstruct
    public void init(){
        loadAllTeams();
    }

    @Inject
    private TeamMapper teamMapper;

    @Inject
    private PlayerMapper playerMapper;

    @Inject
    private LeagueMapper leagueMapper;

    @Inject
    private TeamLeagueMapper teamLeagueMapper;

    @Transactional
    public void signNewPlayer(){
        player.setTeamId(team.getId());
        playerMapper.insert(player);
    }

    @Transactional
    public void CreateNewTeam(){
        List<League> leagueList = new ArrayList<>();
        leagueList.add(leagueMapper.selectByPrimaryKey(league.getId()));
        team.setLeagues(leagueList);
        teamMapper.insert(team);
        teamLeague.setLeagueId(league.getId());
        teamLeague.setTeamId(team.getId());
        teamLeagueMapper.insert(teamLeague);
    }

    private void loadAllTeams()
    {
        this.teams = teamMapper.selectAll();
    }
}
