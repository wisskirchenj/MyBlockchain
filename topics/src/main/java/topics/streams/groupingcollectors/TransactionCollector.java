package topics.streams.groupingcollectors;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

/**
 * The total sum of transaction by each account
 * You have two classes:
 * Account: number: String, balance: Long
 * Transaction: uuid: String, sum: Long, account: Account
 *
 * Both classes have getters for all fields with the corresponding names (getNumber(), getSum(), getAccount() and so on).
 * Write a collector that calculates the total sum of transactions (long type, not integer) by each account
 * (i.e. by account number). The collector will be applied to a stream of transactions.
 */
class TransactionCollector {

    public static Map<String, Long> getAccount2TransSum(List<Transaction> trans) {
        return trans.stream()
                .collect(groupingBy(tr -> tr.getAccount().getNumber(),
                        summingLong(Transaction::getSum)));
    }

    static class Transaction {

        private final String uuid;
        private final Long sum;
        private final Account account;

        public Transaction(String uuid, Long sum, Account account) {
            this.uuid = uuid;
            this.sum = sum;
            this.account = account;
        }

        public String getUuid() {
            return uuid;
        }

        public Long getSum() {
            return sum;
        }

        public Account getAccount() {
            return account;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "uuid='" + uuid + '\'' +
                    ", sum=" + sum +
                    '}';
        }
    }

    static class Account {
        private final String number;
        private final Long balance;

        public Account(String number, Long balance) {
            this.number = number;
            this.balance = balance;
        }

        public String getNumber() {
            return number;
        }

        public Long getBalance() {
            return balance;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "number='" + number + '\'' +
                    ", balance=" + balance +
                    '}';
        }
    }

}
