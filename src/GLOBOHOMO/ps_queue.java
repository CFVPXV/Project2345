package GLOBOHOMO;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ps_queue<T>{

    public T data[];
    public static ReentrantLock lockingMechanism;
    int productionIndex;
    int consumerIndex;
    int size;

    ps_queue(){
        data = (T[]) new Object[10];
        productionIndex = 0;
        consumerIndex = 0;
        size = 0;
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
        size++;
        productionIndex = (productionIndex + 1) % size;

    }

    T consume() throws Exception{

        if(consumerIndex == productionIndex){
            System.out.println("consume");
            throw new Exception("Queue full");
        }

        T ret = data[consumerIndex];
        size--;
        consumerIndex = (consumerIndex + 1) % size;

        return ret;

    }

}
