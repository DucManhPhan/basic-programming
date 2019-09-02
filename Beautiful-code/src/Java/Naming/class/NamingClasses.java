import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Random;

// Assuming that we have two class in our source code:
// - Client.java
// - CommonUtils.java

// With the name Client.java, it's noun, short and simple. But is it specific? 
// What kind of a client? 
// Is a human customer of a business?

// Next, go to the ClientDemo.java class, we have:
public class ClientDemo {
  public static void main(String[] args) {
    HttpResponse r = new Client.send("http:api.github.com");
  }
}

// Here, Client send something to a URL and receives a response.
// Look the content of Client class, we find that it send Http requests. So, it is not a human client.

public class Client {
  private HttpClient client = getDefaultClient();

  public HttpResponse send(String s) throws IOException {
    return client.execute(new HttpGet(s));
  }

  private ClosableHttpClient getDefaultClient() {
    return HttpClientBuilder.create().build();
  }
}

// It's a program client that knows how to talk to a web service.
// So, we need to change the name of Client class to a better name such as WebHttpClient.java class.


// CommonUtils's name is not specific at all. It probably has a whole bunch of unrelated things.
// Look at inside CommonUtils class.

public class CommonUtils {

  public static int countOccurrences(String stringToSearch, char charToFind) {
    int count = 0;
    for (int i = 0; i < stringToSearch.length(); ++i) {
      if (stringToSearch.charAt(i) == charToFind) {
        count++;
      }
    }

    return count;
  }

  public static void printNowNewYorkTime() {
    String now = Instant.now().atZone(ZoneId.of("American/New_York")).toString();
    System.out.println(now);
  }

  public static int generateRandomNumberBetween(int low, int high) {
    return new Random().nextInt(high - low) + low;
  }
}

// CommonUtils class contains generic helper methods that aren't related
// to a specific business logic.
// There is one that searches and count a specific character inside a string - countOccurrences() method
// There is one that prints the current time in New York - printNowNewYorkTime() method,
// and yet another one that generate random numbers within a specific range - generateRandomNumberBetween() method.

// So, we have to change the name of this class into StringAndTimeAndNumberUtils which is rather ugly.
// Our solution here is to simply split the class into smaller classes.
// There are:
// - StringUtils.java
// - NumberUtils.java
// - TimeUtils.java

// Wrapping up
// To fix classes with bad names, we use two technique:
// - split
// - rename

// Simple, but more our code a lot clearer.
