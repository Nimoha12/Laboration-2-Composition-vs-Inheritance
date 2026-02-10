public class Ramp{
    private double angle;
    private final double maxAngle;
    private final RampMode mode;

    public Ramp(RampMode mode, double maxAngle) {
        this.angle = 0;
        this.mode = mode;
        this.maxAngle = maxAngle;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isDown() {
        return angle > 0;
    }

    public void raise(double amount) {
        if (mode == RampMode.Two_State) {
            angle = 1;
        } else {
            if (amount < 0) throw new IllegalStateException("Angle must be positive");
            angle = Math.min(angle + amount, 70);
        }

    }

    public void lower(double amount) {
        if (mode == RampMode.Two_State) {
            angle = 0;
        } else {
            if (amount < 0) throw new IllegalStateException("Angle must be positive");
            angle = Math.max(angle - amount,0);
        }
    }

}