package org.example.football_manager.command;

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
public class CommandRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    private Command command;

    @BeforeEach
    void setUp() {
        command = new Command();
        command.setId(UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"));
        command.setName("Malorka");
        command.setBalance(BigDecimal.valueOf(1000000.00));
        command.setCommissionRate(BigDecimal.valueOf(1.50));
    }


    @Test
    void connectionToH2DataBaseTest() {
        Command persistedEntity = entityManager.persistFlushFind(command);
        assertNotNull(persistedEntity.getId());
    }

}
