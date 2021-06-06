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
<<<<<<< HEAD
        if(handler.getPlayer().getLives() > 0){
            graphics.drawImage(Asset.youDied,0,0, 20*40, 15*40, null);
            graphics.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
            graphics.setColor(Color.YELLOW);
            graphics.drawString("You have ", Game.WINDOW_WIDTH / 2 - 200, Game.WINDOW_HEIGHT /2 + 20);
            graphics.setColor(Color.YELLOW);
            if(handler.getPlayer().getLives() > 1)
            graphics.drawString(" lives left!", Game.WINDOW_WIDTH / 2 + 10, Game.WINDOW_HEIGHT /2 + 20);
            else graphics.drawString(" live left!", Game.WINDOW_WIDTH / 2  + 10, Game.WINDOW_HEIGHT /2 + 20);
            graphics.setColor(Color.RED);
            graphics.drawString(""+ handler.getPlayer().getLives(), Game.WINDOW_WIDTH / 2 - 20, Game.WINDOW_HEIGHT /2 + 20);
            drawCenterString(graphics, Game.WINDOW_HEIGHT - 200, "Press Enter to continue", new Font("Copperplate Gothic Bold", Font.BOLD, 30), Color.GREEN);
        }
        else{
            handler.getGame().getPlayAgainState().render(graphics);
        }
=======
        graphics.drawImage(Asset.youDied,0,0, 20*40, 15*40, null);
        drawCenterString(graphics, Game.WINDOW_HEIGHT /2 + 30, "You have " + handler.getPlayer().getLives() + " lives left!", primaryFont, fontColor);
>>>>>>> 9c1b05af631e4b0e174ff841a37df591a791b14e
    }
}

