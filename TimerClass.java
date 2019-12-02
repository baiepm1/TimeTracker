package c.dopemz.timetracker;

import android.widget.TextView;

import java.util.Calendar;

public class TimerClass {
    private String name;
    private Calendar mytime;
    private boolean running = false;
    private int years = 0;
    private int days = 0;
    private int hours = 0;
    private int mins = 0;
    private int secs = 0;

    public TimerClass(){
        mytime = Calendar.getInstance();
        name = "";
    }

    public TimerClass(Calendar time, String text){
        mytime = time;
        name = text;
    }
//--------------------------------------------------------------GETTERS---------------------------------------------
    public String getText(){
        return name;
    }

    public Calendar getTime(){return mytime;}

    public int getYears(){
        if(days != 0 && hours != 0 & mins != 0 && secs != 0)
            return years;
        else
            return mytime.get(Calendar.YEAR);
    }
    public int getDays(){
        if(days != 0 && hours != 0 & mins != 0 && secs != 0)
            return days;
        else
            return mytime.get(Calendar.DAY_OF_YEAR);
    }
    public int getHours(){
        if(days != 0 && hours != 0 & mins != 0 && secs != 0)
            return hours;
        else
            return mytime.get(Calendar.HOUR_OF_DAY);
    }
    public int getMins(){
        if(days != 0 && hours != 0 & mins != 0 && secs != 0)
            return mins;
        else
            return mytime.get(Calendar.MINUTE);
    }
    public int getSecs(){
        if(days != 0 && hours != 0 & mins != 0 && secs != 0)
            return secs;
        else
            return mytime.get(Calendar.SECOND);
    }

    public boolean getRun(){return running;}
//--------------------------------------------------------------GETTERS---------------------------------------------

//--------------------------------------------------------------SETTERS---------------------------------------------
    public void setText(String txt){name = txt;}

    public void setRun(boolean status){running = status;}

    public void newTime(Calendar time){mytime = time;}

    public void newTime(){mytime = Calendar.getInstance();}

    public void newTime(int year, int day, int hour, int min, int sec){
        years = year;
        days = day;
        hours = hour;
        mins = min;
        secs = sec;
    }

    public void newName(String newname){name = newname;}
//--------------------------------------------------------------SETTERS---------------------------------------------
}
