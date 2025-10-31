package org.leFab.players.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.leFab.exceptions.BadRequestException;
import org.leFab.exceptions.ResourceNotFoundException;
import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.leFab.players.services.interfaces.PlayerService;
import org.leFab.rank.dto.Rank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
//import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tennis/players")
@Tag(name = "players rest controller", description = "Endpoint for players")
@RequiredArgsConstructor
public class PlayerController {

    //injection of dependencies
    private final PlayerService playerService;


    @Operation(summary = "Get all players", description = "Retrieves the list of players.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players list",

                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlayerResponse.class)))})})
    @GetMapping()
    public ResponseEntity<List<PlayerResponse>> getAllPlayers(){
        return ResponseEntity.ok(playerService.getPlayers());// Collections.emptyList();
    }


    @Operation(summary = "Find the players by lastname firstname datebirth", description = "Retrieves the player by name.")
    @GetMapping("/search")
    public ResponseEntity<PlayerResponse> getPlayer(@RequestParam(required = false,name = "lastName") String lastName,
                                    @RequestParam(required = false,name = "firstName") String firstName,
                                    @RequestParam(required = false,name = "birthDay") LocalDate birthDay)
    {
        Optional<PlayerResponse> playerResponse = playerService.getPlayerSearch(firstName,lastName,birthDay);
        if(playerResponse.isPresent())
            return ResponseEntity.ok(playerResponse.get());
        else
         throw new ResourceNotFoundException("Player not found for this parameters");
    }


    @Operation(summary = "Find the players by id", description = "Retrieves the player by name.")
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable("id") String id){

        Optional<PlayerResponse> playerResponse = playerService.getPlayerById(id);
        if(playerResponse.isPresent())
            return ResponseEntity.ok(playerResponse.get());
        else
            throw new ResourceNotFoundException("Player not found for this parameters");
    }


    @Operation(summary = "save player", description = "save player.")
    @PostMapping()
    public ResponseEntity<String> savePlayer(@Valid @RequestBody PlayerRequest playerRequest){
      Boolean result= playerService.createPlayer(playerRequest);
      if(result)
          return ResponseEntity.ok("Player saved");
      else
          throw new BadRequestException("Player not saved");
    }


    @Operation(summary = "update the players", description = "maj the player.")
    @PutMapping()
    public ResponseEntity<PlayerResponse> updatePlayer(@Valid @RequestBody PlayerRequest playerRequest){
       PlayerResponse playerResponse= playerService.updatePlayer(playerRequest);
       if(playerResponse!=null)
           return ResponseEntity.ok(playerResponse);
       else
           throw new BadRequestException("Player not updated");
    }

    @Operation(summary = "update the player by id", description = "maj the player.")
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> updatePlayerById(@Valid @RequestBody PlayerRequest playerRequest, @PathVariable("id") String id){
        PlayerResponse playerResponse= playerService.updatePlayer(playerRequest);
        if(playerResponse!=null)
            return ResponseEntity.ok(playerResponse);
        else
            throw new BadRequestException("Player not updated");
    }


    @Operation(summary = "delete he players", description = "remove the player.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayerById(@PathVariable(name ="id",required = true) String id){
       Boolean result=playerService.deletePlayerById(id);
       if(result)
           return ResponseEntity.ok("Player deleted");
       else
           throw new BadRequestException("Player not deleted");
    }

}
