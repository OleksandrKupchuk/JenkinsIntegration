package logger;

import com.epam.reportportal.service.ReportPortal;

import java.util.Calendar;

public class Logger {
    private String levelLog = "INFO";
    public void log(String message){
        ReportPortal.emitLog(message, levelLog, Calendar.getInstance().getTime());
    }

    public void log(String message, Calendar data){
        ReportPortal.emitLog(message, levelLog, data.getTime());
    }
}
