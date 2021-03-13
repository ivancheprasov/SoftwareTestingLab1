import SplayTree.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SplayTreeTest {
    @Test
    public void testInsertion() {
        SplayTree tree = new SplayTree();
        tree.insertAll(50, 20, 40, 70, 35, 80);
        ArrayList<Integer> result = tree.getRootKeys();
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(80, 35, 20, null, null, 70, 50, 40, null, null, null, null, null)
        );
        Assertions.assertEquals(expected, result);
        tree.insert(55);
        result = tree.getRootKeys();
        expected = new ArrayList<>(
                Arrays.asList(55, 35, 20, null, null, 50, 40, null, null, null, 80, 70, null, null, null)
        );
        Assertions.assertEquals(expected, result);
        tree.insert(15);
        result = tree.getRootKeys();
        expected = new ArrayList<>(
                Arrays.asList(15, null, 55, 20, null, 35, null, 50, 40, null, null, null, 80, 70, null, null, null)
        );
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSize() {
        SplayTree tree = new SplayTree();
        Assertions.assertEquals(tree.size(), 0);
        tree.insertAll(50, 20, 40, 70, 35, 80, 55, 15);
        Assertions.assertEquals(tree.size(), 8);
        tree.search(35);
        Assertions.assertEquals(tree.size(), 8);
        tree.remove(80);
        Assertions.assertEquals(tree.size(), 7);
    }

    @Test
    public void testSearch() {
        SplayTree tree = new SplayTree();
        tree.insertAll(50, 60, 70, 80, 30);
        Assertions.assertTrue(tree.search(30));
        Assertions.assertTrue(tree.search(70));
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(70, 30, null, 50, null, 60, null, null, 80, null, null)
        );
        ArrayList <Integer> result = tree.getRootKeys();
        Assertions.assertEquals(expected, result);
        Assertions.assertFalse(tree.search(90));
        expected = new ArrayList<>(
                Arrays.asList(80, 70, 30, null, 50, null, 60, null, null, null, null)
        );
        result = tree.getRootKeys();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testRemoval() {
        SplayTree tree = new SplayTree();
        tree.insert(1);
        tree.remove(1);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(null);
        Assertions.assertEquals(tree.getRootKeys(), expected);
        tree.insertAll(50, 20, 40, 70, 35, 80, 55, 15);
        tree.remove(55);
        expected = new ArrayList<>(
                Arrays.asList(50, 15, null, 35, 20, null, null, 40, null, null, 80, 70, null, null, null)
        );
        Assertions.assertEquals(expected, tree.getRootKeys());
        tree.remove(10);
        expected = new ArrayList<>(
                Arrays.asList(15, null, 50, 35, 20, null, null, 40, null, null, 80, 70, null, null, null)
        );
        Assertions.assertEquals(expected, tree.getRootKeys());
        tree.remove(40);
        expected = new ArrayList<>(
                Arrays.asList(35, 15, null, 20, null, null, 50, null, 80, 70, null, null, null)
        );
        Assertions.assertEquals(expected, tree.getRootKeys());
        tree.remove(35);
        expected = new ArrayList<>(
                Arrays.asList(20, 15, null, null, 50, null, 80, 70, null, null, null)
        );
        Assertions.assertEquals(expected, tree.getRootKeys());
        tree = new SplayTree();
        tree.insertAll(80, 70, 60, 50);
        tree.remove(50);
        expected= new ArrayList<>(
                Arrays.asList(60, null, 70, null, 80, null, null)
        );
        Assertions.assertEquals(expected, tree.getRootKeys());
    }

}
