package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
    private String filePath;
    private String flag1 = "***";
    private String flag2 = "*";
    private Integer[] id;
    private Float[] x;
    private Float[] y;
    private Integer[] id_node_from;
    private Integer[] id_node_to;
    private String[] label;




    public Files(String filePath) {
        this.filePath = filePath;
    }

    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            line = reader.readLine();
            if (line.equals(flag1)) { //Premier flag d'entrée
                int i = 0;
                line = reader.readLine();
                do {
                    if (!line.equals(flag2)) {
                        id[i] = Integer.parseInt(line);
                        line = reader.readLine();

                        if (!line.equals(flag2)) {
                            x[i] = Float.parseFloat(line);
                            line = reader.readLine();
                        }

                        if (!line.equals(flag2)) {
                            y[i] = Float.parseFloat(line);
                            line = reader.readLine();
                        }

                        i++;
                    } else {
                        line = reader.readLine();
                    }
                } while (!line.equals(flag1));

                line = reader.readLine();
                i = 0;
                do {
                    if (!line.equals(flag2)) {
                        id_node_from[i] = Integer.parseInt(line);
                        line = reader.readLine();

                        if (!line.equals(flag2)) {
                            id_node_to[i] = Integer.parseInt(line);
                            line = reader.readLine();
                        }

                        if (!line.equals(flag2)) {
                            label[i] = line;
                            line = reader.readLine();
                        }

                        i++;
                    } else {
                        line = reader.readLine();
                    }
                } while (!line.equals(flag1));
            }
            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        Graph read_graph = new Graph(id,x,y);
        read_graph.createEdges(id_node_from,id_node_to,label);
    }

    public void writeFile(Graph graph) {
        NodeData node = graph.getNodesData();
        EdgeData edge = graph.getEdgesData();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write(flag1);
            writer.newLine();

            for (int i = 0; i < node.ids.length; i++) {
                writer.write(String.valueOf(node.ids[i]));
                writer.newLine();
                writer.write(String.valueOf(node.xValues[i]));
                writer.newLine();
                writer.write(String.valueOf(node.yValues[i]));
                writer.newLine();
                writer.write(flag2);
                writer.newLine();
            }

            writer.write(flag1);
            writer.newLine();

            for (int i = 0; i < edge.node_from_ids.length; i++) {
                writer.write(String.valueOf(edge.node_from_ids[i]));
                writer.newLine();
                writer.write(String.valueOf(edge.node_to_ids[i]));
                writer.newLine();
                writer.write(edge.labels[i]);
                writer.newLine();
                writer.write(flag2);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}