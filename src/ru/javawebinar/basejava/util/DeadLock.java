package ru.javawebinar.basejava.util;

public class DeadLock {
    private static final SomeClass SOME_CLASS = new SomeClass();
    private static final AnotherSomeClass ANOTHER_SOME_CLASS = new AnotherSomeClass();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (ANOTHER_SOME_CLASS) {
                ANOTHER_SOME_CLASS.incCounter();
                try {
                    ANOTHER_SOME_CLASS.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (SOME_CLASS) {
                SOME_CLASS.incCounter();
                try {
                    SOME_CLASS.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (ANOTHER_SOME_CLASS) {
                ANOTHER_SOME_CLASS.incCounter();
                try {
                    ANOTHER_SOME_CLASS.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (SOME_CLASS) {
                SOME_CLASS.incCounter();
                try {
                    SOME_CLASS.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(1000);

        t1.join();
        t2.join();

        System.out.println(SOME_CLASS.getCounter() + ", " + ANOTHER_SOME_CLASS.getCounter());
    }
}

class SomeClass {
    private int counter;

    public void incCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

class AnotherSomeClass {
    private int counter;

    public void incCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
