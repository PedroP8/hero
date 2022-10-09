import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    public Wall(int x,int y) {
        super(x,y);
    }
    public void draw(TextGraphics graphics){
        super.draw(graphics,"#222222","T");
    }
}
