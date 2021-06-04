package state;

import java.awt.Graphics;

import graphic.Asset;

import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;

public class PauseState extends State {

    private String[] optionsMenu;

    private static final String RESUME = "RESUME";
	private static final String MAIN_MENU = "MAIN MENU";
    private static final String INSTRUCTION = "HELP";

    private int selected;

    public PauseState(Handler handler) {
        super(handler);
        
        this.optionsMenu = new String[]{RESUME, INSTRUCTION, MAIN_MENU};

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
                if (selected < optionsMenu.length - 1){
                    selected++;
                }
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().enter) {
                switch (selected) {
                    case 0:
                        State.setState(handler.getGame().getGameState());
                        break;
                    case 1:
                        State.setState(handler.getGame().getInStructionState());
                        break;
                    default:
                        isPlaying = true;
                        State.setState(handler.getGame().getMainMenuState());
                }
                this.selected = 0;
                keyPressedDelayCount = 0;
                return;
            }
        }  
    }

    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(Asset.pauseGame, 0, 0, 20*40, 15*40, null);
		graphics.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 50));
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.BLUE.darker().darker());
			else graphics.setColor(Color.BLACK);
			graphics.drawString(this.optionsMenu[i], (Game.WINDOW_WIDTH - optionsMenu[i].length() * 25)  / 2 - 20, Game.WINDOW_HEIGHT /2 + 60*i - 60);
		}
    }
    
}
