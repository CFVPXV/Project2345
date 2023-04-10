package GLOBOHOMO;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Assembler implements Runnable{


    ps_queue<Integer> theQ;

    Assembler(ps_queue<Integer> refTheQ){
        theQ = refTheQ;
    }

    @Override
    public void run() {
        Random rand = new Random();

        if(theQ.lockingMechanism.tryLock()){
            theQ.lockingMechanism.lock();
            try {
                theQ.produce(2);
            } catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Producing " +  2);
            theQ.lockingMechanism.unlock();
        }
        else {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try{
            Thread.sleep(rand.nextInt(50));
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("Job done!");

    }
}
