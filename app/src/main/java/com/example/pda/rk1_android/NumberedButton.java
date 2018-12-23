package com.example.pda.rk1_android;

import android.view.View;

import java.util.Objects;

public class NumberedButton {
    Integer number;
    Boolean isClicked = Boolean.FALSE;
    NumbersFragment.onNumberClicked onClick = null;

    public Boolean getClicked() {
        return isClicked;
    }

    public void setClicked(Boolean clicked) {
        isClicked = clicked;
    }

    NumberedButton(int i) {
        number = i;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setOnClick(NumbersFragment.onNumberClicked onc) {
        onClick = onc;
    }

    public NumbersFragment.onNumberClicked getOnClick() {
        return onClick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberedButton that = (NumberedButton) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number);
    }
}
