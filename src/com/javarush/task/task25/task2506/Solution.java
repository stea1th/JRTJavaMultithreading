package com.javarush.task.task25.task2506;

/* 
Мониторинг состояния нити
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread();
        LoggingStateThread loggingStateThread = new LoggingStateThread(target);


        loggingStateThread.start();
        Thread.sleep(1000);
        target.start();  //NEW
        Thread.sleep(1000); //RUNNABLE
        target.join(1000);
        Thread.sleep(4000);
        target.interrupt(); //TERMINATED
        Thread.sleep(5000);
    }


}

class LoggingStateThread extends Thread{
    private Thread target;
    public LoggingStateThread(Thread target) {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state = target.getState();
        System.out.println(state);
        do{
            if(!state.equals(target.getState())){
                if(!state.equals(State.TERMINATED)) {
                    state = target.getState();
                    System.out.println(state);
                }
            }
        }while(!state.equals(State.TERMINATED));
        //System.out.println(State.TERMINATED);
    }
}