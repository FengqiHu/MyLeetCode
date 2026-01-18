/**
 * @author louishu
 * @date 1/16/26 20:34
 * @description
 */
public class Tree {

    public static class TreeNode {
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
     *
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
     *
     * @param p
     * @param q
     * @return
     * @Date - 01/17/2026
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
     *
     * @param root
     * @return
     * @Date - 01/17/2026
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
     *
     * @param root
     * @return
     * @Date - 01/17/2026
     */
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root.left, root.right);
    }

    public static boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal - Medium
     *
     * @param preorder
     * @param inorder
     * @return
     * @Date - 01/17/2026
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder, int preRoot, int inStart, int inEnd) {
        System.out.println("inStart: " + inStart + " inEnd: " + inEnd);
        if (inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preRoot]);

        for (int i = inStart; i <= inEnd; i++) {
            if (preorder[preRoot] == inorder[i]) {
                int leftLen = i - inStart;
                root.left = buildTree(preorder, inorder, preRoot + 1, inStart, i - 1);
                root.right = buildTree(preorder, inorder, preRoot + leftLen + 1, i + 1, inEnd);
                break;
            }

        }
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root);
    }


}
