/*
Lớp State
Dễ hiểu thì một trò chơi thường có phần Menu, Setting, màn hình Chơi, màn hình lúc Pause,...
-> các thành phần trên là các state
Đây là lớp cha abstract cho các lớp con state trên
*/

package state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

import graphic.Asset;
import main.Handler;

public abstract class State {
    protected Handler handler;

    //state hiện tại và state trước đó
    private static State currentState = null, previousState = null;

    //delay khi ấn phím -> không gặp tình trạng nhảy nút
    protected static int keyPressedDelay, keyPressedDelayCount;

    //kiểm tra có đang trong giữa màn chơi hay ko? dùng để tạo Continue ở màn hình chính
    protected static boolean isPlaying;

    //một số thuộc tính dùng ch giao diện static
    public static int themeID;
    protected final static BufferedImage[] backGround = Asset.backGround;
    protected final static Color[] primaryColor = {new Color(12, 54, 15), new Color(130, 20, 8)};
    protected final static Color[] secondaryColor = {new Color(85, 139, 47), new Color(201, 78, 12)};
    protected final static Color fontColor = new Color(255, 213, 0);
    protected final static Font primaryFont = new Font("Copperplate Gothic Bold", Font.BOLD, 50);

    public State(Handler handler){
        this.handler = handler;

        keyPressedDelay = 15;
        keyPressedDelayCount = 0;

        isPlaying = false;

        themeID = 0;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    //Get Set
    public static State getState(){
        return currentState;
    }

    public static State getPreviousState(){
        return previousState;
    }

    public static void setState(State state){
        previousState = currentState;
        currentState = state;
    }

    //vẽ khung menu
    protected void drawMenuBox(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fill3DRect(130, 150, 540, 350, true);
        graphics.setColor(primaryColor[themeID]);
        graphics.fill3DRect(140, 160, 520, 330, true);
    }

    //vẽ khung title cho menu
    protected void drawTitleBox(Graphics graphics, String tilte){
        graphics.setColor(Color.BLACK);
        graphics.fill3DRect(160, 105, 480, 80, true);
        graphics.setColor(secondaryColor[themeID]);
        graphics.fill3DRect(170, 115, 460, 60, true);

        drawCenterString(graphics, 160, tilte, primaryFont, Color.WHITE);
    }

    //in chữ căn giữa màn hình
    protected void drawCenterString(Graphics graphics, int y, String str, Font font, Color color){
        graphics.setFont(font);
        graphics.setColor(color);
        FontMetrics fm = graphics.getFontMetrics();
        int x = (800 - fm.stringWidth(str)) / 2;
        graphics.drawString(str, x, y);
    }

    //in chữ được chọn trong menu
    protected void drawSelectedString(Graphics graphics, int y, String str){
        graphics.setFont(primaryFont);
        graphics.setColor(fontColor);

        FontMetrics fm = graphics.getFontMetrics();
        int width = fm.stringWidth(str);
        int x = (800 - width) / 2;

        graphics.drawString(str, x, y);
        graphics.drawImage(Asset.selected[0], x - 50, y - 35, 30, 30, null);
        graphics.drawImage(Asset.selected[1], x + width + 20, y - 35, 30, 30, null);
    }
}
