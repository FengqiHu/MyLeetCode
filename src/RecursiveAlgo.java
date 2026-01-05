import java.util.ArrayList;
import java.util.List;

/**
 * @author louishu
 * @date 1/5/26 14:42
 * @description
 */
public class RecursiveAlgo {


    public static void possibleCombinations(int n) {
        List<List<Integer>> lists = new ArrayList<>();
//        dfs(n, n, new ArrayList<>(), lists);
        dfs2(n,n,1, new ArrayList<>(), lists);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

    }

    public static void dfs(int remain, int max, List<Integer> list, List<List<Integer>> lists) {
        if (remain == 0) {
            // can't use list, because it will overwrite previous lists
            //lists.add(list);
            // must use a new list
            // end
            lists.add(new ArrayList<>(list));
            // exit
            return;
        }

        int end = (remain > max) ? max : remain;

        for (int i = 1; i <= end; i++) {
            list.add(i);
            // trigger itself
            dfs(remain - i, i, list, lists);
            // backward
            list.remove(list.size() - 1);
        }
    }

    /**
     * @param n
     * @param remain
     * @param pos    the current processing position
     * @param list
     * @param lists
     */
    public static void dfs2(int n, int remain, int pos, List<Integer> list, List<List<Integer>> lists) {
        // finished the last digit
        if (pos == n) {
            // end
            if (remain == 0) {
                lists.add(new ArrayList<>(list));
            }
            //exit
            // can't in if, because current epoch should be ended whether remain = 0
            return;
        }

        if (remain < 0) {
            return;
        }

        for (int i = 0; i <= remain; i++) {
            list.add(i);
            dfs2(n, remain - i, pos + 1, list, lists);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        possibleCombinations(4);
    }
}
