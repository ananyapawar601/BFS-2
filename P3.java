/* 
Iterative using a Queue to process pairs of nodes. BFS

Time Complexity (TC): O(N), where N is the number of nodes in the tree. Each node is processed exactly once.
Space Complexity (SC): O(N), in the worst case (if all nodes are on the same level), the queue will hold all the nodes.

The approach uses an iterative queue to compare nodes in pairs, starting from the left and right children of the root.
For each pair, it checks if they are symmetric by comparing outer children first (left.left with right.right) and inner children (left.right with right.left).
If any pair of nodes is mismatched or if their values differ, it returns false; otherwise, the tree is symmetric and returns true.
 */


 class Solution {
    // Main function to check if the tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        // If the tree is empty, it's symmetric
        if (root == null) return true;
        
        // Initialize a queue to store pairs of nodes to compare
        Queue<TreeNode> q = new LinkedList<>();
        
        // Add the left and right children of the root to the queue
        q.add(root.left);
        q.add(root.right);
        
        // Continue processing the queue until it's empty
        while(!q.isEmpty()) {
            // Poll two nodes for comparison
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            
            // If both nodes are null, continue to the next pair
            if (left == null && right == null) continue;
            
            // If one of the nodes is null or their values don't match, return false
            if (left == null || right == null || left.val != right.val) return false;
            
            // Enqueue the children in the correct order: outer children first, then inner children
            q.add(left.left);  // Left's left child
            q.add(right.right);  // Right's right child
            q.add(left.right);  // Left's right child
            q.add(right.left);  // Right's left child
        }
        
        // If all pairs passed the checks, the tree is symmetric
        return true;
    }
}


/*
Recursive solution DFS

Time Complexity:
O(N) where N is the number of nodes, since each node is visited once.
Space Complexity:
O(H) where H is the height of the tree, for the recursive stack space (in the worst case, it could be O(N) for a skewed tree).

The solution checks if the tree is symmetric by comparing the left and right subtrees of the root using recursion.
The isMirror function compares two nodes to see if they are mirrors by checking their values and recursively comparing their children in reverse order.
If all corresponding pairs of nodes are symmetric, the tree is symmetric; otherwise, it is not.
 */


 class Solution {
    // Main function to check if the tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        // If the tree is empty, it's symmetric; otherwise, check if left and right subtrees are mirrors of each other
        return root == null || isMirror(root.left, root.right);
    }

    // Helper function to check if two trees are mirrors of each other
    private boolean isMirror(TreeNode left, TreeNode right) {
        // If both nodes are null, they are mirrors (both empty)
        if (left == null && right == null) return true;
        
        // If one node is null and the other is not, they are not mirrors
        if (left == null || right == null) return false;
        
        // Check if current nodes' values are the same and recursively check if subtrees are mirrors
        return (left.val == right.val) &&
               isMirror(left.left, right.right) &&  // Check outer subtrees (left-left vs right-right)
               isMirror(left.right, right.left);    // Check inner subtrees (left-right vs right-left)
    }
}