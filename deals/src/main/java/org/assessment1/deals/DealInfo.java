package org.assessment1.deals;

public class DealInfo {
    private int deal_num;
    private String counterparty, location;
    private int volume;
    private String volume_unit, delivery_date;


    public DealInfo(int deal_num, String counterparty, String location, int volume, String volume_unit, String delivery_date) {
        this.deal_num = deal_num;
        this.volume = volume;
        this.counterparty = counterparty;
        this.location = location;
        this.volume_unit = volume_unit;
        this.delivery_date = delivery_date;
    }

    public int getDeal_num() {
        return deal_num;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public String getLocation() {
        return location;
    }

    public String getVolumeUnit() {
        return volume_unit;
    }

    public void setVolumeUnit(String volume_unit) {
        this.volume_unit = volume_unit;
    }

    public String getDeliveryDate() {
        return delivery_date;
    }

    @Override
    public String toString() {
        return "DealInfo{" +
                "counterparty='" + counterparty + '\'' +
                ", location='" + location + '\'' +
                ", volume=" + volume +
                ", volume_unit='" + volume_unit + '\'' +
                ", delivery_date='" + delivery_date + '\'' +
                '}';
    }
}
