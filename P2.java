/*
Breadth-First Search (BFS) using two queues to track nodes and their parents level by level.

Time Complexity (TC)
O(N) - Each node is visited once in the worst case.

Space Complexity (SC)
O(N) - In the worst case (balanced tree), we store all nodes at the deepest level.

Explanation of the Code
We traverse the tree level by level using BFS while keeping track of each node's parent in a separate queue. 
If both target nodes are found in the same level, we check if their parents are different to determine if they are cousins.
 If only one node is found in a level, we return false since cousins must be on the same level.
 */


 class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        // Queue to store nodes at each level
        Queue<TreeNode> q = new LinkedList<>();
        // Queue to store parents of the nodes at each level
        Queue<TreeNode> pq = new LinkedList<>();
        
        // Start with the root node, root has no parent so we add null
        q.add(root);
        pq.add(null);

        while (!q.isEmpty()) {
            int size = q.size(); // Number of nodes at the current level
            boolean x_found = false, y_found = false; // Flags to check if x or y are found
            TreeNode x_parent = null, y_parent = null; // Variables to store their parents

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();  // Get the front node
                TreeNode pCurr = pq.poll(); // Get its parent

                // Check if the current node is x or y and store its parent
                if (curr.val == x) {
                    x_found = true;
                    x_parent = pCurr;
                }
                if (curr.val == y) {
                    y_found = true;
                    y_parent = pCurr;
                }

                // Add left child to queue if it exists, and track its parent
                if (curr.left != null) {
                    q.add(curr.left);
                    pq.add(curr);
                }

                // Add right child to queue if it exists, and track its parent
                if (curr.right != null) {
                    q.add(curr.right);
                    pq.add(curr);
                }
            }

            // If both nodes are found at the same level, check if they have different parents
            if (x_found && y_found) {
                return x_parent != y_parent; // True if they have different parents (cousins)
            }

            // If only one of the nodes is found, return false (they are not cousins)
            if (x_found || y_found) {
                return false;
            }
        }

        return false; // If we finish traversal and don't find both, return false
    }
}