package com.tictactoe.designpatterns;

// code based on pseudo-code from https://refactoring.guru/design-patterns/observer

public class StrategyDP {
	public static void main(String[] args) {
		StrategyDP strategyDP = new StrategyDP();
		strategyDP.launcher();
	}

	void launcher() {
		Context context = new Context();

		int a = 2;
		int b = 3;

		Strategy action = new ConcreteStrategyAdd();

		context.setStrategy(action);

		int result = context.executeStrategy(a, b);

		System.out.println(result);
	}

	interface Strategy {
		int execute(int a, int b);
	}

	class ConcreteStrategyAdd implements Strategy {
		@Override
		public int execute(int a, int b) {
			return a + b;
		}
	}

	class ConcreteStrategySubtract implements Strategy {
		@Override
		public int execute(int a, int b) {
			return a - b;
		}
	}

	class ConcreteStrategyMultiply implements Strategy {
		@Override
		public int execute(int a, int b) {
			return a * b;
		}
	}

	class Context {
		Strategy strategy;

		public void setStrategy(Strategy strategy) {
			this.strategy = strategy;
		}

		int executeStrategy(int a, int b) {
			return strategy.execute(a, b);
		}
	}

}
