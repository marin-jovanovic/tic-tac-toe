package com.tictactoe.designpatterns.creational;

// code based on pseudo-code from https://refactoring.guru/design-patterns/singleton


public class SingletonDP {

	static class Database {
		static Database instance;
		int field;

		private Database() {
		}

		public static Database getInstance() {
			if (instance == null) {
				instance = new Database();
			}

			return instance;
		}

		public int getField() {
			return field;
		}

		public void setField(int field) {
			this.field = field;
		}
	}

	static class Application {
		public static void main(String[] args) {
			Database db1 = Database.getInstance();
			db1.setField(2);

			System.out.println(db1.getField());

			Database db2 = Database.getInstance();
			db2.setField(3);

			System.out.println(db2.getField());

			System.out.println(db1.getField());
			System.out.println(db2.getField());
		}
	}

}
