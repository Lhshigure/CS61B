package hw2;
import edu.princeton.cs.introcs.StdRandom; // 生成随机数
import edu.princeton.cs.introcs.StdStats; // 计算样本均值和标准差
public class PercolationStats {
    private double[] thresholds; // 用来存放每一次的实验的threshold

    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException("Both N and T must be positive");
        }
        thresholds = new double[T];
        for(int i = 0; i < T; i++){
            Percolation p = pf.make(N);
            while(!p.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            double threshold = (double)p.numberOfOpenSites() / (N * N);
            thresholds[i] = threshold;
        }
    }

    //计算给定数组的均值
    public double mean(){
        return StdStats.mean(thresholds);
    }
    //计算给定数组的标准差
    public double stddev(){
        return StdStats.stddev(thresholds);
    }
    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(thresholds.length);
    }

    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(thresholds.length);
    }
}
