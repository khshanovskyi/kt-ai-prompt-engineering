package khshanovskyi.ktaipromptengineering.utils;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import lombok.experimental.UtilityClass;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class Utils {

    public static EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    public static ContentRetriever createContentRetriever(List<Document> documents,
                                                          EmbeddingStore<TextSegment> embeddingStore,
                                                          EmbeddingModel embeddingModel) {
        return createContentRetriever(documents, embeddingStore, embeddingModel, null);
    }

    public static ContentRetriever createContentRetriever(List<Document> documents,
                                                          EmbeddingStore<TextSegment> embeddingStore,
                                                          EmbeddingModel embeddingModel,
                                                          DocumentSplitter documentSplitter) {
        embeddingStoreIngestor(embeddingStore, embeddingModel, documentSplitter).ingest(documents);
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }

    public static EmbeddingStoreIngestor embeddingStoreIngestor(EmbeddingStore<TextSegment> embeddingStore,
                                                                EmbeddingModel embeddingModel,
                                                                DocumentSplitter documentSplitter) {
        EmbeddingStoreIngestor.Builder builder = EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore);

        if (documentSplitter != null) {
            builder.documentSplitter(documentSplitter);
        }

        return builder.build();
    }

    public static Path toPath(String relativePath) throws URISyntaxException {
        URL fileUrl = Utils.class.getClassLoader().getResource(relativePath);
        assert fileUrl != null;
        return Paths.get(fileUrl.toURI());
    }

    public static PathMatcher glob(String glob) {
        return FileSystems.getDefault().getPathMatcher("glob:" + glob);
    }
}
