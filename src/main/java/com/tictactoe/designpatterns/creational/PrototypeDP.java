package com.tictactoe.designpatterns.creational;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class PrototypeDP {

	static abstract class Shape {
		int x;
		int y;
		String color;

		Shape(Shape  target) {
			if (target != null) {
				this.x = target.x;
				this.y = target.y;
				this.color = target.color;
			}
		}

		public abstract Shape clone();
	}

	static class Rectangle extends Shape {
		int width;
		int height;

		Rectangle(Rectangle target) {
			super(target);

			if (target != null) {
				this.width = target.width;
				this.height = target.height;
			}
		}

		@Override
		public Shape clone() {
			return new Rectangle(this);
		}

		@Override
		public String toString() {
			return "Rectangle{" +
					"x=" + x +
					", y=" + y +
					", color='" + color + '\'' +
					", width=" + width +
					", height=" + height +
					'}';
		}
	}

	static class Circle extends Shape {
		int radius;

		Circle(Circle target) {
			super(target);

			if (target != null) {
				this.radius = target.radius;
			}

		}

		@Override
		public Shape clone() {
			return new Circle(this);
		}

		@Override
		public String toString() {
			return "Circle{" +
					"x=" + x +
					", y=" + y +
					", color='" + color + '\'' +
					", radius=" + radius +
					'}';
		}
	}


	static class Application {
		Shape[] shapes;

		Application() {
			shapes = new Shape[5];

			Circle circle = new Circle(null);
			circle.x = 5;
			circle.y = 4;
			circle.radius = 6;
			shapes[0] = circle;

			Shape anotherCircle = circle.clone();
			shapes[1] = anotherCircle;

			Rectangle rectangle = new Rectangle(null);
			rectangle.width = 210;
			rectangle.height = 4;
			shapes[2] = rectangle;

			for (Shape s: shapes
				 ) {
				System.out.println(s);
			}

		}

		void businessLogic() {
			Shape[] shapesCopy = new Shape[5];

			shapesCopy[0] = shapes[0].clone();
			shapesCopy[1] = shapes[1].clone();
			shapesCopy[2] = shapes[2].clone();

			for (Shape s: shapesCopy
			) {
				System.out.println(s);
			}

		}

		public static void main(String[] args) {
			Application application = new Application();

			System.out.println();

			application.businessLogic();


		}
	}

}
