package state.gameover;

import java.awt.Graphics;

import graphic.Asset;
import main.Game;
import main.Handler;
import state.State;

public class LoseGameState extends State {

    public LoseGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().enter){
            if (handler.getPlayer().getLives() > 0){
                handler.getPlayer().revive();
                handler.getPlayer().setX(360f);
                handler.getPlayer().setY(280f);
                handler.getWorld().setCurrentRoom(0);
                State.setState(handler.getGame().getGameState());
            } else {
                State.setState(handler.getGame().getPlayAgainState());
            }

            keyPressedDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Asset.youDied,0,0, 20*40, 15*40, null);
        drawCenterString(graphics, Game.WINDOW_HEIGHT /2 + 30, "You have " + handler.getPlayer().getLives() + " lives left!", primaryFont, fontColor);
    }
}

