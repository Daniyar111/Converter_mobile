package com.example.saint.convertproject.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saint on 19.03.2018.
 */

public class CurrencyGetter {

    private String base;
    private String date;
    private Rates rates;

    public List<CurrencyModel> getCurrencyList(){

        List<CurrencyModel> currencyModelList = new ArrayList<>();

        currencyModelList.add(new CurrencyModel("EUR", rates.getEUR()));

        currencyModelList.add(new CurrencyModel("AUD", rates.getAUD()));
        currencyModelList.add(new CurrencyModel("BGN", rates.getBGN()));
        currencyModelList.add(new CurrencyModel("BRL", rates.getBRL()));
        currencyModelList.add(new CurrencyModel("CAD", rates.getCAD()));
        currencyModelList.add(new CurrencyModel("CHF", rates.getCHF()));

        currencyModelList.add(new CurrencyModel("CNY", rates.getCNY()));
        currencyModelList.add(new CurrencyModel("CZK", rates.getCZK()));
        currencyModelList.add(new CurrencyModel("DKK", rates.getDKK()));
        currencyModelList.add(new CurrencyModel("GBP", rates.getGBP()));
        currencyModelList.add(new CurrencyModel("HKD", rates.getHKD()));

        currencyModelList.add(new CurrencyModel("HRK", rates.getHRK()));
        currencyModelList.add(new CurrencyModel("HUF", rates.getHUF()));
        currencyModelList.add(new CurrencyModel("IDR", rates.getIDR()));
        currencyModelList.add(new CurrencyModel("ILS", rates.getILS()));
        currencyModelList.add(new CurrencyModel("INR", rates.getINR()));

        currencyModelList.add(new CurrencyModel("ISK", rates.getISK()));
        currencyModelList.add(new CurrencyModel("JPY", rates.getJPY()));
        currencyModelList.add(new CurrencyModel("KRW", rates.getKRW()));
        currencyModelList.add(new CurrencyModel("MXN", rates.getMXN()));
        currencyModelList.add(new CurrencyModel("MYR", rates.getMYR()));

        currencyModelList.add(new CurrencyModel("NOK", rates.getNOK()));
        currencyModelList.add(new CurrencyModel("NZD", rates.getNZD()));
        currencyModelList.add(new CurrencyModel("PHP", rates.getPHP()));
        currencyModelList.add(new CurrencyModel("PLN", rates.getPLN()));
        currencyModelList.add(new CurrencyModel("RON", rates.getRON()));

        currencyModelList.add(new CurrencyModel("RUB", rates.getRUB()));
        currencyModelList.add(new CurrencyModel("SEK", rates.getSEK()));
        currencyModelList.add(new CurrencyModel("SGD", rates.getSGD()));
        currencyModelList.add(new CurrencyModel("THB", rates.getTHB()));
        currencyModelList.add(new CurrencyModel("TRY", rates.getTRY()));

        currencyModelList.add(new CurrencyModel("USD", rates.getUSD()));
        currencyModelList.add(new CurrencyModel("ZAR", rates.getZAR()));

        return currencyModelList;
    }


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public static class Rates{

        private double EUR;

        public double getEUR() {
            return 1;
        }


        private double AUD;
        private double BGN;
        private double BRL;
        private double CAD;
        private double CHF;
        private double CNY;
        private double CZK;
        private double DKK;
        private double GBP;
        private double HKD;
        private double HRK;
        private double HUF;
        private double IDR;
        private double ILS;
        private double INR;
        private double ISK;
        private double JPY;
        private double KRW;
        private double MXN;
        private double MYR;
        private double NOK;
        private double NZD;
        private double PHP;
        private double PLN;
        private double RON;
        private double RUB;
        private double SEK;
        private double SGD;
        private double THB;
        private double TRY;
        private double USD;
        private double ZAR;

        public double getAUD() {
            return AUD;
        }

        public void setAUD(double AUD) {
            this.AUD = AUD;
        }

        public double getBGN() {
            return BGN;
        }

        public void setBGN(double BGN) {
            this.BGN = BGN;
        }

        public double getBRL() {
            return BRL;
        }

        public void setBRL(double BRL) {
            this.BRL = BRL;
        }

        public double getCAD() {
            return CAD;
        }

        public void setCAD(double CAD) {
            this.CAD = CAD;
        }

        public double getCHF() {
            return CHF;
        }

        public void setCHF(double CHF) {
            this.CHF = CHF;
        }

        public double getCNY() {
            return CNY;
        }

        public void setCNY(double CNY) {
            this.CNY = CNY;
        }

        public double getCZK() {
            return CZK;
        }

        public void setCZK(double CZK) {
            this.CZK = CZK;
        }

        public double getDKK() {
            return DKK;
        }

        public void setDKK(double DKK) {
            this.DKK = DKK;
        }

        public double getGBP() {
            return GBP;
        }

        public void setGBP(double GBP) {
            this.GBP = GBP;
        }

        public double getHKD() {
            return HKD;
        }

        public void setHKD(double HKD) {
            this.HKD = HKD;
        }

        public double getHRK() {
            return HRK;
        }

        public void setHRK(double HRK) {
            this.HRK = HRK;
        }

        public double getHUF() {
            return HUF;
        }

        public void setHUF(double HUF) {
            this.HUF = HUF;
        }

        public double getIDR() {
            return IDR;
        }

        public void setIDR(double IDR) {
            this.IDR = IDR;
        }

        public double getILS() {
            return ILS;
        }

        public void setILS(double ILS) {
            this.ILS = ILS;
        }

        public double getINR() {
            return INR;
        }

        public void setINR(double INR) {
            this.INR = INR;
        }

        public double getISK() {
            return ISK;
        }

        public void setISK(double ISK) {
            this.ISK = ISK;
        }

        public double getJPY() {
            return JPY;
        }

        public void setJPY(double JPY) {
            this.JPY = JPY;
        }

        public double getKRW() {
            return KRW;
        }

        public void setKRW(double KRW) {
            this.KRW = KRW;
        }

        public double getMXN() {
            return MXN;
        }

        public void setMXN(double MXN) {
            this.MXN = MXN;
        }

        public double getMYR() {
            return MYR;
        }

        public void setMYR(double MYR) {
            this.MYR = MYR;
        }

        public double getNOK() {
            return NOK;
        }

        public void setNOK(double NOK) {
            this.NOK = NOK;
        }

        public double getNZD() {
            return NZD;
        }

        public void setNZD(double NZD) {
            this.NZD = NZD;
        }

        public double getPHP() {
            return PHP;
        }

        public void setPHP(double PHP) {
            this.PHP = PHP;
        }

        public double getPLN() {
            return PLN;
        }

        public void setPLN(double PLN) {
            this.PLN = PLN;
        }

        public double getRON() {
            return RON;
        }

        public void setRON(double RON) {
            this.RON = RON;
        }

        public double getRUB() {
            return RUB;
        }

        public void setRUB(double RUB) {
            this.RUB = RUB;
        }

        public double getSEK() {
            return SEK;
        }

        public void setSEK(double SEK) {
            this.SEK = SEK;
        }

        public double getSGD() {
            return SGD;
        }

        public void setSGD(double SGD) {
            this.SGD = SGD;
        }

        public double getTHB() {
            return THB;
        }

        public void setTHB(double THB) {
            this.THB = THB;
        }

        public double getTRY() {
            return TRY;
        }

        public void setTRY(double TRY) {
            this.TRY = TRY;
        }

        public double getUSD() {
            return USD;
        }

        public void setUSD(double USD) {
            this.USD = USD;
        }

        public double getZAR() {
            return ZAR;
        }

        public void setZAR(double ZAR) {
            this.ZAR = ZAR;
        }


    }
}
