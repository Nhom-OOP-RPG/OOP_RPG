//Lá»›p ngÆ°á»�i chÆ¡i

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

    public static final float DEFAULT_SPAWN_X = 9 * 40, DEFAULT_SPAWN_Y = 7 * 40; 


    private int lives;

    private int energy, energyDelay, energyDelayCount;


    private int currentWeaponID;

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
        weapons[0] = new PlayerMelee(handler, 8);
        weapons[1] = new PlayerGun(handler, 5);

        currentWeaponID = 0;
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

        //há»“i mana
        energyDelayCount++;
        if (energy < 100 && energyDelayCount > energyDelay){
            energy++;
            energyDelayCount = 0;
        }


        getInput();

        move();

        currentFrameUpdate();

        if (isAttacking){
            weapons[currentWeaponID].damaging();
            isAttacking = false;
            System.out.println("attack");
        }

        weapons[currentWeaponID].tick();
    }

    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);

        graphics.drawImage(overlayFrame, (int) x, (int) y, width, height, null);

        weapons[currentWeaponID].render(graphics);

        for (int i = 0; i < lives; i++){
            graphics.drawImage(Asset.heart, Tile.TILE_WIDTH * 2/3 * i + Tile.TILE_WIDTH * 16 , Tile.TILE_HEIGHT / 2 - 5, Tile.TILE_WIDTH * 2/3, Tile.TILE_HEIGHT * 2/3, null);
        }
		graphics.setFont(new Font("Amasis MT Pro Black", 3, 18));
        graphics.setColor(Color.RED);
		graphics.drawString("HP", 1 , 16);

        float ratio = (float) (100 - getHealth()) / 100;
        graphics.setColor(Color.RED);
        graphics.fillRect(40, 5, (int) 120, 12);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(40, 5, (int) (120 * ratio), 12);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Amasis MT Pro Black", 3, 15));
		graphics.drawString(getHealth() + " / 100", 70 , 16);
		
		
		graphics.setFont(new Font("Amasis MT Pro Black", 3, 18));
        graphics.setColor(Color.BLUE);
		graphics.drawString("MP", 1 , 37);

        float ratioMP = (float) (100 - energy) / 100;

        if(energy >= 60) {
			graphics.setColor(Color.BLUE);
		}else {
			graphics.setColor(Color.YELLOW);
		}
        graphics.fillRect(40, 25, (int) 120, 12);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(40, 25, (int) (120 * ratioMP), 12);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Amasis MT Pro Black", 3, 15));
		graphics.drawString(energy + " / 100", 70 , 37);		
    }
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
                currentWeaponID = 1 - currentWeaponID;
                System.out.println("change weapon");
                keyPressedDelayCount = 0;
            }

            if (handler.getKeyManager().ultimate){
                weapons[currentWeaponID].ultimate();
                System.out.println("ultimate");
                keyPressedDelayCount = 0;
            }
        }
    }

    //Chuyá»ƒn Ä‘á»•i animation cá»§a ngÆ°á»�i chÆ¡i
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
    
    //Get Set vÃ  tÄƒng giáº£m
    public int getLives(){
        return lives;
    }

    public PlayerWeapon getWeapon(int index){
        if (index < weapons.length){
            return weapons[index];
        }
        return null;
    }

    //tÄƒng mÃ¡u (giáº£m mÃ¡u á»Ÿ lá»›p cha Creature)
    public void increaseHealth(int h){
        if (health + h > maxHealth){
            health = maxHealth;
        } else {
            health += h;
        }
    }

    //tÄƒng mana
    public void increaseEnergy(int e){
        if (energy + e > 100){
            energy = 100;
        } else {
            energy += e;
        }
    }

    //giáº£m mana
    public boolean decreaseEnergy(int e){
        if (energy >= e){
            energy -= e;
            return true;
        }
        System.out.println("out of energy");
        return false;
    }

    //tÄƒng tá»‘c Ä‘á»™
    public void increaseSpeed(float s){
        speed += s;
    }

    //giáº£m máº¡ng
    public void decreaseLives(){
        lives--;
    }

    //há»“i sinh
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

    //dÃ¹ng trong chuyá»ƒn cÃ¡c phÃ²ng
    public void setChangeRoomX(float x){
        this.x = x - this.width / 2;
    }

    public void setChangeRoomY(float y){
        this.y = y - this.height / 2;
    }

    //set áº£nh hiá»‡u á»©ng chá»“ng lÃªn
    public void setOverlayFrame(BufferedImage frame){
        this.overlayFrame = frame;
        this.animationDelayCount = 0;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)getCenterX(), (int) getCenterY(),  20, 20);
    }

    public int getCurrentDirect(){
        return currentDirect;
    }
}
