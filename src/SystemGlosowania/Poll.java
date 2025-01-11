package SystemGlosowania;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poll {
    private String question;
    private List<String> options;
    private Map<String, Integer> votes;

    public Poll(String question, List<String> options) {
        this.question = question;
        this.options = options;
        this.votes = new HashMap<>();
        for (String option : options) {
            votes.put(option, 0);
        }
    }

    public void vote(String option) {
        if (votes.containsKey(option)) {
            votes.put(option, votes.get(option) + 1);
        }
    }

    public String getResults() {
        StringBuilder results = new StringBuilder();
        for (String option : options) {
            results.append(option).append(": ").append(votes.get(option)).append(" votes\n");
        }
        return results.toString();
    }
}
