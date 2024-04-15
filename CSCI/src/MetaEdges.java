import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MetaEdges {
    static List<Integer>[] edges;
    static int n;
    public static int DFS_time(int current_node,int[]finish,boolean[] visited,int current_time){
        visited[current_node]=true;
        for (int i = 0; i < edges[current_node].size(); i++) {
            if(!visited[edges[current_node].get(i)])current_time=DFS_time(edges[current_node].get(i),finish,visited,current_time);
        }
        finish[current_node]=current_time;
        return current_time+1;
    }

    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        n=sc.nextInt();
        edges=new List[n];
        List<Integer> nodes=new ArrayList<>();
        List<Integer>[] reversed_edges=new List[n];
        for (int i = 0; i < n; i++) {
            nodes.add(i);
            edges[i]=new ArrayList<>();
            reversed_edges[i]=new ArrayList<>();
            int neigh=sc.nextInt();
            while (neigh>=0){
                edges[i].add(neigh);
                neigh=sc.nextInt();
            }
        }

        boolean[]visited=new boolean[n];
        int[] finishTime=new int[n];
        int time=0;
        for (int i = 0; i < n; i++) {
            if(!visited[i])time=DFS_time(i,finishTime,visited,time);
        }

        int[] finalFinishTime = finishTime;
        nodes.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(finalFinishTime[o1], finalFinishTime[o2]);
            }
        });

        //reverse
        for (int i  = 0; i < n; i++) {
            for (int j = 0; j < edges[i].size(); j++) {
                reversed_edges[edges[i].get(j)].add(i);
            }
        }

        visited=new boolean[n];
        finishTime=new int[n];
        time=1;
        int idGroup=0;
        int[] groupsMeta=new int[n];
        for (int i = 0; i < n; i++) {
            if(!visited[nodes.get(i)]){
                int inputTime=time;
                time=DFS_time(nodes.get(i),finishTime,visited,inputTime);
                for (int j = 0; j < n; j++) {
                    if(finishTime[j]>=inputTime)groupsMeta[j]=idGroup;
                }
                idGroup++;
            }
        }

        //List<Integer>[] meta_edges=new List[idGroup];
        int count=0;
        //for (int i = 0; i < idGroup; i++) meta_edges[idGroup]=new ArrayList<>();
        boolean[][] has_edge=new boolean[idGroup][idGroup];
        for (int u = 0; u < n; u++) {
            for (int j = 0; j < edges[u].size(); j++) {
                int v=edges[u].get(j);
                if(groupsMeta[u]!=groupsMeta[v] && !has_edge[groupsMeta[u]][groupsMeta[v]]){
                    has_edge[groupsMeta[u]][groupsMeta[v]]=true;
                    count++;
                }
            }
        }
        System.out.println(idGroup);
        System.out.println(count);
    }
}
