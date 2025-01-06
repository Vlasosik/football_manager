package org.example.football_manager.transfer;

import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.player.dto.PlayerDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TransferInvoicesProcessor {
    public BigDecimal getCostOfTransfer(PlayerDTO playerDTO) {
        if (playerDTO.getAge() == 0) {
            throw new IllegalArgumentException("Age cannot be zero");
        }
        short experienceYear = playerDTO.getExperience();
        short age = playerDTO.getAge();
        BigDecimal experienceMonths = BigDecimal.valueOf(experienceYear * 12);
        return experienceMonths.multiply(BigDecimal.valueOf(100000))
                .divide(BigDecimal.valueOf(age), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalAmountOfTransfer(CommandDTO commandDTO, PlayerDTO playerDTO) {
        BigDecimal commissionRate = commandDTO.getCommissionRate();
        BigDecimal costOfTransfer = getCostOfTransfer(playerDTO);
        BigDecimal commissionAmount = costOfTransfer.multiply(commissionRate)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return costOfTransfer.add(commissionAmount);
    }

}
