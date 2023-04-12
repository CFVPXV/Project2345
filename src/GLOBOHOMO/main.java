package GLOBOHOMO;

public class main {

    public static void main(String[] args){

        ps_queue<String> sQueue = new ps_queue<>();
        ps_queue<Integer> iQueue = new ps_queue<>();

        Thread[] factory = new Thread[3];

        factory[0] = new Thread(new Assembler(iQueue));
        factory[1] = new Thread(new Packager(iQueue, sQueue));
        factory[2] = new Thread(new Shipper(sQueue));

        factory[0].start();
        factory[1].start();
        factory[2].start();

        try {
            factory[0].join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            factory[1].join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            factory[2].join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        /*for(int i = 0; i < factory.length; i++){
            factory[i].start();
        }
        for(int i =0; i < factory.length; i ++) {
            try
            {
                factory[i].join();
            }
            catch(Exception e)
            {
                System.out.println("Thread exception: "+e.getMessage());
            }
        }*/

    }
}
