//Thử nghiệm cho một loại quái

package entity.creature.enemy;

import entity.creature.Creature;
import entity.creature.player.Player;
import main.Handler;

public abstract class Enemy extends Creature {
    private Player target;

    protected float distanceToTarget;

    protected int attackDelayCount;
    protected int attackDelay;

    public Enemy(Handler handler, float x, float y, Player target) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        health = 30;
        speed = 1.5f;

        this.target = target;

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

        if (distanceToTarget <= near) return;
        if (distanceToTarget >= far) return;

        float tanTargetToThis = yDiffer / xDiffer;
        if (tanTargetToThis >= -1 && tanTargetToThis <= 1){
            if (target.getX() < x){
                xMove -= speed;
            } else {
                xMove += speed;
            }
        } else if (tanTargetToThis < -1 || tanTargetToThis > 1){
            if (target.getY() < y){
                yMove -= speed;
            } else {
                yMove += speed;
            }
        }
    }

    public float getDistanceToTarget() {
        return distanceToTarget;
    }
}
