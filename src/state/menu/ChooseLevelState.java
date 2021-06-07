package state.menu;

import java.awt.Graphics;
import java.awt.Color;

import main.Handler;
import state.State;

public class ChooseLevelState extends State {

    private String[] options;

    private static final String DEMO = "DEMO";
    private static final String EASY = "EASY";
	private static final String HARD = "HARD";

	private int selected;

    public ChooseLevelState(Handler handler) {
        super(handler);
        this.selected = 0;
        this.options = new String[] {DEMO, EASY, HARD};
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
                themeID = 0;
                this.selected = 0;
                keyPressedDelayCount = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(backGround[themeID], 0, 0, 20*40, 15*40, null);
        drawMenuBox(graphics);
        drawTitleBox(graphics, "Choose Level");
		
		graphics.setFont(primaryFont);
		for(int i=0;i<this.options.length;i++) {
			if(i==this.selected){
                drawSelectedString(graphics, 290 + 60*i, this.options[i]);
            } else {
                drawCenterString(graphics, 290 + 60*i, this.options[i], primaryFont, Color.WHITE);
            }
		}
        
    }
    
}
