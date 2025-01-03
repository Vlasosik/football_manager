package org.example.football_manager.transfer.dto;

import org.example.football_manager.transfer.Transfer;

public class TransferMapper {
    public static Transfer toEntity(TransferDTO transferDTO) {
        return new Transfer(
                transferDTO.getId(),
                transferDTO.getPlayerId(),
                transferDTO.getFromCommandId(),
                transferDTO.getToCommandId(),
                transferDTO.getTransferFee(),
                transferDTO.getSalesCommissionFee(),
                transferDTO.getTotalAmount(),
                transferDTO.getTransferDate()
        );
    }

    public static TransferDTO toDto(Transfer transfer) {
        return new TransferDTO(
                transfer.getId(),
                transfer.getPlayerId(),
                transfer.getFromCommandId(),
                transfer.getToCommandId(),
                transfer.getTransferFee(),
                transfer.getSalesCommissionFee(),
                transfer.getTotalAmount(),
                transfer.getTransferDate()
        );
    }
}
