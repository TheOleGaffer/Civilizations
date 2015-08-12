package io.github.theolegaffer.civilizations.Towns;

import io.github.theolegaffer.civilizations.util.Cuboid;

/**
 * Created by Sam on 8/4/2015.
 */
public class Building {

    protected String type;
    protected String enterMessage;
    protected String leaveMessage;
    protected Cuboid cuboid;
    protected String name;
    protected String linked;
    protected String location;

//    public Building(String type, Cuboid cuboid){
//        this.type = type;
//        this.cuboid = cuboid;
//    }

    public Building(String type, Cuboid cuboid, String name){
        this.type = type;
        this.cuboid = cuboid;
        this.name = name;
        this.linked = "none";
        this.enterMessage = "Welcome to the " + type;
        this.leaveMessage = "none";
        this.location = cuboid.newSerialize();
    }

    public Building(String type, Cuboid cuboid, String name, String linked){
        this.type = type;
        this.cuboid = cuboid;
        this.name = name;
        this.linked = linked;
        this.enterMessage = "Welcome to the " + type;
        this.leaveMessage = "none";
        this.location = cuboid.newSerialize();
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    public String getEnterMessage() {
        return enterMessage;
    }

    public void setEnterMessage(String enterMessage) {
        this.enterMessage = enterMessage;
    }

    public String getLocation(){
        return location;
    }
}

