/*
Recursive approach

TC - O(n)
SC - O(n)

In this approach we will iterate the tree in such a way that we will keep on iterating and 
what we will be doing is as initailly the size of result list is going to be zero 
so whenever we will be getting a node at depth one that means we are gettting the rightmost element as we are iterating by 
calling the function first for right children then for left children. similarly we will keep on doing this till the time we are not at null node.

*/


class Solution {

    public List<Integer> rightSideView(TreeNode root) {
 
        List<Integer> result = new ArrayList<>();
 
        view(root,result,0);
 
        return result;
 
    }
 
    public void view(TreeNode root,List result,int depth){
 
        if(root==null) return;
 
        if(depth==result.size())
 
            result.add(root.val);
 
        view(root.right,result,depth+1);
 
        view(root.left,result,depth+1);
 
    }
 
 }

 /*
In iterative approach we will be pushing the elements in the queue level wise that means lets say 
we will be adding the root element initially so when we will be popping that element we will be pushing the children of that node in our queue and 
add that node in the list of rightmost elements and then we will pop the number of elements which we have added in this step and 
along with that we will keep on pushing the children of those nodes in our queue and whatever was the last popped out value in every iteration 
is going to be the rightmost element for that level and we will be storing that element in the final list.
*/


class Solution {

    public List<Integer> rightSideView(TreeNode root) {
 
        List<Integer> result = new ArrayList<>();
 
        Queue<TreeNode> q = new LinkedList<>();
 
        if(root==null) return result;
 
        q.add(root);
 
        while(!q.isEmpty()){
            Integer temp = null;
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode node = q.poll();
                temp=node.val;
                if(node.left!=null)
                    q.add(node.left);
                if(node.right!=null)
                    q.add(node.right);
            }
            result.add(temp);
        }
 
        return result;
 
    }
 
 }