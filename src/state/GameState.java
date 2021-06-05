/*
Lớp GameState (kế thừa State): là phần thực hiện việc Chơi
*/

package state;

import java.awt.Graphics;

import main.Handler;

public class GameState extends State{
    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay && handler.getKeyManager().escape){
            State.setState(handler.getGame().getPauseState());
        }

        handler.getPlayer().tick();
        if (handler.getPlayer().getIsDead()){
            handler.getPlayer().decreaseLives();
            State.setState(handler.getGame().getLoseGameState());
        }
        
        handler.getWorld().changeRoom(handler.getPlayer());
        handler.getWorld().getRoom().tick();
    }

    @Override
    public void render(Graphics graphics) {
        handler.getWorld().getRoom().render(graphics);
        handler.getPlayer().render(graphics);
    }
}