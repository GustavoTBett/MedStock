package com.app.medStock.service;

import com.app.medStock.model.Item;
import com.app.medStock.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author @joaopauloazm
 */
@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;
    
    public Item findByItemWithQuantity(final Long id) {

        Optional<Item> item = itemRepository.findById(id);

        if (item.isPresent()) {

            if (Objects.isNull(item.get().getQuantity()) || item.get().getQuantity().longValue() <= 0) {
                throw new RuntimeException(String.format("Item %s sem estoque!", id));
            }

            return item.get();

        } else {
            throw new RuntimeException(String.format("Item %s não encontrado!", id));
        }
    }
}
