package state;

import java.awt.Graphics;

import main.Game;
import main.Handler;

import java.awt.Color;
import java.awt.Font;

public class MainMenuState extends State{

    private String[] optionsMenu;

    private static final String START_GAME = "Start Game";
	private static final String QUIT_GAME = "Quit Game";

	private int selected;

    //private Game game = new Game();
    public MainMenuState(Handler handler) {

        super(handler);
        this.optionsMenu = new String[] {START_GAME, QUIT_GAME};
		this.selected = 0;
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().up) {
                selected = 0;
                return;
            }
    
            if (handler.getKeyManager().down) {
                selected = 1;
                return;
            }
    
            if (handler.getKeyManager().enter) {
                if (selected == 0){
                    State.setState(handler.getGame().getChooseLevelState());
                } else {
                    System.exit(0);
                }
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
