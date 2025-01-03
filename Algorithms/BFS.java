package Algorithms;
import java.util.*;

// To run : 
// javac Algorithms/TreeNode.java Algorithms/BFS.java
// java Algorithms.BFS

public class BFS {

   // Topological Sort - given an 2d int array of start and destination of size n
   // mimics course schedule problem
   private void TopoSort(int[][] arr, int n) {
      if (n <= 0) return;

      HashMap<Integer, Integer> inDegree = new HashMap<>();
      HashMap<Integer, List<Integer>> topoMap = new HashMap<>();
      for (int i = 0; i < n; i++) {
         inDegree.put(i, 0);
         topoMap.put(i, new ArrayList<>());
      }

      for (int[] pair : arr) {
         int course = pair[0], prereq = pair[1];
         topoMap.get(prereq).add(course);
         inDegree.put(course, inDegree.get(course) + 1);
      }

      int[] res = new int[n];
      int idx = 0;
      while (!inDegree.isEmpty()) {
         boolean flag = false;
         for (int key : new ArrayList<>(inDegree.keySet())) {
            if (inDegree.get(key) == 0) {
               res[idx++] = key;
               List<Integer> children = topoMap.get(key);
               for (int child : children) {
                  inDegree.put(child, inDegree.get(child) - 1);
               }
               inDegree.remove(key);
               flag = true;
               break;
            }
         }
         if (!flag) {
            return;
         }
      }
      System.out.print("[");
      for (int val : res) {
         System.out.print(val + ",");
      }
      System.out.println("]");
   }

   
   // BFS traversal of a tree
   private void BFSTreeTraversal(TreeNode root) {
      if (root == null) return;
      Queue<TreeNode> q = new LinkedList<>();
      List<Integer> traversal = new ArrayList<>();

      // add root to q and run through it
      q.add(root);
      while (!q.isEmpty()) {
         // pull top of q and add it to traversal list
         TreeNode node = q.poll();
         traversal.add(node.val);
         // add left then right node if possible to queue
         if (node.left != null) q.offer(node.left);
         if (node.right != null) q.offer(node.right);
      }

      // print out traversal
      System.out.print("[");
      for (int val : traversal) {
         System.out.print(val + ",");
      }
      System.out.println("]");
   }

   public static void main(String[] args) {

      TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );

      BFS bfs = new BFS();
      bfs.BFSTreeTraversal(root);

      int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
      int numCourses = 4;
      bfs.TopoSort(prerequisites, numCourses);
      
   }
}
