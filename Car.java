import java.awt.*;

@SuppressWarnings("ALL")
public abstract class Car implements Movable {

    // Private variables
    private int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private boolean EngineOn=false;

   //All possible directions
    private enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
    //variables for moveable
    private Direction direction = Direction.UP;
    private double x=0;
    private double y=0;


    //Constructor
    public Car(int nrDoors, double enginePower, Color color,String modelName)
    {   this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    //Public getters
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public double getX() {return x; }
    public double getY(){return y;}
    public String getmodelName() {return modelName;}

    //public setter
    public void setColor(Color clr){
        color = clr;
    }

    //Engine (public)
    public void startEngine(){
        EngineOn=true;
        currentSpeed = 0.1;
    }
    public void stopEngine(){
        EngineOn=false;
        currentSpeed = 0;
    }

    //Speed (private)
    private void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }
    private void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    //Volvo and Saab have different way to calculate their speedFactor
    protected abstract double speedFactor();

    //Gas and brake (Public) needs to be validated
    public void gas(double amount){
        if(!EngineOn){return;}
        if (amount >= 0 && amount <= 1)
        {   incrementSpeed(amount);
            if (currentSpeed > enginePower)
            {   currentSpeed=enginePower;}
        }
        else { throw new IllegalArgumentException();}
    }

    public void brake(double amount){
        if (amount >= 0 && amount <= 1)
         { decrementSpeed(amount);
            if (currentSpeed < 0)
                currentSpeed = 0;}
        else { throw new IllegalArgumentException();}
    }

    //implement the methods for Movable
    @Override
    public void move() {
        switch (direction){
            case UP -> y+=currentSpeed;
            case DOWN -> y-=currentSpeed;
            case RIGHT -> x+=currentSpeed;
            case LEFT -> x-=currentSpeed;
        }
    }
    @Override
    public void turnLeft() {
        switch (direction) {
            case UP -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.UP;
        }
    }
    @Override
    public void turnRight() {
        switch(direction) {
            case UP -> direction=Direction.RIGHT;
            case RIGHT -> direction=Direction.DOWN;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;
        }
    }

}


