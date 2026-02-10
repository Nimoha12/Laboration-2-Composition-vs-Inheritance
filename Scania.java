import java.awt.*;

public class Scania extends Car{
    private final Flak flak = new Flak();   // composition

    public Scania(){
        super(2, 1000, Color.white, "Scania");
    }

    public double getFlak(){
        return flak.getAngle();
    }

    public void raiseFlak(double degree){
        if (getCurrentSpeed() != 0) throw new IllegalStateException("Cannot raise while moving");
        if (degree < 0 || degree > 70) throw new IllegalArgumentException("Out of range limit");
        flak.raise();

    }
    public void lowerFlak(double degree){
        if (getCurrentSpeed() != 0) throw new IllegalStateException("Cannot lower while moving");
        if (degree < 0 || degree > 70) throw new IllegalArgumentException("Out of range limit");
        flak.lower();
    }
    // check if flak is up, prevent accelerating while the flak is raised
    @Override
    public void gas(double amount){
        if(flak.isRaised()) return;
        super.gas(amount);

    }
    // the truck cannot drive if the flatbed angle is non-zero, means speed = 0
    @Override
    protected double speedFactor(){
        if(flak.isRaised()) return 0;
        return getEnginePower() * 0.01;
    }
}
