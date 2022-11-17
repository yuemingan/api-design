package a0;

public class Main {
    //driver main function to start and enable the app.
    public static void main(String[] args) {
        Rest rest = new Rest();
        rest.start();
        rest.getDate();
        rest.getDay();
        rest.getMonth();
        rest.getYear();
        rest.addOrModifyEvent();
        rest.deleteEvent();
        rest.getEvent();

    }
}
