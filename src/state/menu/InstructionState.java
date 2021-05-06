package state.menu;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class InstructionState extends State{

    private String[] options;

    private static final String S1 = "Press > or D to go right";
    private static final String S2 = "Press < or A to go left";
    private static final String S3 = "Press ^ or W to go up";
    private static final String S4 = "Press v or S to go right";
    private static final String S5 = "Press Space to attack";
    private static final String S6 = "Press J to change weapon";

    private static final String S7 = "Press Enter to back to Main Menu";
    
    public InstructionState(Handler handler) {
        super(handler);
        this.options = new String[] {S1, S2, S3, S4, S5, S6};
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().enter || handler.getKeyManager().escape) {
                State.setState(handler.getGame().getMainMenuState());
                keyPressedDelayCount = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
        graphics.setColor(Color.WHITE);
        graphics.drawString(S7, 0, Game.WINDOW_HEIGHT);
		for(int i=0 ; i<this.options.length ; i++) {
			graphics.setColor(Color.GREEN);
            graphics.drawString(this.options[i], Game.WINDOW_WIDTH / 2 - 160, Game.WINDOW_HEIGHT /2 - 120 + 30*i);
            
		}
        
        
    }


}
