package com.yelinlan.linearlist;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 *@项目名称: dataStructure
 *@类名称: Custom_LinkedList
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/23 19:41
 **/
public class Custom_LinkedList<T> {

	class Node {
		T data;
		Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Node copy(){
			return new Node(data,null);
		}

	}

	public Node head;
	public Node tail;
	public int length;

	public Custom_LinkedList() {
		Node node = new Node(null, null);
		this.head = node;
		this.tail = node;
		this.length = 0;
	}

	void clear() {
		head.next = null;
		tail.next = null;
		length = 0;
	}

	void insertFirst(Node node) {
		Node temp = head.next;
		head.next = node;
		node.next = temp;
		length++;
	}

	Node delFirst() {
		Node temp = head.next;
		if (temp != null) {
			head.next = temp.next;
			length--;
			if (length == 0) {
				tail = head;
			}
			temp=temp.copy();
		}
		return temp;
	}

	void append(Node node) {
		tail.next = node;
		tail = tail.next;
		length++;
	}

	Node prior(Node node) {
		Node p = head;
		while (p.next != null) {
			if (p.next == node) {
				return p;
			}
			p = p.next;
		}
		throw new RuntimeException("没有前驱元素！");
	}

	Node after(Node node) {
		return node.next;
	}

	Node remove() {
		Node del = null;
		if (length != 0) {
			del = tail.copy();
			Node prior = prior(tail);
			prior.next = null;
			tail = prior;
			length--;
			return del;
		}
		return null;
	}

	void insertBefore(Node p, Node s) {
		Node prior = prior(p);
		prior.next = s;
		s.next = p;
		length++;
	}

	void insertAfter(Node p, Node s) {
		Node after = p.next;
		p.next = s;
		s.next = after;
		if (after == null) {
			tail = s;
		}
		length++;
	}

	void setElem(Node p, T e) {
		p.data = e;
	}

	T getElem(Node p) {
		return p.data;
	}

	boolean empty() {
		return length == 0;
	}

	int length() {
		int len = 0;
		Node p = head.next;
		while (p.next != null) {
			len++;
			p = p.next;
		}
		return len;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		Node p = head.next;
		while (p.next != null) {
			p = p.next;
		}
		return p;
	}


	Node get(int pos) {
		int index = 0;
		Node p = head.next;
		if (pos > 0 && pos <= length) {
			while (p != null) {
				index++;
				if (index == pos) {
					return p;
				}
				p = p.next;
			}
		}
		throw new RuntimeException("索引超出范围");
	}

	Node locate(T e) {
		Node p = head.next;
		while (p.next != null) {
			if (p.data.equals(e)) {
				return p;
			}
			p = p.next;
		}
		return null;
	}

	int locate(T data, BiFunction<T, T, Integer> compare) {
		int pos = 1;
		Node p = head.next;
		while (p != null) {
			if (compare.apply(data, p.data) <= 0) {
				return pos;
			}
			pos++;
			p = p.next;
		}
		return pos;
	}

	void insert(int pos, Node node) {
		if (node.next == null) {
			if (pos > 0 && pos <= length) {
				Node cur = get(pos);
				insertBefore(cur, node);
			}
			if (pos > length) {
				append(node);
			}
		}
	}

	void delete(int pos) {
		if (pos > 0 && pos < length) {
			Node cur = get(pos);
			Node prior = prior(cur);
			prior.next = cur.next;
			length--;
			return;
		}
		if (pos == length) {
			remove();
			length--;
		}
	}

	@SuppressWarnings("unchecked")
	void traverse(Consumer visit) {
		System.out.println("=================content===============");
		Node p = head.next;
		while (p != null) {
			visit.accept(p.data);
			p = p.next;
		}
		System.out.println();
	}

	Custom_LinkedList<T> merge(Custom_LinkedList<T> lb, BiFunction<T, T, Integer> compare) {

		Node head_a = head.next;
		Node head_b = lb.head.next;
		//+₈.₈₈X¹¹-₉.₉₉X²²+₅.₆₉X³³
		//================================
		//+₃₃.₀X¹¹-₉.₉₉X²²+₂₂.₀X³³+₃₃.₀X⁴⁴+₃₃.₀X⁶⁶
		while (head_a != null && head_b != null) {
			if (compare.apply(head_a.data, head_b.data) <= 0) {
				head_a = head_a.next;
			} else {
				insertBefore(head_a, new Node(head_b.data, null));
				head_b = head_b.next;
			}
		}

		while (head_b != null) {
			append(new Node(head_b.data, null));
			head_b = head_b.next;
		}
		return this;
	}


	public static void main(String[] args) {
		Custom_LinkedList<String> list = new Custom_LinkedList<>();
		list.append(list.new Node("1", null));
		list.append(list.new Node("2", null));
		list.insert(100, list.new Node("3", null));
		list.delete(2);
		list.traverse(System.out::println);
		Custom_LinkedList<String> list1 = new Custom_LinkedList<>();
		list1.append(list1.new Node("1", null));
		list1.insert(100, list1.new Node("2", null));
				list1.append(list1.new Node("3", null));
				list1.delete(2);
		list1.traverse(System.out::println);
		list.merge(list1, String::compareTo);
		list.traverse(System.out::println);
	}
}