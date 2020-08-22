package UnionFind;

/**
 * Problem: is there a path connecting two objects?
 *
 * Quick-Find [eager approach]
 *
 * - it's convenient to use array, name objects 0 to N-1 as index.
 *
 * array index - "object"
 * array value - "component identifier"
 *             -(same array value for different index means the two index(object)
 *             are connected)
 *
 * - if N union commands on N objects, it takes N^2 array accesses
 */
public class QuickFind {

    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;          // set array value to index
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        for(int i = 0; i < id.length - 1; i++) {
            if(id[i] == id[p]) {
                id[i] = id[q];      //for all objects except q, set all related objects'(in the same component as p)
                                    // component as q's component (at most 2N+2 array accesses)
            }
        }
    }
}
