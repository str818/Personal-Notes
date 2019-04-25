## 0. 讲解视频

[理论讲解-广度优先搜索](https://www.bilibili.com/video/av46292575/?p=27)

[理论讲解-深度优先搜索](https://www.bilibili.com/video/av46292575/?p=28)

[习题讲解-1](https://www.bilibili.com/video/av46292575/?p=29)

[习题讲解-2](https://www.bilibili.com/video/av46292575/?p=30)

[习题讲解-3](https://www.bilibili.com/video/av46292575/?p=31)

## 1. 二叉树的层次遍历

[Leetcode - 102 Binary Tree Level Order Traversal (Medium)](https://leetcode.com/problems/binary-tree-level-order-traversal/)

解法一：BFS

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
    if(root == null) return wrapList;
    queue.offer(root);
    while(!queue.isEmpty()){
        int levelNum = queue.size();
        List<Integer> subList = new LinkedList<Integer>();
        for(int i=0; i<levelNum; i++) {
            if(queue.peek().left != null) queue.offer(queue.peek().left);
            if(queue.peek().right != null) queue.offer(queue.peek().right);
            subList.add(queue.poll().val);
        }
        wrapList.add(subList);
    }
    return wrapList;
}
```

解法二：DFS

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    backtrack(ans, root, 0);
    return ans;
}
public void backtrack(List<List<Integer>> ans, TreeNode curNode, int level){ 
    if(curNode == null) return;
    if(ans.size() - 1 < level){
        ans.add(new ArrayList<Integer>());
    }
    ans.get(level).add(curNode.val);
    backtrack(ans, curNode.left, level + 1);
    backtrack(ans, curNode.right, level + 1);
}
```