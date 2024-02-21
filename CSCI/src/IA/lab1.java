//package IA;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class lab1 {


    public static void main(String[] args) throws IOException {

        long time = System.currentTimeMillis();
        int WIDTH = 395, HEIGHT = 500;
        //INPUT PARSING
        //read the image and the files
        BufferedImage solution = ImageIO.read(new File(args[0]));
        BufferedReader readerHeights = new BufferedReader(new FileReader(new File(args[1])));
        BufferedReader goalReader = new BufferedReader(new FileReader(new File(args[2])));

        //hard code of the colors and the cost associated with the maps
        //Open land;rough meadow;easy mov forest;slow run for;walk for;impassible vege;water;pavedroad;footpath;outof bound
        int[] colors_rgb = {new Color(248, 148, 18).getRGB(), new Color(255, 192, 0).getRGB(), new Color(255, 255, 255).getRGB()
                , new Color(2, 208, 60).getRGB(), new Color(2, 136, 40).getRGB(), new Color(5, 73, 24).getRGB(),
                new Color(0, 0, 255).getRGB(), new Color(71, 51, 3).getRGB(), new Color(0, 0, 0).getRGB(), new Color(205, 0, 101).getRGB()};
        double MIN_COST = 1;

        double[] colors_cost = {2.5 * MIN_COST, 3.5 * MIN_COST, 2.8*MIN_COST, 3 * MIN_COST, 5 * MIN_COST, 300 * MIN_COST, 300 * MIN_COST, MIN_COST, 1.8 * MIN_COST, -999999 * MIN_COST};

        //read the cost of the terrain for each cell
        double[][] terrainCost = new double[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int cRGB = solution.getRGB(x, y);
                int id = -1;
                for (int i = 0; i < colors_rgb.length; i++) {
                    if (cRGB == colors_rgb[i]) {
                        id = i;
                        break;
                    }
                }
                if (id == -1) {
                    System.err.println("Error reading map colors " + x + " " + y + " " + (new Color(cRGB)) + " " + cRGB + " " + Arrays.toString(colors_rgb));
                    return;
                }

                terrainCost[x][y] = colors_cost[id];
            }
        }

        //read the position of each cell
        Vector3[][] heights = new Vector3[WIDTH][HEIGHT];
        for (int y = 0; y < HEIGHT; y++) {
            String[] height_string = readerHeights.readLine().replace("   ", " ").split(" ");

            for (int x = 0; x < WIDTH; x++) {
                //height_string.length-400 because some files have a begin with a blank space
                double z = Double.parseDouble(height_string[x+height_string.length-400]);
                heights[x][y] = new Vector3(x * 10.29, y * 7.55, z);
            }
        }

        //read the travel points
        List<int[]> points = new ArrayList<>();
        String line = goalReader.readLine();
        while (line != null) {
            String[] coord_string = line.split(" ");
            points.add(new int[]{Integer.parseInt(coord_string[0]), Integer.parseInt(coord_string[1])});
            line = goalReader.readLine();
        }
        System.err.println("reading " + (System.currentTimeMillis() - time));
        double totalLength = 0;

        //compute the path with A*
        for (int i = 1; i < points.size(); i++) {
            //travelling points
            int[] begin = points.get(i - 1);
            int[] end = points.get(i);
            Node endNode = new Node(0, 0, end[0], end[1], null);


            PriorityQueue<Node> open = new PriorityQueue<>();//open list
            HashSet<Node> all = new HashSet<>();//list of all the nodes that have already been viewed
            Node first = new Node(0, 0, begin[0], begin[1], null);
            all.add(first);
            open.add(first);


            Node current = null;
            //while there is something to explore
            while (!open.isEmpty()) {
                current = open.poll();//get and remove the node with the lower f value

                //test if the current node is the destination
                if (current.x == end[0] && current.y == end[1]) {
                    break;
                }

                //child generation (with diagonals)
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (k == 0 && j == 0) continue;//remove the current node

                        if (current.x + j >= 0 && current.y + k >= 0 && current.y + k < HEIGHT && current.x + j < WIDTH) {
                            //distance between the current node and his child
                            double dist = heights[current.x][current.y].distance(heights[current.x + j][current.y + k]);
                            //if the node is out of bound we don't add it to the list
                            if (terrainCost[current.x + j][current.y + k] < 0) continue;
                            double g = current.g + terrainCost[current.x + j][current.y + k] * dist;//G cost
                            Node n = new Node(g + 1 * MIN_COST * current.distNodes(endNode), g, current.x + j, current.y + k, current);

                            //add the node if she has not been visited yet.
                            if (!all.contains(n)) {
                                all.add(n);
                                open.add(n);
                            }
                        }
                    }
                }//end of the child generation

            }

            //path reconstruction
            List<Node> path = new ArrayList<>();

            Color color = new Color(118, 63, 231);
            while (current != null) {
                path.add(current);
                //compute the path length
                if (current.parent != null) {
                    totalLength += heights[current.x][current.y].distance(heights[current.parent.x][current.parent.y]);
                }
                //color the map
                solution.setRGB(current.x, current.y, color.getRGB());
                current = current.parent;
            }


        }
        //output
        System.out.println(totalLength);
        ImageIO.write(solution, "png", new File(args[3]));
        System.err.println(System.currentTimeMillis() - time);
    }
}


class Vector3 {
    //this class is a simple representation of a 3 component vector
    double x, y, z;//coords

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    //norm 2 between the two points
    public double distance(Vector3 b) {
        return Math.sqrt((b.x - x) * (b.x - x) + (b.y - y) * (b.y - y) + (b.z - z) * (b.z - z));
    }


}

class Node implements Comparable<Node> {
    //this class is a implementation of a node
    double f, g;//values of the A* algorithm
    int x, y;//coordinate of the node on the map
    Node parent;//parent of this node, null if no parent

    public Node(double f, double g, int x, int y, Node p) {
        this.f = f;
        this.g = g;
        this.x = x;
        this.y = y;
        this.parent = p;
    }

    @Override
    public boolean equals(Object o) {
        //check if the two nodes are on the same position on the map
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    private static final double DIAG = Math.sqrt(7.55 * 7.55 + 10.29 * 10.29);

    public double distNodes(Node n) {
        //heuristics of the A* (modification of the norm 1 to take into account the diagonals).
        int dx = Math.abs(n.x - x);
        int dy = Math.abs(n.y - y);
        if (dx < dy) {
            return 7.55 * (dy - dx) + DIAG * dx;
        }
        return 10.29 * (dx - dy) + DIAG * dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "f=" + f +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Node n) {
        //compare the f values ->used for the PriorityQueue
        return Double.compare(this.f, n.f);
    }
}
