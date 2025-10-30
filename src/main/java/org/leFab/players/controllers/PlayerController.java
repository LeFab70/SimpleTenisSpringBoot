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
import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.leFab.players.services.interfaces.PlayerService;
import org.leFab.rank.dto.Rank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
//import java.util.Collections;
import java.util.List;

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
        return playerService.getPlayers();// Collections.emptyList();
    }


    @Operation(summary = "Find the players by lastname firstname datebirth", description = "Retrieves the player by name.")
    @GetMapping("/search")
    public ResponseEntity<PlayerResponse> getPlayer(@RequestParam(required = false,name = "lastName") String lastName,
                                    @RequestParam(required = false,name = "firstName") String firstName,
                                    @RequestParam(required = false,name = "birthDay") LocalDate birthDay)
    {
        return playerService.getPlayerSearch(firstName,lastName,birthDay);
    }


    @Operation(summary = "Find the players by id", description = "Retrieves the player by name.")
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable("id") String id){
        return playerService.getPlayerById(id);
    }


    @Operation(summary = "save player", description = "save player.")
    @PostMapping()
    public ResponseEntity<String> savePlayer(@Valid @RequestBody PlayerRequest playerRequest){
      return playerService.createPlayer(playerRequest);
    }


    @Operation(summary = "update the players", description = "maj the player.")
    @PutMapping()
    public ResponseEntity<PlayerResponse> updatePlayer(@Valid @RequestBody PlayerRequest playerRequest){
        return playerService.updatePlayer(playerRequest);
    }

    @Operation(summary = "update the player by id", description = "maj the player.")
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> updatePlayerById(@Valid @RequestBody PlayerRequest playerRequest, @PathVariable("id") String id){
        return playerService.updatePlayerById(id,playerRequest);
    }


    @Operation(summary = "delete he players", description = "remove the player.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayerById(@PathVariable("id") String id){
        return playerService.deletePlayerById(id);
    }

}
