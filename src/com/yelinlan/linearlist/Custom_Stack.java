package com.yelinlan.linearlist;

import com.yelinlan.utils.CharUtils;

/**
 *@项目名称: dataStructure
 *@类名称: Custom_Stack
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/24 19:49
 **/
public class Custom_Stack<T> extends Custom_LinkedList<T> {

	public Custom_Stack() {
		super();
	}

	T top() {
		if (tail == head) {
			throw new RuntimeException("栈顶元素不存在！");
		}
		return tail.data;
	}

	void push(T e) {
		super.append(new Node(e, null));
	}

	T pop() {
		Node remove = super.remove();
		if (remove == null) {
			throw new RuntimeException("栈顶元素不存在！");
		}
		return remove.data;
	}

	public static void main(String[] args) {
		//进制转换
		int N = 1348;
		int target = 8;
		System.out.println(N + CharUtils.sub(10));
		Custom_Stack<Integer> stack = new Custom_Stack<>();
		while (N != 0) {
			stack.push(N % target);
			N /= target;
		}
		while (!stack.empty()) {
			System.out.print(stack.pop());
		}
		System.out.println(CharUtils.sub(target));

		//括号匹配
		String re = "[{()()()[]}]";
		Custom_Stack<Character> stack1 = new Custom_Stack<>();
		for (char c : re.toCharArray()) {
			if (stack1.empty()) {
				stack1.push(c);
			} else {
				Character top = stack1.top();
				if (CharUtils.match(top, c)) {
					stack1.pop();
				} else {
					stack1.push(c);
				}
			}
		}
		if (stack1.empty()) {
			System.out.println("完美匹配！");
		} else {
			System.out.println("匹配失败！");
		}

		//行编辑器
		Custom_Stack<Character> stack2 = new Custom_Stack<>();
		while (true) {
			System.out.print(">>");
			//			Scanner scanner = new Scanner(System.in);
			//			char c = scanner.next().charAt(0);
			char c = '!';
			if (c == '@') {
				stack2.clear();
			} else if (c == '#') {
				if (!stack2.empty()) {
					stack2.pop();
				}
			} else if (c == '!') {
				break;
			} else {
				stack2.push(c);
			}
			stack2.traverse(System.out::print);
		}

		//表达式求值
		// TODO: 2024/2/29

		//汉诺塔

		hanoi(3,'A','B','C');


	}

	private static void hanoi(int n, char a, char b, char c) {
		if (n == 1) {
			move(a, 1, c);
		} else {
			hanoi(n - 1, a, c, b);
			move(a, n, c);
			hanoi(n - 1, b, a, c);
		}
	}

	private static void move(char a, int n, char c) {
		System.out.println(n + "：" + a + "--->" + c);
	}


}