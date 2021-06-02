package state;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;

public class PauseState extends State {

    private String[] optionsMenu;

    private static final String RESUME = "Resume";
	private static final String MAIN_MENU = "Main Menu";
    private static final String INSTRUCTION = "Instructions";

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
        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu[i], Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2 + 30*i);
		}
    }
    
}
