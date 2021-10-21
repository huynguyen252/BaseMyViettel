package com.ttc.demo.basemyviettel.data.model;

import com.google.gson.annotations.SerializedName;

public class GetCommonSettingResult extends SimpleResult {
    @SerializedName("data")
    GetCommonSettingModel data;

    public GetCommonSettingModel getData() {
        return data;
    }

    public void setData(GetCommonSettingModel data) {
        this.data = data;
    }

    public class GetCommonSettingModel {
        @SerializedName("fee_switch_telco")
        String fee_switch_telco;
        @SerializedName("intro_switch_telco")
        String intro_switch_telco;
        @SerializedName("popup_switch_telco")
        String popup_switch_telco;
        @SerializedName("required_switch_telco")
        String required_switch_telco;
        @SerializedName("active_switch_telco")
        String active_switch_telco;
        @SerializedName("connect_pre_fee")
        String connect_pre_fee;
        @SerializedName("connect_pos_fee")
        String connect_pos_fee;
        @SerializedName("transfer_fee")
        String transfer_fee;
        @SerializedName("enable_pre_paid")
        String enable_pre_paid;
        @SerializedName("enable_pos_paid")
        String enable_pos_paid;
        @SerializedName("fee_switch_pre")
        String fee_switch_pre;
        @SerializedName("fee_switch_pos")
        String fee_switch_pos;
        @SerializedName("total_fee_title")
        String total_fee_title;
        @SerializedName("cmgs_otp_pattern")
        String cmgs_otp_pattern;
        @SerializedName("require_package_mnp")
        String require_package_mnp;
        @SerializedName("khdn_buy_sim")
        String khdnBuySim;

        //Cờ on/off bật phương thức thanh toán thẻ visa, atm mua sim không đăng nhập
        @SerializedName("enable_sim_ctt_nologin")
        String enable_sim_ctt_nologin;

        //bo sung co hien thi dia chi cap 4
        @SerializedName("active_omi_level4_address")
        String active_omi_level4_address;

        //Tiệm ích khi chưa login
        @SerializedName("tien_ich_not_login")
        String tiem_ich_not_login;

        @SerializedName("buy_sim_online_using_ai_identify_v6")
        String buySimOnlineUsingAIIdentifyV2;

        @SerializedName("buy_sim_online_ai_supporter_v6")
        String buySimOnlineAiSupporter;

        @SerializedName("buy_sim_online_video_call_verify_v6")
        String buySimOnlineVideoCallVerifyV2;

        @SerializedName("invite_ftth_benefit_information")
        String inviteFtthBenefitInformation;

        @SerializedName("new_browser")
        int newBrowser;

        @SerializedName("ftth_register_online_enable")
        String ftthRegisterOnlineEnable;
        @SerializedName("theme_tet_2020")
        String mThemeTet2020;

        @SerializedName("countdown_expire_time_otp_login")
        String countdownExpireTimeOtpLogin;

        @SerializedName("enable_register_partner")
        String enable_register_partner;

        @SerializedName("versionAndroid")
        String versionAndroid;

        @SerializedName("terms_BHOL")
        String terms_BHOL;

        @SerializedName("change_esim_liveness_detection_actions")
        String change_esim_liveness_detection_actions;

        @SerializedName("config-list-voucher")
        private String configListVoucher;

        @SerializedName("videocall_config_sdk")
        String videocall_config_sdk;

        @SerializedName("videocall_config_buy_sim")
        String videocall_config_buy_sim;

        public String getConfigListVoucher() {
            return configListVoucher;
        }


        //cờ on off ký tại nhà cho các luồng chuyển sang trả sau, chuyển mạng giữ số, đổi sim, gửi yêu cầu đổi sim
        @SerializedName("enable_option_sign_contract_home")
        String enable_option_sign_contract_home;

        public String getInviteFtthBenefitInformation() {
            if (inviteFtthBenefitInformation == null) {
                inviteFtthBenefitInformation = "";
            }
            return inviteFtthBenefitInformation;
        }

        public void setInviteFtthBenefitInformation(String inviteFtthBenefitInformation) {
            this.inviteFtthBenefitInformation = inviteFtthBenefitInformation;
        }

        public String getBuySimOnlineUsingAIIdentifyV2() {
            if (buySimOnlineUsingAIIdentifyV2 == null) {
                buySimOnlineUsingAIIdentifyV2 = "";
            }
            return buySimOnlineUsingAIIdentifyV2;
        }

        public String getBuySimOnlineAiSupporter() {
            return buySimOnlineAiSupporter == null ? "" : buySimOnlineAiSupporter;
        }

        public String getBuySimOnlineVideoCallVerifyV2() {
            if (buySimOnlineVideoCallVerifyV2 == null) {
                buySimOnlineVideoCallVerifyV2 = "";
            }
            return buySimOnlineVideoCallVerifyV2;
        }

        public String getActive_switch_telco() {
            return active_switch_telco == null ? "1" : active_switch_telco;
        }

        public void setActive_switch_telco(String active_switch_telco) {
            this.active_switch_telco = active_switch_telco;
        }

        public String getRequired_switch_telco() {
            return required_switch_telco == null ? "" : required_switch_telco;
        }

        public void setRequired_switch_telco(String required_switch_telco) {
            this.required_switch_telco = required_switch_telco;
        }

        public String getPopup_switch_telco() {
            return popup_switch_telco == null ? "" : popup_switch_telco;
        }

        public void setPopup_switch_telco(String popup_switch_telco) {
            this.popup_switch_telco = popup_switch_telco;
        }

        public String getFee_switch_telco() {
            return fee_switch_telco == null ? "" : fee_switch_telco;
        }

        public void setFee_switch_telco(String fee_switch_telco) {
            this.fee_switch_telco = fee_switch_telco;
        }

        public String getIntro_switch_telco() {
            return intro_switch_telco == null ? "" : intro_switch_telco;
        }

        public void setIntro_switch_telco(String intro_switch_telco) {
            this.intro_switch_telco = intro_switch_telco;
        }

        public String getConnect_pre_fee() {
            return connect_pre_fee;
        }

        public void setConnect_pre_fee(String connect_pre_fee) {
            this.connect_pre_fee = connect_pre_fee;
        }

        public String getConnect_pos_fee() {
            return connect_pos_fee;
        }

        public void setConnect_pos_fee(String connect_pos_fee) {
            this.connect_pos_fee = connect_pos_fee;
        }

        public String getTransfer_fee() {
            return transfer_fee;
        }

        public void setTransfer_fee(String transfer_fee) {
            this.transfer_fee = transfer_fee;
        }

        public String getEnable_pre_paid() {
            return enable_pre_paid == null ? "" : enable_pre_paid;
        }

        public void setEnable_pre_paid(String enable_pre_paid) {
            this.enable_pre_paid = enable_pre_paid;
        }

        public String getEnable_pos_paid() {
            return enable_pos_paid == null ? "" : enable_pos_paid;
        }

        public void setEnable_sim_ctt_nologin(String enable_sim_ctt_nologin) {
            this.enable_sim_ctt_nologin = enable_sim_ctt_nologin;
        }

        public String getEnable_sim_ctt_nologin() {
            return enable_sim_ctt_nologin == null ? "" : enable_sim_ctt_nologin;
        }

        public void setEnable_pos_paid(String enable_pos_paid) {
            this.enable_pos_paid = enable_pos_paid;
        }

        public String getFee_switch_pre() {
            return fee_switch_pre == null ? "" : fee_switch_pre;
        }

        public void setFee_switch_pre(String fee_switch_pre) {
            this.fee_switch_pre = fee_switch_pre;
        }

        public String getFee_switch_pos() {
            return fee_switch_pos == null ? "" : fee_switch_pos;
        }

        public void setFee_switch_pos(String fee_switch_pos) {
            this.fee_switch_pos = fee_switch_pos;
        }

        public String getTotal_fee_title() {
            return total_fee_title == null ? "" : total_fee_title;
        }

        public void setTotal_fee_title(String total_fee_title) {
            this.total_fee_title = total_fee_title;
        }

        public String getCmgs_otp_pattern() {
            return cmgs_otp_pattern;
        }

        public void setCmgs_otp_pattern(String cmgs_otp_pattern) {
            this.cmgs_otp_pattern = cmgs_otp_pattern;
        }

        public String getRequire_package_mnp() {
            return require_package_mnp;
        }

        public void setRequire_package_mnp(String require_package_mnp) {
            this.require_package_mnp = require_package_mnp;
        }

        public String getActive_omi_level4_address() {
            return "1";
        }

        public void setActive_omi_level4_address(String active_omi_level4_address) {
            this.active_omi_level4_address = active_omi_level4_address;
        }

        public String getTiem_ich_not_login() {
            return tiem_ich_not_login;
        }

        public void setTiem_ich_not_login(String tiem_ich_not_login) {
            this.tiem_ich_not_login = tiem_ich_not_login;
        }

        public int getNewBrowser() {
            return newBrowser;
        }

        public void setNewBrowser(int newBrowser) {
            this.newBrowser = newBrowser;
        }

        public String getFtthRegisterOnlineEnable() {
            if (ftthRegisterOnlineEnable == null) {
                ftthRegisterOnlineEnable = "";
            }
            return ftthRegisterOnlineEnable;
        }

        @SerializedName("conf_limit_buy_sim")
        String confLimitBuySim;

        public String getCountdownExpireTimeOtpLogin() {
            return countdownExpireTimeOtpLogin;
        }

        public void setCountdownExpireTimeOtpLogin(String countdownExpireTimeOtpLogin) {
            this.countdownExpireTimeOtpLogin = countdownExpireTimeOtpLogin;
        }

        public String getEnable_register_partner() {
            return enable_register_partner;
        }

        public void setEnable_register_partner(String enable_register_partner) {
            this.enable_register_partner = enable_register_partner;
        }


        public String getVersionAndroid() {
            return versionAndroid;
        }

        public void setVersionAndroid(String versionAndroid) {
            this.versionAndroid = versionAndroid;
        }

        public String getTerms_BHOL() {
            if (terms_BHOL == null) {
                terms_BHOL = "";
            }
            return terms_BHOL;
        }

        public void setTerms_BHOL(String terms_BHOL) {
            this.terms_BHOL = terms_BHOL;
        }

        public String getChange_esim_liveness_detection_actions() {
            return change_esim_liveness_detection_actions == null ? "" : change_esim_liveness_detection_actions;
        }

        public void setChange_esim_liveness_detection_actions(String change_esim_liveness_detection_actions) {
            this.change_esim_liveness_detection_actions = change_esim_liveness_detection_actions;
        }

        public String getKhdnBuySim() {
            if (khdnBuySim == null){
                return "";
            }
            return khdnBuySim;
        }

        public void setKhdnBuySim(String khdnBuySim) {
            this.khdnBuySim = khdnBuySim;
        }

        public String getVideocall_config_sdk() {
            return videocall_config_sdk;
        }

        public void setVideocall_config_sdk(String videocall_config_sdk) {
            this.videocall_config_sdk = videocall_config_sdk;
        }

        public String getVideocall_config_buy_sim() {
            return videocall_config_buy_sim;
        }

        public void setVideocall_config_buy_sim(String videocall_config_buy_sim) {
            this.videocall_config_buy_sim = videocall_config_buy_sim;
        }
    }
}
