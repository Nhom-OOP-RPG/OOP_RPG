//Class tinh thoi gian mot vong lap (Game.run())
//KHONG CAN QUAN TAM

package main.fps;

public class FPSTimer {
 
    private double timePerTick;
    private double delta;
    private long now;
    private long lastTime;
    
    public FPSTimer(int fps){
        timePerTick = 1e9 / fps;
        delta = 0;
        lastTime = System.nanoTime();
    }

    public boolean check(){
        now = System.nanoTime();
        delta = (now - lastTime) / timePerTick;
    
        if (delta >= 1){
            lastTime = now;
            return true;
        } else return false;
    }
}