package SmartNavigationSystem;

import java.util.Date;

public class Schedule {

    private int scheduleIndex;
    private Date date;
    // private Mode mode;

    public Schedule(int i) {
        this.scheduleIndex = i;
        this.date = null;
    }

    @Override
    public String toString() {
        return "\nSchedule Comfirmed:\nschedule " + this.scheduleIndex + ":\nmode: " + " " + "\ndate: "
                + this.date.toString() + "\nWe will send you a prompt one day before your scheduled date";
    }

    

}
