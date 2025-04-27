package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DeadlineList {

    @SerializedName("prijimaciObor")
    private List<Deadline> items;

    public List<Deadline> getItems() {
        return items;
    }
}