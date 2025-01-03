package org.example.football_manager.player;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.player.dto.PlayerDTO;
import org.example.football_manager.player.response.PlayerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerDTO playerDTO) {
        playerService.create(playerDTO);
        PlayerResponse response = PlayerResponse.builder()
                .message("Player successfully created!")
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PlayerDTO> readById(@RequestParam UUID id) {
        return ResponseEntity.ok(playerService.readById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<PlayerResponse> update(@Valid @RequestBody PlayerDTO playerDTO) {
        playerService.update(playerDTO);
        PlayerResponse response = PlayerResponse.builder()
                .message("Command successfully update!")
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PlayerResponse> deleteById(@RequestParam UUID id) {
        playerService.deleteById(id);
        PlayerResponse response = PlayerResponse.builder()
                .message("Command successfully deleted!")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
