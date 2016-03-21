package ru.logstore.repository.mock;

import org.springframework.stereotype.Repository;
import ru.logstore.LoggerWrapper;
import ru.logstore.model.User;
import ru.logstore.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    //public static final Comparator<User> USER_COMPARATOR = (u1, u2) -> u1.getName().compareTo(u2.getName());

    { // hardcode mock DB )
        Integer id = counter.incrementAndGet();
        repository.put(id, new User(id, "user", "user"));
        id = counter.incrementAndGet();
        repository.put(id, new User(id, "admin", "admin"));
        id = counter.incrementAndGet();
        repository.put(id, new User(id, "other", "other"));

    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return null;// repository.values().stream().sorted(USER_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public User getByNameAndPass(String name, String pass) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(pass);
        return repository.values().stream()
                .filter(u -> name.equals(u.getName()))
                .filter(u -> pass.equals(u.getPassword()))
                .findFirst().orElse(null);
    }

    @Override
    public User getByName(String name) {
        Objects.requireNonNull(name);
        return repository.values().stream()
                .filter(u -> name.equals(u.getName()))
                .findFirst().orElse(null);
    }
}
