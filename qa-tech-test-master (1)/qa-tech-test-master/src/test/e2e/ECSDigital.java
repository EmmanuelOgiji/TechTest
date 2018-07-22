

import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class ECSDigital {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();


  public int[] Get_Rows(String path){
	    String [] Row_in = new String [9];
	    int [] Row_out = new int [9];
	    List <WebElement> WebRow = driver.findElements(By.xpath(path));
	    String Row_hold = null;
	    for (int i=0; i<WebRow.size(); i++){
	    Row_hold = WebRow.get(i).getText();
	    Row_in[i] = Row_hold;
	    try
	    {
	      Row_out[i] = Integer.parseInt(Row_in[i].trim());
	    }
	    catch (NumberFormatException nfe)
	    {
	      System.out.println("NumberFormatException: " + nfe.getMessage());
	    }
	    }
	    return Row_out;
  }
  
  public String Solve_Row(int[]Row){
	  	String solution;
	    int end = Row.length - 1;
        int start = 0;
        int Leftsum = Row[start];
        int Rightsum = Row[end];
        while (true) {
        	if(start<end){
	            if (Leftsum > Rightsum) {
	                Rightsum = Rightsum +Row[--end];
	            } else if (Rightsum > Leftsum){
	                Leftsum = Leftsum + Row[++start];
	            }
	            else if (Leftsum == Rightsum){
	    	        solution = String.valueOf(start+1);
	    	        break;
	            }
        	}
            else {
            		solution = "null";
            	break;
            }
        }
        return solution;
  }
  
  public void Answer(String solution, int box){
	  String [] paths = {"//input[@data-test-id='submit-1']","//input[@data-test-id='submit-2']","//input[@data-test-id='submit-3']","//input[@data-test-id='submit-4']"};
	  WebElement answerbox = null;
	  if (box == 1){
		  answerbox = driver.findElement(By.xpath(paths[box-1]));
		  answerbox.click();
	      answerbox.clear();
	      answerbox.sendKeys(solution);
	      answerbox.sendKeys(Keys.ENTER);
	  }
	  else if (box==2){
		  answerbox = driver.findElement(By.xpath(paths[box-1]));
		  answerbox.click();
	      answerbox.clear();
	      answerbox.sendKeys(solution);
	      answerbox.sendKeys(Keys.ENTER);
	  }
	  else if (box==3){
		  answerbox = driver.findElement(By.xpath(paths[box-1]));
		  answerbox.click();
	      answerbox.clear();
	      answerbox.sendKeys(solution);
	      answerbox.sendKeys(Keys.ENTER);
	  }
	  else if (box==4){
		  answerbox = driver.findElement(By.xpath(paths[box-1]));
		  answerbox.click();
	      answerbox.clear();
	      answerbox.sendKeys(solution);
	      answerbox.sendKeys(Keys.ENTER);
	  }
	  
	  
  }
  
@SuppressWarnings("deprecation")
@Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\user\\Downloads\\geckodriver-v0.21.0-win64\\geckodriver.exe");
	  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver(capabilities);
		driver.get("https://www.katalon.com/");
	  
		   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		  }

		@Test
		  public void testECSDigital() throws Exception {
		    driver.get("http://localhost:3000/");
		    driver.findElement(By.xpath("//button[@type='button']")).click();
		    int [] Row_i = new int [9];
		    int [] Row_ii = new int [9];
		    int [] Row_iii = new int [9];
		    String solution1;
		    String solution2;
		    String solution3;
		    String solution4;
		    
		    //Get contents of Rows
		    Row_i = Get_Rows("//section[@id='challenge']/div/div/div/div/div[2]/table/tbody/tr[1]/td");
		    Row_ii = Get_Rows("//section[@id='challenge']/div/div/div/div/div[2]/table/tbody/tr[2]/td");
		    Row_iii = Get_Rows("//section[@id='challenge']/div/div/div/div/div[2]/table/tbody/tr[3]/td");
		    
		    //Find solution of Rows
		    solution1 = Solve_Row(Row_i);
		    solution2 = Solve_Row(Row_ii);
		    solution3 = Solve_Row(Row_iii);
		    solution4 = "Emmanuel Pius-Ogiji";
		    
		    //Enter Answers and Name
		    Answer(solution1,1);
		    Answer(solution2,2);
		    Answer(solution3,3);
		    Answer(solution4,4);
		    
		    //Submit Answers
		    WebElement submit = driver.findElement(By.xpath("(//button[@type='button'])[2]"));
		    submit.click();
		  }
		  

		  @After
		  public void tearDown() throws Exception {
		    driver.quit();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		  }

		  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

		  private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
		}