package dev.mauriciocoruja.owlnotes.datacache;

import dev.mauriciocoruja.owlnotes.entities.Note;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {
    @Bean
    public CacheStore<Note> noteCache() {
        return new CacheStore<>(10, TimeUnit.SECONDS);
    }
}
