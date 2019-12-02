package c.dopemz.timetracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TimeTracker extends AppCompatActivity implements View.OnClickListener {
//------------------------------------------------------------------------classes, texts, names, buttons, ect. below--------------------------------
    private int idnum = 1;
    private Button newtimerbtn;
    private ScrollView scroll;
    private LinearLayout vlay;
    private LinearLayout hlay;
    private LinearLayout multivlay[] = new LinearLayout[50];
    private LinearLayout hlaybtns[] = new LinearLayout[50];
    private LinearLayout hlaynums[] = new LinearLayout[50];
    private EditText stuff[] = new EditText[50];
    private TextView days[] = new TextView[50];
    private TextView hours[] = new TextView[50];
    private TextView mins[] = new TextView[50];
    private TextView secs[] = new TextView[50];
    private int runstat[] = new int[50];
    private TextView currentnum;
    private Button remove;
    private Button startstop;
    private Button offclick;
    private Button offclick2;
    private TimerClass[] timers = new TimerClass[50];
    private int length = 0;  //default to 0, if we don't load it's 0
//------------------------------------------------------------------------classes, texts, names, buttons, ect. above--------------------------------
    //loading data
    private SharedPreferences mprefs;
    private SharedPreferences.Editor meditor;
    private String YearArray[] = new String[50];
    private String DayArray[] = new String[50];
    private String HourArray[] = new String[50];
    private String MinArray[] = new String[50];
    private String SecArray[] = new String[50];
    private String RunArray[] = new String[50];
    private String NameArray[] = new String[50];
    private int running[] = new int[50];

//------------------------------------------------------------------------Timer, clock, counting, ect. below-------------------------------------------------------------
    private static final long START_TIME_IN_MILLIS = 600000000;   //10000 mins
    private CountDownTimer countTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private Calendar currentTime;
//------------------------------------------------------------------------Timer, clock, counting, ect. above-------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_tracker);
//------------------------------------------------------------------------classes, texts, names, buttons, ect. below--------------------------------
        newtimerbtn = findViewById(R.id.newtimer);
        scroll = findViewById(R.id.scroll);
        vlay = findViewById(R.id.vlay);
        hlay = findViewById(R.id.hlay);
        currentnum = findViewById(R.id.currentnum);
        offclick = findViewById(R.id.offclick);
        offclick.setVisibility(View.VISIBLE);
        offclick.setBackgroundColor(Color.TRANSPARENT);
        offclick2 = findViewById(R.id.offclick2);
        offclick2.setVisibility(View.VISIBLE);
        offclick2.setBackgroundColor(Color.TRANSPARENT);
        remove = new Button(this);
        int removeid = 1000;
        remove.setId(removeid);
        startstop = new Button(this);
        int startstopid = 2000;
        startstop.setId(startstopid);
        newtimerbtn.setOnClickListener(this);
        offclick.setOnClickListener(this);
        offclick2.setOnClickListener(this);

        //loading data
        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        meditor = mprefs.edit();
        LoadStringArray();
        loadData();

        //dont keep this function for too long
        //troubleshooting, just fill timer data with something to start
        //for(int i = 0; i < length; i++){
        //    timers[i] = new TimerClass(Calendar.getInstance(), Integer.toString(i));
        //}

        //thinking ahead, we should load stored data before we build the scene duhhhhh
        rebuildScene();

        currentnum.setText(Integer.toString(length));
//------------------------------------------------------------------------classes, texts, names, buttons, ect. above--------------------------------

//------------------------------------------------------------------------Timer, clock, counting, ect. below-------------------------------------------------------------


        startTimer();
//------------------------------------------------------------------------Timer, clock, counting, ect. above-------------------------------------------------------------
        //rebuildScene();
    }

    //gives the loaddata function an easier time to operate, call this before calling load data
    public void LoadStringArray(){

        YearArray[0] = getString(R.string.year0);
        DayArray[0] = getString(R.string.day0);
        HourArray[0] = getString(R.string.hour0);
        MinArray[0] = getString(R.string.min0);
        SecArray[0] = getString(R.string.sec0);
        RunArray[0] = getString(R.string.run0);
        NameArray[0] = getString(R.string.text0);

        YearArray[1] = getString(R.string.year1);
        DayArray[1] = getString(R.string.day1);
        HourArray[1] = getString(R.string.hour1);
        MinArray[1] = getString(R.string.min1);
        SecArray[1] = getString(R.string.sec1);
        RunArray[1] = getString(R.string.run1);
        NameArray[1] = getString(R.string.text1);

        YearArray[2] = getString(R.string.year2);
        DayArray[2] = getString(R.string.day2);
        HourArray[2] = getString(R.string.hour2);
        MinArray[2] = getString(R.string.min2);
        SecArray[2] = getString(R.string.sec2);
        RunArray[2] = getString(R.string.run2);
        NameArray[2] = getString(R.string.text2);

        YearArray[3] = getString(R.string.year3);
        DayArray[3] = getString(R.string.day3);
        HourArray[3] = getString(R.string.hour3);
        MinArray[3] = getString(R.string.min3);
        SecArray[3] = getString(R.string.sec3);
        RunArray[3] = getString(R.string.run3);
        NameArray[3] = getString(R.string.text3);

        YearArray[4] = getString(R.string.year4);
        DayArray[4] = getString(R.string.day4);
        HourArray[4] = getString(R.string.hour4);
        MinArray[4] = getString(R.string.min4);
        SecArray[4] = getString(R.string.sec4);
        RunArray[4] = getString(R.string.run4);
        NameArray[4] = getString(R.string.text4);

        YearArray[5] = getString(R.string.year5);
        DayArray[5] = getString(R.string.day5);
        HourArray[5] = getString(R.string.hour5);
        MinArray[5] = getString(R.string.min5);
        SecArray[5] = getString(R.string.sec5);
        RunArray[5] = getString(R.string.run5);
        NameArray[5] = getString(R.string.text5);

        YearArray[6] = getString(R.string.year6);
        DayArray[6] = getString(R.string.day6);
        HourArray[6] = getString(R.string.hour6);
        MinArray[6] = getString(R.string.min6);
        SecArray[6] = getString(R.string.sec6);
        RunArray[6] = getString(R.string.run6);
        NameArray[6] = getString(R.string.text6);

        YearArray[7] = getString(R.string.year7);
        DayArray[7] = getString(R.string.day7);
        HourArray[7] = getString(R.string.hour7);
        MinArray[7] = getString(R.string.min7);
        SecArray[7] = getString(R.string.sec7);
        RunArray[7] = getString(R.string.run7);
        NameArray[7] = getString(R.string.text7);

        YearArray[8] = getString(R.string.year8);
        DayArray[8] = getString(R.string.day8);
        HourArray[8] = getString(R.string.hour8);
        MinArray[8] = getString(R.string.min8);
        SecArray[8] = getString(R.string.sec8);
        RunArray[8] = getString(R.string.run8);
        NameArray[8] = getString(R.string.text8);

        YearArray[9] = getString(R.string.year9);
        DayArray[9] = getString(R.string.day9);
        HourArray[9] = getString(R.string.hour9);
        MinArray[9] = getString(R.string.min9);
        SecArray[9] = getString(R.string.sec9);
        RunArray[9] = getString(R.string.run9);
        NameArray[9] = getString(R.string.text9);

        YearArray[10] = getString(R.string.year10);
        DayArray[10] = getString(R.string.day10);
        HourArray[10] = getString(R.string.hour10);
        MinArray[10] = getString(R.string.min10);
        SecArray[10] = getString(R.string.sec10);
        RunArray[10] = getString(R.string.run10);
        NameArray[10] = getString(R.string.text10);

        YearArray[11] = getString(R.string.year11);
        DayArray[11] = getString(R.string.day11);
        HourArray[11] = getString(R.string.hour11);
        MinArray[11] = getString(R.string.min11);
        SecArray[11] = getString(R.string.sec11);
        RunArray[11] = getString(R.string.run11);
        NameArray[11] = getString(R.string.text11);

        YearArray[12] = getString(R.string.year12);
        DayArray[12] = getString(R.string.day12);
        HourArray[12] = getString(R.string.hour12);
        MinArray[12] = getString(R.string.min12);
        SecArray[12] = getString(R.string.sec12);
        RunArray[12] = getString(R.string.run12);
        NameArray[12] = getString(R.string.text12);

        YearArray[13] = getString(R.string.year13);
        DayArray[13] = getString(R.string.day13);
        HourArray[13] = getString(R.string.hour13);
        MinArray[13] = getString(R.string.min13);
        SecArray[13] = getString(R.string.sec13);
        RunArray[13] = getString(R.string.run13);
        NameArray[13] = getString(R.string.text13);

        YearArray[14] = getString(R.string.year14);
        DayArray[14] = getString(R.string.day14);
        HourArray[14] = getString(R.string.hour14);
        MinArray[14] = getString(R.string.min14);
        SecArray[14] = getString(R.string.sec14);
        RunArray[14] = getString(R.string.run14);
        NameArray[14] = getString(R.string.text14);

        YearArray[15] = getString(R.string.year15);
        DayArray[15] = getString(R.string.day15);
        HourArray[15] = getString(R.string.hour15);
        MinArray[15] = getString(R.string.min15);
        SecArray[15] = getString(R.string.sec15);
        RunArray[15] = getString(R.string.run15);
        NameArray[15] = getString(R.string.text15);

        YearArray[16] = getString(R.string.year16);
        DayArray[16] = getString(R.string.day16);
        HourArray[16] = getString(R.string.hour16);
        MinArray[16] = getString(R.string.min16);
        SecArray[16] = getString(R.string.sec16);
        RunArray[16] = getString(R.string.run16);
        NameArray[16] = getString(R.string.text16);

        YearArray[17] = getString(R.string.year17);
        DayArray[17] = getString(R.string.day17);
        HourArray[17] = getString(R.string.hour17);
        MinArray[17] = getString(R.string.min17);
        SecArray[17] = getString(R.string.sec17);
        RunArray[17] = getString(R.string.run17);
        NameArray[17] = getString(R.string.text17);

        YearArray[18] = getString(R.string.year18);
        DayArray[18] = getString(R.string.day18);
        HourArray[18] = getString(R.string.hour18);
        MinArray[18] = getString(R.string.min18);
        SecArray[18] = getString(R.string.sec18);
        RunArray[18] = getString(R.string.run18);
        NameArray[18] = getString(R.string.text18);

        YearArray[19] = getString(R.string.year19);
        DayArray[19] = getString(R.string.day19);
        HourArray[19] = getString(R.string.hour19);
        MinArray[19] = getString(R.string.min19);
        SecArray[19] = getString(R.string.sec19);
        RunArray[19] = getString(R.string.run19);
        NameArray[19] = getString(R.string.text19);

        YearArray[20] = getString(R.string.year20);
        DayArray[20] = getString(R.string.day20);
        HourArray[20] = getString(R.string.hour20);
        MinArray[20] = getString(R.string.min20);
        SecArray[20] = getString(R.string.sec20);
        RunArray[20] = getString(R.string.run20);
        NameArray[20] = getString(R.string.text20);

        YearArray[21] = getString(R.string.year21);
        DayArray[21] = getString(R.string.day21);
        HourArray[21] = getString(R.string.hour21);
        MinArray[21] = getString(R.string.min21);
        SecArray[21] = getString(R.string.sec21);
        RunArray[21] = getString(R.string.run21);
        NameArray[21] = getString(R.string.text21);
    }

    public void loadData(){
        //load from strings
        //load length
        //load run status
        //load day hour min sec
        //load name
        //set it all into the arrays
        //let the updateTime function handle where the arrays put that data

        String timernum = mprefs.getString(getString(R.string.TimerNum), "0");
        length = Integer.parseInt(timernum);

        for (int i = 0; i < length; i++) {
            timers[i] = new TimerClass();
            String run = mprefs.getString(RunArray[i], "0");
            running[i] = Integer.parseInt(run);
            if(running[i] == 0)
                timers[i].setRun(false);
            else
                timers[i].setRun(true);

            String loadyear = mprefs.getString(YearArray[i], "");
            String loadday = mprefs.getString(DayArray[i], "");
            String loadhour = mprefs.getString(HourArray[i], "");
            String loadmin = mprefs.getString(MinArray[i], "");
            String loadsec = mprefs.getString(SecArray[i], "");
            String loadtext = mprefs.getString(NameArray[i], "");

            timers[i].newTime(Integer.parseInt(loadyear), Integer.parseInt(loadday), Integer.parseInt(loadhour), Integer.parseInt(loadmin), Integer.parseInt(loadsec));
            timers[i].newName(loadtext);
        }

    }

    public void saveData(){
        //have this called in the updateTimer so its called every second
        //get days, hours, mins, secs from each timer and save
        //get names from each timer and save
        //get run status from each timer and save
        //get length and save

        String timernum = Integer.toString(length);
        meditor.putString(getString(R.string.TimerNum), timernum);
        meditor.apply();

        for(int i = 0; i < length; i++){

            if (timers[i].getRun()) {
                running[i] = 1;
            } else
                running[i] = 0;

            String loadyear = Integer.toString(timers[i].getYears());
            meditor.putString(YearArray[i], loadyear);
            meditor.commit();

            String loadday = Integer.toString(timers[i].getDays());
            meditor.putString(DayArray[i], loadday);
            meditor.commit();

            String loadhour = Integer.toString(timers[i].getHours());
            meditor.putString(HourArray[i], loadhour);
            meditor.commit();

            String loadmin = Integer.toString(timers[i].getMins());
            meditor.putString(MinArray[i], loadmin);
            meditor.commit();

            String loadsec = Integer.toString(timers[i].getSecs());
            meditor.putString(SecArray[i], loadsec);
            meditor.commit();

            String loadrun = Integer.toString(running[i]);
            meditor.putString(RunArray[i], loadrun);
            meditor.commit();

            String loadtext = timers[i].getText();
            meditor.putString(NameArray[i], loadtext);
            meditor.apply();


        }


    }

    //handle buttons when they are clicked
    @Override
    public void onClick(View v) {
        idnum = v.getId();
        switch (idnum) {
            //when any of the timers are clicked
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
            case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20:
                buttonstuff(idnum);
                break;

            //"newtimer"
            //close all other timers, add one to length, rebuild the scene
            case R.id.newtimer:
                if (length < 20) {
                    for(int i = 0; i < length; i++){
                        hlaybtns[i].removeAllViews();
                        hlaybtns[i].setMinimumHeight(0);
                        stuff[i].setEnabled(false);
                    }
                    length++;
                    currentnum.setText(Integer.toString(length));
                    timers[length-1] = new TimerClass(Calendar.getInstance(), Integer.toString(length-1));
                    rebuildScene();
                }
                else{
                    length = 20;
                    Toast.makeText(this, "can only have 20", Toast.LENGTH_SHORT).show();
                }
                break;

            //"remove"
            //find whose open, close it out, remove one from length, shift everybodies data down one, rebuild scene
            case 1000:
                int found = 0;
                for(int i = 0; i < length; i++){
                    if(hlaybtns[i].getChildCount() > 0){
                        found = i;
                        hlaybtns[i].removeAllViews();
                        hlaybtns[length-1].removeAllViews();
                        hlaybtns[length-1].setVisibility(View.GONE);
                        multivlay[length-1].removeAllViews();
                        multivlay[length-1].setVisibility(View.GONE);
                        length--;
                        currentnum.setText(Integer.toString(length));
                    }

                }
                for(int i = found; i < length; i++){
                    timers[i] = new TimerClass(timers[i+1].getTime(), timers[i+1].getText());   //shift time and name
                    timers[i].setRun(timers[i+1].getRun());     //shift run status
                }
                rebuildScene();

                break;

            //"startstop"
            //find whose open,
            case 2000:
                for(int i = 0; i < length; i++){
                    if(hlaybtns[i].getChildCount() > 0){
                        if(timers[i].getRun()){
                            startstop.setText("Start");
                            timers[i].setRun(false);
                        }
                        else{
                            startstop.setText("Stop");
                            timers[i].newTime();
                            timers[i].setRun(true);
                        }
                    }
                }
                break;

            //"offclick buttons"
            //multiple buttons, if you dont click a timer or an actual button, this will just close any open timers and make its name un editable.
            case R.id.offclick: case R.id.offclick2:
                Toast.makeText(this, "yeet", Toast.LENGTH_SHORT).show();
                for(int i = 0; i < length; i++){
                    hlaybtns[i].removeAllViews();
                    hlaybtns[i].setMinimumHeight(0);
                    stuff[i].setEnabled(false);
                }
                break;
        }
    }

    //close all open timers, add a "remove" button to the timer that was clicked, enable the name to be editable
    private void buttonstuff(int vnum){
        for(int i = 0; i < length; i++){
            hlaybtns[i].removeAllViews();
            hlaybtns[i].setMinimumHeight(0);
            stuff[i].setEnabled(false);
        }
        //rebuildScene();
        Toast.makeText(this, Integer.toString(vnum), Toast.LENGTH_SHORT).show();
        if(timers[vnum].getRun())
            startstop.setText("Stop");
        else
            startstop.setText("Start");
        startstop.setHeight(50);
        startstop.setWidth(300);
        startstop.setOnClickListener(this);
        hlaybtns[vnum].addView(startstop);
        remove.setText("Remove");
        remove.setHeight(50);
        remove.setWidth(300);
        remove.setOnClickListener(this);
        hlaybtns[vnum].addView(remove);
        stuff[vnum].setEnabled(true);
    }

    //build scene, remove all layouts then fill all layouts from scratch, go until we hit length
    public void rebuildScene(){
        vlay.removeAllViews();
        for(int i = 0; i < length; i++){
            //setup vertical layout, this will hold timer name, horizontal layout1 (days,hours,mins,secs), horiztontal layout2 (mini menu buttons)
            multivlay[i] = new LinearLayout(TimeTracker.this);
            multivlay[i].setOrientation(LinearLayout.VERTICAL);
            multivlay[i].setOnClickListener(this);
            multivlay[i].setId(i);
            multivlay[i].setPadding(20,20,20,20);

            //setup text, read name from the timerclass and put it into the textview
            stuff[i] = new EditText(TimeTracker.this);
            stuff[i].setText(timers[i].getText());
            stuff[i].setEnabled(false);
            stuff[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            multivlay[i].addView(stuff[i]);
            //need to do math with calendar times below
            days[i] = new TextView(TimeTracker.this);
            hours[i] = new TextView(TimeTracker.this);
            mins[i] = new TextView(TimeTracker.this);
            secs[i] = new TextView(TimeTracker.this);

            String secsleftformat = String.format("%02d", 00);
            String minsleftformat = String.format("%02d", 00);
            String hoursleftformat = String.format("%02d", 00);
            secs[i].setText("  " + secsleftformat + ": Secs   ");
            mins[i].setText("  " + minsleftformat + ": Mins   ");
            hours[i].setText("  " + hoursleftformat+ ": Hours   ");
            days[i].setText("  " + 00 + ": Days   ");

            //setup horizontal layout for days, hours, mins, secs
            hlaynums[i] = new LinearLayout(TimeTracker.this);
            hlaynums[i].setOrientation(LinearLayout.HORIZONTAL);
            hlaynums[i].setPadding(0,0,0,0);
            hlaynums[i].addView(days[i]);
            hlaynums[i].addView(hours[i]);
            hlaynums[i].addView(mins[i]);
            hlaynums[i].addView(secs[i]);
            multivlay[i].addView(hlaynums[i]);

            //setup horizontal layout for buttons
            hlaybtns[i] = new LinearLayout(TimeTracker.this);
            hlaybtns[i].setOrientation(LinearLayout.HORIZONTAL);
            hlaybtns[i].setPadding(0,0,0,0);
            multivlay[i].addView(hlaybtns[i]);

            vlay.addView(multivlay[i]);
        }
        updateTime();   //call updatetime outside of the tick clock, just to format better/////this wont rush the speed of time
    }

    //begins clock ticking, doesnt actually last forever since this is a count down timer reversed, but it lasts 60000 minutes. internal countdown restarts at 60000 whenever app is opened
    private void startTimer() {
        mTimerRunning = true;
        countTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {  //do this for X mins
            @Override
            public void onTick(long millisUntilFinished) {      //this is what loops every second when timer is on
                updateTime();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
    }

    //called every tick (second) of internal clock. if the timer is running, it will increment its total running time by doing math from time when it started vs current time
    private void updateTime() {
        currentTime = Calendar.getInstance();
        for (int i = 0; i < length; i++) {
            timers[i].setText(stuff[i].getText().toString());

            if (timers[i].getRun()) {

                int mathyears = 0;
                int mathdays = 0;
                int mathhours = 0;
                int mathmins = 0;
                int mathsecs = 0;

                mathyears = currentTime.get(Calendar.YEAR) - timers[i].getYears();
                mathdays = currentTime.get(Calendar.DAY_OF_YEAR) - timers[i].getDays();
                mathhours = currentTime.get(Calendar.HOUR_OF_DAY) - timers[i].getHours();
                mathmins = currentTime.get(Calendar.MINUTE) - timers[i].getMins();
                mathsecs = currentTime.get(Calendar.SECOND) - timers[i].getSecs();

                if (mathsecs < 0) {
                    mathmins = mathmins - 1;
                    mathsecs = 60 + mathsecs;
                }
                if (mathmins < 0) {
                    mathhours = mathhours - 1;
                    mathmins = 60 + mathmins;
                }
                if (mathhours < 0) {
                    mathdays = mathdays - 1;
                    mathhours = 24 + mathhours;
                }
                if (mathdays < 0) {
                    mathyears = mathyears - 1;
                    mathdays = 365 + mathdays;
                }

                String secsleftformat = String.format("%02d", mathsecs);
                String minsleftformat = String.format("%02d", mathmins);
                String hoursleftformat = String.format("%02d", mathhours);
                secs[i].setText("  " + secsleftformat + ": Secs   ");
                mins[i].setText("  " + minsleftformat + ": Mins   ");
                hours[i].setText("  " + hoursleftformat+ ": Hours   ");
                days[i].setText("  " + mathdays + ": Days   ");
            }
        }
        saveData();
    }

}
