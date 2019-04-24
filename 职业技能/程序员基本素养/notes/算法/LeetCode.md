## 数组

* [80. 删除排序数组中的重复项 Ⅱ](#删除排序数组中的重复项-Ⅱ)


## 双指针
* [167. 两数之和 Ⅱ - 输入有序数组](#两数之和-Ⅱ---输入有序数组)
* [633. 平方数之和](#平方数之和)
* [345. 反转字符串中的元音字母](#反转字符串中的元音字母)
* [11.  乘最多水的容器](#乘最多水的容器)
* [125. 验证回文串](#验证回文串)
* [680. 验证回文字符串 Ⅱ](#验证回文字符串-Ⅱ)
* [88.  合并两个有序数组](#合并两个有序数组)
* [141. 环形链表](#环形链表)
* [524. 通过删除字母匹配到字典里最长单词](#通过删除字母匹配到字典里最长单词)
* [905. 按奇偶排序数组](#按奇偶排序数组)

## 字符串
* [151. 翻转字符串里的单词](#翻转字符串里的单词)
* [443. 压缩字符串](#压缩字符串)


## 数组

### 删除排序数组中的重复项 Ⅱ

【 [英文练习](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/)  |  [中文练习](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/description/) 】

**题目描述：** 给定一个排序数组，你需要在**原地**删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在**原地修改输入数组**并在使用 O(1) 额外空间的条件下完成。

**解题思路** ： 遍历一遍数组，用一个下标记录替换位置，将所有元素重新赋值一遍。
```java
public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
        
    int i = 0;
    for(int num : nums){
        if(i < 2 || num > nums[i-2]){
            nums[i] = num;
            i += 1;
        }
    }
    return i;
}
```

## 双指针

### 两数之和 Ⅱ - 输入有序数组

【 [英文练习](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)  |  [中文练习](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/description/) 】

**题目描述：** 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数，函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

**说明：** 

* 返回的下标值（index1 和 index2）不是从零开始的。
* 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

**示例：**

```
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```

**解题思路：** 双指针一个从数组的头部开始，一个从数组的尾部开始，向中间缩进，比较两数和与 target 的大小，时间复杂度 O(n) 。

```java
public int[] twoSum(int[] numbers, int target) {
    int start = 0 , end = numbers.length-1;
    while(start < end){
        int sum = numbers[start] + numbers[end];
        if(sum == target){
            return new int [] { start+1, end+1};
        }else if(sum > target){
            end--;
        }else{
            start++;
        }
    }
    return new int[2];
}
```

### 平方数之和

【 [英文练习](https://leetcode.com/problems/sum-of-square-numbers/description/)  |  [中文练习](https://leetcode-cn.com/problems/sum-of-square-numbers/description/) 】

**题目描述：** 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，c 等于 a 和 b 的平方和。

**示例：**
```
输入: 5
输出: True
解释: 1 * 1 + 2 * 2 = 5
```

**解法一：** 利用双指针的思想，需要注意两个坑点，一个是 a 可能等于 b ，第二个是 a 和 b 可能为 0 。b 的起始位置为 ```Math.sqrt(c)``` 。

```java
public boolean judgeSquareSum(int c) {
    int a = 0, b = (int)Math.sqrt(c);
    while(a <= b){
        int ans = a * a + b * b;
        if(ans == c){
            return true;
        }else if(ans > c){
            b--;
        }else if(ans < c){
            a++;
        }
    }  
    return false;
}
```
**解法二：** 用根号替换搜索结果的方法。

```java
public boolean judgeSquareSum(int c) {
    for (long a = 0; a * a <= c; a++) {
        double b = Math.sqrt(c - a * a);
        if (b == (int) b) return true;
    }
    return false;
}
```

### 反转字符串中的元音字母

【 [英文练习](https://leetcode.com/problems/reverse-vowels-of-a-string/description/)  |  [中文练习](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/description/) 】

**题目描述：** 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

**示例：**
```
输入: "hello"
输出: "holle"
```

**解题思路：** 双指针典型题目，注意元音字母不要只考虑小写的。

```java
public String reverseVowels(String s) {
	
    if(s == null || s.length() == 0) return s;
        
    char[] c = s.toCharArray();
    int pre = 0, last = c.length - 1;
    while(last > pre){
        while(last > pre && !isVowel(c[last])) last--;
        while(last > pre && !isVowel(c[pre])) pre++;
            
        if(last > pre){
            char t = c[last];
            c[last] = c[pre];
            c[pre] = t;
        }      
        last--;
        pre++;
    }  
    return new String(c);
}
    
public boolean isVowel(char c){
    c = Character.toLowerCase(c);
    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
    return false;
}
```


### 乘最多水的容器

【 [英文练习](https://leetcode.com/problems/container-with-most-water/description/)  |  [中文练习](https://leetcode-cn.com/problems/container-with-most-water/description/) 】


**题目描述：** 给定 n 个非负数 a1,a2,...,an，每个数代表坐标中的一个点 (i,ai)。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,ai) 和 (i,0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

<div align="center">  <img src="./pic/leetcode_11.jpg" width="60%"/> </div><br>

**示例：**
```
输入: [1,8,6,2,5,4,8,3,7]
输出: 49
```

**解题思路：** 两线段之间形成的区域总是会受到其中较短那条长度的限制，同时，两线段距离越远，得到的面积就越大。使用两个指针，一个放在开始，一个放在末尾，更新存储的最大面积，将指向较短线段的指针向较长线段那端移动一步。

```java
public int maxArea(int[] height) {
    int start = 0,end = height.length - 1;
    int maxArea = 0;
    while(end > start){
        maxArea = Math.max(maxArea, Math.min(height[start], height[end]) * (end - start));
        if(height[start] > height[end]) end--;
        else start++;
    }
    return maxArea;
}
```
### 验证回文串

【 [英文练习](https://leetcode.com/problems/valid-palindrome/description/)  |  [中文练习](https://leetcode-cn.com/problems/valid-palindrome/description/) 】

**题目描述：** 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，忽略字母的大小写。

**解题思路：** 用双指针的思想，从字符串的头部与尾部向中间移动比较。

```java
public boolean isPalindrome(String s) {
    if (s.isEmpty()) return true;
    int head = 0, tail = s.length() - 1;
    char cHead, cTail;
    while(head <= tail) {
        cHead = s.charAt(head);
        cTail = s.charAt(tail);
        if (!Character.isLetterOrDigit(cHead)) {
        	head++;
    	} else if(!Character.isLetterOrDigit(cTail)) {
    		tail--;
    	} else {
    		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
    			return false;
    	    }
    	    head++;
    	    tail--;
    	}
    }
    return true;
}
```

### 验证回文字符串 Ⅱ

【 [英文练习](https://leetcode.com/problems/valid-palindrome-ii/description/)  |  [中文练习](https://leetcode-cn.com/problems/valid-palindrome-ii/description/) 】

**题目描述：** 给定一个非空字符串，最多删除一个字符，判断是否能成为回文字符串。

**示例：**
```
输入: "abca"
输出: True
解释: 可以删除c字符
```

```java
public boolean validPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    char[] ca = s.toCharArray();
        
    while (left < right) {
        if (ca[left] != ca[right]) {
            // Moving Left
            int l1 = left + 1, r1 = right;
            while (l1 < r1 && ca[l1] == ca[r1]) { l1++; r1--; }
            // Moving right
            int l2 = left, r2 = right - 1;
            while (l2 < r2 && ca[l2] == ca[r2]) { l2++; r2--; }
            return l1 >= r1 || l2 >= r2;
        } else { left++; right--; }    
    }
    return true;
}
```

### 合并两个有序数组

【 [英文练习](https://leetcode.com/problems/merge-sorted-array/description/)  |  [中文练习](https://leetcode-cn.com/problems/merge-sorted-array/description/) 】

**题目描述：** 给定两个有序数组，合并它们，合并之后的数组依旧有序。

**解题思路：** 从后向前存放。

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null) return;  
        
        while(m > 0 && n > 0){
            if(nums1[m - 1] >= nums2[n - 1]){
                nums1[m + n - 1] = nums1[m - 1];
                m--;
            }else{
                nums1[m + n - 1] = nums2[n - 1];
                n--;
            }
        }
        
        // 这一步可以省略
        while(m > 0){
            nums1[m - 1] = nums1[m - 1];
            m--;
        }
        
        while(n > 0){
            nums1[n - 1] = nums2[n - 1];
            n--;
        }
    }
```

### 环形链表

【 [英文练习](https://leetcode.com/problems/linked-list-cycle/description/)  |  [中文练习](https://leetcode-cn.com/problems/linked-list-cycle/description/) 】

**题目描述：** 给定一个链表，判断链表中是否有环。

**解题思路：** 一种方法可以使用 Hash Table ，判断该结点之前是否遇到过；更优的方法是使用双指针，一个指针每次移动一个结点，一个指针每次移动两个结点，如果存在环，那么这两个指针一定会相遇。

```java
public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
        if (fast == null || fast.next == null) {
            return false;
        }
        slow = slow.next;
        fast = fast.next.next;
    }
    return true;
}
```

### 通过删除字母匹配到字典里最长单词

【 [英文练习](https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/)  |  [中文练习](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/description/) 】

**题目描述：** 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

**示例1：**
```
输入:
s = "abpcplea", d = ["ale","apple","monkey","plea"]
输出: 
"apple"
```

**示例2：**
```
输入:
s = "abpcplea", d = ["a","b","c"]
输出: 
"a"
```
**说明：**
* 所有输入的字符串只包含小写字母。
* 字典的大小不会超过 1000。
* 所有输入的字符串长度不会超过 1000。

**解法一：** 双指针进行比较。

```java
public String findLongestWord(String s, List<String> d) {
    String longestWord = "";
    for (String target : d) {
        int l1 = longestWord.length(), l2 = target.length();
        if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)) {
            continue;
        }
        if (isValid(s, target)) {
            longestWord = target;
        }
    }
    return longestWord;
}

private boolean isValid(String s, String target) {
    int i = 0, j = 0;
    while (i < s.length() && j < target.length()) {
        if (s.charAt(i) == target.charAt(j)) {
            j++;
        }
        i++;
    }
    return j == target.length();
}
```

**解法二：** 运用 Java API `String.indexof` 。

```java
public String findLongestWord(String s, List<String> d) {
    if (d == null || d.size() == 0 || s == null || s.length() == 0) return "";
    String res = "";
    for (String str : d) {
        if ((str.length() > res.length() || (str.length() == res.length() && str.compareTo(res) < 0)) && check(s, str)) {
            res = str;
        }
    }
    return res;
}
    
public boolean check(String s, String str) {
    int index = -1;
    for (char c : str.toCharArray()) {
        index = s.indexOf(c, index+1);
        if (index == -1) return false;
    }
    return true;
}
```

### 按奇偶排序数组

【 [英文练习](https://leetcode.com/problems/sort-array-by-parity/)  |  [中文练习](https://leetcode-cn.com/problems/sort-array-by-parity/) 】

**题目描述：** 将一个数组中所有的偶数放到前半部分，所有的奇数放到后半部分。可以返回满足此条件的任何数组作为答案。

**解题思路** ：可以重新开辟一个数组，遍历两遍元素组，分别将其中的偶数与奇数取出来。还有一种方法是利用双指针的思想，额外空间复杂度为 O(1) 。

```java
public int[] sortArrayByParity(int[] A) {
    if (A == null || A.length < 2) {
        return A;
    }
    int evenIdx = 0, oddIdx = A.length - 1;
    while (evenIdx < oddIdx) {
        if ((A[evenIdx] & 1) == 0) {
            evenIdx++;
        } else if ((A[oddIdx] & 1) != 0) {
            oddIdx--;
        } else {
            swap(A, evenIdx++, oddIdx--);
        }
    }
    return A;
}
void swap(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
}
```


### 翻转字符串里的单词

【 [英文练习](https://leetcode.com/problems/reverse-words-in-a-string/description/)  |  [中文练习](https://leetcode-cn.com/problems/reverse-words-in-a-string/description/) 】

**题目描述：** 给定一个字符串，逐个翻转字符串中的每个单词。

**示例：**
```
输入： "the sky is blue"
输出： "blue is sky the"
```
**说明：**
* 无空格字符构成一个单词。
* 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
* 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

**解法一：** 使用 Java API

```java
public String reverseWords(String s) {
	// 正则式去掉空格
	String[] words = s.trim().split(" +");
	// 翻转
	Collections.reverse(Arrays.asList(words));
	// 单词间隔添加空格
	return String.join(" ", words);
}
```

**解法二：** 不使用 API ，纯裸写

```java
    public String reverseWords(String s) {
        if (s == null) return null;
        
        char[] c = s.toCharArray();
        
        // 第一步：反转整个字符串
        reverse(c, 0, c.length - 1);
        // 第二步：反转每个单词
        reverseWords(c);
        // 第三步：清空空格
        return cleanSpaces(c);
        
    }
    
    // 反转所有单词
    public void reverseWords(char[] c){
        int i = 0, j = 0;
        
        while (i < c.length){
            while (i < j || i < c.length && c[i] == ' ') i++;      // 跳过空格
            while (j < i || j < c.length && c[j] != ' ') j++;      // 跳过非空格
            reverse(c, i, j - 1);
        }
    }
    
    // 去掉头部、尾部与中间的多余空格
    public String cleanSpaces(char[] c){
        int i = 0, j = 0;
        
        while (j < c.length){
            while (j < c.length && c[j] == ' ') j++;               // 跳过空格
            while (j < c.length && c[j] != ' ') c[i++] = c[j++];   // 去掉所有空格
            while (j < c.length && c[j] == ' ') j++;               // 跳过空格
            if (j < c.length) c[i++] = ' ';                        // 仅保留一个空格
        }
        
        return new String(c).substring(0, i);
    }
    
    // 从 i 到 j 反转数组 c
    public void reverse(char[] c, int i, int j){
        while(j > i){
            char t = c[i];
            c[i++] = c[j];
            c[j--] = t;
        }
    }
```

### 压缩字符串

【 [英文练习](https://leetcode.com/problems/string-compression/description/)  |  [中文练习](https://leetcode-cn.com/problems/string-compression/description/) 】

**题目描述：** 给定一组字符，使用原地算法将其压缩。压缩后的长度必须始终小于或等于原数组长度，数组的每个元素应该是长度为 1 的字符（不是 int 整数类型），在完成原地修改输入数组后，返回数组的新长度。

**示例 1：**
```
输入：
["a","a","b","b","c","c","c"]

输出：
返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]

说明：
"aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。
```
**示例 2：**
```
输入：
["a"]

输出：
返回1，输入数组的前1个字符应该是：["a"]

说明：
没有任何字符串被替代。
```
**示例 3：**
```
输入：
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

输出：
返回4，输入数组的前4个字符应该是：["a","b","1","2"]。

说明：
由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。
注意每个数字在数组中都有它自己的位置。
```

**说明：**
* 所有字符都有一个ASCII值在[35, 126]区间内。
* 1 <= len(chars) <= 1000。

**解题思路：** 用双指针的思想，一个指针从前向后读字符，一个指针从前向后写字符，再通过一个指针记录相同字符序列中的第一个字符下标，用来计算相同字符的数量。

```java
public int compress(char[] chars) {
    int anchor = 0, write = 0;
    for (int read = 0; read < chars.length; read++) {
        if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
            chars[write++] = chars[anchor];
            if (read > anchor) {
                for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                    chars[write++] = c;
                }
            }
            anchor = read + 1;
        }
    }
    return write;
}
```