import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Coin extends Element{
    public Coin(int x,int y) {
        super(x,y);
    }
    public void draw(TextGraphics graphics){
        super.draw(graphics,"#FFFF00","0");
    }

}
