package state.gameover;

import java.awt.Graphics;

import main.Game;
import main.Handler;
import state.State;

import java.awt.Color;
import java.awt.Font;

public class PlayAgainState extends State {
    private String[] options;
    private final int numOfOptions;

    private final String QUESTION = "Do you want to play again?";
	private static final String YES = "YES";
    private static final String NO = "NO";

    private int selected;

    public PlayAgainState(Handler handler) {
        super(handler);
        
        this.options = new String[]{YES, NO};
        this.numOfOptions = 2;

        this.selected = 0;
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().up) {
                if (selected > 0){
                    selected--;
                }
                keyPressedDelayCount = 0;
                return;
            }
    
            if (handler.getKeyManager().down) {
                if (selected < numOfOptions - 1){
                    selected++;
                }
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().enter) {
                switch (selected) {
                    case 0:
                        handler.restartGame();
                        State.setState(handler.getGame().getMainMenuState());
                        break;
                    case 1:
                        System.exit(0);
                }
                keyPressedDelayCount = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));

        graphics.setColor(Color.ORANGE);
        graphics.drawString(this.QUESTION, Game.WINDOW_WIDTH / 2 - 180, Game.WINDOW_HEIGHT / 2 - 30);

		for (int i=0; i<this.options.length; i++) {
			if (i == this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.options[i], Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT / 2 + 30*(i));
		}
        
    }
    
}
