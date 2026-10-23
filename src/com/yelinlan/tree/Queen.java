package com.yelinlan.tree;

import java.util.Arrays;

/**
 *@项目名称: dataStructure
 *@类名称: Queen
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/3/9 16:58
 **/
public class Queen {

	int[] a;
	int n;
	int count;

	public Queen(int n) {
		a = new int[n];
		this.n = n;
		for (int i = 0; i < n; i++) {
			a[i] = -1;
		}
		travel(0);
	}

	void travel(int row) {
		if (row == n) {
			print(a);
			return;
		}
		for (int i = 0; i < n; i++) {
			a[row] = i;
			if (canPos(row, i)) {
				travel(row + 1);
			}
			a[row] = -1;
		}
	}

	private boolean canPos(int row, int i) {
		for (int j = 0; j < row; j++) {
			if (a[j] == i || Math.abs(row - j) == Math.abs(a[row] - a[j])) {
				return false;
			}
		}
		return true;
	}

	private void print(int[] a) {
		System.out.println("===========第" + (++count) + "种解法=========");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i] == j) {
					System.out.print("@  ");
				} else {
					System.out.print("|  ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Queen queen = new Queen(8);
	}

}