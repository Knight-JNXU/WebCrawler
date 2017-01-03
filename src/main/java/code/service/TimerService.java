package code.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Knight_JXNU on 2017/1/3.
 */
@Service
public class TimerService {
    private Calendar calendar;
    {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }
    private Date time = calendar.getTime();
    @Autowired
    private ManagerService managerService;

    public TimerService() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                managerService.input();
            }
        }, time, 1000*60*60*24);
    }
}
