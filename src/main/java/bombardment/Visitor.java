package bombardment;

public interface Visitor {
    void enter(Building building);
    void leave(Building building);
}
