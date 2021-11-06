package com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.MVShopResultActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package.MobilePackageFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.model.InfoCustomer;
import com.ttc.demo.basemyviettel.ui.main.detail.model.MobilePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ServicePackage;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStateDataLitener;
import com.ttc.demo.basemyviettel.utils.Constants;
import com.ttc.demo.basemyviettel.utils.NumberUtils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class PaymentFragment extends ViewFragment<PaymentContract.Presenter> implements PaymentContract.View {

    @BindView(R.id.ll_name)
    View llName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.layout_birthday)
    View llBirthDay;
    @BindView(R.id.tv_birthday)
    TextView tvBirthDay;
    @BindView(R.id.layout_contact_phone)
    View llPhone;
    @BindView(R.id.tv_contact_phone)
    TextView tvPhone;
    @BindView(R.id.layout_cmt)
    View llCmt;
    @BindView(R.id.tv_cmt)
    TextView tvCmt;
    @BindView(R.id.ll_date_cmt)
    View llNgayCap;
    @BindView(R.id.tv_cmt_place)
    TextView tvNgayCap;
    @BindView(R.id.ll_address)
    View llAdress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_info_sim)
    View llInfoSim;
    @BindView(R.id.tv_info_sim)
    TextView tvInfoSim;
    @BindView(R.id.tv_price_sim)
    TextView tvPriceSim;
    @BindView(R.id.tv_sim)
    TextView tvSim;
    @BindView(R.id.ll_money_card)
    View llMoneyCard;
    @BindView(R.id.tv_money_card)
    TextView tvMoneyCardTitel;
    @BindView(R.id.tv_total_money_card)
    TextView tvTotalMoneyCard;
    @BindView(R.id.ll_mobile_pk)
    View llPackage;
    @BindView(R.id.tv_mobile_pk)
    TextView tvTitlePackage;
    @BindView(R.id.tv_price_mobile_vas_pk)
    TextView tvTotalPricePackage;
    @BindView(R.id.tv_name_mobile_pk)
    TextView tvNameMobilePk;
    @BindView(R.id.tv_vas_pk)
    TextView tvVasPk;
    @BindView(R.id.ll_shipment)
    View llTitleShipment;
    @BindView(R.id.tv_price_shipment)
    TextView tvPriceShipment;
    @BindView(R.id.order_number_total_price_tv)
    TextView tvOrderTotalPrice;
    @BindView(R.id.accept_condition_iv)
    View checkboxAccept;
    @BindView(R.id.tv_dieu_khoan_mh)
    TextView tvTermOfPurchase;
    @BindView(R.id.tv_request)
    Button btnRequest;
    @BindView(R.id.edit_customer_infor_img)
    ImageView editCustomer;
    @BindView(R.id.edit_giay_to_img)
    ImageView editPaper;
    @BindView(R.id.edit_shipping_img)
    ImageView editShip;
    @BindView(R.id.edit_shipping_img_1)
    ImageView editShip1;

    private PaymentContract.Presenter presenter;
    private boolean checkbox = false;

    public static PaymentFragment getInstance(){
        return new PaymentFragment();
    }
    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_payment;
    }

    @Override
    public
    void initLayout() {
        super.initLayout();
        ButterKnife.bind(getActivity());
        presenter = new PaymentPresenter(this);
        checkOrder();
        listener();
    }

    private void checkOrder(){
        long totalOrder = 0;
        InfoCustomer info = (InfoCustomer) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_INFO_CUSTOMER);
        NumberModel number = (NumberModel) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_NUMBER);
        Constants.ORDER_TYPE orderType = (Constants.ORDER_TYPE) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_NUMBER_TYPE);
        MobilePackage mobile = (MobilePackage) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_PACKAGE_MOBILE);
        ServicePackage vas = (ServicePackage) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_PACKAGE_VAS);
        String moneyCard = (String) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_PACKAGE_MONEY_CARD);
        if (info == null){
            tvName.setText("");
            tvBirthDay.setText("");
            tvPhone.setText("");
            tvCmt.setText("");
            tvNgayCap.setText("");
            tvAddress.setText("");
            editCustomer.setVisibility(View.VISIBLE);
            editPaper.setVisibility(View.VISIBLE);
        }else if(info != null){
            tvName.setText(info.getName());
            tvBirthDay.setText(info.getBirthDay());
            tvPhone.setText(info.getPhone());
            tvCmt.setText(info.getInfoPapers());
            tvNgayCap.setText(info.getNgayCap());
            tvAddress.setText(info.getAddressDetail());
            editCustomer.setVisibility(View.GONE);
            editPaper.setVisibility(View.GONE);
        }
        if (number == null || mobile == null){
            editShip.setVisibility(View.VISIBLE);
        }else {
            editShip.setVisibility(View.GONE);
        }
        if (number == null){
            tvSim.setText("0(null)");
        }else{
            tvSim.setText(NumberUtils.convertVietNamPhoneNumber(number.getIsdn()));
        }
        if (orderType == null){
            tvInfoSim.setText("Hòa mạng trả trước");
            tvPriceSim.setText("0đ");
        }
        else if(orderType == Constants.ORDER_TYPE.PRE && number != null){
            tvInfoSim.setText("Hòa mạng trả trước");
            tvPriceSim.setText(NumberUtils.formatPriceNumber(Integer.valueOf(number.getPrePrice()))+" đ");
            totalOrder += Long.valueOf(number.getPrePrice());
        }
        else if (orderType == Constants.ORDER_TYPE.POS && number != null){
            tvInfoSim.setText("Hòa mạng trả sau");
            tvPriceSim.setText(NumberUtils.formatPriceNumber(Integer.valueOf(number.getPosPrice()))+" đ");
            totalOrder += Long.valueOf(number.getPosPrice());
        }
        if(moneyCard == null){
            tvTotalMoneyCard.setText("0đ");
        }
        else {
            int money = Integer.parseInt(moneyCard);
            tvTotalMoneyCard.setText(NumberUtils.formatPriceNumber(money * MobilePackageFragment.VALUE_MONEY)+" đ");
            totalOrder += money * MobilePackageFragment.VALUE_MONEY;
        }
        if (mobile == null){
            llMoneyCard.setVisibility(View.GONE);
            tvTotalPricePackage.setText("0đ");
            tvNameMobilePk.setVisibility(View.GONE);
            tvVasPk.setVisibility(View.GONE);
        }else if(mobile != null) {
            llMoneyCard.setVisibility(View.VISIBLE);
            tvNameMobilePk.setVisibility(View.VISIBLE);
            tvNameMobilePk.setText(mobile.getShort_name());
            int total = 0;
            if(vas != null){
                tvVasPk.setVisibility(View.VISIBLE);
                tvVasPk.setText(vas.getShort_name());
                total = mobile.getPrice() + vas.getPrice();
            }else if(vas == null){
                total = mobile.getPrice();
            }
            totalOrder += total;
            tvTotalPricePackage.setText(NumberUtils.formatPriceNumber(total));
        }
        tvOrderTotalPrice.setText(NumberUtils.formatPriceNumber(totalOrder)+" đ");
        setCheckbox(checkbox);
        btnRequest.setEnabled(false);
        btnRequest.setBackgroundResource(R.drawable.bg_button_disable);
        if(info != null && number != null && orderType != null && mobile != null && vas != null  && checkbox == true){
            btnRequest.setEnabled(true);
            btnRequest.setBackgroundResource(R.drawable.bg_button);
        }
    }

    private void setCheckbox(boolean checkbox){
        if(checkbox == true){
            checkboxAccept.setBackgroundResource(R.drawable.ic_radio_button_active);
        }else
            checkboxAccept.setBackgroundResource(R.drawable.ic_radio_button_default);
    }

    private void listener(){
        checkboxAccept.setOnClickListener(v -> {
            if (checkbox == true){
                checkbox = false;
                setCheckbox(checkbox);
            }
            else if(checkbox == false){
                checkbox = true;
                setCheckbox(checkbox);
            }
        });
        btnRequest.setOnClickListener(v -> {
            // request order
        });
    }

    @Override
    public
    void setDataReload(NumberModel numberModel) {
    }


}
