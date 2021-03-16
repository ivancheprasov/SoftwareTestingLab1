package Bombardment;

import java.util.ArrayList;

public abstract class Building {
    private BuildingState state = BuildingState.INITIAL;
    private final ArrayList<Human> visitors = new ArrayList<>();
    private final int capacity;

    public Building(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public BuildingState getState() {
        return state;
    }

    public ArrayList<Human> getVisitors() {
        return visitors;
    }

    public void getDamaged() {
        switch (this.getState()){
            case INITIAL:
                this.state = BuildingState.DAMAGED;
                break;
            case DAMAGED:
                this.state = BuildingState.DESTROYED;
                visitors.forEach(Human::die);
                break;
            case DESTROYED:
                throw new IllegalStateException("Building is already destroyed");
        }
    }

    public boolean isAccessible(){
        return getState() == BuildingState.INITIAL;
    }
}
