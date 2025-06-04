package triangle;

import resizable.ResizableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math.*;
import static resizable.Debug.print;

/**
 * Implement your Sierpinski Triangle here.
 *
 *
 * You only need to change the drawTriangle
 * method!
 *
 *
 * If you want to, you can also adapt the
 * getResizeImage() method to draw a fast
 * preview.
 *
 */
public class Triangle implements ResizableImage {
    int drawTriangle = 0;
    /**
     * change this method to implement the triangle!
     * @param size the outer bounds of the triangle
     * @return an Image containing the Triangle
     */
    private BufferedImage drawTriangle(Dimension size) {
        print("drawTriangle: " + ++drawTriangle + "size: " + size);
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.gray);
        if((int)((Math.sqrt(3)*size.width)/2) < size.height) {
            int[] xPoints = {0, size.width / 2, size.width};
            int[] yPoints ={0, (int) ((Math.sqrt(3) * size.width) / 2), 0};
            drawTriangle(gBuffer, xPoints, yPoints);
        }
        else {
            int halfLength = (int)((2*size.height)/Math.sqrt(3))/2;
            int[] xPoints = {(size.width/2)- halfLength,size.width/2,(size.width/2)+ halfLength};
            int[] yPoints ={0,size.height,0};
            drawTriangle(gBuffer, xPoints, yPoints);
        }
        return bufferedImage;
    }



    private void drawTriangle(Graphics2D gBuffer, int xPoints[], int[] yPoints){
        gBuffer.fillPolygon(xPoints, yPoints, 3);

    }
    BufferedImage bufferedImage;
    Dimension bufferedImageSize;


    @Override
    public Image getImage(Dimension triangleSize) {
        if (triangleSize.equals(bufferedImageSize))
            return bufferedImage;
        bufferedImage = drawTriangle(triangleSize);
        bufferedImageSize = triangleSize;
        return bufferedImage;
    }
    @Override
    public Image getResizeImage(Dimension size) {
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.pink);
        int border = 2;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        return bufferedImage;
    }
}
