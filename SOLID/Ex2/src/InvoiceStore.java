public interface InvoiceStore {
    void save(String invId, String content);
    int countLines(String invId);
}