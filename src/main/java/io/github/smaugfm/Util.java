package io.github.smaugfm;

import com.microsoft.playwright.Page.WaitForLoadStateOptions;
import com.microsoft.playwright.Page.WaitForSelectorOptions;

public class Util {

  public static final WaitForSelectorOptions waitForSelectorTimeout =
      new WaitForSelectorOptions().setTimeout(20_000);

  public static final WaitForLoadStateOptions waitForLoadStateTimeout =
      new WaitForLoadStateOptions().setTimeout(20_000);

}
