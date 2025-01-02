package org.example.football_manager.command.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandDTO {
    @NotNull
    private UUID id;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @DecimalMin(value = "0.0")
    private BigDecimal balance = BigDecimal.ZERO;
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private BigDecimal commissionRate = BigDecimal.ZERO;
}
