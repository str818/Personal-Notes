
LRU 全称为 Least Recently Used，意为最近最少使用，设计思想是：「如果数据最近被访问过，那么将来访问的几率更高」。

## 0. 讲解视频

[理论讲解](https://www.bilibili.com/video/av46292575/?p=56)

[习题讲解](https://www.bilibili.com/video/av46292575/?p=57)

## 1. 设计 LRU Cache

[Leetcode - 146 LRU Cache (Hard)](https://leetcode.com/problems/lru-cache/)

```java
class LRUCache {
    class DlinkedNode {
        int key;
        int value;
        DlinkedNode pre;
        DlinkedNode post;
    }
    
    // 在头节点之后插入新节点
    private void addNode(DlinkedNode node) {
        node.pre = head;
        node.post = head.post;
        
        head.post.pre = node;
        head.post = node;
    }
    
    // 删除节点
    private void removeNode(DlinkedNode node) {
        DlinkedNode pre = node.pre;
        DlinkedNode post = node.post;
        
        pre.post = post;
        post.pre = pre;
    }
    
    // 将节点移动到头部
    private void moveToHead(DlinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }
    
    // 弹出尾部的节点
    private DlinkedNode popTail() {
        DlinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }
    
    private Hashtable<Integer, DlinkedNode> cache = new Hashtable<>();
    private int count;
    private int capacity;
    private DlinkedNode head, tail;
    
    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        
        head = new DlinkedNode();
        head.pre = null;
        
        tail = new DlinkedNode();
        tail.post = null;
        
        head.post = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        DlinkedNode node = cache.get(key);
        
        if(node == null) {
            return -1;
        }
        
        this.moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DlinkedNode node = cache.get(key);
        if(node == null) {
            DlinkedNode newNode = new DlinkedNode();
            newNode.key = key;
            newNode.value = value;
            
            this.cache.put(key, newNode);
            this.addNode(newNode);
            
            ++count;
            
            if(count > capacity) {
                DlinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            node.value = value;
            this.moveToHead(node);
        }
    }
}
```