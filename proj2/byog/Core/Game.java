package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.princeton.cs.introcs.StdDraw.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    boolean gameOver;


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public Game(){
        //initialize StdDraw
        setCanvasSize(this.WIDTH * 16, this.HEIGHT * 16);
        setXscale(0, this.WIDTH);
        setYscale(0, this.HEIGHT);
        clear(Color.BLACK);
        enableDoubleBuffering();
    }


    public TETile[][] playWithKeyboard() {

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        String stringSeed = keyBoardInputForUI();
        long inputSeed = Long.parseLong(stringSeed);
        RandomMaze map = new RandomMaze();
        map.seed(inputSeed);
        TETile[][] finalWorldFrame = map.start(world,0,0);
        // draws the world to the screen
        ter.renderFrame(world);
        return finalWorldFrame;

    }
    public void drawFrame(){
        //StdDraw.clear();
        clear(Color.black);

        setFont(new Font("Monaco", Font.BOLD, 30));
        //draw UI

        setPenColor(WHITE);
        text(WIDTH / 2.0 - 1 , HEIGHT / 2.0 + 12, "CS61B : THE GAME");
        setFont(new Font("Monaco", Font.BOLD, 20));
        text(WIDTH / 2.0 , HEIGHT / 2.0, "New Game(N)");
        text(WIDTH / 2.0 , HEIGHT / 2.0 - 2, "Load Game(L)");
        text(WIDTH / 2.0 , HEIGHT / 2.0 - 4, "Quit(Q)");
        show();

    }
   public void drawFrame(String s ){
       //StdDraw.clear();
       clear(Color.black);
       Font bigFont = new Font("Monaco", Font.BOLD, 30);
       setFont(bigFont);
       setPenColor(Color.white);
       text(WIDTH / 2, HEIGHT / 2, s);
       show();

    }
    public String keyBoardInputForUI(){
        String input = "";
        drawFrame();
        boolean condition = true;
        while(condition){
            if(!hasNextKeyTyped()){
                continue;
            }
            char startInput = nextKeyTyped();
            //new game, set random seed
            if(startInput == 'N' || startInput == 'n'){
                while(condition){
                    if(!hasNextKeyTyped()){
                        continue;
                    }
                    char lastInput = nextKeyTyped();
                    if(lastInput == 'S' || lastInput == 's'){
                        condition = false;
                        break;
                    }
                    input += String.valueOf(lastInput);
                    drawFrame(input);
                }
            }
            //quit
            else if(startInput == 'Q' || startInput == 'q'){

            }
            //load last time
            else{

            }
        }
        pause(500);
        return input;
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        String numString = input.substring(1, input.length() - 1);
        long inputSeed = Long.parseLong(numString);
        RandomMaze map = new RandomMaze();
        map.seed(inputSeed);
        TETile[][] finalWorldFrame = map.start(world,0,0);
        playerControl(map,finalWorldFrame,ter);
        //TETile[][] finalWorldFrame2 = map.start(world,1,1);
        // draws the world to the screen
        //ter.renderFrame(world);
        return finalWorldFrame;

    }

    //根据键盘的输入（wsad）控制player在map上的print
    public static void playerControl(RandomMaze map, TETile[][] world, TERenderer ter){
        ter.renderFrame(world);
        boolean gameOver = false;
        while(!gameOver){
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            if(key == 'w' || key == 'W'){
                map.printPlayerMove(world,0,1);
                ter.renderFrame(world);

            } else if (key == 's' || key == 'S') {
                map.printPlayerMove(world,0,-1);
                ter.renderFrame(world);

            } else if (key == 'a' || key == 'A') {
                map.printPlayerMove(world,-1,0);
                ter.renderFrame(world);

            } else if (key == 'd' || key == 'd') {
                map.printPlayerMove(world,1,0);
                ter.renderFrame(world);

            }else{
            }
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        //TETile[][]world = game.playWithKeyboard();
        TETile[][] world = game.playWithInputString("N5197880843569031643S");
        //System.out.println(TETile.toString(world));

    }
}
