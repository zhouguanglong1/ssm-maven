package com.zhougl.search;

import java.util.LinkedList;

/**
 * 深度优先遍历的例子（基于图的实现）
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/14 16:46
 */
public class DepthFirstSearch {

    private int v; // 顶点的个数
    private LinkedList<Integer>[] adj; // 邻接表
    public DepthFirstSearch(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        DepthFirstSearch search = new DepthFirstSearch(10);
        search.addEdge(1,2);
        search.addEdge(1,4);
        search.addEdge(2,3);
        search.addEdge(2,5);
        search.addEdge(5,6);
        search.addEdge(5,7);
        search.addEdge(3,6);
        search.addEdge(6,8);
        search.addEdge(7,8);
        search.addEdge(4,5);

        search.dfs(1,7);
    }

}
