package a0;

import java.util.List;
import io.javalin.Javalin;
/**
 * @author Yueming An
 * andrew id: yueminga
 */
public class Rest {
    //member variables
    Javalin app;
    App cApp = new App();

    //start the app
    public void start() {
        app = Javalin.create().start(8080);
    }

    /**
     * enable get date endpoint
     */
    public void getDate() {
        app.get("/date", ctx -> {
            ctx.result(cApp.getDate() + "");
            ctx.status(200);
        });
    }

    /**
     * enable get day endpoint
     */
    public void getDay() {
        app.get("/day", ctx -> {
            ctx.result(cApp.getDay() + "");
            ctx.status(200);
        });
    }

    /**
     * enable get month endpoint
     */
    public void getMonth() {
        app.get("/month", ctx -> {
            ctx.result(cApp.getMonth() + "");
            ctx.status(200);
        });
    }

    /**
     * enable get year endpoint
     */
    public void getYear() {
        app.get("/year", ctx -> {
            ctx.result(cApp.getYear() + "");
            ctx.status(200);
        });
    }

    /**
     * enable add event or modify event endpoint
     */
    public void addOrModifyEvent() {
        app.post("/event", ctx -> {
            String year = ctx.queryParam("year");
            String month = ctx.queryParam("month");
            String day = ctx.queryParam("day");
            String date = ctx.queryParam("date");
            String event = ctx.queryParam("event");
            String year2 = ctx.queryParam("year2");
            String month2 = ctx.queryParam("month2");
            String day2 = ctx.queryParam("day2");
            String date2 = ctx.queryParam("date2");
            String event2 = ctx.queryParam("event2");
            boolean add = (year != null) && (year2 == null);
            boolean modify = (year != null) && (year2 != null);
            //add event
            if (add) {
                try {
                    cApp.addEvent(year, month, day, date, event);
                    ctx.result("Succeed.");
                    ctx.status(200);
                }
                catch (Exception e) {
                    ctx.status(400);
                    ctx.result("Failed. Invalid input.");
                    return;
                }
            } else if (modify) {//modify event
                try {
                    cApp.modifyEvent(event, year, month, day, date, event2);
                    ctx.result("Succeed.");
                    ctx.status(200);
                } catch (Exception e) {
                    ctx.status(400);
                    ctx.result("Failed. Invalid input.");
                    return;
                }
            } else {//other invalid cases
                ctx.status(400);
                    ctx.result("Failed. Invalid input.");
                    return;
            }
        });
    }

    /**
     * enable delete event endpoint
     */
    public void deleteEvent() {
        app.delete("/event", ctx -> {
            String year = ctx.queryParam("year");
            String month = ctx.queryParam("month");
            String day = ctx.queryParam("day");
            String date = ctx.queryParam("date");
            String event = ctx.queryParam("event");
            //normal case
            if (year != null && month != null && day != null && date != null && event != null) {
                try {
                    cApp.deleteEvent(year, month, day, date, event);
                    ctx.result("Succeed.");
                    ctx.status(200);
                    }
                catch (Exception e) {
                    ctx.status(400);
                    ctx.result("Failed. Invalid input.");
                    return;
                }
            }
            else {//invalid cases
                ctx.status(400);
                ctx.result("Failed. Invalid input.");
                return;
            }
        });
    }
    /**
     * enable get event endpoint
     */
    public void getEvent() {
        app.get("/event", ctx -> {
            String year = ctx.queryParam("year");
            String month = ctx.queryParam("month");
            String day = ctx.queryParam("day");
            String date = ctx.queryParam("date");
            try {
                if (year != null && month != null && day != null && date != null) {
                    List<String> events = cApp.getEvents(year, month, day, date);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Succeed. ");
                    for (int i = 0; i < events.size(); i++) {
                        sb.append("event: " + events.get(i));
                    }
                    ctx.result(sb.toString());
                    ctx.status(200);
                }
            } catch (Exception e) {
                ctx.status(400);
                ctx.result("Failed. Invalid input.");
                return;
            }
            
        });
    }
}
