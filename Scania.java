import java.awt.*;

public class Scania extends Car{
    private double flatBed;

    public Scania(){
        super(2, 1000, Color.white, "Scania");
        this.flatBed = 0.0;
    }

    public double getFlatBed(){ return flatBed;}
    // the maximum angle of the flatbed should not exceed 70 degree and raise only if the truck is stopped
    public void raiseFlatBed(double angle){
        if(getCurrentSpeed() == 0){
        flatBed = Math.min(flatBed + angle, 70.0);
        }
    }
    // the minimum angle of the flatbed should not exceed 0 degree
    public void lowerFlatBed(double angle){
        flatBed = Math.max(flatBed - angle, 0.0);
    }
    // the truck drives if and only the flatbed is at 0 degree
    @Override
    public void move(){
        if(flatBed == 0){
            super.move();
        }
    }
    @Override
    protected double speedFactor(){ return getEnginePower() * 0.01 * flatBed;}
}
