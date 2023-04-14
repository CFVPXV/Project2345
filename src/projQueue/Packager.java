package projQueue;

import java.util.Random;

public class Packager implements Runnable {

    ps_queue<Integer> iQ;
    ps_queue<String> sQ;

    Packager(ps_queue<Integer> intQ, ps_queue<String> strQ) {
        iQ = intQ;
        sQ = strQ;
    }

    @Override
    public void run() {
        int returnedValue;
        Random rand = new Random();
        int i = 0;
        while (i < 10) {
            if (iQ.lockingMechanism.tryLock()) {
                if (sQ.lockingMechanism.tryLock()) {
                    try {
                        returnedValue = iQ.consume();
                        System.out.println("Using " + returnedValue);
                        String pack = "<" + returnedValue + ">";
                        System.out.println("Packaging " + returnedValue);
                        sQ.produce(pack);
                        i++;
                    }

                    catch (InterruptedException e){
                        sQ.lockingMechanism.unlock();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    catch (Exception e) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                    sQ.lockingMechanism.unlock();
                    iQ.lockingMechanism.unlock();
                } else {
                    iQ.lockingMechanism.unlock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    Thread.sleep(rand.nextInt(501) + 200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

