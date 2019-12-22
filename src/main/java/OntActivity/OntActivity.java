package OntActivity;


import MemoryWorld.*;

import java.util.ArrayList;
import java.util.List;

public class OntActivity {
    public static List<ConsumeActivity> bornData() {

        List<ConsumeActivity> activities = new ArrayList<ConsumeActivity>();
        //People , Movement , Currency , Location , Time , MemoryObject
        for (int i = 0; i < 2; i++) {
            People people = new People("Anod " +   i +   (5 + i) + "th", People.GENDER.FEMALE, 100 + i * 10, 120 + i);

            for (int j = 2; j < 4; j++) {
                Movement movement;
                if (j % 3 == 0)
                    movement = new Movement("Spent");
                else
                    movement = new Movement("cost" +   j);
                for (int k = 4; k < 7; k++) {
                    Currency currency;
                    if (k % 5 == 0)
                        currency = new Currency((float) (k * 1.08), Currency.CurrencyType.YUAN);
                    else
                        currency = new Currency(1, Currency.CurrencyType.DOLLAR);
                    for (int l = 4; l < 6; l++) {
                        Location location;

                            location = new Location("北京");

                        for (int m = 0; m < 12; m++) {
                            Time time = new Time(String.valueOf(m));
                            MemoryObject memoryObject = new MemoryObject();

                            ConsumeActivity activity = new ConsumeActivity(people, movement, currency, location, time, memoryObject);
                            activities.add(activity);
                        }
                    }
                }
            }
        }
        return activities;
    }

}
