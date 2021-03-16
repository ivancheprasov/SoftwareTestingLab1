package Bombardment;

import java.util.ArrayList;

public class Human implements Visitor {
    private Building position;
    private boolean isAlive = true;
    private final String Name;

    public Human(String name) {
        Name = name;
    }

    public Building getPosition() {
        return position;
    }

    public boolean isDead() {
        return !isAlive;
    }

    public String getName() {
        return Name;
    }

    public void setPosition(Building position) {
        this.position = position;
    }

    @Override
    public void enter(Building building) {
        if (building == null)
            throw new IllegalArgumentException("Building doesn't exist");
        ArrayList<Human> visitors = building.getVisitors();
        if (this.isDead())
            throw new IllegalStateException(this.getName() + " is dead");
        if (visitors.contains(this))
            throw new IllegalStateException(this.getName() + " has already entered");
        if(!building.isAccessible())
            throw new IllegalArgumentException("Building isn't accessible");
        if (building.getCapacity() == building.getVisitors().size())
            throw new ArrayIndexOutOfBoundsException("Building is full");
        this.setPosition(building);
        visitors.add(this);
    }

    @Override
    public void leave(Building building) {
        if (building == null)
            throw new IllegalArgumentException("Building doesn't exist");
        ArrayList<Human> visitors = building.getVisitors();
        if (this.isDead())
            throw new IllegalStateException(this.getName() + " is dead");
        if (!visitors.contains(this))
            throw new IllegalStateException(this.getName() + " isn't there");
        this.setPosition(null);
        visitors.remove(this);
    }

    public void die() {
        this.isAlive = false;
    }
}
