package com.yelinlan.tree;

import com.yelinlan.linearlist.Custom_Queue;
import com.yelinlan.linearlist.Custom_Stack;

/**
 *@项目名称: dataStructure
 *@类名称: Subset
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/3/9 16:19
 **/
public class Subset {

	void sub(String[] a, int index, Custom_Stack<String> stack) {
		if (index == a.length) {
			stack.traverse(System.out::println);
			return;
		}
		stack.push(a[index]);
		sub(a, index + 1, stack);
		stack.pop();
		sub(a, index + 1, stack);
	}

	public static void main(String[] args) {
		Subset subset = new Subset();
		Custom_Stack<String> stack = new Custom_Stack<>();
		subset.sub(new String[]{"1", "2", "3"}, 0, stack);
	}

}