package state;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import main.Game;
import main.Handler;

public class WinGameState extends State {


    private static final String WIN_GAME = "You Win!";

    public WinGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));

		graphics.setColor(Color.GREEN);

		graphics.drawString(WIN_GAME, Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2);
    }
    
}
