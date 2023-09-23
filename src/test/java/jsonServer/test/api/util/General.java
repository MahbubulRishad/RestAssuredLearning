package jsonServer.test.api.util;

import jsonServer.test.api.test.BaseTestApi;

import java.util.Random;

public class General extends BaseTestApi {
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
