package com.yelinlan.linearlist;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 *@项目名称: dataStructure
 *@类名称: Custom_ArrayList
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/23 19:41
 **/
public class Custom_ArrayList<T> {

	private final int LIST_INIT_SIZE = 100;
	private final int LIST_INCREMENT = 20;
	private final double LIST_INCREMENT_SPEED = 1.5;

	public Object[] array;
	public int length;
	public int size = LIST_INIT_SIZE;

	public Custom_ArrayList() {
		array = new Object[size];
	}


	void clear() {
		length = 0;
	}

	boolean empty() {
		return length == 0;
	}

	int length() {
		return length;
	}

	@SuppressWarnings("unchecked")
	T get(int i) {
		return (T) array[i - 1];
	}

	int locate(T e) {
		for (int i = 0; i < length; i++) {
			if (array[i].equals(e)) {
				return i + 1;
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	T prior(T e) {
		int pos = locate(e);
		if (pos != 0 && pos != 1) {
			return (T) array[pos - 2];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	T next(T e) {
		int pos = locate(e);
		if (pos != 0 && pos != length) {
			return (T) array[pos];
		}
		return null;
	}

	void insert(int pos, T e) {
		autoGrow(length + 1);
		int moveNum = length - pos + 1;
		if (moveNum > 0)
			System.arraycopy(array, pos - 1, array, pos, moveNum);
		array[pos - 1] = e;
		length++;
	}

	@SuppressWarnings("unchecked")
	T delete(int pos) {
		T old = (T) array[pos];
		int move = length - pos;
		if (move > 0)
			System.arraycopy(array, pos, array, pos - 1, move);
		length--;
		return old;
	}

	@SuppressWarnings("unchecked")
	void traverse(Consumer visit) {
		System.out.println("================================");
		Arrays.stream(array, 0, length).forEach(visit);
	}

	void autoGrow(int len) {
		boolean isGrow = false;
		while (len > size) {
			size = (int) (size > LIST_INCREMENT << 1 ? (size * LIST_INCREMENT_SPEED) : size + LIST_INCREMENT);
			isGrow = true;
		}
		if (isGrow) {
			Object[] o = new Object[size];
			System.arraycopy(array, 0, o, 0, length);
			array = o;
		}
	}

	void union(Custom_ArrayList<T> lb) {
		int len_a = this.length;
		int len_b = lb.length;
		for (int i = 1; i <= len_b; i++) {
			T e = lb.get(i);
			if (this.locate(e) == 0) {
				this.insert(++len_a, e);
			}
		}
	}

	void merge(Custom_ArrayList<T> la, Custom_ArrayList<T> lb, BiFunction<T, T, Integer> compare) {
		int i = 1;
		int j = 1;
		int k = 1;
		int len_b = lb.length;
		int len_a = la.length;

		while (i <= len_a && j <= len_b) {
			if (compare.apply(la.get(i), lb.get(j)) <= 0) {
				insert(k++, la.get(i++));
			} else {
				insert(k++, lb.get(j++));
			}
		}
		while (i <= len_a) {
			insert(k++, la.get(i++));
		}
		while (j <= len_b) {
			insert(k++, lb.get(j++));
		}
	}

	public static void main(String[] args) {
		Custom_ArrayList<Integer> list = new Custom_ArrayList<>();
		for (int i = 1; i < 5; i++) {
			list.insert(i, i);
		}
		list.traverse(System.out::println);
		Custom_ArrayList<Integer> list1 = new Custom_ArrayList<>();
		for (int i = 1; i < 6; i++) {
			list1.insert(i, i + 1);
		}
		list.union(list1);
		list.traverse(System.out::println);
		list1.traverse(System.out::println);

		Custom_ArrayList<Integer> list3 = new Custom_ArrayList<>();
		list3.merge(list, list1, Integer::compare);
		list3.traverse(System.out::println);
	}
}