import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void firstArgumentIsNullExeption(){
        assertThrows(IllegalArgumentException.class, ()->{new Horse(null,20);});
    }
    @Test
    public void firstArgumentIsNullExeptionMessage(){
        var exception = assertThrows(IllegalArgumentException.class, ()->{new Horse(null,20);});
        assertEquals("Name cannot be null.",exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   ",
            "\t",
            "\n",
            " \t \n "
    })
    void constructorShouldThrowExceptionWhenNameIsBlank(String invalidName) {

        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(invalidName, 30);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   ",
            "\t",
            "\n",
            " \t \n "
    })
    void constructorShouldThrowExceptionWhenNameIsBlankMessage(String invalidName) {

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(invalidName, 30);
        });
        assertEquals("Name cannot be blank.",exception.getMessage());
    }
    @Test
    void seccondArgumentHasANegativeValueExeption(){
        assertThrows(IllegalArgumentException.class, ()->{new Horse("Bambie",-1);});
    }
    @Test
    void seccondArgumentHasANegativeValueExeptionMessage(){
        var exception = assertThrows(IllegalArgumentException.class, ()->{new Horse("Bambie",-1);});
        assertEquals("Speed cannot be negative.",exception.getMessage());
    }
    @Test
    void thirdArgumentHasANegativeValueExeption(){
        assertThrows(IllegalArgumentException.class, ()->{new Horse("Bambie",10,-1);});
    }
    @Test
    void thirdArgumentHasANegativeValueExeptionMessage(){
        var exception = assertThrows(IllegalArgumentException.class, ()->{new Horse("Bambie",10,-1);});
        assertEquals("Distance cannot be negative.",exception.getMessage());
    }

    Horse horse = new Horse("Betty", 25, 10);

    @Test
    void getNameReturnsCorrectName() {
        assertEquals("Betty", horse.getName());
    }
    @Test
    void getSpeedReturnsCorrectSpeed() {
        assertEquals(25, horse.getSpeed());
    }
    @Test
    void getAgeReturnsCorrectDistance() {
        assertEquals(10,horse.getDistance());
    }
    @Test
    void moveShouldCallGetRandomDouble(){
        try(MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)) {
            mock.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);

        Horse horse = new Horse("Betty", 25, 10);
        horse.move();
        mock.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }

    }

}
