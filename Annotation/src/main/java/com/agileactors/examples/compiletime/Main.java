package com.agileactors.examples.compiletime;

import com.agileactors.annotation.processor.CompilerAnnotation;

public class Main {

    public static void main(String[] args) {

        Object mammal = new Mammal();
        Object human = new Human();
        Object dolphin = new Dolphin();

        System.err.println(mammal);
        System.err.println(human);
        System.err.println(dolphin);
    }
}

class Mammal {
    @Override
    public String toString() {
        return "Mammal";
    }
}

class Human extends Mammal {
    public String toString() {
        return "Human";
    }
}


class Dolphin extends Mammal {


    public String annotationsAreAwesome() {
        return "Dolphin";
    }

    @CompilerAnnotation
    public String toStrings() {
        return "Dolphin";
    }
}