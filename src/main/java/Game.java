import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        arena= new Arena(width,height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory=terminalFactory.setInitialTerminalSize(terminalSize);
        Terminal terminal= terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    };
    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType()==KeyType.EOF)break;
            processKey(key);
            if(arena.verifyMonsterCollisions()){
                System.out.println("GAME OVER");
                screen.close();
            }
        }
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character && key.getCharacter()=='q')screen.close();
        arena.processKey(key);
    }


}
