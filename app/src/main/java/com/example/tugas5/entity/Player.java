package com.example.tugas5.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tPlayer")
public class Player implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public Integer age;

    @ColumnInfo(name = "number")
    public Integer number;

    @ColumnInfo(name = "position")
    public String position;

    public Player(){}

    protected Player(Parcel in) {
        this.uid = in.readInt();
        this.name = in.readString();
        this.age = in.readInt();
        this.number = in.readInt();
        this.position = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeInt(this.number);
        dest.writeString(this.position);
    }
}
