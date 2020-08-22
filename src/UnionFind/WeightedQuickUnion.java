package UnionFind;

/**
 * Problem: is there a path connecting two objects?
 *
 * - to avoid tall trees that might be happen in quick union
 * - keep track of size of each tree(number of objects)
 * - balance the tree by link root of smaller tree to root of larger tree.
 *      (make root of smaller tree child of root of larger tree)
 *      id[root(s)] = root[l]
 *
 *              initialize  union                           find
 * quick-find   N           N                               1
 *
 * quick-union  N           N(cost of finding roots)        N
 *
 * weighted     N           lgN                             lgN
 * quick-union
 *
 * when link root of smaller tree to root of larger tree,
 * from the point view of smaller tree, size at least doubles,
 * while the depth increases by 1
 *
 */
public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;   // used to track size of each tree

    public WeightedQuickUnion(int N) {
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;          // set array value to index
        }
        for(int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while(i != id[i]) {     // chase parent until reach root
            //path compression, make every other node in path child of its grandparent(halving path length)
            id[i] = id[id[i]];
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
        if(pRoot == qRoot) return;
        if(sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
