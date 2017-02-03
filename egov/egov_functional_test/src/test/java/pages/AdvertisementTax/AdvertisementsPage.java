package pages.AdvertisementTax;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
//import org.openqa.selenium.common.touch_actions;

/**
 * Created by karthik on 11/1/17.
 */
public class AdvertisementsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "category")
    private WebElement categoryBox;

    @FindBy(id = "subCategory")
    private WebElement subCategoryBox;

    @FindBy(id = "rateClass")
    private WebElement classBox;

    @FindBy(id = "revenueInspector")
    private WebElement revenueInspectorBox;

    @FindBy(css = "input[id='advertisement.type2'][type='radio']")
    private WebElement structureTypeRadioButton;

    @FindBy(id = "propertyType")
    private WebElement propertyTypeBox;

    @FindBy(css = "input[id='applicationDate'][type='text']")
    private WebElement applicationDateBox;

    @FindBy(css = "input[id='agencyTypeAhead'][type='text']")
    private WebElement agencyTextBox;

    @FindBy(css = "input[id='permissionstartdate'][type='text']")
    private WebElement permissionStartDateBox;

    @FindBy(css = "input[id='permissionenddate'][type='text']")
    private WebElement permissionEndDateBox;

    @FindBy(id = "advertisementParticular")
    private WebElement adParticularTextBox;

    @FindBy(id = "advertisementDuration")
    private WebElement advertismentDurationBox;

    @FindBy(id = "locality")
    private WebElement localityBox;

    @FindBy(id = "address")
    private WebElement addressTextBox;

    @FindBy(css = "input[id='measurement'][type='text']")
    private WebElement measurementTextBox;

    @FindBy(id = "unitOfMeasure")
    private WebElement measurementTypeBox;

    @FindBy(css = "input[id='taxAmount'][type='text']")
    private WebElement taxAmountTextBox;

    @FindBy(id = "ownerDetail")
    private WebElement ownerDetailsTextBox;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(xpath = ".//*[@id='advertisementSuccessform']/div/div/div")
    private WebElement creationMsg;

    @FindBy(xpath = ".//*[@id='statusinactivesuccess']/div/div/div")
    private WebElement creationMsgForDeactivation;

    @FindBy(linkText = "Close")
     private WebElement closeLink;

    @FindBy(css = "li[role='presentation'] a[data-now^='Advertisement']")
    private WebElement advertisementLink;

    @FindBy(id = "official_inbox_wrapper")
    private WebElement workListTable;

    @FindBy(id = "approvalComent")
    private WebElement commentBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(css = "input[id='hoardingnumber'][type='text']")
    private WebElement hoardingNumberTextBox;

    @FindBy(id = "search")
    private WebElement submitButton;

    @FindBy(css = "input[value= 'Advertisement'][type= 'radio']")
    private WebElement searchType;

    @FindBy(id = "hoardingnumber")
    private WebElement advertisementNumber;

    @FindBy(id = "search")
    private WebElement searchAdvertisementButton;

    @FindBy(xpath = "//*[@id='adtax_search']/tbody/tr[1]/td[7]/button")
    private WebElement collectButton;

    @FindBy(xpath = "//*[@id='adtax_search']/tbody/tr[4]/td[5]/button")
    private WebElement agencyWisecollectButton;

    @FindBy(css = "input[id='totalamounttobepaid'][type='text']")
     private WebElement totalamounttobepaid;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
     private WebElement amount;

    @FindBy(css = "input[type='submit'][value ='Pay']")
     private WebElement payButton;

    @FindBy(id = "agencyWiseCollectionListSelected[0]")
     private WebElement selectAdvertisement;

    @FindBy(css = "input[type ='button'][value='Close']")
     private WebElement closeButton;

    @FindBy(css = "input[type='text'][id='agencycode']")
    private WebElement agencyCodeTextBox;

    @FindBy(css = "input[type='text'][id='agencyname']")
    private WebElement agencyNameTextBox;

    @FindBy(css = "input[type='text'][id='depositAmount']")
    private WebElement depositAmountTextBox;

    @FindBy(css = "input[type='text'][id='mobilenumber']")
    private WebElement mobileNumberTextBox;

    @FindBy(id = "status_dropdown")
    private WebElement statusDropDownBox;

    @FindBy(xpath = ".//*[@id='agencyform']/div[2]/div/button[1]")
    private WebElement submitAgencyDetailsButton;

    @FindBy(xpath = ".//*[@id='agencysuccess']/div[1]")
    private WebElement agencyCreationMessage;

    @FindBy(xpath = ".//*[@id='agencysuccess']/div[3]/div/button[2]")
    private WebElement closeAgencyCreation;

    @FindBy(xpath = ".//*[@id='agencysuccess']/div[2]/div/button")
    private WebElement closeAgencySearch;

    @FindBy(id = "agencies")
    private WebElement searchAgencyBox;

    @FindBy(xpath = ".//*[@id='agencysearch']/div[2]/div/button[2]")
    private WebElement viewButton;

    @FindBy(xpath = ".//*[@id='adtax_search']/tbody/tr/td[5]/button")
    private WebElement collectAdvertisementTaxButton;

    @FindBy(css = "input[id='selectAll'][type='checkbox']")
    private WebElement selectAllCheckBox;

    @FindBy(xpath = ".//*[@id='agencysearch']")
     private WebElement collectFeeButton;

    @FindBy(id = "searchrecord")
     private WebElement submitButtonForDeactive;

    @FindBy(xpath = "//*[@id='adtax_searchrecord']/tbody/tr[1]/td[8]/button")
     private WebElement deactivateButton;

    @FindBy(id = "deactivation_remarks")
     private WebElement deactivationRemarks;

    @FindBy(id = "deactiveDate")
     private WebElement deactiveDate;

    @FindBy(id = "deactivation")
     private WebElement deactivateSubmitButton;

    @FindBy(xpath = ".//*[@id='statusinactivesuccess']/div/div[2]/div/button")
     private WebElement closeButtonOfDeactivateSuccessPage;

    @FindBy(css = "input[id='cashradiobutton'][type='radio']")
     private WebElement cashRadioButton;

    @FindBy(css = "input[id = 'chequeradiobutton'][type='radio']")
     private WebElement chequeRadioButton;

    String min = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
    String min1 = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));

    public AdvertisementsPage(WebDriver driver){
       this.driver = driver;
    }


    public void enterAdvertisementDetails() {
        waitForElementToBeVisible(categoryBox,driver);
        new Select(categoryBox).selectByVisibleText("Hoardings");

        waitForElementToBeClickable(subCategoryBox,driver);
        new Select(subCategoryBox).selectByVisibleText("Foot over Bridges");

        waitForElementToBeClickable(classBox,driver);
        new Select(classBox).selectByVisibleText("A");

        waitForElementToBeClickable(revenueInspectorBox,driver);
        new Select(revenueInspectorBox).selectByVisibleText("N Rajesh");

        waitForElementToBeClickable(structureTypeRadioButton,driver);
        structureTypeRadioButton.click();

        waitForElementToBeClickable(propertyTypeBox,driver);
        new Select(propertyTypeBox).selectByVisibleText("GOVERNMENT");
    }

    public void enterPermissionDetails() {
         waitForElementToBeClickable(applicationDateBox,driver);
         applicationDateBox.sendKeys(getCurrentDate(), Keys.TAB);

        waitForElementToBeClickable(adParticularTextBox,driver);
         adParticularTextBox.sendKeys("For elections");

        waitForElementToBeClickable(ownerDetailsTextBox,driver);
        ownerDetailsTextBox.sendKeys("Bhartiya janatha party");

        waitForElementToBeClickable(permissionStartDateBox,driver);
        permissionStartDateBox.sendKeys(getFutureDate(7), Keys.TAB);

        waitForElementToBeClickable(permissionEndDateBox,driver);
        permissionEndDateBox.sendKeys(getFutureDate(37), Keys.TAB);

        waitForElementToBeClickable(advertismentDurationBox,driver);
        new Select(advertismentDurationBox).selectByVisibleText("MONTH");
    }

    public void enterAgencyDetailsForCreationAdvertisement(String agencyName) {
        waitForElementToBeClickable(agencyTextBox, driver);
        agencyTextBox.sendKeys(agencyName);
        waitForElementToBeVisible(driver.findElement(By.className("tt-dropdown-menu")), driver);
        WebElement dropdown = driver.findElement(By.className("tt-dropdown-menu"));
        dropdown.click();
    }

    public void enterLocalityDetails() {
        waitForElementToBeClickable(localityBox,driver);
        new Select(localityBox).selectByVisibleText("Avanthi Nagar");

        waitForElementToBeClickable(addressTextBox,driver);
        addressTextBox.sendKeys("footover Bridge, mainroad, Avanthi Nagar");
    }


    public void enterStructureDetails() {
        waitForElementToBeClickable(measurementTextBox, driver);
        measurementTextBox.sendKeys("20");

        waitForElementToBeClickable(measurementTypeBox, driver);
        new Select(measurementTypeBox).selectByVisibleText("SQ.FT");

      for(int i=0;i<4;i++){
          try {
              waitForElementToBeClickable(taxAmountTextBox, driver);
              taxAmountTextBox.clear();
              taxAmountTextBox.sendKeys("10");
          }catch (StaleElementReferenceException e){
              WebElement element = driver.findElement(By.cssSelector("input[id='taxAmount'][type='text']"));
              element.clear();
              element.sendKeys("10");
          }
      }
    }


    public String forward() {
        waitForElementToBeClickable(forwardButton,driver);
        forwardButton.click();

        String Msg = creationMsg.getText();
        String applicationNumber = Msg.substring(Msg.lastIndexOf(" ")+1);
        System.out.println(applicationNumber);

        return applicationNumber;
    }


    public String successMessage() {

        String msg = creationMsg.getText();
        return msg;
    }

    public void close() {
     waitForElementToBeVisible(closeLink,driver);
     closeLink.click();

     switchToPreviouslyOpenedWindow(driver);
    }


    public void selectAdvertisementTag(String applicationNumber) {

        await().atMost(25, SECONDS).until(() -> workListTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 0);
        List<WebElement> totalRows = workListTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("\n"+totalRows.size());
        for (WebElement applicationRow : totalRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber))
                applicationRow.click();
            break;
        }

        switchToNewlyOpenedWindow(driver);
    }


    public void approverComment() {
        waitForElementToBeClickable(commentBox,driver);
        commentBox.sendKeys("Approved");
        //commentBox.sendKeys(Keys.TAB, Keys.ENTER);
    }

    public void approve() {

        waitForElementToBeVisible(approveButton,driver);
        waitForElementToBeClickable(approveButton,driver);
        approveButton.click();

        Boolean isPresent = driver.findElements(By.id("Approve")).size() > 0;

        if(isPresent){
            approveButton.click();
            System.out.println("success");
        }
    }
    public String getAdvertisementNumber() {
        waitForElementToBeVisible(creationMsg,driver);
        String number = creationMsg.getText();

        String applicationNumber = (number.split("\\ "))[8];
        System.out.println(applicationNumber);

        return applicationNumber;
    }

    public void searchAndSelect(String number) {
      waitForElementToBeVisible(hoardingNumberTextBox,driver);
      hoardingNumberTextBox.sendKeys(number);

     waitForElementToBeClickable(submitButton,driver);
     submitButton.click();

       WebElement dropDownBox = driver.findElement(By.id("adtaxdropdown"));
       waitForElementToBeVisible(dropDownBox,driver);
       new Select(dropDownBox).selectByVisibleText("View");

       switchToNewlyOpenedWindow(driver);
    }

    public void closeMultipleWindows(String url) {

        waitForElementToBeVisible(closeLink,driver);
        closeLink.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(url)){
                break;
            }
        }

        close();
    }

    public void closeMultiple(String url){

        waitForElementToBeClickable(closeButton, driver);
        closeButton.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(url)){
                break;
            }
        }

        close();
    }

    public void searchByAdvertisementNumber(String adervtisementNumber) {
        waitForElementToBeClickable(searchType, driver);
        searchType.click();
        waitForElementToBeClickable(advertisementNumber, driver);
        advertisementNumber.sendKeys(adervtisementNumber);
        waitForElementToBeClickable(searchAdvertisementButton, driver);
        searchAdvertisementButton.click();

    }

    public void collectAdvertisementTax() {
        waitForElementToBeClickable(collectButton, driver);
        collectButton.click();
        switchToNewlyOpenedWindow(driver);

        waitForElementToBeClickable(chequeRadioButton,driver);
        jsClick(chequeRadioButton,driver);
        waitForElementToBeVisible(cashRadioButton,driver);
        jsClick(cashRadioButton,driver);
        waitForElementToBeVisible(totalamounttobepaid, driver);
        String AmountNum = totalamounttobepaid.getAttribute("value");
        String Amount = AmountNum.split("\\.")[0];
        System.out.println(Amount);
        waitForElementToBeClickable(amount, driver);
        amount.sendKeys(Amount);
        waitForElementToBeClickable(payButton, driver);
        jsClick(payButton, driver);
    }

    public String enterAgencyDetails() {
       waitForElementToBeVisible(agencyCodeTextBox,driver);
       agencyCodeTextBox.sendKeys("AC"+min+min1);

       waitForElementToBeClickable(agencyNameTextBox,driver);
       String name = "Test"+min;
       agencyNameTextBox.sendKeys(name);

       waitForElementToBeClickable(depositAmountTextBox,driver);
       depositAmountTextBox.sendKeys("1000");

       waitForElementToBeClickable(mobileNumberTextBox,driver);
       mobileNumberTextBox.sendKeys("9885"+min+min+min);

       waitForElementToBeClickable(statusDropDownBox,driver);
       new Select(statusDropDownBox).selectByVisibleText("ACTIVE");

       return name;
    }

    public void searchByAgency(String name) {
       waitForElementToBeVisible(agencyTextBox,driver);
       agencyTextBox.click();
       agencyTextBox.sendKeys(name);
       waitForElementToBeVisible( driver.findElement(By.className("tt-dropdown-menu")),driver);
       WebElement dropdown = driver.findElement(By.className("tt-dropdown-menu"));
       dropdown.click();

       waitForElementToBeClickable(searchAdvertisementButton, driver);
       searchAdvertisementButton.click();
    }

     public void collectAdvertisementTaxByAgency() {
        waitForElementToBeVisible(totalamounttobepaid, driver);
        String AmountNum = totalamounttobepaid.getAttribute("value");
        String Amount = AmountNum.split("\\.")[0];
        System.out.println(Amount);
        waitForElementToBeClickable(amount, driver);
        amount.sendKeys(Amount);
        waitForElementToBeClickable(payButton, driver);
        jsClick(payButton, driver);
    }

    public void submit() {
      waitForElementToBeVisible(submitAgencyDetailsButton,driver);
      submitAgencyDetailsButton.click();
    }

    public String agencyCreationMessage(){
        String message = agencyCreationMessage.getText();
        return message;
    }

    public void CloseAgency(){
         waitForElementToBeClickable(closeAgencyCreation,driver);
        closeAgencyCreation.click();

        switchToPreviouslyOpenedWindow(driver);
    }

    public void searchAgency(String name) {

      waitForElementToBeVisible(searchAgencyBox,driver);
      searchAgencyBox.click();
      new Select(searchAgencyBox).selectByVisibleText(name);

      waitForElementToBeClickable(viewButton,driver);
      viewButton.click();
    }
    public void CloseAgencySearch(){
        waitForElementToBeClickable(closeAgencySearch,driver);
        closeAgencySearch.click();

        switchToPreviouslyOpenedWindow(driver);
    }

    public void searchAdvertisementForDeactivate(String number) {
        waitForElementToBeVisible(hoardingNumberTextBox,driver);
        hoardingNumberTextBox.sendKeys(number);
        waitForElementToBeClickable(submitButtonForDeactive, driver);
        submitButtonForDeactive.click();
        waitForElementToBeClickable(deactivateButton, driver);
        deactivateButton.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void selectAdvertisementAgency() {
     waitForElementToBeVisible(collectAdvertisementTaxButton,driver);
     collectAdvertisementTaxButton.click();
     switchToNewlyOpenedWindow(driver);

     waitForElementToBeVisible(selectAllCheckBox,driver);
     selectAllCheckBox.click();

     waitForElementToBeClickable(collectFeeButton,driver);
     collectFeeButton.click();
    }

    public void deactivatesAdvertisement() {
        waitForElementToBeClickable(deactivationRemarks, driver);
        deactivationRemarks.sendKeys("Deactivate the Advertisement");
        waitForElementToBeClickable(deactiveDate, driver);
        deactiveDate.sendKeys(getCurrentDate());
        waitForElementToBeClickable(deactivateSubmitButton, driver);
        deactivateSubmitButton.click();
        switchToNewlyOpenedWindow(driver);
    }

    public String successMessageForDeactivation(){
        String message = creationMsgForDeactivation.getText();
        System.out.println("\n"+message);
        return message;
    }

    public void closeMultipleWindowsForDeactivateadvertisement(String url) {
        waitForElementToBeClickable(closeButtonOfDeactivateSuccessPage,driver);
        closeButtonOfDeactivateSuccessPage.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(url)){
                break;
            }
        }

        close();
    }
}
