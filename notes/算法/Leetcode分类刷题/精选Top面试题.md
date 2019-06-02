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