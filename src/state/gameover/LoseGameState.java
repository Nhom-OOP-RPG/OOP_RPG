package state.gameover;

import java.awt.Color;
import java.awt.Font;
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
    	Font font = new Font("Ink Free", Font.BOLD, 30);
    	Font font1 = new Font("Copperplate Gothic Bold", Font.BOLD, 40);
    	graphics.setFont(font1);
    	if(handler.getPlayer().getLives() > 0){
    		graphics.drawImage(Asset.youDied,0,0, 20*40, 15*40, null);

            graphics.setColor(Color.YELLOW);
            graphics.drawString("You have ", Game.WINDOW_WIDTH / 2 - 240, Game.WINDOW_HEIGHT /2 - 20);
            graphics.setColor(Color.YELLOW);
            if(handler.getPlayer().getLives() > 1)
            graphics.drawString(" lives left!", Game.WINDOW_WIDTH / 2 + 40, Game.WINDOW_HEIGHT /2 - 20);
            else graphics.drawString(" live left!", Game.WINDOW_WIDTH / 2  + 40, Game.WINDOW_HEIGHT /2 - 20);
            graphics.setColor(Color.RED);
            graphics.drawString(""+ handler.getPlayer().getLives(), Game.WINDOW_WIDTH / 2 , Game.WINDOW_HEIGHT /2 - 20);
            graphics.setColor(Color.GREEN);
            graphics.drawString("Press                  to continue",Game.WINDOW_WIDTH / 2 - 270 , Game.WINDOW_HEIGHT - 250);
            graphics.setColor(Color.ORANGE);
            graphics.drawString("   Enter", Game.WINDOW_WIDTH / 2 - 140, Game.WINDOW_HEIGHT - 250);
        }
        else{
        	graphics.drawImage(Asset.loseGame,0,0, 20*40, 15*40, null);
            drawCenterString(graphics, handler.getWindowHeight() - 250, "You have no more life!", font1, Color.ORANGE);
            drawCenterString(graphics, handler.getWindowHeight(), "Press Enter to continue...", font, Color.WHITE);
    	}
    }
}

