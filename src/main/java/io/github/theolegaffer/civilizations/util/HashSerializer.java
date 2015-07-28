package io.github.theolegaffer.civilizations.util;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Sam on 7/22/2015.
 */
public class HashSerializer {
    private File storageFile;
    private HashMap<String,String> hmap;

    public HashSerializer(File file) {
        this.storageFile = file;
        this.hmap = new HashMap();

        if (!(this.storageFile.exists())){
            try {
                this.storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public HashSerializer(File file, HashMap map) {
        this.storageFile = file;
        this.hmap = map;

        if (!(this.storageFile.exists())){
            try {
                this.storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveToFile(){
        try{
            FileOutputStream fos = new FileOutputStream(this.storageFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hmap);
            oos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadFromFile(){
        try{
            FileInputStream fis = new FileInputStream(this.storageFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            hmap = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException x){
            x.printStackTrace();
        }
    }

    public void add(String key, String value){
        if(!(this.containKey(key))) {
            hmap.put(key, value);
        }
    }

    public String getValue(String key){
        if(this.containKey(key)) {
            return hmap.get(key);
        }
        return "empty";
    }

    public boolean containKey(String key){
        return hmap.containsKey(key);
    }

    public void remove(String key){
        hmap.remove(key);
    }
}
