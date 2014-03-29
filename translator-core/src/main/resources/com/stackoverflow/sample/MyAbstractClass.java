package com.stackoverflow.sample;

public abstract class MyAbstractClass {
    public abstract void a();

    public abstract void b();
}

class foo extends MyAbstractClass {
    public void a() {
        System.out.println("hello");
    }

    public void b() {
        System.out.println("bye");
    }
}

class bar extends MyAbstractClass {
    public void a() {
        System.out.println("hello");
    }

    public void delta() {
        System.out.println("gamma");
    }
}