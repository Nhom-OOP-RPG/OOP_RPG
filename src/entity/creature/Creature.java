/*
Lớp Creature: Lớp cha abstract các sinh vật nói chung: người, quái
*/

package entity.creature;

import java.awt.image.BufferedImage;

import entity.Entity;
import graphic.tile.Tile;
import main.Handler;

public abstract class Creature extends Entity {
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    //Máu
    protected int health, maxHealth;
    protected boolean isDead;
    //tốc độ di chuyển (một bước đi được bao nhiêu pixel)
    protected float speed;

    //Di chuyển ngang dọc bao nhiêu ô (tính theo pixel)
    protected float xMove, yMove;
    protected int currentDirect;
    
    //Các thành phần liên quan tới Animation
    //thời gian để chuyển animation, vì không delay thì animation sẽ nhanh quá
    protected int animationDelay;
    //Ảnh animation hiện tại
    protected BufferedImage currentFrame;
    //Mã index của animation hiện tại
    protected int currentFrameID;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = 100;
        speed = 2.5f;
        xMove = 0;
        yMove = 0;

        currentDirect = 0;

        isDead = false;
    }


    //BEGIN MOVE
    //sau khi cập nhật xMove, yMove, phương thức này sẽ thực hiện di chuyển
    public void move(){
        moveX();
        moveY();
    }

    //thực hiện di chuyển phương ngang
    //nếu không va chạm thì sẽ đi bình thường
    //nếu đi bình thường mà có va chạm thì đi đến kịch chỗ va chạm và không đi tiếp nữa
    public void moveX(){
        int playerHead = (int) (y + bounds.y) / Tile.TILE_HEIGHT;
        int playerTail = (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
        if (xMove > 0){ //Sang phai
            int playerRight = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!isCollision(playerRight, playerHead)
              && !isCollision(playerRight, playerTail)){
                x += xMove;
            } else {
                x = playerRight * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
            currentDirect = EAST;
        } else if (xMove < 0){ //Sang trai
            int playerLeft = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if (!isCollision(playerLeft, playerHead)
              && !isCollision(playerLeft, playerTail)){
                  x += xMove;
            } else {
                x = playerLeft * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
            currentDirect = WEST;
        }
    }

    //thực hiện di chuyển phương dọc
    //xử lý va chạm tương tự phương ngang
    public void moveY(){
        int playerLeft = (int) (x + bounds.x) / Tile.TILE_WIDTH;
        int playerRight = (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH;
        if (yMove > 0){ //Xuong duoi
            int playerTail = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!isCollision(playerLeft, playerTail)
              && !isCollision(playerRight, playerTail)){
                y += yMove;
            } else {
                y = playerTail * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
            currentDirect = SOUTH;
        } else if (yMove < 0){ //Len tren
            int playerHead = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if (!isCollision(playerLeft, playerHead)
              && !isCollision(playerRight, playerHead)){
                y += yMove;
            } else {
                y = playerHead * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
            currentDirect = NORTH;
        }
    }

    //kiểm tra xem có va chạm tới Tile không dẫm được ở vị trí (x, y) không
    //true nếu có va chạm
    protected boolean isCollision(int x, int y){
        return handler.getWorld().getRoom().getTile(x, y).isSolid();
    }
    //END MOVE

    //HEALTH
    public void increaseHealth(int n){
        health += n;
    }
    public void decreaseHealth(int n){
        health -= n;
    }

    public void setDead(){
        isDead = true;
        bounds.setBounds((int) x, (int) y, 0, 0);
        currentFrame = setDeadFrame();
    }

    public int getHealth(){
        return health;
    }

    public boolean getIsDead(){
        return isDead;
    }

    //chuyển đổi Animation
    protected abstract void currentFrameUpdate();

    protected BufferedImage setDeadFrame(){
        return null;
    }
}
