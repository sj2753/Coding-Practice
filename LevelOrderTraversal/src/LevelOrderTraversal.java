import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class LevelOrderTraversal {

    /**
     * Leetcode average of levels in a tree code with and without using external data structure
     */
    private Double levelSum = 0.0;
    private int levelSize = 0;

    public List<Double> findAverageWithQueue(TreeNode root){
        if(root == null) return null;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<Double> answer = new ArrayList<Double>();

        queue.add(root);

        Double sum = 0.0;
        int tracker = 0;
        int qSize = queue.size();

        while(!queue.isEmpty()){
            if(tracker == qSize){
                answer.add(sum/qSize);
                qSize = queue.size();
                tracker = 0;
                sum = 0.0;
            }

            tracker++;
            TreeNode node = queue.poll();
            sum += node.val*1.0;

            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
//        As logic to calculate the average is at the beginning of loop, we need to calculate average one more time.

        if(tracker == qSize){
            answer.add(sum/qSize);
        }

        return answer;
    }

    public List<Double> findAverageWithoutQueue(TreeNode root){
        int height = getHeight(root);
        ArrayList<Double> result = new ArrayList<>();

        for(int i = 0; i < height; i++){
            levelSum = 0.0;
            levelSize = 0;

            givenLevelAverage(root, i);
            if(levelSize != 0)
                result.add(levelSum/levelSize);
        }

        return result;
    }
    public void printList(List<Double> printable){
        for(int i = 0; i < printable.size(); i++){
            System.out.print(printable.get(i)+" ");
        }
        System.out.println();
    }

    private int getHeight(TreeNode root){
        if(root == null)
            return 0;
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }

    private void givenLevelAverage(TreeNode root, int level){
        if(root == null || level < 0)
            return;
        if(level == 0){
            levelSize++;
            levelSum += root.val;
        }

        givenLevelAverage(root.left, level-1);
        givenLevelAverage(root.right, level-1);
    }


    public static void main(String[] args){
        LevelOrderTraversal level = new LevelOrderTraversal();
        LevelOrderTraversal.TreeNode root = level.new TreeNode(3);
        root.left =  level.new TreeNode(1);
        root.right = level.new TreeNode(4);
        root.left.right = level.new TreeNode(2147483647);
        root.right.right = level.new TreeNode(2147483647);

        level.printList(level.findAverageWithQueue(root));

        level.printList(level.findAverageWithoutQueue(root));
    }

    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }
}

