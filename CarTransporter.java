import java.awt.*;
import java.util.Stack;

    public class CarTransporter extends Car {
        private final int maxCars;               // maximum cars allowed
        private final Stack<Car> loadedCars;     // LIFO stack of cars
        private final double loadDistance;      // max distance to load a car
        private final Ramp ramp = new Ramp();   // composition

        // Flexible constructor
        public CarTransporter(String modelName, Color color, int maxCars, double loadDistance) {
            super(2, 1200, color, modelName);
            this.maxCars = maxCars;
            this.loadedCars = new Stack<>();
            this.loadDistance = loadDistance;
        }

        // Default constructor
        public CarTransporter() {
            this("AKFS Mercedes Atego Enclosed Vehicle Transporter", Color.DARK_GRAY, 2, 5.0);
        }
        public void rampUp(){
            if(getCurrentSpeed() != 0) throw new IllegalStateException();
            ramp.raise();
        }

        public void rampDown(){
            if(getCurrentSpeed() != 0) throw new IllegalStateException();
            ramp.lower();
        }

        // Load a car
        public void loadCar(Car car) {
            if (ramp.isRaised()) throw new IllegalStateException("Cannot load, ramp is up!");
            if (loadedCars.size() >= maxCars) throw new IllegalStateException("Cannot load, transporter is full!");
            if (car == this) throw new IllegalStateException("Cannot load self");
            if (car instanceof CarTransporter) throw new IllegalStateException("Cannot load another transporter!");
            if (!isCloseEnough(car)) throw new IllegalStateException("Cannot load, car is too far!");

            loadedCars.push(car);
            car.setX(getX());
            car.setY(getY());
        }

        // Unload a car
        public Car unloadCar() {
            if (ramp.isRaised()) throw new IllegalStateException("Cannot unload, ramp is up!");
            if (loadedCars.isEmpty()) throw new IllegalStateException("Cannot unload, no cars loaded!");

            Car car = loadedCars.pop();
            car.setX(getX());
            car.setY(getY() - 1);
            return car;
        }

        // Move transporter and update loaded cars
        @Override
        public void move() {
            if (ramp.isRaised()) {
                super.move();
                for (Car car : loadedCars) {
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
            return loadedCars.size();
        }

        public boolean isFull() {
            return loadedCars.size() >= maxCars;
        }

        public boolean isEmpty() {
            return loadedCars.isEmpty();
        }

        public int getMaxCars() {
            return maxCars;
        }
    }
