package org.leFab.players.services.implement;

import lombok.RequiredArgsConstructor;
import org.leFab.exceptions.BadRequestException;
import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.leFab.players.entities.PlayerEntity;
import org.leFab.players.repositories.PlayerRepository;
import org.leFab.players.services.interfaces.PlayerService;
import org.leFab.rank.dto.Rank;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImplement implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public Boolean createPlayer(PlayerRequest playerRequest) {
        if(playerRequest==null)
            return false;
        PlayerEntity playerEntity = PlayerEntity.builder()
                        .firstName(playerRequest.firstName())
                                .lastName(playerRequest.lastName())
                .birthDay(playerRequest.birthDay())
                .points(playerRequest.rank().points())
                .position(playerRequest.rank().position())
                                        .build();

        playerRepository.save(playerEntity);
        return true;
    }

    @Override
    public Boolean deletePlayerById(String id) {
        if (id == null)
            return false;
        playerRepository.deleteById(Long.valueOf(id));
        return true;
    }

    @Override
    public Boolean deletePlayer(String firstName, String lastName) {

        return null;
    }

    @Override
    public PlayerResponse updatePlayer(PlayerRequest playerRequest) {

        if(playerRequest==null)
            throw new BadRequestException("Player not saved");

        //check if player already exists ?


        PlayerEntity playerEntity = PlayerEntity.builder()
                .firstName(playerRequest.firstName())
                .lastName(playerRequest.lastName())
                .birthDay(playerRequest.birthDay())
                .points(playerRequest.rank().points())
                .position(playerRequest.rank().position())
                .build();

        playerRepository.save(playerEntity);
        return new PlayerResponse(playerRequest.firstName(),playerRequest.lastName(),playerRequest.birthDay(),playerRequest.rank());
    }

    @Override
    public PlayerResponse updatePlayerById(String id, PlayerRequest playerRequest) {


        if(playerRequest==null || id==null)
            throw new BadRequestException("Player not saved");

        //check if player already exists ?
        if(!playerRepository.existsById(Long.valueOf(id)))
            throw new BadRequestException("Player not found");


        PlayerEntity playerEntity = PlayerEntity.builder()
                .firstName(playerRequest.firstName())
                .lastName(playerRequest.lastName())
                .birthDay(playerRequest.birthDay())
                .points(playerRequest.rank().points())
                .position(playerRequest.rank().position())
                .build();

        playerRepository.save(playerEntity);
        return new PlayerResponse(playerRequest.firstName(),playerRequest.lastName(),playerRequest.birthDay(),playerRequest.rank());

    }

    @Override
    public List<PlayerResponse> getPlayers() {
      return playerRepository.findAll()
                .stream()
                .map(
                     player->new
                             PlayerResponse(
                                     player.getFirstName(),
                                     player.getLastName(),
                                     player.getBirthDay(),
                                    new Rank(player.getPoints()
                                            ,player.getPosition()))
                ).sorted(Comparator.comparing(p->p.rank().points())).toList();
      //return ResponseEntity.ok(listOfPlayers);
    }

    @Override
    public Optional<PlayerResponse> getPlayerSearch(String firstName, String lastName, LocalDate dateBirth) {
        return null;
    }


    @Override
    public Optional<PlayerResponse> getPlayerById(String id) {
        return null;
    }


}
