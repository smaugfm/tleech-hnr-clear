package io.github.smaugfm;

import static io.github.smaugfm.Util.waitForSelectorTimeout;

import com.microsoft.playwright.Page;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginScript {

  private static final String USERNAME_SELECTOR = "section[class='login'] input[name='username']";
  private static final String PASSWORD_SELECTOR = "section[class='login'] input[name='password']";
  private static final String SUBMIT_SELECTOR = "section[class='login'] button[type='submit']";
  private static final String URL = "https://www.torrentleech.org";

  private final Page page;
  private final String tleechUser;
  private final String tleechPassword;

  public void login() {
    page.navigate(URL);
    var usernameInput = page.waitForSelector(USERNAME_SELECTOR, waitForSelectorTimeout);
    var passwordInput = page.waitForSelector(PASSWORD_SELECTOR, waitForSelectorTimeout);
    var submitButton = page.waitForSelector(SUBMIT_SELECTOR, waitForSelectorTimeout);
    usernameInput.fill(tleechUser);
    passwordInput.fill(tleechPassword);
    submitButton.click();
    System.out.printf("Logged in to %s as %s%n", URL, tleechUser);
  }
}
