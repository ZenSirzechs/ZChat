package zen.nukkit.zchat.Data;

import cn.nukkit.utils.Config;
import zen.nukkit.zchat.Objects.ZChatFormats;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class DataCache {

    public static Config nickNames;

    public static final ConcurrentHashMap<String, ZChatFormats> formats = new ConcurrentHashMap<>();

    public static final HashSet<String> blockedTexts = new HashSet<>();
    public static final HashSet<String> textCensor = new HashSet<>();

}
