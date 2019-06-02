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

      left_part          |        right_part
A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

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