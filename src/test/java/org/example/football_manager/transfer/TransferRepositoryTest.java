package org.example.football_manager.transfer;

import org.example.football_manager.command.Command;
import org.example.football_manager.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TransferRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    private Transfer transfer;

    @BeforeEach
    void setUp() {
        Command fromCommand = new Command(
                UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"),
                "Malorka",
                BigDecimal.valueOf(1000000.00),
                BigDecimal.valueOf(1.50)
        );
        entityManager.persistFlushFind(fromCommand);
        Command toCommand = new Command(
                UUID.fromString("7471cc38-83f8-4f2a-97ac-62b4ff3137d5"),
                "Betis",
                BigDecimal.valueOf(1052000.00),
                BigDecimal.valueOf(2.50)
        );
        entityManager.persistFlushFind(toCommand);
        Player player = new Player(
                UUID.fromString("89a096fc-439d-4482-97d0-bfd8c4ce61fe"),
                fromCommand.getId(),
                "Fredy",
                "McDonald",
                (short) 18,
                (short) 0
        );
        entityManager.persistFlushFind(player);
        transfer = new Transfer(
                UUID.fromString("f5bf87eb-d0d5-420e-82f8-926c2bb4b9e3"),
                player.getId(),
                fromCommand.getId(),
                toCommand.getId(),
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(8.00),
                BigDecimal.valueOf(10000.00),
                LocalDateTime.now()
        );
    }

    @Test
    void connectionToH2DataBaseTest() {
        Transfer persistedEntity = entityManager.persistFlushFind(transfer);
        assertNotNull(persistedEntity.getId());

    }
}
