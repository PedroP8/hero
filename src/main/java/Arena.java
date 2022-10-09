import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private Hero hero= new Hero(10,10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private int width,height;
    public Arena(int width, int height){
        this.height=height;
        this.width=width;
        this.walls=createWalls();
        this.coins=createCoins();
        this.monsters=createMonsters();
    }
    public void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());

    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin: coins)
            coin.draw(graphics);
        for(Monster monster: monsters)
            monster.draw(graphics);
    }
    private void moveHero(Position position){
        if(canMoveTo(position)){
            hero.setPosition(position);
            retrieveCoins();
            moveMonsters();
        }
    }
    private boolean canMoveTo(Position position){
        if(position.getX()>=width)return false;
        if(position.getX()<0)return false;
        if(position.getY()>=height)return false;
        if(position.getY()<0)return false;
        for(Wall wall: walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }
    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        boolean canAdd;
        Position position;
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5;){
            position=new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            canAdd = true;
            for(Coin coin:coins){
                if (coin.getPosition().equals(position)){
                    canAdd=false;
                    break;
                }
            }
            if(hero.getPosition().equals(position)) canAdd=false;
            if(canAdd){
                coins.add(new Coin(position.getX(), position.getY()));
                i++;
            }
        }
        return coins;
    }
    private void retrieveCoins(){
        for(Coin coin:coins)
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
        if(coins.size()==0)coins=createCoins();
    }
    private List<Monster> createMonsters(){
        boolean canAdd;
        Position position;
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5;){
            position=new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            canAdd = true;
            for(Monster monster:monsters){
                if (monster.getPosition().equals(position)){
                    canAdd=false;
                    break;
                }
            }
            if(hero.getPosition().equals(position)) canAdd=false;
            if(canAdd){
                monsters.add(new Monster(position.getX(), position.getY()));
                i++;
            }
        }
        return monsters;
    }
    public boolean verifyMonsterCollisions(){
        for(Monster monster:monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                return true;
            }
        }
        return false;
    }
    private void moveMonsters(){
        for(Monster monster:monsters){
            Position position=monster.move();
            if(canMoveTo(position))monster.setPosition(position);
        }
    }

}
