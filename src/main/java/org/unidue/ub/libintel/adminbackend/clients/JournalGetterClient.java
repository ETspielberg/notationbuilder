package org.unidue.ub.libintel.adminbackend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import unidue.ub.media.contracts.Contract;

import java.util.List;

@FeignClient("getter")
@Component
public interface JournalGetterClient {

    @RequestMapping(method= RequestMethod.GET, value="/journalcontract/all")
    List<Contract> getAllContracts();
}
