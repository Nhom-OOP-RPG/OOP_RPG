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

    protected int health, maxHealth;
    protected boolean isDead, isDamaged;

    protected float speed;

    protected float xMove, yMove;
    protected int currentDirect;

    protected final int animationDelay;
    protected int animationDelayCount, changeToDamagedFrame;
    protected int currentFrameID;
    protected BufferedImage currentFrame;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        maxHealth = 0;
        health = 0;
        speed = 0;

        xMove = 0;
        yMove = 0;

        currentDirect = 0;

        animationDelay = 10;

        animationDelayCount = 0;
        changeToDamagedFrame = 0;

        isDead = false;
        isDamaged = false;
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
        int head = (int) (y + bounds.y) / Tile.TILE_HEIGHT;
        int tail = (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
        if (xMove > 0){ //Sang phai
            int right = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!isCollision(right, head)
              && !isCollision(right, tail)){
                x += xMove;
            } else {
                x = right * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0){ //Sang trai
            int left = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if (!isCollision(left, head)
              && !isCollision(left, tail)){
                  x += xMove;
            } else {
                x = left * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    //thực hiện di chuyển phương dọc
    //xử lý va chạm tương tự phương ngang
    public void moveY(){
        int left = (int) (x + bounds.x) / Tile.TILE_WIDTH;
        int right = (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH;
        if (yMove > 0){ //Xuong duoi
            int tail = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!isCollision(left, tail) && !isCollision(right, tail)){
                y += yMove;
            } else {
                y = tail * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        } else if (yMove < 0){ //Len tren
            int head = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if (!isCollision(left, head) && !isCollision(right, head)){
                y += yMove;
            } else {
                y = head * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
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
        isDamaged = true;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public boolean getIsDead(){
        return isDead;
    }

    //chuyển đổi Animation
    protected abstract void currentFrameUpdate();
}
