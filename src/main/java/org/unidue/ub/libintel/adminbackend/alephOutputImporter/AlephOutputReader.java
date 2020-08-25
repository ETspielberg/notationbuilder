package org.unidue.ub.libintel.adminbackend.alephOutputImporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unidue.ub.media.contracts.Contract;

import org.springframework.batch.item.ItemReader;
import org.unidue.ub.libintel.adminbackend.clients.JournalGetterClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlephOutputReader implements ItemReader<Contract> {

    private final
    JournalGetterClient journalGetterClient;

    private boolean isRead = false;

    private List<Contract> contracts = new ArrayList<>();

    @Autowired
    public AlephOutputReader(JournalGetterClient journalGetterClient) {
        this.journalGetterClient = journalGetterClient;
    }

    @Override
    public Contract read() {
        if (!isRead) {
            contracts = journalGetterClient.getAllContracts();
            isRead = true;
        }
        if (contracts == null)
            return null;
        if (!contracts.isEmpty()) {
            return contracts.remove(0);
        }
        return null;
    }
}
