/**
 * responsible for raise/lower logic of cars transporter
 */
public class Ramp implements Liftable{
    private boolean lift = false; // true=up

    @Override
    public void raise(){
        lift = true;        // always up
    }
    @Override
    public void lower(){
        lift = false;       // always down
    }
    @Override
    public boolean isRaised(){
        return lift;        // check current state
    }
}

/**
 * private final Ramp ramp = new Ramp();
 *
 * public void rampUp(){
 *     if(getCurrentSpeed() != 0) throw new...
 *     ramp.raise();
 * }
 *
 * public void rampDown(){
 *     if(getCurrentSpeed() !=0) throw new...
 *     ramp.lower();
 * }
 *
 * public void gas(double amount){
 *     if(ramp.isRaised()) return;
 *     super.gas(amount);
 * }
 */
