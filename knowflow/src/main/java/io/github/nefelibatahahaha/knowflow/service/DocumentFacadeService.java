package io.github.nefelibatahahaha.knowflow.service;

import io.github.nefelibatahahaha.knowflow.model.request.CreateDocumentRequest;
import io.github.nefelibatahahaha.knowflow.model.request.UpdateDocumentRequest;
import io.github.nefelibatahahaha.knowflow.model.response.CreateDocumentResponse;
import io.github.nefelibatahahaha.knowflow.model.response.GetDocumentsResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentFacadeService {
    GetDocumentsResponse getDocuments();

    GetDocumentsResponse getDocumentsByKbId(String kbId);

    CreateDocumentResponse createDocument(CreateDocumentRequest request);

    CreateDocumentResponse uploadDocument(String kbId, MultipartFile file);

    void deleteDocument(String documentId);

    void updateDocument(String documentId, UpdateDocumentRequest request);
}
