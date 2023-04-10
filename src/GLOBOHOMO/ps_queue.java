package GLOBOHOMO;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ps_queue<T>{

    T data[];
    public ReentrantLock lockingMechanism;
    int productionIndex;
    int consumerindex;

    ps_queue(){
        data = (T[]) new Object[10];
        productionIndex = 0;
        consumerindex = 0;
    }

    void produce(T parm) throws Exception{

        if(productionIndex == consumerindex - 1){
            throw new Exception("Queue full");
        }

        data[productionIndex] = parm;

        consumerindex = (consumerindex + 1) % 10;

    }

    T consume() throws Exception{

        if(consumerindex == productionIndex){
            throw new Exception("Queue full");
        }

        T ret = data[consumerindex];

        consumerindex = (consumerindex + 1) % 10;

        return ret;

    }

}
