package com.yelinlan.linearlist;

import java.util.function.Consumer;

/**
 *@项目名称: dataStructure
 *@类名称: Maze
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/24 21:47
 **/
public class Maze {

	class PosType {
		private int x;
		private int y;

		public PosType(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			PosType posType = (PosType) o;
			return x == posType.x && y == posType.y;
		}

		PosType copy() {
			return new PosType(this.x, this.y);
		}

		void next(int dir) {
			switch (dir) {
				//东西南北
				case 1:
					this.x++;
					break;
				case 2:
					this.y++;
					break;
				case 3:
					this.x--;
					break;
				case 4:
					this.y--;
					break;
				default:
					break;
			}

		}

	}

	static class Elem {
		private PosType posType;
		private int dir;

		public Elem(PosType posType, int dir) {
			this.posType = posType;
			this.dir = dir;
		}

	}

	private int[][] m;
	private PosType start;
	private PosType end;

	public Maze() {
	}

	public Maze(int[][] m, int start_x, int start_y, int end_x, int end_y) {
		this.m = m;
		this.start = new PosType(start_x, start_y);
		this.end = new PosType(end_x, end_y);
	}

	void traverse(Consumer<Integer> visit) {
		System.out.println("============maze============");
		for (int k = 0; k < m.length; k++) {
			int[] r = m[k];
			for (int j = 0; j < r.length; j++) {
				int i = r[j];
				PosType posType = new PosType(k, j);
				if (posType.equals(start)) {
					System.out.print("S  ");
				} else if (posType.equals(end)) {
					System.out.print("E  ");
				} else {
					visit.accept(i);
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int len = 10;
		int[][] m = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (i == 0 || j == 0 || i == len - 1 || j == len - 1) {
					m[i][j] = 1;
				}
			}
		}
		m[1][3] = 1;
		m[1][7] = 1;
		m[2][3] = 1;
		m[2][7] = 1;
		m[3][5] = 1;
		m[3][6] = 1;
		m[4][2] = 1;
		m[4][3] = 1;
		m[4][4] = 1;
		m[5][4] = 1;
		m[6][2] = 1;
		m[6][6] = 1;
		m[7][2] = 1;
		m[7][3] = 1;
		m[7][4] = 1;
		m[7][6] = 1;
		m[7][7] = 1;
		m[8][1] = 1;

		Maze maze = new Maze(m, 1, 1, 8, 8);
		Custom_Stack<Elem> stack = new Custom_Stack<>();
		PosType current = maze.start.copy();
		int num = 1;
		do {
			if (m[current.x][current.y] == 0) {
				m[current.x][current.y] = 2;
				Elem elem = new Elem(current.copy(), 1);
				stack.push(elem);
				if (current.equals(maze.end)) {
					System.out.println("第" + (num++) + "种解法, 走了" + (stack.length - 1) + "步");
					maze.traverse(Maze::print);
				}
				current.next(elem.dir++);
			} else {
				if (!stack.empty()) {
					Elem pop = stack.pop();
					while (pop.dir > 4 && !stack.empty()) {
						//3.打印尝试过的路径 0.打印所有解
						//m[pop.posType.x][pop.posType.y] = 3;
						m[pop.posType.x][pop.posType.y] = 0;
						pop = stack.pop();
					}
					if (pop.dir <= 4) {
						stack.push(pop);
						current = pop.posType.copy();
						current.next(pop.dir++);
					}
				}
			}
		} while (!stack.empty());

	}

	private static void print(Integer p) {
		if (p == 0) {
			System.out.print("   ");
		}
		if (p == 1) {
			System.out.print("#  ");
		}
		if (p == 2) {
			System.out.print("!  ");
		}
		if (p == 3) {
			System.out.print("♡  ");
		}
	}

}