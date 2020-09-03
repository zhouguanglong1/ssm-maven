package com.zhougl.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历的demo（基于图的例子）
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/14 16:44
 */
public class BreadthFirstSearch {

    private int v; // 顶点的个数
    private LinkedList<Integer>[] adj; // 邻接表
    public BreadthFirstSearch(int v) {
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

    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s]=true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
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
        BreadthFirstSearch search = new BreadthFirstSearch(10);
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

        search.bfs(1,7);
    }
}
