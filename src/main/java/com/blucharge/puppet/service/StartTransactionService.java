package com.blucharge.puppet.service;

public interface StartTransactionService {
    String sendStartTransactionMessage();
    String sendStartTransactionMessageFromIdTag(String idTag);
}
