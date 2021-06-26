package com.tictactoe.designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

public class FlyweightDP {

	public static void main(String[] args) {

	}

	static class TreeType {
		String name;
		String color;
		String texture;

		TreeType(String name, String color, String texture) {
			this.name = name;
			this.color = color;
			this.texture = texture;
		}

		void draw(String canvas, int x, int y) {
			System.out.println("TreeType; draw; ");
		}

	}

	static class TreeFactory {

		static List<TreeType> treeTypes = new ArrayList<>();

		/**
		 * wrong implementation, used for demonstrative purposes
		 *
		 * @param name
		 * @param color
		 * @param texture
		 * @return
		 */
		static public TreeType getTreeType(String name, String color, String texture) {
//			if not present create it, if present return it
//			idea is that these objects are common and Forest uses them

			if (treeTypes.contains(new TreeType(name, color, texture))) {
				System.out.println("contains");

			} else {
				treeTypes.add(new TreeType(name, color, texture));
			}

			return new TreeType(name, color, texture);

		}
	}

	class Tree {
		int x;
		int y;

		TreeType type;

		Tree(int x, int y, TreeType type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		void draw(String canvas) {
			type.draw(canvas, this.x, this.y);
		}
	}

	class Forest {
		List<Tree> trees;

		void plantTree(int x, int y, String name, String color, String texture) {
			TreeType type = TreeFactory.getTreeType(name, color, texture);

			Tree tree = new Tree(x, y, type);
			trees.add(tree);

		}

		void draw(String canvas) {
			for (Tree tree : trees) {
				tree.draw(canvas);
			}
		}
	}

}
