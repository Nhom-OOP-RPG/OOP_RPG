package state.gameover;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import graphic.Asset;
import main.Game;
import main.Handler;
import state.State;

public class WinGameState extends State {

    public WinGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().enter) {
            themeID = 0;
            isPlaying = false;
            State.setState(handler.getGame().getMainMenuState());
            keyPressedDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Asset.winGame,0,0, 20*40, 15*40, null);
        drawCenterString(graphics, Game.WINDOW_HEIGHT, "Press Enter to continue...", new Font("Ink Free", Font.BOLD, 30), Color.WHITE);
    }
    
}
