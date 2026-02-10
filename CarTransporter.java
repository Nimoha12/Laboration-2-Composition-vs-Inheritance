import java.awt.*;
import java.util.Stack;

public class CarTransporter extends Car {
    private final Ramp ramp;
    private final int maxCars;               // maximum cars allowed
    private final double loadDistance;       // max distance to load a car
    public Storable<Car> storage;
    // Flexible constructor
    public CarTransporter(String modelName, Color color, int maxCars, double loadDistance) {
        super(2, 1200, color, modelName);
        this.maxCars = maxCars;
        this.loadDistance = loadDistance;
        this.ramp = new Ramp(RampMode.Two_State, 1); // 0 is up and 1 is down
        storage = new Storable<Car>(maxCars);
    }

    // Default constructor
    public CarTransporter() {
        this("AKFS Mercedes Atego Enclosed Vehicle Transporter", Color.DARK_GRAY, 2, 5.0);
    }

    // Ramp controls
    public void raiseRamp() {
        if (getCurrentSpeed() == 0) ramp.lower(1); // up is 0
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) ramp.raise(1); // down is 1
    }

    public boolean isRampDown() {
        return ramp.isDown();
    }

    // Load a car
    public void loadCar(Car car) {
        if (!isRampDown()) throw new IllegalStateException("Cannot load, ramp is up!");
        if (car == this) throw new IllegalStateException("Cannot load self");
        if (car instanceof CarTransporter) throw new IllegalStateException("Cannot load another transporter!");
        if (!isCloseEnough(car)) throw new IllegalStateException("Cannot load, car is too far!");
        storage.store(car);

        car.setX(getX());
        car.setY(getY());
    }

    // Unload a car
    public Car unloadCar() {
        if (!isRampDown()) throw new IllegalStateException("Cannot unload, ramp is up!");
        if (storage.getItems().isEmpty()) throw new IllegalStateException("Cannot unload, no cars loaded!");

        Car returned = storage.unloadLast();
        returned.setX(getX());
        returned.setY(getY() - 1);
        return returned;
    }

    // Move transporter and update loaded cars
    @Override
    public void move() {
        if (!isRampDown()) {
            super.move();
            for (Car car : storage.getItems()) {
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
        return storage.size();
    }

    public boolean isFull() {
        return storage.size() >= maxCars;
    }

    public boolean isEmpty() {
        return storage.getItems().isEmpty();
    }

    public int getMaxCars() {
        return storage.getCapacity();
    }
}