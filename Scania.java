import java.awt.*;

public class Scania extends Car {
    private double flatbedAngle;
    public Scania(){
        super(2,1000, Color.white,"Scania");
        this.flatbedAngle = 0.0;
    }

    //Increases flat angle with desired degree if the car is still
    public void raiseFlatBed(double degrees){
        if (degrees < 0 || degrees > 70) {
            throw new IllegalArgumentException(); }
        if (getCurrentSpeed() == 0 )
        {   flatbedAngle += degrees ;
            if (flatbedAngle > 70) flatbedAngle = 70;
        }}

    //Decreases flat angle with desired degree if the car is still
    public void lowerFlatBed(double degrees){
        if (degrees < 0 || degrees > 70) {
            throw new IllegalArgumentException();}
        if (getCurrentSpeed() == 0 )
        { flatbedAngle -= degrees;
            if (flatbedAngle < 0) {
                flatbedAngle = 0; }
        }
    }

    public double getCurrentAngle(){
        return flatbedAngle;}

    @Override
    public void move() {
        if (flatbedAngle == 0) {
            super.move();
        }
    }
    @Override
    public void startEngine() {
        if (flatbedAngle == 0) {
            super.startEngine();
        }
    }

    @Override
    public void gas(double amount) {
        if (flatbedAngle == 0) {
            super.gas(amount);
        }
    }

    //Scania is heavy so it is slower --> lower factor than Volvo and Saab
    @Override
    protected double speedFactor(){ return getEnginePower() * 0.005 ;}

}
