package io.github.theolegaffer.civilizations;



import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.commands.*;
import io.github.theolegaffer.civilizations.commands.TownHandlers.*;
import io.github.theolegaffer.civilizations.events.LoginListener;
import io.github.theolegaffer.civilizations.events.PlayerMoveListener;
import io.github.theolegaffer.civilizations.util.Cuboid;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
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
    protected ArrayList<Cuboid> townBorders = new ArrayList<>();

    @Override
    public void onEnable(){

        setupFilePaths();
        setupCommandHelpers();

        this.getCommand("addmoney").setExecutor(new AddMoneyCommand(this));
        this.getCommand("givemoney").setExecutor(new GiveMoneyCommand(this));
        this.getCommand("money").setExecutor(new MoneyCommand(this));
        this.getCommand("removemoney").setExecutor(new RemoveMoneyCommand(this));
        this.getCommand("setmoney").setExecutor(new SetMoneyCommand(this));

        this.getCommand("accept").setExecutor(new AcceptCommand(this));
        this.getCommand("decline").setExecutor(new DeclineCommand(this));
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
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

        ArrayList<String> tNames = this.townList.listReturn();
        for (String temp : tNames){
            getLogger().info("looping with ." + temp + ".");
            TownDataHandler newTown = new TownDataHandler(temp);
            if(newTown == null){
                getLogger().info("Whoops newtown is null");
            }
            getLogger().info("newTown test " + newTown.getTownLimits());
            Cuboid temp2 = Cuboid.newDeserialize(newTown.getTownLimits());
            if (temp2 == null){
                getLogger().info("whoops temp2 is null");
            }
            addBorders(temp2);
            getLogger().info("via array " + townBorders.get(0).getTownName());
            getLogger().info(temp2.getTownName());
        }

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
            .setMinArgs(1)
            .setUsage("<typeofbuilding>")
            .setHandler(new TownBuildHandler());

        base.addSub("set")
            .setDescription("Set's town properties")
            .setUsage("[property] <argument>")
            .setHandler(setSub);
    }
    @Override
    public void onDisable(){
        this.getLogger().info("Civilizations has been disabled on this server.");
    }

    public void addBorders(Cuboid cuboid){
        townBorders.add(cuboid);
    }
    public Cuboid getBorder(int index){
        return townBorders.get(index);
    }
    public ArrayList<Cuboid> getBorderArray(){
        return townBorders;
    }
}

