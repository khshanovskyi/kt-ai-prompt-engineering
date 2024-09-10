package khshanovskyi.ktaipromptengineering.controllers;

import khshanovskyi.ktaipromptengineering.assistants.InterviewAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/interview")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewAssistant assistant;

    /**
     * Regular REST endpoint that produces the answer when AI fully collected it.
     * You can freely test it with Postman
     */
    @GetMapping("/chat")
    public String chat(@RequestParam("question") String question) {
        return assistant.chat(question);
    }
}
