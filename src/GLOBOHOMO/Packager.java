package GLOBOHOMO;

import java.util.Random;

public class Packager implements Runnable{

    ps_queue<Integer> iQ;
    ps_queue<String> sQ;

    Packager(ps_queue<Integer> intQ, ps_queue<String> strQ){
        iQ = intQ;
        sQ = strQ;
    }

    @Override
    public void run() {
        int returnedValue;
        Random rand = new Random();
        int i = 0;
        while(i < 10) {
            if (iQ.lockingMechanism.tryLock()) {
                if (sQ.lockingMechanism.tryLock()) {
                    try {
                        returnedValue = iQ.consume();
                        System.out.println("Using " + returnedValue);
                        String pack = "<" + returnedValue + ">";
                        System.out.println("Packaging" + returnedValue);
                        sQ.produce(pack);
                        sQ.lockingMechanism.unlock();
                    } catch (Exception e) {
                        System.out.println("Holdup!");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                    iQ.lockingMechanism.unlock();
                } else {
                    iQ.lockingMechanism.unlock();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                try {
                    Thread.sleep(rand.nextInt(51) + 20);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
