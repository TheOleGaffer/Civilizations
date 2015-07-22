package io.github.theolegaffer.civilizations;



import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.commands.*;

import io.github.theolegaffer.civilizations.commands.TownHandlers.TownCreateHandler;
import io.github.theolegaffer.civilizations.commands.TownHandlers.TownDeleteHandler;
import io.github.theolegaffer.civilizations.commands.TownHandlers.TownInfoHandler;
import io.github.theolegaffer.civilizations.events.LoginListener;
import io.github.theolegaffer.civilizations.util.ListStore;
import mondocommand.MondoCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sam on 7/12/2015.
 */
public class Civilizations extends JavaPlugin{
    protected ListStore townList;

    @Override
    public void onEnable(){

        setupFilePaths();
        setupCommandHelpers();
//        BaseTownCommand temp = new BaseTownCommand(this);
//        temp.setupCommandHelpers();
        this.getCommand("addmoney").setExecutor(new AddMoneyCommand(this));
        this.getCommand("givemoney").setExecutor(new GiveMoneyCommand(this));
        this.getCommand("money").setExecutor(new MoneyCommand(this));
        this.getCommand("removemoney").setExecutor(new RemoveMoneyCommand(this));
        this.getCommand("setmoney").setExecutor(new SetMoneyCommand(this));
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        this.getLogger().info("Civilizations has been enabled on this server!");
/**
 * This is starts the economy schedule to add 20 dollars every 15 minutes
 *
 */
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                getLogger().info("Added money");
                for (Player target : getServer().getOnlinePlayers()) {
                    String name = target.getName();
                    EconomyMethods playerData = new EconomyMethods(name);
                    playerData.giveMoney(20);
                    playerData.savePlayerConfig();
                }
            }
        }, 0, 18000);

    }
    public void setupFilePaths(){
        String pluginFolder = this.getDataFolder().getAbsolutePath();

        (new File(pluginFolder)).mkdirs();
        (new File(pluginFolder + File.separator + "PlayerData")).mkdirs();
        (new File(pluginFolder + File.separator + "TownData")).mkdirs();
        this.townList = new ListStore(new File(pluginFolder + File.separator + "TownData" + File.separator +"town-list.txt"));
        this.townList.load();
    }
    public void setupCommandHelpers(){
        MondoCommand base = new MondoCommand();
        base.autoRegisterFrom(this);
        getCommand("town").setExecutor(base);

        base.addSub("info", "town.info")
                .setDescription("Views a town's information")
                .setMinArgs(0)
                .setUsage("<name>")
                .setHandler(new TownInfoHandler());

        base.addSub("create", "town.create")
                .setDescription("Creates a new Town")
                .setMinArgs(1)
                .setUsage("<nameoftown>")
                .setHandler(new TownCreateHandler());

        base.addSub("delete", "town.delete")
                .setDescription("Deletes a town")
                .setMinArgs(1)
                .setUsage("<nameoftown>")
                .setHandler(new TownDeleteHandler());

    }
    @Override
    public void onDisable(){
        this.getLogger().info("Civilizations has been disabled on this server.");
    }
}

