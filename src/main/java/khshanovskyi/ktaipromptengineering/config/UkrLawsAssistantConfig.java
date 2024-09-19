package khshanovskyi.ktaipromptengineering.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.service.AiServices;
import khshanovskyi.ktaipromptengineering.assistants.UkrLawsAssistant;
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
public class UkrLawsAssistantConfig {

    /**
     * Creates bean of {@link UkrLawsAssistant}. Under the hood will be created a proxy that will have all AI components.
     * If you acknowledged with 'chains' from langchain - its alternative of them
     *
     * @see AiModelConfig
     */
    @Bean
    UkrLawsAssistant ukrLawsAssistant(StreamingChatLanguageModel streamingChatLanguageModel,
                                      ChatLanguageModel chatLanguageModel,
                                      EmbeddingModel embeddingModel) throws URISyntaxException {
        // Loads PDF documents from 'documents/laws/' and parse its binary content into text representation
        List<Document> documents = loadDocuments(toPath("documents/laws/"), glob("*.pdf"), new ApachePdfBoxDocumentParser());

        return AiServices.builder(UkrLawsAssistant.class)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20)) //maximum amount of previous questions from user and answers that will be passed with request to AI
                .contentRetriever(
                        createContentRetriever(
                                documents,
                                embeddingStore(),
                                embeddingModel,
                                DocumentSplitters.recursive(300, 50) //splits Document with chunks with 300 characters and with overlap 50 character in each chunk
                        )
                )
                .build();
    }

}
