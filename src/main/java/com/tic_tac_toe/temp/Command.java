package com.tic_tac_toe.temp;

import java.util.HashMap;
import java.util.Map;

public class Command {
    interface IOperations {
        void performOperations();
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
    interface ICommand {
        void execute();
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
    enum OperationType {
        EASY,
        HARD
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

    public static void main(String[] args) {
        OperationsPerformer performer = new OperationsPerformer();
        performer.perform(OperationType.EASY);
    }
}
