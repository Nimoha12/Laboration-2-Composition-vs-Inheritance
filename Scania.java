import java.awt.*;

public class Scania extends Car{
    private double flatBed;
    private boolean flatBedUp;          // the flatbed raise
    private boolean flatBedDown;        // the flatbed lower

    public Scania(){
        super(2, 1000, Color.white, "Scania");
        this.flatBed = 0.0;
    }

    public double getFlatBed(){
        return flatBed;}

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
    // a helper function (flag) that see if we can still raise or lower the flatbed
    // to prevent raising and lowering beyond the allowed range
    public boolean flatBedUp(){
        if(getFlatBed() > 0 && getFlatBed() < 70){
            return true;
        } else {
            return false;
        }
    }
    public boolean flatBedDown(){
        if(getFlatBed() > 0 && getFlatBed() < 70){
            return true;
        } else {
            return false;}
    }
    // the truck drives if and only the flatbed is at 0 degree
    // might fix this later
    @Override
    public void move(){
        if(flatBed == 0){
            super.move();
        }
    }
    // the truck cannot drive if the flatbed angle is non-zero, means speed = 0
    @Override
    protected double speedFactor(){
        if(flatBed > 0){
            return 0;
        }
        return getEnginePower() * 0.01;
    }
}
