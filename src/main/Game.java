/*
Lớp Game chứa tất cả những gì liên quan tới việc chạy game
Khi Main gọi Game.start(), chương trình tạo thread, gọi đến hàm run() để chạy game:
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
import state.PauseState;
import state.menu.MainMenuState;
import state.menu.InstructionState;
import state.menu.ChooseLevelState;
import state.gameover.WinGameState;
import state.gameover.LoseGameState;
import state.gameover.PlayAgainState;


public class Game implements Runnable {
    //Cửa sổ
    private Display display;

    //Title và kích thước cửa sổ
    public final String GAME_TITLE = "OOP-RPG";
    public static final int WINDOW_WIDTH = 40*20, WINDOW_HEIGHT = 40*15;

    private boolean isRunning = false;
    private Thread thread;

    BufferStrategy bufferStrategy;
    Graphics graphics;
    
    //Input
    private KeyManager keyManager;

    //Handler
    private Handler handler;

    //State
    private State gameState;
    private State mainMenuState;
    private State instructionState;
    private State chooseLevelState;
    private State pauseState;
    private State winGameState;
    private State loseGameState;
    private State playAgainState;

    //Khởi tạo game
    private void init(){
        //Tạo cửa sổ mới
        display = new Display(GAME_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        //Tạo input từ bàn phím
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);

        //Khởi tạo tài nguyên (ảnh)
        Asset.init();

        //Handler
        handler = new Handler(this);

        //State
        gameState = new GameState(handler);
        mainMenuState = new MainMenuState(handler);
        instructionState = new InstructionState(handler);
        chooseLevelState = new ChooseLevelState(handler);
        pauseState = new PauseState(handler);
        winGameState = new WinGameState(handler);
        loseGameState = new LoseGameState(handler);
        playAgainState = new PlayAgainState(handler);

        State.setState(mainMenuState);
    }

    //Hàm tick() để cập nhật State hiện tại 
    private void tick(){
        //Nhận input từ bàn phím
        keyManager.tick();

        //Cập nhật
        if (State.getState() != null){
            State.getState().tick();
        }
    }

    //Hàm render() để in ra màn hình State hiện tại
    private void render(){
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        
        //Clear màn hình cũ
        graphics.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //Vẽ lên cửa sổ theo render() của State hiện tại
        if (State.getState() != null){
            State.getState().render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    //Chạy chương trình
    public void run(){
        //Khởi tạo game
        this.init();

        FPSTimer timer = new FPSTimer(60);
  
        //Chạy game
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

    //Thoát game
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

    public State getPauseState() {
        return pauseState;
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
