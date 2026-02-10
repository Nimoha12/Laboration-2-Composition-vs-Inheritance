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
        return lift;
    }
}
