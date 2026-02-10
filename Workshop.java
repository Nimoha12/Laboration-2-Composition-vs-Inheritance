public class Workshop < T extends Car > {
    private final Storable <T> workshop;

    public Workshop(int capacity) {
        workshop = new Storable<>(capacity);
    }

    //loads car into workshop if workshop is not full
    public void load(T car){
       workshop.store(car);
    }

    //removes the desired car
    public T unload(T car){
        return workshop.unload(car);
    }

    //gets number of cars currently in workshop
    public int getNumberOfCars(){
        return workshop.size();
    }

    //gets the max capacity for the workshop
    public int getCapacity()
    {return workshop.getCapacity();}
}
