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


public class Player extends Creature {
    //vị trí spawn đầu màn chơi (tính theo pixel)
    public static final float DEFAULT_SPAWN_X = 9 * 40, DEFAULT_SPAWN_Y = 7 * 40; 

    //thế giới và phòng hiện tại
    //các này t tạo ra cho có mà chưa dùng làm gì
    int atWorld, atRoom = 0;

    private int currentWeapon;
    private PlayerWeapon[] weapons;
    public int attackDirect;
    private boolean isAttacking;
    private int keyPressedDelayCount;
    private int keyPressedDelay;

    private BufferedImage[][] normalFrame, damagedFrame;
    private BufferedImage scratchedFrame;

    public Player(Handler handler){
        super(handler, DEFAULT_SPAWN_X, DEFAULT_SPAWN_Y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 30;

        weapons = new PlayerWeapon[2];
        weapons[0] = new PlayerMelee(handler, 20);
        weapons[1] = new PlayerGun(handler, 40);

        currentWeapon = 0;
        isAttacking = false;
        keyPressedDelayCount = 20;
        keyPressedDelay = 20;


        animationDelay = 0;
        currentFrame = Asset.player[0][0];
        currentFrameID = 0;

        scratchedFrame = null;
    }

    @Override
    public void tick() {
        if (health <= 0) {
            isDead = true;
            System.out.println("player dead");
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
        graphics.drawImage(scratchedFrame, (int) x, (int) y, width, height, null);

        weapons[currentWeapon].render(graphics);

        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawImage(Asset.heart, 0, 0, Tile.TILE_WIDTH * 2/3, Tile.TILE_HEIGHT * 2/3, null);
		graphics.drawString(getHealth()+" / 100", Tile.TILE_HEIGHT*2/3+5, 20);
    }
    
    //Kiểm tra input để cập nhật xMove, yMove
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up){
            yMove = -speed;
            attackDirect = 3;
        }
        if (handler.getKeyManager().down){
            yMove = speed;
            attackDirect = 2;
        }
        if (handler.getKeyManager().left){
            xMove = -speed;
            attackDirect = 1;
        }
        if (handler.getKeyManager().right){
            xMove = speed;
            attackDirect = 0;
        }

        keyPressedDelayCount++;
        if (keyPressedDelayCount >= keyPressedDelay){
            if (handler.getKeyManager().attack){
                isAttacking = true;
                keyPressedDelayCount = 0;
            }
            if (handler.getKeyManager().enter){
                currentWeapon++;
                if (currentWeapon >= 2){
                    currentWeapon = 0;
                }
                System.out.println("change weapon");
                keyPressedDelayCount = 0;
            }
        }
    }

    //Chuyển đổi animation của người chơi
    @Override
    protected void currentFrameUpdate() {
        animationDelay++;
        if (animationDelay >= 10){
            animationDelay = 0;
            currentFrameID = 1 - currentFrameID;

            scratchedFrame = null;
        }

        if (xMove == 0 && yMove == 0){
            return;
        }

        if (xMove > 0){
            currentFrame = Asset.player[0][currentFrameID];
        } else if (xMove < 0){
            currentFrame = Asset.player[1][currentFrameID];
        } else if (yMove > 0){
            currentFrame = Asset.player[2][currentFrameID];
        } else if (yMove < 0){
            currentFrame = Asset.player[3][currentFrameID];
        }
    }
    
    //Get Set

    //dùng trong chuyển các phòng
    public void setChangeRoomX(float x){
        this.x = x - this.width / 2;
    }

    public void setChangeRoomY(float y){
        this.y = y - this.height / 2;
    }

    public void setScratchedFrame(BufferedImage frame){
        this.scratchedFrame = frame;
    }
}
