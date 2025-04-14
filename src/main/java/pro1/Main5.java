package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;

import java.util.stream.Collectors;

public class Main5 {

    public static void main(String[] args) {
        System.out.println(roomsSummary("KIKM",2024));
    }

    public static String roomsSummary(String department, int year)
    {
        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions = new Gson().fromJson(json, ActionsList.class); //stahujem data o katedre, konverze do gsonu

        return actions.items.stream()
                .filter(a -> a.room != null) //odebere ucebny co nemaj zapsanej nazev
                .map(a -> a.room) //vezmem vsechny mistnosti
                .distinct() //odeberem duplikaty
                .sorted() //seradi abecedne, bere 10 pred 2
                .collect(Collectors.joining(",")); //oddelujem carkou

        // zadani: Vrať výpis učeben, které katedra v daném roce využila (seřadit abecedně, oddělit čárkou)... jenze prvne J11 a az pak J2
    }
}