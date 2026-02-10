import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestCar {

    //Objects to test
    Car volvo;
    Car saab;

    @BeforeEach
    void setUP() {
        volvo = new Volvo240();
        saab= new Saab95();
    }

    //Testing that the constructor creates a car with correct settings
    @Test
    void testConstructorAndGetters() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(100, volvo.getEnginePower());
        assertEquals(Color.black, volvo.getColor());
        assertEquals("Volvo240", volvo.getmodelName());
        assertEquals(0, volvo.getCurrentSpeed());
        assertEquals(0, volvo.getX());
        assertEquals(0, volvo.getY());
    }

    //Tests that color can be changed
    @Test
    void testColor() {
        volvo.setColor(Color.PINK);
        assertEquals(Color.PINK, volvo.getColor());
    }

    //Tests startEngine and StopEngine
    @Test
    void TestStartAndStopEngine() {
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());

        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed());
    }

    //Checks that start engine always sets speed to 0.1
    @Test
    void testEngineAlwaysSetsSpeed(){
        volvo.gas(1);
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());

    }
    //Checks that gas increases with valid input and that gas cant decrease speed
    @Test
    void testGasValid() {
        volvo.startEngine();
        double initialSpeed = volvo.getCurrentSpeed();

        volvo.gas(1);
        assertTrue(initialSpeed < volvo.getCurrentSpeed());

        double inSpeed = volvo.getCurrentSpeed();
        volvo.gas(0.1);
        assertFalse(inSpeed > volvo.getCurrentSpeed());
    }


    // Checks that gas rejects invalid inputs
    @Test
    void testGasInvalid1(){
        volvo.startEngine();
        assertThrows(
                IllegalArgumentException.class, () -> volvo.gas(2));
    }
    @Test
    void testGasInvalid() {
        volvo.startEngine();
        assertThrows(
                IllegalArgumentException.class, () -> volvo.gas(-1));
    }

    //Checks that speed never exceeds engine power
    @Test
    void testNotExceedingEnginePower(){
        volvo.startEngine();
        for (int i = 0; i < 100; i++) volvo.gas(1);
        assertTrue(volvo.getEnginePower() >= volvo.getCurrentSpeed());
    }
    // Checks that brake reduces speed
    @Test
    void testBrake() {
        volvo.startEngine();
        volvo.gas(1);

        double initialSpeed = volvo.getCurrentSpeed();
        volvo.brake(1);

        assertTrue(volvo.getCurrentSpeed() < initialSpeed);
    }

    //Checks that brake rejects invalid input
    @Test
    void testBrakesInvalid(){
        assertThrows(
                IllegalArgumentException.class,
                () -> volvo.brake(-1));

        assertThrows(
                IllegalArgumentException.class,
                () -> volvo.brake(2));
    }

    //Checks that speed never becomes negative
    @Test
    void testBrakesStopsAtZero() {
        volvo.startEngine();
        for (int i = 0; i < 100; i++) volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    //Checks movement to different directions
    @Test
    void testMoveUP() {
        volvo.startEngine();
        volvo.gas(1);
        double intY = volvo.getY();
        volvo.move();
        assertTrue(volvo.getY() > intY);
    }

    @Test
    void testMoveRight() {
        volvo.startEngine();
        volvo.gas(1);
        volvo.turnRight();
        double intX = volvo.getX();
        volvo.move();
        assertTrue(volvo.getX() > intX);}

    @Test
    void testMoveDown() {
        volvo.startEngine();
        volvo.gas(1);
        volvo.turnRight();
        volvo.turnRight();
        double intY = volvo.getY();
        volvo.move();
        assertTrue(volvo.getY() < intY);}


    @Test
    void testMoveLeft() {
        volvo.startEngine();
        volvo.turnLeft();
        volvo.gas(1);
        double intX = volvo.getX();
        volvo.move();
        assertTrue(volvo.getX() < intX);
    }

    //Checks that 4 left/right turns reset direction (default is UP)
    @Test
    void testRightCycle() {
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();
        volvo.startEngine();
        volvo.gas(1);
        double intY = volvo.getY();
        volvo.move();
        assertTrue(volvo.getY() > intY);
    }

    @Test
    void testLeftCycle(){
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnLeft();

        volvo.startEngine();
        volvo.gas(1);
        double intY = volvo.getY();
        volvo.move();
        assertTrue(volvo.getY() > intY);
    }

    //Checks that turbo increases speed
    @Test
    void testSaabTurboON() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(1);

        double noTurbo = saab.getCurrentSpeed();

        saab.setTurboOn();
        saab.gas(1);
        assertTrue(noTurbo < saab.getCurrentSpeed());
    }

    //Checks that turbo off gives lower speed
    @Test
    void testSaabTurboOFF() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(1);
        double turboSpeed = saab.getCurrentSpeed();

        saab.stopEngine();
        saab.setTurboOff();
        saab.startEngine();
        saab.gas(1);
        double noTurboSpeed = saab.getCurrentSpeed();

        assertTrue(turboSpeed > noTurboSpeed);
    }

    //Checks volvo speed factor
    @Test
    void testVolvoSpeedFactor() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();

        double speed1 = volvo.getCurrentSpeed();
        volvo.gas(1);
        double speed2 = volvo.getCurrentSpeed();

        double actIncrease = speed2 - speed1;
        double expIncrease = volvo.getEnginePower() * 0.01 * 1.25;
        assertEquals(expIncrease, actIncrease, 0.0001);
    }

    //sanity check for speed
    @Test
    void testSanity() {
        //fails because gas still works even when engine is off
        volvo.startEngine();
        volvo.gas(0.5);
        volvo.stopEngine();
        double intSpeed = volvo.getCurrentSpeed();
        volvo.gas(1);
        assertEquals(intSpeed, volvo.getCurrentSpeed());

    }
}

