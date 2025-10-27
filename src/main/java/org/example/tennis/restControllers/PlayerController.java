package org.example.tennis.restControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.tennis.DTO.PlayerRequest;
import org.example.tennis.DTO.PlayerResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/tennis/players")
@Tag(name = "players rest controller", description = "Endpoint for players")
public class PlayerController {
    @Operation(summary = "Get all players", description = "Retrieves the list of players.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players list",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlayerResponse.class)))})})

    @GetMapping()
    public List<PlayerResponse> getAllPlayers(){
        return Collections.emptyList();
    }

    @Operation(summary = "Find the players", description = "Retrieves the player by name.")
    @GetMapping("/{lastName}")
    public PlayerResponse getPlayer(@PathVariable("lastName") String lastName){
        return null;
    }


    @Operation(summary = "Find the players by id", description = "Retrieves the player by name.")
    @GetMapping("/{id}")
    public PlayerResponse getPlayerById(@PathVariable("id") String id){
        return null;
    }



    @Operation(summary = "save player", description = "save player.")
    @PostMapping()
    public PlayerResponse savePlayer(@Valid @RequestBody PlayerRequest playerRequest){
        return null;
    }



    @Operation(summary = "update the players", description = "maj the player.")
    @PutMapping("/{id}")
    public PlayerResponse updatePlayerById(@PathVariable("id") String id, @Valid @RequestBody PlayerRequest playerRequest){
        return null;
    }

    @Operation(summary = "update the players", description = "maj the player.")
    @PutMapping()
    public PlayerResponse updatePlayer(@Valid @RequestBody PlayerRequest playerRequest){
        return null;
    }


    @Operation(summary = "delete he players", description = "remove the player.")
    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable("id") String id){

    }

    @Operation(summary = "find the players", description = "Find player by name,rank or position, datebirth.")
    @GetMapping("/search")
    public List<PlayerResponse> getPlayerSearch(@RequestParam(required = false) String lastName,
                                                @RequestParam(required = false) LocalDate dateBirth

                                                ){
        return null;
    }

}
