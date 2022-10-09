import com.googlecode.lanterna.graphics.TextGraphics;


public class Hero extends Element {

    public Hero(int x,int y){
        super(x,y);
    }
    public void draw(TextGraphics graphics){
        super.draw(graphics,"#FFFFFF","X");
    }
    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

}
