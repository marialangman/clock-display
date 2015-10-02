
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @Maria Langman
 * @Sept 28, 2015
 */

public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private boolean display12Hour;
    private String displayString;    // simulates the actual display

    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * Contructor to create ClockDisplay objects if parameter isDisplay12Hour is true.
     * To hack the NumberDisplay's rollOverLimit to include 1 - 12 to display, create
     * a NumberDisplay object with rollOverLimit = 13. When 12:59 rolls over, the clock
     * displays 1:00. ClockDisplay object is initialized as 00:00, but updateDisplay shows 
     * time as 1:00.
     */
    public ClockDisplay(boolean isDisplay12Hour)
    {
        display12Hour = isDisplay12Hour;
        if (display12Hour)
            hours = new NumberDisplay(13);
        else
            hours = new NumberDisplay(24);  //if display12Hour is false, create a 24 hour clock
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }

    /**
     * Convert Military Time to 12 hour display
     */
    public void MilitaryTimeTo12HourDisplay()
    {
        String am_pm = "";
        if (!display12Hour)  //Make sure ClockDisplay is 24 hour mode
        {
            int currentHour = hours.getValue()%24;  //get the current hour
            if (currentHour < 12)
            {
                am_pm = " am";
            }
            else
            {
                am_pm = " pm";
                currentHour = currentHour - 12;
            }
            if (currentHour == 0)
                currentHour = 12;
            displayString = currentHour + ":" + minutes.getDisplayValue() + am_pm;
        }
        else
            displayString = hours.getDisplayValue()+ ":" + minutes.getDisplayValue();
    }

    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {

        if (display12Hour)
        {
            if (hours.getValue()==0 || hours.getValue()==13)
                hours.setValue(1);
        }
        displayString = hours.getDisplayValue()+ ":" + minutes.getDisplayValue();
    }

}

