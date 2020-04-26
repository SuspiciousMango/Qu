import java.util.Comparator;

public class AccountComparator  implements Comparator<Account> {
    public int compare(Account obj1, Account obj2) {
      if (obj1 == obj2) {
          return 0;
      }
      else if (obj1 == null) {
          return -1;
      }
      else if (obj2 == null) {
          return 1;
      }
      return obj1.getUsername().compareTo(obj2.getUsername());
    }
    }
