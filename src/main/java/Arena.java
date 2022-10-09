import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private Hero hero= new Hero(10,10);
    private int width,height;
    public Arena(int width, int height){
        this.height=height;
        this.width=width;
    }
    public void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());

    }
    public void draw(Screen screen){
        hero.draw(screen);
    }
    private void moveHero(Position position){
        if(canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position position){
        if(position.getX()>=width)return false;
        if(position.getX()<0)return false;
        if(position.getY()>=height)return false;
        if(position.getY()<0)return false;
        return true;
    }
}
