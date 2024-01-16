package com.blucharge.puppet.service;

public interface StopTransactionService {
     String sendStopTransactionMessage();
     String sendStopTransactionMessageForTransactionId(Integer transactionId);
}
