package org.leFab.players.services.implement;

import lombok.RequiredArgsConstructor;
import org.leFab.exceptions.BadRequestException;
import org.leFab.exceptions.ResourceNotFoundException;
import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.leFab.players.entities.PlayerEntity;
import org.leFab.players.repositories.PlayerRepository;
import org.leFab.players.services.interfaces.PlayerService;
import org.leFab.rank.dto.Rank;
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
    public PlayerResponse createPlayer(PlayerRequest playerRequest) {
        if(playerRequest==null)
            throw new BadRequestException("Body is empty,Player not saved");

        Optional<PlayerEntity> foundPlayer=playerRepository.findOneByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDay(playerRequest.firstName(), playerRequest.lastName(), playerRequest.birthDay());
        if(foundPlayer.isPresent())
            throw new BadRequestException("Player already exists");

        PlayerEntity playerEntity = PlayerEntity.builder()
                        .firstName(playerRequest.firstName())
                                .lastName(playerRequest.lastName())
                .birthDay(playerRequest.birthDay())
                .points(playerRequest.rank().points())
                .position(playerRequest.rank().position())
                                        .build();

        PlayerEntity savePlayer=playerRepository.save(playerEntity);
        return new PlayerResponse(
                savePlayer.getFirstName(),
                savePlayer.getLastName(),
                savePlayer.getBirthDay(),
                new Rank(savePlayer.getPoints(),savePlayer.getPosition())
        );
    }

    @Override
    public void deletePlayerById(String id) {
        if (id == null)
            throw new BadRequestException("Id is required, Player not deleted");
        PlayerEntity playerEntity = playerRepository.findById(Long.valueOf(id)).orElseThrow(()->new ResourceNotFoundException("Player not found"));
        playerRepository.delete(playerEntity);
    }

    @Override
    public void deletePlayer(String firstName, String lastName) {

        if(firstName==null || lastName==null)
            throw new BadRequestException("Player not deleted");
    }



    @Override
    public PlayerResponse updatePlayerById(String id, PlayerRequest playerRequest) {


        if(playerRequest==null || id==null)
            throw new BadRequestException("Player not saved");

        //check if player exists ?
       PlayerEntity playerEntity=playerRepository.findById(Long.valueOf(id)).orElseThrow(()->new ResourceNotFoundException("Player not found"));
        //check if player already exists
        Optional<PlayerEntity> foundPlayer=playerRepository.findOneByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDay(playerRequest.firstName(), playerEntity.getLastName(), playerEntity.getBirthDay());
       if(foundPlayer.isPresent() && !foundPlayer.get().getId().equals(playerEntity.getId()))
           throw new BadRequestException("Player already exists");

       return getPlayerResponse(playerRequest,playerEntity);
    }

    private PlayerResponse getPlayerResponse(PlayerRequest playerRequest,PlayerEntity playerEntity) {
        playerEntity.setFirstName(playerRequest.firstName());
        playerEntity.setLastName(playerRequest.lastName());
        playerEntity.setBirthDay(playerRequest.birthDay());
        playerEntity.setPoints(playerRequest.rank().points());
        playerEntity.setPosition(playerRequest.rank().position());
        PlayerEntity updated = playerRepository.save(playerEntity);

        return new PlayerResponse(
                updated.getFirstName(),
                updated.getLastName(),
                updated.getBirthDay(),
                new Rank(updated.getPoints(), updated.getPosition())
        );
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
    public PlayerResponse getPlayerSearch(String firstName, String lastName, LocalDate dateBirth) {
       Optional<PlayerEntity> playerEntity = playerRepository.findOneByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDay(firstName,lastName,dateBirth);
        //System.out.println("Recherche : " + firstName + " " + lastName + " " + dateBirth);
        //System.out.println("Résultat : " + playerEntity.isPresent());

        if (playerEntity.isEmpty())
            throw new ResourceNotFoundException("Player not found");
       return (new PlayerResponse(playerEntity.get().getFirstName(),playerEntity.get().getLastName(),playerEntity.get().getBirthDay(),new Rank(playerEntity.get().getPoints(),playerEntity.get().getPosition())));
    }


    @Override
    public PlayerResponse getPlayerById(String id) {

        Optional<PlayerEntity> playerEntity = playerRepository.findById(Long.valueOf(id));
    if (playerEntity.isEmpty())
        throw new ResourceNotFoundException("Player not found");
    return (new PlayerResponse(playerEntity.get().getFirstName(),playerEntity.get().getLastName(),playerEntity.get().getBirthDay(),new Rank(playerEntity.get().getPoints(),playerEntity.get().getPosition())));
    }


}
