package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.commands.TownHandlers.TownCreateHandler;
import io.github.theolegaffer.civilizations.commands.TownHandlers.TownDeleteHandler;
import io.github.theolegaffer.civilizations.commands.TownHandlers.TownInfoHandler;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoCommand;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Sam on 7/15/2015.
 */
public class BaseTownCommand extends JavaPlugin{
    private final Civilizations plugin;

    public BaseTownCommand(Civilizations plugin){
        this.plugin = plugin;
    }

//    public void setupCommandHelpers(){
//        MondoCommand base = new MondoCommand();
//        base.autoRegisterFrom(this);
//        getCommand("town").setExecutor(base);
//
//        base.addSub("info", "town.info")
//            .setDescription("Views a town's information")
//            .setMinArgs(0)
//            .setUsage("<name>")
//            .setHandler(new TownInfoHandler());
//
//        base.addSub("create", "town.create")
//            .setDescription("Creates a new Town")
//            .setMinArgs(1)
//            .setUsage("<nameoftown>")
//            .setHandler(new TownCreateHandler());
//
//        base.addSub("delete", "town.delete")
//            .setDescription("Deletes a town")
//            .setMinArgs(1)
//            .setUsage("<nameoftown>")
//            .setHandler(new TownDeleteHandler());
//
//    }
}
