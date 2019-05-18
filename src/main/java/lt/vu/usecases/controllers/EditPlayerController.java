package lt.vu.usecases.controllers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.Player;
import lt.vu.usecases.dao.PlayerDAO;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class EditPlayerController implements Serializable {

    @Getter
    private Player selectedPlayer;

    @Getter
    private Player conflictingPlayer;

    @Getter
    private List<Player> playerList;

    @Inject
    private PlayerDAO playerDAO;

    @PostConstruct
    public void init(){
        reloadAll();
    }

    public void prepareForEditing(Player player){
        selectedPlayer = player;
        conflictingPlayer = null;
    }

    public void reloadAll(){
        playerList = playerDAO.getAllPlayers();
    }

    @Transactional
    public void updateSelectedPlayer(){
        try{
            playerDAO.updateAndFlush(selectedPlayer);
            reloadAll();
        }
        catch(OptimisticLockException ole){
            conflictingPlayer = playerDAO.findById(selectedPlayer.getId());
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }
    }

    @Transactional
    public  void overwritePlayer(){
        selectedPlayer.setOptLockVersion(conflictingPlayer.getOptLockVersion());
        updateSelectedPlayer();
    }
}
