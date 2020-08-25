package org.unidue.ub.libintel.adminbackend.alephOutputImporter;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.unidue.ub.libintel.adminbackend.model.journals.Invoice;

@Component
@StepScope
public class AlephOutputProcessor implements ItemProcessor<String, Invoice> {

    @Override
    public Invoice process(final String line) {
        String[] parts = line.split("\t");
        Invoice invoice = new Invoice();
        invoice.setBudgetCode(parts[18]);
        invoice.setCurrency(parts[13]);
        invoice.setInternalId(parts[0]);
        return invoice;
    }
}
