package topics.executors;

// The class simulates mail sending
// Do not change it
class MockMailSender implements MailSender {
    public void send(String message) {
        System.out.println("Message " + message + " was sent");
    }
}
