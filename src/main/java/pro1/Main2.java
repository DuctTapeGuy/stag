package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionsList;

import java.util.Comparator;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(maxPersonsCount("KIKM",2024));
    }

    public static long maxPersonsCount(String department, int year)
    {
        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions = new Gson().fromJson(json, ActionsList.class);

        //return actions.items.stream().sorted(maxPersonsCount("KIKM",2024)).max();
       // return actions.items.stream()
       //         .mapToLong(a -> a.personsCount) //prevedu seznam veci na seznam cisel
       //         .max().getAsLong(); // a v nem hledam maximum; pokud je prazdny pole (nevyjde long) tak vyjimka
        Action result = actions.items.stream()
                .max(Comparator.comparing(a -> a.personsCount)).get(); //alternativa - seradime podle nejvyssi ucasti
        return result.personsCount;
    }
}