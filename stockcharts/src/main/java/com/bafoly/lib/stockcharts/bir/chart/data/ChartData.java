package com.bafoly.lib.stockcharts.bir.chart.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basarb on 10/4/2015.
 */
public class ChartData {
    List<String> tarih;

    List<Float> acilis;
    List<Float> dusuk;
    List<Float> yuksek;
    List<Float> kapanis; // single line tipindeki grafiklerde sadece kapanis kullanilacak
    List<Float> islem;

    public ChartData(){}

    public List<String> getTarih() {
        return tarih;
    }

    public void setTarih(List<String> tarih) {
        this.tarih = tarih;
    }

    public void addTarih(String tarih){
        if(this.tarih==null){
            this.tarih = new ArrayList<String>();
        }
        this.tarih.add(tarih);
    }

    public List<Float> getAcilis() {
        return acilis;
    }

    public void setAcilis(List<Float> acilis) {
        this.acilis = acilis;
    }

    public void addAcilis(float acilis){
        if(this.acilis==null){
            this.acilis = new ArrayList<Float>();
        }
        this.acilis.add(acilis);
    }

    public List<Float> getDusuk() {
        return dusuk;
    }

    public void setDusuk(List<Float> dusuk) {
        this.dusuk = dusuk;
    }

    public void addDusuk(float dusuk){
        if(this.dusuk==null){
            this.dusuk = new ArrayList<Float>();
        }
        this.dusuk.add(dusuk);
    }

    public List<Float> getYuksek() {
        return yuksek;
    }

    public void setYuksek(List<Float> yuksek) {
        this.yuksek = yuksek;
    }

    public void addYuksek(float yuksek){
        if(this.yuksek==null){
            this.yuksek = new ArrayList<Float>();
        }
        this.yuksek.add(yuksek);
    }

    public List<Float> getKapanis() {
        return kapanis;
    }

    public void setKapanis(List<Float> kapanis) {
        this.kapanis = kapanis;
    }

    public void addKapanis(float kapanis){
        if(this.kapanis==null){
            this.kapanis = new ArrayList<Float>();
        }
        this.kapanis.add(kapanis);
    }

    public List<Float> getIslem() {
        return islem;
    }

    public void setIslem(List<Float> islem) {
        this.islem = islem;
    }

    public void addIslem(float islem){
        if(this.islem==null){
            this.islem = new ArrayList<Float>();
        }
        this.islem.add(islem);
    }

    public List<Float> getLineData() {
        return kapanis;
    }

    public void setLineData(List<Float> lineData) {
        this.kapanis = lineData;
    }

}

