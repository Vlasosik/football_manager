package org.example.football_manager.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.command.response.CommandResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommandController {
    private final CommandService commandService;

    @PostMapping("/create")
    public ResponseEntity<CommandResponse> create(@Valid @RequestBody CommandDTO commandDTO) {
        commandService.create(commandDTO);
        CommandResponse response = CommandResponse.builder()
                .message("Command successfully created!")
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommandDTO> readById(@RequestParam UUID id) {
        return ResponseEntity.ok(commandService.readById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<CommandResponse> update(@Valid @RequestBody CommandDTO commandDTO) {
        commandService.update(commandDTO);
        CommandResponse response = CommandResponse.builder()
                .message("Command successfully update!")
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommandResponse> deleteById(@RequestParam UUID id) {
        commandService.deleteById(id);
        CommandResponse response = CommandResponse.builder()
                .message("Command successfully deleted!")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
