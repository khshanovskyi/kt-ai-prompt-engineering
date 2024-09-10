package khshanovskyi.ktaipromptengineering.services;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CalculatorService {

    @Tool("Calculates sum of two integers")
    public long add(int a, int b) {
        return a + b;
    }

    @Tool("Calculates sum of two and more integers")
    public long add(int... arr) {
        if (arr.length == 0) {
            return 0;
        }
        return Arrays.stream(arr).sum();
    }

    @Tool("Subtract b from a (a - b)")
    public long subtract(long a, long b) {
        return a - b;
    }

    @Tool("Return characters length in question")
    public long questionLength(String question) {
        return question.length();
    }

    @Tool("Return amount of words in question")
    public long questionWordsAmount(String question) {
        return Arrays.stream(question.split(" ")).count();
    }
}
