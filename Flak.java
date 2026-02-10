/**
 * responsible for raise/lower logic of Scania truck
 */
public class Flak implements Liftable{
    private double flakAngle = 0;

    public double getAngle(){
        return flakAngle;
    }
    public void raise_by(double amount){
        flakAngle = Math.min(70,flakAngle + amount);
    }
    public void lower_by(double amount){
        flakAngle = Math.max(0,flakAngle + amount);
    }

    @Override
    public void raise(){
        raise_by(10); // default step by 10 degree
    }

    @Override
    public void lower(){
        lower_by(10);
    }

    @Override
    public boolean isRaised(){
        return flakAngle > 0;
    }
}
