package model;

/**
 * Created by A on 15. 5. 22..
 */
public class Event {
    private String tag;
    public Event(String tag){
        this.setTag(tag);
    }
    public void action(){

    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}