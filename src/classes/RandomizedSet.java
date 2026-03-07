package classes;

import org.w3c.dom.Node;

import java.util.*;

/**
 * Q384. Insert Delete GetRandom O(1)
 *
 * @author louishu
 * @date 3/7/26 14:01
 * @description
 */
public class RandomizedSet {
    List<Integer> list;

    class Node{
        int num;
        Node next;

        public Node(int num){
            this.num = num;
        }
    }

    public RandomizedSet() {
        this.list = new ArrayList<>();

    }

    public boolean insert(int val) {
        if (list.contains(val))
            return false;
        list.add(val);
        return true;

    }

    public boolean remove(int val) {
        if (!list.contains(val))
            return false;
        list.remove(Integer.valueOf(val));
        return true;

    }

    public int getRandom() {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);

    }

}
