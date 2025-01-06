package org.example.football_manager.player;

import org.example.football_manager.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PlayerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    private Player player;

    @BeforeEach
    void setUp() {
        Command command = new Command(
                UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"),
                "Malorka",
                BigDecimal.valueOf(1000000.00),
                BigDecimal.valueOf(1.50)
        );
        entityManager.persistFlushFind(command);

        player = new Player(
                UUID.fromString("459ded1d-047c-43e5-9157-2b40073bf300"),
                command.getId(),
                "Fredy",
                "McDonald",
                (short) 18,
                (short) 0
        );
    }

    @Test
    void connectionToH2DataBaseTest() {
        Player persistedEntity = entityManager.persistFlushFind(player);
        assertNotNull(persistedEntity.getId());
    }
}
