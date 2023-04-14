package projQueue;

import projQueue.ps_queue;

public class Shipper implements Runnable{

    ps_queue<String> sQ;

    Shipper(ps_queue<String> strQ){
        sQ = strQ;
    }


    @Override
    public void run() {
        String stringEater;
        int i = 0;
        while(i < 10) {
            if (sQ.lockingMechanism.tryLock()) {
                try {
                    stringEater = sQ.consume();
                    System.out.println("Shipping " + stringEater);
                    sQ.lockingMechanism.unlock();
                    i++;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sQ.lockingMechanism.unlock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            else{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
