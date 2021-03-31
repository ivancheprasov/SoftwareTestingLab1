package bombardment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BuildingTest {

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
}