/*
Lớp Game chứa tất cả những gì liên quan tới việc chạy game
Khi Main gọi Game.start(), hàm sẽ tạo thread, gọi đến hàm run() để chạy game:
    - đầu tiên run() gọi init() để khởi tạo các thành phần
    - sau đó đi vào vòng lặp, vòng lặp sẽ thực hiện lặp liên tục 60 nhịp/giây (theo khởi tạo fps), gọi tick() và render():
            + tick(): cập nhật lại dữ liệu (gọi đến tick() nhỏ trong state hiện tại)
            + render(): in dữ liệu trên ra màn hình (gọi render() nhỏ trong state hiện tại)
    - nếu thoát khỏi cửa sổ thì điều kiện while isRunning = false, game gọi stop() và dừng.
*/

package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import graphic.Asset;
import input.KeyManager;
import main.fps.FPSTimer;

//State
import state.State;
import state.GameState;
import state.menu.MainMenuState;
import state.menu.InstructionState;
import state.menu.ChooseLevelState;
import state.gameover.WinGameState;
import state.gameover.LoseGameState;
import state.gameover.PlayAgainState;


public class Game implements Runnable {
    //Cua so
    private Display display;

    //Title va kich thuoc cua so
    public final String GAME_TITLE = "OOP-RPG";
    public static final int WINDOW_WIDTH = 40*20, WINDOW_HEIGHT = 40*15;

    private boolean isRunning = false;
    private Thread thread;

    BufferStrategy bufferStrategy;
    Graphics graphics;
    
    //Input
    private KeyManager keyManager;

    // Handler
    private Handler handler;

    //State
    private State gameState;
    private State mainMenuState;
    private State instructionState;
    private State chooseLevelState;
    private State winGameState;
    private State loseGameState;
    private State playAgainState;

    //Khoi tao game
    private void init(){
        //Tao cua so va input
        display = new Display(GAME_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);

        //Khoi tao tai nguyen
        Asset.init();

        //Handler
        handler = new Handler(this);

        //State
        gameState = new GameState(handler);
        mainMenuState = new MainMenuState(handler);
        instructionState = new InstructionState(handler);
        chooseLevelState = new ChooseLevelState(handler);
        winGameState = new WinGameState(handler);
        loseGameState = new LoseGameState(handler);
        playAgainState = new PlayAgainState(handler);

        State.setState(playAgainState);
    }

    //Cap nhat du lieu
    private void tick(){
        keyManager.tick();

        if (State.getState() != null){
            State.getState().tick();
        }
    }

    //In ra man hinh
    private void render(){
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        
        //Clear man hinh
        graphics.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //Ve len cua so

        if (State.getState() != null){
            State.getState().render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    //Chay chuong trinh
    public void run(){
        //Khoi tao game
        this.init();

        FPSTimer timer = new FPSTimer(60);
  
        //Chay game
        while (isRunning){
            if (timer.check()){
                tick();
                render();
            }
        }

        stop();
    }

    //Start game
    public synchronized void start(){
        if (isRunning) return;

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    //Thoat game
    public synchronized void stop(){
        if (!isRunning) return;

        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Get
    public int getWindowWidth(){
        return WINDOW_WIDTH;
    }

    public int getWindowHeight(){
        return WINDOW_HEIGHT;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public State getGameState() {
        return gameState;
    }

    public State getChooseLevelState(){
        return chooseLevelState;
    }

    public State getMainMenuState() {
        return mainMenuState;
    }

    public State getWinGameState() {
        return winGameState;
    }

    public State getLoseGameState() {
        return loseGameState;
    }

    public State getInStructionState(){
        return instructionState;
    }

    public State getPlayAgainState(){
        return playAgainState;
    }
}
