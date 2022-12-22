package com.wit.wafon.designmodelstudy.m_m_observer.e;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/12/22 17:56
 * @describe
 *
 *      示例：气象站
 *
 */
public class WeatherStationTest {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        Phone phone = new Phone(weatherStation);
        Window window = new Window(weatherStation);
        weatherStation.setData(39.0,60,1100);

    }
}

class Window implements Observer{
    private WeatherStation ws;
    public Window (WeatherStation ws){
        this.ws=ws;
        ws.addObserver(this);
    }
    @Override
    public void update() {
        System.out.println("智能窗户屏："+ws);
    }
}

class Phone implements Observer {

    private WeatherStation ws;
    public Phone (WeatherStation ws){
        this.ws=ws;
        ws.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("手机端："+ws);
    }
}
//=================================== 作者 ========================================
interface Observer {
    void update();
}


class WeatherStation {
    /**
     * 温度
     */
    private Double temperature;
    /**
     * 湿度
     */
    private Double humidity;
    /**
     * 气压
     */
    private Double pressure;

    /**
     * 观察者容器
     */
    private List<Observer> observers = new ArrayList<>();

    /**
     * 添加观察者者
     * @param observer
     */
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyObserver(){
        observers.forEach(v->v.update());
    }

    /**
     * 设置数据
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setData(double temperature,double humidity,double pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }

    @Override
    public String   toString() {
        return "WeatherStation{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
