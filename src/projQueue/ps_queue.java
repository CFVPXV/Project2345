package projQueue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ps_queue<T>{

    public T data[];
    public Lock lockingMechanism;
    int productionIndex;
    int consumerIndex;
    int size;

    ps_queue(){
        data = (T[]) new Object[10];
        productionIndex = 0;
        consumerIndex = 0;
        lockingMechanism = new ReentrantLock();
    }

    void produce(T parm) throws InterruptedException{

        if(productionIndex == (consumerIndex - 1)){
            throw new InterruptedException("Queue full");
        }

        data[productionIndex] = parm;
        productionIndex = (productionIndex + 1) % data.length;

    }

    T consume() throws Exception{

        if(consumerIndex == productionIndex){
            throw new Exception("Queue empty");
        }

        T ret = data[consumerIndex];
        consumerIndex = (consumerIndex + 1) % data.length;

        return ret;

    }

}
