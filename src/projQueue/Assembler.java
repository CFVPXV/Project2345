package projQueue;

import java.util.Random;

public class Assembler implements Runnable{
    ps_queue<Integer> theQ;

    Assembler(ps_queue<Integer> refTheQ){
         theQ = refTheQ;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(100);
        }
        int i = 0;
        while (i < 10) {
            if (theQ.lockingMechanism.tryLock()) {
                try {
                    theQ.produce(nums[i]);
                    System.out.println("Producing " + nums[i]);
                    theQ.lockingMechanism.unlock();
                    i++;
                    try {
                        Thread.sleep(rand.nextInt(500));
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Job done!");
                } catch (InterruptedException e) {
                    theQ.lockingMechanism.unlock();
                    System.out.println(e.getMessage());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

}

