import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestWorkshop {

        private Workshop<Volvo240> volvo240Workshop;
        private Workshop<Saab95> saab95Workshop;
        private Workshop<Scania> scaniaWorkshop;

        private Volvo240 volvo1;
        private Volvo240 volvo2;

        private Saab95 saab1;
        private Saab95 saab2;
        private Saab95 saab3;

        private Scania scania1;
        private Scania scania2;

@BeforeEach
    void setUp() {//create workshops
     volvo240Workshop = new Workshop<>(3);
     saab95Workshop = new Workshop<>(2);
     scaniaWorkshop = new Workshop<>(5);

    //Create car objects
   volvo1 = new Volvo240();
   volvo2 = new Volvo240();

   saab1 = new Saab95();
   saab2 = new Saab95();
   saab3 = new Saab95();

    scania1 = new Scania();
    scania2 = new Scania();
   }


    //Test load
    @Test
    void testLoadCars(){
        volvo240Workshop.load(volvo1);
        volvo240Workshop.load(volvo2);
        assertEquals(2, volvo240Workshop.getNumberOfCars());
    }

    @Test
    void testLoadThrowsWhenFull(){
    assertThrows(IllegalStateException.class, () -> {
        saab95Workshop.load(saab1);
        saab95Workshop.load(saab2);
        saab95Workshop.load(saab3);
    });

    }


    @Test
    void testLoadDoesNotAddWhenFull(){
        saab95Workshop.load(saab1);
        saab95Workshop.load(saab2);
        assertThrows(IllegalStateException.class, () -> saab95Workshop.load(saab3));
        assertEquals(saab95Workshop.getCapacity(), saab95Workshop.getNumberOfCars());

    }

    @Test
    void testUnloadCarsRemovesCar(){
    scaniaWorkshop.load(scania1);
    scaniaWorkshop.load(scania2);
    scaniaWorkshop.unload(scania1);
    assertEquals(1, scaniaWorkshop.getNumberOfCars());
    }

    @Test
    void testUnloadThrowsWhenEmpty(){
    assertThrows(NoSuchElementException.class, () -> scaniaWorkshop.unload(scania1));
    }
    @Test
    void testUnloadCarReturnsCorrectCar(){
    scaniaWorkshop.load(scania1);
    scaniaWorkshop.load(scania2);
    Scania returned = scaniaWorkshop.unload(scania1);
    assertEquals(scania1,returned);

    }

    //Test getters
    @Test
    void testGetCapacity(){
    assertEquals(5, scaniaWorkshop.getCapacity());
    }

    @Test
    void testGetNumberOfCars(){
    volvo240Workshop.load(volvo1);
    volvo240Workshop.load(volvo2);
    volvo240Workshop.unload(volvo1);
    volvo240Workshop.unload(volvo2);
    assertEquals(0,volvo240Workshop.getNumberOfCars());
    }


}
