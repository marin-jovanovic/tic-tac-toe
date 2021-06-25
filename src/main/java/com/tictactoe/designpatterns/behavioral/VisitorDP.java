package com.tictactoe.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class VisitorDP {

	interface Shape {
		void move(int x, int y);
		void draw();
		void accept(Visitor v);
	}

	class Dot implements Shape {

		@Override
		public void move(int x, int y) {

		}

		@Override
		public void draw() {

		}

		@Override
		public void accept(Visitor v) {
			v.visitDot(this);
		}
	}

	class Circle implements Shape {

		@Override
		public void move(int x, int y) {

		}

		@Override
		public void draw() {

		}

		@Override
		public void accept(Visitor v) {
			v.visitCircle(this);
		}
	}

	class Rectangle implements Shape {

		@Override
		public void move(int x, int y) {

		}

		@Override
		public void draw() {

		}

		@Override
		public void accept(Visitor v) {
			v.visitRectangle(this);
		}
	}

	class CompoundShape implements Shape {

		@Override
		public void move(int x, int y) {

		}

		@Override
		public void draw() {

		}

		@Override
		public void accept(Visitor v) {
			v.visitCompoundShape(this);
		}
	}

	interface Visitor {
		void visitDot(Dot d);
		void visitCircle(Circle c);
		void visitRectangle(Rectangle r);
		void visitCompoundShape(CompoundShape cs);
	}

	class XMLExportVisitor implements Visitor {
		@Override
		public void visitDot(Dot d) {

		}

		@Override
		public void visitCircle(Circle c) {

		}

		@Override
		public void visitRectangle(Rectangle r) {

		}

		@Override
		public void visitCompoundShape(CompoundShape cs) {

		}
	}

	class Application {
		List<Shape> allShapes = new ArrayList<>();

		void export() {
			Visitor exportVisitor = new XMLExportVisitor();

			for (Shape s: allShapes) {
				s.accept(exportVisitor);
			}
		}
	}

}
