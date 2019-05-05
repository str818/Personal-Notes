# Leetcode 分类刷题

## 1. 爬楼梯

[Leetcode - 70 Climbing Stairs (Easy)](https://leetcode.com/problems/climbing-stairs/)

题目描述：共有 n 个台阶，每次只能往上爬 1 个或者 2 个台阶，求爬 n 个台阶共有几种不同的爬法。

```
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```

解题思路：

首先想到的是暴力求解，使用递归的方法依次计算，模拟向上爬 1 步与爬 2 步的情况，使用递归方法的话就不可避免的产生重复计算，时间复杂度 O(2^n)，当 n = 44 时，Leetcode 就会提示 「Time Limit Exceeded」。

```java
public int climbStairs(int n) {
    return climb_Stairs(0, n);
}
public int climb_Stairs(int i, int n) {
    if (i > n) return 0;
    if (i == n) return 1;
    return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
}
```

既然有重复计算，那就使用数组记录重复的计算，这样第二次计算就可以直接读取数组中记录的结果了，时间与空间复杂度均为 O(n)。

```java
public int climbStairs(int n) {
    int memo[] = new int[n + 1];
    return climb_Stairs(0, n, memo);
}
public int climb_Stairs(int i, int n, int memo[]) {
    if (i > n) return 0;
    if (i == n) return 1;
    if (memo[i] > 0) return memo[i];
    memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
    return memo[i];
}
```

除此之外，还可以将题目分解成子结构，使用动态规划求解。定义 dp[i] 表示爬上第 i 个台阶共有几种方法，可以得到状态方程 dp[i] = dp[i - 1] + dp[i - 2]，意为前一次爬一步与爬两步到 i 的方法和。

```java
public int climbStairs(int n) {
    if (n == 1) return 1;
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```

通过前面的分析，也可以看出这是一个斐波那契数列，直接使用两个变量递推即可。

```java
public int climbStairs(int n) {
    if (n <= 2) return n;
    int f1 = 1, f2 = 2;
    for(int i = 2; i < n; i++){
        int t = f2;
        f2 = f1 + f2;
        f1 = t;
    }
    return f2;
}
```



