/*
Lớp State
Dễ hiểu thì một trò chơi thường có phần Menu, Setting, màn hình Chơi, màn hình lúc Pause,...
-> các thành phần trên là các state
Đây là lớp cha abstract cho các lớp con state trên
*/

package state;

import java.awt.Graphics;

import main.Handler;

public abstract class State {
    private static State currentState = null;

    protected Handler handler;

    protected int keyPressedDelay, keyPressedDelayCount;

    public State(Handler handler){
        this.handler = handler;

        keyPressedDelay = 15;
        keyPressedDelayCount = 0;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    //Get Set
    public static State getState(){
        return currentState;
    }

    public static void setState(State state){
        currentState = state;
    }
}
