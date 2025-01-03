package org.example.football_manager.command.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.football_manager.command.validation.UniqueNameValidation;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandDTO {
    @NotNull
    private UUID id;
    @UniqueNameValidation
    @Size(min = 1, max = 50)
    private String name;
    @DecimalMin(value = "0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal balance = BigDecimal.ZERO;
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "10.00")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal commissionRate = BigDecimal.ZERO;
}
