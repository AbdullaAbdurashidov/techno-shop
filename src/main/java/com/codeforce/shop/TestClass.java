package com.codeforce.shop;

public class TestClass {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        int num = 0;
        do{
            ListNode node = new ListNode();
            if(l1.val != 0){
                if(l2.val !=0){
                    if(l1.val + l2.val + num>=10){
                        node.val = l1.val + l2.val-10;
                        num = 1;
                    }else{
                        node.val = l1.val + l2.val;
                        num = 0;
                    }
                }
                else{
                    node.val = l1.val;
                }
            }
            else{
                node.val = l2.val;
            }

            if(result.next ==null){
                result.next = node;
            }
            else{
                ListNode traverse;
                traverse = result.next;
                while(traverse.next != null){
                    traverse = traverse.next;
                }
                traverse.next = node;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1.next!=null || l2.next!=null);

        return result;
    }

    public static void main(String args[]){
        ListNode b = new ListNode(2);
        ListNode b1 = new ListNode(4);
        ListNode b2 = new ListNode(3);
        b1.next = b2;
        b.next = b1;

        ListNode c = new ListNode(5);
        ListNode c1 = new ListNode(6);
        ListNode c2 = new ListNode(4);
        c1.next = c2;
        c.next = c1;
        ListNode result = addTwoNumbers(b, c);
        System.out.println(result.toString());
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


