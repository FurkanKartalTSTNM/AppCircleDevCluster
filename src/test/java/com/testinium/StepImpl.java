package com.testinium;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableMap;
import com.testinium.helper.RandomString;
import com.testinium.helper.StoreHelper;
import com.testinium.model.SelectorInfo;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Random;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.Keys;

public class StepImpl extends HookImpl {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static String staticVariable;
    long startTime= 0;
    static List <String> user = new ArrayList<>();
    static List <String> password = new ArrayList<>();
    static List <String> name = new ArrayList<>();
    static List <String> surname = new ArrayList<>();
    static List <String> email = new ArrayList<>();
    static List <String> phoneNumber = new ArrayList<>();
    static List <String> birthDate = new ArrayList<>();
    static List <String> password1 = new ArrayList<>();
    static List <String> password2 = new ArrayList<>();
    static List <String> Old = new ArrayList<>();
    static List <String> New = new ArrayList<>();
    static List <String> Again = new ArrayList<>();
    static List <String> CustomerName = new ArrayList<>();
    static List <String> CustomerSurname = new ArrayList<>();
    static List <String> CustomerPhone = new ArrayList<>();
    static List <String> CustomerAddressTitle = new ArrayList<>();
    static List <String> CustomerAddressDetail = new ArrayList<>();
    static List <String> CustomerAddressPostCode = new ArrayList<>();
    static List <String> province = new ArrayList<>();
    static List <String> county = new ArrayList<>();


    String accountUser;
    String accountpassword;
    String registerName;
    String registerSurname;
    String registerEmail;
    String registerPhoneNumber;
    String registerBirthDate;
    String registerPassword1;
    String registerPassword2;
    String oldPassword;

    String newPassword;

    String againNewPassword;

    String customerName;

    String customerSurname;

    String customerPhone;

    String customerAddressTitle;

    String customerAddressDetail;

    String customerAddressPostCode;
    String realProvince;
    String realCounty;




    public StepImpl() {

    }


    public List<MobileElement> findElements(By by) throws Exception {
        List<MobileElement> webElementList = null;
        try {
            webElementList = appiumFluentWait.until(new ExpectedCondition<List<MobileElement>>() {
                @Nullable
                @Override
                public List<MobileElement> apply(@Nullable WebDriver driver) {
                    List<MobileElement> elements = driver.findElements(by);
                    return elements.size() > 0 ? elements : null;
                }
            });

            if (webElementList == null) {
                throw new NullPointerException(String.format("by = %s Web element list not found", by.toString()));
            }

        } catch (Exception e) {
            throw e;
        }
        return webElementList;
    }

    public List<MobileElement> findElementsWithoutAssert(By by) {

        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(by);
        } catch (Exception e) {
        }
        return mobileElements;
    }

    public List<MobileElement> findElementsWithAssert(By by) {

        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(by);
        } catch (Exception e) {
            Assertions.fail("by = %s Elements not found ", by.toString());
            e.printStackTrace();
        }
        return mobileElements;
    }


    public MobileElement findElement(By by) throws Exception {
        MobileElement mobileElement;
        try {
            mobileElement = findElements(by).get(0);
        } catch (Exception e) {
            throw e;
        }
        return mobileElement;
    }

    public MobileElement findElementWithoutAssert(By by) {
        MobileElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementWithAssertion(By by) {
        MobileElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            Assertions.fail(mobileElement.getAttribute("value") + " " + "by = %s Element not found ", by.toString());
            e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementByKeyWithoutAssert(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        MobileElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);

        MobileElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Element not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElement;
    }


    public List<MobileElement> findElemenstByKeyWithoutAssert(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElements;
    }

    public List<MobileElement> findElemenstByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Elements not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElements;
    }

    @Step("Excelden degerler <sheetneme>,<deger> okunur")
    public void excel(String sheetname, Integer deger) throws IOException {

        List<Object> excelRead = new ArrayList<>();
        String path = "src/test/resources/excel/KampanyaTestleri.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetname);

        int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
        System.out.println("row count:"+rowCount);

        for(int i=0;i<=rowCount;i++){
            System.out.print(sheet.getRow(i).getCell(deger));
            System.out.println();
            excelRead.add((sheet.getRow(i).getCell(deger)).toString().trim());
        }
    }

    @Step("Excelden degerler <sheetname>,<deger>,<row> okunur ve <saveKey> sakla")
    public void excelRead(String sheetname, Integer deger, Integer row, String saveKey) throws IOException {

        List<Object> excelRead = new ArrayList<>();
        String path = "src/test/resources/excel/KampanyaTestleri.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetname);

        Cell cell = sheet.getRow(row - 1).getCell(deger - 1);
        String value = (cell != null) ? cell.toString().trim() : "";

        System.out.println("Değer: " + value);
        StoreHelper.INSTANCE.saveValue(saveKey, value);
    }

    @Step("Excelden degerler <sheetname>,<deger>,<row> sku okunur ve <saveKey> saklanir")
    public void excelReadSKU(String sheetname, Integer deger, Integer row, String saveKey) throws IOException {

        List<Object> excelRead = new ArrayList<>();
        String path = "src/test/resources/excel/KampanyaTestleri.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetname);

        Cell cell = sheet.getRow(row - 1).getCell(deger - 1);

        // SKU
        if (cell.getCellType() == CellType.NUMERIC) {
            DataFormatter dataFormatter = new DataFormatter();
            String formattedValue = dataFormatter.formatCellValue(cell);
            System.out.println("Değer: " + formattedValue);
            StoreHelper.INSTANCE.saveValue(saveKey, formattedValue);
        } else {
            String value = (cell != null) ? cell.toString().trim() : "";
            System.out.println("Değer: " + value);
            StoreHelper.INSTANCE.saveValue(saveKey, value);
        }

        workbook.close();
    }

    @Step({"<str> elementine <str2> degerini gir", "<str> element write <str2> text"})
    public void sendK(String str, String str2) {
        findElementWithAssertion(By.id(str)).sendKeys(str2);
    }


    @Step({"Değeri <text> e eşit olan elementi bul ve tıkla",
            "Find element text equals <text> and click"})
    public void clickByText(String text) {
        findElementWithAssertion(By.xpath(".//*[contains(@text,'" + text + "')]")).click();
    }


    @Step({"İçeriği <value> e eşit olan elementli bul ve tıkla",
            "Find element value equals <value> and click"})
    public void clickByValue(String value) {
        findElementWithAssertion(MobileBy.xpath(".//*[contains(@value,'" + value + "')]")).click();
    }
    @Step("İçeriği <value> value iceren text degerinin goruntulendigi kontrol edilir")
    public void checkByValue(String value) {
        appiumDriver.findElement(MobileBy.xpath(".//*[contains(@value,'" + value + "')]"));
        System.out.println(value+ " "+ " degeri goruntulendi");
    }

    @Step("<key> csv dosyasindan rastgele kullanici sec")
    public void csvReader(String value) {
        try {
            String line = "";
            String splitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("data/"+value+".csv"));
            while ((line = br.readLine()) != null)
            {
                String[] keyValue = line.split(splitBy,2);
                user.add(keyValue[0]);
                password.add(keyValue[1]);
            }

            System.out.println("Maillere ait csv okundu");

            int number = createRandomNumber(user.size());

            accountUser = user.get(number);
            accountpassword = password.get(number);

            System.out.println("Kullanilacak Kullanici adi :" + accountUser);
            System.out.println("Kullanilacak Sifre :" + accountpassword);

        }catch (Exception e){
            System.out.println("Csv dosyasi oluşturulurken hatayla karsilasildi");
            System.out.println(user);
            System.out.println(password);
        }
    }
    @Step("<key> csv dosyasindan <number> numaralı kullaniciyi sec")
    public void csvExactReader(String value, String no) {
        try {
            String line = "";
            String splitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("data/"+value+".csv"));
            while ((line = br.readLine()) != null)
            {
                String[] keyValue = line.split(splitBy,2);
                user.add(keyValue[0]);
                password.add(keyValue[1]);
            }

            System.out.println("Maillere ait csv okundu");


            int number = Integer.parseInt(no);

            accountUser = user.get(number);
            accountpassword = password.get(number);

            System.out.println("Kullanilacak Kullanici adi :" + accountUser);
            System.out.println("Kullanilacak Sifre :" + accountpassword);

        }catch (Exception e){
            System.out.println("Csv dosyasi oluşturulurken hatayla karsilasildi");
            System.out.println(user);
            System.out.println(password);
        }
    }

    @Step("<key> csv dosyasindan rastgele il ve ilce sec")
    public void csvCityReader(String value) {
        try {
            String line = "";
            String splitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("data/"+value+".csv"));
            while ((line = br.readLine()) != null)
            {
                String[] keyValue = line.split(splitBy,2);
                province.add(keyValue[0]);
                county.add(keyValue[1]);
            }

            System.out.println("Şehirlere ait csv okundu");

            int number = createRandomNumber(province.size());

            realProvince = province.get(number);
            realCounty = county.get(number);

            System.out.println("Kullanilacak Şehir Adi :" + realProvince);
            System.out.println("Kullanilacak Ilce Adi :" + realCounty);

        }catch (Exception e){
            System.out.println("Csv dosyasi oluşturulurken hatayla karsilasildi");
            System.out.println(province);
            System.out.println(county);
        }
    }

    @Step("<key> csv dosyasindan rastgele register kullanicisi sec")
    public void csvRegisterReader(String value) {
        try {
            String line = "";
            String splitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("data/"+value+".csv"));
            while ((line = br.readLine()) != null)
            {
                String[] keyValue = line.split(splitBy,7);
                name.add(keyValue[0]);
                surname.add(keyValue[1]);
                email.add(keyValue[2]);
                phoneNumber.add(keyValue[3]);
                birthDate.add(keyValue[4]);
                password1.add(keyValue[5]);
                password2.add(keyValue[6]);
            }

            System.out.println("Ad, Soyad ve Maillere ait csv okundu");
            System.out.println("Telefon ve Doğum Tarihine ait csv okundu");
            System.out.println("Şifre ve Şifre Tekrar ait csv okundu");

            int number = createRandomNumber(name.size());

            registerName = name.get(number);
            registerSurname = surname.get(number);
            registerEmail = email.get(number);
            registerPhoneNumber = phoneNumber.get(number);
            registerBirthDate = birthDate.get(number);
            registerPassword1 = password1.get(number);
            registerPassword2 = password2.get(number);

            System.out.println("Kullanilacak ad :" + registerName);
            System.out.println("Kullanilacak soyad :" + registerSurname);
            System.out.println("Kullanilacak email :" + registerEmail);
            System.out.println("Kullanilacak Kullanici numarası :" + registerPhoneNumber);
            System.out.println("Kullanilacak tarih :" + registerBirthDate);
            System.out.println("Kullanilacak şifre :" + registerPassword1);
            System.out.println("Kullanilacak şifre tekrar :" + registerPassword2);

        }catch (Exception e){
            System.out.println("Csv dosyasi oluşturulurken hatayla karsilasildi");
            System.out.println(name);
            System.out.println(surname);
            System.out.println(email);
            System.out.println(phoneNumber);
            System.out.println(birthDate);
            System.out.println(registerPassword1);
            System.out.println(registerPassword2);
        }
    }
    @Step("<key> csv dosyasindan rastgele adres bilgileri sec")
    public void csvReaderAddress(String value) {
        try {
            String line = "";
            String splitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("data/"+value+".csv"));
            while ((line = br.readLine()) != null)
            {
                String[] keyValue = line.split(splitBy,6);
                CustomerName.add(keyValue[0]);
                CustomerSurname.add(keyValue[1]);
                CustomerPhone.add(keyValue[2]);
                CustomerAddressTitle.add(keyValue[3]);
                CustomerAddressDetail.add(keyValue[4]);
                CustomerAddressPostCode.add(keyValue[5]);
            }

            System.out.println("Adres icin kisisel bilgilere ait csv okundu");

            int number = createRandomNumber(CustomerName.size());

            customerName = CustomerName.get(number);
            customerSurname = CustomerSurname.get(number);
            customerPhone = CustomerPhone.get(number);
            customerAddressTitle = CustomerAddressTitle.get(number);
            customerAddressDetail = CustomerAddressDetail.get(number);
            customerAddressPostCode = CustomerAddressPostCode.get(number);


            System.out.println("Kisisel Bilgiler icin Musteri Adi :" + customerName);
            System.out.println("Kisisel Bilgiler icin Musteri Soyadi :" + customerSurname);
            System.out.println("Kisisel Bilgiler icin Müsteri Telefon No :" + customerPhone);
            System.out.println("Kisisel Bilgiler icin Müsteri Adres Ismi :" + customerAddressTitle);
            System.out.println("Kisisel Bilgiler icin Müsteri Adres Detay :" + customerAddressDetail);
            System.out.println("Kisisel Bilgiler icin Müsteri Adres Posta Kodu :" + customerAddressPostCode);
        }catch (Exception e){
            System.out.println("Csv dosyasi oluşturulurken hatayla karsilasildi");
            System.out.println(customerName);
            System.out.println(customerSurname);
            System.out.println(customerPhone);
            System.out.println(customerAddressTitle);
            System.out.println(customerAddressDetail);
            System.out.println(customerAddressPostCode);
        }
    }

    @Step("<key> csv dosyasindan rastgele sifre sec")
    public void csvReaderPassword(String value) {
        try {
            String line = "";
            String splitBy = ",";

            BufferedReader br = new BufferedReader(new FileReader("data/"+value+".csv"));
            while ((line = br.readLine()) != null)
            {
                String[] keyValue = line.split(splitBy,3);
                Old.add(keyValue[0]);
                New.add(keyValue[1]);
                Again.add(keyValue[2]);
            }

            System.out.println("Sifrelere ait csv okundu");

            int number = createRandomNumber(Old.size());

            oldPassword = Old.get(number);
            newPassword = New.get(number);
            againNewPassword = Again.get(number);

            System.out.println("Kullanilacak Eski Sifre:" + oldPassword);
            System.out.println("Kullanilacak Yeni Sifre :" + newPassword);
            System.out.println("Kullanilacak Tekrar Yeni Sifre :" + againNewPassword);

        }catch (Exception e){
            System.out.println("Csv dosyasi oluşturulurken hatayla karsilasildi");
            System.out.println(oldPassword);
            System.out.println(newPassword);
            System.out.println(againNewPassword);
        }
    }

    @Step({"Değeri <text> e eşit olan <index>. elementi bul ve tıkla"})
    public void clickByText(String text, int index) {
        findElementWithAssertion(By.xpath("(.//*[contains(@text,'" + text + "')])[" + index + "]")).click();
    }

    @Step({"İçeriği <value> e eşit olan <index>. elementi bul ve tıkla"})
    public void clickByValue(String value, int index) {
        findElementWithAssertion(MobileBy.xpath("(.//*[contains(@value,'" + value + "')])[" + index + "]")).click();
    }

    @Step("<key> elementinin <index> .li elementi bul ve tıkla")
    public void clickByKeyIndex(String key, int index) {
        findElementsWithoutAssert(selector.getSelectorInfo(key).getBy()).get(index).click();
    }


    @Step({"Elementine tıkla <key>", "Click element by <key>"})
    public void clickByKey(String key) {
        doesElementExistByKey(key, 5);
        findElementByKey(key).click();
        logger.info(key + " elemente tıkladı");
    }

    @Step("Safari'de <key> kontrolu yapilir ve uygulamaya geri donulur")
    public void IosCheckSafari(String key) {
        doesElementExistByKey(key, 5);
        findElementByKey(key);
        waitBySecond(3);
        findElementByKeyWithoutAssert(key);
        logger.info("Safari sayfasi acildi " + key + " elementi bulundu.");
        // Uygulamaya geri dönme işlemi (iOS için)
        appiumDriver.runAppInBackground(Duration.ofSeconds(5));
        appiumDriver.activateApp("com.pharos.Gratis");
        logger.info("Gratis app uygulamasina geri donuldu.");
        waitBySecond(3);
    }

    @Step("Safari'de element kontrolu yapilir ve uygulamaya geri donulur")
    public void IosCheckSafari() {
        waitBySecond(5);
        logger.info("Safari sayfasi acildi elementi bulundu.");
        // Uygulamaya geri dönme işlemi (iOS için)
        appiumDriver.runAppInBackground(Duration.ofSeconds(10));
        appiumDriver.activateApp("com.pharos.Gratis");
        logger.info("Gratis app uygulamasina geri donuldu.");
        waitBySecond(10);
    }

    @Step({"Check if element <key> exists",
            "Wait for element to load with key <key>",
            "Element var mı kontrol et <key>",
            "Elementin yüklenmesini bekle <key>"})
    public MobileElement getElementWithKeyIfExists(String key) throws InterruptedException {
        MobileElement element;
        try {
            element = findElementByKey(key);
            logger.info(key + " elementi bulundu.");
        } catch (Exception ex) {
            logger.info("Element: '" + key + "' doesn't exist.");
            return null;
        }
        return element;
    }
    @Step({"Rastgele secilen kullanici adi <key> elementine, sifre <key> elementine yazilir"})
    public void existElementt(String key,String key2) {
        findElementByKey(key).sendKeys(accountUser);
        findElementByKey(key2).sendKeys(accountpassword);
    }

    @Step({"Yeni Rastgele secilen kullanici adi <key> elementine, sifre <key> elementine yazilir"})
    public void existElementtNew(String key,String key2) {
        findElementByKey(key).sendKeys(accountUser);
        waitBySecond(2);
        tapElementWithKeyControlArea("devamEtBtn");
        tapElementWithKeyControlArea("ContinueBtn");
        waitBySecond(2);
        findElementByKey(key2).sendKeys(accountpassword);
        waitBySecond(2);
    }

    @Step({"Rastgele secilen il adi <key> elementine yazilir"})
    public void existProvince(String key) {
        findElementByKey(key).sendKeys(realProvince);
    }

    @Step({"Rastgele secilen ilce adi <key> elementine yazilir"})
    public void existCounty(String key) {
        findElementByKey(key).sendKeys(realCounty);
    }

    @Step({"Rastgele secilen kullanici adi <key> elementine, soyadi <key> elementine, mail <key> elementine yazilir"})
    public void personalInfo(String key,String key2,String key3) {
        findElementByKey(key).sendKeys(registerName);
        findElementByKey(key2).sendKeys(registerSurname);
        findElementByKey(key3).sendKeys(registerEmail);
    }

    @Step({"Rastgele secilen telefon <key> elementine, doğum tarihi <key>  elementine yazilir"})
    public void personalInfo2(String key,String key2) {
        findElementByKey(key).sendKeys(registerPhoneNumber);
        findElementByKey(key2).sendKeys(registerBirthDate);
    }
    @Step({"Rastgele secilen telefon <key> elementine yazilir"})
    public void phoneInfo(String key) {
        findElementByKey(key).sendKeys(registerPhoneNumber);
    }

    @Step({"Rastgele secilen şifre <key> elementine, şifre tekrar <key>  elementine yazilir"})
    public void registerPassword(String key,String key2) {
        findElementByKey(key).sendKeys(registerPassword1);
        findElementByKey(key2).sendKeys(registerPassword2);
    }
    @Step({"Eski sifre <key> elementine, yeni sifre <key> elementine, tekrar yeni sifre <key> elementine yazilir"})
    public void existElementtPassword(String key,String key2,String key3) {
        findElementByKey(key).sendKeys(oldPassword);
        findElementByKey(key2).sendKeys(newPassword);
        findElementByKey(key3).sendKeys(againNewPassword);
    }

    @Step({"Adres icin Ad <key> elementine, Soyad <key> elementine, Telefon <key> elementine, Adres Ismi <key> elementine yazilir"})
    public void existElementtAddAddress(String key,String key2,String key3,String key4) {
        findElementByKey(key).sendKeys(customerName);
        findElementByKey(key2).sendKeys(customerSurname);
        findElementByKey(key3).sendKeys(customerPhone);
        findElementByKey(key4).sendKeys(customerAddressTitle);
    }
    @Step({"Adres Ismi <key>, Adres icin Ad <key> elementine elementine yazilir"})
    public void existElementAddAddressTitleName(String key,String key2) {
        findElementByKey(key).sendKeys(customerAddressTitle);
        findElementByKey(key2).sendKeys(customerName);
    }

    @Step({"Soyad <key> elementine, Telefon <key> elementine yazilir"})
    public void existElementAddAddressSurnamePhone(String key,String key2) {
        findElementByKey(key).sendKeys(customerSurname);
        findElementByKey(key2).sendKeys(customerPhone);
    }

    @Step({"Yeni Adres Ekleme icin Adres Detay <key> elementine, Posta Kodu <key> elementine yazilir"})
    public void existElementtAddAddressContinue(String key,String key2) {
        findElementByKey(key).sendKeys(customerAddressDetail);
        findElementByKey(key2).sendKeys(customerAddressPostCode);
    }

    @Step({"<key> elementine sayacli tikla <key2> value degerini bekle"})
    public void clickByKeyWithCounter(String key,String key2) {
        if (findElementByKey(key).isDisplayed()) {
            System.out.println("Dongeye girdi");
            findElementByKey(key).click();
        }
        logger.info(key + " elementine tiklandi");
        clickExistElement(key,key2);
        logger.info(key2 + " uyarısı görüldü.");
    }

    public void clickExistElement(String key1,String key) {
        startTime = System.currentTimeMillis();
        findElementByKey(key);
        //assertTrue(findElementByKey(key).isDisplayed(), "Element sayfada bulunamadı !");
        long finishTime = System.currentTimeMillis();
        long eventDuration = (finishTime - startTime) / 1000;  // Zamanı saniyeye çeviriyoruz
        long seconds = eventDuration % 60;  // Saniyeyi hesaplıyoruz
        long milliseconds = (finishTime - startTime) % 1000;  // Saliseyi hesaplıyoruz
        System.out.println("["+key1+"] ------> "+"["+key+"] " + seconds + " seconds, " + milliseconds + " milliseconds");
    }

    @Step({"<key> icin sayac tut"})
    public void existElement(String key) {
        startTime = System.currentTimeMillis();
        findElementByKey(key);
        finishCounter(key);
    }

    public void finishCounter(String key){
        long finishTime = System.currentTimeMillis();
        long eventDuration = (finishTime - startTime) / 1000;  // Zamanı saniyeye çeviriyoruz
        long seconds = eventDuration % 60;  // Saniyeyi hesaplıyoruz
        long milliseconds = (finishTime - startTime) % 1000;  // Saliseyi hesaplıyoruz
        logger.info("["+key+"] "+" elementi sayfada görüntülendi");
        System.out.println("["+key+"] " + seconds + " seconds, " + milliseconds + " milliseconds");
    }

    @Step("<key> elementinin <text> textini içerdiği kontrol edilir")
    public void checkTextByKey(String key, String text) {
        try {
            Thread.sleep(3000);
            System.out.println("******"+findElementByKey(key).getText()+"******");
            assertTrue(findElementByKey(key).getText().contains(text), "Element beklenen değeri taşımıyor !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementinin <text>, <text2> veya <text3> textini içerdiği kontrol edilir")
    public void checkTextByKey(String key, String text, String text2, String text3) {
        try {
            Thread.sleep(3000);
            String elementText = findElementByKey(key).getText();
            System.out.println("******"+ elementText + "******");
            assertTrue(elementText.contains(text) || elementText.contains(text2) || elementText.contains(text3), "Element beklenen değeri taşımıyor !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementi <text> degerine esit mi")
    public void checkTextEqualsByKey(String key, String text) {
        try {
            Thread.sleep(3000);
            System.out.println("******"+findElementByKey(key).getText()+"******");
            assertNotEquals(findElementByKey(key).getText(),text,"Elementi girilen text değerine eşit değil!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"<key> li elementi bul ve varsa tıkla", "Click element by <key> if exist"})
    public void existClickByKey(String key) throws InterruptedException {
        MobileElement element;
        element = findElementByKeyWithoutAssert(key);
        if (element != null) {
            Point elementPoint = ((MobileElement) element).getCenter();
            TouchAction action = new TouchAction(appiumDriver);
            action.tap(PointOption.point(elementPoint.x, elementPoint.y)).perform();
        }
        waitBySecond(2);
    }


    @Step({"<key> li elementi bul ve varsa dokun", "Click element by <key> if exist"})
    public void existTapByKey(String key) {
        if (findElementByKey(key).isDisplayed()) {
            findElementByKey(key).click();
        }
    }

    @Step({"sayfadaki <X> <Y> alana dokun"})
    public void coordinateTap(int X, int Y) {
        Dimension dimension = appiumDriver.manage().window().getSize();
        int width = dimension.width;
        int height = dimension.height;

        TouchAction action = new TouchAction(appiumDriver);
        action.tap(PointOption.point((width * X) / 100, (height * Y) / 100))
                .release().perform();

    }

    @Step({"<key> li elementi bul, temizle ve <text> değerini yaz",
            "Find element by <key> clear and send keys <text>"})
    public void sendKeysByKey(String key, String text) {
        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue(text);
        logger.info(key+" texti "+text+" key elementine temizlenip yazildi");
    }

    @Step({"<key> li elementi bul ve temizle",
            "Find element by <key> and clear"})
    public void clearFieldByKey(String key) {
        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        logger.info(key + " elementi temizlendi");
    }

    @Step({"<key> li elementin text degeri silinir"})
    public void keyClear(String key) {
        findElementByKey(key).clear();
    }


    @Step({"<t> textini <k> elemente yaz",
            "Find element by <key> and send keys <text>"})
    public void sendKeysByKeyNotClear(String t, String k) {
        doesElementExistByKey(k, 5);
        findElementByKey(k).sendKeys(t);
        logger.info(t+" texti"+k+" key elementine yazildi");
    }

    @Step({"Saklanan <SKU> textini <key> elemente yaz"})
    public void getSendKeysByKeyNotClear(String SKU, String key) {
        String sKUs = StoreHelper.INSTANCE.getValue(SKU);
        doesElementExistByKey(key, 5);
        findElementByKey(key).sendKeys(sKUs);
        logger.info(SKU+" texti"+key+" key elementine yazildi");
    }

    public int createRandomNumber(int max) {
        Random rand = new Random();

        int randomNumber = rand.nextInt(max);

        return randomNumber;
    }


    @Step({"<key> li elementi bul ve değerini <saveKey> olarak sakla",
            "Find element by <key> and save text <saveKey>"})
    public void saveTextByKey(String key, String saveKey) {
        StoreHelper.INSTANCE.saveValue(saveKey, findElementByKey(key).getText());
        logger.info("["+StoreHelper.INSTANCE.getValue(saveKey)+"]" + " degeri ["+ saveKey + "] ismiyle hafizaya kaydedildi");
    }
    @Step({"<key> name li elementi bul ve değerini <saveKey> olarak sakla",
            "Find element by <key> and save name <saveKey>"})
    public void saveNameByKey(String key, String saveKey) {
        StoreHelper.INSTANCE.saveValue(saveKey, findElementByKey(key).getAttribute("name"));
        logger.info("["+StoreHelper.INSTANCE.getValue(saveKey)+"]" + " degeri ["+ saveKey + "] ismiyle hafizaya kaydedildi");
    }

    @Step({"<key> li promosyon elementini bul ve değerini <saveKey> olarak sakla",
            "Find element by <key> and save text <saveKey>"})
    public void saveTextByKeyPromosyon(String key, String saveKey) {
        StoreHelper.INSTANCE.saveValue(saveKey, findElementByKey(key).getText().replace("-", "").trim());
        logger.info("["+StoreHelper.INSTANCE.getValue(saveKey)+"]" + " degeri ["+ saveKey + "] ismiyle hafizaya kaydedildi");
    }

    @Step({"<key> li elementli markayı bul ve değerini <saveKey> olarak sakla",
            "Find element by <key> and save text <saveKey>"})
    public void saveTextByKeyy(String key, String saveKey) {
        StoreHelper.INSTANCE.saveValue(saveKey, findElementByKey(key).getText().substring(0,findElementByKey(key).getText().length()-5));
        logger.info("["+StoreHelper.INSTANCE.getValue(saveKey)+"]" + " degeri ["+ saveKey + "] ismiyle hafizaya kaydedildi");
    }

    @Step({"<key> li elementli küsüratı bul ve değerini <saveKey> olarak sakla",
            "Find element by <key> and save text <saveKey>"})
    public void savePriceByKeyy(String key, String saveKey) {
        String kusur = findElementByKey(key).getText().substring(1,findElementByKey(key).getText().length());
        StoreHelper.INSTANCE.saveValue(saveKey,kusur);
        logger.info("["+StoreHelper.INSTANCE.getValue(saveKey)+"]" + " degeri ["+ saveKey + "] ismiyle hafizaya kaydedildi");
    }

    @Step({"<key> li ve değeri <text> e eşit olan elementli bul ve tıkla",
            "Find element by <key> text equals <text> and click"})
    public void clickByIdWithContains(String key, String text) {
        List<MobileElement> elements = findElemenstByKey(key);
        for (MobileElement element : elements) {
            logger.info("Text !!!" + element.getText());
            if (element.getText().toLowerCase().contains(text.toLowerCase())) {
                element.click();
                break;
            }
        }
    }

    @Step({"<key> li ve değeri <text> e eşit olan elementli bulana kadar swipe et ve tıkla",
            "Find element by <key> text equals <text> swipe and click"})
    public void clickByKeyWithSwipe(String key, String text) throws InterruptedException {
        boolean find = false;
        int maxRetryCount = 10;
        while (!find && maxRetryCount > 0) {
            List<MobileElement> elements = findElemenstByKey(key);
            for (MobileElement element : elements) {
                if (element.getText().contains(text)) {
                    element.click();
                    find = true;
                    break;
                }
            }
            if (!find) {
                maxRetryCount--;
                if (appiumDriver instanceof AndroidDriver) {
                    swipeUpAccordingToPhoneSize();
                    waitBySecond(1);
                } else {
                    swipeDownAccordingToPhoneSize();
                    waitBySecond(1);
                }
            }
        }
    }

    @Step({"<key> li elementi bulana kadar swipe et ve tıkla",
            "Find element by <key>  swipe and click"})
    public void clickByKeyWithSwipe(String key) throws InterruptedException {
        int maxRetryCount = 10;
        while (maxRetryCount > 0) {
            List<MobileElement> elements = findElemenstByKey(key);
            if (elements.size() > 0) {
                if (elements.get(0).isDisplayed() == false) {
                    maxRetryCount--;
                    swipeDownAccordingToPhoneSize();
                    waitBySecond(1);

                } else {
                    elements.get(0).click();
                    logger.info(key + " elementine tıklandı");
                    break;
                }
            } else {
                maxRetryCount--;
                swipeDownAccordingToPhoneSize();
                waitBySecond(1);
            }

        }
    }


    private int getScreenWidth() {
        return appiumDriver.manage().window().getSize().width;
    }

    private int getScreenHeight() {
        return appiumDriver.manage().window().getSize().height;
    }

    private int getScreenWithRateToPercent(int percent) {
        return getScreenWidth() * percent / 100;
    }

    private int getScreenHeightRateToPercent(int percent) {
        return getScreenHeight() * percent / 100;
    }


    public void swipeDownAccordingToPhoneSize(int startXLocation, int startYLocation, int endXLocation, int endYLocation) {
        startXLocation = getScreenWithRateToPercent(startXLocation);
        startYLocation = getScreenHeightRateToPercent(startYLocation);
        endXLocation = getScreenWithRateToPercent(endXLocation);
        endYLocation = getScreenHeightRateToPercent(endYLocation);

        new TouchAction(appiumDriver)
                .press(PointOption.point(startXLocation, startYLocation))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(endXLocation, endYLocation))
                .release()
                .perform();
    }

    @Step({"<key> id'li elementi bulana kadar <times> swipe yap ",
            "Find element by <key>  <times> swipe "})
    public void swipeDownUntilSeeTheElement(String element, int limit) throws InterruptedException {
        for (int i = 0; i < limit; i++) {
            List<MobileElement> meList = findElementsWithoutAssert(By.id(element));
            meList = meList != null ? meList : new ArrayList<MobileElement>();
            logger.info(i + ". swipe yapiliyor");
            if (meList.size() > 0 &&
                    meList.get(0).getLocation().x <= getScreenWidth() &&
                    meList.get(0).getLocation().y <= getScreenHeight()) {
                break;
            } else {
                swipeDownAccordingToPhoneSize(50, 80, 50, 30);
                waitBySecond(1);

                break;
            }
        }
    }


    @Step({"<key> li elementi bulana kadar swipe et",
            "Find element by <key>  swipe "})
    public void findByKeyWithSwipe(String key) {
        int maxRetryCount = 10;
        while (maxRetryCount > 0) {
            List<MobileElement> elements = findElemenstByKeyWithoutAssert(key);
            if (elements.size() > 0) {
                if (elements.get(0).isDisplayed() == false) {
                    maxRetryCount--;

                    swipeDownAccordingToPhoneSize();

                } else {
                    System.out.println(key + " element bulundu");
                    break;
                }
            } else {
                maxRetryCount--;
                swipeDownAccordingToPhoneSize();

            }

        }
    }


    @Step("<yon> yönüne swipe et")
    public void swipe(String yon) {

        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        if (yon.equals("SAĞ")) {

            int swipeStartWidth = (width * 80) / 100;
            int swipeEndWidth = (width * 30) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else if (yon.equals("SOL")) {

            int swipeStartWidth = (width * 30) / 100;
            int swipeEndWidth = (width * 80) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();

        }
    }


    @Step({"<key> li elementin değeri <text> e içerdiğini kontrol et",
            "Find element by <key> and text contains <text>"})
    public void containsTextByKey(String key, String text) {
        By by = selector.getElementInfoToBy(key);
        assertTrue(appiumFluentWait.until(new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    currentValue = driver.findElement(by).getText();
                    return currentValue.contains(text);
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("text contains be \"%s\". Current text: \"%s\"", text, currentValue);
            }
        }));
        logger.info(key + " li elementin değeri \"" + text + "\" metnini içeriyor.");
    }

    @Step("toast message <key> değerine eşit mi")
    public void toastMessage(String key){
        String toastMessage = appiumDriver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println("The toast mesaage is: " + toastMessage);

        assertEquals(toastMessage, key);
    }

    @Step({"<key> li elementin değeri <text> e eşitliğini kontrol et",
            "Find element by <key> and text equals <text>"})
    public void equalsTextByKey(String key, String text) {
        assertTrue(appiumFluentWait.until(
                ExpectedConditions.textToBe(selector.getElementInfoToBy(key), text)));
    }

    @Step({"<seconds> saniye bekle", "Wait <second> seconds"})
    public void waitBySecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeUpAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;
            System.out.println(width + "  " + height);

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 75) / 100;
            int swipeEndHeight = (height * 40) / 100;
            System.out.println("Start width: " + swipeStartWidth + " - Start height: " + swipeStartHeight + " - End height: " + swipeEndHeight);
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction((AndroidDriver) appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 75) / 100;
            int swipeEndHeight = (height * 20) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        }
    }


    public void swipeDownAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 75) / 100;
            int swipeEndHeight = (height * 25) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 75) / 100;
            int swipeEndHeight = (height * 40) / 100;
            // appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }
    }

    public void quickSwipeDownAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int width = d.width;
            int height = d.height;

            int swipeStartWidth = width / 2;
            int swipeStartHeight = (height * 75) / 100;
            int swipeEndHeight = (height * 10) / 100;

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction.press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .release()
                    .perform();

            touchAction.press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int width = d.width;
            int height = d.height;

            int swipeStartWidth = width / 2;
            int swipeStartHeight = (height * 75) / 100;
            int swipeEndHeight = (height * 15) / 100;

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction.press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .release()
                    .perform();

            touchAction.press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .release()
                    .perform();
        }
    }


    public boolean isElementPresent(By by) {
        return findElementWithoutAssert(by) != null;
    }


    @Step({"<times> kere aşağıya kaydır", "Swipe times <times>"})
    public void swipe(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            swipeDownAccordingToPhoneSize();
            waitBySecond(1);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWİPE EDİLDİ");
            System.out.println("-----------------------------------------------------------------");

        }
    }

    @Step({"<times> kere teması devam ettirmeden aşağıya kaydır", "Swipe times <times>"})
    public void quickSwipe(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            quickSwipeDownAccordingToPhoneSize();
            waitBySecond(1);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWİPE EDİLDİ");
            System.out.println("-----------------------------------------------------------------");

        }
    }


    @Step({"<times> kere yukarı doğru kaydır", "Swipe up times <times>"})
    public void swipeUP(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            swipeUpAccordingToPhoneSize();
            waitBySecond(2);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWİPE EDİLDİ");
            System.out.println("-----------------------------------------------------------------");

        }
    }


    @Step({"Klavyeyi kapat", "Hide keyboard"})
    public void hideAndroidKeyboard() {
        try {

            if (localAndroid == false) {
                appiumDriver.hideKeyboard();
            } else {
                appiumDriver.hideKeyboard();
            }
        } catch (Exception ex) {
            logger.error("Klavye kapatılamadı "+ex.getMessage());
        }
    }

    @Step({"<text> değerini sayfa üzerinde olup olmadığını kontrol et."})
    public void getPageSourceFindWord(String text) {
        assertTrue(appiumDriver.getPageSource().contains(text), text + " sayfa üzerinde bulunamadı."
        );

        logger.info(text + " sayfa üzerinde bulundu");
    }

    @Step({"<saveKey> olarak saklanan değerin sayfa üzerinde olup olmadığını kontrol et"})
    public void getPageSourceFindSaveKey(String saveKey) {
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);

        assertTrue(appiumDriver.getPageSource().contains(saveElementTxt), saveElementTxt + " sayfa üzerinde bulunamadı."
        );

        logger.info(saveElementTxt + " sayfa üzerinde bulundu");
    }

    @Step({"<saveKey> olarak saklanan değerin sayfa üzerinde olmadığını kontrol et"})
    public void getPageSourceDontFindSaveKey(String saveKey) {
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);

        assertFalse(appiumDriver.getPageSource().contains(saveElementTxt), saveElementTxt + " sayfa üzerinde bulundu."
        );

        logger.info(saveElementTxt + " sayfa üzerinde bulunmadı");
    }

    @Step({"<key> değerini sayfa üzerinde olmadıgını kontrol et"})
    public void getPageSourceFindWordKey(String key) {

        MobileElement deneme = findElementByKeyWithoutAssert(key);

        if (deneme==null)
        {
            logger.info(key + " sayfa üzerinde olmadıgı kontrol edildi");
        }
        if (deneme!=null)
        {
            Assertions.fail("Element bulundu");
        }

    }

    @Step({"<key> değerini sayfa olmadığını kontrol et"})
    public void getPageSourceFindWordKeyy(String key) {
        assertFalse(appiumDriver.getPageSource().contains(key), key + " sayfa üzerinde bulunamadı."
        );

        logger.info(key + " sayfa üzerinde bulundu");
    }

    @Step("<key> elementi bulunana kadar en fazla <sure> saniye kadar bekle")
    public void waitUntilElementExist(String key, int sure) {
        boolean doesExist = doesElementExistByKey(key,sure);
        if (doesExist)
        {
            logger.info("element beklendi ve bulundu");
        }
        else
        {
            logger.info("element beklendi ama verilen süre içerisinde bulunamadı");
        }
    }

    @Step({"<length> uzunlugunda random bir kelime üret ve <saveKey> olarak sakla"})
    public void createRandomNumber(int length, String saveKey) {
        StoreHelper.INSTANCE.saveValue(saveKey, new RandomString(length).nextString());
    }

    @Step("Geri butonuna bas")
    public void clickBybackButton() {
        if (!localAndroid) {
            backPage();
        } else {
            ((AndroidDriver) appiumDriver).pressKeyCode(AndroidKeyCode.BACK);
        }

    }


    @Step("<StartX>,<StartY> oranlarından <EndX>,<EndY> oranlarına <times> kere swipe et")
    public void pointToPointSwipe(int startX, int startY, int endX, int endY, int count) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();

        int height = d.height;
        int width = d.width;
        startX = (width * startX) / 100;
        startY = (height * startY) / 100;
        endX = (width * endX) / 100;
        endY = (height * endY) / 100;
        for (int i = 0; i < count; i++) {
            waitBySecond(1);
            TouchAction action = new TouchAction(appiumDriver);
            action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(endX, endY))
                    .release().perform();
        }


    }

    @Step("<StartX>,<StartY> oranlarından <EndX>,<EndY> oranlarına <times> kere yukarı swipe et")
    public void pointToPointSwipeUp(int startX, int startY, int endX, int endY, int count) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();

        int height = d.height;
        int width = d.width;
        startX = (width * startX) / 100;
        startY = (height * startY) / 100;
        endX = (width * endX) / 100;
        endY = (height * endY) / 100;

        int swipeDistanceX = endX - startX;
        int swipeDistanceY = startY - endY;

        for (int i = 0; i < count; i++) {
            waitBySecond(1);
            TouchAction action = new TouchAction(appiumDriver);
            action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(startX + swipeDistanceX, startY + swipeDistanceY))
                    .release().perform();
        }
    }


    @Step("<key> elementinin hizasından sağdan sola <times> kere kaydır")
    public void swipeFromLeftToRightAligned(String key, int times) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();

        int height = d.height;
        int width = d.width;
        Point elementLocation = findElementByKeyWithoutAssert(key).getLocation();
        pointToPointSwipeWithCoordinats(width - 65, elementLocation.getY(), 40, elementLocation.getY(), times);
    }
    @Step("<key> elementinin hizasından soldan sağa <times> kere kaydır")
    public void swipeFromRightToLeftAligned(String key, int times) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();

        int height = d.height;
        int width = d.width;
        Point elementLocation = findElementByKeyWithoutAssert(key).getLocation();
        pointToPointSwipeWithCoordinats(40, elementLocation.getY(),  width - 50, elementLocation.getY(), times);
    }

    @Step("<key> elementinin hizasından aşağıdan yukarıya <times> kere kaydır")
    public void swipeFromDownToUpAligned(String key, int times) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();

        int height = d.height;
        Point elementLocation = findElementByKeyWithoutAssert(key).getLocation();
        pointToPointSwipeWithCoordinats(elementLocation.getX(), 160,  elementLocation.getX(), height + 50, times);
    }

    @Step("<key> li elementi hizala ve sagdan sola kaydır <times> kere y cordinatına <number> ekle")
    public void horizontalSwipeWithElement(String key, int times, int number) throws InterruptedException {

        Point elementLocation = findElementByKeyWithoutAssert(key).getLocation();
        logger.info("x==" + elementLocation.getX() + " y==" + elementLocation.getY() + "----------");

        pointToPointSwipeWithCoordinats(900, elementLocation.getY(), 40, elementLocation.getY(), times);
        logger.info("-----horizonal kaydırma işlemi tamamlandı-----");
    }

    @Step("<StartX>,<StartY> coordinatından <EndX>,<EndY> coordinatına <times> kere swipe et")
    public void pointToPointSwipeWithCoordinats(int startX, int startY, int endX, int endY, int count) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();


        for (int i = 0; i < count; i++) {
            waitBySecond(1);
            TouchAction action = new TouchAction(appiumDriver);
            action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(endX, endY))
                    .release().perform();
        }


    }

    public void pointToPointSwipeForDayAndYear(int startX, int startY, int endX, int endY, int count) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        int width = d.width;
        if (count > 200) {
            startX = (width * startX) / 100;
            startY = (height * startY) / 100;
            endX = (width * endX) / 100;
            endY = (height * endY) / 100;
            count = count - 2019;

        } else
            count--;
        for (int i = 0; i < count; i++) {
            waitBySecond(1);
            TouchAction action = new TouchAction(appiumDriver);
            action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(endX, endY))
                    .release().perform();
        }
    }

    @Step("uygulamayı yeniden başlat")
    public void restart() throws InterruptedException {
        appiumDriver.closeApp();
        appiumDriver.launchApp();
        logger.info("uygulama yeniden başlatıldı");
        waitBySecond(5);
        existClickByKey("ızınVer");

    }

    private void backPage() {
        appiumDriver.navigate().back();
    }


    private String getCapability(String text) {
        return appiumDriver.getCapabilities().getCapability(text).toString();

    }

    public boolean doesElementExistByKey(String key, int time) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        try {
            WebDriverWait elementExist = new WebDriverWait(appiumDriver, time);
            elementExist.until(ExpectedConditions.visibilityOfElementLocated(selectorInfo.getBy()));
            return true;
        } catch (Exception e) {
            logger.info(key + " aranan elementi bulamadı");
            return false;
        }

    }


    public void tapElementWithCoordinate(int x, int y) {
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(x, y)).perform();
    }

    @Step("<key> li elementin  merkezine tıkla ")
    public void tapElementWithKey(String key) {

        Point point = findElementByKey(key).getCenter();
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(point.x, point.y)).perform();
    }

    @Step("<key> li elementin x kordinatında <xInt> yüzdesi y ekseninde merkezine tıkla")
    public void tapElementCordinaateWithKey(String key, int xInt) {

        Point point = findElementByKey(key).getCenter();
        TouchAction a2 = new TouchAction(appiumDriver);
        System.out.println(point);
        a2.tap(PointOption.point(point.x*(xInt/50), point.y)).perform();
    }

    @Step("<key> li element varsa tıkla")
    public void tapElementWithKeyControl(String key) {

        logger.info("element varsa verilen tıkla ya girdi");
        MobileElement mobileElement;

        mobileElement = findElementByKeyWithoutAssert(key);

        if (mobileElement != null) {

            doesElementExistByKey(key, 3);
            findElementByKey(key).click();
            logger.info(key + "elemente tıkladı");

        }}

    @Step("<key> li element varsa tıkla <key2> tıkla")
    public void tapElementWithKeyControlLogin(String key, String key2) {

        logger.info("element varsa verilen tıkla girdi");
        MobileElement mobileElement;

        waitBySecond(2);

        mobileElement = findElementByKeyWithoutAssert(key);

        if (mobileElement != null) {

            doesElementExistByKey(key, 3);
            findElementByKey(key).click();
            findElementByKey(key2).click();
            logger.info(key + "elemente tıkladı");

        }
        else {
            System.out.println(key + " element bulunamadi");

        }
    }

    @Step("<key> li element varsa <key2> tıkla")
    public void tapElementWithKeyControlLoginClick(String key, String key2) {

        logger.info("element varsa verilen tıkla girdi");
        MobileElement mobileElement;

        waitBySecond(2);

        mobileElement = findElementByKeyWithoutAssert(key);

        if (mobileElement != null) {

            doesElementExistByKey(key, 3);
            findElementByKey(key);
            findElementByKey(key2).click();
            logger.info(key + "elemente tıkladı");

        }
        else {
            System.out.println(key + " element bulunamadi");

        }
    }

    @Step("<key> li element varsa tıkla yoksa devam et")
    public void tapElementWithKeyControlArea(String key) {

        logger.info("element varsa verilen tıkla ya girdi");
        MobileElement mobileElement;

        mobileElement = findElementByKeyWithoutAssert(key);

        if (mobileElement != null) {

            doesElementExistByKey(key, 3);
            findElementByKey(key).click();
            logger.info(key + "elemente tıkladı");

        }
        else {
            System.out.println(key + " element yuklenmedi");

        }}


    @Step("<key> li element varsa  <x> <y> koordinatına tıkla ")
    public void tapElementWithKeyCoordinate(String key, int x, int y) {

        logger.info("element varsa verilen koordinata tıkla ya girdi");
        MobileElement mobileElement;

        mobileElement = findElementByKeyWithoutAssert(key);

        if (mobileElement != null) {

            System.out.println("pakachu");
            Point point = mobileElement.getLocation();
            logger.info(point.x + "  " + point.y);
            Dimension dimension = mobileElement.getSize();
            logger.info(dimension.width + "  " + dimension.height);
            TouchAction a2 = new TouchAction(appiumDriver);
            a2.tap(PointOption.point(point.x + (dimension.width * x) / 100, point.y + (dimension.height * y) / 100)).perform();
        }
    }

    @Step("<key> li elementin  merkezine press ile çift tıkla ")
    public void pressElementWithKey(String key) {

        Point point = findElementByKey(key).getCenter();
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.press(PointOption.point(point.x, point.y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .press(PointOption.point(point.x, point.y)).release().perform();

    }


    @Step("<key> li elementin  merkezine double tıkla ")
    public void pressElementWithKey2(String key) {
        Actions actions = new Actions(appiumDriver);
        actions.moveToElement(findElementByKey(key));
        actions.doubleClick();
        actions.perform();
        appiumDriver.getKeyboard();

    }

    @Step("<key> listesinden rastgele bir elemente sayacla tikla ve <key2> bekle")
    public void chooseRandomProduct(String key,String key2) {
        List<MobileElement> list = findElemenstByKey(key);
        startTime = System.currentTimeMillis();
        list.get(createRandomNumber(list.size()-1)).click();
        finishCounter(key);
    }


    @Step("<key> li elemente kadar <text> textine sahip değilse ve <timeout> saniyede bulamazsa swipe yap")
    public void swipeAndFindwithKey(String key, String text, int timeout) {
        MobileElement sktYil1 = null;
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        WebDriverWait wait = new WebDriverWait(appiumDriver, timeout);
        int count = 0;
        while (true) {
            count++;
            try {
                sktYil1 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(selectorInfo.getBy()));
                if (text.equals("") || sktYil1.getText().trim().equals(text)) {
                    break;
                }
            } catch (Exception e) {
                logger.info("Bulamadı");

            }
            if (count == 8) {

                Assertions.fail("Element bulunamadı");
            }

            Dimension dimension = appiumDriver.manage().window().getSize();
            int startX1 = dimension.width / 2;
            int startY1 = (dimension.height * 75) / 100;
            int secondX1 = dimension.width / 2;
            int secondY1 = (dimension.height * 30) / 100;

            TouchAction action2 = new TouchAction(appiumDriver);

            action2
                    .press(PointOption.point(startX1, startY1))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(secondX1, secondY1))
                    .release()
                    .perform();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    @Step("<key>li elementi bulana kadar <limit> kere swipe yap ve elementi bul")
    public void swipeKeyy(String key, int limit) throws InterruptedException {


        boolean isAppear = false;

        int windowHeight = this.getScreenHeight();
        for (int i = 0; i < limit; ++i) {
            logger.info((i+1)+". kere swipe edilecek");
            try {

                Dimension phoneSize = appiumDriver.manage().window().getSize();
                Point elementLocation = findElementByKeyWithoutAssert(key).getLocation();
                logger.info(elementLocation.x + "  " + elementLocation.y);
                Dimension elementDimension = findElementByKeyWithoutAssert(key).getSize();
                logger.info(elementDimension.width + "  " + elementDimension.height);
                // logger.info(appiumDriver.getPageSource());
                if ((0 < elementLocation.y) && (elementLocation.y <= phoneSize.height - 30)) {
                    isAppear = true;
                    logger.info("aranan elementi buldu");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Element ekranda görülmedi. Tekrar swipe ediliyor");
            }
            System.out.println("Element ekranda görülmedi. Tekrar swipe ediliyor");

            swipeDownAccordingToPhoneSize();
            waitBySecond(1);
        }

    }

    @Step("<key>li elementi bulana kadar <limit> kere yukarı swipe yap ve elementi bul")
    public void swipeUpKeyy(String key, int limit) throws InterruptedException {


        boolean isAppear = false;

        int windowHeight = this.getScreenHeight();
        for (int i = 0; i < limit; ++i) {
            logger.info((i+1)+". kere swipe edilecek");
            try {

                Dimension phoneSize = appiumDriver.manage().window().getSize();
                Point elementLocation = findElementByKeyWithoutAssert(key).getLocation();
                logger.info(elementLocation.x + "  " + elementLocation.y);
                Dimension elementDimension = findElementByKeyWithoutAssert(key).getSize();
                logger.info(elementDimension.width + "  " + elementDimension.height);
                // logger.info(appiumDriver.getPageSource());
                if ((0 < elementLocation.y) && (elementLocation.y <= phoneSize.height - 30)) {
                    isAppear = true;
                    logger.info("aranan elementi buldu");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Element ekranda görülmedi. Tekrar swipe ediliyor");
            }
            System.out.println("Element ekranda görülmedi. Tekrar swipe ediliyor");

            swipeUpAccordingToPhoneSize();
            waitBySecond(1);
        }

    }


    @Step("<key> li  telefonun  <x> ve elementin <y> kordinatına göre tıkla")
    public void elementFindwithXandYcoordinate(String key, int x, int y) {

        WebElement element = findElementByKey(key);
        int height = element.getLocation().y + (element.getSize().height * y) / 100;
        int width = (appiumDriver.manage().window().getSize().width * x) / 100;
        System.out.println(height + "  " + width + "   ");
        TouchAction action = new TouchAction(appiumDriver);
        action.tap(PointOption.point(width, height)).perform();
    }

    @Step("<key> elementinin koordinatlarına x=<x> y=<y> degerlerini ekleyerek tıkla")
    public void coordinatClickWithAdds(String key, int x, int y) {
        MobileElement me = findElementByKey(key);
        tapElementWithCoordinate(me.getLocation().x + x, me.getLocation().y + y);
    }

    @Step("<x>,<y> koordinatlarına tıkla")
    public void koordinataTikla(int x, int y) {
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(x, y)).perform();
        logger.info("tıklama yapıldı");
    }
    @Step({"Değeri <key> e eşit olan elementli bul"})
    public void clickByTexte(String key) {
        String Sec;
        Sec = findElementByKeyWithoutAssert(key).getAttribute("checked");
        String E2 = Sec;
        if (E2.equals("false")){
            doesElementExistByKey(key, 5);
            findElementByKey(key).click();
            logger.info(key + "elemente tıkladı");
        }
        else if(E2.equals("true"))
        {
            logger.info(key + " secili gelmistir");
        }

    }
    @Step("Enter tıkla")
    public void keyboardClickEnter() {

        tapElementWithCoordinate(999,1991);
        logger.info("'%s' objesi üzerinde ENTER tuşuna basıldı.");
    }

    @Step("Android Enter tıkla")
    public void keyboardClickEnterAndroid() {
        waitBySecond(2);
        ((AndroidDriver) appiumDriver).pressKeyCode(AndroidKeyCode.ENTER);
        //tapElementWithCoordinate(1320,2700);
        logger.info("'%s' objesi üzerinde ENTER tuşuna basıldı.");
    }

    @Step("iOS Enter tıkla")
    public void keyboardClickEnteriOS() {
        waitBySecond(2);
        appiumDriver.findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField'")).sendKeys(Keys.ENTER);
        // Devam eden işlemleri burada gerçekleştirin
        logger.info("'%s' objesi üzerinde ENTER tuşuna basıldı.");
    }


    @Step("<x> elementinde <y> yilina git")
    public void clickByKeyRepeat(String key, int y) {
        y = 2022-y;
        for (int i=1; i<(y+1);i++)
        {
            doesElementExistByKey(key, 5);
            findElementByKey(key).click();
            logger.info(key + " elementine "+i+ ". kere tıkladı");
        }
    }


    @Step({"<key> Checkbox degerinin false geldigini kontrol et"})
    public void checkBoxControl(String key) {
        String Sec;
        Sec = findElementByKeyWithoutAssert(key).getAttribute("checked");
        String E2 = Sec;
        if (E2.equals("false")){
            logger.info(key + " checkbox boş olarak(false) olarak gelmiştir.");
        }
        else if(E2.equals("true"))
        {
            Boolean selected = true;
            assertFalse(selected,"Checkbox seçili(true) olarak gelmiştir.");

        }
    }


    private Long getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return (timestamp.getTime());
    }

    @Step({"<key> li elementi bul, temizle ve rasgele  email değerini yaz",
            "Find element by <key> clear and send keys  random email"})
    public void RandomeMail(String key) {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH-mm-s");
        Date date = new Date(System.currentTimeMillis());
        logger.info(formatter.format(date));

        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue("testotomasyon" + formatter.format(date) + "@beymentest.com");
    }

    @Step({"<key> li elementi bul, temizle ve <length> uzunluğunda string değer yaz",
            "Find element by <key> clear and send keys <text>"})
    public void randomStringSendKeysByKey(String key, int length) {

        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {

            int index = random.nextInt(lowerAlphabet.length());
            char randomChar = lowerAlphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();

        logger.info("'"+randomString+ "' random kelime olarak oluşturuldu");
        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue(randomString);
    }

    @Step("<key>'li elementin <attr> degerini icerdigini kontrol et")
    public void checkByAttr(String key,String attr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("***************"+dtf.format(now)+"***************");
        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        String attribute = findElementByKey(key).getAttribute(attr);
        System.out.println("Height: "+height+" - Attribute: "+attribute);

        LocalDateTime now2 = LocalDateTime.now();
        System.out.println("***************"+dtf.format(now2)+"***************");

    }

    @Step("<key> elementinin <text> textini içermediği kontrol edilir")
    public void checkTextByKeyFalse(String key, String text) {
        try {
            Thread.sleep(3000);
            String denemee = findElementByKey(key).getText();
            assertFalse(findElementByKey(key).getText().contains(text), "Element beklenen değeri taşıyor !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementinin <attr> attribute degeri ile scrool bar kontrol edilir")
    public void checkScroolBar(String key, String attr) throws InterruptedException {
        String firstAttribute = findElementByKey(key).getAttribute(attr);
        logger.info("First attribute: " + firstAttribute);
        swipe(2);
        String secondAttribute = findElementByKey(key).getAttribute(attr);
        logger.info("Second attribute: " + secondAttribute);
        assertFalse(firstAttribute.equals(secondAttribute),"Scrool bar yok!");
        swipeUP(2);
    }

    @Step({"<key> li elementin text degerini hafizada <saveKey> olarak saklanan text ile karsilastir"})
    public void chechTextByKeyAndSaveKey(String key, String saveKey) {

        System.out.println("------------------------------------------------------");
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);
        logger.info("Expected Value : "+saveElementTxt);
        String elementTxt = findElementByKey(key).getText();
        logger.info("Actual Value : "+elementTxt);
        System.out.println("------------------------------------------------------");
        assertTrue(elementTxt.contains(saveElementTxt),"Degerler birbirine esit degil!");
    }

    @Step({"<key> li elementin text degeri hafizada <saveKey> olarak saklanan text ile esit mi kontrol et"})
    public void equalsByKeyAndSaveKey(String key, String saveKey) {

        System.out.println("------------------------------------------------------");
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);
        logger.info("Expected Value : "+saveElementTxt);
        String elementTxt = findElementByKey(key).getText();
        logger.info("Actual Value : "+elementTxt);
        System.out.println("------------------------------------------------------");
        assertEquals(elementTxt, saveElementTxt, "Degerler birbirine esit degil!");
    }

    @Step({"<key> li elementin text degerini hafizada <saveKey> olarak saklanan text ayni olmadigini kontrol et"})
    public void DifferentTextByKeyAndSaveKey(String key, String saveKey) {

        System.out.println("------------------------------------------------------");
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);
        logger.info("Expected Value : "+saveElementTxt);
        String elementTxt = findElementByKey(key).getText();
        logger.info("Actual Value : "+elementTxt);
        System.out.println("------------------------------------------------------");
        assertFalse(elementTxt.contains(saveElementTxt),"Degerler birbirine esit!");
    }

    @Step({"<saveKey> olarak saklanan text degerinin uzunlugu <int> uzunlugunda mi kontrol et"})
    public void saveKeyLeght(String saveKey, int leghth) {

        System.out.println("------------------------------------------------------");
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);
        logger.info("Save Value : "+saveElementTxt);
        assertTrue(saveElementTxt.length()==leghth,"Text degerinin uzunlugu esit degil");
    }


    @Step({"<key> li elementin text degerini, saklanan <saveKey> degeriyle ile <islem>"})
    public void calculateAndSave(String key, String saveKey,String islem) {

        MobileElement element;
        element = findElementByKeyWithoutAssert(key);

        if (element!=null){
            String lastSaveKey = StoreHelper.INSTANCE.getValue(saveKey);

            lastSaveKey = lastSaveKey.replaceAll("\\s", "");
            lastSaveKey = lastSaveKey.replaceAll(",", ".");
            lastSaveKey = lastSaveKey.replaceAll("TL", "");
            Float floatLastSaveKey =Float.parseFloat(lastSaveKey);
            logger.info("Birikimli odenecek tutar : "+lastSaveKey);

            String elementTxt = findElementByKey(key).getText();

            String price = findElementByKey(key).getText();
            elementTxt = elementTxt.replaceAll("\\s", "");
            elementTxt = elementTxt.replaceAll(",", ".");
            elementTxt = elementTxt.replace(elementTxt.substring(elementTxt.length()-2), "");
            Float floatElementTxt =Float.parseFloat(elementTxt);
            logger.info(key+" : "+elementTxt);


            if (islem.equals("topla"))
            {
                floatLastSaveKey = (floatLastSaveKey*100)+(floatElementTxt*100);
                floatLastSaveKey/=100;
                logger.info("Islem sonucu : "+floatLastSaveKey);

            }
            else if (islem.equals("cikar"))
            {
                floatLastSaveKey = (floatLastSaveKey*100)-(floatElementTxt*100);
                floatLastSaveKey/=100;
                logger.info("Islem sonucu : "+floatLastSaveKey);
            }
            else{
                logger.info("Islem adi yanlis girilmiştir!");
                assertTrue(0>1);

            }

            String strValue = Float.toString(floatLastSaveKey);
            strValue = strValue.replaceAll("\\.", ",");
            StoreHelper.INSTANCE.saveValue(saveKey,strValue);
        }

        else {
            logger.info("------ Islem icin ["+key+"] li element bulunamadi ------");
        }

    }

    @Step({"<key> li elementin text degerini, saklanan <saveKey> degeriyle ile <islem>"})
    public void calculateAndSavePrices(String key, String saveKey,String islem) {

        MobileElement element;
        element = findElementByKeyWithoutAssert(key);

        if (element!=null){
            String lastSaveKey = StoreHelper.INSTANCE.getValue(saveKey);

            lastSaveKey = lastSaveKey.replaceAll("\\s", "");
            lastSaveKey = lastSaveKey.replaceAll(",", ".");
            lastSaveKey = lastSaveKey.replaceAll("TL", "");
            Float floatLastSaveKey =Float.parseFloat(lastSaveKey);
            logger.info("Birikimli odenecek tutar : "+lastSaveKey);

            String elementTxt = findElementByKey(key).getText();

            String price = findElementByKey(key).getText();
            elementTxt = elementTxt.replaceAll("\\s", "");
            elementTxt = elementTxt.replaceAll(",", ".");
            elementTxt = elementTxt.replace(elementTxt.substring(elementTxt.length()-2), "");
            Float floatElementTxt =Float.parseFloat(elementTxt);
            logger.info(key+" : "+elementTxt);


            if (islem.equals("topla"))
            {
                floatLastSaveKey = (floatLastSaveKey*100)+(floatElementTxt*100);
                floatLastSaveKey/=100;
                logger.info("Islem sonucu : "+floatLastSaveKey);

            }
            else if (islem.equals("cikar"))
            {
                floatLastSaveKey = (floatLastSaveKey*100)-(floatElementTxt*100);
                floatLastSaveKey/=100;
                logger.info("Islem sonucu : "+floatLastSaveKey);
            }
            else{
                logger.info("Islem adi yanlis girilmiştir!");
                assertTrue(0>1);

            }

            String strValue = Float.toString(floatLastSaveKey);
            strValue = strValue.replaceAll("\\.", ",");
            StoreHelper.INSTANCE.saveValue(saveKey,strValue);
        }

        else {
            logger.info("------ Islem icin ["+key+"] li element bulunamadi ------");
        }

    }

    @Step("Saklanan fiyat değerlerini <priceTwo>, <secondPriceTwo>, <priceOne>, <secondPriceOne> toplam fiyat <sum>, <sumKusur> ile eşit mi kontrol et")
    public void sumAllPrices(String priceTwo, String secondPriceTwo, String priceOne, String secondPriceOne, String sum, String sumKusur)
    {
        String priceTwos= StoreHelper.INSTANCE.getValue(priceTwo);
        String secondPriceTwos= StoreHelper.INSTANCE.getValue(secondPriceTwo);
        String priceOnes= StoreHelper.INSTANCE.getValue(priceOne);
        String secondPriceOnes= StoreHelper.INSTANCE.getValue(secondPriceOne);

        logger.info("Expected Value : "+priceTwos);
        logger.info("Expected Value : "+secondPriceTwos);
        logger.info("Expected Value : "+priceOnes);
        logger.info("Expected Value : "+secondPriceOnes);

        String sums = findElementByKey(sum).getText();
        String sumKusurs = findElementByKey(sumKusur).getText().substring(1,findElementByKey(sumKusur).getText().length());

        double toplam = (Double.parseDouble(sumKusurs)/100) + Double.parseDouble(sums);
        double count = (Double.parseDouble(priceTwos)/100) + (Double.parseDouble(secondPriceTwos)/100)
                + Double.parseDouble(priceOnes) + Double.parseDouble(secondPriceOnes);

        logger.info("Expected Value : "+count);
        logger.info("Actual Value : "+toplam);

        assertEquals(toplam,count,"Degerler birbirine esit degil");
    }

    @Step("Saklanan tek ürün fiyat değerlerini <priceTwo>, <priceOne> toplam fiyat <sum>, <sumKusur> ile eşit mi kontrol et")
    public void sumAllPrices(String priceTwo, String priceOne, String sum, String sumKusur)
    {
        String priceTwos= StoreHelper.INSTANCE.getValue(priceTwo);
        String priceOnes= StoreHelper.INSTANCE.getValue(priceOne);

        logger.info("Expected Value : "+priceTwos);
        logger.info("Expected Value : "+priceOnes);

        String sums = findElementByKey(sum).getText();
        String sumKusurs = findElementByKey(sumKusur).getText().substring(1,findElementByKey(sumKusur).getText().length());

        double toplam = (Double.parseDouble(sumKusurs)/100) + Double.parseDouble(sums);
        double count = (Double.parseDouble(priceTwos)/100) + Double.parseDouble(priceOnes);

        logger.info("Expected Value : "+count);
        logger.info("Actual Value : "+toplam);

        assertEquals(toplam,count,"Degerler birbirine esit degil");
    }

    @Step("Saklanan tek ürün fiyat değerlerinin <priceTwo>, <priceOne> toplam fiyat <sum> ile eşit mi kontrol et")
    public void sumAllPricesBasket(String priceTwo, String priceOne, String sum) {
        String priceTwos = StoreHelper.INSTANCE.getValue(priceTwo);
        String priceOnes = StoreHelper.INSTANCE.getValue(priceOne);

        logger.info("Expected Value : " + priceTwos);
        logger.info("Expected Value : " + priceOnes);
        waitBySecond(1);

        double expectedSum = Double.parseDouble(sum);
        double total = (Double.parseDouble(priceTwos) / 100) + Double.parseDouble(priceOnes);

        logger.info("Expected Value : " + expectedSum);
        logger.info("Actual Value : " + total);

        assertEquals(total, expectedSum, "Degerler birbirine esit degil");
    }

    @Step("Saklanan tek ürün fiyat değerlerinin <priceTwo>, <priceOne> saklanan toplam fiyat <priceSum> ile eşit mi kontrol et")
    public void sumGetAllPricesBasket(String priceTwo, String priceOne, String priceSum) {
        String priceTwos = StoreHelper.INSTANCE.getValue(priceTwo);
        String priceOnes = StoreHelper.INSTANCE.getValue(priceOne);
        String priceSums = StoreHelper.INSTANCE.getValue(priceSum);

        logger.info("Expected Value : " + priceTwos);
        logger.info("Expected Value : " + priceOnes);
        waitBySecond(1);

        double expectedSum = Double.parseDouble(priceSums);
        double total = (Double.parseDouble(priceTwos) / 100) + Double.parseDouble(priceOnes);

        logger.info("Expected Value : " + expectedSum);
        logger.info("Actual Value : " + total);

        assertEquals(total, expectedSum, "Degerler birbirine esit degil");
    }

    @Step("Saklanan tek ürün fiyat değerlerini <priceTwo>, <priceOne> toplam fiyat <sum> ile eşit mi kontrol et")
    public void sumAllPricesIyzıco(String priceTwo, String priceOne, String sum)
    {
        String priceTwos= StoreHelper.INSTANCE.getValue(priceTwo);
        String priceOnes= StoreHelper.INSTANCE.getValue(priceOne);

        logger.info("Expected Value : "+priceTwos);
        logger.info("Expected Value : "+priceOnes);
        waitBySecond(1);
        String sumPrice = findElementByKey(sum).getText();
        waitBySecond(1);
        String sums = sumPrice.substring(0,sumPrice.length()-7);
        sums = sums.replace(",", ".");

        double toplam = Double.parseDouble(sums);
        double count = (Double.parseDouble(priceTwos)/100) + Double.parseDouble(priceOnes);

        logger.info("Expected Value : "+count);
        logger.info("Actual Value : "+toplam);

        assertEquals(toplam,count,"Degerler birbirine esit degil");
    }

    @Step("Ödenecek tutar <ödenecektutar> toplam fiyat <sum> ile eşit mi kontrol et")
    public void sumAllPricesIyzıco(String ödenecektutar, String sum) {

        double ödenecekTutar = Double.parseDouble(ödenecektutar);

        String sumPrice = findElementByKey(sum).getText();
        String sums = sumPrice.substring(0, sumPrice.length() - 7);
        sums = sums.replace(",", ".");
        double toplam = Double.parseDouble(sums);

        logger.info("Expected Value : " + ödenecekTutar);
        logger.info("Actual Value : " + toplam);

        assertEquals(toplam, ödenecekTutar, "Degerler birbirine esit degil");
    }

    @Step("Saklanan ödenecek tutar <odenecekToplamSum> toplam fiyat <sum> ile eşit mi kontrol et")
    public void sumGetAllPricesIyzıco(String odenecekToplamSum, String sum) {
        String odenecekToplamSums = StoreHelper.INSTANCE.getValue(odenecekToplamSum);
        double ödenecekTutar = Double.parseDouble(odenecekToplamSums);

        String sumPrice = findElementByKey(sum).getText();
        String sums = sumPrice.substring(0, sumPrice.length() - 7);
        sums = sums.replace(".", "");
        sums = sums.replace(",", ".");
        double toplam = Double.parseDouble(sums);

        logger.info("Expected Value : " + ödenecekTutar);
        logger.info("Actual Value : " + toplam);

        assertEquals(toplam, ödenecekTutar, "Degerler birbirine esit degil");
    }


    @Step("Saklanan fiyat değerlerini <priceTwo>, <secondPriceTwo>, <priceOne>, <secondPriceOne> toplam fiyat <sum> ile eşit mi kontrol et")
    public void sumAllPricesStr(String priceTwo, String secondPriceTwo, String priceOne, String secondPriceOne, String sum)
    {
        String priceTwos= StoreHelper.INSTANCE.getValue(priceTwo);
        String secondPriceTwos= StoreHelper.INSTANCE.getValue(secondPriceTwo);
        String priceOnes= StoreHelper.INSTANCE.getValue(priceOne);
        String secondPriceOnes= StoreHelper.INSTANCE.getValue(secondPriceOne);

        logger.info("Expected Value : "+priceTwos);
        logger.info("Expected Value : "+secondPriceTwos);
        logger.info("Expected Value : "+priceOnes);
        logger.info("Expected Value : "+secondPriceOnes);

        String sums = findElementByKey(sum).getText().substring(0,findElementByKey(sum).getText().length()-7);
        sums = sums.replace(",", ".");
        double toplam = Double.parseDouble(sums);
        double count = (Double.parseDouble(priceTwos)/100) + (Double.parseDouble(secondPriceTwos)/100)
                + Double.parseDouble(priceOnes) + Double.parseDouble(secondPriceOnes);

        logger.info("Expected Value : "+count);
        logger.info("Actual Value : "+toplam);

        assertEquals(toplam,count,"Degerler birbirine esit degil");
    }

    @Step("Saklanan fiyat değerlerini kargo indirimine bağlı <priceTwo>, <secondPriceTwo>, <priceOne>, <secondPriceOne> toplam fiyat <sum> ile eşit mi kontrol et")
    public void sumAllPricesWithShippingStr(String priceTwo, String secondPriceTwo, String priceOne, String secondPriceOne, String sum)
    {
        String priceTwos= StoreHelper.INSTANCE.getValue(priceTwo);
        String secondPriceTwos= StoreHelper.INSTANCE.getValue(secondPriceTwo);
        String priceOnes= StoreHelper.INSTANCE.getValue(priceOne);
        String secondPriceOnes= StoreHelper.INSTANCE.getValue(secondPriceOne);

        logger.info("Expected Value : "+priceTwos);
        logger.info("Expected Value : "+secondPriceTwos);
        logger.info("Expected Value : "+priceOnes);
        logger.info("Expected Value : "+secondPriceOnes);

        String sums = findElementByKey(sum).getText().substring(0,findElementByKey(sum).getText().length()-7);
        sums = sums.replace(".", "");
        sums = sums.replace(",", ".");
        double toplam = Double.parseDouble(sums);
        logger.info("toplam Value : "+toplam);
        double countOne = Double.parseDouble(priceOnes) + (Double.parseDouble(priceTwos)/100);
        logger.info("countOne Value : "+countOne);
        double countTwo = Double.parseDouble(secondPriceOnes) + (Double.parseDouble(secondPriceTwos)/100);
        logger.info("countTwo Value : "+countTwo);
        double count = 0;
        double indirim = 350;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String strcountOne = decimalFormat.format(countOne);
        String strcountTwo = decimalFormat.format(countTwo);

        strcountOne = strcountOne.replace(",", ".");
        strcountTwo = strcountTwo.replace(",", ".");

        double countOnes = Double.parseDouble(strcountOne);
        double countTwos = Double.parseDouble(strcountTwo);

        if(countOnes >= indirim)
            count = countOnes;
        else
            count =  countOnes + countTwos;

        String strcount = decimalFormat.format(count);
        strcount = strcount.replace(",", ".");
        double counts = Double.parseDouble(strcount);


        logger.info("Expected Value : "+counts);
        logger.info("Actual Value : "+toplam);

        assertEquals(toplam,counts,"Degerler birbirine esit degil");
    }

    @Step("Sipariste bulunan urunlerin toplam fiyati <key> degeri ile kontrol edilirerek <saveKey> ile saklanir")
    public void pointToPointSwipeWithCoordinatsForProduct(String key, String saveKey) throws InterruptedException {
        String strCount = appiumDriver.findElement(By.id("com.mobisoft.beymen:id/tvOrderStatus")).getText();
        strCount = strCount.substring(strCount.indexOf(":")+1);
        strCount = strCount.substring(1, 3);
        strCount = strCount.replaceAll("\\s", "");
        int count =Integer.parseInt(strCount);
        logger.info("Siparis verilmiş ürün sayisi : " + count);


        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point(0, 375))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(0, 45))
                .release().perform();


        int startY=520;
        int endY=45;
        float result=0;
        Dimension d = appiumDriver.manage().window().getSize();

        for (int i = 0; i < count; i++) {
            waitBySecond(1);

            String price = findElementByKey(key).getText();
            price = price.replaceAll("\\s", "");
            price = price.replaceAll(",", ".");
            price = price.replace(price.substring(price.length()-2), "");
            Float itemPrice =Float.parseFloat(price);
            result = (result*100)+(itemPrice*100);
            result/=100;
            String strResult = Float.toString(result);
            strResult = strResult.replaceAll("\\.", ",");
            logger.info((i+1)+". urun degeri : " +itemPrice);
            StoreHelper.INSTANCE.saveValue(saveKey,strResult);


            action.press(PointOption.point(0, startY))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(0, endY))
                    .release().perform();


        }
        logger.info("Urunlerin toplam degeri : " +StoreHelper.INSTANCE.getValue(saveKey));
    }


    @Step({"<text> text degerine sahip elementin sayfada gorundugu kontrol edilir"})
    public void ifExistCompare(String text) throws InterruptedException {
        waitBySecond(1);
        String key = "(//*[contains(@text,'"+text+"')])";
        assertTrue(appiumDriver.findElement(By.xpath(key)).isDisplayed(), "Element sayfada bulunamadı !");
        logger.info(key+" elementi sayfada görüntülendiği kontrol edildi");
    }

    @Step({"<firstKey> li tarihin,<secondKey> li tarihten yeni oldugu kontrol edilir"})
    public void ifExistCompareeee(String firstKey,String secondKey) throws InterruptedException, ParseException {

        String firstDate = findElementByKey(firstKey).getText();
        String secondDate = findElementByKey(secondKey).getText();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date date1 = sdf.parse(firstDate);
        Date date2 = sdf.parse(secondDate);

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        int result = date1.compareTo(date2);

        if (result == 0) {
            logger.info("Iki tarih birbirine esittir [" + firstDate+"]["+ secondDate+"]");
        } else if (result > 0) {
            logger.info("Iki tarih arasindan yeni olan : ["+firstDate+"]");

        } else if (result < 0) {
            logger.info("Iki tarih arasindan yeni olan : ["+secondDate+"]");

        } else {
            logger.info("Tarih kiyaslamada problem cikti!");
        }
        assertTrue((result>0 )|| (result==0),"Urunlerin tarih siralamasi dogru değil!");


    }

    @Step({"<saveKey> olarak hafizada saklanan degeri <key> elementine yaz"})
    public void getTextAndWriteForKey(String saveKey, String key){
        String saveElementTxt= StoreHelper.INSTANCE.getValue(saveKey);
        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue(saveElementTxt);
    }

    @Step({"<element> li elementi yukari kaydir"})
    public void swipeBirtdayUp(String key){

        String attribute = findElementByKey(key).getAttribute("bounds");

        String allString[] = attribute.split("]");
        String firstPart = allString[0].substring(1);
        String secondPart = allString[1].substring(1);
        String start[] = firstPart.split(",");
        String stringStartWidth = start[0];
        String stringStartHeight = start[1];
        String end[] = secondPart.split(",");
        String stringEndWidth = end[0];
        String stringEndHeight = end[1];
        int startWidth = Integer.parseInt(stringStartWidth);
        int startHeight = Integer.parseInt(stringEndHeight);
        int endWidth = Integer.parseInt(stringEndWidth);
        int endHeight = Integer.parseInt(stringEndHeight);
        int width = (startWidth+endWidth)/2;

        new TouchAction(appiumDriver)
                .press(PointOption.point(width, endHeight-90))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(width, startHeight+90))
                .release()
                .perform();
    }

    @Step({"<element> li elementi asagi kaydir"})
    public void swipeBirtdayDown(String key){

        String attribute = findElementByKey(key).getAttribute("bounds");

        String allString[] = attribute.split("]");
        String firstPart = allString[0].substring(1);
        String secondPart = allString[1].substring(1);
        String start[] = firstPart.split(",");
        String stringStartWidth = start[0];
        String stringStartHeight = start[1];
        String end[] = secondPart.split(",");
        String stringEndWidth = end[0];
        String stringEndHeight = end[1];
        int startWidth = Integer.parseInt(stringStartWidth);
        int startHeight = Integer.parseInt(stringEndHeight);
        int endWidth = Integer.parseInt(stringEndWidth);
        int endHeight = Integer.parseInt(stringEndHeight);
        int width = (startWidth+endWidth)/2;

        new TouchAction(appiumDriver)
                .press(PointOption.point(width,startHeight+90 ))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(width,endHeight-90 ))
                .release()
                .perform();
    }

    @Step({"Klavyede arama tusuna bas"})
    public void enterAndroidKeyboard() {
        try {

            if (localAndroid == false) {
                Actions action = new Actions(appiumDriver);
                action.sendKeys(Keys.ENTER).perform();
            }
            else {
                Actions action = new Actions(appiumDriver);
                action.sendKeys(Keys.ENTER).perform();
            }
        } catch (Exception ex) {
            logger.error("Klavye üzerinden arama başarısız "+ex.getMessage());
        }
    }

    @Step({"Favori ürünler silinir"})
    public void deleteFavElement() {

        try {

            Boolean dongu = true;

            while (dongu) {
                List<MobileElement> elements = findElemenstByKeyWithoutAssert("FAVORI_ELEMENT_SIL");
                int elementsSize = elements.size();
                System.out.println("Element size : " + elementsSize);
                for (int i = 0; i < elementsSize; i++) {
                    clickByKey("FAVORI_ELEMENT_SIL");
                    System.out.println("i : "+i);
                }
            }
        }catch (Exception e){
            logger.info("Tüm elementler silindi");
        }

    }

    @Step({"Sepetteki ürünler silinir"})
    public void deleteBasketElement() {

        try {

            Boolean dongu = true;

            while (dongu) {
                List<MobileElement> elements = findElemenstByKeyWithoutAssert("SEPETIM_ELEMENT_SIL");
                int elementsSize = elements.size();
                System.out.println("Element size : " + elementsSize);
                for (int i = 0; i < elementsSize; i++) {
                    clickByKey("SEPETIM_ELEMENT_SIL");
                    System.out.println("i : "+i);
                }
            }
        }catch (Exception e){
            logger.info("Tüm elementler silindi");
        }
    }

    @Step({"Urunlerin fotolari kontol edilir"})
    public void controlImageElement() {

        List<MobileElement> elements = findElemenstByKey("URUN_FOTO_SLIDER_BULLET");
        int elementsSize = elements.size();
        System.out.println("Element size : " + elementsSize);

        for (int i = 0; i < (elementsSize-1); i++) {
            waitBySecond(1);
            System.out.println("i : "+i);
            existElement("URUN_FOTO");
            elements.get(i+1).click();
        }

        for (int i = 0; i < (elementsSize-1); i++) {

            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = (width * 15) / 100;
            int swipeEndWidth = (width * 90) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }

    }

    @Step({"<times> kere sağa kaydirilir"})
    public void swipeRight(int time) {



        for (int i = 0; i < time; i++) {

            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = (width * 90) / 100;
            int swipeEndWidth = (width * 15) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }


    }

    @Step({"<times> kere sola kaydirilir"})
    public void swipeLeft(int time) {



        for (int i = 0; i < time; i++) {

            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = (width * 15) / 100;
            int swipeEndWidth = (width * 90) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }


    }

    @Step({"Fiyatların yazdığı <key1> li elementin degerinin fiyata gore azalan oldugu kontrol edilir"})
    public void compareTwoIntValue(String key1) throws InterruptedException {

        long firstValue = 0;
        long secondValue = 0;

        while (firstValue==secondValue)
        { String firstKeyTxt = findElementByKey(key1).getText();
            firstKeyTxt = firstKeyTxt.replaceAll("\\s", "");
            //firtKeyTxt = firtKeyTxt.replace(firtKeyTxt.substring(firtKeyTxt.length()-2), "");
            //firtKeyTxt = firtKeyTxt.replaceAll(",", "");
            //firtKeyTxt = firtKeyTxt.replaceAll("\\.", "");
            firstValue =Long.parseLong(firstKeyTxt);
            logger.info("1.elementinin degeri : "+firstValue);

            swipe(3);

            String secondKeyText = findElementByKey(key1).getText();
            secondKeyText = secondKeyText.replaceAll("\\s", "");
            //secondKeyText = secondKeyText.replace(secondKeyText.substring(secondKeyText.length()-2), "");
            //secondKeyText = secondKeyText.replaceAll(",", "");
            //secondKeyText = secondKeyText.replaceAll("\\.", "");
            secondValue =Long.parseLong(secondKeyText);
            logger.info("2.elementinin degeri : "+secondValue);

            if (firstValue!=secondValue)
                break;

            swipe(1);
        }
        assertTrue(firstValue>secondValue,firstValue+" elementinin degeri"+ secondValue+" elementinin degerinden kucuk");

    }

    @Step({"Fiyatların yazdığı <key1> li elementin degerinin fiyata gore artan oldugu kontrol edilir"})
    public void compareTwoIntValuee(String key1) throws InterruptedException {

        long firstValue = 0;
        long secondValue = 0;

        while (firstValue==secondValue)
        { String firtKeyTxt = null;
            while (true)
            {
                swipeDownAccordingToPhoneSize();
                swipeDownAccordingToPhoneSize();
                try {
                    firtKeyTxt = findElementByKeyWithoutAssert(key1).getText();
                }
                catch (Exception e)
                {
                    System.out.println("Fiyat bulunamadı, tekrar swipe ediliyor!");
                }
                if(firtKeyTxt != null)
                { break;}
            }
            firtKeyTxt = firtKeyTxt.replaceAll("\\s", "");
            //firtKeyTxt = firtKeyTxt.replace(firtKeyTxt.substring(firtKeyTxt.length()-2), "");
            //firtKeyTxt = firtKeyTxt.replaceAll(",", "");
            //firtKeyTxt = firtKeyTxt.replaceAll("\\.", "");
            firstValue =Long.parseLong(firtKeyTxt);
            logger.info(key1+" elementinin degeri : "+firstValue);

            //swipeDownAccordingToPhoneSize();
            swipeDownAccordingToPhoneSize();
            swipeDownAccordingToPhoneSize();
            waitBySecond(1);

            String secondKeyText = null;

            while (true)
            {
                swipeDownAccordingToPhoneSize();
                swipeDownAccordingToPhoneSize();
                try {
                    secondKeyText = findElementByKeyWithoutAssert(key1).getText();
                }
                catch (Exception e)
                {
                    System.out.println("Fiyat bulunamadı, tekrar swipe ediliyor!");
                }
                if(secondKeyText != null)
                {break;}
            }


            secondKeyText = secondKeyText.replaceAll("\\s", "");
            //secondKeyText = secondKeyText.replace(secondKeyText.substring(secondKeyText.length()-2), "");
            //secondKeyText = secondKeyText.replaceAll(",", "");
            //secondKeyText = secondKeyText.replaceAll("\\.", "");
            secondValue =Long.parseLong(secondKeyText);
            logger.info(key1+" elementinin degeri : "+secondValue);

            if (firstValue!=secondValue)
                break;

            swipeDownAccordingToPhoneSize();
            waitBySecond(1);
        }
        assertTrue(secondValue>firstValue,secondValue+" elementinin degeri"+ firstValue+" elementinin degerinden büyük");

    }


    @Step({"<key> elementine dogru <element> elementini kaydir"})
    public void sliderElementiniKontrolEt(String key, String element){

        waitBySecond(2);

        Dimension dKey = findElementByKey(key).getSize();
        logger.info(key+" Y point "+ dKey.height);

        Point elementPoint = findElementByKey(element).getLocation();
        String elementY = String.valueOf(elementPoint.y);
        logger.info(element+" Y point " + elementY);
        int elementBottomY = Integer.parseInt(elementY);

        Dimension d = appiumDriver.manage().window().getSize();
        int width = d.width;
        int swipeStartWidth = width / 2, swipeEndWidth = width / 2;


        new TouchAction(appiumDriver)
                .press(PointOption.point(swipeStartWidth, elementBottomY))
                .waitAction(WaitOptions.waitOptions(ofMillis(3000)))
                .moveTo(PointOption.point(swipeEndWidth, dKey.height))
                .release()
                .perform();

    }

    @Step("<key> li  elementin x ekseninde merkezine, y ekseninde yukaridan %<int> deger kadar asagisina tikla")
    public void clickCenterXandIntY(String key, int x) {

        WebElement element = findElementByKey(key);
        int height = element.getLocation().y + (element.getSize().height)/ 100;
        int height2 = element.getLocation().y + (element.getSize().height*x)/ 100;
        int width = (appiumDriver.manage().window().getSize().width)/2;
        logger.info("Elementin height degeri : "+height+", Tiklanacak height degeri"+height2);
        logger.info("Elementin width degeri : "+width+", Tiklanacak width degeri"+width);
        TouchAction action = new TouchAction(appiumDriver);
        action.tap(PointOption.point(width, height2)).perform();
    }

    @Step("<key> li  elementin x ekseninde yukaridan %<int> deger, y ekseninde yukaridan %<int> deger kadar asagisina tikla")
    public void clickIntXandIntY(String key, int x, int y) {

        WebElement element = findElementByKey(key);
        int height = element.getLocation().y + (element.getSize().height)/ 100;
        int height2 = element.getLocation().y + (element.getSize().height*y)/ 100;
        int width = element.getLocation().x + (element.getSize().width) / 100;
        int width2 = element.getLocation().x + (element.getSize().width*x) / 100;
        logger.info("Elementin height degeri : "+height+", Tiklanacak height degeri"+height2);
        logger.info("Elementin width degeri : "+width+", Tiklanacak width degeri"+width2);
        TouchAction action = new TouchAction(appiumDriver);
        action.tap(PointOption.point(width2, height2)).perform();
    }

    @Step("<String> alt kategorisinin goruntulendigi kontrol edilir")
    public void findCategory(String key) {

        String element = "//XCUIElementTypeStaticText[@name=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element));
        logger.info(key +" elementinin sayfa uzerinde goruntulendigi kontrol edilir");

    }

    @Step("<String> title degerinin goruntulendigi kontrol edilir")
    public void findTitleText(String key) {

        String element = "//XCUIElementTypeStaticText[@name=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element));
        logger.info(key +" elementinin sayfa uzerinde goruntulendigi kontrol edilir");

    }

    @Step("<String> text degerine sahip elementin goruntulendigi kontrol edilir")
    public void findTextXpath(String key) {

        String element = "//XCUIElementTypeStaticText[@name=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element));
        logger.info(key +" text degerine sahip elementin goruntulendigi kontrol edildi");
    }

    @Step("<String> text degerine sahip elemente tikla")
    public void clickWithText(String key) {

        String element = "//XCUIElementTypeStaticText[@name=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element)).click();
        logger.info(key +" text degerine sahip elemente tiklandi");
    }

    @Step("<String> text degerine sahip elemente tikla.")
    public void clickWithTextAnd(String key) {

        String element = "//android.widget.TextView[@text=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element)).click();
        logger.info(key +" text degerine sahip elemente tiklandi");
    }

    @Step("<String> text degerine sahip butonun goruntulendigi kontrol edilir")
    public void findButtonWithText(String key) {

        String element = "//XCUIElementTypeButton[@name=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element));
        logger.info(key +" text degerine sahip buton goruntulendi");
    }

    @Step("<String> text degerine sahip butona tikla")
    public void clickButtonWithText(String key) {

        String element = "//XCUIElementTypeButton[@name=\""+key+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element)).click();
        logger.info(key +" text degerine sahip elemente tiklandi");
    }


    @Step("<key> ismiyle hafizada text degeri sakli elementin sayfada gorundugu kontrol edilir")
    public void clickButtonWithTextt(String key) {

        String saveElementTxt= StoreHelper.INSTANCE.getValue(key);

        String element = "//XCUIElementTypeStaticText[@name=\""+saveElementTxt+"\"]";
        appiumDriver.findElement(MobileBy.xpath(element));
        logger.info(saveElementTxt +" text degerine sahip elementin goruntulendigi kontrol edildi");

    }
    @Step("<key> ismiyle saklanan fiyat degerinin <key2> ismiyle saklanan degerden buyuk oldugu kontrol edilir")
    public void compareSavedTextForIntValue(String key,String key2) {



        String saveElement1Txt= StoreHelper.INSTANCE.getValue(key);
        String saveElement2Txt= StoreHelper.INSTANCE.getValue(key2);

        String element1txt = saveElement1Txt.substring(0,saveElement1Txt.length()-3);
        String element2txt = saveElement2Txt.substring(0,saveElement2Txt.length()-3);

        element1txt = element1txt.replace(",","");
        element2txt = element2txt.replace(",","");

        int price1 = Integer.parseInt(element1txt.replace(".",""));
        int price2 = Integer.parseInt(element2txt.replace(".",""));

        logger.info(key+" degerine ait text : ["+price1+"]");
        logger.info(key2+" degerine ait text : ["+price2+"]");

//        boolean compareResult = price1 > price2;

        assertTrue(price1>price2);
    }
    @Step("<key> ismiyle saklanan indirim yüzde degerinin <key2> ismiyle saklanan degerden buyuk oldugu kontrol edilir")
    public void compareSavedTextForPertanceValue(String key,String key2) {



        String saveElement1Txt= StoreHelper.INSTANCE.getValue(key);
        String saveElement2Txt= StoreHelper.INSTANCE.getValue(key2);

        saveElement1Txt = saveElement1Txt.replace("-","");
        saveElement2Txt = saveElement2Txt.replace("-","");

        int price1 = Integer.parseInt(saveElement1Txt.replace("%",""));
        int price2 = Integer.parseInt(saveElement2Txt.replace("%",""));

        logger.info(key+" degerine ait text : ["+price1+"]");
        logger.info(key2+" degerine ait text : ["+price2+"]");

        boolean compareResult = price1 > price2;

        assertTrue(compareResult);
    }

    @Step({"IOS icin login ise logout olunur"})
    public void ifLoginDoLogout() throws InterruptedException {
        MobileElement element;
        element = findElementByKeyWithoutAssert("digerProfilimSekmesi");
        if (element != null) {
            swipe(1);
            waitBySecond(5);
            System.out.println("Cikis yapilacak");
            clickByKeyWithCounter("cikisYapBtnn","cikisYapPopUpCikisBtn");
            waitBySecond(3);
            clickByKey("cikisYapPopUpCikisBtn");
            swipeUP(1);
        }
        System.out.println("Uygulama logout durumda");
    }

    @Step({"login ise logout olunur And"})
    public void ifLoginDoLogoutAndroid() throws InterruptedException {
        MobileElement element;
        waitBySecond(2);
        element = findElementByKeyWithoutAssert("girisYapBtn");
        //element = findElementByKeyWithoutAssert("girisYapBtn");
        if (element == null) {
            System.out.println("buraya girdi");
            existElement("loginMailText");
            swipe(1);
            waitBySecond(2);
            //findTextXpath("Çıkış");
            //clickWithText("Çıkış");
            clickByKey("cikisYapBtn");
            waitBySecond(2);
            existElement("popUpCikisBtn");
            clickByKey("popUpCikisBtn");
        }
        waitBySecond(4);
    }

    @Step({"logout ise <email> ve <sifre> ile login olunur Android"})
    public void ifLogoutDoLoginAndroid(String mail,String sifre) throws InterruptedException {
        MobileElement element;
        waitBySecond(2);
        element = findElementByKeyWithoutAssert("cikisYapBtn");
        if (element == null) {
            logger.info("****** Logout durumda ******");
            existElement("girisYapBtn");
            clickByKey("girisYapBtn");
            existElement("andEpostaGirisInput");
            sendKeysByKeyNotClear(mail,"andEpostaGirisInput");
            sendKeysByKeyNotClear(sifre,"andGirisYapSifreInputArea");
            clickByKey("andLoginBtn");
        }
        waitBySecond(2);
        logger.info("****** Basarili sekilde login olundu ******");

        //Doğru hesap kontrolu yapilmasi icin
        MobileElement element2;
        element2 = findElementByKeyWithoutAssert("andLoginMailText");

        if (element2!=null){

            if (!Objects.equals(findElementByKey("andLoginMailText").getText(), mail)){
                logger.info("****** Yanlis hepap ile login olunmus ******");
                waitBySecond(2);
                swipe(1);
                waitBySecond(2);
                clickByKey("cikisYapBtn");
                waitBySecond(2);
                existElement("popUpCikisBtn");
                clickByKey("popUpCikisBtn");
                waitBySecond(2);
                existElement("profilSekmesi");
                clickByKey("profilSekmesi");
                existElement("girisYapBtn");
                clickByKey("girisYapBtn");
                existElement("andEpostaGirisInput");
                sendKeysByKeyNotClear(mail,"andEpostaGirisInput");
                sendKeysByKeyNotClear(sifre,"andGirisYapSifreInputArea");
                clickByKey("andLoginBtn");
                logger.info("****** Dogru hesapla login olundu ******");
                waitBySecond(3);
                existElement("andLoginMailText");
            }
        }

    }


    @Step({"logout ise <email> ve <sifre> ile login olunur"})
    public void ifLogoutDoLogin(String mail,String sifre) throws InterruptedException {
        MobileElement element;
        element = findElementByKeyWithoutAssert("loginSiparislerim");
        if (element == null) {
            logger.info("****** Logout durumda ******");
            existElement("girisYapBtn");
            clickByKey("girisYapBtn");
            existElement("girisYapEpostaInputArea");
            sendKeysByKeyNotClear(mail,"girisYapEpostaInputArea");
            sendKeysByKeyNotClear(sifre,"girisYapSifreInputArea");
            clickByKey("loginBtn");
        }
        waitBySecond(2);
        logger.info("****** Basarili sekilde login olundu ******");

        //Doğru hesap kontrolu yapilmasi icin

        if (!Objects.equals(findElementByKey("loginMailText").getText(), mail)){
            logger.info("****** Yanlis hepap ile login olunmus ******");
            waitBySecond(2);
            swipe(1);
            waitBySecond(2);
            findTextXpath("Çıkış");
            clickWithText("Çıkış");
            waitBySecond(2);
            existElement("popUpCikisBtn");
            clickByKey("popUpCikisBtn");
            waitBySecond(2);
            existElement("digerSekmesi");
            clickByKey("digerSekmesi");
            existElement("girisYapBtn");
            clickByKey("girisYapBtn");
            existElement("girisYapEpostaInputArea");
            sendKeysByKeyNotClear(mail,"girisYapEpostaInputArea");
            sendKeysByKeyNotClear(sifre,"girisYapSifreInputArea");
            clickByKey("loginBtn");
            logger.info("****** Dogru hesapla login olundu ******");
            waitBySecond(2);
            existElement("loginSiparislerim");
        }
    }

    @Step("<key> elementli urunler sepetten silinir")
    public void swipeFromLeftToRightOnCenterY(String key) throws InterruptedException {

        logger.info("Sepet urun kontrolleri yapilacak");

//        List denemee =findElemenstByKeyWithoutAssert(key);


        while (findElementByKeyWithoutAssert(key)!=null){

            logger.info("Sepette urun bulundu");
            waitBySecond(1);
            Dimension elementSize = findElementByKey(key).getSize();

            String yElementSize = String.valueOf(elementSize.height);
            String xElementSize = String.valueOf(elementSize.width);
            int elementY = Integer.parseInt(yElementSize);
            int elementX = Integer.parseInt(xElementSize);

            Point elementPoint = findElementByKey(key).getLocation();
            String elementStartPointY = String.valueOf(elementPoint.y);
            String elementStartPointX = String.valueOf(elementPoint.x);
            int elementBottomY = Integer.parseInt(elementStartPointY);
            int elementBottomX = Integer.parseInt(elementStartPointX);

            System.out.println("Başlangıç noktası X : " + elementBottomX);
            System.out.println("Başlangıç noktası Y : " + (elementBottomY + (elementY / 2)));
            System.out.println("Bitiş noktası X : " + elementX);
            System.out.println("Bitiş noktası Y : " + (elementBottomY + (elementY / 2)));
            System.out.println("Y1 : " + elementBottomY);
            System.out.println("Y2 : " + elementY);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(elementX, (elementBottomY + (elementY / 2))))
                    .waitAction(WaitOptions.waitOptions(ofMillis(3000)))
                    .moveTo(PointOption.point(elementBottomX, (elementBottomY + (elementY / 2))))
                    .release()
                    .perform();
        }
        logger.info("Sepette bulunan urun silindi");
        waitBySecond(1);

    }


    @Step({"<key> elementine var oldukca tiklanir"})
    public void ifLogoutDoLogin(String key) throws InterruptedException {
        MobileElement element;
        waitBySecond(1);
        element = findElementByKeyWithoutAssert(key);
        while (element != null) {
            int i=0;
            logger.info(i+". element bulundu");

            clickByKey(key);

            waitBySecond(2);

            element = findElementByKeyWithoutAssert(key);
            waitBySecond(2);
        }
    }

    @Step({"Android klavye kapatılır"})
    public void closeKeyboard() {
        appiumDriver.hideKeyboard();
    }

}
























