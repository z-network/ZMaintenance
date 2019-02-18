package gq.devmc.zmaint.utilities;

import java.util.List;

public class Motd
{
  public static String[] normalmotd;
  public static String[] maintenancemotd;
  public static List<String> playerinfo;
  public static String maintenancemessage;
  public static boolean motdsystem;
  public static int slots;
  
  public static String getPlayerInfo()
  {
    String info = "";
    for (int i = 0; i < playerinfo.size(); i++) {
      if (i + 1 < playerinfo.size()) {
        info = info + ((String)playerinfo.get(i)).replaceAll("&", "§").replaceAll("%prefix%", Data.prefix).replaceAll("%duration%", Data.maintenanceduration).replaceAll("%reason%", Data.maintenancereason).replaceAll("%servername%", Data.servername) + "\n§r";
      } else {
        info = info + ((String)playerinfo.get(i)).replaceAll("&", "§").replaceAll("%prefix%", Data.prefix).replaceAll("%duration%", Data.maintenanceduration).replaceAll("%reason%", Data.maintenancereason).replaceAll("%servername%", Data.servername);
      }
    }
    return info;
  }
}

