package org.leFab.players.services.interfaces;

import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerService {
    //create player
    public Boolean createPlayer(PlayerRequest playerRequest);


    //delete player
    public Boolean deletePlayerById(String id);
    public Boolean deletePlayer(String firstName, String lastName);

    //update player
    public PlayerResponse updatePlayer(PlayerRequest playerRequest);
    public PlayerResponse updatePlayerById(String id, PlayerRequest playerRequest);


    //get player(s)
    public List<PlayerResponse> getPlayers();
    public  Optional<PlayerResponse> getPlayerSearch(String firstName, String lastName, LocalDate dateBirth);
    public Optional<PlayerResponse> getPlayerById(String id);



}
