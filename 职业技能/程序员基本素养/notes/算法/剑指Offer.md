* [编程语言](#编程语言)
    * [1. 赋值运算符函数(C++) 略](#赋值运算符函数C-略)
    * [2. 实现 Singleton 模式](#实现-singleton-模式)
* [数据结构](#数据结构)
    * [数组](#数组)
        * [3. 数组中重复的数字](#数组中重复的数字)
        * [4. 二维数组中的查找](#二维数组中的查找)
    * [字符串](#字符串)
        * [5. 替换空格](#替换空格)
    * [链表](#链表)
        * [6. 从尾到头打印链表](#从尾到头打印链表)
    * [树](#树)
        * [7. 重建二叉树](#重建二叉树)
        * [8. 二叉树的下一个结点](#二叉树的下一个结点)
    * [栈和队列](#栈和队列)
        * [9. 用两个栈实现队列](#用两个栈实现队列)
* [算法和数据操作](#算法和数据操作)
    * [递归和循环](#递归和循环)
        * [10.1 斐波那契数列](#斐波那契数列)
        * [10.2 跳台阶](#跳台阶)
        * [10.3 变态跳台阶](#变态跳台阶)
        * [10.4 矩形覆盖](#矩形覆盖)
    * [查找和排序](#查找和排序)
        * [11. 旋转数组的最小数字](#旋转数组的最小数字)
    * [回溯法](#回溯法)
        * [12. 矩阵中的路径](#矩阵中的路径)
        * [13. 机器人的运动范围](#机器人的运动范围)
    * [动态规划与贪婪算法](#动态规划与贪婪算法)
        * [14. 剪绳子](#剪绳子)
    * [位运算](#位运算)
        * [15. 二进制中 1 的个数](#二进制中-1-的个数)
* [高质量的代码](#高质量的代码)
    * [代码的完整性](#代码的完整性)
        * [16. 数值的整数次方](#数值的整数次方)
        * [17. 打印从 1 到最大的 n 位数](#打印从-1-到最大的-n-位数) 
        * [18. 删除链表的节点](#删除链表的节点)
            * [18.1 在 O(1) 时间内删除链表节点](#在-O(1)-时间内删除链表节点)
            * [18.2 删除链表中重复的节点](#删除链表中重复的节点)
        * [19. 正则表达式匹配](#正则表达式匹配)
        * [20. 表示数值的字符串](#表示数值的字符串)
        * [21. 调整数组顺序使奇数位于偶数前面](#调整数组顺序使奇数位于偶数前面)
    * [代码的鲁棒性](#代码的鲁棒性)
        * [22. 链表中倒数第 k 个结点](#链表中倒数第-k-个结点)
        * [23. 链表中环的入口结点](#链表中环的入口结点)
        * [24. 反转链表](#反转链表)
        * [25. 合并两个排序的链表](#合并两个排序的链表)
        * [26. 树的子结构](#树的子结构)

# 编程语言

### 赋值运算符函数(C++) 略

### 实现 Singleton 模式

[设计模式——单例模式](./设计模式.md#单例Singleton)

# 数据结构

## 数组

### 数组中重复的数字

[题目描述](https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：在一个长度为 n 的数组里的所有数字都在 0 ~ n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为 7 的数组 `{2,3,1,0,2,5,3}` ，那么对应的输出是重复的数字 2 或 3。

**解题思路：** 可以先排序再找，时间复杂度 O(nlogn)；也可以用哈希表，时间复杂度 O(n) ，额外空间复杂度 O(n) ；下面的这个方法复杂度是 O(n) + O(1) 。

数组中的数字都在 0 ~ n-1 的范围内，如果这个数组没有重复的数字，那么当数组排序之后数字 i 将出现在下标为 i 的位置。由于数组中有重复的数字，有些位置可能存在多个数字，同时有些位置可能没有数字。

从头到尾依次扫描这个数组中的每个数字，当扫描到下标为 i 的数字时，首先比较这个数字（用 m 表示）是不是等于 i 。如果是，则接着扫描下一个数字；如果不是，则再拿它和第 m 个数字进行比较。如果它和第 m 个数字相等，就找到了一个重复的数字（该数字在下标为 i 和 m 的位置都出现了）；如果它和第 m 个数字不相等，就把第 i 个数字和第 m 个数字交换，把 m 放到输入它的位置。重复这个比较、交换的过程，直到发现一个重复的数字。

```
数组：(2, 3, 1, 0, 2, 5)
position-0 : (2,3,1,0,2,5) // 2 <-> 1
             (1,3,2,0,2,5) // 1 <-> 3
             (3,1,2,0,2,5) // 3 <-> 0
             (0,1,2,3,2,5) // already in position
position-1 : (0,1,2,3,2,5) // already in position
position-2 : (0,1,2,3,2,5) // already in position
position-3 : (0,1,2,3,2,5) // already in position
position-4 : (0,1,2,3,2,5) // nums[i] == nums[nums[i]], exit
```

尽管有一个两重循环，但每个数字做多只要交换两次就能找到属于它自己的位置，因此总的时间复杂度是 O(n) 。

```java
public boolean duplicate(int[] nums, int length, int[] duplication) {
    if (nums == null || length <= 0)
        return false;
    for (int i = 0; i < length; i++) {
        while (nums[i] != i) {
            if (nums[i] == nums[nums[i]]) {
                duplication[0] = nums[i];
                return true;
            }
            swap(nums, i, nums[i]);
        }
    }
    return false;
}

private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```

### 二维数组中的查找

[题目描述](https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：有一个二维数组（每一维数组的长度股相同），每一行按照从左到右的顺序排列，每一列按照从上到下递增的顺序排列。完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

**解题思路：** 从二维数组的右上角的元素开始看，该元素的列从上到下是升序排列，行从右向左是降序排列，这就营造了一个很好的二分环境，从该元素开始，如果 target 大于该元素则向下继续比较；如果 target 小于该元素则向左继续比较；如果相等则返回 true，直到索引越界，二维数组中没有该整数，返回 false 。

```java
public boolean Find(int target, int [][] array) {
    while(i < array.length && j >= 0){
        if(target > array[i][j]){
            i++;
        }else if(target < array[i][j]){
            j--;
        }else{
            return true;
        }
    }
    return false;
}
```
## 字符串

### 替换空格

[题目描述](https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：将字符串中的每个空格替换成"%20"。

**解法一：** 重新开辟一个字符串进行替换

```java
public String replaceSpace(StringBuffer str) {
    StringBuilder newStr = new StringBuilder();
    for(int i=0;i<str.length();i++){
        if(str.charAt(i)==' '){
            newStr.append('%');
            newStr.append('2');
            newStr.append('0');
        }else{
            newStr.append(str.charAt(i));
        }
    }
    return newStr.toString();
}
```

**解法二：** 在当前字符替换，从后往前。

```java
public String replaceSpace(StringBuffer str) {
    int spaceLength = 0;
    for(int i = 0;i<str.length();i++){
          if(str.charAt(i) == ' ') spaceLength++;
    }
        
    int newLength = str.length() + 2 * spaceLength;
    int newIndex = newLength - 1;
    int oldIndex = str.length() - 1;
    str.setLength(newLength);
    for(;oldIndex >= 0 && newIndex > oldIndex;oldIndex--){
         if(str.charAt(oldIndex) == ' '){
            str.setCharAt(newIndex--, '0');
            str.setCharAt(newIndex--, '2');
            str.setCharAt(newIndex--, '%');
        }else {
        	str.setCharAt(newIndex--, str.charAt(oldIndex));
        }
    }
     return str.toString();
}
```

## 链表

### 从尾到头打印链表

[题目描述](https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。

**解题思路：** 遍历的顺序是从头到尾，输出的顺序是从尾到头，典型的「后进先出」，可以用递归或栈实现，但是当链表非常长的时候，使用递归会导致函数调用的层级很深，从而有可能导致函数调用栈溢出，所以用栈实现鲁棒性更好一些。

```java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    ArrayList<Integer> al = new ArrayList<Integer>();
    Stack<Integer> stack = new Stack<Integer>();
    ListNode node = listNode;
     while(node != null){
         stack.push(node.val);
        node = node.next;
    }
    while(!stack.isEmpty()){
        al.add(stack.pop());
    }
    return al;
}
```

## 树

### 重建二叉树

[题目描述](https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：输入某二叉树的前序和中序遍历结果，重建出该二叉树。

**解题思路：** 前序遍历的第一个数字为根结点，在中序遍历序列中找到根结点的位置，能够确定左、右子树结点的数量，递归分分别构建左右子树。

```java
public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    return reConstructBinaryTree(pre,0,in,0,in.length);
}
    
public TreeNode reConstructBinaryTree(int[] pre, int preIndex,int[] in,int start, int end) {	
    if(preIndex >= pre.length || start == end) return null;
	
    TreeNode node = new TreeNode(pre[preIndex]);
	
    for(int i = start;i<end;i++) {
        if(in[i] == pre[preIndex]) {
            node.left = reConstructBinaryTree(pre,preIndex+1,in,start,i);
            node.right = reConstructBinaryTree(pre,preIndex+i-start+1,in,i+1,end);
        }
    }	
    return node;
}
```

### 二叉树的下一个结点

[题目描述](https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

**解题思路：** 解题的时候要将情况考虑清楚，（1） 若一个结点有右子树，那么下一个结点就是右子树中的最左子结点。 （2） 若没有右子树，则向上遍历，找到第一个作为左子结点的结点。

```java
public TreeLinkNode GetNext(TreeLinkNode pNode){
        
    if(pNode == null) return null;
        
    if(pNode.right != null){
        pNode = pNode.right;
        while(pNode.left != null){
            pNode = pNode.left;
        }
        return pNode;
    }
        
    while(pNode.next != null){
        if(pNode.next.left == pNode){
            return pNode.next;
        }   
        pNode = pNode.next;
    }
    return null;
}
```

## 栈和队列

### 用两个栈实现队列

[题目描述](https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

**解题思路：** 首先明确队列是先进先出，栈是后进后出的。每次 push 进 stack1 中；每次 pop 先将 stack1 中的元素都 pop 到 stack2 中，这时 stack2 的栈顶即为「先进」的元素，将该元素 pop 出，之后再将 stack2 中的所有元素 pop 回 stack1 中。

```java
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
	
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int ans = stack2.pop();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return ans;
    }
}
```
看了大佬的解法，发现再把元素 push 回去是多次一举。
```java
public int pop(){
    if(stack2.empty()){
        while(!stack1.empty()){
            stack2.push(stack1.pop());
        }
    }
    return stack2.pop();
}
```

# 算法和数据操作

## 递归和循环

### 斐波那契数列

[题目描述](https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=13&tqId=11160&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：输入一个整数 n ,输出斐波那契数列的第 n 项（从 0 开始）。

**解题思路：** 斐波那契数列的计算方法为下面的公式，递归的解法写起来很方便，但是会重复计算很多元素，所以要把前两个元素记录下来。
<div align="center">  <img src=".\pic\Fibonacci.gif" width="30%"/> </div><br>

```java
public int Fibonacci(int n) {
    if(n < 2) return n;
    int a = 0,b = 1;
    for(int i = 2;i <= n;i++){
        int ans = a + b;
        a = b;
        b = ans;
    }
    return b;
}
```

### 跳台阶

[题目描述](https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

**解题思路：** 最简单的情况，如果只有 1 级台阶，显然只有一种跳法。如果有 2 级台阶，就有两种跳法（一种是分两次跳，另一种是一次跳两级）。
把 n 级台阶时的跳法看成是 n 的函数，记为 f(n) 。当 n>2 时，第一次跳的时候有两种不同的选择：一是第一次只跳 1 级，此时跳法数目等于后面剩下的 n-1 级台阶跳法数目，即为 f(n-1);另外一种选择是第一次跳 2 级，此时跳法数目等于后面剩下的 n-2 级台阶的跳法数目，即为 f(n-2)。因此 n 级台阶的不同跳法总数为 f(n)=f(n-1)+f(n-2)。可以看出是斐波那契数列。

```java
public int JumpFloor(int target) {
    if(target <= 2) return target;
    int a = 1,b = 2;
    for(int i = 3;i <= target;i++){
        int ans = a + b;
        a = b;
        b = ans;
    }
    return b;
}
```

### 变态跳台阶

[题目描述](https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tqId=11162&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级 ...... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少中跳法。

**解法一：** 每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳，所以所有的跳法可以用公式 ```2^(n-1)``` 计算。

```java
public int JumpFloorII(int target) {
    if(target <= 0){
        return 0;
    } else if(target == 1){
        return 1;
    } else {
        return (int)Math.pow(2,target - 1);
    }
}
```

**解法二：** 按照上一题 [跳台阶](#跳台阶) 的思路可以推导出 ```f(n) = f(n-1) + f(n-2) + f(n-3) + ... + f(n-n)``` ，这样用一个循环就可以计算了，下面再简化一下，根据下面两个公式：

```f(n-1) = f(0) + f(1) + f(2) + ... + f((n-1)-1) = f(0) + f(1) + f(2) + ... + f(n-2)```

```f(n) = f(0) + f(1) + f(2) + ... + f(n-2) + f(n-1)```

将第一个公式带入第二个公式，可以得出 ```f(n) = 2 * f(n-1)``` 。

```java
public int JumpFloorII(int target) {
    if(target < 2) return target;
    int fn = 1;//初始为 f(1) 的值
    for(int i = 2;i <= target;i++){
        fn = 2 * fn;
    }
    return fn;
}
```

### 矩形覆盖

[题目描述](https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：我们可以用 2 * 1 的小矩形横着或者竖着去覆盖更大的矩形。请问用 n 个 2 * 1 的小矩形无重叠地覆盖一个 2 * n 的大矩形，总共有多少种方法？

**解题思路：** 当 n = 1 时，只有竖着覆盖的一种方法；当 n = 2 时，既可以两个小矩形横着放，也可以竖着放，有两种放法；当 n > 2 时，推广一下，用一个小矩形去覆盖大矩形最左边的区域时，有两种方法，第一种竖着放，那么剩下区域的覆盖方法就是 f(n - 1)；第二种横着放，如果横着放，那么另外一块也势必会横着放，这样就覆盖了 2 * 2 个矩形，剩下区域的覆盖方法就是 f(n - 2)，可以得出 f(n) = f(n - 1) + f(n - 2)，发现还是斐波那契数列，这次就用递归的方式简单写一下吧。

```java
public int RectCover(int target) {
    if(target <= 2) return target;
    return RectCover(target - 1) + RectCover(target - 2);
}
```

## 查找和排序

### 旋转数组的最小数字

[题目描述](https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如数组 {3,4,5,1,2} 为 {1,2,3,4,5} 的一个旋转，该数组的最小值为 1 。

**解题思路：** 直观的解法就是遍历一遍数组，找出最小元素，时间复杂度 O(n) ，但是这个思路没有利用旋转数组的特性。旋转之后的数组实际上可以划分为两个排序的子数组，而且前面的子数组的元素都大于或者等于后面子数组的元素，最小数组刚好是两个子数组的分界线，可以使用二分查找的方式，时间复杂度 O(logn)。

```java
public int minNumberInRotateArray(int [] array) {
    if(array.length == 0) return 0;
    int start = 0,end = array.length-1;
    int middle = start;//如果讲前 0 个元素旋转到后面，最小元素的下标就是 start
    while(array[start] >= array[end]){
        if(end - start == 1){
            middle = end;
            break;
        }
            
        middle = (start + end) / 2;
        //如果 start、end 与 middle 对应的元素相等，只能顺序查找
        if(array[start] == array[middle] && array[end] == array[middle]){
            return MinInOrder(array,start,end);
        }
            
        if(array[middle] >= array[start]){
            start = middle;
        }else if(array[middle] <= array[end]){
            end = middle;
        }
    }
    return array[middle];
}
    
 public int MinInOrder(int[] array,int start,int end){
    int min = array[start];
    for(int i = start;i <= end;i++){
        if(array[i] < min) min = array[i];
    }
    return min;
}
```

## 回溯法

### 矩阵中的路径

[题目描述](https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&tqId=11218&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。

**解题思路：** 回溯法的典型题，在矩阵中任选一格格子作为路径的起点。递归判断上下左右是否为下一个字符，直到找到这条路径或找完整个矩阵。

```java
public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
    boolean[] isVisited = new boolean[matrix.length];
    if(matrix == null || str == null || str.length <= 0 || rows < 1 || cols < 1) return false;
        
    for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols; j++){
            if(hasThePath(matrix,rows,cols,i,j,str,0,isVisited)) return true;
        }
    }
    return false;
}
public static boolean hasThePath(char[] matrix, int rows, int cols, int x, int y, char[] str, int index, boolean[] isVisited){
    if(x >= rows || x < 0 || y >= cols || y < 0 || isVisited[x * cols + y]) return false;
    if(str[index] == matrix[x * cols + y]){
        if(index == str.length - 1) return true;
        isVisited[x * cols + y] = true;
        boolean hasPath =  hasThePath(matrix,rows,cols,x,y + 1,str,index + 1,isVisited) ||
            hasThePath(matrix,rows,cols,x,y - 1,str,index + 1,isVisited) ||
            hasThePath(matrix,rows,cols,x + 1,y,str,index + 1,isVisited) ||
            hasThePath(matrix,rows,cols,x - 1,y,str,index + 1,isVisited);
        if(!hasPath){
            isVisited[x * cols + y] = false;
        }
        return hasPath;
    }
    return false;
}
```

### 机器人的运动范围

[题目描述](https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8?tpId=13&tqId=11219&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：地上有一个 m 行和 n 列的方格。一个机器人从坐标（0,0）的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。 例如，当 k 为 18 时，机器人能够进入方格（35,37），因为 3+5+3+7 = 18 。但是，它不能进入方格（35,38），因为 3+5+3+8 = 19 。请问该机器人能够达到多少个格子？

**解题思路：** 和上一题类似，回溯法的灵活应用。

```java
public int movingCount(int threshold, int rows, int cols){
        
    if(threshold < 0 || rows <= 0 || cols <= 0) return 0;
        
    boolean[][] isVisited = new boolean[rows][cols];
    int count = move(threshold, rows, cols, 0, 0, isVisited);
    return count;
}
    
public int move(int threshold, int rows, int cols, int i, int j, boolean[][] isVisited){
        
    if(i < 0 || j < 0 || i >= rows || j >= cols || isVisited[i][j]) return 0;
    int count = 0;
    int ti = i, tj = j;
    while(ti != 0){
        count += ti % 10;
        ti /= 10;
    }
        
    while(tj != 0){
        count += tj % 10;
        tj /= 10;
    }
    if(count > threshold) return 0;
    isVisited[i][j] = true;
    return 1 + move(threshold, rows, cols, i + 1, j, isVisited)
             + move(threshold, rows, cols, i - 1, j, isVisited)
             + move(threshold, rows, cols, i, j + 1, isVisited)
             + move(threshold, rows, cols, i, j - 1, isVisited);
}
```

## 动态规划与贪婪算法

### 剪绳子

**题目描述：** 把一根长度为 n 的绳子剪成 m 段，使 m 段绳子的长度乘积最大（m > 1）。

**解法一：** 动态规划，子问题的最优解存储在数组 dp 中。

```java
public int maxCutting(int length){
    if(length < 2) return 0;
    if(length == 2)return 1;
    if(length == 3)return 2;
    int[] dp = new int[length + 1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;
    int max = 0;
    for(int i = 4; i <= length; i++){
        max = 0;
        for(int j = 1;j <= i/2; j++){
            int t = dp[j]*dp[i - j];
            if(t > max) max = t;
        }
        dp[i] = max;
    }
    return dp[length];
}
```

**解法二：** 贪婪算法。当 `n >= 5` 时，尽可能多地剪长度为 `3` 的绳子；当剩下的绳子长度为 `4` 时，把绳子剪成两端长度为 `2` 的绳子。

当 `n < 5` 时，无论怎么剪切，乘积 `product <= n`，`n` 为 `4` 时，`product` 最大为 `2 * 2 = 4`。

当 `n >= 5` 时，可以证明 `2(n-2)>n` 并且 `3(n-3)>n` 。而且 `3(n-3)>=2(n-2)` 。所以我们应该尽可能地多剪长度为`3`的绳子段。

```java
public int maxCutting(int length){
        
    if(length < 2) return 0;
    if(length == 2)return 1;
    if(length == 3)return 2;
        
    int timesOf3 = length / 3 ;
    if(length - timesOf3 * 3 == 1) {
        timesOf3 -= 1 ;
    }
        
    int timesOf2 = (length - timesOf3 * 3) / 2 ;
        
    return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2)) ;
}
```

## 位运算

### 二进制中 1 的个数

[题目描述](https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：输入一个整数，输出该数二进制表示中 1 的个数。其中负数用补码表示。

**解题思路：** 如果一个整数不为 0 ，那么这个整数至少有一位是 1 。如果我们把这个整数减 1 ，那么原来处在整数最右边的 1 就会变为 0 ，原来在1后面的所有的 0 都会变成 1 (如果最右边的 1 后面还有 0 的话)。其余所有位将不会受到影响。

举个例子：一个二进制数 1100 ，从右边数起第三位是处于最右边的一个 1 。减去 1 后，第三位变成 0 ，它后面的两位 0 变成了 1 ，而前面的 1 保持不变，因此得到的结果是 1011 。我们发现减 1 的结果是把最右边的一个1开始的所有位都取反了。

这个时候如果我们再把原来的整数和减去 1 之后的结果做与运算，从原来整数最右边一个 1 那一位开始所有位都会变成 0 。如 1100 & 1011=1000 。也就是说，把一个整数减去 1 ，再和原整数做与运算，会把该整数最右边一个 1 变成 0 。那么一个整数的二进制有多少个 1 ，就可以进行多少次这样的操作。

```java
public int NumberOf1(int n) {
    int count = 0;
    while(n!= 0){
        count++;
        n = n & (n - 1);
    }
    return count;
}
```

# 高质量代码

## 代码的完整性

### 数值的整数次方

[题目描述](https://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00?tpId=13&tqId=11165&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)：给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。不得使用库函数，同时不需要考虑大数问题。

**解题思路：** 假设输入的指数为 32 ，普通的方法需要在循环中进行 31 次乘法，可以换一种思路：我们的目标是求出一个数字的 32 次方，如果知道了它的 16 次方，那么只要在 16 次方的基础上再平方一次就可以了，同理，也可以推导出输出指数为奇数时的算法，可以用下面的公式求解：

<div align="center">  <img src="./pic/offer12.gif" width="30%"/> </div><br>

```java
public double Power(double base, int exponent) {
    if(exponent == 0) return 1;
    if(exponent == 1) return base;
        
    boolean isN = false;
    if(exponent < 0){
        isN = true;
        exponent = -exponent;
    }
        
    double result = Power(base*base, exponent / 2);
    if(exponent % 2 == 1) result *= base;
        
    return isN ? 1/result : result;
}
```
### 打印从 1 到最大的 n 位数

**题目描述：** 输入数字 n ,按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3 ，则打印出 1、2/3 一直到最大的 3 位数 999 。

**解题思路：** 没有给出 n 的范围，这时就要考虑当 n 很大时需要怎么处理了。可以使用字符串模拟数字的加法，但是写法比较复杂，更优的解法是将问题转换成数字排列的解法，递归进行打印。

```java
public void print1ToMaxOfNDigits(int n) {
    if (n <= 0) return;
    char[] number = new char[n];
    print1ToMaxOfNDigits(number, 0);
}

private void print1ToMaxOfNDigits(char[] number, int digit) {
    if (digit == number.length) {
        printNumber(number);
        return;
    }
    for (int i = 0; i < 10; i++) {
        number[digit] = (char) (i + '0');
        print1ToMaxOfNDigits(number, digit + 1);
    }
}

private void printNumber(char[] number) {
    int index = 0;
    while (index < number.length && number[index] == '0')
        index++;
    while (index < number.length)
        System.out.print(number[index++]);
    System.out.println();
}
```
### 删除链表的节点

#### 在 O(1) 时间内删除链表节点

**题目描述：** 给定单向链表的头指针和一个节点指针，定义一个函数在 O(1) 时间内删除该节点。

**解题思路：** 正常的思路，如果想删除单链表中的一个节点，肯定是要找到这一节点的前一个节点，但是这样遍历寻找复杂度就是 O(n) 了。
换一种思维方式，可以将待删除节点下一个节点的值赋到待删除节点上，然后将待删除节点的下一个节点删除（前提是待删除节点不是链表中的最后一个节点），如果待删除节点是链表的最后一个节点，还是用遍历寻找前一个节点的方式，最后的时间复杂度就是 O(1) 。
需要注意，这种方法的前提是待删除节点一定在链表中，如果要判断的话，还是需要遍历寻找一遍。

```java
public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
    if (head == null || tobeDelete == null) return null;
    if (tobeDelete.next != null) {
        // 要删除的节点不是尾节点
        ListNode next = tobeDelete.next;
        tobeDelete.val = next.val;
        tobeDelete.next = next.next;
    } else {
        ListNode cur = head;
        while (cur.next != tobeDelete)
            cur = cur.next;
        cur.next = null;
    }
    return head;
}
```

#### 删除链表中重复的节点

[题目描述](https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。

**解法一：** 递归方法。

```java
public ListNode deleteDuplication(ListNode pHead){
    
    if(pHead == null || pHead.next == null) return pHead;
    ListNode pNext = pHead.next;
    
    if(pHead.val == pNext.val){
        while(pNext.next != null && pHead.val == pNext.next.val){
            pNext = pNext.next;
        }
        return deleteDuplication(pNext.next);
    }else{
        pHead.next = deleteDuplication(pHead.next);
        return pHead;
    }   
}
```

**解法二：** 非递归方法。

```java
public ListNode deleteDuplication(ListNode pHead){

    ListNode first = new ListNode(-1);
    first.next = pHead;
 
    ListNode p = pHead;
    ListNode last = first;
    while (p != null && p.next != null) {
        if (p.val == p.next.val) {
            int val = p.val;
            while (p!= null && p.val == val)
                p = p.next;
            last.next = p;
        } else {
            last = p;
            p = p.next;
        }
    }
    return first.next;
}
```

### 正则表达式匹配

[题目描述](https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c?tpId=13&tqId=11205&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：请实现一个函数用来匹配包括 `.` 和 `*` 的正则表达式。模式中的字符 `.` 表示任意一个字符，而 `*` 表示它前面的字符可以出现任意次（包含 0 次）。 
在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串 `aaa` 与模式 `a.a` 和`ab*ac*a` 匹配，但是与 `aa.a` 和 `ab*a` 均不匹配。

**解题思路：** 这种题目就是要将所有的情况都考虑清楚，可以结合递归思想简化思路。

```java
public boolean match(char[] str, char[] pattern){

    if(str == null || pattern == null) return false;
        
    return matchCore(str, 0, pattern, 0);
}
    
public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex){
        
    if (strIndex == str.length && patternIndex == pattern.length) {
        return true;
    }
    if (strIndex != str.length && patternIndex == pattern.length) {
        return false;
    }
        
    if(patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*'){
        if(strIndex != str.length && str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.' && strIndex != str.length){
            return matchCore(str, strIndex + 1, pattern, patternIndex + 2)
                || matchCore(str, strIndex + 1, pattern, patternIndex)
                || matchCore(str, strIndex, pattern, patternIndex + 2);
        }else{
            return matchCore(str, strIndex, pattern, patternIndex + 2);
        }
    }
        
    if((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)){
        return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
    }
        
    return false;
}
```

### 表示数值的字符串

[题目描述](https://www.nowcoder.com/practice/6f8c901d091949a5837e24bb82a731f2?tpId=13&tqId=11206&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串 `+100`、`5e2`、`-123`、`3.1416` 和 `-1E-16` 都表示数值。 但是 `12e`、`1a3.14`、`1.2.3`、`+-5` 和 `12e+4.3` 都不是。

**解题思路：** 正则表达式。[具体规则](https://blog.csdn.net/qq_27124771/article/details/85097231)

```java
public boolean isNumeric(char[] str) {
    if (str == null || str.length == 0)
        return false;
    return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
}
```

### 调整数组顺序使奇数位于偶数前面

[题目描述](https://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593?tpId=13&tqId=11166&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

**解题思路：** 空间换时间，从新开辟数组，记录所有偶数元素，将原数组中的奇数放到前面，之后依次放入偶数。


```java
public static void reOrderArray(int[] array) {
    int oddCount = 0;
    int evenCount = 0;
    int[] evenArray = new int[array.length];

    for(int i = 0;i<array.length;i++){
        if((array[i] & 1) == 0) evenArray[evenCount++]=array[i];
        else array[oddCount++]=array[i];
    }

    for(int i = 0;i<evenCount;i++){
        array[oddCount++]=evenArray[i];
    }
}
```
## 代码的鲁棒性

### 链表中倒数第 k 个结点

[题目描述](https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a?tpId=13&tqId=11167&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：输入一个链表，输出该链表中倒数第k个结点。

**解题思路：** 用两个指针 pre , last 指向头结点，先让 pre 走到第 k 个结点，然后两个指针一起往前走，当指针 pre 走到最后时， last 指向的就是倒数第 k 个结点。

注意考虑问题要全面：
* k > 链表长度
* 链表为空
* k <= 0

```java
public ListNode FindKthToTail(ListNode head,int k) {
    if(head == null || k <= 0) return null;
        
    ListNode pre = head, last = head;
        
    for(int i = 1; i < k; i++){
        if(pre.next == null) return null;
        pre = pre.next;
    }
    while(pre.next != null){
        pre = pre.next;
        last = last.next;
    }
    return last;
}
```

### 链表中环的入口结点

[题目描述](https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出 null 。

**解题思路：** 可以直接暴力求解，依次将链表中的结点放入一个 List 中，第一个重复的结点就是入口结点，下面给出两种比较巧妙的方法，思想值得学习。

**解法一：** 双指针。上一题「链表中倒数第 k 个结点」中，利用双指针的思想进行求解，此题同样可以借用这一思路。假设链表长度为 N ，第 N 个结点连接到第 k 个结点形成了环，这样链表中环的入口结点就转化成了倒数第 N-k+1 个结点，N-k+1 即为环中结点的数量，求出环中结点的数量就能计算出入口结点，怎么计算环中结点数量呢？

使用快慢指针，慢指针一次走一步，快指针依次走两步，如果走的过程中快指针追上了慢指针，说明链表中有环（因为在两个指针都进入环的时候，一个步长为 1，一个步长为 2，两个指针之间的距离每走一步就缩小 1 个单位，所以两个指针肯定会再次相遇），并且相遇的位置一定在环内，从相遇结点触发再回到相遇结点的距离就是环的长度。

```java
public ListNode EntryNodeOfLoop(ListNode pHead){
    
    // 寻找相遇结点
    ListNode meetingNode = MeetingNode(pHead);
    
    if(meetingNode == null) return null;
    
    // 计算环中的结点数
    int nodesInLoop = 1;
    ListNode nowNode = meetingNode;
    while(nowNode.next != meetingNode){
        nowNode = nowNode.next;
        nodesInLoop++;
    }
    
    // 快指针前移
    ListNode slow = pHead, fast = pHead;
    while(nodesInLoop > 0){
        fast = fast.next;
        nodesInLoop--;
    }
    
    while(fast != slow){
        slow = slow.next;
        fast = fast.next;
    }
    
    return slow;
    
}

// 寻找相遇节点
public ListNode MeetingNode(ListNode pHead){
    
    if(pHead == null) return null;
    
    ListNode slow = pHead.next;
    if(slow == null) return null;
    
    ListNode fast = slow.next;
    
    while(fast != null && slow != null){
        if(fast == slow) return fast;
        slow = slow.next;
        fast = fast.next;
        if(fast != null) fast = fast.next;
    }
    
    return null;
}
```
**解法二：** 数学推导。此方法解释来自[牛客网大佬题解](https://www.nowcoder.com/questionTerminal/253d2c59ec3e4bc68da16833f79a38e4)。
<div align="center">  <img src="./pic/OfferCode_23.png" width="50%"/> </div><br>

假设 x 为环前面的路程（黑色路程），a 为环入口到相遇点的路程（蓝色路程，假设顺时针走），c 为环的长度（蓝色+橙色路程）。

当快慢指针相遇的时候：

慢指针走过的路程为 `S_slow = x + m * c + a` ；
快指针走过的路程为 `S_fast = x + n * c + a` ；

`2 * S_slow = S_fast`
`2 * (x + m * c + a) = (x + n * c + a)`

从而可以推导出：
`x = (n - 2 * m) * c - a = (n - 2 * m - 1) * c + c - a`

即`环前面的路程 = 数个环的长度（可能为 0 ） + c - a`

什么是 `c - a` ? 这是相遇后，环后面部分的路程（橙色路程）。

所以，可以让一个指针从起点 A 开始走，让一个指针从相遇点 B 开始继续往后走，两个指针速度一样，那么，当从原点的指针走到环入口点的时候，从相遇点开始走的那个指针也一定刚好到达环入口点。

```java
public ListNode EntryNodeOfLoop(ListNode pHead){
 
    if(pHead==null || pHead.next==null || pHead.next.next==null)return null;

    ListNode fast = pHead.next.next;
    ListNode slow = pHead.next;

    //先判断有没有环
    while(fast!=slow){
        if(fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }else{
            return null;
        }
    }

    //循环出来的话就是有环，且此时fast==slow.
    fast=pHead;
    while(fast!=slow){
        fast=fast.next;
        slow=slow.next;
    }
    return slow;
}
```


### 反转链表

[题目描述](https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：输入一个链表，反转链表后，输出新链表的表头。

**解题思路：** 有递归和非递归两种解法，注意断链问题。

**解法一：** 非递归

```java
public ListNode ReverseList(ListNode head) {
    if(head == null) return null;
    ListNode newHead = null;
    ListNode pNode = head;
    ListNode pPrev = null;
        
    while(pNode != null){
        ListNode pNext = pNode.next;
        if(pNext == null) {
            newHead = pNode;
        }
        pNode.next = pPrev;
        pPrev = pNode;
        pNode = pNext;
    }
    return newHead;
}
```

**解法二：** 递归

```java
public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode node = ReverseList(head.next);
        
        head.next.next = head;
        head.next = null;

        return node;
    }
```

### 合并两个排序的链表

[题目描述](https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=11169&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是递增排序的。

**解题思路：** 有递归和非递归两种方法。

**解法一：** 递归。

```java
public ListNode Merge(ListNode list1, ListNode list2) {
    if (list1 == null)
        return list2;
    if (list2 == null)
        return list1;
    if (list1.val <= list2.val) {
        list1.next = Merge(list1.next, list2);
        return list1;
    } else {
        list2.next = Merge(list1, list2.next);
        return list2;
    }
}
```

**解法二：** 非递归。

```java
public ListNode Merge(ListNode list1,ListNode list2) {
        
    ListNode ans = new ListNode(-1);
    ListNode curr = ans;
    while(list1 != null && list2 != null){
        if(list1.val > list2.val){
            curr.next = list2;
            list2 = list2.next;
        }else{
            curr.next = list1;
            list1 = list1.next;
        }
        curr = curr.next;
    }

    if (list1 != null)
        curr.next = list1;
    if (list2 != null)
        curr.next = list2; 

    return ans.next;
}
```

### 树的子结构

[题目描述](https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)：输入两棵二叉树 A 和 B ，判断 B 是不是 A 的子结构。

**解题思路：** 遍历二叉树，找到 A 中所有与 B 头结点相同的子树，再判断该子树是否与 B 结构相同。这道题竟然没一遍 AC ，真是不应该，明天再刷一遍。

```java
public boolean HasSubtree(TreeNode root1, TreeNode root2) {
    if (root1 == null || root2 == null)
        return false;
    return isSubtreeWithRoot(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
}

public boolean isSubtreeWithRoot(TreeNode root1, TreeNode root2) {
    if (root2 == null)
        return true;
    if (root1 == null)
        return false;
    if (root1.val != root2.val)
        return false;
    return isSubtreeWithRoot(root1.left, root2.left) && isSubtreeWithRoot(root1.right, root2.right);
}
```