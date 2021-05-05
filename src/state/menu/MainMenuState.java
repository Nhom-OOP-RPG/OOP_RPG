package state.menu;

import java.awt.Graphics;

import main.Game;
import main.Handler;
import state.State;

import java.awt.Color;
import java.awt.Font;

public class MainMenuState extends State {
    private String[] optionsMenu;
    private final int numOfOptions;

    private static final String START_GAME = "Start Game";
	private static final String QUIT_GAME = "Quit Game";
    private static final String INSTRUCTION = "Instructions";

    private int selected;

    public MainMenuState(Handler handler) {
        super(handler);
        
        this.optionsMenu = new String[]{START_GAME,INSTRUCTION, QUIT_GAME};
        this.numOfOptions = 3;

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
                        State.setState(handler.getGame().getChooseLevelState());
                        break;
                    case 1:
                        State.setState(handler.getGame().getInStructionState());
                        break;
                    default:
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
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu[i], Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2 + 30*i);
		}
    }
    
}