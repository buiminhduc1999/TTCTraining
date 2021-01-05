package exerciseone;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    private static final LocalTime timeCheckInStart = LocalTime.of(8, 30);
    private static final LocalTime timeCheckInEnd = LocalTime.of(9, 00);
    private static final LocalTime timeCheckOutStart = LocalTime.of(17, 30);
    private static final LocalTime timeCheckOutEnd = LocalTime.of(18, 00);
    private static final LocalTime timeLunchBreakStart = LocalTime.of(12, 00);
    private static final LocalTime timeLunchBreakEnd = LocalTime.of(13, 00);
    private static final LocalTime timeEnough = LocalTime.of(8, 00);
    private static final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm");
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        System.out.print("Enter the start time to work: ");
        String timeStartToWork = sc.nextLine();
        System.out.print("Enter the end time to work: ");
        String timeEndToWork = sc.nextLine();
        //parse
        LocalTime timeStartToWorkFormat = LocalTime.parse(timeStartToWork, parseFormat);;
        LocalTime timeEndToWorkFormat = LocalTime.parse(timeEndToWork, parseFormat);;
        System.out.println("-------------------------------------------");
        System.out.println("Time start is:  " + timeStartToWorkFormat);
        System.out.println("Time end is: " + timeEndToWorkFormat);
        System.out.println("-------------------------------------------");
        //Tính thời gian nghỉ trưa
        main.setTimeWithTimeLunchBreak(timeStartToWorkFormat, timeEndToWorkFormat);
        LocalTime timeResult = main.getResults(timeStartToWorkFormat, timeEndToWorkFormat);
        if (timeStartToWorkFormat.compareTo(timeLunchBreakStart) < 0 && timeEndToWorkFormat.compareTo(timeLunchBreakEnd) >= 0) {
            timeResult = LocalTime.of(timeResult.getHour() - 1, timeResult.getMinute());
        }
        main.setMoreThan8HoursInto8Hours(timeResult);
        main.printlnMessage(timeStartToWorkFormat, timeEndToWorkFormat, timeResult);
        System.out.println(timeResult);
    }

    public LocalTime setMoreThan8HoursInto8Hours(LocalTime resultTime) {
        return checkWorkEnoughEightHour(resultTime) ? timeEnough : resultTime;
    }

    public void setTimeWithTimeLunchBreak(LocalTime timeStartToWorkFormat, LocalTime timeEndToWorkFormat) {
        if (timeStartToWorkFormat.compareTo(timeLunchBreakStart) < 0 && timeEndToWorkFormat.compareTo(timeLunchBreakEnd) <= 0) {
            timeEndToWorkFormat = timeLunchBreakStart;
        }
        if (timeStartToWorkFormat.compareTo(timeLunchBreakStart) >= 0 && timeEndToWorkFormat.compareTo(timeLunchBreakEnd) < 0) {
            timeStartToWorkFormat = timeLunchBreakEnd;
            timeEndToWorkFormat = timeLunchBreakEnd;
        }
        if (timeStartToWorkFormat.compareTo(timeLunchBreakStart) >= 0 && timeEndToWorkFormat.compareTo(timeLunchBreakEnd) >= 0) {
            timeStartToWorkFormat = timeLunchBreakEnd;
        }
    }

    public void printlnMessage(LocalTime timeStartToWorkFormat, LocalTime timeEndToWorkFormat, LocalTime resultTime) {
        if (!checkWorkEnoughEightHour(resultTime)) {
            System.out.println("You have not worked for 8 hours");
        } else {
            System.out.println("You have worked for 8 hours");
        }
        if (getIndexTimeStartWork(timeStartToWorkFormat) == OptionTime.ARRIVEAFTER9HOUR.getValue()) {
            System.out.println("You are late for work");
        }
        if (getIndexTimeEndWork(timeEndToWorkFormat) == OptionTime.GOBACKAFTER17HOUR30MINUS.getValue()) {
            System.out.println("You come home from work early");
        }
    }

    public LocalTime getResults(LocalTime timeStartToWorkFormat, LocalTime timeEndToWorkFormat) {
        int valueIndexStart = getIndexTimeStartWork(timeStartToWorkFormat);
        int valueIndexEnd = getIndexTimeEndWork(timeEndToWorkFormat);
        if (valueIndexStart == OptionTime.ARRIVEBEFOR8HOUR30MINUS.getValue() && valueIndexEnd == OptionTime.GOBACKBEFORE18HOUR.getValue()) {
            return calculatorTimeResult(timeCheckInStart, timeCheckOutEnd);
        }
        if (valueIndexStart == OptionTime.ARRIVEBEFOR8HOUR30MINUS.getValue() && valueIndexEnd == OptionTime.GOBACKBETWEEN17HOUR30AND18HOUR.getValue()) {
            return calculatorTimeResult(timeCheckInStart, timeEndToWorkFormat);
        }
        if (valueIndexStart == OptionTime.ARRIVEBEFOR8HOUR30MINUS.getValue() && valueIndexEnd == OptionTime.GOBACKAFTER17HOUR30MINUS.getValue()) {
            return calculatorTimeResult(timeCheckInStart, timeEndToWorkFormat);
        }
        if (valueIndexStart == OptionTime.ARRIVEBETWEEN8HOURAND9HOUR30MINUS.getValue() && valueIndexEnd == OptionTime.GOBACKBETWEEN17HOUR30AND18HOUR.getValue()) {
            return calculatorTimeResult(timeStartToWorkFormat, timeEndToWorkFormat);
        }
        if (valueIndexStart == OptionTime.ARRIVEBETWEEN8HOURAND9HOUR30MINUS.getValue() && valueIndexEnd == OptionTime.GOBACKAFTER17HOUR30MINUS.getValue()) {
            return calculatorTimeResult(timeStartToWorkFormat, timeEndToWorkFormat);
        }
        if (valueIndexStart == OptionTime.ARRIVEBETWEEN8HOURAND9HOUR30MINUS.getValue() && valueIndexEnd == OptionTime.GOBACKBEFORE18HOUR.getValue()) {
            return calculatorTimeResult(timeStartToWorkFormat, timeCheckOutEnd);
        }
        if (valueIndexStart == OptionTime.ARRIVEAFTER9HOUR.getValue() && valueIndexEnd == OptionTime.GOBACKBETWEEN17HOUR30AND18HOUR.getValue()) {
            return calculatorTimeResult(timeStartToWorkFormat, timeEndToWorkFormat);
        }
        if (valueIndexStart == OptionTime.ARRIVEAFTER9HOUR.getValue() && valueIndexEnd == OptionTime.GOBACKAFTER17HOUR30MINUS.getValue()) {
            return calculatorTimeResult(timeStartToWorkFormat, timeEndToWorkFormat);
        }
        return calculatorTimeResult(timeStartToWorkFormat, timeCheckOutEnd);
    }



    public Boolean checkabc(int valueIndexStart, int valueIndexEnd, int optionWithValuesIndexStart, int optionWithValuesIndexEnd){
        return (valueIndexStart == optionWithValuesIndexStart && valueIndexEnd == OptionTime.GOBACKAFTER17HOUR30MINUS.getValue()) ? true : false;
    }

    //check số giờ làm lớn hơn 8
    public Boolean checkWorkEnoughEightHour(LocalTime timeResult) {
        return timeResult.getHour() >= 8;
    }

    //trước 8h30 =0, 8h30<=x<=9h00 1, sau 9h là 2
    public int getIndexTimeStartWork(LocalTime timeStartToWorkFormat) {
        if (timeStartToWorkFormat.compareTo(timeCheckInStart) < 0) {
            return OptionTime.ARRIVEBEFOR8HOUR30MINUS.getValue();
        }
        if (timeStartToWorkFormat.compareTo(timeCheckInStart) >= 0 && timeStartToWorkFormat.compareTo(timeCheckInEnd) <= 0) {
            return OptionTime.ARRIVEBETWEEN8HOURAND9HOUR30MINUS.getValue();
        }
        return OptionTime.ARRIVEAFTER9HOUR.getValue();
    }

    //về trong khoảng 5h30<=x<=6h 3 ,về sau 6h 4, về trước 5h30 5,
    public int getIndexTimeEndWork(LocalTime timeEndToWorkFormat) {
        if (timeEndToWorkFormat.compareTo(timeCheckOutEnd) > 0) {
            return OptionTime.GOBACKBEFORE18HOUR.getValue();
        }
        if (timeEndToWorkFormat.compareTo(timeCheckOutStart) >= 0 && timeEndToWorkFormat.compareTo(timeCheckOutEnd) <= 0) {
            return OptionTime.GOBACKBETWEEN17HOUR30AND18HOUR.getValue();
        }
        return OptionTime.GOBACKAFTER17HOUR30MINUS.getValue();
    }

    public LocalTime calculatorTimeResult(LocalTime timeStartToWorkFormat, LocalTime timeEndToWorkFormat) {
        String result;
        long hourResult = ChronoUnit.HOURS.between(timeStartToWorkFormat, timeEndToWorkFormat);
        long totalMinusBetweenStartAndEndTime = ChronoUnit.MINUTES.between(timeStartToWorkFormat, timeEndToWorkFormat);
        int minutesResult = (int) (totalMinusBetweenStartAndEndTime - hourResult * 60);
        if (minutesResult < 10) {
            result = hourResult + ":" + minutesResult + "0";
        } else {
            result = hourResult + ":" + minutesResult;
        }
        LocalTime resultTime = LocalTime.parse(result, parseFormat);
        return resultTime;
    }
}
