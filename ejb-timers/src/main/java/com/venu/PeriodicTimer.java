package com.venu;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;

@Singleton
@Startup
public class PeriodicTimer {

    @Resource
    TimerService timerService;

    private int i = 1;

    @PostConstruct
    public void initialize(){
        TimerConfig tc = new TimerConfig();
        tc.setInfo("10 sec timer");
        cancelAllTimers();
        System.out.println("Post construct invoked");
        ScheduleExpression expression = new ScheduleExpression();
        expression.hour("*").minute("*").second("*/10");
        timerService.createCalendarTimer(expression,tc);
    }

    @Timeout
    public void execute() throws Exception{
       for (Timer t : timerService.getTimers()){
            System.out.println(t.getInfo());
        }
        System.out.println("Invoked: " + System.currentTimeMillis());
    }

    @PreDestroy
    public void cancelAllTimers(){
        for (Timer t : timerService.getTimers()){
            //if (t.getInfo().equals("10 sec timer")) {
                t.cancel();

        }

    }

    public Timer getTimer(){
        Timer retTimer = null;
        for (Timer t : timerService.getTimers()){
            if (t.getInfo().equals("10 sec timer")) {
                retTimer = t;
            }
        }
        return retTimer;

    }

    public void resetTimer(int seconds ){
        cancelAllTimers();
        TimerConfig tc = new TimerConfig();

        tc.setInfo("10 sec timer");
        System.out.println("RestTimer");
        ScheduleExpression expression = new ScheduleExpression();
        expression.hour("*").minute("*").second("*/"+seconds);
        timerService.createCalendarTimer(expression,tc);

    }
}
