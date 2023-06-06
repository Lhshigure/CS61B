package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 45;
    private static final int HEIGHT = 45;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.TREE;
            default: return Tileset.SAND;

        }
    }
    static class P{
        int x;
        int y;
        public P(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    //生成一个六边形
    private static void helpHexagon(int s, TETile[][] tiles, int x, int y){
        int middle = s + (s-1) * 2;
        P p = new P(x,y);
        //左下角为（p_x，p_y）
        // 上半
        TETile randomtile = randomTile();
        for(int j = 0; j < s; j++){
            for(int i = 0; i < s + j*2 ; i++){
                tiles[p.x + i + (s - j - 1)][p.y + j] = randomtile;
            }
        }
        //下半
        for(int j = 0; j < s; j++){
            for(int i = 0; i < middle - (j * 2); i++){
                tiles[p.x + i + j][p.y + j + s] = randomtile;
            }
        }

    }
    public static void addHexagon(int s, int x, int y, TETile[][] tiles){
        //在（x，y）位置创建一个new hexagon
        helpHexagon(s, tiles, x, y);

        // 有点类似向量的移动方式
        for(int i = 1; i < s; i++){
            // 对于四个角, dx=(2*s-1), dy = (2*s-1)-(s-1)
            int dx = i*(2*s-1);
            int dy = i*s;
            //在右上角
            helpHexagon(s, tiles,x + dx, y + dy);
            //在左上角
            helpHexagon(s, tiles, x - dx, y + dy);
            //在左下角
            helpHexagon(s,tiles, x - dx, y - dy);
            //右下角
            helpHexagon(s, tiles, x + dx, y - dy);

        }
        for(int i = 1; i < s; i++){
            //对于正上方或正下方， dx = 0，dy = (2*s-1)+1
            int dy_vertical= i*(2*s-1)+i;
            //在正上方（x, y+(2*s-1)+1）
            helpHexagon(s, tiles, x, y + dy_vertical);
            //在正下方(x, y - (2*s-1)-1)
            helpHexagon(s, tiles, x, y - dy_vertical);
        }

        //类似于复合向量的移动
        for(int i = 1; i < s - 1; i ++){
            int dx = i*(2*s-1);
            int dy = i*s;
            int dy_vertical= i*(2*s-1)+i;

            helpHexagon(s, tiles,x + dx, y + dy + dy_vertical);
            helpHexagon(s, tiles,x - dx, y + dy + dy_vertical);
            helpHexagon(s, tiles,x + dx, y - dy - dy_vertical);
            helpHexagon(s, tiles,x - dx, y - dy - dy_vertical);

        }

        //也可以直接算
        //对于正左方或正右方，dx =  , dy = 0
        int dx_horizontal = 2*(2*s-1);
        helpHexagon(s, tiles, x+dx_horizontal, y);
        helpHexagon(s, tiles, x-dx_horizontal, y);
    }




    public static void main(String [] args){
        // 初始化渲染器
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // 生成地图
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        HexWorld.addHexagon(3, 20, 20, world);

        ter.renderFrame(world);
    }
}
