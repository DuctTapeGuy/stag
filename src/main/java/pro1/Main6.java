package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionsList;

import java.util.Comparator;
import java.util.HashMap;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        //zadani:
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID


        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions = new Gson().fromJson(json, ActionsList.class);

        //for(Action a : actions.items)
        HashMap<Long,Integer> hashMap = new HashMap<>(); //hashmap = asociativni pole
        actions.items.stream().forEach(a -> {
            int current = hashMap.getOrDefault(a.teacherId,0);
            hashMap.put(a.teacherId, current +a.personsCount);
            });

        return hashMap.entrySet().stream() //prevod pro stream
                .max(Comparator.comparing(h -> h.getValue())) //hashmapa ma klic a hodnotu - tohle porovnava podle hodnoty (to druhy)
                .get()
                .getKey(); //bere z toho klic
    }
}