package io.github.smaugfm;

import static io.github.smaugfm.Util.waitForSelectorTimeout;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ClearHnrScript {

  public static final String CLEAR_WITH_SURPLUS_BUTTON_SELECTOR = "div[class='clearHARButtons'] > button[class~='clear-whole-surplus']";
  public static final String CLEAR_WITH_SURPLUS_BUTTON_TOTAL_SELECTOR = "div[class='clearHARButtons'] > button[class~='clear-whole-surplus'] span[class~='total_har_surplus']";
  private final Page page;

  public ClearHnrScript(Page page) {
    this.page = page;
  }

  public void clearWithSurplusButton() {
    var button = page.waitForSelector(CLEAR_WITH_SURPLUS_BUTTON_SELECTOR, waitForSelectorTimeout);
    var total = page.querySelector(CLEAR_WITH_SURPLUS_BUTTON_TOTAL_SELECTOR);
    System.out.printf("Clearing HNRs with %s surplus%n", total.textContent());
    button.click();
    page.waitForLoadState(LoadState.NETWORKIDLE);
  }
}
