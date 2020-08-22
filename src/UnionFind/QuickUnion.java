package UnionFind;

/**
 *  Problem: is there a path connecting two objects?
 *
 * Quick Union [lazy approach]:
 * -id[i] is parent of i
 *
 * array index i - child
 * array value id[i] - parent
 *
 *              initialize  union                           find
 * quick-find   N           N                               1
 * quick-union  N           N(cost of finding roots)        N
 *
 * Quick find defect:
 *      - union too expensive
 *      - trees are flat, but too expensive to keep them flat
 *
 * Quick union defect:
 *      - trees can get tall
 *      - find too expensive(could be N array accesses)
 */
public class QuickUnion {

    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;          // set array value to index
        }
    }

    private int root(int i) {
        while(i != id[i]) {     // chase parent until reach root
            i = id[i];          // depth of i array accesses
        }
        return i;       // the root object
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);      // check if object p, q have same root(in the same component)
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        id[pRoot] = qRoot;  //make p's root a child of q's root
    }
}
