# Run notes

If the run stops at:
===== BEFORE CHROME DRIVER =====

Then the test code did not start yet. ChromeDriver is not opening.

Fix:
1. Open Chrome and check your version from chrome://settings/help
2. Download matching chromedriver for Windows x64.
3. Put chromedriver.exe here:
   mahara-tech/drivers/chromedriver.exe
4. Close all chrome.exe and chromedriver.exe from Task Manager.
5. Run testng.xml again.

Run:
mvn clean test

Or from IntelliJ: right click testng.xml > Run.
