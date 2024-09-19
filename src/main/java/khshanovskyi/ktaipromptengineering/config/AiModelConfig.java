package khshanovskyi.ktaipromptengineering.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiModelConfig {

    @Value("${ollama.model.name}")
    private String ollamaModelName;

    @Value("${ollama.host.url}")
    private String ollamaBaseUrl;

    @Value("${open-ai.api-key}")
    private String openAIApiKey;

    @Value("${open-ai.model.name}")
    private String openAIModelName;

    @Value("${open-ai.enabled}")
    private boolean openAIEnabled;

    /**
     * Provides Bean that will be used for regular calls where answer will be returned when AI finished with its generation
     */
    @Bean
    ChatLanguageModel chatLanguageModel() {
        return openAIEnabled
                ? OpenAiChatModel.builder()
                .modelName(openAIModelName)
                .apiKey(openAIApiKey)
                .build()
                : OllamaChatModel.builder()
                .modelName(ollamaModelName)
                .baseUrl(ollamaBaseUrl)
                .build();
    }

    /**
     * Been that allows answer streaming
     */
    @Bean
    StreamingChatLanguageModel streamingModel() {
        return openAIEnabled
                ? OpenAiStreamingChatModel.builder()
                .modelName(openAIModelName)
                .apiKey(openAIApiKey)
                .build()
                : OllamaStreamingChatModel.builder()
                .modelName(ollamaModelName)
                .baseUrl(ollamaBaseUrl)
                .build();
    }

    /**
     * Been that will be used for creating <a href="https://docs.langchain4j.dev/category/embedding-models/">embeddings</a> from provided documents
     *
     * @return {@link AllMiniLmL6V2QuantizedEmbeddingModel} (just new AllMiniLmL6V2QuantizedEmbeddingModel()) this model is running in
     * memory and doesn't use any LLM's
     * @see <a href="https://docs.langchain4j.dev/integrations/embedding-models/in-process">About onnx AllMiniLmL6V2QuantizedEmbeddingModel model</a>
     */
    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2QuantizedEmbeddingModel();
    }

}
