package com.example.lab2androidmr;


import android.os.Parcel;
import android.os.Parcelable;

public class ModelOceny implements Parcelable {

    int selectedId;
    private String nazwa;
    private int ocena;


    ModelOceny(String nazwa, int ocena, int selectedId) {
        this.nazwa = nazwa;
        this.ocena = ocena;
        this.selectedId = selectedId;
    }


    protected ModelOceny(Parcel in) {
        nazwa = in.readString();
        ocena = in.readInt();
        selectedId = in.readInt();
    }

    public static final Creator<ModelOceny> CREATOR = new Creator<ModelOceny>() {
        @Override
        public ModelOceny createFromParcel(Parcel in) {
            return new ModelOceny(in);
        }

        @Override
        public ModelOceny[] newArray(int size) {
            return new ModelOceny[size];
        }
    };

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public int getSelectedId() { return selectedId; }

    public void setSelectedId(int selectedId) { this.selectedId = selectedId; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nazwa);
        parcel.writeInt(ocena);
        parcel.writeInt(selectedId);
    }
}
