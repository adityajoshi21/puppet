package com.blucharge.puppet.service;

import com.blucharge.puppet.dto.req.RemoteStartTransactionReq;

public interface RemoteTransactionService {
    String remoteStartTransaction(String messageId);
    String remoteStopTransaction(String messageId);
}
