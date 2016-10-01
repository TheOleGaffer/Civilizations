package io.github.theolegaffer.civilizations;



import io.github.theolegaffer.civilizations.commands.*;
import io.github.theolegaffer.civilizations.commands.TownHandlers.*;
import io.github.theolegaffer.civilizations.commands.TownHandlers.SetHandlers.SetBorderHandler;
import io.github.theolegaffer.civilizations.events.LoginListener;
import io.github.theolegaffer.civilizations.events.OnBlockBreakListener;
import io.github.theolegaffer.civilizations.events.OnDamageListener;
import io.github.theolegaffer.civilizations.events.PlayerMoveListener;
//import io.github.theolegaffer.civilizations.events.XPCivGainListener;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.MondoCommand;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;


/**
 * Created by Sam on 7/12/2015.
 */
public class Civilizations extends JavaPlugin implements Listener{
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    public HashMap replaceBlockMap = null;



    @Override
    public void onEnable(){
        setupFilePaths();
        setupCommandHelpers();
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        Tools.setupBlockChanger();

        this.getCommand("accept").setExecutor(new AcceptCommand(this));
        this.getCommand("decline").setExecutor(new DeclineCommand(this));
        getServer().getPluginManager().registerEvents(new OnDamageListener(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this); //for playermove event when entering towns
//        getServer().getPluginManager().registerEvents(new XPCivGainListener(), this);
        log.info("Civilizations has been enabled on this server!");
/**
 * This is starts the economy schedule to add 20 dollars every 15 minutes
 *
 */
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                log.info("Added money");
                for (Player target : getServer().getOnlinePlayers()) {
                    String name = target.getName();
                    econ.bankDeposit(name, 20);
                    TownDataHandler tData = new TownDataHandler(name);
                    if(tData.getBankPerk()){
                        econ.bankDeposit(name,20);
                    }
                }
            }
        }, 0, 18000);
    }
    public void setupFilePaths(){
        String pluginFolder = this.getDataFolder().getAbsolutePath();

        (new File(pluginFolder)).mkdirs();
        (new File(pluginFolder + File.separator + "PlayerData")).mkdirs();
        (new File(pluginFolder + File.separator + "TownData")).mkdirs();

    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public void setupCommandHelpers(){
        MondoCommand base = new MondoCommand();
        MondoCommand setSub = new MondoCommand();
        base.autoRegisterFrom(this);
        getCommand("town").setExecutor(base);


        base.addSub("info", "civilizations.town.info")
                .setDescription("Views a town's information")
                .setMinArgs(0)
                .setUsage("<name>")
                .setHandler(new TownInfoHandler());

        base.addSub("create", "civilizations.town.create")
                .setDescription("Creates a new Town")
                .setMinArgs(1)
                .setUsage("<nameoftown>")
                .setHandler(new TownCreateHandler());

        base.addSub("delete", "civilizations.town.delete")
                .setDescription("Deletes a town")
                .setMinArgs(1)
                .setUsage("<nameoftown>")
                .allowConsole()
                .setHandler(new TownDeleteHandler());

        base.addSub("invite", "civilizations.town.invite")
            .setDescription("Invites a player to your town")
            .setMinArgs(1)
            .setUsage("<nameofplayer")
            .setHandler(new TownInviteHandler());

        base.addSub("remove", "civilizations.town.remove")
            .setDescription("Removes a player from the town")
            .setMinArgs(1)
            .setUsage("<nameofplayer> <nameoftown")
            .setHandler(new TownRemoveHandler());

        base.addSub("leave", "civilizations.town.leave")
            .setDescription("Leaves your current town")
            .setHandler(new TownLeaveHandler());
        base.addSub("listall", "civilizations.town.listall")
            .setDescription("Lists all the towns on the server")
            .setHandler(new TownListAllHandler());
        base.addSub("spawn", "civilizations.town.spawn")
            .setDescription("Teleports a player to the town spawn")
            .setHandler(new TownSpawnHandler());
        base.addSub("build", "civilizations.town.build")
            .setDescription("Creates a new type of building")
            .setMinArgs(2)
            .setUsage("<typeofbuilding> <name>")
            .setHandler(new TownBuildHandler());

        base.addSub("set")
            .setDescription("Set's town properties")
            .setUsage("[property] <argument>")
            .setHandler(setSub);
        setSub.addSub("border", "civilizations.town.border")
              .setDescription("Sets a new border for the town via your location")
              .setHandler(new SetBorderHandler());
    }
    @Override
    public void onDisable(){
        Tools.resetBlocks(); //changes all obsidian unmined with fire perk back to obsidian
        this.getLogger().info("Civilizations has been disabled on this server.");
    }

}

