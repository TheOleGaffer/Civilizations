package io.github.theolegaffer.civilizations.Towns;

import io.github.theolegaffer.civilizations.util.Cuboid;
import org.apache.commons.lang.WordUtils;

/**
 * Created by Sam on 7/9/2016.
 */
public class Temple extends Building {
    private String templeType;

    public Temple(Cuboid cuboid, String name){
        super.type = "temple";
        super.cuboid = cuboid;
        super.name = name;
        super.location = cuboid.newSerialize();
        super.enterMessage = WordUtils.capitalize(getDefaultEnterMessage());
        super.leaveMessage = Default;
        this.templeType = "test";
    }
    public String getTempleType(){
        return this.templeType;
    }

    public void setTempleType(String type){
        this.templeType = type;
    }
}
