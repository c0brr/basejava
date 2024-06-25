package ru.javawebinar.basejava;

public class MainDeadLock {
    private static final SomeClass SOME_CLASS_INSTANCE_1 = new SomeClass();
    private static final SomeClass SOME_CLASS_INSTANCE_2 = new SomeClass();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> someAction(SOME_CLASS_INSTANCE_1, SOME_CLASS_INSTANCE_2));
        Thread t2 = new Thread(() -> someAction(SOME_CLASS_INSTANCE_2, SOME_CLASS_INSTANCE_1));
        startThreads(t1, t2);
        Thread.sleep(1000);
        joinThreads(t1, t2);
        System.out.println(SOME_CLASS_INSTANCE_1.getCounter() + ", " + SOME_CLASS_INSTANCE_2.getCounter());
    }

    private static void startThreads(Thread... threads) {
        for (Thread t : threads) {
            t.start();
        }
    }

    private static void joinThreads(Thread... threads) throws InterruptedException {
        for (Thread t : threads) {
            t.join();
        }
    }

    private static void someAction(SomeClass someClass1, SomeClass someClass2) {
        synchronized (someClass1) {
            for (int i = 0; i < 1_000_000; i++) {
                someClass1.incCounter();
                if (i % 500_000 == 0) {
                    synchronized (someClass2) {
                        System.out.println("Hi!");
                    }
                }
            }
        }
    }

    private static class SomeClass {
        private int counter;

        public void incCounter() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }
    }
}