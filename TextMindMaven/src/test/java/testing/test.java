/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import com.TextMind.swing.blurHash.BlurHash;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author KHOA
 */
public class test {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("F:\\FPT\\Ky4\\duan1\\duan\\TextMindMaven\\TextMindMaven\\src\\main\\resources\\images\\avatar.jpg"));
            String blurhashStr = BlurHash.encode(image);
            System.out.println(blurhashStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
