import java.util.ArrayList;
import java.util.List;

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
        return buildTreeInorder(preorder, inorder, 0, 0, inorder.length - 1);
    }

    /**
     * @param preRoot current root node
     * @param inStart the start index of current tree in inorder
     * @param inEnd
     * @return
     */
    public static TreeNode buildTreeInorder(int[] preorder, int[] inorder, int preRoot, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preRoot]);

        for (int i = inStart; i <= inEnd; i++) {
            if (preorder[preRoot] == inorder[i]) {
                // calculate the left tree length
                int leftLen = i - inStart;

                root.left = buildTreeInorder(preorder, inorder, preRoot + 1, inStart, i - 1);
                root.right = buildTreeInorder(preorder, inorder, preRoot + leftLen + 1, i + 1, inEnd);
                break;
            }
        }
        return root;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal - Medium
     *
     * @param inorder
     * @param postorder
     * @return
     * @Date - 01/18/2026
     */
    public TreeNode buildTreePostorder(int[] inorder, int[] postorder) {
        return buildTreePostorder(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildTreePostorder(int[] inorder, int[] postorder, int postRoot, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(postorder[postRoot]);

        // searching in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (postorder[postRoot] == inorder[i]) {
                // calculate the left tree length
                int rightLen = inEnd - i;
                root.left = buildTreePostorder(inorder, postorder, postRoot - rightLen - 1, inStart, i - 1);
                root.right = buildTreePostorder(inorder, postorder, postRoot - 1, i + 1, inEnd);
                break;
            }

        }
        return root;
    }

    /**
     * 112. Path Sum - Easy
     *
     * @param root
     * @param targetSum
     * @return
     * @Date - 01/18/2026
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf && targetSum == root.val) {
            return true;
        } else if (isLeaf && targetSum != 0) {
            return false;
        }
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);
        return left || right;
    }

    /**
     * 114. Flatten Binary Tree to Linked List - Medium
     *
     * @param root
     * @Date - 01/18/2026
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            // enter the left node
            if (root.left != null) {
                // to the most right node
                // set a start, from left branch
                TreeNode rightNode = root.left;
                while (rightNode.right != null) {
                    rightNode = rightNode.right;
                }
                // connect this node to the right branch
                rightNode.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    /**
     * 94. Binary Tree Inorder Traversal - Easy
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null)
            traverseTree(root, res);
        return res;
    }

    public void traverseTree(TreeNode root, List<Integer> res) {
        if (root.left != null)
            traverseTree(root.left, res);
        res.add(root.val);
        if (root.right != null)
            traverseTree(root.right, res);
    }


    /**
     * 95. Unique Binary Search Trees II - Medium
     *
     * @Date - 02/01/2026
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return buildTree(1, n);
    }

    public List<TreeNode> buildTree(int start, int end) {
        if (start > end)
            return null;
        List<TreeNode> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNode = buildTree(start, i - 1);
            List<TreeNode> rightNode = buildTree(i + 1, end);
            if(leftNode ==null || rightNode == null)
                continue;
            // enum the left and right trees
            for (int j = 0; j < leftNode.size(); j++) {
                for (int k = 0; k < rightNode.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode.get(j);
                    root.right = rightNode.get(k);
                    res.add(root);
                }
                
            }
        }
        return res;
    }

    /**
     * 96. Unique Binary Search Trees - Medium
     * @Date - 02/01/2026
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // left * right tree
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root);
    }


}
