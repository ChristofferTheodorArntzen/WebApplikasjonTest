package codeCase.letterService;

public class LetterSentResponse {

    private boolean sent;

    public LetterSentResponse() {
    }

    public LetterSentResponse(boolean sent) {
        this.sent = sent;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
