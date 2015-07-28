package io.github.theolegaffer.civilizations.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sam on 7/13/2015.
 * Used for loading and saving list files
 * taken from betterphp youtube channel
 */
public class ListStore {
    private File storageFile;
    private ArrayList<String> values;

    public ListStore(File file){
        this.storageFile = file;
        this.values = new ArrayList();

        if (this.storageFile.exists() == false){
            try {
                this.storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(){
        try {
            DataInputStream input = new DataInputStream(new FileInputStream(this.storageFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;

            while ((line = reader.readLine()) != null) {
                if (this.contains(line) == false) {
                    this.values.add(line);
                }
            }

            reader.close();
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save(){
        try{
            FileWriter stream = new FileWriter(this.storageFile);
            BufferedWriter out = new BufferedWriter(stream);

            for (String value : this.values){
                out.write(value);
                out.newLine();
            }

            out.close();
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean contains(String value){
        return this.values.contains(value);
    }

    public void add(String value){
        if (!(this.contains(value))){
            this.values.add(value);
        }
    }

    public void remove(String value){
        this.values.remove(value);
    }

    public ArrayList<String> listReturn(){
        return values;
    }
}
