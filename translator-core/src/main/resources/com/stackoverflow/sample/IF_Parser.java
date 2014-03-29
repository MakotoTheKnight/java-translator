package com.stackoverflow.sample;

import java.io.*;

/**
 * Reference: http://stackoverflow.com/q/21477011
 */

public class IF_Parser {
    private FileInputStream fstream;
    private DataInputStream in;
    private BufferedReader br;

    public IF_Parser(String filename)
        throws IOException {
        try {
            fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void Parse_given_file()
        throws IOException {
        try {
            String strLine;
            int line = 1;
            while((strLine = br.readLine()) != null) {
                System.out.println("Line " + line);
                int i;
                String[] splits = strLine.split("\t");
                // splits[0] : int value, splits[1] : string representation of list of postings.
                String[] postings = splits[1].split(" ");

                line++;
            }
        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}