package com.tictactoe.designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositeDP {

	public static void main(String[] args) {
		ImageEditor imageEditor = new ImageEditor();
		imageEditor.load();

		List<Graphic> list = new ArrayList<>();
		list.add(new Dot(2, 3));
		list.add(new Circle(2, 6, 6));

		imageEditor.groupSelected(list);
	}

	interface Graphic {
		void move(int x, int y);

		void draw();
	}

	static class Dot implements Graphic {
		int x;
		int y;

		Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void move(int x, int y) {
			this.x += x;
			this.y += y;
		}

		@Override
		public void draw() {
			System.out.println("draw dot");
		}

		@Override
		public String toString() {
			return "Dot{" +
					"x=" + x +
					", y=" + y +
					'}';
		}
	}

	static class Circle extends Dot {
		int radius;

		Circle(int x, int y, int radius) {
			super(x, y);

			this.radius = radius;
		}

		@Override
		public void draw() {
			System.out.println("draw circle");
		}

		@Override
		public String toString() {
			return "Circle{" +
					"x=" + x +
					", y=" + y +
					", radius=" + radius +
					'}';
		}
	}

	static class CompoundGraphic implements Graphic {
		List<Graphic> children = new ArrayList<>();

		void add(Graphic child) {
			children.add(child);
		}

		void remove(Graphic child) {
			children.remove(child);
		}

		@Override
		public void move(int x, int y) {
			for (Graphic child :
					children) {
				child.move(x, y);
			}
		}

		@Override
		public void draw() {
			for (Graphic child :
					children) {
				System.out.println("draw compound graphic " + child);
			}
		}

		@Override
		public String toString() {
			return "CompoundGraphic{" +
					"children=" + children +
					'}';
		}
	}

	static class ImageEditor {
		CompoundGraphic all;

		void load() {
			all = new CompoundGraphic();
			all.add(new Dot(1, 2));
			all.add(new Circle(5, 3, 10));
		}

		void groupSelected(List<Graphic> components) {
			CompoundGraphic group = new CompoundGraphic();

			for (Graphic component : components) {
				group.add(component);
				all.remove(component);
			}

			all.add(group);

			all.draw();
		}
	}

}
