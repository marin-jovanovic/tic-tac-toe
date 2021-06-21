package com.tictactoe.designpatterns.creational;


public class BuilderDP {

	static class Car {

	}

	static class Manual {

	}

	interface Builder {
		void reset();
		void setSeats(String s);
		void setEngine(String s);
		void setTripCalculator(String s);
		void setGPS(String s);
	}

	static class CarBuilder implements Builder {
		Car car;

		CarBuilder() {
			this.reset();
		}

		@Override
		public void reset() {
			this.car = new Car();
		}

		@Override
		public void setSeats(String s) {
			System.out.println("seats " + s);
		}

		@Override
		public void setEngine(String s) {
			System.out.println("engine " + s);

		}

		@Override
		public void setTripCalculator(String s) {
			System.out.println("trip calc " + s);

		}

		@Override
		public void setGPS(String s) {
			System.out.println("gps " + s);

		}


		Car getProduct() {
			Car product = this.car;
			this.reset();
			return product;
		}
	}

	static class CarManualBuilder implements Builder {
		Manual manual;

		CarManualBuilder() {
			this.reset();
		}

		@Override
		public void reset() {
			this.manual = new Manual();
		}

		@Override
		public void setSeats(String s) {
			System.out.println("seats " + s);
		}

		@Override
		public void setEngine(String s) {
			System.out.println("engine " + s);

		}

		@Override
		public void setTripCalculator(String s) {
			System.out.println("trip calc " + s);

		}

		@Override
		public void setGPS(String s) {
			System.out.println("gps " + s);

		}

		Manual getProduct() {
			Manual product = this.manual;
			this.reset();
			return product;
		}
	}

	static class Director {
		Builder builder;

		public void setBuilder(Builder builder) {
			this.builder = builder;
		}

		void constructSportsCar(Builder builder) {
			builder.reset();
			builder.setSeats("2");
			builder.setEngine("sport engine");
			builder.setTripCalculator("true");
			builder.setGPS("true");
		}

		void constructSUV(Builder builder) {
			builder.reset();
			builder.setSeats("4");
			builder.setEngine("suv engine");
			builder.setTripCalculator("true");
			builder.setGPS("false");
		}
	}

	static class App {
		void makeCar() {
			Director director = new Director();

			CarBuilder builder = new CarBuilder();
			director.constructSportsCar(builder);
			Car car = builder.getProduct();

			CarManualBuilder manualBuilder = new CarManualBuilder();
			director.constructSportsCar(manualBuilder);

			Manual manual = manualBuilder.getProduct();
		}

		public static void main(String[] args) {
			App app = new App();
			app.makeCar();
		}
	}

}
