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
    private int x=10;
    private int y=10;
    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
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
        }
    }
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y , TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) y--;
        if (key.getKeyType() == KeyType.ArrowDown)y++;
        if (key.getKeyType() == KeyType.ArrowLeft)x--;
        if (key.getKeyType() == KeyType.ArrowRight)x++;
        if (key.getKeyType() == KeyType.Character && key.getCharacter()=='q')screen.close();
    }

}
