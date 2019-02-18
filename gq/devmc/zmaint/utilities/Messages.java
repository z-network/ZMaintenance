package gq.devmc.zmaint.utilities;

import java.util.List;

public class Messages
{
  public static String teamnotify;
  public static String nopermission;
  public static List<String> cancellist;
  public static String alreadyonwhitelist;
  public static String isnotonwhitelist;
  public static String addedtowhitelist;
  public static String removedfromwhitelist;
  public static String alreadymaintenance;
  public static String isnotmaintenance;
  public static String maintenanceactive;
  public static String maintenancedeactive;
  public static String newmaintenancereason;
  public static String newmaintenanceduration;
  
  public static String cancelreason()
  {
    String reason = "";
    for (int i = 0; i < cancellist.size(); i++) {
      if (i + 1 < cancellist.size()) {
        reason = reason + ((String)cancellist.get(i)).replaceAll("&", "§").replaceAll("%prefix%", Data.prefix).replaceAll("%duration%", Data.maintenanceduration).replaceAll("%reason%", Data.maintenancereason).replaceAll("%servername%", Data.servername) + "\n§r";
      } else {
        reason = reason + ((String)cancellist.get(i)).replaceAll("&", "§").replaceAll("%prefix%", Data.prefix).replaceAll("%duration%", Data.maintenanceduration).replaceAll("%reason%", Data.maintenancereason).replaceAll("%servername%", Data.servername);
      }
    }
    return reason;
  }
}
