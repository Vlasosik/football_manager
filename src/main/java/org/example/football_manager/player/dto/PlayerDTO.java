package org.example.football_manager.player.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    @NotNull
    private UUID id;
    @NotNull
    private UUID commandId;
    @NotEmpty
    @Size(min = 1, max = 50)
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 50)
    private String lastName;
    @NotNull
    @Range(min = 0, max = 120)
    private short age;
    @NotNull
    @Min(0)
    private short experience;
}
