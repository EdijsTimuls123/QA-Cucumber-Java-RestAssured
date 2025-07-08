package utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PayloadBuilder {

  public static Map<String, Object> buildDefaultPayload(int page, int pageSize) {
    Map<String, Object> payload = new LinkedHashMap<>();
    payload.put("page", page);
    payload.put("pageSize", pageSize);
    payload.put("sortOption", null);
    payload.put("interestRateFrom", null);
    payload.put("interestRateTo", null);
    payload.put("remainingTermMonthsFrom", null);
    payload.put("remainingTermMonthsTo", null);
    payload.put("availableInvestmentAmountFrom", null);
    payload.put("availableInvestmentAmountTo", null);
    payload.put("countryCodes", Collections.emptyList());
    payload.put("amountFrom", null);
    payload.put("amountTo", null);
    payload.put("filtered", false);
    payload.put("transactionTypes", Collections.emptyList());
    payload.put("bookingDateFrom", "2025-07-01");
    payload.put("bookingDateTo", "2025-07-08");
    return payload;
  }
}
