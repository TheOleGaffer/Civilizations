package io.github.theolegaffer.civilizations;



import io.github.theolegaffer.civilizations.Economy.econMethods;
import io.github.theolegaffer.civilizations.commands.*;

import io.github.theolegaffer.civilizations.events.LoginListener;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;

/**
 * Created by Sam on 7/12/2015.
 */
public class Civilizations extends JavaPlugin{

//    public static File civFile;
//    public static String mainDirectory;
//    public static String flatFileDirectory;
//    public static String usersFile;
//    public static String leaderboardDirectory;

    @Override
    public void onEnable(){

        setupFilePaths();
        this.getCommand("addmoney").setExecutor(new AddMoneyCommand(this));
        this.getCommand("givemoney").setExecutor(new GiveMoneyCommand(this));
        this.getCommand("money").setExecutor(new MoneyCommand(this));
        this.getCommand("removemoney").setExecutor(new RemoveMoneyCommand(this));
        this.getCommand("setmoney").setExecutor(new SetMoneyCommand(this));
        getServer().getPluginManager().registerEvents(new LoginListener(), this);



        this.getLogger().info("Civilizations has been enabled on this server!");

    }
    public void setupFilePaths(){
        String pluginFolder = this.getDataFolder().getAbsolutePath();

        (new File(pluginFolder)).mkdirs();
        (new File(pluginFolder + File.separator + "Economy")).mkdirs();
//        civFile = getFile();
//        mainDirectory = getDataFolder().getPath() + File.separator;
//        flatFileDirectory = mainDirectory + "FlatFiles" + File.separator;
//        usersFile = flatFileDirectory + "users";
//        leaderboardDirectory = flatFileDirectory + "Leaderboards" + File.separator;


    }
    @Override
    public void onDisable(){
        this.getLogger().info("Civilizations has been disabled on this server.");
    }
}

