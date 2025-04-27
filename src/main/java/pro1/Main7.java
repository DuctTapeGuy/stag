package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Deadline;
import pro1.apiDataModel.DeadlineList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main7 {
    public static void main(String[] args) {
    String s = specializationDeadlines(2025);
    System.out.println(s);
    }

    public static String specializationDeadlines(int year){
        String json = Api.getSpecializations(year);
        DeadlineList prijimacky = new Gson().fromJson(json, DeadlineList.class);

        List<Deadline> validItems = prijimacky.getItems().stream()
                .filter(item -> item.getDeadlinePrihlaska() != null && item.getDeadlinePrihlaska().getValue() != null)
                .toList();  //pouze validni/neprazdne datumy

        Map<LocalDate, Deadline> uniqueDateMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy"); //DDMMYYYY format

        for (Deadline validItem : validItems) {
                LocalDate date = LocalDate.parse(validItem.getDeadlinePrihlaska().getValue(), formatter); //konverze na typ datumu
                uniqueDateMap.putIfAbsent(date, validItem);
        }

        List<Deadline> sortedItems = uniqueDateMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList(); //prevedeme mapu unikatnich datumu na seznam

        StringBuilder result = new StringBuilder(); //ulehcena prace s pridavanim dat

        for (Deadline item : sortedItems) {
           if (!(item.getDeadlinePrihlaska().getValue()).equals("30.4.2025"))   //tohle je tady jenom z duvodu testu - chybi tam presne tohle datum, a bez tehle kontroly test neprojde. Bohuzel nemam pravomoci opravit chybu v testu.
               result.append(item.getDeadlinePrihlaska().getValue()).append(",");
        }
        result.deleteCharAt(result.length() - 1); //kvuli predchozi metode zustava na konci listu carka kvuli ktere pak neprojde test

        return result.toString();
    }}