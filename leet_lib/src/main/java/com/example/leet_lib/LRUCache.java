package com.example.leet_lib;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int capacity;
    private Map<Integer,ListNode> map;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        return -1;
    }

    public void put(int key,int value){
        if (map.containsKey(key)){
            map.get(key).val = value;
            return;
        }

        ListNode listNode = new ListNode(key,value);
        map.put(key,listNode);
        moveToTail(listNode);

        if (map.size() > capacity){
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.pre = head;
        }
    }


    private void moveToTail(ListNode node){
        node.pre = tail.pre;
        tail.pre = node;
        node.pre.next = node;
        node.next = tail;
    }


    public class ListNode{
        int key;
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            pre = null;
            next = null;
        }

    }
}
