package org.example.football_manager.command;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "commands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    @Id
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "commission_rate")
    private BigDecimal commissionRate;
}
