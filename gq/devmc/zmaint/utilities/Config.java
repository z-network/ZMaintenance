package gq.devmc.zmaint.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

public class Config
{
  private static File ordner = new File("plugins/ZMaintenance");
  private static File config = new File(ordner, "config.yml");
  private static File messages = new File(ordner, "messages.yml");
  private static File whitelist = new File(ordner, "whitelist.yml");
  private static File motd = new File(ordner, "motd.yml");
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static void init()
  {
    if (!ordner.exists()) {
      ordner.mkdir();
    }
    if (!config.exists()) {
      try
      {
        config.createNewFile();
        
        Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);
        
        cfg.set("Prefix", "&6&lZ");
        cfg.set("Server", "&6&lZNetwork");
        cfg.set("Maintenance", Boolean.valueOf(false));
        cfg.set("MaintenanceReason", "Updates");
        cfg.set("MaintenanceDuration", "Custom");
        
        YamlConfiguration.getProvider(YamlConfiguration.class).save(cfg, config);
      }
      catch (IOException localIOException) {}
    }
    if (!messages.exists()) {
      try
      {
        messages.createNewFile();
        
        Configuration msg = YamlConfiguration.getProvider(YamlConfiguration.class).load(messages);
        
        msg.set("TeamNotify", "&cThe player &7%player% &chas tried to enter the network during the maintenance!");
        msg.set("NoPermission", "&cYou are not permitted!");
        
        msg.set("AlreadyOnWhitelist", "&cThis player is already on the whitelist!");
        msg.set("IsNotOnWhitelist", "&cThis player is not on the whitelist!");
        msg.set("AddedToWhitelist", "&aThe player is now allowed to enter the Network!");
        msg.set("RemovedFromWhitelist", "&aThe player was succesfully removed from the Whitelist!");
        
        msg.set("AlreadyMaintenance", "&cThe Network is already in Maintenance!");
        msg.set("IsNotMaintenance", "&cThe Network is not in Maintenance!");
        msg.set("MaintenanceActive", "&aThe Network is now in Maintenance!");
        msg.set("MaintenanceDeactive", "&aThe Network is now out of Maintenance!");
        
        msg.set("NewMaintenanceReason", "&aThe new Reason of the Maintenance is &e%newreason%");
        msg.set("NewMaintenanceDuration", "&aThe new Duration of the Maintenance is &e%newduration%");
        
        List<String> cancel = new ArrayList();
        
        cancel.add("&8&m-------------------------");
        cancel.add("");
        cancel.add("&6&lZNetwork &7| &4Maintenance");
        cancel.add("");
        cancel.add("&cOur Network is currently in Maintenance!");
        cancel.add("");
        cancel.add("&3Reason&8: &e%reason%");
        cancel.add("&3Duration&8: &e%duration%");
        cancel.add("");
        cancel.add("&8&m-------------------------");
        
        msg.set("JoinMessage", cancel);
        
        YamlConfiguration.getProvider(YamlConfiguration.class).save(msg, messages);
      }
      catch (IOException localIOException1) {}
    }
    if (!whitelist.exists()) {
      try
      {
        whitelist.createNewFile();
        
        Configuration wl = YamlConfiguration.getProvider(YamlConfiguration.class).load(whitelist);
        
        wl.set("Whitelist", new ArrayList());
        
        YamlConfiguration.getProvider(YamlConfiguration.class).save(wl, whitelist);
      }
      catch (IOException localIOException2) {}
    }
    if (!motd.exists()) {
      try
      {
        motd.createNewFile();
        
        Configuration mtd = YamlConfiguration.getProvider(YamlConfiguration.class).load(motd);
        
        mtd.set("MotdSystem", Boolean.valueOf(true));
        
        mtd.set("NormalMotd.Line1", "6&lZNetwork &8| &7Closed.");
        mtd.set("NormalMotd.Line2", "&l&6+ &4Maintenance System");
        
        mtd.set("MaintenanceMotd.Line1", "&6&lZNetwork &8| &7Maintenance");
        mtd.set("MaintenanceMotd.Line2", "&&&lZNetwork &8| &7Actif");
        
        mtd.set("MaintenanceMessage", "&6&lZMaintenance");
        
        mtd.set("Slots", Integer.valueOf(100));
        
        List<String> info = new ArrayList();
        
        info.add("&7&m-------------------------");
        info.add("");
        info.add("&aWebsite&7: znetworks.gq");
        info.add("&aDiscord&7: soon!");
        info.add("");
        info.add("&7&m-------------------------");
        
        mtd.set("PlayerInfo", info);
        
        YamlConfiguration.getProvider(YamlConfiguration.class).save(mtd, motd);
      }
      catch (IOException localIOException3) {}
    }
    try
    {
      Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);
      Configuration msg = YamlConfiguration.getProvider(YamlConfiguration.class).load(messages);
      Configuration wl = YamlConfiguration.getProvider(YamlConfiguration.class).load(whitelist);
      Configuration mtd = YamlConfiguration.getProvider(YamlConfiguration.class).load(motd);
      
      Data.prefix = cfg.getString("Prefix").replaceAll("&", "§") + " §r";
      Data.servername = cfg.getString("Server");
      
      Data.maintenance = cfg.getBoolean("Maintenance");
      Data.maintenancereason = cfg.getString("MaintenanceReason");
      Data.maintenanceduration = cfg.getString("MaintenanceDuration");
      
      Messages.teamnotify = msg.getString("TeamNotify").replaceAll("&", "§");
      
      Messages.nopermission = msg.getString("NoPermission").replaceAll("&", "§");
      
      Messages.removedfromwhitelist = msg.getString("RemovedFromWhitelist").replaceAll("&", "§");
      Messages.addedtowhitelist = msg.getString("AddedToWhitelist").replaceAll("&", "§");
      Messages.isnotonwhitelist = msg.getString("IsNotOnWhitelist").replaceAll("&", "§");
      Messages.alreadyonwhitelist = msg.getString("AlreadyOnWhitelist").replaceAll("&", "§");
      
      Messages.alreadymaintenance = msg.getString("AlreadyMaintenance").replaceAll("&", "§");
      Messages.isnotmaintenance = msg.getString("IsNotMaintenance").replaceAll("&", "§");
      Messages.maintenanceactive = msg.getString("MaintenanceActive").replaceAll("&", "§");
      Messages.maintenancedeactive = msg.getString("MaintenanceDeactive").replaceAll("&", "§");
      
      Messages.newmaintenancereason = msg.getString("NewMaintenanceReason").replaceAll("&", "§");
      Messages.newmaintenanceduration = msg.getString("NewMaintenanceDuration").replaceAll("&", "§");
      
      Messages.cancellist = msg.getStringList("JoinMessage");
      
      Data.whitelist = wl.getStringList("Whitelist");
      
      Motd.motdsystem = mtd.getBoolean("MotdSystem");
      Motd.slots = mtd.getInt("Slots");
      Motd.normalmotd = new String[] { mtd.getString("NormalMotd.Line1").replaceAll("&", "§"), mtd.getString("NormalMotd.Line2").replaceAll("&", "§") };
      Motd.maintenancemotd = new String[] { mtd.getString("MaintenanceMotd.Line1").replaceAll("&", "§"), mtd.getString("MaintenanceMotd.Line2").replaceAll("&", "§") };
      Motd.maintenancemessage = mtd.getString("MaintenanceMessage").replaceAll("&", "§");
      Motd.playerinfo = mtd.getStringList("PlayerInfo");
    }
    catch (IOException localIOException4) {}
  }
  
  public static void shutdown()
  {
    try
    {
      Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);
      Configuration wl = YamlConfiguration.getProvider(YamlConfiguration.class).load(whitelist);
      
      cfg.set("Prefix", Data.prefix);
      cfg.set("Server", Data.servername);
      cfg.set("Maintenance", Boolean.valueOf(Data.maintenance));
      cfg.set("MaintenanceReason", Data.maintenancereason);
      cfg.set("MaintenanceDuration", Data.maintenanceduration);
      
      wl.set("Whitelist", Data.whitelist);
      
      YamlConfiguration.getProvider(YamlConfiguration.class).save(cfg, config);
      YamlConfiguration.getProvider(YamlConfiguration.class).save(wl, whitelist);
    }
    catch (IOException localIOException) {}
  }
}
