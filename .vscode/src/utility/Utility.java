/*
Lớp Utility: chứa các phương thức load file, load ảnh, chuyển String thành int
-> để tiện sử dụng, không đóng góp nhiều vào xử lý game
*/

package utility;

//loadFileAsString
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//loadImage
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Utility {
    //chuyen duong dan thanh String
    public static String loadFileAString(String path){
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null){
                builder.append(line + "\n");
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    //String -> int
    public static int parseInt(String number){
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }


    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
