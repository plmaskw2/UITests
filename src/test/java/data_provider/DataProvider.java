package data_provider;

import com.github.javafaker.Faker;

public class DataProvider {

    public static String getMessageContent() {
        return new Faker().friends().quote();
    }
}
