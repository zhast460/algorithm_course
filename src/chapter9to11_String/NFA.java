package chapter9to11_String;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// regular expression matching
public class NFA {

    private char[] re;
    private int M;
    private Digraph G; // Epsilon transition Digraph

    public NFA(String regexp) {
        re = regexp.toCharArray();
        M = regexp.length();
        G = buildEpsilonTransitionDigraph();
    }

    public boolean recognize(String txt) {
        List<Integer> states = new ArrayList<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v))
                states.add(v);
        }

        for (int i = 0; i < txt.length(); i++) {
            List<Integer> matches = new ArrayList<>();
            for (int v : states) {
                if (v == M) continue;
                if (re[v] == txt.charAt(i) || re[v] == '.') {
                    matches.add(v+1);
                }
            }

            dfs = new DirectedDFS(G, matches);
            states = new ArrayList<>();
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v))
                    states.add(v);
            }
        }

        for (int i : states) {
            if (i == M)
                return true;
        }
        return false;
    }

    public Digraph buildEpsilonTransitionDigraph() {
        Digraph G = new Digraph(M+1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|'){
                stack.push(i);
            } else if (re[i] == ')') {
                int or = stack.pop();
                if (re[or] == '|') { // Notes: '|' only work inside parenthesis, that is why GREP function enclose regexp with (.* and .*)
                    lp = stack.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                }else
                    lp = or;
            }

            if (i < M - 1 && re[i + 1] == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == ')' || re[i] == '*')
                G.addEdge(i, i+1);
        }
        return G;
    }

    public static void main(String[] args) {
        NFA pattern = new NFA("e(ab|cd2|ee)*f");
        System.out.println(pattern.recognize("ecd2abf"));
        System.out.println(pattern.recognize("ecd2babf"));
        System.out.println("ecd2babf".matches("ec(ba|d2)+bf"));
    }
}
