//Thử nghiệm cho một loại quái

package entity.creature.enemy;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import entity.creature.Creature;
import entity.creature.player.Player;
import main.Handler;

public abstract class Enemy extends Creature {
    private Player target;

    protected float distanceToTarget;
    protected double angleToTarget;

    protected int attackDelayCount;
    protected int attackDelay;

    public Enemy(Handler handler, float x, float y){
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

        health = 30;
        maxHealth = 30;
        speed = 1f;

        this.target = handler.getPlayer();

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 20;
        
        currentFrameID = 0;
    }


    //xét xem target đang ở đâu để chọn hướng di chuyển
    //sẽ giải thích nếu cần :)
    protected void updateTarget(float near, float far){
        xMove = 0;
        yMove = 0;

        float xDiffer = target.getX() - x;
        float yDiffer = target.getY() - y;
        
        distanceToTarget = (float) java.lang.Math.sqrt(xDiffer*xDiffer + yDiffer*yDiffer);

        angleToTarget = Math.toDegrees(Math.atan(yDiffer / xDiffer));
        if (xDiffer < 0) angleToTarget += 180;
        if (angleToTarget < 0) angleToTarget += 360;

        if (distanceToTarget <= near) return;
        if (distanceToTarget >= far) return;

        if (xDiffer < 0){
            xMove -= speed;
        } else {
            xMove += speed;
        }

        if (yDiffer < 0){
            yMove -= speed;
        } else {
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

    protected void renderHealth(Graphics graphics){
        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 10));
		graphics.drawString(getHealth()+" / " + this.maxHealth, (int) this.x, (int) this.y);
    }

    protected void renderBossHealth(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) this.x - 20, (int) this.y - 25, 120, 20);
        float ratio = (float) this.health / this.maxHealth;
        graphics.setColor(Color.RED);
        graphics.fillRect((int) this.x - 20, (int) this.y - 25, (int) (120 * ratio), 20);
    }


    public float getDistanceToTarget() {
        return distanceToTarget;
    }

    public double getAngleToTarget() {
        return angleToTarget;
    }
}
