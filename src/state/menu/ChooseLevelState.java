package state.menu;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class ChooseLevelState extends State {

    private String[] optionsLevel;

    private static final String EASY = "Easy";
	private static final String HARD = "Hard";

	private int selected;

    public ChooseLevelState(Handler handler) {
        super(handler);
        this.selected = 0;
        this.optionsLevel = new String[] {EASY, HARD};
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().up) {
                selected = 0;
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().down) {
                selected = 1;
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().escape) {
                State.setState(handler.getGame().getMainMenuState());
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().enter) {
                if (selected == 0){
                    isPlaying = true;
                    handler.setNewGame(1);
                    State.setState(handler.getGame().getGameState());
                } else {
                    isPlaying = true;
                    handler.setNewGame(2);
                    State.setState(handler.getGame().getGameState());
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
		for(int i=0;i<this.optionsLevel.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsLevel[i], Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2 + 30*i);
		}
        
    }
    
}
