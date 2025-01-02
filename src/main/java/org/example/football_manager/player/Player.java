package org.example.football_manager.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private UUID id;
    @Column(name = "command_id", nullable = false)
    private UUID commandId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private short age;
    @Column(name = "experience")
    private short experience;
}
