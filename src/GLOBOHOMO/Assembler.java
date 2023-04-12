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
        int[] nums = new int[10];
        for(int i = 0; i < nums.length; i++){
            nums[i] = rand.nextInt();
        }
        int i = 0;
        while(i < 10) {
            if (theQ.lockingMechanism.tryLock()) {
                theQ.lockingMechanism.lock();
                try {
                    theQ.produce(nums[i]);
                    System.out.println("Producing " + nums[i]);
                    i++;
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Letting go for now in assembler, queue full");
                    theQ.lockingMechanism.unlock();
                    try {
                        Thread.sleep(rand.nextInt(10));
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                theQ.lockingMechanism.unlock();
            } else {
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
}
