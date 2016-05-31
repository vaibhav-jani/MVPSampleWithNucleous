package ws.entity;

import com.example.vaibhav.mvpsample.bean.SampleBean;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vaibhav on 30/5/16.
 */
public class SampleResponse extends BaseResponse {

    @SerializedName("value")
    private ArrayList<SampleBean> items;

    public ArrayList<SampleBean> getItems() {

        return items;
    }

    public void setItems(ArrayList<SampleBean> items) {

        this.items = items;
    }
}
