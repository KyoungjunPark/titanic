package model;

import java.util.ArrayList;

/**
 * Created by kimjisoo on 5/23/15.
 */
public class EventManager {
    static private ArrayList<Event> eventArrayList = new ArrayList<Event>();
    /**
     * event 를 받습니다.
     * @param e event 객체를 받습니다. event 객체는 action 메서드를 오버라이드 해야합니다.
     *          event 객체에 자세한 사항은 {@link Event} 를 참고하세요.
     */
    static public void addEvent(Event e){
        EventManager.eventArrayList.add(e);
    }

    /**
     * 이벤트 리스트에서 특정 이벤트를 삭제합니다.
     * @param e event 객체를 받습니다. 해당 객체를 이벤트 리스트에서 삭제합니다.
     */
    static public void removeEvent(Event e){
        EventManager.eventArrayList.remove(e);
    }
    /**
     * 특정 태그인 이벤트를 모두 실행합니다.
     * @param tag 해당 값으로 생성된 이벤트를 실행합니다.
     */
    static public void callEvent(String tag){
        for(Event e : EventManager.eventArrayList){
            if(e.getTag().compareTo(tag) == 0){
                e.action();
            }
        }
    }
}
