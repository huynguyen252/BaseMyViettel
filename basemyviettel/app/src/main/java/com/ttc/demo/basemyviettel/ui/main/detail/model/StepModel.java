package com.ttc.demo.basemyviettel.ui.main.detail.model;

public class StepModel {
    String title;
    boolean isSelected;
    boolean isPreSelected;
    boolean isLineSelected;
    boolean isLineFirstSelected;
    boolean isTextFocus;
    boolean isValidate;
    boolean isVisible;
    boolean isChangeTextNum;

    public StepModel(String title,
                     boolean isSelected,
                     boolean isPreSelected,
                     boolean isLineSelected,
                     boolean isTextFocus,
                     boolean isValidate,
                     boolean isVisible,
                     boolean isChangeTextNum) {
        this.title = title;
        this.isSelected = isSelected;
        this.isPreSelected = isPreSelected;
        this.isLineSelected = isLineSelected;
        this.isTextFocus = isTextFocus;
        this.isValidate = isValidate;
        this.isVisible = isVisible;
        this.isChangeTextNum = isChangeTextNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setLineSelected(boolean lineSelected) {
        isLineSelected = lineSelected;
    }

    public boolean isLineSelected() {
        return isLineSelected;
    }

    public boolean isLineFirstSelected() {
        return isLineFirstSelected;
    }

    public boolean isTextFocus() {
        return isTextFocus;
    }

    public void setLineFirstSelected(boolean lineFirstSelected) {
        isLineFirstSelected = lineFirstSelected;
    }

    public void setTextFocus(boolean textFocus) {
        isTextFocus = textFocus;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isChangeTextNum() {
        return isChangeTextNum;
    }

    public void setChangeTextNum(boolean changeTextNum) {
        isChangeTextNum = changeTextNum;
    }
}
