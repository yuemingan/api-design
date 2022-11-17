package a0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
/**
 * @author Yueming An.
 * andrew id: yueminga
 */
public class App {
    //map to store events.
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    //date getter
    public String getDate() {
        int date = Calendar.getInstance().get(Calendar.DATE);
        return String.valueOf(date);
    }
    //day getter
    public String getDay() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return String.valueOf(day);
    }
    //month getter
    public String getMonth() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        return String.valueOf(month);
    }
    //year getter
    public String getYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(year);
    }
    /**
     * add event 
     * @param year
     * @param month
     * @param day
     * @param date
     * @param event
     */
    public void addEvent(String year, String month, String day, String date, String event) {
        String key = year + "|" + month + "|" + date + "|" + day;
        List<String> events = new ArrayList<>();
        if (map.containsKey(key)) {
            events = map.get(key);
        }
        events.add(event);
        map.put(key, events);
    }
    /**
     * modify event
     * @param event
     * @param year
     * @param month
     * @param day
     * @param date
     * @param event2
     */
    public void modifyEvent(String event, String year, String month, String day, String date, String event2) {
        List<String> events = new ArrayList<String>();
        String key = year + "|" + month + "|" + date + "|" + day;
        if (map.containsKey(key)) {
            events = map.get(key);
        }
        if (events.contains(event)) {
            events.set(events.indexOf(event), event2);
            map.put(key, events);
        }

    }
    /**
     * delete event
     * @param year
     * @param month
     * @param day
     * @param date
     * @param event
     */
    public void deleteEvent(String year, String month, String day, String date, String event) {
        List<String> events = new ArrayList<String>();
        String key = year + "|" + month + "|" + date + "|" + day;
        if (map.containsKey(key)) {
            events = map.get(key);
            if (events.contains(event)) {
                events.remove(event);
                if (events.isEmpty()) {
                    map.remove(key);
                } else {
                    map.put(key, events);
                }
            }
        }
    }
/**
 * get event according to date
 * @param year
 * @param month
 * @param day
 * @param date
 * @return
 */
    public List<String> getEvents(String year, String month, String day, String date) {
        String key = year + "|" + month + "|" + date + "|" + day;
        return map.get(key);
    }
    
}
