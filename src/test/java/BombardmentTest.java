import Bombardment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BombardmentTest {
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
    public void testAccessibility() {
        Bank bank = new Bank();
        Human testSubject1 = new Human("Rat1");
        testSubject1.enter(bank);
        Bomb bomb1 = new Bomb();
        bomb1.hit(bank);
        Human testSubject2 = new Human("Rat2");
        Assertions.assertThrows(IllegalArgumentException.class, () -> testSubject2.enter(bank));
        Bomb bomb2 = new Bomb();
        bomb2.hit(bank);
        Assertions.assertThrows(IllegalArgumentException.class, () -> testSubject2.enter(bank));
    }

    @Test
    public void testBuildingState() {
        Bank bank = new Bank();
        Bomb littleBoy = new Bomb();
        Bomb fatMan = new Bomb();
        Assertions.assertEquals(bank.getState(), BuildingState.INITIAL);
        littleBoy.hit(bank);
        Assertions.assertEquals(bank.getState(), BuildingState.DAMAGED);
        fatMan.hit(bank);
        Assertions.assertEquals(bank.getState(), BuildingState.DESTROYED);
        Assertions.assertThrows(IllegalStateException.class, () -> new Bomb().hit(bank));
    }

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

    @Test
    public void testCapacity(){
        Bank bank = new Bank();
        for (int i = 0; i < bank.getCapacity(); i++) {
            Human human = new Human("testSubject");
            human.enter(bank);
        }
        Assertions.assertEquals(bank.getCapacity(), bank.getVisitors().size());
        Human human = new Human("Oleg");
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> human.enter(bank));
        bank.getVisitors().get(1).leave(bank);
        Assertions.assertEquals(bank.getCapacity(), bank.getVisitors().size() + 1);
        human.enter(bank);
        Assertions.assertEquals(bank.getCapacity(), bank.getVisitors().size());
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
}
