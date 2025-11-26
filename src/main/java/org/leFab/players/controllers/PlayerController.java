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

import org.leFab.players.dto.PlayerRequest;
import org.leFab.players.dto.PlayerResponse;
import org.leFab.players.services.interfaces.PlayerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PlayerResponse>> getAllPlayers(){
        return ResponseEntity.ok(playerService.getPlayers());// Collections.emptyList();
    }


    @Operation(summary = "Find the players by lastname firstname datebirth", description = "Retrieves the player by name.")
    @GetMapping("/search")
    public ResponseEntity<PlayerResponse> getPlayer(@RequestParam(name = "lastName") String lastName,
                                    @RequestParam(name = "firstName") String firstName,
                                    @RequestParam(name = "birthDay") LocalDate birthDay)
    {
            return ResponseEntity.ok(playerService.getPlayerSearch(firstName,lastName,birthDay));
           }


    @Operation(summary = "Find the players by id", description = "Retrieves the player by name.")
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable(value = "id") String id){
             return ResponseEntity.ok(playerService.getPlayerById(id));
    }


    @Operation(summary = "save player", description = "save player.")
    @PostMapping()
    public ResponseEntity<PlayerResponse> savePlayer(@Valid @RequestBody PlayerRequest playerRequest){
      return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(playerRequest));
    }


    @Operation(summary = "update the player by id", description = "maj the player.")
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> updatePlayerById(@Valid @RequestBody PlayerRequest playerRequest, @PathVariable("id") String id){
        return ResponseEntity.ok(playerService.updatePlayerById(id,playerRequest));
    }


    @Operation(summary = "delete the player", description = "remove the player.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable(name ="id",required = true) String id){
       playerService.deletePlayerById(id);
       return ResponseEntity.noContent().build();
    }

}
