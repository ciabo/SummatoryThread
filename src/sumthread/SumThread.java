/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumthread;
import java.lang.Thread;
/**
 *
 * @author Luca
 */
public class SumThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        // TODO code application logic here
        
        ThreadS.nthread=5;
        int s=0;
        int array[] = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i]=i;
        }
        ThreadS[] tss=new ThreadS[ThreadS.nthread]; //create an array of threads. It will be used to use join() since it cannot be used in the for (it would wait every time...)
        for (int i = 0; i < ThreadS.nthread; i++) {
            ThreadS ts=new ThreadS(i,array);//create a new thread
            tss[i]=ts;//add the thread in the array
            ts.start();//start the thread
        }
        
        for (int i = 0; i < tss.length; i++) {
            tss[i].join();
            s+=tss[i].getSum();
        }

        System.out.println(s);
    }
    
}

class ThreadS extends Thread{
    private int id;
    private int[] array;
    private int sum;
    public static int nthread;
    
    public int getSum()
    {
        return this.sum;
    }
    public ThreadS(int id, int[] array){
        this.id=id;
        this.array=array;
    }
    
    @Override
    public void run(){
        for (int i = id*array.length/nthread; i < (id+1)*array.length/nthread; i++) {
            sum+=array[i];
        }
    }
}