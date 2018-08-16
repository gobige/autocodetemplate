package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 访问者模式
 */
public class VisitorPattern {
    public static void main(String[] args) {
        RealComputerVisitor visitor = new RealComputerVisitor();
        Computer computer = new Computer();
        computer.accept(visitor);

    }
}

class RealComputerVisitor implements ComputerVisitor {
    @Override
    public void visit(Computer computer) {
        System.out.println("visiting computer");
    }

    @Override
    public void visit(KeyBoard keyBoard) {
        System.out.println("visiting keyBoard");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("visiting mouse");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("visiting monitor");
    }
}

class Computer implements ComputerPart {
    ComputerPart[] parts;

    Computer() {
        parts = new ComputerPart[] {new Mouse(),new Monitor(),new KeyBoard()};
    }

    @Override
    public void accept(ComputerVisitor computerVisitor) {
        for (ComputerPart part : parts) {
            part.accept(computerVisitor);
        }
        computerVisitor.visit(this);
    }
}

interface ComputerVisitor {
    void visit(Computer computer);
    void visit(KeyBoard keyBoard);
    void visit(Mouse mouse);
    void visit(Monitor monitor);
}

class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerVisitor computerVisitor) {
        computerVisitor.visit(this);
    }
}

class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerVisitor computerVisitor) {
        computerVisitor.visit(this);
    }
}

class KeyBoard implements ComputerPart {
    @Override
    public void accept(ComputerVisitor computerVisitor) {
        computerVisitor.visit(this);
    }
}

interface ComputerPart {
    void accept(ComputerVisitor computerVisitor);
}