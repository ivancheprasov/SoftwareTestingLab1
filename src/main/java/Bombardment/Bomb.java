package Bombardment;

public class Bomb {
    private boolean isDangerous = true;

    public void hit(Building building) {
        if (this.isDangerous() && building != null) {
            this.setDangerous(false);
            building.getDamaged();
        }
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }
}
