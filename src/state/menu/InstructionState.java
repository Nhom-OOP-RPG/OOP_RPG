package state.menu;

import java.awt.Graphics;

import graphic.Asset;

import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class InstructionState extends State{

    private static final String S8 = "Press ENTER to back";
    
    public InstructionState(Handler handler) {
        super(handler);
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

        graphics.drawImage(Asset.instruction, 0, 0, 20*40, 15*40, null);

        graphics.setFont(new Font("Ink Free", Font.BOLD, 20));
        graphics.setColor(Color.BLUE.darker().darker());
        graphics.drawString(S8, 0, Game.WINDOW_HEIGHT);  
    }
}
