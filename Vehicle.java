import java.awt.*;

public abstract class Vehicle implements Moveable{

    private int nrDoors;
    public double enginePower;
    public double currentSpeed;
    private Color color;
    private String modelName;

    private enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
    // cars' directions and positions
    private double x;
    private double y;
    private Direction direction;

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
    }

    public int getNrDoors() { return nrDoors; }
    public double getEnginePower() { return enginePower;}
    public double getCurrentSpeed() { return currentSpeed;}
    public Color getColor() { return color;}
    public double getX() { return x;}
    public double getY() { return y;}

    public void setColor(Color clr) { color = clr;}

    public void startEngine() { currentSpeed = 0.1;}
    public void stopEngine() { currentSpeed = 0;}

    protected abstract double speedFactor();
    protected abstract void incrementSpeed(double amount);
    protected abstract void decrementSpeed(double amount);
    public void gas(double amount){incrementSpeed(amount);}
    public void brake(double amount){ decrementSpeed(amount);}

    @Override
    public void move(){
        switch (direction){
            case UP -> y += currentSpeed;
            case DOWN -> y -= currentSpeed;
            case LEFT -> x -= currentSpeed;
            case RIGHT -> x += currentSpeed;
        }
    }
    @Override
    public void turnLeft(){}
    @Override
    public void turnRight(){}

}



