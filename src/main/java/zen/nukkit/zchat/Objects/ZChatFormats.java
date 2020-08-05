package zen.nukkit.zchat.Objects;

import cn.nukkit.level.Level;

import java.util.concurrent.ConcurrentHashMap;

public class ZChatFormats {

    private final String group;

    private final ConcurrentHashMap<Level, String> chatFormats = new ConcurrentHashMap<>();
    private String chatFormat;

    private final ConcurrentHashMap<Level, String> tagFormats = new ConcurrentHashMap<>();
    private String tagFormat;

    private String joinFormat;
    private String quitFormat;
    private String welcomeMessage;

    public ZChatFormats(String group) {
        this.group = group;
    }

    public String getChatFormat() {
        return chatFormat;
    }

    public String getChatFormat(Level level) {
        return chatFormats.get(level);
    }

    public String getTagFormat() {
        return tagFormat;
    }

    public String getTagFormat(Level level) {
        return tagFormats.get(level);
    }

    public String getGroup() {
        return group;
    }

    public String getJoinFormat() {
        return joinFormat;
    }

    public void setJoinFormat(String joinFormat) {
        this.joinFormat = joinFormat;
    }

    public String getQuitFormat() {
        return quitFormat;
    }

    public void setQuitFormat(String quitFormat) {
        this.quitFormat = quitFormat;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
