package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> result = new ArrayList<>();
        List<LocalDate> listDate = new ArrayList<>();
        List<Integer> listCal = new ArrayList<>();
        boolean ex = false;


        for (UserMeal o : mealList) {
            if (listDate.contains(o.getDateTime().toLocalDate())) {
                listCal.set(listDate.indexOf(o.getDateTime().toLocalDate()), o.getCalories() + listCal.get(listDate.indexOf(o.getDateTime().toLocalDate())));
            } else {
                listDate.add(o.getDateTime().toLocalDate());
                listCal.add(o.getCalories());
            }
        }

        for (UserMeal o : mealList) {
            if (TimeUtil.isBetween(o.getDateTime().toLocalTime(), startTime, endTime)) {
                ex = (listCal.get(listDate.indexOf(o.getDateTime().toLocalDate())) > caloriesPerDay) ? true : false;

                result.add(new UserMealWithExceed(o.getDateTime(), o.getDescription(), o.getCalories(), ex));
            }
        }


        for (UserMealWithExceed o : result) {
            System.out.println(o.getExceed());
        }

        //result.add()
        // for (int i = 0; i < mealList.size() ; i++){
        // mealList[i].getCalories
        //  System.out.println(i);
        // }

        return null;
    }
}
