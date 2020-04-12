package com.baeldung.service;

import java.util.List;
import java.util.Optional;

import com.baeldung.exception.CardHolderNotFoundException;
import com.baeldung.domain.CardHolder;

public interface CardHolderService {

    public CardHolder registerCardHolder(CardHolder cardHolder);

    public CardHolder upgradeCardHolder(CardHolder cardHolder) throws CardHolderNotFoundException;

    public CardHolder downgradeCardHolder(CardHolder cardHolder) throws CardHolderNotFoundException;

    public List<CardHolder> getAllCardHolders();

    public Optional<CardHolder> findCardHolderById(int cardHolderId);

}
