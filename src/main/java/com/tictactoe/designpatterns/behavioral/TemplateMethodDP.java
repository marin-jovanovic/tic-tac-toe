package com.tictactoe.designpatterns.behavioral;

public class TemplateMethodDP {

	public abstract class GameAI {

		// The template method defines the skeleton of an algorithm.
		void turn() {

			collectResources();
			buildStructures();
			buildUnits();
			attack();
		}

		// Some of the steps may be implemented right in a base
		// class.
		void collectResources() {

//			foreach(s in this.builtStructures) {
//				s.collect();
//
//			}
		}

		// And some of them may be defined as abstract.
		abstract void buildStructures();

		abstract void buildUnits();

		// A class can have several template methods.
		void attack() {

			Enemy enemy = closestEnemy();

			if (enemy == null) {
//				sendScouts(map.center);
			} else {
//				sendWarriors(enemy.position);
			}

		}

		protected abstract Enemy closestEnemy();

		abstract void sendScouts(int position);

		abstract void sendWarriors(int position);

		class Enemy {
		}

	}

	// Concrete classes have to implement all abstract operations of
// the base class but they must not override the template method
// itself.
	class OrcsAI extends GameAI {

		void buildStructures() {
			boolean thereAreSomeResources = false;
			if (thereAreSomeResources) {
				// Build farms, then barracks, then stronghold.
			}

		}

		void buildUnits() {
			boolean thereArePlentyOfResources = false;

			if (thereArePlentyOfResources) {

				boolean thereAreNoScouts = false;
				if (thereAreNoScouts) {
					// Build peon, add it to scouts group.

				} else {
					// Build grunt, add it to warriors group.

				}

				// ...
			}

		}

		@Override
		protected Enemy closestEnemy() {
			return null;
		}

		void sendScouts(int position) {
//			if (scouts.length > 0) {
//				// Send scouts to position.
//
//			}
		}

		void sendWarriors(int position) {
//			if (warriors.length > 5) {
//				// Send warriors to position.
//
//			}

		}


	}


	// Subclasses can also override some operations with a default
// implementation.
	class MonstersAI extends GameAI {

		void collectResources() {
			// Monsters don't collect resources.

		}

		void buildStructures() {
			// Monsters don't build structures.
		}

		void buildUnits() {
			// Monsters don't build units.

		}

		@Override
		protected Enemy closestEnemy() {
			return null;
		}

		@Override
		void sendScouts(int position) {

		}

		@Override
		void sendWarriors(int position) {

		}


	}

}
