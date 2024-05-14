package io.github.smaugfm;

import static io.github.smaugfm.Util.waitForLoadStateTimeout;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HnrCheckScript {

  public static final String HNR_WARNING_SELECTOR = "body > div[class~='harReminder']";
  public static final String HNR_WARNING_TOTAL_SELECTOR = "body > div[class~='harReminder'] span[class~='hitnrunCountTotal']";

  private final Page page;

  public boolean loadHnrIfHasWarning() {
    page.waitForLoadState(LoadState.NETWORKIDLE, waitForLoadStateTimeout);

    var element = page.querySelector(HNR_WARNING_SELECTOR);
    if (element != null) {
      var total = page.querySelector(HNR_WARNING_TOTAL_SELECTOR);
      System.out.printf("Has HNR warning for %s torrents%n", total.textContent());
      element.click();
      return true;
    }
    System.out.println("No HNR warning");
    return false;
  }
}
