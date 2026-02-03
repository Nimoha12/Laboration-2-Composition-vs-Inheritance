import java.awt.*;

public class Scania extends Car{
    private double flatBed;
    private boolean flatBedUp;          // the flatbed raise
    private boolean flatBedDown;        // the flatbed lower

    public Scania(){
        super(2, 1000, Color.white, "Scania");
        this.flatBed = 0.0;
    }

    public double getFlatBed(){ return flatBed;}
    // the maximum angle of the flatbed should not exceed 70 degree and raise only if the truck is stopped
    public void raiseFlatBed(double degree){
        flatBedUp();
        if(getCurrentSpeed() == 0){
        flatBed = Math.min(flatBed + degree, 70.0);
        }
    }
    // the minimum angle of the flatbed should not exceed 0 degree
    public void lowerFlatBed(double degree){
        flatBedDown();
        flatBed = Math.max(flatBed - degree, 0.0);
    }
    public boolean flatBedUp(){
        if(getFlatBed() > 0 || getFlatBed() < 70){
            return true;
        } else {
            return false;
        }
    }
    public boolean flatBedDown(){
        if(getFlatBed() > 0 || getFlatBed() < 70){
            return true;
        } else {
            return false;
    }
    }
    // the truck drives if and only the flatbed is at 0 degree
    // might fix this later
    @Override
    public void move(){
        if(flatBed == 0){
            super.move();
        }
    }
    @Override
    protected double speedFactor(){ return getEnginePower() * 0.01 * flatBed;}
}
