import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x, int y){super(x,y);}
    public void draw(TextGraphics graphics){
        super.draw(graphics,"#AA2222","P");
    }
    public Position move(){
        Random random= new Random();
        switch (random.nextInt(4)){
            case 0: return new Position(position.getX(), position.getY() - 1);
            case 1: return new Position(position.getX(), position.getY() + 1);
            case 2: return new Position(position.getX() - 1, position.getY());
            default: return new Position(position.getX() + 1, position.getY());
        }
    }
}
