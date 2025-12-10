import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    void horseNameIsNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void horseNameIsNullExceptionMessage() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void hourseListEmptyException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void hourseListEmptyExceptionMessage() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void hippodromeInputListIsEqualReturnList() {
        List<Horse> horses = new ArrayList<>();
        Random random = new Random();
        Hippodrome hippodrome;

        for (int i = 1; i <= 30; i++) {
            String name = "Horse_" + i;

            double speed = 2.0 + (random.nextDouble() * 2.0);

            horses.add(new Horse(name, speed));
        }
        hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    static class MockHorse extends Horse {
        private boolean moveWasCalled = false;

        public MockHorse(String name, double speed) {
            super(name, speed);
        }

        @Override
        public void move() {
            moveWasCalled = true;
        }

        public boolean isMoveWasCalled() {
            return moveWasCalled;
        }
    }

    @Test
    void moveAllHorsesTest() {
        List<MockHorse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(new MockHorse("Horse_" + i, 2.0 + i * 0.1));
        }
        List<Horse> horses = new ArrayList<>(mockHorses);
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (MockHorse mockHorse : mockHorses) {
            assertTrue(mockHorse.isMoveWasCalled());
        }
    }

    @Test
    void getWinnerIsRturningTheHorseWithBiggestDistanceValue() {
        List<Horse> horses = new ArrayList<>();
        Random random = new Random();
        Hippodrome hippodrome;

        for (int i = 1; i <= 30; i++) {
            String name = "Horse_" + i;

            double speed = 2.0 + (random.nextDouble() * 2.0);
            double distance = 2.0 + (random.nextDouble() * 2.0);

            horses.add(new Horse(name, speed, distance));
        }
        hippodrome = new Hippodrome(horses);

        assertEquals(horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get(), hippodrome.getHorses().stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get());
    }

}
