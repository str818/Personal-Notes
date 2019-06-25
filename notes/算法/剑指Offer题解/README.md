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

# 43. 从 1 到 n 整数中 1 出现的次数

[Online Programming Link](https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tqId=11184&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

解题思路：找规律，从最高位计算到最低位。

```java
public int NumberOf1Between1AndN_Solution(int n) {
    int cnt = 0;
    for (int m = 1; m <= n; m *= 10) {
        int a = n / m, b = n % m;
        cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
    }
    return cnt;
}
```

# 45. 把数组排成最小

[Online Programming Link](https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993?tpId=13&tqId=11185&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

解题思路：可以看成是一个排序问题，在比较两个字符串 S1 和 S2 的大小时，应该比较的是 S1+S2 和 S2+S1 的大小，如果 S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。

```java
public String PrintMinNumber(int[] numbers) {
    if (numbers == null || numbers.length == 0)
        return "";
    int n = numbers.length;
    String[] nums = new String[n];
    for (int i = 0; i < n; i++)
        nums[i] = numbers[i] + "";
    Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
    String ret = "";
    for (String str : nums)
        ret += str;
    return ret;
}
```

# 46. 把数字翻译成字符串

[Online Programming Link](https://leetcode.com/problems/decode-ways/)

题目描述：给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。实现一个函数，用来计算一个数字有多少种不同的翻译方法。

```java
public int numDecodings(String s) {
    if (s == null || s.length() == 0)
        return 0;
    int n = s.length();
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) == '0' ? 0 : 1;
    for (int i = 2; i <= n; i++) {
        int one = Integer.valueOf(s.substring(i - 1, i));
        if (one != 0)
            dp[i] += dp[i - 1];
        if (s.charAt(i - 2) == '0')
            continue;
        int two = Integer.valueOf(s.substring(i - 2, i));
        if (two <= 26)
            dp[i] += dp[i - 2];
    }
    return dp[n];
}
```

# 47. 礼物的最大价值

[Online Programming Link](https://www.nowcoder.com/questionTerminal/72a99e28381a407991f2c96d8cb238ab)

题目描述：在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。

```java
public int getMost(int[][] values) {
    if (values == null || values.length == 0 || values[0].length == 0)
        return 0;
    int n = values[0].length;
    int[] dp = new int[n];
    for (int[] value : values) {
        dp[0] += value[0];
        for (int i = 1; i < n; i++)
            dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
    }
    return dp[n - 1];
}
```

# 49. 丑数

[Online Programming Link](https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

```java
public int GetUglyNumber_Solution(int N) {
    if (N <= 6)
    return N;
    int i2 = 0, i3 = 0, i5 = 0;
    int[] dp = new int[N];
    dp[0] = 1;
    for (int i = 1; i < N; i++) {
        int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
        dp[i] = Math.min(next2, Math.min(next3, next5));
        if (dp[i] == next2)
            i2++;
        if (dp[i] == next3)
            i3++;
        if (dp[i] == next5)
            i5++;
    }
    return dp[N - 1];
}
```

# 50. 第一个只出现一次的字符位置

[Online Programming Link](https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c?tpId=13&tqId=11187&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

```java
public int FirstNotRepeatingChar(String str) {
    int[] cnts = new int[256];
    for (int i = 0; i < str.length(); i++) {
        cnts[str.charAt(i)]++;
    }
    for (int i = 0; i < str.length(); i++) {
        if (cnts[str.charAt(i)] == 1)
            return i;
    }
    return -1;
}
```

# 51. 数组中的逆序对

[Online Programming Link](https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

```java
private long cnt;
private int[] tmp;
public int InversePairs(int[] nums) {
    tmp = new int[nums.length];
    mergeSort(nums, 0, nums.length - 1);
    return (int)(cnt % 1000000007);
}

public void mergeSort(int[] nums, int l, int h) {
    if (h <= l) return;
    int m = l + (h - l) / 2;
    mergeSort(nums, l, m);
    mergeSort(nums, m + 1, h);
    merge(nums, l, m, h);
}

public void merge(int[] nums, int l, int m, int h) {
    int i = l, j = m + 1, k = l;
    while (i <= m || j <= h) {
        if (i > m) {
            tmp[k++] = nums[j++];
        } else if (j > h) {
            tmp[k++] = nums[i++];
        } else if (nums[i] <= nums[j]) {
            tmp[k++] = nums[i++];
        } else {
            tmp[k++] = nums[j++];
            cnt += m - i + 1;
        }
    }
    for (k = l; k <= h; k++)
        nums[k] = tmp[k];
}
```

# 52. 两个链表的第一个公共结点

[Online Programming Link](https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

解题思路：设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。

```java
public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    ListNode p1 = pHead1, p2 = pHead2;
    while (p1 != p2) {
        p1 = p1 == null ? pHead2 : p1.next;
        p2 = p2 == null ? pHead1 : p2.next;
    }
    return p1;
}
```

# 53. 数字在排序数组中出现的次数

[Online Programming Link](https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13&tqId=11190&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

```java
public int GetNumberOfK(int [] nums , int k) {
    int first = binarySearch(nums, k);
    int second = binarySearch(nums, k + 1);
    return (first == nums.length || nums[first] != k) ? 0 : second - first;
}
private int binarySearch(int[] nums, int k) {
    int l = 0, h = nums.length - 1;
    while (l <= h) {
        int m = l + (h - l) / 2;
        if (nums[m] >= k) {
            h = m - 1;
        } else {
            l = m + 1;
        }
    }
    return l;
}
```

# 54. 二叉查找树的第 K 个结点

[Online Programming Link](https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a?tpId=13&tqId=11215&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

```java
private TreeNode res;
private int cnt = 0;
public TreeNode KthNode(TreeNode pRoot, int k) {
    inOrder(pRoot, k);
    return res;
}
private void inOrder (TreeNode root, int k) {
    if (root == null || cnt >= k)
        return;
    inOrder(root.left, k);
    cnt++;
    if (cnt == k)
        res = root;
    inOrder(root.right, k);
}
```

# 55.1 二叉树的深度

[Online Programming Link](https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b?tpId=13&tqId=11191&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

```java
public int TreeDepth(TreeNode root) {
    return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
}
```

# 55.2 平衡二叉树

[Online Programming Link](https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：判断是否是平衡二叉树，高度差小于一。

```java
private boolean isBalance = true;
public boolean IsBalanced_Solution(TreeNode root) {
    height(root);
    return isBalance;
}
private int height(TreeNode root) {
    if (root == null || !isBalance)
        return 0;
    int left = height(root.left);
    int right = height(root.right);
    if (Math.abs(right - left) > 1)
        isBalance = false;
    return 1 + Math.max(left, right);
}
```

# 56 数组中只出现一次的数字

[Online Programming Link](https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811?tpId=13&tqId=11193&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。

解题思路：用异或元素抵消掉所有出现两次的数字，得到只出现一次的两个数字的异或结果，通过 `diff ^= -diff` 取出从右数第一个不为 0 的位置，按照这个位置是 0 还是 1 划分成两个不同的部分，两个部分分别异或可求出最终结果。

```java
public void FindNumsAppearOnce(int [] nums, int num1[], int num2[]) {
    int diff = 0;
    for (int num : nums)
        diff ^= num;
    diff &= -diff;
    for (int num : nums) {
        if ((num & diff) == 0)
            num1[0] ^= num;
        else
            num2[0] ^= num;
    }
}
```

# 57.1 和为 S 的两个数字

[Online Programming Link](https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：输入一个递增排序的数组和一个数字 S，在数组中查找两个数，使得他们的和正好是 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。

```java
public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
    int i = 0, j = array.length - 1;
    while (i < j) {
        int cur = array[i] + array[j];
        if (cur == sum)
            return new ArrayList<>(Arrays.asList(array[i], array[j]));
        if (cur < sum)
            i++;
        else
            j--;
    }
    return new ArrayList<>();
}
```

# 57.2 和为 S 的连续正数序列

[Online Programming Link](https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：输出所有和为 S 的连续正数序列。

```java
public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    int start = 1, end = 2;
    int curSum = 3;
    while (end < sum) {
        if (curSum > sum) {
            curSum -= start;
            start++;
        } else if (curSum < sum) {
            end++;
            curSum += end;
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = start; i <= end; i++)
                list.add(i);
            ret.add(list);
            curSum -= start;
            start++;
            end++;
            curSum += end;
        }
    }
    return ret;
}
```

# 58.1 翻转单词顺序列

[Online Programming Link](https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=11197&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

```
Input:
"I am a student."

Output:
"student. a am I"
```

解题思路：先旋转每个单词，再旋转整个字符串。

```java
public String ReverseSentence(String str) {
    int n = str.length();
    char[] chars = str.toCharArray();
    int i = 0, j = 0;
    while (j <= n) {
        if (j == n || chars[j] == ' ') {
            reverse(chars, i, j - 1);
            i = j + 1;
        }
        j++;
    }
    reverse(chars, 0, n - 1);
    return new String(chars);
}

private void reverse(char[] c, int i, int j) {
    while (i < j)
        swap(c, i++, j--);
}

private void swap(char[] c, int i, int j) {
    char t = c[i];
    c[i] = c[j];
    c[j] = t;
}
```

# 58.2 左旋转字符串

[Online Programming Link](https://www.nowcoder.com/practice/12d959b108cb42b1ab72cef4d36af5ec?tpId=13&tqId=11196&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

```
Input:
S="abcXYZdef"
K=3

Output:
"XYZdefabc"
```

解题思路：先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。

```java
public String LeftRotateString(String str, int n) {
    if (n >= str.length())
        return str;
    char[] chars = str.toCharArray();
    reverse(chars, 0, n - 1);
    reverse(chars, n, chars.length - 1);
    reverse(chars, 0, chars.length - 1);
    return new String(chars);
}

private void reverse(char[] chars, int i, int j) {
    while (i < j)
        swap(chars, i++, j--);
}

private void swap(char[] chars, int i, int j) {
    char t = chars[i];
    chars[i] = chars[j];
    chars[j] = t;
}
```

# 59. 滑动窗口的最大值

[Online Programming Link](https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。

```java
public ArrayList<Integer> maxInWindows(int[] num, int size) {
    ArrayList<Integer> ret = new ArrayList<>();
    if (size > num.length || size < 1)
        return ret;
    PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);  /* 大顶堆 */
    for (int i = 0; i < size; i++)
        heap.add(num[i]);
    ret.add(heap.peek());
    for (int i = 0, j = i + size; j < num.length; i++, j++) {            /* 维护一个大小为 size 的大顶堆 */
        heap.remove(num[i]);
        heap.add(num[j]);
        ret.add(heap.peek());
    }
    return ret;
}
```

# 60. n 个骰子的点数

[Online Programming Link](<https://www.lintcode.com/problem/dices-sum/description>)

题目描述：把 n 个骰子仍在地上，求点数和为 s 的概率。

解题思路：定义 `dp[i][j]` 为前 i 个骰子产生点数 j 的次数。

状态转移方程为：`dp[i][j] = dp(i-1,j-1) + dp(i-1,j-2) + dp(i-1,j-3) + dp(i-1,j-4) + dp(i-1,j-5)+dp(c-1,k-6)`。

```java
public List<Map.Entry<Integer, Double>> dicesSum(int n) {
    final int face = 6;
    final int pointNum = face * n;
    long[][] dp = new long[n + 1][pointNum + 1];

    for (int i = 1; i <= face; i++)
        dp[1][i] = 1;

    for (int i = 2; i <= n; i++)
        for (int j = i; j <= pointNum; j++)     /* 使用 i 个骰子最小点数为 i */
            for (int k = 1; k <= face && k <= j; k++)
                dp[i][j] += dp[i - 1][j - k];

    final double totalNum = Math.pow(6, n);
    List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
    for (int i = n; i <= pointNum; i++)
        ret.add(new AbstractMap.SimpleEntry<>(i, dp[n][i] / totalNum));

    return ret;
}
```