package com.yelinlan.linearlist;

/**
 *@项目名称: dataStructure
 *@类名称: Custom_Queue
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/24 19:49
 **/
public class Custom_Queue<T> extends Custom_LinkedList<T> {

	public Custom_Queue() {
		super();
	}

	void enQueue(T data) {
		super.append(new Node(data, null));
	}

	T DeQueue() {
		return super.delFirst().data;
	}

	public static void main(String[] args) {
		Custom_Queue<Integer> queue = new Custom_Queue<>();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		System.out.println(queue.DeQueue());
		System.out.println(queue.DeQueue());
		System.out.println(queue.DeQueue());
		System.out.println(queue.DeQueue());

		Custom_Stack<Integer> stack = new Custom_Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}