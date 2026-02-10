public class Main {
     public static void main(String[] args){
        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();

        volvo.startEngine();
        saab.startEngine();
        saab.setTurboOn();

        volvo.gas(0.5);
        saab.gas(0.5);

        saab.turnLeft();
        saab.move();
        volvo.turnRight();
        volvo.move();

        System.out.println("Current x : " + saab.getX());
        System.out.println("Current Y: " + saab.getY());

        System.out.println("Volvo speed: " + volvo.getCurrentSpeed());
        System.out.println("Saab speed: " + saab.getCurrentSpeed());

        saab.setTurboOff();
     }

}
