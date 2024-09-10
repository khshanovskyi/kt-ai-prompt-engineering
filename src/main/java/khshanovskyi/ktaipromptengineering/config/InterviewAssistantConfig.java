package khshanovskyi.ktaipromptengineering.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.service.AiServices;
import khshanovskyi.ktaipromptengineering.assistants.InterviewAssistant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;
import static khshanovskyi.ktaipromptengineering.utils.Utils.createContentRetriever;
import static khshanovskyi.ktaipromptengineering.utils.Utils.embeddingStore;
import static khshanovskyi.ktaipromptengineering.utils.Utils.glob;
import static khshanovskyi.ktaipromptengineering.utils.Utils.toPath;


@Configuration
public class InterviewAssistantConfig {

    /**
     * Creates bean of {@link InterviewAssistant}. Under the hood will be created a proxy that will have all AI components.
     * If you acknowledged with 'chains' from langchain - its alternative of them
     *
     * @see LangchaingConfig
     */
    @Bean
    InterviewAssistant interviewAssistant(ChatLanguageModel chatLanguageModel,
                                          EmbeddingModel embeddingModel) throws URISyntaxException {
        // Loads TXT documents from 'documents/interview/'
        List<Document> documents = loadDocuments(toPath("documents/interview/"), glob("*.txt"));

        return AiServices.builder(InterviewAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10)) //maximum amount of previous questions from user and answers that will be passed with request to AI
                .contentRetriever(
                        createContentRetriever(
                                documents,
                                embeddingStore(),
                                embeddingModel
                                //no splitters provided, so, the document will be fully stored as embedding
                        )
                )
                .build();
    }

}
