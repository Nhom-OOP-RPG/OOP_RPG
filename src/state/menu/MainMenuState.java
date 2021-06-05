package state.menu;

import java.awt.Graphics;
import java.util.ArrayList;

import graphic.Asset;

import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class MainMenuState extends State {
    private ArrayList<String> options;

    private static final String START_GAME = "START";
	private static final String QUIT_GAME = "QUIT";
    private static final String INSTRUCTION = "HELP";
    private static final String CONTINUEGAME = "CONTINUE";

    private int selected;

    public MainMenuState(Handler handler) {
        super(handler);
        
        this.options = new ArrayList<String>();
        this.options.add(START_GAME);
        this.options.add(INSTRUCTION);
        this.options.add(QUIT_GAME);

        this.selected = 0;
    }

    @Override
    public void tick() {
        addContinueOption();

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
                if (selected < options.size() - 1){
                    selected++;
                }
                keyPressedDelayCount = 0;
                return;
            }

            if (handler.getKeyManager().enter) {
                if (isPlaying) switch (selected) {
                    case 0:
                        State.setState(handler.getGame().getGameState());
                        break;
                    case 1:
                        State.setState(handler.getGame().getChooseLevelState());
                        break;
                    case 2:
                        State.setState(handler.getGame().getInStructionState());
                        break;
                    default:
                        System.exit(0);
                }
                else switch (selected) {
                    case 0:
                        State.setState(handler.getGame().getChooseLevelState());
                        break;
                    case 1:
                        State.setState(handler.getGame().getInStructionState());
                        break;
                    default:
                        System.exit(0);
                }
                this.selected = 0;
                keyPressedDelayCount = 0;
                removeContinueOption();
            }
        } 
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(backGround[themeID], 0, 0, 800, 600, null);
        drawMenuBox(graphics);
        graphics.drawImage(Asset.logo, 152, 60, 496, 184, null);

        graphics.setFont(primaryFont);
		for(int i = 0; i < this.options.size(); i++) {
			if(i == this.selected){
                drawCenterString(graphics, 290 + 60*i, this.options.get(i), primaryFont, fontColor);
            } else {
                drawCenterString(graphics, 290 + 60*i, this.options.get(i), primaryFont, Color.WHITE);
            }
		}

        drawCenterString(graphics, Game.WINDOW_HEIGHT - 30, "©2021", new Font("Arial", Font.BOLD, 20), primaryColor[themeID]);
        drawCenterString(graphics, Game.WINDOW_HEIGHT - 10, "Made by Group 21", new Font("Arial", Font.BOLD, 20), primaryColor[themeID]);
    }
    
    public void addContinueOption(){
        if (options.size() == 3 && isPlaying){
            options.add(0, CONTINUEGAME);
        }
    }

    public void removeContinueOption(){
        if (options.size() == 4){
            options.remove(0);
        }
    }
}