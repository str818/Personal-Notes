# 两数之和

[Leetcode - 1 Two Sum (Easy)](https://leetcode.com/problems/two-sum/)

题目描述：给定一个数组，返回相加和为 target 的两个元素的下标。

```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int value = target - nums[i];
        if (map.containsKey(value)) {
            return new int[]{map.get(value), i};
        }
        map.put(nums[i], i);
    }
    return null;
}
```

# 两数相加

[Leetcode - 2 Add Two Numbers (Medium)](https://leetcode.com/problems/add-two-numbers/)

题目描述：使用链表表示两个正整数，返回两正整数相加结果，使用链表表示。

```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
```

# 无重复字符的最长子串

[Leetcode - 3 Longest Substring Without Repeating Characters (Medium)](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

```
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
```

解题思路：使用 HashMap 记录出现字符的位置，利用双指针滑动窗口记录子串的长度。

```java
public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) return 0;
    Map<Character, Integer> map = new HashMap<>();
    int max = 0;
    for (int i = 0, j = 0; i < s.length(); i++) {
        if (map.containsKey(s.charAt(i))) {
            j = Math.max(j, map.get(s.charAt(i)) + 1);
        }
        map.put(s.charAt(i), i);
        max = Math.max(max, i - j + 1);
    }
    return max;
}
```

# 寻找两个有序数组的中位数

[Leetcode - 4 Median of Two Sorted Arrays (Hard)](https://leetcode.com/problems/median-of-two-sorted-arrays/)

题目描述：要求时间复杂度为 O(log(m + n))。

```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```

解题思路：二分。

```
      left_part          |        right_part
A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
```

```java
public double findMedianSortedArrays(int[] A, int[] B) {
    int m = A.length, n = B.length;
    if (m > n) { // 确保 n >= m
        int[] temp = A; A = B; B = temp;
        int tmp = m; m = n; n = tmp;
    }
    int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
        int i = (iMin + iMax) / 2;
        int j = halfLen - i;
        if (i < iMax && B[j - 1] > A[i]) {
            iMin = i + 1;
        } else if (i > iMin && A[i - 1]  > B[j]) {
            iMax = i - 1;
        } else {
            int maxLeft = 0;
            if (i == 0) maxLeft = B[j - 1];
            else if (j == 0) maxLeft = A[i - 1];
            else maxLeft = Math.max(A[i - 1], B[j - 1]);
            if ((m + n) % 2 == 1) return maxLeft;
            
            int minRight = 0;
            if (i == m) minRight = B[j];
            else if (j == n) minRight = A[i];
            else minRight = Math.min(B[j], A[i]);
            
            return (maxLeft + minRight) / 2.0;
        }
    }
    return 0;
}
```

# 最长回文子串

[Leetcode - 5 Longest Palindromic Substring (Medium)](https://leetcode.com/problems/longest-palindromic-substring/)

```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

```java
private int lo, maxLen;
public String longestPalindrome(String s) {
    int len = s.length();
    if (len < 2) return s;
    for (int i = 0; i < len-1; i++) {
        extendPalindrome(s, i, i);
        extendPalindrome(s, i, i+1);
    }
    return s.substring(lo, lo + maxLen);
}
private void extendPalindrome(String s, int j, int k) {
    while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
        j--;
        k++;
    }
    if (maxLen < k - j - 1) {
        lo = j + 1;
        maxLen = k - j - 1;
    }
}
```

# 整数反转

[Leetcode - 7 Reverse Integer (Easy)](https://leetcode.com/problems/reverse-integer/)

题目描述：反转一个 32 位有符号的整数。

```
Input: -123
Output: -321
```

解题思路：注意整型范围越界。

```java
public int reverse(int x) {
    int result = 0;
    while (x != 0) {
        int tail = x % 10;
        int newResult = result * 10 + tail;
        if ((newResult - tail) / 10 != result) {
            return 0;
        }
        result = newResult;
        x /= 10;
    }
    return result;
}
```

# 正则表达式匹配

[Leetcode - 10 Regular Expression Matching (Hard)](https://leetcode.com/problems/regular-expression-matching/)

题目描述：`.` 表示任意字符，`*` 表示它前面的字符可以出现任意次（包含 0 次）。

```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
```

解题思路：动态规划，dp[i][j] 表示字符串 s 0 - i 与字符串 p 0 - j 是否匹配。

```java
public boolean isMatch(String s, String p) {
    if (s == null || p == null) {
        return false;
    }
    boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    dp[0][0] = true;
    for (int i = 0; i < p.length(); i++) {
        if (p.charAt(i) == '*' && dp[0][i-1]) {
            dp[0][i+1] = true;
        }
    }
    for (int i = 0 ; i < s.length(); i++) {
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '.') {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == s.charAt(i)) {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == '*') {
                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                } else {
                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }
    }
    return dp[s.length()][p.length()];
}
```

# 盛最多水的容器

[Leetcode - 11 Container With Most Water (Medium)](https://leetcode.com/problems/container-with-most-water/)

题目描述：找出两条线，使得构成的容器能够容纳最多的水。

<div align="center">  <img src="img/leetcode_11.jpg" width="80%"/> </div><br>

```java
public int maxArea(int[] height) {
    int max = 0, i = 0, j = height.length - 1;
    while (i < j) {
        max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
        if (height[i] < height[j]) {
            i++;
        } else {
            j--;
        }
    }
    return max;
}
```

# 罗马数字转整数

[Leetcode - 13 Roman to Integer (Easy)](https://leetcode.com/problems/roman-to-integer/)

题目描述：

```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

- I can be placed before V (5) and X (10) to make 4 and 9. 
- X can be placed before L (50) and C (100) to make 40 and 90. 
- C can be placed before D (500) and M (1000) to make 400 and 900.

```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

```java
public int romanToInt(String s) {
    int count = 0;
    int prev = 0;
    for (int x = s.length()-1; x >= 0; x--) {
        int cur = 0;
        if (s.charAt(x) == 'M') { cur=1000; }
        if (s.charAt(x) == 'D') { cur=500; }
        if (s.charAt(x) == 'C') { cur=100; }
        if (s.charAt(x) == 'L') { cur=50; }
        if (s.charAt(x) == 'X') { cur=10; }
        if (s.charAt(x) == 'V') { cur=5; }
        if (s.charAt(x) == 'I') { cur=1; }
        if (prev > cur) {
            count -= cur;
        } else {
            count += cur;
        }
        prev = cur;
    }
    return count;
}
```

# 最长公共前缀

[Leetcode - 14 Longest Common Prefix (Easy)](https://leetcode.com/problems/longest-common-prefix/)

```
Input: ["flower","flow","flight"]
Output: "fl"
```

```java
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) return "";
        }
    }
    return prefix;
}
```

# 三数之和

[Leetcode - 15 3Sum (Medium)](https://leetcode.com/problems/3sum/)

题目描述：找出数组中三数和为 0 的组合。

```
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

解题思路：注意去重。

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();
    for(int i = 0; i < nums.length - 2; i++){
        if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
            int l = i + 1, r = nums.length - 1;
            while(l < r){
                if(nums[i] + nums[l] + nums[r] == 0){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while(l < r && nums[l] == nums[l + 1]) l++;
                    while(l < r && nums[r] == nums[r - 1]) r--;
                    l++; r--;
                }else if(nums[i] + nums[l] + nums[r] < 0){
                    l++;
                }else{
                    r--;
                }
            }
        }
    }
    return res;
}
```

# 电话号码的字母组合

[Leetcode - 17 Letter Combinations of a Phone Number (Medium)](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)

题目描述：输出数字键盘 9 宫格可能的所有字母组合。

<div align="center">  <img src="img/leetcode-17.png" width="50%"/> </div><br>

```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

解题思路：用 Map 存储数字对应的字母，回溯遍历所有情况。

```java
Map<String, String> map = new HashMap<String, String>(){{
    put("2", "abc"); put("3", "def"); put("4", "ghi");
    put("5", "jkl"); put("6", "mno"); put("7", "pqrs");
    put("8", "tuv"); put("9", "wxyz");
}};
List<String> res = new ArrayList<>();
public List<String> letterCombinations(String digits) {
    if (digits.length() != 0) {
        helper(digits, "");
    }
    return res;
}
public void helper(String digits, String s) {
    if (s.length() == digits.length()) {
        res.add(s);
    } else {
        String tmp = map.get(digits.charAt(s.length()) + "");
        for (char c : tmp.toCharArray()) {
            helper(digits, s + c);
        }
    }
}
```

# 删除链表的倒数第 n 个节点

[Leetcode - 19 Remove Nth Node From End of List (Medium)](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```

解题思路：可能会删除第 0 个节点，所以要使用尾节点。

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode cur = dummyHead;
    ListNode first = cur;
    while(n-- >= 0){
        first = first.next;
    }
    while(first != null){
        first = first.next;
        cur = cur.next;
    }
    cur.next = cur.next.next;
    return dummyHead.next;
}
```

# 有效的括号

[Leetcode - 20 Valid Parenthese (Easy)](https://leetcode.com/problems/valid-parentheses/)

题目描述：给定字符串，判断括号是否有效。

```
Input: "()[]{}"
Output: true
```

```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (Character c : s.toCharArray()){
        if(c == '('){
            stack.push(')');
        }else if(c == '['){
            stack.push(']');
        }else if(c == '{'){
            stack.push('}');
        }else if(stack.isEmpty() || stack.pop() != c){
            return false;
        }
    }
    return stack.isEmpty();
}
```

# 合并两个有序链表

[Leetcode - 21 Merge Two Sorted Lists (Easy)](https://leetcode.com/problems/merge-two-sorted-lists/)

```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode cur = dummyHead;
    while(l1 != null && l2 != null){
        if(l1.val > l2.val){
            cur.next = l2;
            l2 = l2.next;
        }else{
            cur.next = l1;
            l1 = l1.next;
        }
        cur = cur.next;
    }
    if(l1 != null){
        cur.next = l1;
    }
    if(l2 != null){
        cur.next = l2;
    }
    return dummyHead.next;
}
```

# 生成括号

[Leetcode - 22 Generate Parentheses (Medium)](https://leetcode.com/problems/generate-parentheses/)

题目描述：给定整数 n，生成包含 n 个括号合法字符串的所有组合。

```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```
解题思路：结合合法括号的特性。

```java
public List<String> generateParenthesis(int n) {
    List<String> ansList = new ArrayList<String>();
    backtrack(ansList, "", 0, 0, n);
    return ansList;
}

public void backtrack(List<String> ansList, String curString, int openCount, int closeCount, int max){
    if(curString.length() == 2 * max){
        ansList.add(curString);
        return;
    }
    if(openCount < max){
        backtrack(ansList, curString + '(', openCount + 1, closeCount, max);
    }
    if(closeCount < openCount){
        backtrack(ansList, curString + ')', openCount, closeCount + 1, max);
    }
} 
```

# 合并 k 个有序链表

[Leetcode - 23 Merge k Sorted Lists (Hard)](https://leetcode.com/problems/merge-k-sorted-lists/)

```
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
```

解题思路：这道题类似之前的合并两个有序链表，可以逐个两两合并，也可以每次取出链表头部的最小节点，我也是首先想到这种方法，但是效率有点低。能够通过优先级队列和分治进行优化。

```java
public static ListNode mergeKLists(ListNode[] lists){
    return partion(lists,0,lists.length-1);
}

public static ListNode partion(ListNode[] lists,int s,int e){
    if(s==e)  return lists[s];
    if(s<e){
        int q=(s+e)/2;
        ListNode l1=partion(lists,s,q);
        ListNode l2=partion(lists,q+1,e);
        return merge(l1,l2);
    }else
        return null;
}

// 合并两个链表
public static ListNode merge(ListNode l1,ListNode l2){
    if(l1==null) return l2;
    if(l2==null) return l1;
    if(l1.val<l2.val){
        l1.next=merge(l1.next,l2);
        return l1;
    }else{
        l2.next=merge(l1,l2.next);
        return l2;
    }
}
```

# 删除有序数组中的重复项

[Leetcode - 26 Remove Duplicates from Sorted Array (Easy)](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

```
Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
```

```java
public int removeDuplicates(int[] nums) {
    int index = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i == 0 || nums[i] != nums[i - 1]) {
            nums[index++] = nums[i];
        }
    }
    return index;
}
```

# 实现 strStr()

[Leetcode - 28. Implement strStr() (Easy)](https://leetcode.com/problems/implement-strstr/)

题目描述：返回匹配字符串首字母的下标，不存在返回 -1。

```
Input: haystack = "hello", needle = "ll"
Output: 2
```

解题思路：注意 needle 长度为 0 时返回 0，而不是 -1。

```java
public int strStr(String haystack, String needle) {
    int l1 = haystack.length(), l2 = needle.length();
    if (l1 < l2) {
        return -1;
    } else if (l2 == 0) {
        return 0;
    }
    int threshold = l1 - l2;
    for (int i = 0; i <= threshold; ++i) {
        if (haystack.substring(i,i + l2).equals(needle)) {
            return i;
        }
    }
    return -1;
}
```

# 两数相除

[Leetcode - 29 Divide Two Integers (Medium)](https://leetcode.com/problems/divide-two-integers/)

题目描述：不使用乘法、除法和 mod 运算符。

```
Input: dividend = 10, divisor = 3
Output: 3
```

解题思路：注意整数溢出的情况， 可以利用二分的思想优化求解。

```java
public int divide(int dividend, int divisor) {
    int sign = 1;
    if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
        sign = -1;
    long ldividend = Math.abs((long) dividend);
    long ldivisor = Math.abs((long) divisor);

    if (ldivisor == 0) return Integer.MAX_VALUE;
    if ((ldividend == 0) || (ldividend < ldivisor))	return 0;

    long lans = ldivide(ldividend, ldivisor);

    int ans;
    if (lans > Integer.MAX_VALUE){
        ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
    } else {
        ans = (int) (sign * lans);
    }
    return ans;
}

private long ldivide(long ldividend, long ldivisor) {
    if (ldividend < ldivisor) return 0;
    long sum = ldivisor;
    long multiple = 1;
    while ((sum + sum) <= ldividend) {
        sum += sum;
        multiple += multiple;
    }
    return multiple + ldivide(ldividend - sum, ldivisor);
}
```

# 搜索旋转排序数组

[Leetcode - 33 Search in Rotated Sorted Array (Medium)](https://leetcode.com/problems/search-in-rotated-sorted-array/)

题目描述：给定一个旋转的有序数组，例如有序数组  [0,1,2,4,5,6,7] 经一次旋转后可得 [4,5,6,7,0,1,2] ，再给定一个 target 值，返回 target 值在数组中的下标，如果不在返回 -1，假设数组中无重复元素，时间复杂度要求为 O(logn)。

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

解题思路：题目要求时间复杂度是 O(logn) ，那就告诉我们要用二分搜索了，但是二分搜索是基于有序序列的，但是给出的数组经过一次旋转之后就不再是有序的了，这时就要添加额外的判断条件。

```java
public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;
    int lo = 0, hi = nums.length - 1;
    while (hi > lo) {
        int mid = (lo + hi) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        // 一定有一边是有序的
        if (nums[mid] >= nums[lo]) {
            // 如果不再有序的那一边，就一定在另外一边
            if (target >= nums[lo] && target < nums[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        } else {
            if (target > nums[mid] && target <= nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
    }
    return nums[lo] == target ? lo : -1;
}
```

# 在排序数组中查找元素的第一个和最后一个位置

[Leetcode - 34 Find First and Last Position of Element in Sorted Array (Medium)](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

题目描述：给定一个有序序列与一个 target 值，求出 target 在有序序列中的区间，时间复杂度要求为 O(logn)，若没有 target 值，则返回 [-1, -1]。

```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

解题思路：时间复杂度 O(logn)，依旧是二分法，经典的二分法只需找到一个元素，本题需要对二分法进行改进，分两次查找，第一次找到左区间的位置，第二次找到右区间的位置。

```java
public int[] searchRange(int[] nums, int target) {
    if(nums.length == 0) return new int[]{-1, -1};
    int[] ans = new int[]{-1, -1};
    int l = 0, r = nums.length - 1;
    // 左闭区间
    while(l < r){
        int mid = (l + r)/2;
        if(target <= nums[mid]){
            r = mid;
        }else{
            l = mid + 1;
        }
    }
    if(nums[l] != target) return ans;
    ans[0] = l;
    // 右闭区间
    r = nums.length - 1;
    while(l < r){
        int mid = (l + r)/2 + 1;
        if(target >= nums[mid]){
            l = mid;
        }else{
            r = mid - 1;
        }
    }
    ans[1] = r;
    return ans;
}
```
