package com.tictactoe.designpatterns.structural;

public class AdapterDP {

	public static void main(String[] args) {
		RoundHole hole = new RoundHole(5);
		RoundPeg roundPeg = new RoundPeg(5);

		System.out.println(hole.fits(roundPeg));

		SquarePeg smallSquarePeg = new SquarePeg(5);
		SquarePeg largeSquarePeg = new SquarePeg(10);

//		compile error
//		System.out.println(hole.fits(smallSquarePeg));

		SquarePegAdapter smallSquarePegAdapter = new SquarePegAdapter(smallSquarePeg);
		SquarePegAdapter largeSquarePegAdapter = new SquarePegAdapter(largeSquarePeg);

		System.out.println(hole.fits(smallSquarePegAdapter));
		System.out.println(hole.fits(largeSquarePegAdapter));
	}

	static class RoundHole {
		int radius;

		RoundHole(int radius) {
			this.radius = radius;
		}

		public double getRadius() {
			return radius;
		}

		boolean fits(RoundPeg peg) {
			System.out.println(this.getRadius() + " >= " + peg.getRadius() + " ?");
			return this.getRadius() >= peg.getRadius();
		}
	}

	static class RoundPeg {
		int radius;

		RoundPeg(int radius) {
			this.radius = radius;
		}

		public double getRadius() {
			return radius;
		}
	}

	static class SquarePeg {
		int width;

		SquarePeg(int width) {
			this.width = width;
		}

		public int getWidth() {
			return width;
		}
	}

	static class SquarePegAdapter extends RoundPeg {
		SquarePeg peg;

		SquarePegAdapter(SquarePeg peg) {
			super(peg.getWidth());
			this.peg = peg;
		}

		@Override
		public double getRadius() {
			return peg.getWidth() * Math.sqrt(2) / 2;
		}

	}
}
