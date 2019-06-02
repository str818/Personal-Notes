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