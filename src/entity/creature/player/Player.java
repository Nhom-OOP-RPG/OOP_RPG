//Lớp người chơi

package entity.creature.player;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import entity.creature.Creature;
import entity.creature.player.playerweapon.PlayerGun;
import entity.creature.player.playerweapon.PlayerMelee;
import entity.creature.player.playerweapon.PlayerWeapon;
import graphic.Asset;
import graphic.tile.Tile;
import main.Handler;

import java.awt.Rectangle;

public class Player extends Creature {
    //vị trí spawn đầu màn chơi (tính theo pixel)
    public static final float DEFAULT_SPAWN_X = 9 * 40, DEFAULT_SPAWN_Y = 7 * 40; 

    private int lives;
    //thế giới và phòng hiện tại
    //các này t tạo ra cho có mà chưa dùng làm gì
    int atWorld, atRoom = 0;

    private int energy, energyDelay, energyDelayCount;

    private int currentWeapon;
    private PlayerWeapon[] weapons;
    private boolean isAttacking;
    private int keyPressedDelayCount;
    private int keyPressedDelay;

    private BufferedImage overlayFrame;

    public Player(Handler handler){
        super(handler, DEFAULT_SPAWN_X, DEFAULT_SPAWN_Y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

        maxHealth = 100;
        health = 100;
        speed = 2.5f;

        lives = 3;

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 29;

        energy = 100;
        energyDelay = 70;
        energyDelayCount = 0;

        weapons = new PlayerWeapon[2];
        weapons[0] = new PlayerMelee(handler, 15);
        weapons[1] = new PlayerGun(handler, 10);

        currentWeapon = 0;
        isAttacking = false;
        keyPressedDelayCount = 20;
        keyPressedDelay = 20;

        currentFrame = Asset.player[0][0];
        currentFrameID = 0;

        overlayFrame = null;
    }

    @Override
    public void tick() {
        if (health <= 0) {
            isDead = true;
            System.out.println("player dead");
        }

        energyDelayCount++;
        if (energy < 100 && energyDelayCount > energyDelay){
            energy++;
            energyDelayCount = 0;
        }

        getInput();
        move();

        if (isAttacking){
            weapons[currentWeapon].damaging();
            isAttacking = false;
            System.out.println("attack");
        }
        weapons[currentWeapon].tick();
    }

    @Override
    public void render(Graphics graphics) {
        currentFrameUpdate();
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);
        graphics.drawImage(overlayFrame, (int) x, (int) y, width, height, null);

        weapons[currentWeapon].render(graphics);

        for (int i = 0; i < lives; i++){
            graphics.drawImage(Asset.heart, Tile.TILE_WIDTH * 2/3 * i, 0, Tile.TILE_WIDTH * 2/3, Tile.TILE_HEIGHT * 2/3, null);
        }
        graphics.setColor(Color.RED);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawString(getHealth()+ " / 100", Tile.TILE_WIDTH * 2/3 + 60, 20);
        graphics.setColor(Color.BLUE);
        graphics.drawString(energy + " / 100", Tile.TILE_WIDTH * 2/3 + 140, 20);
    }
    
    //Kiểm tra input để cập nhật xMove, yMove
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().left){
            xMove = -speed;
            currentDirect = WEST;
        }
        if (handler.getKeyManager().right){
            xMove = speed;
            currentDirect = EAST;
        }
        if (handler.getKeyManager().up){
            yMove = -speed;
            currentDirect = NORTH;
        }
        if (handler.getKeyManager().down){
            yMove = speed;
            currentDirect = SOUTH;
        }

        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().attack){
                isAttacking = true;
                keyPressedDelayCount = 0;
            }

            if (handler.getKeyManager().changeWeapon){
                currentWeapon = 1 - currentWeapon;
                System.out.println("change weapon");
                keyPressedDelayCount = 0;
            }

            if (handler.getKeyManager().ultimate){
                weapons[currentWeapon].ultimate();
            }
        }
    }

    int i = 0;
    //Chuyển đổi animation của người chơi
    @Override
    protected void currentFrameUpdate() {
        animationDelayCount++;
        
        if (isDamaged){
            changeToDamagedFrame = 1;
            isDamaged = false;
            animationDelayCount = 0;
        }

        if (animationDelayCount >= animationDelay){
            if (xMove != 0 || yMove != 0){
                currentFrameID = 1 - currentFrameID;
            }
            changeToDamagedFrame = 0;
            animationDelayCount = 0;
            overlayFrame = null;
        }

        currentFrame = Asset.player[currentDirect + 4 * changeToDamagedFrame][currentFrameID];
    }
    
    //Get Set
    public int getLives(){
        return lives;
    }

    public PlayerWeapon getWeapon(int index){
        if (index < weapons.length){
            return weapons[i];
        }
        return null;
    }

    public void increaseHealth(int h){
        if (health + h > maxHealth){
            health = maxHealth;
        } else {
            health += h;
        }
    }

    public void increaseEnergy(int e){
        if (energy + e > 100){
            energy = 100;
        } else {
            energy += e;
        }
    }

    public boolean decreaseEnergy(int e){
        if (energy >= e){
            energy -= e;
            return true;
        }
        System.out.println("out of energy");
        return false;
    }

    public void increaseSpeed(float s){
        speed += s;
    }

    public void decreaseLives(){
        lives--;
    }

    public void revive(){
        health = maxHealth;
        energy = 100;
        overlayFrame = null;
        currentFrame = Asset.player[0][0];
        currentFrameID = 0;
        changeToDamagedFrame = 0;
        isDead = false;
        weapons[0].resetWeapon();
        weapons[1].resetWeapon();
    }

    //dùng trong chuyển các phòng
    public void setChangeRoomX(float x){
        this.x = x - this.width / 2;
    }

    public void setChangeRoomY(float y){
        this.y = y - this.height / 2;
    }

    public void setOverlayFrame(BufferedImage frame){
        this.overlayFrame = frame;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)getCenterX(), (int) getCenterY(),  20, 20);
    }

    public int getCurrentDirect(){
        return currentDirect;
    }
}
