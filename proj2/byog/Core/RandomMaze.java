package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private static long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public void seed(int x){
        this.SEED = x;
    }


    static class P{
        int x;
        int y;

        int height;
        int width;
        public P(int x, int y, int width, int height ){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    //生成一个随机的位置（x,y) 同时随机生成矩阵的长，宽
    public static P addRandomPosition(){
        int x = RANDOM.nextInt(WIDTH-10) + 2;
        int y = RANDOM.nextInt(HEIGHT-10) + 2;
        int height = RANDOM.nextInt(10) + 2;
        int width =  RANDOM.nextInt(10) + 2;

        P p = new P(x,y, width, height);
        return p;
    }

    //在生成(x,y)位置的矩阵前，先检查该位置是否已经有了矩阵
    private static boolean overlap(P p, int height, int width, TETile[][] world, boolean condition){
        if(condition){
            for(int x = p.x; x < p.x + width + 2; x += 1){
                for(int y = p.y; y < p.y + height + 2; y += 1){
                    if(!world[x][y].equals(Tileset.NOTHING)){
                        return false;
                    }
                }
            }
            return true;
        }
       return false;
    }
    //在生成(x,y)位置的矩阵前，先检查矩阵是否回超出world的范围
    private static boolean outOfrange(P p, int height, int width){
        if(p.x + width + 3 > WIDTH){
            return false;
        } else if (p.y + height + 3 > HEIGHT) {
            return false;
        }
        return true;
    }

    //分奇偶，计算height和width的一半
    public static int half(int x){
        if(x % 2 == 0){
            x = x / 2;
        }else{
            x = (x + 1) / 2;
        }
        return x;
    }

    //计算出该位置矩阵的中心位置
    public static P centerP(P p){
        int x = p.x + half(p.width);
        int y = p.y + half(p.width);

        P p_center = new P(x,y,p.width, p.height);
        return p_center;
    }

    //生成一系列的随机的位置
    public static P[] addPosition(){
        P[] ps = new P[300];
        //记录每个矩阵的位置
        for(int i = 0; i< ps.length; i++){
            ps[i] = addRandomPosition();
        }
        return ps;
    }

    // 在（x,y）生成一个矩形
    public static P[] addRectangle(TETile[][] world, P[] ps){
        // 记录每个矩阵的位置
        List<P> temp = new ArrayList<>();

        //生成矩阵
        for(int i = 0; i < ps.length; i++){
            // check if out of range
            boolean condition = outOfrange(ps[i], ps[i].height, ps[i].width);
            //check if overlap
            boolean condition2 = overlap(ps[i], ps[i].height, ps[i].width, world, condition);
            if(condition2){
                //填充矩形的边缘
                for (int x = ps[i].x; x < ps[i].x + ps[i].width+2; x+=1){
                    world[x][ps[i].y] = Tileset.WALL;
                    world[x][ps[i].y+ps[i].height+1] = Tileset.WALL;
                }
                for(int y = ps[i].y; y < ps[i].y + ps[i].height+2; y+=1){
                    world[ps[i].x][y] = Tileset.WALL;
                    world[ps[i].x + ps[i].width+1][y] = Tileset.WALL;
                }
                //填充矩形的内部
                for (int x = ps[i].x + 1; x < ps[i].x+ps[i].width + 1; x += 1) {
                    for (int y = ps[i].y + 1; y <ps[i].y+ps[i].height + 1; y += 1) {
                        world[x][y] = Tileset.FLOOR;
                    }
                }
            temp.add(centerP(ps[i]));
            }
        }
        return temp.toArray(new P[0]);
    }

    //将位置P按照x的大小进行排序
    public static P[] sortPostionByX(P[] ps){
        for(int i = 0; i < ps.length - 1; i++){
            for(int j = 0; j < ps.length - 1 - i; j++){
                if(ps[j].x > ps[j + 1].x){
                    P tempP = ps[j + 1];
                    ps[j + 1] = ps[j];
                    ps[j] = tempP;
                }
            }
        }
        return ps;
    }
    // 将已排序中心位置连接起来
    public static void connectCenter(P[] sorted, TETile[][] world){
        for(int i = 0; i < sorted.length - 1; i++){
            P center1 = sorted[i];
            P center2 = sorted[i+1];
            if(center1.y <= center2.y){
                //生成L形hallway的｜
                for(int x = center1.x; x < center1.x + 1; x++){
                    for(int y = center1.y; y < center2.y; y++){
                        world[x][y] = Tileset.FLOOR;
                    }
                }
                //生成L形hallway的——
                for(int x = center1.x; x < center2.x; x++){
                    for(int y = center2.y; y <center2.y + 1; y++){
                        world[x][y] = Tileset.FLOOR;
                    }
                }
            }else{
                //生成L形hallway的｜
                for(int x = center2.x; x < center2.x + 1; x++){
                    for(int y = center2.y - 1; y < center1.y; y++){
                        world[x][y] = Tileset.FLOOR;
                    }
                }
                //生成L形hallway的——
                for(int x = center2.x; x > center1.x; x--){
                    for(int y = center1.y; y <center1.y + 1; y++){
                        world[x][y] = Tileset.FLOOR;
                    }
                }
            }

        }
    }

    //给hallway的周围填充tiles
    public static void hallwayFill(TETile[][] world){
        for(int x = 1; x < WIDTH - 1; x ++ ){
            for(int y = 1; y < HEIGHT - 1; y++){
                if(world[x][y].equals(Tileset.FLOOR)){
                    // ｜型，两边都是notiog
                    if(world[x-1][y].equals(Tileset.NOTHING) && world[x+1][y].equals(Tileset.NOTHING)){
                        world[x-1][y] = Tileset.WALL;
                        world[x+1][y] = Tileset.WALL;
                    }
                    // ｜型，一边是nothing，一边是wall
                    if(world[x-1][y].equals(Tileset.NOTHING) && world[x+1][y].equals(Tileset.WALL)){
                        world[x-1][y] = Tileset.WALL;
                    }
                    if(world[x-1][y].equals(Tileset.WALL) && world[x+1][y].equals(Tileset.NOTHING)){
                        world[x+1][y] = Tileset.WALL;
                    }
                    // ｜型，一边是nothing，一边是Floor
                    if(world[x-1][y].equals(Tileset.NOTHING) && world[x+1][y].equals(Tileset.FLOOR)){
                        world[x-1][y] = Tileset.WALL;
                    }
                    if(world[x-1][y].equals(Tileset.FLOOR) && world[x+1][y].equals(Tileset.NOTHING)){
                        world[x+1][y] = Tileset.WALL;
                    }
                    // --型，上下都是nothing
                    if(world[x][y-1].equals(Tileset.NOTHING) && world[x][y+1].equals(Tileset.NOTHING)){
                        world[x][y-1] = Tileset.WALL;
                        world[x][y+1] = Tileset.WALL;
                    }
                    // --型，上下是nothing或floor
                    if(world[x][y-1].equals(Tileset.FLOOR) && world[x][y+1].equals(Tileset.NOTHING)){
                        world[x][y+1] = Tileset.WALL;
                    }
                    if(world[x][y-1].equals(Tileset.NOTHING) && world[x][y+1].equals(Tileset.FLOOR)){
                        world[x][y-1] = Tileset.WALL;
                    }
                    //--型，上下是nothing或wall
                    if(world[x][y-1].equals(Tileset.WALL) && world[x][y+1].equals(Tileset.NOTHING)){
                        world[x][y+1] = Tileset.WALL;
                    }
                    if(world[x][y-1].equals(Tileset.NOTHING) && world[x][y+1].equals(Tileset.WALL)){
                        world[x][y-1] = Tileset.WALL;
                    }
                }
            }
        }
    }
    //生成地图和随机迷宫
    public TETile[][] start(){
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

        P[] ps = addPosition();
        P[] psCenter = addRectangle(world, ps);
        P[] sorted = sortPostionByX(psCenter);
        connectCenter(sorted, world);
        hallwayFill(world);

        // draws the world to the screen
        ter.renderFrame(world);
        return world;
    }
}
