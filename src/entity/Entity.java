/*
Lớp Entity: Tất cả những thứ khác ngoài Tile ở trong game
    - Sinh vật: người, quái
    - Các vũ khí (phần này t nghiên cứu sau) 
-> Đây là lớp cha abstract
*/

package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;

public abstract class Entity {
    protected Handler handler;

    //
    protected float x, y;
    protected int width, height;

    //Khối thể hiện thực tế
    //Hiểu là lúc in thành phần ra màn hình sẽ in theo kích thước 40x40 (kể cả phần viền trong suốt)
    //nhưng các thành phần khi xử lý các công việc khác không phải kích thước 40x40 như thế
    //để hiểu rõ hơn vào Player.java:
    //  - trên import xóa dấu comment ở phần Color đi
    //  - trong hàm render, xóa dấu comment ở trong hàm đi
    //  - chạy thử, sẽ thấy có phần màu là phần thực tế của player
    //  - khi xử lý va chạm (hoặc đánh quái,... sau này) sẽ xử lý theo phần đó của player
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    //Get Set
    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getMovingBounds() {
        return new Rectangle(bounds.x + (int)x, bounds.y + (int)y, bounds.width, bounds.height);
    }
}