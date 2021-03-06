//Thử nghiệm cho một loại quái

package entity.creature.enemy;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import entity.creature.Creature;
import entity.creature.player.Player;
import graphic.Asset;
import main.Handler;

public abstract class Enemy extends Creature {
    public static final int DEMO = 0, EASY = 1, HARD = 2;

    //mục tiêu tấn công là người chơi
    private Player target;

    //khoảng cách đến người chơi
    protected float distanceToTarget;
    //góc đến người chơi (với trục Ox)
    protected double angleToTarget;

    //delay thơif gian tấn công
    protected int attackDelayCount;
    protected int attackDelay;

    public Enemy(Handler handler, float x, float y, int level){
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

        init(level);

        health = maxHealth;

        this.target = handler.getPlayer();

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 20;
        
        currentFrameID = 0;
        attackDelayCount = 0;
    }


    //xét xem target đang ở đâu để chọn hướng di chuyển
    //nếu người chơi đứng quá gần hoặc quá xa sẽ ko di chuyển
    protected void updateTarget(float near, float far){
        xMove = 0;
        yMove = 0;

        float xDiffer = target.getCenterX() - this.getCenterX();
        float yDiffer = target.getCenterY() - this.getCenterY();
        
        distanceToTarget = (float) java.lang.Math.sqrt(xDiffer*xDiffer + yDiffer*yDiffer);

        angleToTarget = Math.toDegrees(Math.atan(yDiffer / xDiffer));
        if (xDiffer < 0) angleToTarget += 180;
        if (angleToTarget < 0) angleToTarget += 360;

        if (distanceToTarget <= near) return;
        if (distanceToTarget >= far) return;

        if (xDiffer < 0){
            xMove -= speed;
        } else if (xDiffer > 2){
            xMove += speed;
        }

        if (yDiffer < 0){
            yMove -= speed;
        } else if (yDiffer > 2) {
            yMove += speed;
        }

        if (angleToTarget < 45 && angleToTarget >= 315){
            currentDirect = EAST;
        } else if (angleToTarget < 135) {
            currentDirect = SOUTH;
        } else if (angleToTarget < 225) {
            currentDirect = WEST;
        } else {
            currentDirect = NORTH;
        }
    }

    //in lượng máu bằng số
    protected void renderHealth(Graphics graphics){
        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 10));
		graphics.drawString(getHealth()+" / " + this.maxHealth, (int) this.x, (int) this.y);
    }

    //in thanh máu (cho boss)
    protected void renderBossHealth(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) this.x - 20, (int) this.y - 25, 120, 20);
        float ratio = (float) this.health / this.maxHealth;
        graphics.setColor(Color.RED);
        graphics.fillRect((int) this.x - 20, (int) this.y - 25, (int) (120 * ratio), 20);
    }

    //khởi tạo theo mức độ
    protected void init(int level){
        switch (level) {
            case DEMO:
                initDemo();
                break;
            case EASY:
                initEasy();
                break;
            case HARD:
                initHard();
        }
    }

    protected abstract void initDemo();
    protected abstract void initEasy();
    protected abstract void initHard();

    public float getDistanceToTarget() {
        return distanceToTarget;
    }

    public double getAngleToTarget() {
        return angleToTarget;
    }

    public void setDead(){
        isDead = true;
        bounds.setBounds((int) this.x, (int) this.y, 0, 0);
        currentFrame = Asset.dead;
        handler.getWorld().getRoom().addNewItem((int) this.x, (int) this.y);
    }
}
