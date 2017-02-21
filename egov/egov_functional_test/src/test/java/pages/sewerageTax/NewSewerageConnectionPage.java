package pages.sewerageTax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by karthik on 27/1/17.
 */
public class NewSewerageConnectionPage extends BasePage {

    private WebDriver driver;

    @FindBy(css = "input[id='propertyIdentifier'][type='text']")
    private WebElement PTAssessmentNumberTextBox;

    @FindBy(id = "propertyType")
    private WebElement propertyTypeDropBox;

    @FindBy(css = "input[id='noOfClosetsResidential'][type='text']")
    private WebElement noOfClosetsTextBox;

    @FindBy(css = "input[id='appDetailsDocument0documentNumber'][type='text']")
    private WebElement documentNumberTextBox;

    @FindBy(css = "input[id='appDetailsDocument0documentDate'][type='text']")
    private WebElement documentDateTextBox;

    @FindBy(css = "input[id='file0id'][type='file']")
    private WebElement chooseFileButton;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(xpath = ".//*[@id='sewarageConnectionSuccess']/div/div/div/span[1]")
    private WebElement applicationNumberText;

    @FindBy(xpath = ".//*[@id='sewarageConnectionSuccess']/div/div/div/span[2]")
    private WebElement successMessageForSewerageConnectionText;

    @FindBy(xpath = ".//*[@id='sewarageConnectionSuccess']/div/div/div/span")
    private WebElement successMessageForSewerageConnectionText1;

    @FindBy(xpath = ".//*[@id='sewarageChangeClosetsSuccess']/div/div/div/span[1]")
    private WebElement applicationNumberTextForChange;

    @FindBy(xpath = ".//*[@id='sewarageChangeClosetsSuccess']/div/div/div/span[2]")
    private WebElement getSuccessMessageForChangeSewerageConnectionText;

    @FindBy(xpath = ".//*[@id='sewarageChangeClosetsSuccess']/div/div/div/span")
    private WebElement successMessageForChangeSewerageConnectionText1;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(css = "input[id='consumerNumber'][type='text']")
    private WebElement applicationNumberTextBox;

    @FindBy(id = "searchSewerageapplication")
    private WebElement searchButton;

    @FindBy(id = "aplicationSearchResults")
    private WebElement searchResultsTable;

    @FindBy(id = "totalamounttobepaid")
    private WebElement amountToBePaidText;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountToBePaidTextBox;

    @FindBy(css = "input[value='Pay'][type='submit']")
    private WebElement payButton;

    @FindBy(css = "input[value='Close'][type='button']")
    private WebElement closeButton;

    @FindBy(id = "approvalComent")
    private WebElement approverCommentTextBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(id = "Generate Estimation Notice")
    private WebElement generateEstimationNoticeButton;

    @FindBy(css = "input[id='inboxsearch'][type='text']")
    private WebElement inboxSearchTextBox;

    @FindBy(linkText = "Generate Work Order")
    private WebElement generateWorkOrderLink;

    @FindBy(id = "Execute Connection")
    private WebElement executeConnectionButton;

    @FindBy(id = "closeConnectionReason")
    private WebElement closeConnectionRemarksTextBox;

    @FindBy(xpath = ".//*[@id='sewarageCloseConnectionSuccess']/div/div/div/span[1]")
    private WebElement getApplicationNumberTextForClosure;

    @FindBy(xpath = ".//*[@id='sewarageCloseConnectionSuccess']/div/div/div/span[2]")
    private WebElement getSuccessMessageForSewerageConnectionClosure;

    @FindBy(id = "Generate Close Connection Notice")
    private WebElement generateClosureNoticeButton;

    @FindBy(css = "input[id='shscNumber'][type='text']")
    private WebElement hscNumberTextBox;

    @FindBy(css = "input[id='executionDate'][type='text']")
    private WebElement executionDateTextBox;

    @FindBy(css = "input[id='demandDetailBeanList0actualAmount'][type='text']")
    private WebElement demandTextBox;

    @FindBy(css = "input[id='demandDetailBeanList0actualCollection'][type='text']")
    private WebElement collectionTextBox;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public NewSewerageConnectionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewConnection(String assessmentNumber) {
        enterText(PTAssessmentNumberTextBox,assessmentNumber,driver);
        selectFromDropDown(propertyTypeDropBox,"RESIDENTIAL",driver);
        enterText(noOfClosetsTextBox,"3",driver);
        enterText(documentNumberTextBox,"123",driver);
        enterDate(documentDateTextBox,getCurrentDate(),driver);
        uploadFile(chooseFileButton,System.getProperty("user.dir") + "/src/test/resources/logo.jpg",driver);
    }

    public void forward() {
          clickOnButton(forwardButton,driver);
    }

    public String getSuccessMessage() {
        return getTextFromWeb(successMessageForSewerageConnectionText,driver);
    }

    public String getSuccessMessageForChangeSewerage() {
          return  getTextFromWeb(getSuccessMessageForChangeSewerageConnectionText,driver);
    }

    public String getSuccessMessage1() {
        return getTextFromWeb(successMessageForSewerageConnectionText1,driver);
    }

    public String getApplicationNumberForLegacyCreation(){
        String num1 = successMessageForSewerageConnectionText1.getText().split("\\ ")[5].substring(1,14);
        return num1;
    }

    public String getSuccessMessage1ForChangeSewerage() {
        return getTextFromWeb(successMessageForChangeSewerageConnectionText1,driver);
    }

    public String getApplicatioNumber() {
        waitForElementToBeVisible(applicationNumberText,driver);
        String num1 = applicationNumberText.getText().split("\\ ")[5].substring(1,14);
        System.out.println("\n "+num1);
        return num1;
    }

    public String getApplicatioNumberForChangeSewerage() {
        waitForElementToBeVisible(applicationNumberTextForChange,driver);
        String num1 = applicationNumberTextForChange.getText().split("\\ ")[5].substring(1,14);
        System.out.println("\n "+num1);
        return num1;
    }

    public void close() {
      clickOnButton(closeLink,driver);
      switchToPreviouslyOpenedWindow(driver);
    }

    public void searchForApplicationNumberToCollect(String number) {
        enterText(applicationNumberTextBox,number,driver);
        clickOnButton(searchButton,driver);
        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).findElement(By.className("actiondropdown"));
        selectFromDropDown(dropDownAction,"Collect Fee",driver);
        switchToNewlyOpenedWindow(driver);
    }

    public void collectCharges() {
        waitForElementToBeVisible(amountToBePaidText,driver);
        String amount = amountToBePaidText.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];
        enterText(amountToBePaidTextBox,actualAmount,driver);
        jsClick(payButton,driver);
     }

    public void closeMultipleWindows(String s) {
        clickOnButton(closeButton,driver);

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(getEnvironmentURL()+s)){
                break;
            }
        }

        close();
    }

    public void approveTheApplication() {;
        enterText(approverCommentTextBox,"Approved",driver);
        clickOnButton(approveButton,driver);
    }

    public void generateEstimationNotice() {;
          enterText(approverCommentTextBox,"Generated estimate notice",driver);
          clickOnButton(generateEstimationNoticeButton,driver);
          await().atMost(3, SECONDS);
          driver.close();
          switchToPreviouslyOpenedWindow(driver);
    }

    public void generateWorkOrder(String num) {
        clickOnButton(generateWorkOrderLink,driver);
        switchToNewlyOpenedWindow(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.close();
        for (String winHandle : driver.getWindowHandles()) {
                if (driver.switchTo().window(winHandle).getCurrentUrl().equals(getEnvironmentURL()+"/stms/transactions/update/" + num)) {
                    break;
                }
            }
        }

    public void executeConnection() {
          clickOnButton(executeConnectionButton,driver);
    }

    public void searchForAboveSewerageConnection(String number,String action) {
        enterDate(applicationNumberTextBox,number,driver);
        clickOnButton(searchButton,driver);
        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("a"));
        String hscNumber = dropDownAction.getText();
        driver.navigate().to(getEnvironmentURL()+"/stms/transactions/" + action + "/" + hscNumber);
    }

    public void increseTheNumberOfClosets() {
        enterText(noOfClosetsTextBox,"5",driver);
        enterText(documentNumberTextBox,"123",driver);
        enterDate(documentDateTextBox,getCurrentDate(),driver);;
        uploadFile(chooseFileButton,System.getProperty("user.dir") + "/src/test/resources/logo.jpg",driver);
    }

    public void remarks() {
          enterText(closeConnectionRemarksTextBox,"Testing...",driver);
    }

    public String getApplicatioNumberForClosure() {
        waitForElementToBeVisible(getApplicationNumberTextForClosure,driver);
        String num1 = getApplicationNumberTextForClosure.getText().split("\\ ")[4].substring(1,14);
        System.out.println("\n "+num1);
        return num1;
    }

    public String getSuccessMessageForClosure() {
        return getTextFromWeb(getSuccessMessageForSewerageConnectionClosure,driver);
    }

    public void generateClosureNotice() {
        clickOnButton(generateClosureNoticeButton,driver);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        driver.close();
        switchToPreviouslyOpenedWindow(driver);
    }

    public void enterDetailsForLegacySewerageConnection(String assessmentNumber) {
        enterText(PTAssessmentNumberTextBox,assessmentNumber,driver);
        enterText(hscNumberTextBox,"1016"+get6DigitRandomInt(),driver);
        enterText(executionDateTextBox,getPreviousDate(),driver);
        enterText(demandTextBox,"1000",driver);
        enterText(collectionTextBox,"0",driver);
        selectFromDropDown(propertyTypeDropBox,"RESIDENTIAL",driver);
        enterText(noOfClosetsTextBox,"3",driver);
    }

    public void submit() {
        clickOnButton(submitButton,driver);
    }

    public void searchAndGenerateDemandBill(String number) {
        enterText(applicationNumberTextBox,number,driver);
        clickOnButton(searchButton,driver);
        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("a"));
        String hscNumber = dropDownAction.getText();
        driver.navigate().to(getEnvironmentURL()+"/stms/reports/generate-sewerage-demand-bill/" + number + "/" + hscNumber);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        driver.close();
        switchToPreviouslyOpenedWindow(driver);
    }
}
