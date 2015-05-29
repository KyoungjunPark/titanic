package model;

/**
 * Created by A on 15. 5. 22..
 */
public class Event {
    private String tag;
    private String[] param;

    public Event(String tag){
        this.setTag(tag);
    }
    public Event(String tag, String... param){
        this.setTag(tag);
        this.param = param;
    }
    public void action(){

    }
    public void action(String... param){

    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}