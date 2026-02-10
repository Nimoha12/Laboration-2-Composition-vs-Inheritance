/**
 * what they have in common:
 * 1. can be raised and lowered
 * 2. the truck must not drive when raised
 * 3. the truck must be stopped to raise it
 */

public interface Liftable {
    void raise();
    void lower();
    boolean isRaised();
}
