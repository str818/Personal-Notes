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

## 2. 二叉树的最大深度

[Leetcode - 104 Maximum Depth of Binary Tree (Easy)](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

```java
public int maxDepth(TreeNode root) {
    if(root == null) return 0;
    return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
}
```

## 3. 二叉树的最小深度

[Leetcode - 111 Minimum Depth of Binary Tree (Easy)](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

```java
public int minDepth(TreeNode root) {
    if(root == null) return 0;
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    return (left == 0 || right == 0) ? left + right + 1: Math.mi(left,right) + 1;
}
```

## 4. 生成括号

[Leetcode - 22 Generate Parentheses (Medium)](https://leetcode.com/problems/generate-parentheses/)

题目描述：给定整数 n，生成包含 n 个括号合法字符串的所有组合。

```java
public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<String>();
    helper(res, "", 0, 0, n);
    return res;
}

public void helper(List<String> res, String curString, int openCount, int closeCount, int max){
    if(curString.length() == 2 * max){
        res.add(curString);
        return;
    }
    
    if(openCount < max){
        helper(res, curString + '(', openCount + 1, closeCount, max);
    }
    
    if(closeCount < openCount){
        helper(res, curString + ')', openCount, closeCount + 1, max);
    }
}
```