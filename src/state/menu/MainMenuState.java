package state.menu;

import java.awt.Graphics;
import java.util.ArrayList;

import graphic.Asset;
import graphic.tile.Tile;

import java.awt.Color;
import java.awt.Font;

import main.Game;
import main.Handler;
import state.State;

public class MainMenuState extends State {
    private ArrayList<String> optionsMenu;

    private static final String START_GAME = "Start New Game";
	private static final String QUIT_GAME = "Quit Game";
    private static final String INSTRUCTION = "Instructions";
    private static final String CONTINUEGAME = "Continue Game";

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

		graphics.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		graphics.setFont(new Font("Araial", Font.BOLD, 25));
        /*for (int y = 0; y < 15; y++){
            for (int x = 0; x < 20; x++){
                Tile tile = new Tile(Asset.main0, 7);
                tile.render(graphics, x, y);
            }
        }*/
        graphics.drawImage(Asset.mainMenu,0,0, 20*40, 15*40, null);
		for(int i=0; i<this.optionsMenu.size(); i++) {
			if(i==this.selected) graphics.setColor(Color.BLUE);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu.get(i), Game.WINDOW_WIDTH / 2 - 40, Game.WINDOW_HEIGHT /2 + 30*i);
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