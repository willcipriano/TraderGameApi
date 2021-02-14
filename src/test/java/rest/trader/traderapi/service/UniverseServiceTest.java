package rest.trader.traderapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import rest.trader.traderapi.BaseTraderTest;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.repository.UniverseRepository;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UniverseServiceTest extends BaseTraderTest {

    @Mock
    UniverseRepository universeRepository;

    @InjectMocks
    UniverseService universeService;

    @BeforeEach
    void setMockOutput() {
        when(universeRepository.findAllByCreatedBeforeOrderByCreated(any()))
                .thenReturn(Collections.singletonList(Universe.builder().created(LocalDateTime.now())
                        .name("Mock Universe").universeSeed(defaultUniverseSeed()).build()));

        when(universeRepository.save(any())).thenAnswer(new Answer<Universe>() {
            @Override
            public Universe answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return (Universe) args[0];
            }
        });
    }

    @Test
    void getUniverse() {
        assertThat(universeService.getUniverse().getName()).isEqualTo("Mock Universe");
        assertThat(universeService.getUniverse().getName()).isEqualTo("Mock Universe");
    }

    @Test
    void createAndSaveUniverse() {
        Universe newlyCreatedUniverse = universeService.createAndSaveUniverse("New Universe", defaultUniverseSeed());
        assertThat(newlyCreatedUniverse.getName()).isEqualTo("New Universe");
    }

}
