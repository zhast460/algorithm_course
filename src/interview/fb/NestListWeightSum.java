package interview.fb;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestListWeightSum {
    public static int weightSum(List<NestListNode> list) {
        //return dfs(list, 1);
        return bfsWithCumulativeProductWeight(list);
    }

    public static int bfs(List<NestListNode> list) {
        int res = 0, level = 1;
        Queue<List<NestListNode>> queue = new LinkedList<>();
        queue.offer(list);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                List<NestListNode> curr = queue.poll();
                for (NestListNode node : curr) {
                    if (node.isNumber()) {
                        res += node.getNumber() * level;
                    } else {
                        queue.offer(node.getList());
                    }
                }
            }
            level++;
        }
        return res;
    }

    public static int bfsWithCumulativeProductWeight(List<NestListNode> list) {
        int res = 0, level = 1, weight = 1;
        Queue<List<NestListNode>> queue = new LinkedList<>();
        queue.offer(list);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                List<NestListNode> curr = queue.poll();
                for (NestListNode node : curr) {
                    if (node.isNumber()) {
                        res += node.getNumber() * weight;
                    } else {
                        queue.offer(node.getList());
                    }
                }
            }
            weight *= ++level;
        }
        return res;
    }

    private static int dfs(List<NestListNode> list, int level) {
        int sum = 0;
        for (NestListNode node : list) {
            if (node.isNumber()) {
                sum += node.getNumber() * level;
            } else {
                sum += dfs(node.getList(), level + 1);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        NestListWeightSum sol = new NestListWeightSum();
        List<NestListNode> list4 = Arrays.asList(new NestListNode(2));
        List<NestListNode> list3 = Arrays.asList(new NestListNode(6), new NestListNode(list4));
        List<NestListNode> list2 = Arrays.asList(new NestListNode(4), new NestListNode(list3));
        List<NestListNode> list1 = Arrays.asList(new NestListNode(1), new NestListNode(2), new NestListNode(list2));
        System.out.println(weightSum(list1));
    }
}

class NestListNode {
    Integer val;
    List<NestListNode> list;

    public NestListNode(Integer val) {
        this.val = val;
    }

    public NestListNode(List<NestListNode> list) {
        this.list = list;
    }

    public boolean isNumber() {
        return val != null;
    }

    public int getNumber() {
        return val;
    }

    public List<NestListNode> getList() {
        return list;
    }
}
