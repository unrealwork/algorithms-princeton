package week1.unionfid;


public interface UF {
    boolean connected(int p, int q);

    void union(int p, int q);
}
