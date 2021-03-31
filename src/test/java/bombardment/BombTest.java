package bombardment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BombTest {
    @Test
    public void testBombDanger() {
        Bank bank1 = new Bank();
        Bank bank2 = new Bank();
        Bomb bomb = new Bomb();
        bomb.hit(bank1);
        bomb.hit(bank2);
        bomb.hit(null);
        Assertions.assertEquals(bank1.getState(), BuildingState.DAMAGED);
        Assertions.assertEquals(bank2.getState(), BuildingState.INITIAL);
    }
}
