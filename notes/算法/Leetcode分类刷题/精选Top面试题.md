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
            count+=cur;
        }
        prev = cur;
    }
    return count;
}
```