package state.menu;

import java.awt.Graphics;

import graphic.Asset;

import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class ChooseLevelState extends State {

    private String[] optionsLevel;

    private static final String DEMO = "Demo";
    private static final String EASY = "Easy";
	private static final String HARD = "Hard";

	private int selected;

    public ChooseLevelState(Handler handler) {
        super(handler);
        this.selected = 0;
        this.optionsLevel = new String[] {DEMO, EASY, HARD};
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().up) {
                if (selected > 0) selected--;
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().down) {
                if (selected < 2) selected++;
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
                    handler.setNewGame(0);
                    State.setState(handler.getGame().getGameState());
                } else if (selected == 1){
                    isPlaying = true;
                    handler.setNewGame(1);
                    State.setState(handler.getGame().getGameState());
                } else {
                    isPlaying = true;
                    handler.setNewGame(2);
                    State.setState(handler.getGame().getGameState());
                }
                this.selected = 0;
                keyPressedDelayCount = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {

		graphics.drawImage(Asset.chooseLevel, 0, 0, 20*40, 15*40, null);
		graphics.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 50));
		for(int i=0;i<this.optionsLevel.length;i++) {
			if(i==this.selected) graphics.setColor(Color.BLUE);
			else graphics.setColor(Color.BLACK);
			graphics.drawString(this.optionsLevel[i], (Game.WINDOW_WIDTH - optionsLevel[i].length() * 25)  / 2 - 20, Game.WINDOW_HEIGHT /2 + 60*i);
		}
        
    }
    
}
