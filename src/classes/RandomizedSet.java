package classes;

import java.util.*;

/**
 * Q384. Insert Delete GetRandom O(1)
 *
 * @author louishu
 * @date 3/7/26 14:01
 * @description Implement a data structure that supports insert, remove, and getRandom in O(1) time.
 *
 */
public class RandomizedSet {
    private Map<Integer, Integer> indexMap;  // value -> index in list
    private List<Integer> list;               // actual nums, get vals from here
    private Random random;

    public RandomizedSet() {
        this.indexMap = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }
        indexMap.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }

        // swap the last num with val, then delete the last num
        int currentIndex = indexMap.get(val);
        int lastNum = list.get(list.size()-1);

        // swap two nums
        int tmp = list.get(currentIndex);
        list.set(currentIndex, lastNum);

        // update index of the last num
        indexMap.put(lastNum,currentIndex);

        // remove the last num in two lists
        indexMap.remove(val);
        list.remove(list.size()-1);



        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

}
