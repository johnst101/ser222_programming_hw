package edu.ser222.m04_02;

/**
 * An implementation of KanjiMain which builds topological sorts off of a graph of Kanji characters
 *
 * Completion time: (estimation of hours spent on this program) TODO: fill in
 *
 * @author Tyler Johnson, Acuna, Buckner
 * @version 02/24/2024
 */

//Note: not all of these packages may be needed.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class CompletedMain implements KanjiMain {

    //Do not add any member variables to this class.

    //TODO: implement interface methods.

    public HashMap<Integer, String> loadKanji(String filename, EditableDiGraph graph) {
        HashMap<Integer, String> kanjiMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/" + filename));
            String line = reader.readLine();
            char comCheck;
            while (line != null) {
                reader.mark(1);
                comCheck = (char) reader.read();
                if (comCheck == '#') {
                    reader.reset();
                    reader.readLine();
                    continue;
                }
                reader.reset();
                line = reader.readLine();
                String[] lineArr = line.split("\t");
                int id = Integer.parseInt(lineArr[0]);
                String kanjiChar = lineArr[1];
                kanjiMap.put(id, kanjiChar);
                graph.addVertex(id);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return kanjiMap;
    }

    public void loadDataComponents(String filename, EditableDiGraph graph) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/" + filename));
            String line = reader.readLine();
            char comCheck;
            while (line != null) {
                reader.mark(1);
                comCheck = (char) reader.read();
                if (comCheck == '#') {
                    reader.reset();
                    reader.readLine();
                    continue;
                }
                reader.reset();
                line = reader.readLine();
                String[] lineArr = line.split("\t");
                int char1 = Integer.parseInt(lineArr[0]);
                int char2 = Integer.parseInt(lineArr[1]);
                graph.addEdge(char1, char2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String buildOrderString(EditableDiGraph graph, TopologicalSort topSort, HashMap<Integer, String> kanjiMap) {
        // TODO: implement
        throw new java.lang.UnsupportedOperationException("TODO!");
    }

    public static void main(String[] args) {
        /***************************************************************************
         * START - CORE DRIVER LOGIC, DO NOT MODIFY                                *
         **************************************************************************/
        String FILENAME_KANJI = "data-kanji.txt";
        String FILENAME_COMPONENTS = "data-components.txt";

        KanjiMain driver = new CompletedMain();
        EditableDiGraph graph = new BetterDiGraph();

        HashMap<Integer, String> kanjiMap = driver.loadKanji(FILENAME_KANJI, graph);
        driver.loadDataComponents(FILENAME_COMPONENTS, graph);

        TopologicalSort intuitive = new IntuitiveTopological(graph);

        System.out.println(driver.buildOrderString(graph, intuitive, kanjiMap));

        /***************************************************************************
         * END - CORE DRIVER LOGIC, DO NOT MODIFY                                  *
         **************************************************************************/

        //NOTE: feel free to temporarily comment out parts of the above code while
        //you incrementally develop your program. Just make sure all of it is there
        //when you test the final version of your program.

        //OPTIONAL: add code for extra credit here.
    }
}