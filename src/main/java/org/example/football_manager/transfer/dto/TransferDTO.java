package org.example.football_manager.transfer.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {
    @NotNull
    private UUID id;
    @NotNull
    private UUID playerId;
    @NotNull
    private UUID fromCommandId;
    @NotNull
    private UUID toCommandId;
    @DecimalMin(value = "0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal transferFee = BigDecimal.ZERO;
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "10.00")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal salesCommissionFee = BigDecimal.ZERO;
    @DecimalMin(value = "0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private LocalDateTime transferDate = LocalDateTime.now();
}
