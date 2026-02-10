import java.awt.*;
import java.util.Stack;

public class CarTransporter extends Car {
    private final int maxCars;               // maximum cars allowed
    private final double loadDistance;       // max distance to load a car
    // Flexible constructor
    public CarTransporter(String modelName, Color color, int maxCars, double loadDistance) {
        super(2, 1200, color, modelName);
        this.maxCars = maxCars;
        this.loadDistance = loadDistance;
    }

    // Default constructor
    public CarTransporter() {
        this("AKFS Mercedes Atego Enclosed Vehicle Transporter", Color.DARK_GRAY, 2, 5.0);
    }
    }

    }

    // Load a car
    public void loadCar(Car car) {
        if (car == this) throw new IllegalStateException("Cannot load self");
        if (car instanceof CarTransporter) throw new IllegalStateException("Cannot load another transporter!");
        if (!isCloseEnough(car)) throw new IllegalStateException("Cannot load, car is too far!");

        car.setX(getX());
        car.setY(getY());
    }

    // Unload a car
    public Car unloadCar() {

    }

    // Move transporter and update loaded cars
    @Override
    public void move() {
            super.move();
                car.setX(getX());
                car.setY(getY());
            }
        }
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    private boolean isCloseEnough(Car car) {
        double distance = Math.hypot(getX() - car.getX(), getY() - car.getY());
        return distance <= loadDistance;
    }

    public int getNumberOfLoadedCars() {
    }

    public boolean isFull() {
    }

    public boolean isEmpty() {
    }

    public int getMaxCars() {
    }
}