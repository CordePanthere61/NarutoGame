package cegepst;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    private static ImageLoader instance;
    private final String NARUTO_PATH = "images/narutoSprites.png";
    private final String NARUTO_PATH2 = "images/narutoSprites2.png";
    private final String NARUTO_DOUBLE_JUMP_PATH = "images/doubleJumpImages/";
    private final String DEIDARA_PATH = "images/deidaraSprites.png";
    private final String KAKASHI_PATH = "images/kakashiSpriteSheet.png";
    private final String LOG_PATH = "images/log.png";
    private final String MAP_PATH = "images/map.png";
    private final String KUNAI_PATH = "images/";
    private BufferedImage narutoSpriteSheet;
    private BufferedImage narutoSpriteSheet2;
    private BufferedImage[] narutoRollLeftImages = new BufferedImage[4];
    private BufferedImage[] narutoRollRightImages = new BufferedImage[4];
    private BufferedImage kakashiSpriteSheet;
    private BufferedImage deidaraSpriteSheet;
    private BufferedImage kunaiLeft;
    private BufferedImage kunaiRight;
    private BufferedImage map;
    private BufferedImage logImage;

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
            image[1] = narutoSpriteSheet.getSubimage(50,538,40,52);
            image[2] = narutoSpriteSheet.getSubimage(92,538,40,52);
            image[3] = narutoSpriteSheet.getSubimage(134,538,40,52);
            image[4] = narutoSpriteSheet.getSubimage(183,538,40,52);
            image[5] = narutoSpriteSheet.getSubimage(226,538,40,52);
            image[6] = narutoSpriteSheet.getSubimage(268,538,40,52);
            image[7] = narutoSpriteSheet.getSubimage(310,538,40,52); //end right frames
        } else if (type.equals("left")) {
            image = new Image[8];
            image[0] = narutoSpriteSheet.getSubimage(0,802,40,52); //start left frames
            image[1] = narutoSpriteSheet.getSubimage(50,802,40,52);
            image[2] = narutoSpriteSheet.getSubimage(92,802,40,52);
            image[3] = narutoSpriteSheet.getSubimage(134,802,40,52);
            image[4] = narutoSpriteSheet.getSubimage(176,802,40,52);
            image[5] = narutoSpriteSheet.getSubimage(226,802,40,52);
            image[6] = narutoSpriteSheet.getSubimage(268,802,40,52);
            image[7] = narutoSpriteSheet.getSubimage(310,802,40,52); //end left frames
        } else if (type.equals("down")) {
            image = new Image[1];
            image[0] = narutoSpriteSheet.getSubimage(102, 924, 32, 52);
        } else if (type.equals("up")) {
            image = new Image[1];
            image[0] = narutoSpriteSheet.getSubimage(70, 2175, 30, 52);
        } else if (type.equals("doubleJump")) {
            image = new Image[4];
            image[0] = narutoRollRightImages[0].getSubimage(0, 8, 40, 56);
            image[1] = narutoRollRightImages[1].getSubimage(0, 8, 40, 56);
            image[2] = narutoRollRightImages[2].getSubimage(0, 8, 40, 56);
            image[3] = narutoRollRightImages[3].getSubimage(0, 8, 40, 56);
        } else if (type.equals("fireRight")) {
            image = new Image[7];
            image[0] = narutoSpriteSheet.getSubimage(0, 2247,32,56);
            image[1] = narutoSpriteSheet.getSubimage(37, 2247,40,56);
            image[2] = narutoSpriteSheet.getSubimage(82, 2247,40,56);
            image[3] = narutoSpriteSheet.getSubimage(128, 2247,40,56);
            image[4] = narutoSpriteSheet.getSubimage(174, 2247,40,56);
            image[5] = narutoSpriteSheet.getSubimage(220, 2247,40,56);
            image[6] = narutoSpriteSheet.getSubimage(263, 2247,40,56);
        } else if (type.equals("fireLeft")) {
            image = new Image[7];
            image[0] = narutoSpriteSheet.getSubimage(0, 2510,32,56);
            image[1] = narutoSpriteSheet.getSubimage(37, 2510,40,56);
            image[2] = narutoSpriteSheet.getSubimage(82, 2510,40,56);
            image[3] = narutoSpriteSheet.getSubimage(128, 2510,40,56);
            image[4] = narutoSpriteSheet.getSubimage(174, 2510,40,56);
            image[5] = narutoSpriteSheet.getSubimage(220, 2510,40,56);
            image[6] = narutoSpriteSheet.getSubimage(263, 2510,40,56);
        } else if (type.equals("log")) {
            image = new Image[5];
            image[0] = narutoSpriteSheet2.getSubimage(600, 1729,42, 55);
            image[1] = narutoSpriteSheet2.getSubimage(652, 1721,40, 56);
            image[2] = narutoSpriteSheet2.getSubimage(700, 1711,46, 56);
            image[3] = narutoSpriteSheet2.getSubimage(758, 1708,46, 56);
            image[4] = narutoSpriteSheet2.getSubimage(817, 1704,47, 56);
        }
        return image;
    }

    public Image[] getDeidaraFrames(String type) {
        Image image[] = null;
        if (type.equals("idle")) {
            image = new Image[2];
            image[0] = deidaraSpriteSheet.getSubimage(18, 203, 28, 55);
            image[1] = deidaraSpriteSheet.getSubimage(261, 203, 28, 55);
        } else if (type.equals("right")) {
            image = new Image[6];
            image[0] = deidaraSpriteSheet.getSubimage(19, 459, 56, 52);
            image[1] = deidaraSpriteSheet.getSubimage(87, 459, 42, 52);
            image[2] = deidaraSpriteSheet.getSubimage(136, 459, 43, 52);
            image[3] = deidaraSpriteSheet.getSubimage(187, 459, 53, 52);
            image[4] = deidaraSpriteSheet.getSubimage(251, 459, 40, 52);
            image[5] = deidaraSpriteSheet.getSubimage(304, 459, 48, 52);
        } else if (type.equals("left")) {
            image = new Image[6];
            image[0] = deidaraSpriteSheet.getSubimage(436, 461, 56, 52);
            image[1] = deidaraSpriteSheet.getSubimage(505, 461, 42, 52);
            image[2] = deidaraSpriteSheet.getSubimage(560, 461, 43, 52);
            image[3] = deidaraSpriteSheet.getSubimage(613, 461, 53, 52);
            image[4] = deidaraSpriteSheet.getSubimage(675, 461, 40, 52);
            image[5] = deidaraSpriteSheet.getSubimage(725, 461, 48, 52);
        } else if (type.equals("down")) {
            image = new Image[1];
            image[0] = deidaraSpriteSheet.getSubimage(452, 570, 36, 67);
        } else if (type.equals("up")) {
            image = new Image[1];

        } else if (type.equals("fireRight")) {
            image = new Image[4];
            image[0] = deidaraSpriteSheet.getSubimage(17, 1617, 31, 67);
            image[2] = deidaraSpriteSheet.getSubimage(59, 1617, 47, 67);
            image[1] = deidaraSpriteSheet.getSubimage(118, 1617, 52, 67);
            image[3] = deidaraSpriteSheet.getSubimage(176, 1617, 42, 67);
        } else if (type.equals("fireLeft")) {
            image = new Image[4];
            image[0] = deidaraSpriteSheet.getSubimage(293, 1617, 31, 67);
            image[1] = deidaraSpriteSheet.getSubimage(335, 1617, 50, 67);
            image[2] = deidaraSpriteSheet.getSubimage(397, 1617, 52, 67);
            image[3] = deidaraSpriteSheet.getSubimage(461, 1617, 41, 67);
        }
        return image;
    }

    public Image[] getKakashiFrames(String type) {
        Image image[] = null;
        if (type.equals("idle")) {
            image = new Image[2];
            image[0] = kakashiSpriteSheet.getSubimage(99,479,28,67); //idle image looking right
            image[1] = kakashiSpriteSheet.getSubimage(418,479,28,67); //idle image looking left
        } else if (type.equals("right")) {
            image = new Image[6];
            image[0] = kakashiSpriteSheet.getSubimage(23, 610, 58, 56);
            image[1] = kakashiSpriteSheet.getSubimage(91, 610, 42, 56);
            image[2] = kakashiSpriteSheet.getSubimage(142, 610, 51, 56);
            image[3] = kakashiSpriteSheet.getSubimage(201, 610, 55, 56);
            image[4] = kakashiSpriteSheet.getSubimage(270, 610, 47, 56);
            image[5] = kakashiSpriteSheet.getSubimage(329, 610, 51, 56);
        } else if (type.equals("left")) {
            image = new Image[6];
            image[0] = kakashiSpriteSheet.getSubimage(503, 610, 56, 56);
            image[1] = kakashiSpriteSheet.getSubimage(569, 610, 42, 56);
            image[2] = kakashiSpriteSheet.getSubimage(622, 610, 50, 56);
            image[3] = kakashiSpriteSheet.getSubimage(684, 610, 55, 56);
            image[4] = kakashiSpriteSheet.getSubimage(747, 610, 48, 56);
            image[5] = kakashiSpriteSheet.getSubimage(804, 610, 52, 56);
        } else if (type.equals("meleeLeft")) {
            image = new Image[3];
            image[0] = kakashiSpriteSheet.getSubimage(383, 2067, 40, 56);
            image[1] = kakashiSpriteSheet.getSubimage(321, 2067, 51, 56);
            image[2] = kakashiSpriteSheet.getSubimage(268, 2067, 45, 56);
        } else if (type.equals("meleeRight")) {
            image = new Image[3];
            image[0] = kakashiSpriteSheet.getSubimage(17, 2067, 45, 56);
            image[1] = kakashiSpriteSheet.getSubimage(72, 2067, 50, 56);
            image[2] = kakashiSpriteSheet.getSubimage(130, 2067, 40, 56);
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

    public Image getLogImage() {
        return logImage;
    }

    public void loadLogImage() {
        try {
            logImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(LOG_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getMapImage() {
        return map;
    }


    private ImageLoader() {
        loadNarutoSpriteSheet();
        loadKunais();
        loadDeidaraSpriteSheet();
        loadKakashiSpriteSheet();
        loadMap();
        loadLogImage();
    }

    private void loadDeidaraSpriteSheet()  {
        try {
            deidaraSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(DEIDARA_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadKakashiSpriteSheet()  {
        try {
            kakashiSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(KAKASHI_PATH));
            kakashiSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(KAKASHI_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadKunais() {
        try {
            kunaiLeft = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(KUNAI_PATH + "kunaiLeft.png"));
            kunaiRight = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(KUNAI_PATH + "kunaiRight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNarutoSpriteSheet() {
        try {
            narutoSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(NARUTO_PATH));
            narutoSpriteSheet2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(NARUTO_PATH2));
            for (int i = 0; i < 4; i++) {
                narutoRollLeftImages[i] = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(NARUTO_DOUBLE_JUMP_PATH + "left" + (i + 1) + ".png"));
                narutoRollRightImages[i] = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(NARUTO_DOUBLE_JUMP_PATH + "right" + (i + 1) + ".png"));
            }
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
