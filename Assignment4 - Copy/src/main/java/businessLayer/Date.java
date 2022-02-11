package businessLayer;

import java.io.Serializable;

public class Date implements Serializable {

    private int year,month,day,hour;

    public Date(int year, int month, int day, int hour) throws Exception {
        if(year>2022 || year<2020)
            throw new Exception("An incompatibil");
        else
            this.year=year;
        if(month>12 || month <1)
            throw new Exception("Luna incompatibila");
        else
            this.month=month;
        if(day>31 || day<1)
            throw new Exception("Zi incompatibila");
        else
            this.day=day;
        if(hour>24 || hour<1)
            throw new Exception("Ora incompatibila");
        else
            this.hour=hour;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }

    public String toString(){
        return this.day+"-"+this.month+"-"+this.year+" h_"+this.hour;
    }
}
