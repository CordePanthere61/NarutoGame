package cegepst;

import sun.awt.util.IdentityArrayList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    private static ImageLoader instance;
    private final String NARUTO_PATH = "images/narutoSprites.png";
    private final String DEIDARA_PATH = "images/deidaraSprites.png";
    private final String MAP_PATH = "images/narutomap.png";
    private final String KUNAI_PATH = "images/";
    private BufferedImage narutoSpriteSheet;
    private BufferedImage deidaraSpriteSheet;
    private BufferedImage kunaiLeft;
    private BufferedImage kunaiRight;
    private BufferedImage map;

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public Image[] getPlayerFrames(String type) {
        Image image[] = null;
        if (type.equals("idle")) {
            image = new Image[2];
            image[0] = narutoSpriteSheet.getSubimage(175,1061,20,56); //idle image looking right
            image[1] = narutoSpriteSheet.getSubimage(175,1194,20,56); //idle image looking left
        } else if (type.equals("right")) {
            image = new Image[8];
            image[0] = narutoSpriteSheet.getSubimage(5,538,40,52); //start right frames
            image[1] = narutoSpriteSheet.getSubimage(51,538,40,52);
            image[2] = narutoSpriteSheet.getSubimage(92,538,40,52);
            image[3] = narutoSpriteSheet.getSubimage(134,538,40,52);
            image[4] = narutoSpriteSheet.getSubimage(184,538,40,52);
            image[5] = narutoSpriteSheet.getSubimage(226,538,40,52);
            image[6] = narutoSpriteSheet.getSubimage(268,538,40,52);
            image[7] = narutoSpriteSheet.getSubimage(310,538,40,52); //end right frames
        } else if (type.equals("left")) {
            image = new Image[8];
            image[0] = narutoSpriteSheet.getSubimage(0,802,40,52); //start left frames
            image[1] = narutoSpriteSheet.getSubimage(50,802,40,52);
            image[2] = narutoSpriteSheet.getSubimage(90,802,40,52);
            image[3] = narutoSpriteSheet.getSubimage(134,802,40,52);
            image[4] = narutoSpriteSheet.getSubimage(176,802,40,52);
            image[5] = narutoSpriteSheet.getSubimage(226,802,40,52);
            image[6] = narutoSpriteSheet.getSubimage(268,802,40,52);
            image[7] = narutoSpriteSheet.getSubimage(310,802,40,52); //end left frames
        } else if (type.equals("down")) {
            image = new Image[1];
            image[0] = narutoSpriteSheet.getSubimage(105, 2175, 40, 52);
        } else if (type.equals("up")) {
            image = new Image[1];
            image[0] = narutoSpriteSheet.getSubimage(70, 2175, 30, 52);
        } else if (type.equals("doubleJump")) {
            image = new Image[8];
            image[0] = narutoSpriteSheet.getSubimage(0, 5768,40,52 );
//            for (int i = 1; i < 8; i++) {
//                image[i] = rotateImage((BufferedImage)image[i - 1]);
//            }
        }
        return image;
    }

    public Image getKunaiFrames(String type) {
        Image image = null;
        if (type.equals("right")) {
            image = kunaiRight.getSubimage(0,0,20,9);
        } else if (type.equals("left")) {
            image = kunaiLeft.getSubimage(0,0,20,9);
        }
        return image;
    }

    public Image getMapImage() {
        return map;
    }


    private ImageLoader() {
        loadNarutoSpriteSheet();
        loadKunais();
        loadDeidaraSpriteSheet();
        loadMap();
    }

//    private Image rotateImage(BufferedImage image) {
//
//    }

    private void loadDeidaraSpriteSheet()  {
        try {
            deidaraSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(DEIDARA_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadKunais() {
        try {
            kunaiLeft = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(KUNAI_PATH + "t.png"));
            kunaiRight = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(KUNAI_PATH + "aa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNarutoSpriteSheet() {
        try {
            narutoSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(NARUTO_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap() {
        try {
            map = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
