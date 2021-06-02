package entity.creature.enemy.world1;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.*;
import entity.item.EnergyItem;
import entity.item.HealthItem;
import graphic.Asset;
import graphic.tile.Tile;
import main.Handler;

public class Boss1 extends Enemy {

    private EnemyWeapon eGun, eMelee;

    public Boss1(Handler handler, float x, float y, int level) {
        super(handler, x, y, level);

        this.width = DEFAULT_WIDTH * 2;
        this.height = DEFAULT_HEIGHT * 2;

        this.bounds.x = 15;
        this.bounds.y = 10;
        this.bounds.width = 50;
        this.bounds.height = 60;

        currentFrame = Asset.boss1[0][0];
    }

    @Override
    public void tick(){
        eGun.tick();

        if (isDead) return;
        if (health <= 0) {
            setDead();
            currentFrame = Asset.deadBoss1;
            return;
        }

        currentFrameUpdate();

        move();
        updateTarget(80f, 1000f);

        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            if (this.health < 150){
                ((EnemyGun) eGun).damaging8Dir();
            } else if (this.health < 300){
                eGun.damaging();
            }

            eMelee.damaging();
            attackDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        eGun.render(graphics);
        eMelee.render(graphics);
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);
        
        if (isDead) return;

        renderBossHealth(graphics);
    }
    
    //Chuyển đổi Animation
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
        }

        currentFrame = Asset.boss1[currentDirect + 4 * changeToDamagedFrame][currentFrameID];
    }

    @Override
    protected void initDemo() {
        this.maxHealth = 1;
        speed = 1f;
        eGun = new EnemyGun(handler, 1, 300f, this, Asset.bulletFlame);
        eMelee = new EnemyMelee(handler, 1, 100f, this, Asset.scratchRed);
        attackDelay = 100;
    }

    @Override
    protected void initEasy() {
        this.maxHealth = 400;
        speed = 2f;
        eGun = new EnemyGun(handler, 10, 300f, this, Asset.bulletFlame);
        eMelee = new EnemyMelee(handler, 15, 100, this, Asset.scratchRed);
        attackDelay = 80;
    }

    @Override
    protected void initHard() {
        this.maxHealth = 400;
        speed = 2f;
        eGun = new EnemyGun(handler, 10, 300f, this, Asset.bulletFlame);
        eMelee = new EnemyMelee(handler, 15, 100, this, Asset.scratchRed);
        attackDelay = 80;
    }
    
    @Override
    public void moveX(){
        int head = (int) (y + bounds.y) / Tile.TILE_HEIGHT;
        int tail = (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
        int middle = (head + tail) / 2;

        if (xMove > 0){ //Sang phai
            int right = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!isCollision(right, head)
              && !isCollision(right, tail)
              && !isCollision(right, middle)){
                x += xMove;
            } else {
                x = right * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0){ //Sang trai
            int left = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if (!isCollision(left, head)
              && !isCollision(left, tail)
              && !isCollision(left, middle)){
                  x += xMove;
            } else {
                x = left * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    @Override
    public void moveY(){
        int left = (int) (x + bounds.x) / Tile.TILE_WIDTH;
        int right = (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH;
        int middle = (left + right) / 2;

        if (yMove > 0){ //Xuong duoi
            int tail = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!isCollision(left, tail) 
              && !isCollision(right, tail)
              && !isCollision(middle, tail)){
                y += yMove;
            } else {
                y = tail * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        } else if (yMove < 0){ //Len tren
            int head = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if (!isCollision(left, head) 
              && !isCollision(right, head)
              && !isCollision(middle, head)){
                y += yMove;
            } else {
                y = head * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }
    }

    @Override
    public void setDead(){
        isDead = true;
        bounds.setBounds((int) this.x, (int) this.y, 0, 0);
        currentFrame = Asset.deadBoss0;
        handler.getWorld().getRoom().getItemList().add(new HealthItem(handler, this.x + 40, y + 40));
        handler.getWorld().getRoom().getItemList().add(new EnergyItem(handler, this.x, y + 40));
    }
}

