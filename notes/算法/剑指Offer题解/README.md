# 1. 数组中重复的数字

[Online Programming Link](https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

## I. 找出数组中的重复数字

题目描述：在一个长度为 n 的数组里，所有的数字都在 0 ~ n-1 的范围内。数组中某些数字是重复的，找出数组中任意一个重复的数字。

解题思路：首先可以考虑排序，时间复杂度 O(nlogn)；其次哈希表也能够解决，时间复杂度 O(n)，额外空间复杂度 O(n);最优的解法时间复杂度 O(n)，额外空间复杂度 O(1)。

数组中的数字都在 0 ~ n-1 的范围内，如果数组中没有重复的数字，那么当数组排序后数字 i 将都会出现在下标为 i 的位置。如果有重复数字，就不出现不止一个数字 i 出现在下标为 i 的位置，利用这一原理可以求解。

```java
public boolean duplicate(int[] nums,int length,int []duplication) {
    
    if(nums == null || length <= 0) return false;
    
    for(int i = 0; i < length; i++){
        while(nums[i] != i){
            if(nums[i] == nums[nums[i]]){
                duplication[0] = nums[i];
                return true;
            }
            int t = nums[i];
            nums[i] = nums[t];  // 注意不能写成 nums[nums[i]]
            nums[t] = t;
        }
    }
    return false;
}
```

## II. 不修改数组找出重复的数字

解题思路：可以开辟一个数组，依次将原数组中的元素放入对应的位置，判断是否重复，额外空间复杂度 O(n)；还有一种额外空间复杂度 O(1) 的方法，利用二分的思想，把从 1 ~ n 的数字从中间的数字 m 分为两部分，前面一半为 1 ~ m，后面一半为 m + 1 ~ n。如果 1 ~ m 的数字数目超过 m，那么这一版的区间一定包含重复的数字。

```java
public int getDuplication(int[] nums, int length){
    if(nums == null || length <= 0) return -1;
    int start = 1;
    int end = length - 1;
    while(end >= start){
        int mid = start + ((end - start) >> 1);
        int count = countRange(nums, length, start, middle);
        if(end == start){
            if(count > 1) return start;
            else break;
        }
        if(count > (middle - start + 1)){
            end = middle;
        }else{
            start = middle;
        }
    }
}

public int countRange(int[] nums, int length, int start, int end){
    if(nums == null) return 0;
    int count = 0;
    for(int i = 0; i < length; i++){
        if(nums[i] >= start && numbers[i] <= end){
            ++count;
        }
    }
    return count;
}
```

# 2. 二维数组中的查找

[Online Programming Link](https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：一个二维数组的每一行从左到右递增，每一列从上到下递增，判断数组中是否包含一个整数。

解题思路：从右上角开始找。

```java
public boolean Find(int target, int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
        return false;
    int rows = matrix.length, cols = matrix[0].length;
    int r = 0, c = cols - 1;
    while (r <= rows - 1 && c >= 0) {
        if (target == matrix[r][c])
            return true;
        else if (target > matrix[r][c])
            r++;
        else
            c--;
    }
    return false;
}
```

# 3. 替换空格

[Online Programming Link](https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：将字符串中的空格替换成"%20"。

解题思路：如果从前向后替换的话，需要不断将后面的字符后移，所以可以先遍历字符串将需要添加的字符数量扩充到字符串的后面，从后向前依次替换。

```java
public String replaceSpace(StringBuffer str) {
    int p1 = str.length() - 1;
    for(int i = 0; i <= p1; i++){
        if(str.charAt(i) == ' '){
            str.append("  ");
        }
    }
    int p2 = str.length() - 1;
    while(p1 >= 0 && p2 > p1){
        char c = str.charAt(p1--);
        if(c == ' '){
            str.setCharAt(p2--, '0');
            str.setCharAt(p2--, '2');
            str.setCharAt(p2--, '%');
        }else{
            str.setCharAt(p2--, c);
        }
    }
    return str.toString();
}
```

# 4. 从尾到头打印链表

[Online Programming Link](https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

解法一：递归

```java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    ArrayList<Integer> list = new ArrayList<>();
    if (listNode != null) {
        list.addAll(printListFromTailToHead(listNode.next));
        list.add(listNode.val);
    }
    return list;
}
```

解法二：栈。

```java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    Stack<Integer> stack = new Stack<>();
    while (listNode != null) {
        stack.add(listNode.val);
        listNode = listNode.next;
    }
    ArrayList<Integer> list = new ArrayList<>();
    while (!stack.isEmpty())
        list.add(stack.pop());
    return list;
}
```

# 5. 重建二叉树

[Online Programming Link](https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：给定二叉树的先序与中序遍历，重建二叉树。

解题思路：二叉树先序遍历的第一个结点是根节点，找到其在中序遍历里的位置，二分。

```java
public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    return reConstructBinaryTree(pre,0,in,0,in.length);
}

public TreeNode reConstructBinaryTree(int[] pre, int preIndex, int[] in, int start, int end){
    if(preIndex >= pre.length || start == end) return null;
    
    TreeNode root = new TreeNode(pre[preIndex]);
    
    for(int i = start; i < end; i++){
        if(pre[preIndex] == in[i]){
            root.left = reConstructBinaryTree(pre, preIndex + 1, in, start, i);
            root.right = reConstructBinaryTree(pre, preIndex + i - start + 1, in, i + 1, end);
        }
    }
    
    return root;
}
```


# 36. 二叉树与双向链表

[Online Programming Link](https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&tqId=11179&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

解题思路：中序遍历。

```java
private TreeNode pre = null;
private TreeNode head = null;
public TreeNode Convert(TreeNode pRootOfTree) {
    inOrder(pRootOfTree);
    return head;
}
public void inOrder(TreeNode node) {
    if (node == null) 
        return;
    inOrder(node.left);
    node.left = pre;
    if (pre != null) {
        pre.right = node;
    }
    pre = node;
    if (head == null) {
        head = node;
    }
    inOrder(node.right);
}
```
 
# 37. 序列化二叉树

[Online Programming Link](https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=13&tqId=11214&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：请实现两个函数，分别用来序列化和反序列化二叉树。

解题思路：前序遍历。

```java
private String deserializeStr;

public String Serialize(TreeNode root) {
    if (root == null)
        return "#";
    return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
}

public TreeNode Deserialize(String str) {
    deserializeStr = str;
    return Deserialize();
}

private TreeNode Deserialize() {
    if (deserializeStr.length() == 0)
        return null;
    int index = deserializeStr.indexOf(" ");
    String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
    deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
    if (node.equals("#"))
        return null;
    int val = Integer.valueOf(node);
    TreeNode t = new TreeNode(val);
    t.left = Deserialize();
    t.right = Deserialize();
    return t;
}
```

# 38. 字符串的排列

[Online Programming Link](https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&tqId=11180&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串 abc,则打印出由字符 a,b,c 所能排列出来的所有字符串 abc,acb,bac,bca,cab 和 cba。输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。

解题思路：注意可能有重复。

```java
private ArrayList<String> res = new ArrayList<>();
public ArrayList<String> Permutation(String str) {
    if (str.length() == 0) 
        return res;
    char[] c = str.toCharArray();
    Arrays.sort(c);
    backtracking(c, new boolean[c.length], new StringBuilder());
    return res;
}

public void backtracking(char[] c, boolean[] hasUsed, StringBuilder s) {
    if (s.length() == c.length) {
        res.add(s.toString());
        return;
    }
    for (int i = 0; i < c.length; i++) {
        if (hasUsed[i])
            continue;
        if (i != 0 && c[i] == c[i - 1] && !hasUsed[i - 1])
            continue;
        hasUsed[i] = true;
        s.append(c[i]);
        backtracking(c, hasUsed, s);
        s.deleteCharAt(s.length() - 1);
        hasUsed[i] = false;
    }
}
```

# 39. 数组中出现次数超过一半的数字

[Online Programming Link](https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tqId=11181&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

解题思路：将数组中出现次数超过一半的数字与其他数字抵消。

```java
public int MoreThanHalfNum_Solution(int [] array) {
    if (array == null || array.length == 0)
        return 0;
    int major = array[0];
    for (int i = 1, cnt = 1; i < array.length; i++) {
        if (cnt == 0) {
            major = array[i];
            cnt = 1;
        } else {
            cnt = array[i] == major ? cnt + 1 : cnt - 1;
        }
    }
    
    int cnt = 0;
    for (int x : array) {
        if (x == major)
            cnt++;
    }
    return cnt > array.length / 2 ? major : 0;
}
```

# 40. 最小的 K 个数

[Online Programming Link](https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=13&tqId=11182&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

解法一：快速选择。

```java
public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    ArrayList<Integer> res = new ArrayList<>();
    if (k > input.length || k <= 0)
        return res;
    findKthSamllest(input, k - 1);
    for (int i = 0; i < k; i++) {
        res.add(input[i]);
    }
    return res;
}
public void findKthSamllest(int[] nums, int k) {
    int l = 0, h = nums.length - 1;
    while (l < h) {
        int j = partition(nums, l, h);
        if (j == k)
            break;
        if (j > k)
            h = j - 1;
        else
            l = j + 1;
    }
}
private int partition(int[] nums, int l, int h) {
    int p = nums[l];
    int i = l, j = h + 1;
    while (true) {
        while (i != h && nums[++i] < p);
        while (j != l && nums[--j] > p);
        if (i >= j)
            break;
        swap(nums, i, j);
    }
    swap(nums, l, j);
    return j;
}
private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```

解法二：最大堆。

```java
public ArrayList<Integer> GetLeastNumbers_Solution(int [] nums, int k) {
    if (k > nums.length || k < 0)
        return new ArrayList<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    for (int num : nums) {
        maxHeap.add(num);
        if (maxHeap.size() > k)
            maxHeap.poll();
    }
    return new ArrayList<>(maxHeap);
}
```

# 41.1 数据流中的中位数

[Online Programming Link](https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tqId=11216&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

解题思路：大顶推存储左面的元素，小顶堆存储右面的元素。

```java
private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
private PriorityQueue<Integer> right = new PriorityQueue<>();
private int N = 0;
public void Insert(Integer num) {
    if (N % 2 == 0) {
        left.add(num);
        right.add(left.poll());
    } else {
        right.add(num);
        left.add(right.poll());
    }
    N++;
}
public Double GetMedian() {
    if (N % 2 == 0)
        return (left.peek() + right.peek()) / 2.0;
    else
        return (double) right.peek();
}
```

# 41.2 字符流中第一个不重复的字符

[Online Programming Link](https://www.nowcoder.com/practice/00de97733b8e4f97a3fb5c680ee10720?tpId=13&tqId=11207&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。

```java
private int[] cnts = new int[256];
private Queue<Character> queue = new LinkedList<>();
public void Insert(char ch) {
    cnts[ch]++;
    queue.offer(ch);
    while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
        queue.poll();
    }
}
public char FirstAppearingOnce() {
    return queue.isEmpty() ? '#' : queue.peek();
}
```

# 42. 连续自数字的最大和

[Online Programming Link](https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484?tpId=13&tqId=11183&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：{6, -3, -2, 7, -15, 1, 2, 2}，连续子数组的最大和为 8（从第 0 个开始，到第 3 个为止）。

解题思路：贪心，舍弃负数。

```java
public int FindGreatestSumOfSubArray(int[] nums) {
    if (nums == null || nums.length == 0)
        return 0;
    int max = Integer.MIN_VALUE;
    int sum = 0;
    for (int num : nums) {
        sum = sum <= 0 ? num : sum + num;
        max = Math.max(max, sum);
    }
    return max;
}
```