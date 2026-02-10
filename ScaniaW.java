import java.awt.*;

public class ScaniaW extends Car{
    private double flatBed;
    private boolean flatBedUp;          // the flatbed raise
    private boolean flatBedDown;        // the flatbed lower

    public ScaniaW(){
        super(2, 1000, Color.white, "Scania");
        this.flatBed = 0.0;
    }

    //C:The Math.min does not validate anything, it silently clamps the value
    //C:I think it would be better to return an exception instead of correcting the input value to fit.
    public double getFlatBed(){ return flatBed;}
    // the maximum angle of the flatbed should not exceed 70 degree and raise only if the truck is stopped
    public void raiseFlatBed(double degree){
        flatBedUp();

        if(getCurrentSpeed() == 0){
            flatBed = Math.min(flatBed + degree, 70.0); //this just restricts the value from exceeding 70.
        }
    }

    //C: Same as above. Max does not validate it just clamps.
    // the minimum angle of the flatbed should not exceed 0 degree
    public void lowerFlatBed(double degree){
        flatBedDown();
        flatBed = Math.max(flatBed - degree, 0.0);
    }

    // C: This always true. The condition  > 0 ||  < 70 is always true.
    //C: Example: -10 < 70 --> True
    //C: Example: 100 > 0 --> True
    //C: There is no value that would make this False.
    public boolean flatBedUp(){
        if(getFlatBed() > 0 || getFlatBed() < 70){
            return true;
        } else {
            return false;
        }
    }
    //C: This condition is also always true.
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

    //C:Not logical
    //C: if flatbed is 0 the car won't ever move because speedFactor would also be 0.

    @Override
    protected double speedFactor(){ return getEnginePower() * 0.01 * flatBed;}
}