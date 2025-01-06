package org.example.football_manager.transfer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.transfer.dto.TransferDTO;
import org.example.football_manager.transfer.response.TransferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/create")
    public ResponseEntity<TransferResponse> create(@Valid @RequestBody TransferDTO transferDTO) {
        transferService.create(transferDTO);
        TransferResponse response = TransferResponse.builder()
                .message("Transfer successfully created!")
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<TransferDTO> readById(@RequestParam UUID id) {
        return ResponseEntity.ok(transferService.readById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<TransferResponse> update(@Valid @RequestBody TransferDTO transferDTO) {
        transferService.update(transferDTO);
        TransferResponse response = TransferResponse.builder()
                .message("Transfer successfully update!")
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TransferResponse> deleteById(@RequestParam UUID id) {
        transferService.deleteById(id);
        TransferResponse response = TransferResponse.builder()
                .message("Transfer successfully deleted!")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
