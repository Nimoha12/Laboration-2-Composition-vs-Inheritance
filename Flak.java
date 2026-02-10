/**
 * responsible for raise/lower logic of Scania truck
 */
public class Flak implements Liftable{
    private double flakAngle = 0;

    public double getAngle(){
        return flakAngle;
    }

    @Override
    public void raise(){
        flakAngle = Math.min(70, flakAngle + 10); // default step by 10 degree
    }

    @Override
    public void lower(){
        flakAngle = Math.max(0, flakAngle - 10);
    }

    @Override
    public boolean isRaised(){
        return flakAngle > 0;
    }
}
