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
    private ArrayList<String> optionsMenu;

    private static final String START_GAME = "START";
	private static final String QUIT_GAME = "QUIT";
    private static final String INSTRUCTION = "HELP";
    private static final String CONTINUEGAME = "CONTINUE";

    private int selected;

    public MainMenuState(Handler handler) {
        super(handler);
        
        this.optionsMenu = new ArrayList<String>();
        this.optionsMenu.add(START_GAME);
        this.optionsMenu.add(INSTRUCTION);
        this.optionsMenu.add(QUIT_GAME);

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
                if (selected < optionsMenu.size() - 1){
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

		graphics.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 50));
        graphics.drawImage(Asset.mainMenu1,0,0, 20*40, 15*40, null);

		for(int i=0; i<this.optionsMenu.size(); i++) {
			if(i==this.selected) graphics.setColor(Color.BLUE.darker().darker());
			else graphics.setColor(Color.GREEN.darker().darker().darker());
			graphics.drawString(this.optionsMenu.get(i), (Game.WINDOW_WIDTH - this.optionsMenu.get(i).length() * 25)  / 2 - 20, Game.WINDOW_HEIGHT /2 + 60*i - 60);
		}
    }
    
    public void addContinueOption(){
        if (optionsMenu.size() == 3 && isPlaying){
            optionsMenu.add(0, CONTINUEGAME);
        }
    }

    public void removeContinueOption(){
        if (optionsMenu.size() == 4){
            optionsMenu.remove(0);
        }
    }
}