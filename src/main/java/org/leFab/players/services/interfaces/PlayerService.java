package org.leFab.players.services.interfaces;

import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PlayerService {
    //create player
    public ResponseEntity<String> createPlayer(PlayerRequest playerRequest);


    //delete player
    public ResponseEntity<String> deletePlayerById(String id);
    public ResponseEntity<String> deletePlayer(String firstName, String lastName);

    //update player
    public ResponseEntity<PlayerResponse> updatePlayer(PlayerRequest playerRequest);
    public ResponseEntity<PlayerResponse> updatePlayerById(String id, PlayerRequest playerRequest);


    //get player(s)
    public ResponseEntity<List<PlayerResponse>> getPlayers();
    public ResponseEntity<PlayerResponse> getPlayerSearch(String firstName, String lastName, LocalDate dateBirth);
    public ResponseEntity<PlayerResponse> getPlayerById(String id);

    //ResponseEntity<List<PlayerResponse>> getPlayerSearch(String lastName, String dateBirth);
    //public ResponseEntity<List<PlayerResponse>> getPlayerSearch(String lastName, String dateBirth);

}
