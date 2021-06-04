package state.menu;

import java.awt.Graphics;

import graphic.Asset;

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
    private static final String S4 = "Press v or S to go down";
    private static final String S5 = "Press Space to attack";
    private static final String S6 = "Press K to ultimate attack";
    private static final String S7 = "Press J to change weapon";

    private static final String S8 = "Press Enter to back to Main Menu";
    
    public InstructionState(Handler handler) {
        super(handler);
        this.options = new String[] {S1, S2, S3, S4, S5, S6, S7};
    }

    @Override
    public void tick() {
        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().enter || handler.getKeyManager().escape) {
                State.setState(State.getPreviousState());
                keyPressedDelayCount = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {

		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
        for (int i = 0; i < 20; i++) {
            for(int j = 0; j < 15; j ++){
                graphics.drawImage(Asset.main1, i * 40, j* 40, 40, 40, null);
            }
        }
        graphics.setColor(Color.WHITE);
        graphics.drawString(S8, 0, Game.WINDOW_HEIGHT);
		
        graphics.setColor(Color.GREEN);
        for(int i=0 ; i<this.options.length ; i++) {	
            graphics.drawString(this.options[i], Game.WINDOW_WIDTH / 2 - 160, Game.WINDOW_HEIGHT /2 - 120 + 30*i);
		}       
    }
}
