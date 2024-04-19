package com.solvd.atm.utils.tests;

import com.solvd.atm.utils.atm.InputHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class InputHandlerTest {

    static Map<String, Boolean> intTest = new HashMap<>();
    static Map<String, Boolean> doubleTest = new HashMap<>();
    static Map<String, Boolean> pinTest = new HashMap<>();
    static Map<String, Boolean> cvcTest = new HashMap<>();
    static Map<String, Boolean> cardExpTest = new HashMap<>();
    static Map<String, Boolean> cardNumberTest = new HashMap<>();
    static Map<String, Boolean> doubleParsableTest = new HashMap<>();
    static ArrayList<String> results = new ArrayList<>();

    static {
        intTest.put("-2147483648", false);
        intTest.put("2147483647", true);
        intTest.put("2147483648", false);
        intTest.put("not a number", false);
        intTest.put(" ", false);

        doubleTest.put("-1.234", false);
        doubleTest.put("3.14", true);
        doubleTest.put("3.342", false);
        doubleTest.put(".222222222222222222222222", false);
        doubleTest.put("1.7976931348623157E308", true); // Largest positive double value
        doubleTest.put("1.7976931348623159E308", false); // Beyond the positive double range
        doubleTest.put("not a number", false);
        doubleTest.put(" ", false);

        pinTest.put("1111", true);
        pinTest.put("12", false);
        pinTest.put("1a12", false);
        pinTest.put("a112", false);
        pinTest.put("112a", false);

        cvcTest.put("31a", false);
        cvcTest.put("3a1", false);
        cvcTest.put("a31", false);
        cvcTest.put("1111", false);
        cvcTest.put("123", true);

        cardExpTest.put("/24", false);
        cardExpTest.put("01/12", false);
        cardExpTest.put("03/24", true);
        cardExpTest.put("2/24", false);
        cardExpTest.put("1a/25", false);

        cardNumberTest.put("1232-1234-1234-1234", true);
        cardNumberTest.put("1232-1234-1234-1a34", false);
        cardNumberTest.put("123-1234-1234-1234", false);
        cardNumberTest.put("1232-123-1234-1234", false);
        cardNumberTest.put("1232-1234-124-1234", false);
        cardNumberTest.put("1232-1234-1234-124", false);
        cardNumberTest.put("1232-1234-1234-12343", false);
        cardNumberTest.put("a232-1234-1234-1234", false);


    }

    private static <K> void testMethod(Predicate<String> testMethod, Map<K, Boolean> mapSet, String testName) {
        for (K input : mapSet.keySet()) {
            boolean expectedResult = mapSet.get(input);
            boolean actualResult = testMethod.test(input.toString());
            results.add(testName + ": input: " + input + ": " + ((actualResult == expectedResult) ? "pass" : "fail"));
        }
    }


    private static void prtResults() {
        for (String test: results){
            System.out.println(test);
        }
    }

    public static void test() {
        // Use a shorter alias for the method
        testMethod(InputHandler::checkCardId, intTest, "checkCardIdInput");
        testMethod(InputHandler::checkAmount, doubleTest, "checkAmountInput");
        testMethod(InputHandler::checkCardPin, pinTest, "checkPinInput");
        testMethod(InputHandler::checkCardCvc, cvcTest, "checkCvcInput");
        testMethod(InputHandler::checkCardExpirationDate, cardExpTest, "checkExpirationDateInput");
        testMethod(InputHandler::checkCardNumber, cardNumberTest, "checkCardNumberInput");
        prtResults();
    }
}
