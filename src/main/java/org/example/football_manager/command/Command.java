package org.example.football_manager.command;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "command")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "commission_rate")
    private BigDecimal commissionRate;
}
