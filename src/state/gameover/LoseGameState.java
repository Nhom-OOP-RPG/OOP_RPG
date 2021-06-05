package state.gameover;

import java.awt.Graphics;

import graphic.Asset;

import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class LoseGameState extends State {

    public LoseGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getPlayer().getLives() > 0) {
            if (handler.getKeyManager().enter) {
                handler.getPlayer().revive();
                handler.getPlayer().setX(360f);
                handler.getPlayer().setY(280f);
                handler.getWorld().setCurrentRoom(0);
                State.setState(handler.getGame().getGameState());
                return;
            }
                keyPressedDelayCount = 0;
        }
        else handler.getGame().getPlayAgainState().tick();
    }

    @Override
    public void render(Graphics graphics) {
        if(handler.getPlayer().getLives() > 0){
            graphics.drawImage(Asset.youDied,0,0, 20*40, 15*40, null);
            graphics.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
            graphics.setColor(Color.YELLOW);
            graphics.drawString("You have " + handler.getPlayer().getLives() + " lives left!", Game.WINDOW_WIDTH / 2 - 200, Game.WINDOW_HEIGHT /2 - 30);
        }
        else{
            handler.getGame().getPlayAgainState().render(graphics);
        }
    }
}

