package GLOBOHOMO;

public class Shipper implements Runnable{

    ps_queue<String> sQ;

    Shipper(ps_queue<String> strQ){
        sQ = strQ;
    }


    @Override
    public void run() {
        String stringEater;
        if(sQ.lockingMechanism.tryLock()){
            try {
                stringEater = sQ.consume();
                System.out.println("Shipping " + stringEater);
                sQ.lockingMechanism.unlock();
            } catch (Exception e) {
                System.out.println("Holdup!");
                System.out.println(e);
                sQ.lockingMechanism.unlock();
            }
        }
        else{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
