/*
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



// 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求：AA打印5次，BB打印10次，CC打印15次....来10轮
 class  ShareResource2{

    private int number = 0; //A:0 B:1 C:2
    private int count = 5;
    int curNumber;
    private Lock lock = new ReentrantLock();
    private Condition c0 = lock.newCondition();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void print(int curNum,int nextNum,Condition curDition,Condition nextDition){
        lock.lock();
        try {
            curNumber = number % 3;// 0 1 2

            //判断
            while(curNumber!=curNum){
                curDition.await();
            }

            //干活
            count = (curNumber==0)?5:(curNumber==1)?10:15;

            for (int i = 1; i <= count; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            // 通知
            nextDition.signal();
            number++;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


@SpringBootTest
public class ReentrantLock2 {
    ShareResource2 shareResource = new ShareResource2();
    private int number = 0; //A:0 B:1 C:2
    private int count = 5;
    int curNumber;
    private Lock lock = new ReentrantLock();
    private Condition c0 = lock.newCondition();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void print(int curNum,int nextNum,Condition curDition,Condition nextDition){
        lock.lock();
        try {
            curNumber = number % 3;// 0 1 2

            //判断
            while(curNumber!=curNum){
                curDition.await();
            }

            //干活
            count = (curNumber==0)?5:(curNumber==1)?10:15;

            for (int i = 1; i <= count; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            // 通知
            nextDition.signal();
            number++;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        ReentrantLock2 shareResource = new ReentrantLock2();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print(0,c0,c1);
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print(1,shareResource.c1,shareResource.c2);
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print(2,shareResource.c2,shareResource.c0);
            }
        }, "CC").start();

    }


}

*/
