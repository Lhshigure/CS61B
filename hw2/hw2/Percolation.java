package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int[] openArray;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF backwashGrid;

    private void validate(int i){
        if(i < 0 || i > this.n - 1){
            throw new IndexOutOfBoundsException("the index should be between in 0 and " + (this.n - 1));
        }
    }
    private void checkUnion(int row, int col){
        int up = (row) * (n+2) + col + 1;
        int down = (row+2) * (n+2) + col + 1;
        int left = (row+1) * (n+2) + col;
        int right = (row+1) * ( n+2) + col + 2;

        int index = (row+1) * (n+2) + col + 1; // current open 的位置

        if(openArray[up] == 1){
            grid.union(up, index);
            backwashGrid.union(up, index);
        }
        if(openArray[down] == 1){
            grid.union(down, index);
            backwashGrid.union(down, index);
        }
        if(openArray[left] == 1){
            grid.union(left,index);
            backwashGrid.union(left, index);
        }
        if(openArray[right] == 1){
            grid.union(right, index);
            backwashGrid.union(right, index);
        }
    }
    //在openArray周围加上一圈0，
    private int[] creatOpenArray(int N){
        openArray = new int[(N+2) * (N+2)];
        this.n = N;
        return openArray;
    }

    // 0 = block, 1 = open
    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException("N shold be positive number");
        }

        this.openArray = creatOpenArray(N);
        // 在grid周围加上一圈
        int num = (N+2)*(N+2);
        this.grid = new WeightedQuickUnionUF(num);
        this.backwashGrid =  new WeightedQuickUnionUF(num);

        //第二行和virtual up side(0,0) connect
        for(int i = 0; i < n; i++){
            grid.union(0,(n+2)+i);
            backwashGrid.union(0,(n+2)+i);
        }

        //倒数第二行和virtul bottom side(n-1,n-1) connect
        for(int i = 0; i<n; i++){
            grid.union((n+2)*(n+2)-1,(n)*(n+2) + 1 + i);
        }
    }

    public void open(int row, int col){
        validate(row);
        validate(col);
        int index = (row+1) * (n+2) + col + 1;
        openArray[index] = 1;
        //System.out.println(openArray[index]);
        // 检查四周,判断是否需要union
        checkUnion(row, col);
    }
    public int numberOfOpenSites(){
        int count = 0;
        for(int i = 0; i < (n+2)*(n+2); i++){
            if(openArray[i] == 1){
                count += 1;
            }
        }
        return count;
    }
    public boolean isOpen(int row, int col){
        validate(row);
        validate(col);
        int index = (row+1) * (n+2) + col + 1;
        return openArray[index] == 1;
    }

    public boolean isFull(int row, int col){
        validate(row);
        validate(col);
        //virtual top side = （0.0）
        // virtual bottom size = (n-1, n-1)
        return isOpen(row, col)&&backwashGrid.connected(0, (row+1)*(n+2) + col + 1);
    }

    public boolean percolates(){
        return grid.connected(0, (n+2)*(n+2)-1);
    }

    public static void main(String[] arg){
        Percolation p = new Percolation(5);
        // test backwash
        p.open(0,1);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(4,1);

        System.out.println( p.isFull(4,1));
        System.out.println(p.percolates());
        p.open(2,3);
        p.open(3,3);
        p.open(4,3);
        System.out.println(p.isFull(3,3));


    }

}
