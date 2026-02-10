import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Storable <T>{
    private final int capacity ;
    private final List<T> items = new ArrayList<>();

    public Storable (int capacity) {
        this.capacity = capacity;
    }

    //Add an item to storage
    public void store(T item){
        if (items.size() < capacity) {
            items.add(item);}
        else {
            throw new IllegalStateException();
        }

    }

    //Remove an item from storage
    public T unload(T item){
        if (items.isEmpty()){
            throw new NoSuchElementException();
        }
        else {
            items.remove(item); //
            return item;}
    }


    public T unloadLast(){
        if (items.isEmpty()){
            throw new NoSuchElementException();
        }
        else {
           return items.remove(items.size() - 1); }
    }


    //gets number of items currently in storage
    public int size(){
        return items.size();
    }

    //gets the max capacity for the workshop
    public int getCapacity()
    {return capacity;}

    public List<T> getItems() {
        return items;
    }
}

