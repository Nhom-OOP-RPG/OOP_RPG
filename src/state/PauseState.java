//trạng thái tạm dừng giữa trò chơi

package state;

import java.awt.Graphics;
import java.awt.Color;

import main.Handler;

public class PauseState extends State {

    private String[] options;

    private static final String RESUME = "RESUME";
	private static final String MAIN_MENU = "MAIN MENU";
    private static final String INSTRUCTION = "HELP";

    private int selected;

    public PauseState(Handler handler) {
        super(handler);
        
        this.options = new String[]{RESUME, INSTRUCTION, MAIN_MENU};

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
                if (selected < options.length - 1){
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
        graphics.drawImage(backGround[themeID], 0, 0, 20*40, 15*40, null);
        drawMenuBox(graphics);
        drawTitleBox(graphics, "Pause");
		
		graphics.setFont(primaryFont);
		for (int i = 0; i < this.options.length; i++){
			if (i == this.selected){
                drawSelectedString(graphics, 290 + 60*i, this.options[i]);
            } else {
                drawCenterString(graphics, 290 + 60*i, this.options[i], primaryFont, Color.WHITE);
            }
		}
    }
    
}
