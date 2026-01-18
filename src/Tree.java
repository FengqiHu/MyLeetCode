/**
 * @author louishu
 * @date 1/16/26 20:34
 * @description
 */
public class Tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 104. Maximum Depth of Binary Tree - Easy
     * @Date - 01/16/2026
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 100. Same Tree - Easy
     * @Date - 01/17/2026
     * @param p
     * @param q
     * @return
     */
    //    left:true        1        right: true
    //                    / \
    //    left:true      2   3      right: true
    //                  /   / \
    //    left:true    4   4   5    right: true
    //                / \ / \ / \
    //            null null null null

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;

        // is left tree same and right tree same
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 226. Reverse Binary Tree - Easy
     * @Date - 01/17/2026
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        return new TreeNode(root.val, right, left);

    }

    /**
     * 101. Symmetric Tree - Easy
     * @Date - 01/17/2026
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root.left, root.right);
    }
    public static boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
    }

}
