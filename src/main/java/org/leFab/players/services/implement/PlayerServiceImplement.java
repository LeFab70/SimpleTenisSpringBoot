package org.leFab.players.services.implement;

import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.leFab.players.services.interfaces.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerServiceImplement implements PlayerService {
    @Override
    public ResponseEntity<String> createPlayer(PlayerRequest playerRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> deletePlayerById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deletePlayer(String firstName, String lastName) {
        return null;
    }

    @Override
    public ResponseEntity<PlayerResponse> updatePlayer(PlayerRequest playerRequest) {
        return null;
    }

    @Override
    public ResponseEntity<PlayerResponse> updatePlayerById(String id, PlayerRequest playerRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<PlayerResponse>> getPlayers() {
        return null;
    }

    @Override
    public ResponseEntity<PlayerResponse> getPlayerSearch(String firstName, String lastName, LocalDate dateBirth) {
        return null;
    }


    @Override
    public ResponseEntity<PlayerResponse> getPlayerById(String id) {
        return null;
    }


}
