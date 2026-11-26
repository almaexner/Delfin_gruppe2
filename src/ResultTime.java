public class ResultTime {
    int minutes;
    int seconds;
    int milliseconds;

    public ResultTime(int minutes, int seconds, int milliseconds){
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }
    public int convertToMilliseconds(){ //1000 millisekunder på et sekund - 6000 på et minut
        return (minutes * 60000) + (seconds * 1000) + milliseconds;
    }

    public String toCsv() {
        return minutes + ":" + seconds + ":" + milliseconds;
    }

    @Override
    public String toString(){
        return String.format("""
                Tid: %d:%d:%d""", this.minutes, this.seconds, this.milliseconds);
    }
}
