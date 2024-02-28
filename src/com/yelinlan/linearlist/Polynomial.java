package com.yelinlan.linearlist;

import com.yelinlan.utils.CharUtils;

import java.util.function.BiFunction;

/**
 *@项目名称: dataStructure
 *@类名称: Polynomial
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/24 9:18
 **/
public class Polynomial {

	class Elem {
		double coef;
		int expn;

		public Elem(double coef, int expn) {
			this.coef = coef;
			this.expn = expn;
		}

		@Override
		public String toString() {
			return CharUtils.sub(coef) + "X" + CharUtils.sup(expn);
		}
	}

	private Custom_LinkedList<Elem> polynomial = new Custom_LinkedList<>();

	void createPoly(Elem e) {
		BiFunction<Elem, Elem, Integer> compare = (a, b) -> Integer.compare(a.expn, b.expn);
		createPoly(e, compare);
	}

	void createPoly(Elem e, BiFunction<Elem, Elem, Integer> compare) {
		Custom_LinkedList<Elem>.Node node = polynomial.new Node(e, null);
		if (polynomial.length == 0) {
			polynomial.append(node);
		} else {
			int pos = polynomial.locate(e, compare);
			polynomial.insert(pos, node);
		}
	}

	void destroy() {
		polynomial = new Custom_LinkedList<>();
	}

	void print() {
		polynomial.traverse(System.out::print);
		System.out.println();
	}

	int length() {
		return polynomial.length();
	}

	Polynomial add(Polynomial pb) {
		BiFunction<Elem, Elem, Integer> compare = (a, b) -> Integer.compare(a.expn, b.expn);
		if (pb != null) {
			polynomial.merge(pb.polynomial, compare);
		}
		if (polynomial.length > 1) {
			Custom_LinkedList<Elem>.Node p = polynomial.head.next;
			Custom_LinkedList<Elem>.Node q = p.next;
			while (p != null && q != null) {
				if (compare.apply(p.data, q.data) == 0) {
					p.data.coef += q.data.coef;
					p.next = q.next;
				}
				p = p.next;
				q = p == null ? null : p.next;
			}
		}
		return this;
	}

	Polynomial collect() {
		return add(null);
	}

	Polynomial subtract(Polynomial pa, Polynomial pb) {
		oppositeNumber(pb);
		Polynomial polynomial = add(pb);
		oppositeNumber(pb);
		return polynomial;
	}

	Polynomial multiply(Polynomial pa) {
		Polynomial polynomial = new Polynomial();
		Custom_LinkedList<Elem>.Node a = pa.polynomial.head.next;
		while (a != null) {
			Custom_LinkedList<Elem>.Node b = this.polynomial.head.next;
			while (b != null) {
				polynomial.createPoly(new Elem(b.data.coef * a.data.coef, b.data.expn + a.data.expn));
				b = b.next;
			}
			a = a.next;
		}
		return polynomial.collect();
	}

	private void oppositeNumber(Polynomial pb) {
		Custom_LinkedList<Elem>.Node p = pb.polynomial.head.next;
		while (p != null) {
			p.data.coef = -p.data.coef;
			p = p.next;
		}
	}

	public static void main(String[] args) {
		Polynomial polynomial = new Polynomial();
		polynomial.createPoly(polynomial.new Elem(3, 0));
		polynomial.createPoly(polynomial.new Elem(1, 2));
		polynomial.createPoly(polynomial.new Elem(2, 4));
		polynomial.print();
		Polynomial polynomial1 = new Polynomial();
		polynomial1.createPoly(polynomial1.new Elem(1, 0));
		polynomial1.createPoly(polynomial1.new Elem(1, 2));
		polynomial1.print();
		//		polynomial.add(polynomial1).print();
		polynomial.multiply(polynomial1).print();
	}

}