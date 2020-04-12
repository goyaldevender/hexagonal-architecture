package com.baeldung.repository;

import java.util.List;
import java.util.Optional;

import com.baeldung.domain.CardHolder;
import com.baeldung.exception.CardHolderNotFoundException;

public interface CardHolderRepository {

    public CardHolder CreateCardHolder(CardHolder cardholder);

    public CardHolder UpdateCardHolder(CardHolder cardHolder) throws CardHolderNotFoundException;

    public List<CardHolder> findAll();

    public Optional<CardHolder> findCardHolderById(int cardHolderId);

}
