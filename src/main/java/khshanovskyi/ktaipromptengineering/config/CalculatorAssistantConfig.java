package khshanovskyi.ktaipromptengineering.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import khshanovskyi.ktaipromptengineering.assistants.CalculatorAssistant;
import khshanovskyi.ktaipromptengineering.services.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorAssistantConfig {

    /**
     * Creates bean of {@link CalculatorAssistant}. Under the hood will be created a proxy that will have all AI components.
     * If you acknowledged with 'chains' from langchain - its alternative of them
     *
     * @see LangchaingConfig
     */
    @Bean
    CalculatorAssistant calculatorAssistant(ChatLanguageModel chatLanguageModel, CalculatorService calculatorService)  {
        return AiServices.builder(CalculatorAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10)) //maximum amount of previous questions from user and answers that will be passed with request to AI
                .tools(calculatorService)// AI will use methods with 'Tool' annotation for calculations
                .build();
    }

}
