package com.nettyTest;

import org.junit.Test;

public class NodeTest {
		public class Node {
		    //为了方便，这两个变量都使用public，而不用private就不需要编写get、set方法了。
		    //存放数据的变量，简单点，直接为int型
		    public int data;
		    //存放结点的变量,默认为null
		    public Node next;
		    
		    //构造方法，在构造时就能够给data赋值
		    public Node(int data){
		        this.data = data;
		    }
		
		public Node head;
		/**
	     * 增加操作
	     *         直接在链表的最后插入新增的结点即可
	     *         将原本最后一个结点的next指向新结点
	     */
	    public void addNode(Node node){
	        //链表中有结点，遍历到最后一个结点
	        Node temp = head;    //一个移动的指针(把头结点看做一个指向结点的指针)
	        while(temp.next != null){    //遍历单链表，直到遍历到最后一个则跳出循环。
	            temp = temp.next;        //往后移一个结点，指向下一个结点。
	        }
	        temp.next = node;    //temp为最后一个结点或者是头结点，将其next指向新结点
	    }
	    /**
	     * 遍历单链表，打印所有data
	     */
	    public void print(){
	        Node temp = head;
	        while(temp != null){
	            System.out.print(temp.data+",");
	            temp = temp.next;
	        }
	        System.out.println();
	    }
	    
		}
		@Test
		public void test() {
			Node node1=new Node(0);
			Node node2=new Node(1);
			Node node3=new Node(2);
			node1.head=node1;
			node1.addNode(node2);
			node1.addNode(node3);
			node1.print();
			node1=node1.next;
			System.out.println(node1.next.data);
		}
	}

