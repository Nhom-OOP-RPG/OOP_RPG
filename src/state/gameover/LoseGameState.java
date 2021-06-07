package state.gameover;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import graphic.Asset;
import main.Handler;
import state.State;

public class LoseGameState extends State {

    public LoseGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().enter) {
            if (handler.getPlayer().getLives() > 0) {
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
        int lives = handler.getPlayer().getLives();
        Font font = new Font("Copperplate Gothic Bold", Font.BOLD, 40);

        if (lives > 0){
            graphics.drawImage(Asset.youDied,0,0, 20*40, 15*40, null);
            drawCenterString(graphics, handler.getWindowHeight() / 2 - 20, "You have " + lives + " lives left!", font, Color.YELLOW);
        } else {
            graphics.drawImage(Asset.loseGame,0,0, 20*40, 15*40, null);
            drawCenterString(graphics, handler.getWindowHeight() / 2 - 20, "You have no more life!", font, Color.YELLOW);
        }
        drawCenterString(graphics, handler.getWindowHeight() - 250, "Press Enter to continue", font, Color.GREEN);
    }
}

