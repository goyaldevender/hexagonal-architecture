package com.baeldung.adapter.console;

import com.baeldung.adapter.repository.CardHolderRepositoryImpl;
import com.baeldung.service.CardHolderService;
import com.baeldung.service.CardHolderServiceImpl;
import com.baeldung.repository.CardHolderRepository;
import com.google.inject.AbstractModule;

/**
 * Guice module for binding production dependencies
 */
public class CmdLineModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardHolderRepository.class).to(CardHolderRepositoryImpl.class);
        bind(CardHolderService.class).to(CardHolderServiceImpl.class);
        bind(CmdLineInterface.class).to(CmdLineInterfaceImpl.class);
    }
}
