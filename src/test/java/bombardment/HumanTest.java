package bombardment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HumanTest {
    @Test
    public void testPosition() {
        Human testSubject1 = new Human("Rat1");
        Human testSubject2 = new Human("Rat2");
        Bank bank = new Bank();
        testSubject1.enter(bank);
        ArrayList<Human> visitors = bank.getVisitors();
        Assertions.assertEquals(visitors.get(0), testSubject1);
        Assertions.assertEquals(testSubject1.getPosition(), bank);
        Assertions.assertEquals(visitors.size(), 1);
        testSubject2.enter(bank);
        visitors = bank.getVisitors();
        Assertions.assertEquals(visitors.get(1), testSubject2);
        Assertions.assertEquals(visitors.size(), 2);
        testSubject1.leave(bank);
        Assertions.assertEquals(visitors.get(0), testSubject2);
        Assertions.assertEquals(visitors.size(), 1);
        Assertions.assertNull(testSubject1.getPosition());
    }

    @Test
    public void testBuildingWrongInteraction() {
        Human human = new Human("Rat");
        Assertions.assertThrows(IllegalArgumentException.class, () -> human.enter(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> human.leave(null));
        Bank bank = new Bank();
        Assertions.assertThrows(IllegalStateException.class, () -> human.leave(bank));
        human.enter(bank);
        Assertions.assertThrows(IllegalStateException.class, () -> human.enter(bank));
    }

    @Test
    public void testAbilities(){
        Human human = new Human("Rat");
        Bank bank = new Bank();
        Assertions.assertFalse(human.isDead());
        human.die();
        Assertions.assertTrue(human.isDead());
        Assertions.assertThrows(IllegalStateException.class, () -> human.enter(bank));
        Assertions.assertThrows(IllegalStateException.class, () -> human.leave(bank));
        Assertions.assertEquals(human.getName(), "Rat");
    }

}
