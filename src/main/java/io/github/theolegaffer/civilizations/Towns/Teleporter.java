package io.github.theolegaffer.civilizations.Towns;

import io.github.theolegaffer.civilizations.util.Cuboid;
import org.apache.commons.lang.WordUtils;

/**
 * Created by Sam on 7/9/2016.
 */
public class Teleporter extends Building {

    private String linked;

    public Teleporter(Cuboid cuboid, String name){
        type = "teleporter";
        super.cuboid = cuboid;
        super.name = name;
        location = cuboid.newSerialize();
        enterMessage = WordUtils.capitalize(getDefaultEnterMessage());
        leaveMessage = Default;
        this.linked = "test";
    }

    public String getLinked() {
        return this.linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }
}
