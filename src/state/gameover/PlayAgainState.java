package state.gameover;

import java.awt.Graphics;

import graphic.Asset;
import main.Game;
import main.Handler;
import state.State;
import state.menu.MainMenuState;

import java.awt.Color;
import java.awt.Font;

public class PlayAgainState extends State {
    private String[] options;
    private final int numOfOptions;

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
                        isPlaying = false;
                        ((MainMenuState) handler.getGame().getMainMenuState()).removeContinueOption();
                        State.setState(handler.getGame().getMainMenuState());
                        break;
                    case 1:
                        System.exit(0);
                }
                this.selected = 0;
                keyPressedDelayCount = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Asset.loseGame,0,0, 20*40, 15*40, null);
		graphics.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
		for (int i=0; i<this.options.length; i++) {
			if (i == this.selected) graphics.setColor(Color.GREEN.darker());
			else graphics.setColor(Color.YELLOW.darker());
			graphics.drawString(this.options[i], (Game.WINDOW_WIDTH - options[i].length()*20) / 2 , Game.WINDOW_HEIGHT / 2 + 40*(i));
		}        
    }
}
