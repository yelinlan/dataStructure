package com.yelinlan.linearlist;

/**
 *@项目名称: dataStructure
 *@类名称: Custom_String
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/29 18:29
 **/
public class Custom_String<T> extends Custom_ArrayList<Character> {

	public Custom_String() {
		super();
	}

	public Custom_String(char[] ch) {
		super();
		for (int i = 1; i <= ch.length; i++) {
			super.insert(i, ch[i - 1]);
		}
	}

	int compare(Custom_String<Character> string) {
		for (int i = 1; i <= string.length && i <= length; i++) {
			if ((char) get(i) < (char) string.get(i)) {
				return -1;
			}
			if ((char) get(i) < (char) string.get(i)) {
				return 1;
			}
		}
		return Integer.compare(length, string.length);
	}

	Custom_String<Character> subString(int pos, int len) {
		if (pos + len > length) {
			throw new RuntimeException("数组越界异常");
		}
		Custom_String<Character> sub = new Custom_String<>();
		for (int i = pos; i < pos + len; i++) {
			sub.insert(i - pos+1, get(i+1));
		}
		return sub;
	}

	Custom_String<Character> subString(int pos) {
		return subString(pos, length);
	}

	Custom_String<Character> concat(Custom_String<Character> a) {
		Custom_String<Character> sub = new Custom_String<>();
		int i = 0;
		for (; i < length; i++) {
			sub.insert(i+1, get(i+1));
		}
		for (; i < length + a.length; i++) {
			sub.insert(i+1, a.get(i+1-length));
		}
		return sub;
	}

	int indexOf(Custom_String<Character> a) {
		return indexOf(a, 0);
	}

	int indexOf(Custom_String<Character> a, int start) {
		for (int i = start; i < length; i++) {
			int index = i;
			int j = 0;
			for (; j < a.length; j++) {
				if ((char) get(++index) != (char) a.get(j+1)) {
					break;
				}
			}
			if (j == a.length) {
				return i;
			}
		}
		return -1;
	}

	void replace(Custom_String<Character> a, Custom_String<Character> rep) {
		int index = indexOf(a, 0);
		while (index != -1) {
			remove(index + 1, a.length);
			add(index, rep);
			index = indexOf(a, index + rep.length);
		}
	}

	void add(int pos, Custom_String<Character> a) {
		for (int i = 1; i <= a.length; i++) {
			insert(i+pos, a.get(i));
		}
	}

	void remove(int pos, int len) {
		for (int i = 0; i < len; i++) {
			delete(pos);
		}
	}

	public static void main(String[] args) {
		Custom_String<Character> a = new Custom_String<>("123456789".toCharArray());
		a.traverse(System.out::print);
		Custom_String<Character> b = new Custom_String<>("abcdefghi".toCharArray());
		b.traverse(System.out::print);

		System.out.println(a.compare(a));
		a.subString(0).traverse(System.out::print);
		a.subString(3,4).traverse(System.out::print);
		a.concat(b).traverse(System.out::print);
		Custom_String<Character> c = new Custom_String<>("123123123".toCharArray());
		c.traverse(System.out::print);
		Custom_String<Character> d = new Custom_String<>("123".toCharArray());
		d.traverse(System.out::print);
		System.out.println(c.indexOf(d,2));
		Custom_String<Character> e = new Custom_String<>("ab".toCharArray());
		e.traverse(System.out::print);
		c.replace(d,e);
		c.traverse(System.out::print);
	}

}