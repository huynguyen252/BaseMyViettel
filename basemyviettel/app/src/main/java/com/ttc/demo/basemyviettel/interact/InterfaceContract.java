package com.ttc.demo.basemyviettel.interact;

public interface InterfaceContract {
    interface Rating {
        void getRating(float numStar);
    }
    interface EditText{
        void getContentEditText(String content);
    }
    interface OTP {
        void onEnterCompleted(String cmt);
        void onClearCompleted(String cmt);
    }
    interface EnterDataOTP {
        void onEnterData(String cmt);
    }
    interface Button {
        void onClick(int id);
    }
    interface UnderLine {
        void clickUnderLine();
    }
    interface Spinner {
        void getItemSpinner(String content);
    }

}

