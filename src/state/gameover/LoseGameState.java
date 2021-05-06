package state.gameover;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class LoseGameState extends State {

    private static final String LOSE_GAME = "You Died!";
    private static final String CONTINUE = "Press Enter to continue!";

    public LoseGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().enter) {
            if (handler.getPlayer().getLives() > 0) {
                handler.getPlayer().setMaxHealth();
                State.setState(handler.getGame().getGameState());
                return;
            }
            State.setState(handler.getGame().getPlayAgainState());
            keyPressedDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
        graphics.setColor(Color.WHITE);
        graphics.drawString(LOSE_GAME, Game.WINDOW_WIDTH / 2 - 100, Game.WINDOW_HEIGHT /2);
        graphics.drawString("You have " + handler.getPlayer().getLives() + " lives left!", Game.WINDOW_WIDTH / 2 - 100, Game.WINDOW_HEIGHT /2 + 30);
        graphics.setColor(Color.GREEN);
        graphics.drawString(CONTINUE, Game.WINDOW_WIDTH / 2 - 100, Game.WINDOW_HEIGHT /2 + 60);
    }
}

