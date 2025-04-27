package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Deadline {

    @SerializedName("eprDeadlinePrihlaska")
    public DeadlineValue deadlinePrihlaska;

    public static class DeadlineValue { //eprDeadlinePrihlaska ma jako hodnotu dalsi Json
        private String value; //a v tom Jsonu je jen atribut value se String hodnotou

        public String getValue() { //bezpecnost predevsim
            return value;
        }
    }
    public DeadlineValue getDeadlinePrihlaska() {
        return deadlinePrihlaska;
    }
}