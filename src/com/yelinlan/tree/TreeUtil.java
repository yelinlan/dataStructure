package com.yelinlan.tree;

import org.junit.Test;

import java.util.function.Consumer;

/**
 *@项目名称: dataStructure
 *@类名称: BiTree
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/3/1 0:21
 **/

class BiNode<T> {
	T data;
	BiNode<T> left;
	BiNode<T> right;

	public BiNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public BiNode<T> copy() {
		return new BiNode<T>(data);
	}
}

public class TreeUtil<T> {

	public TreeUtil() {
	}

	void preOrderTraverse(BiNode<T> node, Consumer<T> visit) {
		if (node != null) {
			visit.accept(node.data);
			preOrderTraverse(node.left, visit);
			preOrderTraverse(node.right, visit);
		}
	}

	void inOrderTraverse(BiNode<T> node, Consumer<T> visit) {
		if (node != null) {
			inOrderTraverse(node.left, visit);
			visit.accept(node.data);
			inOrderTraverse(node.right, visit);
		}
	}

	void postOrderTraverse(BiNode<T> node, Consumer<T> visit) {
		if (node != null) {
			postOrderTraverse(node.left, visit);
			postOrderTraverse(node.right, visit);
			visit.accept(node.data);
		}
	}


	@Test
	public void main() {
		TreeUtil<Character> tree = new TreeUtil<>();
		BiNode<Character> root = new BiNode<>('-');
		root.left = new BiNode<>('+');
		root.right = new BiNode<>('/');
		root.left.left = new BiNode<>('a');
		root.left.right = new BiNode<>('*');
		root.right.left = new BiNode<>('e');
		root.right.right = new BiNode<>('f');

		root.left.right.left = new BiNode<>('b');
		root.left.right.right = new BiNode<>('-');
		root.left.right.right.left = new BiNode<>('c');
		root.left.right.right.right = new BiNode<>('d');
		System.out.print("先序遍历：");
		tree.preOrderTraverse(root, System.out::print);
		System.out.println();
		System.out.print("中序遍历：");
		tree.inOrderTraverse(root, System.out::print);
		System.out.println();
		System.out.print("后序遍历：");
		tree.postOrderTraverse(root, System.out::print);
		System.out.println();
	}

}