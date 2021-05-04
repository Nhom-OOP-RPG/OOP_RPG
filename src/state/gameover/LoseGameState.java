package state.gameover;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class LoseGameState extends State {
    
    private String[] options;

    private static final String LOSE_GAME = "You Lose!";
    private static final String CONTINUE = "Press 1 to continue!";

    public LoseGameState(Handler handler) {
        super(handler);
        this.options = new String[] {LOSE_GAME, CONTINUE};
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().enter) {
            State.setState(handler.getGame().getPlayAgainState());
        }
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
        for(int i=0;i<this.options.length;i++) {
			if(i==1) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.options[i], Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2 + 30*i);
		}
    }
}

