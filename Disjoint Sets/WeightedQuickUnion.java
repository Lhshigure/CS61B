public class WeightedQuickUnion {
    private int[]parents;
    public WeightedQuickUnion(int n){
        parents = new int[n];
        for(int i = 0; i<n; i++){
            parents[i] = -1;
        }
    }
    public void validate(int v1){
        if(!(v1 > 0 && v1 < parents.length)){
            throw new IllegalArgumentException("This is not a valid index");
        }
    }
    public int sizeOf(int v1){
        validate((v1));
        return -parents[find(v1)];
    }
    public int parent(int v1){
        validate(v1);
        return find(v1);
    }
    public int find(int v1){
        validate(v1);
        while(parents[v1] >= 0){
            v1 = parents[v1];
        }
        return v1;
    }
    public boolean connected(int v1, int v2){
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2){
        validate(v1);
        validate(v2);

        int size1 = -parents[find(v1)];
        int size2 = -parents[find(v2)];
        if(size1 >= size2){
            parents[find(v1)] += parents[find(v2)];
            parents[find(v2)] = find(v1);
        }else{
            parents[find(v2)] += parents[find(v1)];
            parents[find(v1)] = find(v2);
        }
    }

}
