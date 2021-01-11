package by.babanin.ems.service;

import org.springframework.data.domain.Page;

public interface PagingService<T> {

    /**
     * Returns a {@link Page} of entities meeting for page number with size
     * @param number is current page number
     * @param size is page size
     * @return a page of entities
     */
    Page<T> getPage(int number, int size);

}
