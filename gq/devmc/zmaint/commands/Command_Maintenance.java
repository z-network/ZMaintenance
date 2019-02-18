package gq.devmc.zmaint.commands;

import gq.devmc.zmaint.utilities.Data;
import gq.devmc.zmaint.utilities.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class Command_Maintenance
  extends Command
{
  public Command_Maintenance()
  {
    super("zmaintenance");
  }
  
  public void execute(CommandSender sender, String[] args)
  {
    if (sender.hasPermission("zmaintenance.modify"))
    {
      if (args.length == 1)
      {
        if (args[0].equalsIgnoreCase("on"))
        {
          if (Data.maintenance)
          {
            sender.sendMessage(new TextComponent(Data.prefix + Messages.alreadymaintenance));
          }
          else
          {
            Data.maintenance = true;
            sender.sendMessage(new TextComponent(Data.prefix + Messages.maintenanceactive));
          }
        }
        else if (args[0].equalsIgnoreCase("off"))
        {
          if (Data.maintenance)
          {
            Data.maintenance = false;
            sender.sendMessage(new TextComponent(Data.prefix + Messages.maintenancedeactive));
          }
          else
          {
            sender.sendMessage(new TextComponent(Data.prefix + Messages.isnotmaintenance));
          }
        }
        else {
          sender.sendMessage(new TextComponent(Data.prefix + "?cUsage?8: ?c/maintenance <on/off>"));
        }
      }
      else if (args.length >= 2)
      {
        if (args[0].equalsIgnoreCase("reason"))
        {
          String reason = "";
          for (int i = 1; i < args.length; i++) {
            reason = reason + " " + args[i];
          }
          reason = reason.replaceFirst(" ", "");
          
          Data.maintenancereason = reason;
          
          sender.sendMessage(new TextComponent(Data.prefix + Messages.newmaintenancereason.replaceAll("%newreason%", reason)));
        }
        else if (args[0].equalsIgnoreCase("duration"))
        {
          String duration = "";
          for (int i = 1; i < args.length; i++) {
            duration = duration + " " + args[i];
          }
          duration = duration.replaceFirst(" ", "");
          
          Data.maintenanceduration = duration;
          
          sender.sendMessage(new TextComponent(Data.prefix + Messages.newmaintenanceduration.replaceAll("%newduration%", duration)));
        }
        else
        {
          sender.sendMessage(new TextComponent(Data.prefix + "§cUsage§8: §8/§7maintenance §8<§7duration§8/§7reason§8> §8<§7new Duration §8/§7 new Reason§8>"));
        }
      }
      else
      {
        sender.sendMessage(new TextComponent(Data.prefix + "§cUsage?8: §8/§7maintenance §8<§7duration§8/§7reason§8> §8<§7new Duration §8/§7 new Reason§8>"));
        sender.sendMessage(new TextComponent(Data.prefix + "§cUsage§8: §8/§7maintenance §8<§7on§8/§7off§8>"));
      }
    }
    else {
      sender.sendMessage(new TextComponent(Messages.nopermission));
    }
  }
}
