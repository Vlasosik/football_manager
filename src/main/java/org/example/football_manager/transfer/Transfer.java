package org.example.football_manager.transfer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transfers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    private UUID id;
    @Column(name = "player_id")
    private UUID playerId;
    @Column(name = "from_command_id")
    private UUID fromCommandId;
    @Column(name = "to_command_id")
    private UUID toCommandId;
    @Column(name = "transfer_fee")
    private BigDecimal transferFee;
    @Column(name = "sales_commission_fee")
    private BigDecimal salesCommissionFee;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "transfer_date")
    private LocalDateTime transferDate = LocalDateTime.now();
}
