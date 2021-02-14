package rest.trader.traderapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.trader.traderapi.BaseTraderTest;
import rest.trader.traderapi.entity.Universe;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UniverseRepositoryTest extends BaseTraderTest {

    @Autowired
    UniverseRepository universeRepository;

    @Test
    @Transactional
    void saveTest() {
        Universe newUniverse = Universe.builder().name("Test Universe").universeSeed(defaultUniverseSeed()).build();
        universeRepository.save(newUniverse);
    }

    @Test
    @Transactional
    void findAllByCreatedBeforeOrderByCreatedTest() {
        Universe newlyCreatedUniverse = createFakeUniverse();
        List<Universe> universeList = universeRepository.findAllByCreatedBeforeOrderByCreated(LocalDateTime.now());
        Universe foundUniverse = universeList.get(0);
        assertThat(newlyCreatedUniverse).isEqualTo(foundUniverse);
    }

}
