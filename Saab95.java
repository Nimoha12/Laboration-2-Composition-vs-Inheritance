import java.awt.*;

public class Saab95 extends Vehicle{

    private boolean turboOn;
    
    public Saab95(){
        super(2,125, Color.red, "Saab95");
	    this.turboOn = false;
        stopEngine();
    }
    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1;
        return enginePower * 0.01 * turbo;
    }
    @Override
    protected void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

}
