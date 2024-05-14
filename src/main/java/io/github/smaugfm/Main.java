package io.github.smaugfm;

import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;
import java.util.Objects;
import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
  public static void main(String[] args) {
    var tleechUser = Objects.requireNonNull(System.getenv().get("TLEECH_USER"));
    var tleechPassword = Objects.requireNonNull(System.getenv().get("TLEECH_PASSWORD"));

    try (var playwright = Playwright.create()) {
      try (var browser = playwright.chromium().launch(new LaunchOptions().setHeadless(true))) {
        var context = browser.newContext();
        var page = context.newPage();
        var loginScript = new LoginScript(page, tleechUser, tleechPassword);
        loginScript.login();
        var hnrCheckScript = new HnrCheckScript(page);
        if (hnrCheckScript.loadHnrIfHasWarning()) {
          var clearHnrScript = new ClearHnrScript(page);
          clearHnrScript.clearWithSurplusButton();
        }
      }
    }
  }
}
