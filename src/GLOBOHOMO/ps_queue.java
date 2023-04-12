package GLOBOHOMO;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ps_queue<T>{

    T data[];
    public ReentrantLock lockingMechanism;
    int productionIndex;
    int consumerIndex;

    ps_queue(){
        data = (T[]) new Object[10];
        productionIndex = 0;
        consumerIndex = 0;
        lockingMechanism = new ReentrantLock();
    }

    void produce(T parm) throws Exception{

        System.out.println(productionIndex);
        System.out.println(consumerIndex);

        if(productionIndex == (consumerIndex - 1)){
            System.out.println("produce");
            throw new Exception("Queue full");
        }

        data[productionIndex] = parm;

        productionIndex = (productionIndex + 1) % ((productionIndex - consumerIndex) * -1);

    }

    T consume() throws Exception{

        if(consumerIndex == productionIndex){
            System.out.println("consume");
            throw new Exception("Queue full");
        }

        T ret = data[consumerIndex];

        consumerIndex = (consumerIndex + 1) % ((productionIndex - consumerIndex) * -1);

        return ret;

    }

}
