package com.tic_tac_toe.temp;

import java.util.HashMap;
import java.util.Map;

public class Command {
    public static void main(String[] args) {
        OperationsPerformer performer = new OperationsPerformer();
        performer.perform(OperationType.EASY);
    }

    enum OperationType {
        EASY,
        HARD
    }

    interface IOperations {
        void performOperations();
    }

    interface ICommand {
        void execute();
    }

    static class EasyOperations implements IOperations {
        @Override
        public void performOperations() {
            System.out.println("easy op");
        }
    }

    static class ComplexOperations implements IOperations {
        @Override
        public void performOperations() {
            System.out.println("comp operations");
        }
    }

    static class EasyCommand implements ICommand {
        IOperations operations;

        EasyCommand(IOperations operations) {
            this.operations = operations;
        }

        @Override
        public void execute() {
            operations.performOperations();
        }
    }

    static class ComplexCommand implements ICommand {
        IOperations operations;

        ComplexCommand(IOperations operations) {
            this.operations = operations;
        }

        @Override
        public void execute() {
            operations.performOperations();
        }
    }

    static class OperationsPerformer {
        Map<OperationType, ICommand> commands = new HashMap<>();

        OperationsPerformer() {
            commands.put(OperationType.EASY, new EasyCommand(new EasyOperations()));
            commands.put(OperationType.HARD, new ComplexCommand(new ComplexOperations()));
        }

        public void perform(OperationType operationType) {
            ICommand command = commands.get(operationType);
            command.execute();
        }
    }
}
