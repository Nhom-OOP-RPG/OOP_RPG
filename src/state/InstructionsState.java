package state;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;

public class InstructionsState extends State{

    private String[] options;

    private static final String S1 = "Press -> to go right";
    private static final String S2 = "Press <- to go left";
    private static final String S3 = "Press ^ to go up";
    private static final String S4 = "Press v to go right";
    private static final String S5 = "Press Space to attack";

    private static final String S6 = "Press 2 to back to Main Menu";
    
    public InstructionsState(Handler handler) {
        super(handler);
        this.options = new String[] {S1, S2, S3, S4, S5};
    }

    @Override
    public void tick() {

        if (handler.getKeyManager().two) {

            State.setState(handler.getGame().getMainMenuState());
        }
        
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
        graphics.setColor(Color.WHITE);
        graphics.drawString(S6, 0, Game.WINDOW_HEIGHT);
		for(int i=0;i<this.options.length;i++) {
			graphics.setColor(Color.GREEN);
            graphics.drawString(this.options[i], Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2 + 30*i);
            
		}
        
        
    }


}