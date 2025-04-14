package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {

        String json = Api.getActionsByDepartment(department,year);
        String json2 = Api.getTeachersByDepartment(department); //v akcich neni uvedenej email ucitele a timhle to resim
        ActionsList actions = new Gson().fromJson(json, ActionsList.class);
        TeachersList teachers = new Gson().fromJson(json2, TeachersList.class);


        return teachers.items.stream()
                .max(Comparator.comparing(t -> TeacherScore(t.id, actions))) //porovname ucitele podle score (viz dole)
                .get().email; //vezmem email toho co vyslo
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        return departmentSchedule.items.stream()
                .filter(a -> a.teacherId == teacherId) //vsechny akce od ucitele
                .mapToLong(a -> a.personsCount).sum(); //prevedeno na seznam + secteno
    }
}